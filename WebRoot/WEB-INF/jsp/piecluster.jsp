<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
            //--- 折柱 ---
            var myChart = ec.init(document.getElementById('main'));
            myChart.setOption({
  
   

    tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    legend: {
        x : 'center',
        y : 'bottom',
        data:[<%=request.getAttribute("label")%>]
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
            type:'pie',
            radius : [35, 120],
            center : ['50%', 200],
            roseType : 'area',
            data:[
              <%=request.getAttribute("clustersize")%>
            ]
        }
    ]
            
        }
    );
    })
    
    </script>
</body>
</html>
