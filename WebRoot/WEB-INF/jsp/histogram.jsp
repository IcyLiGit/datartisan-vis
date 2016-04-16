<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta charset="utf-8">
    <title>ECharts</title>
    <script src="js/echarts/esl.js"></script>    
  </head>
  
 <body>
    <div id="main" style="height:500px;padding:10px;"></div>
    <div>
   
   
    <script type="text/javascript">
    require.config({
        paths:{ 
            echarts:'js/echarts',
            'echarts/chart/bar' : 'js/echarts/echarts-map',
            'echarts/chart/line': 'js/echarts/echarts-map',
            'echarts/chart/map' : 'js/echarts/echarts-map'
        }
    });
    
    require(
        [
            'echarts',
            'echarts/chart/bar',
            'echarts/chart/line',
            'echarts/chart/map'
        ],
        function(ec) {
            //--- 折柱 ---
            var myChart = ec.init(document.getElementById('main'));
            myChart.setOption({
               tooltip : {
        trigger: 'axis'
    },
    toolbox: {
        show : true,
        feature : {
            mark : {show: true},
            dataView : {show: true, readOnly: false},
            magicType: {show: true, type: ['line', 'bar']},
            restore : {show: true},
            saveAsImage : {show: true}
        }
    },
    calculable : true,
    legend: {
        data:[<%=request.getAttribute("category")%>]
    },
    xAxis : [
        {
            type : 'category',
            data : [<%=request.getAttribute("species")%>]
        }
    ],
    yAxis : [
        {
            type : 'value',
            name : '个数',
            axisLabel : {
                formatter: '{value} '
            }
        },
        {
            type : 'value',
            name : '平均个数',
            axisLabel : {
                formatter: '{value} '
            }
        }
    ],
    series : [
<%=request.getAttribute("histogram")%>

    ]
            
        }
    );
    })
    
    </script>

</body>
</html>
