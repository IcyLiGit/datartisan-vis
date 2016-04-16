$(function() {

	$("#menu").draggable({
		cursor : "move",
		revert : "invalid",
		handle : ".window"
	});

	// $(".functionMenu").sortable();

	// 排序，全选，添加
	$(".openFileInfo").mousedown(function() {
		// "${sessionScope.引用明}";
		flashFileInfo();
		// alert(filename);
		$(".fileInfo").fadeToggle();
	});

	$(".submitButton").click(function() {
		// alert($("#filename").val());
		// filename=$("#filename").val();
	})

	$(".secondMenuList li a").mouseover(function() {
		$(this).css("backgroundColor", "#eff3f1");
	}).mouseout(function() {
		$(this).css("backgroundColor", "");
	}).mousedown(function() {

		$(".fileInfo").hide();

		// alert(NominalSelected);
		var classname = $(this).attr("class");

		// var obj = $(this);
		attemptCreateResultView(classname);
	})

});

function attemptCreateResultView(classname) {
	$.ajax({
		url : getResultViewURL(classname),
		dataType : 'json',
		error : function() {
			newResultView(classname);
		},
		success : function(data) {
			if (data.tag == "false")
				alert("sorry~ 您的数据不支持该图形");
			// else
			// newResultView(obj);
		}
	});
}

function getResultViewURL(classname) {
	// alert("ok");
	var NumericSelected = ""; // 定义数组
	var fromObj = document.getElementById("NumericSelected");
	// alert(fromObj.length);
	for (var i = 0; i < fromObj.length; i++)
		NumericSelected = NumericSelected + fromObj[i].text + ",";

	// alert(NumericSelected);
	var NominalSelected = "";
	fromObj = document.getElementById("NominalSelected");
	for (var i = 0; i < fromObj.length; i++)
		NominalSelected = NominalSelected + fromObj[i].text + ",";

	return classname + 'Servlet' + '?filename=' + $("#filename").val()
			+ '&NumericSelected=' + NumericSelected + '&NominalSelected='
			+ NominalSelected;

}

function flashFileInfo() {
	if ($("#lastfilename").val() == $("#filename").val())
		return;

	$.ajax({
		url : 'ArffDetailServlet' + '?filename=' + $("#filename").val(),
		dataType : 'json', // 预期服务器返回的数据类型
		error : function() {
			alert("ajax reer");
		},
		success : function(data) {
			$(".fileInfo .menu_section .fileNum").text(data.num);

			var species = data.species.split(',');
			for (var i = 0; i < species.length; i++) {
				var option = $("<option>").val(species[i]).text(species[i]);
				$(".fileInfo .menu_section .fileSpecies").append(option);
			}
			// $(".fileInfo .menu_section
			// .fileSpecies").text(data.species);

			var numeric = data.numeric.split(',');
			for (var i = 0; i < numeric.length; i++) {
				var option = $("<option>").val(numeric[i]).text(numeric[i]);
				$(".fileInfo .menu_section .NumericSelected").append(option);
			}

			var nominal = data.nominal.split(',');

			for (var i = 0; i < nominal.length; i++) {
				var option = $("<option>").val(nominal[i]).text(nominal[i]);
				$(".fileInfo .menu_section .NominalSelected").append(option);
			}

			// $(".fileInfo .menu_section
			// .fileNominal").text(data.nominal);
			$(".fileInfo .menu_section .fileAttselected")
					.text(data.attselected);
			$("#lastfilename").val($("#filename").val());
		}
	});

}

function change(fromObj, toObj) {
	for (var i = 0; i < fromObj.length; i++) {
		if (fromObj[i].selected) {
			toObj.appendChild(fromObj[i]);
			i--;
		}
	}
}

