<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>


<html>
  <head>
    <meta charset="utf-8">
    <title>ECharts</title>
    <script src="js/echarts/esl.js"></script> 
    
       
  </head>
  
 <body>
    <div id="main" style="height:500px;padding:10px;"></div>
    <script type="text/javascript">
    require.config({
        paths:{ 
            echarts:'js/echarts',
            'echarts/chart/pie' : 'js/echarts/echarts-map',
            'echarts/chart/map' : 'js/echarts/echarts-map'
        }
    });
    
    require(
        [
            'echarts',
            'echarts/chart/pie',
            'echarts/chart/map'
        ],
        function(ec) {

            var myChart = ec.init(document.getElementById('main'));
            myChart.setOption({
  
     title : {
        text: '<%=request.getAttribute("attribute")%>',
        x:'center'
    },
    tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    legend: {
        orient : 'vertical',
        x : 'left',
        data:[<%=request.getAttribute("category")%>]
    },
    toolbox: {
        show : true,
        feature : {
            mark : {show: true},
            dataView : {show: true, readOnly: false},
            restore : {show: true},
            saveAsImage : {show: true}
        }
    },
    calculable : true,
    series : [
        {
            name:'访问来源',
            type:'pie',
            radius : '55%',
            center: ['50%', '60%'],
            data:[
              <%=request.getAttribute("pie")%>
            ]
        }
    ]
   
    
        }
    );
    })
    
    </script>
</body>
</html>
