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
  
   
    tooltip : {
        trigger: 'axis'
    },
    legend: {
        x : 'center',
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
    polar : [
        {
            indicator : [
               <%=request.getAttribute("polar")%>
            ],
            radius : 130
        }
    ],
    series : [
        {
            name: 'Cluster',
            type: 'radar',
            itemStyle: {
                normal: {
                    areaStyle: {
                        type: 'default'
                    }
                }
            },
            data : [
              <%=request.getAttribute("radar")%>
            ]
        }
    ]

            
        }
    );
    })
    
    </script>
</body>
</html>
