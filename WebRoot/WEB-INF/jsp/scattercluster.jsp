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
  
   
   
   title : {
    },
    tooltip : {
        trigger: 'axis',
        showDelay : 0,
        axisPointer:{
            type : 'cross',
            lineStyle: {
                type : 'dashed',
                width : 1
            }
        },
        formatter : function (value) {
            if (value[2].length > 1) {
                return value[0] + ' :<br/>'
                   + value[2][0] + 'cm ' 
                   + value[2][1] + 'cm ';
            }
            else {
                return value[0] + ' :<br/>'
                   + value[1] + ' : '
                   + value[2] + 'cm ';
            }
        }
    },
    legend: {
        data:[<%=request.getAttribute("label")%>]
    },
    toolbox: {
        show : true,
        feature : {
            mark : {show: true},
            dataZoom : {show: true},
            dataView : {show: true, readOnly: false},
            restore : {show: true},
            saveAsImage : {show: true}
        }
    },
    xAxis : [
        {
            type : 'value',
            name :'<%=request.getAttribute("xaxis")%>',
            power: 1,
            precision: 2,
            scale:true,
            axisLabel : {
                formatter: '{value} cm'
            }
        }
    ],
    yAxis : [
        {
            type : 'value',
            name :'<%=request.getAttribute("yaxis")%>',
            power: 1,
            precision: 2,
            scale:true,
            axisLabel : {
                formatter: '{value} cm'
            }
        }
    ],
    series : [
      <%=request.getAttribute("scatter")%>
    ]
            
        }
    );
    })
    
    </script>
</body>
</html>
