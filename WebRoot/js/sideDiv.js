$(document).ready(function() {		
 	var sideDiv_Left=(0.34-0.014)*$(window).width();
	var speed=300;
	
	$("#sideDiv").css("left",-sideDiv_Left);
	$("#sending").val("");	
	$("#sideDiv").css("width",0.34*$(window).width());
	$("#sideDiv").css("top",window.pageYOffset+'px');
	// document.getElementById('sideDiv').style.top=window.pageYOffset+'px';
	$("#menu").css("top",window.pageYOffset+'px');
	  
	$(".resultView").width(0.56*$(window).width());
	$('.iframe').width($(".resultView").width()-40);
	$('.resultView .window').css("width",$('.resultView').width()-100+"px");	
	
	

	
	
	
	
	
	var xmlhttp=new XMLHttpRequest();
	xmlhttp.open("GET","UploadServlet",true);
	xmlhttp.send();
	
	getResponseDiv("你好");
	
	var cnt=0;
	
	$("#slideOutButton").click(function(){	
		if(!cnt){		
				$("#sideDiv").animate({left:'0px'},speed,function(){	
			document.getElementById("slideOutButton").style.transform="rotate(0deg)";
				cnt=1;
				});	
		}
		else{
				$("#sideDiv").animate({left:-sideDiv_Left+'px'},speed,function(){	
				document.getElementById("slideOutButton").style.transform="rotate(180deg)";
				cnt=0;
				});	
		}
	});

}).scroll(function(){
 $("#sideDiv").css("top",window.pageYOffset+'px');
// document.getElementById('sideDiv').style.top=window.pageYOffset+'px';
 $("#menu").css("top",window.pageYOffset+'px');
});


function chang_sideDiv_ctrlBlock_button(flag){
	if(flag==2)
		document.getElementById("slideOutButton").style.backgroundImage="url('image/sideDiv_ctrlBlock_button_02.png')";		
	else
		document.getElementById("slideOutButton").style.backgroundImage="url('image/sideDiv_ctrlBlock_button_01.png')";	
}