$(function() {

	$(".NumericSelectedButtons .selectAll").click(function(){
		var obj = document.getElementById("NumericSelected");
		for(var i=0;i<obj.length;i++)
				obj[i].selected="true";				
	})
	
	$(".NumericSelectedButtons .complete").click(function(){
		var fromObj = document.getElementById("NumericSelected");
		var toObj = document.getElementById("NumericToSelect");
		change(fromObj, toObj);
	})
	
	$(".NominalSelectedButtons .selectAll").click(function(){
		var obj = document.getElementById("NominalSelected");
		for(var i=0;i<obj.length;i++)
				obj[i].selected="true";				
	})
	
	$(".NominalSelectedButtons .complete").click(function(){
		var fromObj = document.getElementById("NominalSelected");
		var toObj = document.getElementById("NominalToSelect");
		change(fromObj, toObj);
	})
	
	
	
	
	$(".NumericToSelectButtons .selectAll").click(function(){
		var obj = document.getElementById("NumericToSelect");
		for(var i=0;i<obj.length;i++)
				obj[i].selected="true";				
	})
	
	$(".NumericToSelectButtons .complete").click(function(){
		var  toObj= document.getElementById("NumericSelected");
		var  fromObj= document.getElementById("NumericToSelect");
		change(fromObj, toObj);
	})
	
	
	
	$(".NominalToSelectButtons .selectAll").click(function(){
		var obj = document.getElementById("NominalToSelect");
		for(var i=0;i<obj.length;i++)
				obj[i].selected="true";				
	})
	
	$(".NominalToSelectButtons .complete").click(function(){
		var  toObj = document.getElementById("NominalSelected");
		var fromObj= document.getElementById("NominalToSelect");
		change(fromObj, toObj);
	})
	
	$("#NumericSelected").dblclick(function() {
		var fromObj = document.getElementById("NumericSelected");
		var toObj = document.getElementById("NumericToSelect");
		change(fromObj, toObj);

	});

	$("#NumericToSelect").dblclick(function() {
		var toObj = document.getElementById("NumericSelected");
		var fromObj = document.getElementById("NumericToSelect");
		change(fromObj, toObj);
	});

	$("#NominalSelected").dblclick(function() {
		var fromObj = document.getElementById("NominalSelected");
		var toObj = document.getElementById("NominalToSelect");
		change(fromObj, toObj);

	});

	$("#NominalToSelect").dblclick(function() {

		var toObj = document.getElementById("NominalSelected");
		var fromObj = document.getElementById("NominalToSelect");
		change(fromObj, toObj);
	});

	$(".menu .fileInfo .showNumericToSelect").mousedown(function() {
		$(".NumericToSelectDiv").fadeToggle();
		$(".NominalToSelectDiv").hide();
	});

	$(".menu .fileInfo .showNominalToSelect").mousedown(function() {
		$(".NominalToSelectDiv").fadeToggle();
		$(".NumericToSelectDiv").hide();
	});

	$("#dataStatistics").mouseover(function() {
		$("#dataStatisticsList").fadeIn();
	}).mouseout(function() {
		$("#dataStatisticsList").hide();
	})

	$("#dataStatisticsList").mouseover(function() {
		$("#dataStatisticsList").show();
	}).mouseout(function() {
		$("#dataStatisticsList").hide();
	})

	$("#associationAnalysis").mouseover(function() {
		$("#associationAnalysisList").fadeIn();
	}).mouseout(function() {
		$("#associationAnalysisList").hide();
	})

	$("#associationAnalysisList").mouseover(function() {
		$("#associationAnalysisList").show();
	}).mouseout(function() {
		$("#associationAnalysisList").hide();
	})

	$("#clusterAnalysis").mouseover(function() {
		$("#clusterAnalysisList").fadeIn();
	}).mouseout(function() {
		$("#clusterAnalysisList").hide();
	})

	$("#clusterAnalysisList").mouseover(function() {
		$("#clusterAnalysisList").show();
	}).mouseout(function() {
		$("#clusterAnalysisList").hide();
	})

	$("#dcisionTree").mouseover(function() {
		$("#dcisionTreeList").fadeIn();
	}).mouseout(function() {
		$("#dcisionTreeList").hide();
	})

	$("#dcisionTreeList").mouseover(function() {
		$("#dcisionTreeList").show();
	}).mouseout(function() {
		$("#dcisionTreeList").hide();
	})

	$("#Network").mouseover(function() {
		$("#NetworkList").fadeIn();
	}).mouseout(function() {
		$("#NetworkList").hide();
	})

	$("#NetworkList").mouseover(function() {
		$("#NetworkList").show();
	}).mouseout(function() {
		$("#NetworkList").hide();
	})

	$(".functionMenu >li >a").mouseover(function() {
		$(this).css("backgroundColor", "#bbc9c4");
	}).mouseout(function() {
		$(this).css("backgroundColor", "rgba(198,211,205,0.5)");
	})

});
