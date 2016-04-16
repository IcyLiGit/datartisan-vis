$(function(){
	$("#chatExpressionIcon").mouseover(	function(){
		$("#expressionChoice").show()
	}).mouseout(function(){
			$("#expressionChoice").hide();
	}),
	$("#expressionChoice").mouseover(function(){
		$("#expressionChoice").show()	
	}).mouseout(function(){
			$("#expressionChoice").hide();
	}),
	$("#sendButton").click(function(){			
		startRobot();	
	}),
	$("#expressionChoice img").click(function(){
			var temp=$(this).attr("src");	
			$("#sending").val(  $ ("#sending").val()+  "*#" +temp.substr(temp.indexOf("image/expression/")+17,6 ) +"#*"),
			$("#expressionChoice").hide();	
	})
});


	function showPicture(curm)
	{
		// *#emo_50#*
		// alert(curm);
		(curm.indexOf("*#emo_") !=-1  )&&  ( curm=curm.replace("*#","<img src='image/expression/").replace("#*", ".gif'/>"), showPicture(curm));
		return curm;	
	}
	function getUserWordDiv(message){
		var newMessage='<h1 style="color:#ff7f50">&#x8001;&#x5927; '+  getCurTime()+ '</h1> <h3>' +  message +' </h3>	' ;
		null!=message && ""!=message?  (
		$ ("#sended") .append(newMessage),
		$ ("#sendedContent").scrollTop(  $ ("#sended") .height() ), 
		$("#sending").val("")	)	
		:(alert("\u8bf7\u8f93\u5165\u804a\u5929\u5185\u5bb9!"));	
	}
	
	
	function getResponseDiv(message)
	{
		if(message.length==0) return;
		var queryString="foranswer="+message;
		var xmlhttp=new XMLHttpRequest();
		xmlhttp.open("POST","RobotResponse",true);
		xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded; charset=UTF-8");
		xmlhttp.send(queryString);
		
		xmlhttp.onreadystatechange=function()
		  {
		  if (xmlhttp.readyState==4 && xmlhttp.status==200)
		    {						
			 var message=xmlhttp.responseText+"";
			 var returnMessage=message.substring(0,message.length-1);
			 var message1=returnMessage;
			 returnMessage=returnMessage.split(" ");
	
			//alert(returnMessage[1]);
			 var newMessage1='<h1 style="color:#179ef2">小U '+  getCurTime()+ '</h1> <h3>';
			 
			 if(returnMessage[0]=="return")
			 {
				 
				 if(returnMessage[1]=="dataStatistics")
				 {
					// alert("数据分析");
					 if(returnMessage[2]!=null) {
						 newMessage1+="小U正在努力的帮你打开图形~"
						 attemptCreateResultView(returnMessage[2]);		
					 }
					 else{
						 newMessage1+="点击相应菜单即可打开哦</h3><h3>";
						 newMessage1+=' <ul class="secondMenuList " >'
						+'<li><a class="Pie" onmousedown="newLister(event)">饼状图 </a></li>'	
						+'	<li><a class="StackPie" onmousedown="newLister(event)">层叠饼图 </a></li>'
						+'	<li><a class="Histogram" onmousedown="newLister(event)">柱状图 </a></li>'
						+' <li><a class="Scatter3d" onmousedown="newLister(event)">散点图 </a></li>'
						+' <li><a class="Parallel" onmousedown="newLister(event)">平行坐标 </a></li>'
						+' <li><a class="ScatterMatrix" onmousedown="newLister(event)">散点矩阵 </a></li>'
						+'</ul>';	
					 }					
				 }
				 else if(returnMessage[1]=="associationAnalysis")
				{
				
					 if(returnMessage[2]!=null) {
						 newMessage1+="小U正在努力的帮你打开图形~"
						 attemptCreateResultView(returnMessage[2]);		
					 }
					 else{
						 newMessage1+="点击相应菜单即可打开哦</h3><h3>";
						 newMessage1+=' <ul class="secondMenuList " >'
						+'<li><a class="AprMatrix" onmousedown="newLister(event)">矩阵 </a></li>'	
						+'	<li><a class="AprBubble" onmousedown="newLister(event)">气泡图 </a></li>'
						+'</ul>';
					 }
											 
				}
				 else if(returnMessage[1]=="clusterAnalysisList")
				 { 
					 if(returnMessage[2]!=null) {
						 newMessage1+="小U正在努力的帮你打开图形~"
						 attemptCreateResultView(returnMessage[2]);		
					 }
					 else{
						 newMessage1+="点击相应菜单即可打开哦</h3><h3>";
						 newMessage1+=' <ul class="secondMenuList " >'
						+'<li><a class="CluPie" onmousedown="newLister(event)">饼状图(玫瑰图) </a></li>'	
						+'	<li><a class="CluRadar" onmousedown="newLister(event)">雷达图 </a></li>'
						+'	<li><a class="CluScatter" onmousedown="newLister(event)">散点图 </a></li>'
						+'</ul>';	
					 }
					 				
				 }
				 else if(returnMessage[1]=="dcisionTreeList")
				{
					 if(returnMessage[2]!=null) {
						 newMessage1+="小U正在努力的帮你打开图形~"
						 attemptCreateResultView(returnMessage[2]);		
					 }
					 else{
						 newMessage1+="点击相应菜单即可打开哦</h3><h3>";
						 newMessage1+=' <ul class="secondMenuList " >'
						+'<li><a class="TreeMap" onmousedown="newLister(event)">分层树 </a></li>'	
						+'	<li><a class="PartitionTree" onmousedown="newLister(event)">冰柱树 </a></li>'
						+' <li><a class="TilfordTree" onmousedown="newLister(event)">环形树 </a></li>'
						+'</ul>';
					 }
						
				}
				 else if(returnMessage[1]=="NetworkList")
				{
					 if(returnMessage[2]!=null) {
						 newMessage1+="小U正在努力的帮你打开图形~"
						 attemptCreateResultView(returnMessage[2]);		
					 }
					 else{
						 newMessage1+="点击相应菜单即可打开哦</h3><h3>";
						 newMessage1+=' <ul class="secondMenuList " >'
						+'<li><a class="Hierarchical" onmousedown="newLister(event)">弦图 </a></li>'					
						+'</ul>';
					 }
					 
				}
		    }
			 else{
				  newMessage1+=  message1 ;						
			 }
			// alert(message1);
		    // document.getElementById("myDiv").innerHTML=xmlhttp.responseText;
			 newMessage1 +=' </h3>' 
				$ ("#sended") .append(newMessage1),
				$ ("#sendedContent").scrollTop(  $ ("#sended") .height() ), 
				$("#sending").val("");
		
		    }
		  }

	}
 
	function getCurTime()
	{
		var date=new Date;
		var time=date.getFullYear()+'/';
		time+=date.getMonth()+1+'/'+date.getDate()+ "  ";
		time+=date.getHours() + ":" + date.getMinutes() + ":"+date.getSeconds();
		return time;
	}
	
	function startRobot()
	{
		var message=$("#sending").val();
		message=showPicture(message);
		var test=message.replace( /^\s*/, '');
		if(test.length==0)
		{
			alert("\u8bf7\u8f93\u5165\u804a\u5929\u5185\u5bb9!");
			return;
		}
		
		getUserWordDiv(message);			
		getResponseDiv(message);	
	}
	
function newLister(e)
{				
	
	var targ;
	if (!e) var e = window.event;
	if (e.target) targ = e.target;
	else if (e.srcElement) targ = e.srcElement;
	if (targ.nodeType == 3) // defeat Safari bug
	   targ = targ.parentNode;
	obj=targ;
	
	var classname=obj.className;
	// alert(classname);
	attemptCreateResultView(classname);		
}
	