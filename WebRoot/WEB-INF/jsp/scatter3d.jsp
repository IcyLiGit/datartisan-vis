<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>3dscatter Example</title>

<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
<script type="text/javascript">
	$(function() {

		// Give the points a 3D feel by adding a radial gradient
		Highcharts.getOptions().colors = $.map(Highcharts.getOptions().colors,
				function(color) {
					return {
						radialGradient : {
							cx : 0.4,
							cy : 0.3,
							r : 0.5
						},
						stops : [
								[ 0, color ],
								[
										1,
										Highcharts.Color(color).brighten(-0.2)
												.get('rgb') ] ]
					};
				});

		// Set up the chart
		var chart = new Highcharts.Chart({
			chart : {
				renderTo : 'container',
				margin : 100,
				type : 'scatter',
				backgroundColor: 'rgba(0,0,0,0)',
				options3d : {
					enabled : true,
					alpha : 10,
					beta : 30,
					depth : 250,
					viewDistance : 5,

					frame : {
						bottom : {
							size : 1,
							color : 'rgba(0,0,0,0.02)'
						},
						back : {
							size : 1,
							color : 'rgba(0,0,0,0.04)'
						},
						side : {
							size : 1,
							color : 'rgba(0,0,0,0.06)'
						}
					}
				}
			},
		 	credits:{
	            enabled:false 
	       },
			title : {
				text : 'Attribute Compare'
			},
			subtitle : {
				text : 'Click and drag the plot area to rotate in space'
			},
			plotOptions : {
				scatter : {
					width : 10,
					height : 10,
					depth : 10
				}
			},
			xAxis : {
				categories : [ '0',
<%=request.getAttribute("xlabel")%>
	]

			},
			yAxis : {
				categories : [ '0',
<%=request.getAttribute("ylabel")%>
	]

			},
			zAxis : {
				min : 0,
				max :
<%=request.getAttribute("znum")%>
	},
			legend : {
				enabled : false
			},
			series : [ {
				name : 'Reading',
				colorByPoint : true,
				data : [
<%=request.getAttribute("scatter3d")%>
	]
			} ]
		});

		// Add mouse events for rotation
		$(chart.container)
				.bind(
						'mousedown.hc touchstart.hc',
						function(e) {
							e = chart.pointer.normalize(e);

							var posX = e.pageX, posY = e.pageY, alpha = chart.options.chart.options3d.alpha, beta = chart.options.chart.options3d.beta, newAlpha, newBeta, sensitivity = 5; // lower is more sensitive

							$(document)
									.bind(
											{
												'mousemove.hc touchdrag.hc' : function(
														e) {
													// Run beta
													newBeta = beta
															+ (posX - e.pageX)
															/ sensitivity;
													newBeta = Math.min(100,
															Math.max(-100,
																	newBeta));
													chart.options.chart.options3d.beta = newBeta;

													// Run alpha
													newAlpha = alpha
															+ (e.pageY - posY)
															/ sensitivity;
													newAlpha = Math.min(100,
															Math.max(-100,
																	newAlpha));
													chart.options.chart.options3d.alpha = newAlpha;

													chart.redraw(false);
												},
												'mouseup touchend' : function() {
													$(document).unbind('.hc');
												}
											});
						});

	});
</script>
</head>
<body>

	<script src="js/hightchart/highcharts.js"></script>
	<script src="js/hightchart/highcharts-3d.js"></script>
	<script src="js/hightchart/modules/exporting.js"></script>

	<div id="container" style="height: 400px"></div>
</body>
</html>
