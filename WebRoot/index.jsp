<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Weclome</title>
<link href="css/base.css" rel="stylesheet" type="text/css" />
<link href="css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.8.18.custom.min.js"></script>
<script type="text/javascript" src="js/robot.js"></script>
<script type="text/javascript" src="js/sideDiv.js"></script>
<script type="text/javascript" src="js/menu.js"></script>
<script type="text/javascript" src="js/dragAction.js"></script>

</head>

<body>

	<input type="hidden" style="margin-left: 200px" id="filename"
		value='<%=request.getAttribute("filename")%>' />
	<input type="hidden" style="margin-left: 400px" id="lastfilename"
		value='null' />

	<!-- 侧边-->
	<div id="sideDiv" class="sideDiv">
		<div class="sideDiv_content">
			<div class="message">
				<div class="profile">
					<img src="image/profile.png" />
				</div>
				<p class="charName">小U</p>
				<div id="sendedContent">
					<div id="sended" style="display: block;">
						<!--     
				      	  <h1 >我是可爱的喵  2014/7/31 15:11</h1>
				          <h3> hello </h3> 
				        -->
					</div>
				</div>
				<div class="charMenu">
					<div id="chatExpressionIcon">
						<div id="expressionChoice">
							<ul>
								<li><a href="javascript:;"> <img
										src="image/expression/emo_01.gif" /></a></li>
								<li><a href="javascript:;"> <img
										src="image/expression/emo_02.gif" /></a></li>
								<li><a href="javascript:;"> <img
										src="image/expression/emo_03.gif" /></a></li>
								<li><a href="javascript:;"> <img
										src="image/expression/emo_04.gif" /></a></li>
								<li><a href="javascript:;"> <img
										src="image/expression/emo_05.gif" /></a></li>
								<li><a href="javascript:;"> <img
										src="image/expression/emo_06.gif" /></a></li>
								<li><a href="javascript:;"> <img
										src="image/expression/emo_07.gif" /></a></li>
								<li><a href="javascript:;"> <img
										src="image/expression/emo_08.gif" /></a></li>
								<li><a href="javascript:;"> <img
										src="image/expression/emo_09.gif" /></a></li>
								<li><a href="javascript:;"> <img
										src="image/expression/emo_10.gif" /></a></li>
								<li><a href="javascript:;"> <img
										src="image/expression/emo_11.gif" /></a></li>
								<li><a href="javascript:;"> <img
										src="image/expression/emo_12.gif" /></a></li>
								<li><a href="javascript:;"> <img
										src="image/expression/emo_13.gif" /></a></li>
								<li><a href="javascript:;"> <img
										src="image/expression/emo_14.gif" /></a></li>
								<li><a href="javascript:;"> <img
										src="image/expression/emo_15.gif" /></a></li>
								<li><a href="javascript:;"> <img
										src="image/expression/emo_16.gif" /></a></li>
								<li><a href="javascript:;"> <img
										src="image/expression/emo_17.gif" /></a></li>
								<li><a href="javascript:;"> <img
										src="image/expression/emo_18.gif" /></a></li>
								<li><a href="javascript:;"> <img
										src="image/expression/emo_19.gif" /></a></li>
								<li><a href="javascript:;"> <img
										src="image/expression/emo_20.gif" /></a></li>
								<li><a href="javascript:;"> <img
										src="image/expression/emo_21.gif" /></a></li>
								<li><a href="javascript:;"> <img
										src="image/expression/emo_22.gif" /></a></li>
								<li><a href="javascript:;"> <img
										src="image/expression/emo_23.gif" /></a></li>
								<li><a href="javascript:;"> <img
										src="image/expression/emo_24.gif" /></a></li>
								<li><a href="javascript:;"> <img
										src="image/expression/emo_25.gif" /></a></li>
								<li><a href="javascript:;"> <img
										src="image/expression/emo_26.gif" /></a></li>
								<li><a href="javascript:;"> <img
										src="image/expression/emo_27.gif" /></a></li>
								<li><a href="javascript:;"> <img
										src="image/expression/emo_28.gif" /></a></li>
								<li><a href="javascript:;"> <img
										src="image/expression/emo_29.gif" /></a></li>
								<li><a href="javascript:;"> <img
										src="image/expression/emo_30.gif" /></a></li>
								<li><a href="javascript:;"> <img
										src="image/expression/emo_31.gif" /></a></li>
								<li><a href="javascript:;"> <img
										src="image/expression/emo_32.gif" /></a></li>
								<li><a href="javascript:;"> <img
										src="image/expression/emo_33.gif" /></a></li>
								<li><a href="javascript:;"> <img
										src="image/expression/emo_34.gif" /></a></li>
								<li><a href="javascript:;"> <img
										src="image/expression/emo_35.gif" /></a></li>
								<li><a href="javascript:;"> <img
										src="image/expression/emo_36.gif" /></a></li>
								<li><a href="javascript:;"> <img
										src="image/expression/emo_37.gif" /></a></li>
								<li><a href="javascript:;"> <img
										src="image/expression/emo_38.gif" /></a></li>
								<li><a href="javascript:;"> <img
										src="image/expression/emo_39.gif" /></a></li>
								<li><a href="javascript:;"> <img
										src="image/expression/emo_40.gif" /></a></li>
								<li><a href="javascript:;"> <img
										src="image/expression/emo_41.gif" /></a></li>
								<li><a href="javascript:;"> <img
										src="image/expression/emo_42.gif" /></a></li>
								<li><a href="javascript:;"> <img
										src="image/expression/emo_43.gif" /></a></li>
								<li><a href="javascript:;"> <img
										src="image/expression/emo_44.gif" /></a></li>
								<li><a href="javascript:;"> <img
										src="image/expression/emo_45.gif" /></a></li>
								<li><a href="javascript:;"> <img
										src="image/expression/emo_46.gif" /></a></li>
								<li><a href="javascript:;"> <img
										src="image/expression/emo_47.gif" /></a></li>
								<li><a href="javascript:;"> <img
										src="image/expression/emo_48.gif" /></a></li>
								<li><a href="javascript:;"> <img
										src="image/expression/emo_49.gif" /></a></li>
								<li><a href="javascript:;"> <img
										src="image/expression/emo_50.gif" /></a></li>
								<li><a href="javascript:;"> <img
										src="image/expression/emo_51.gif" /></a></li>
								<li><a href="javascript:;"> <img
										src="image/expression/emo_52.gif" /></a></li>
								<li><a href="javascript:;"> <img
										src="image/expression/emo_53.gif" /></a></li>
								<li><a href="javascript:;"> <img
										src="image/expression/emo_54.gif" /></a></li>
								<li><a href="javascript:;"> <img
										src="image/expression/emo_55.gif" /></a></li>
								<li><a href="javascript:;"> <img
										src="image/expression/emo_56.gif" /></a></li>
								<li><a href="javascript:;"> <img
										src="image/expression/emo_57.gif" /></a></li>
								<li><a href="javascript:;"> <img
										src="image/expression/emo_58.gif" /></a></li>
								<li><a href="javascript:;"> <img
										src="image/expression/emo_59.gif" /></a></li>
								<li><a href="javascript:;"> <img
										src="image/expression/emo_60.gif" /></a></li>
							</ul>
						</div>
					</div>
				</div>
				<textarea id="sending">
  </textarea>
				<input type="button" value="发送" id="sendButton" />
			</div>
		</div>
		<div class="sideDiv_ctrlBlock">
			<div id="slideOutButton"
				onmouseover="chang_sideDiv_ctrlBlock_button(2)"
				onmouseout="chang_sideDiv_ctrlBlock_button(1)"></div>
		</div>
	</div>
	<div class="wrap">

		<!-- 菜单-->
		<div class="menu" id="menu">
			<div class="window">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
			<div class="menu_section">
				<h3 class="title">
					上传文件<span class="line"></span>
				</h3>
				<form method="post" action="UploadServlet"
					ENCTYPE="multipart/form-data">
					<ul class="menu_list">
						<li><input type='text' name='textfield' id='textfield'
							class='fileTxt' /> <input type='button' class='btn' value='浏览' />
							<input type="file" name="fileField" class="fileInput"
							id="fileField" size="28"
							onchange="document.getElementById('textfield').value=this.value" />
							<input type="submit" value="上传" name="submit"
							class="submitButton"></li>
					</ul>
				</form>
			</div>
			<div class="menu_section">
				<h3 class="title">
					功能选择<span class="line"></span>
				</h3>
				<ul class="menu_list functionMenu">
					<li><a>数据统计 <span class="secondMenuIcon"
							id="dataStatistics">∨ </span>
					</a>
						<ul class="secondMenuList " id="dataStatisticsList">
							<li><a class="Pie">Pie </a></li>
							<li><a class="StackPie">StackPie </a></li>
							<li><a class="Histogram">Histogram </a></li>
							<li><a class="Scatter3d">Scatter3d </a></li>
							<li><a class="Parallel">Parallel </a></li>
							<li><a class="ScatterMatrix">ScatterMatrix </a></li>
						</ul></li>
					<li><a>关联分析 <span class="secondMenuIcon"
							id="associationAnalysis">∨ </span>
					</a>
						<ul class="secondMenuList " id="associationAnalysisList">
							<li><a class="AprMatrix">Matrix </a></li>
							<li><a class="AprBubble">AprBubble </a></li>
						</ul></li>
					<li><a>聚类分析<span class="secondMenuIcon"
							id="clusterAnalysis">∨ </span>
					</a>
						<ul class="secondMenuList " id="clusterAnalysisList">
							<li><a class="CluPie">Pie </a></li>
							<li><a class="CluRadar">Radar</a></li>
							<li><a class="CluScatter">Scatter </a></li>
						</ul></li>
					<li><a>决策树<span class="secondMenuIcon" id="dcisionTree">∨
						</span>
					</a>
						<ul class="secondMenuList " id="dcisionTreeList">
							<li><a class="TreeMap">TreeMap </a></li>
							<li><a class="PartitionTree">PartitionTree </a></li>
							<li><a class="TilfordTree">TilfordTree </a></li>
						</ul></li>
					<li><a>网络图<span class="secondMenuIcon" id="Network">∨
						</span>
					</a>
						<ul class="secondMenuList " id="NetworkList">
							<li><a class="Hierarchical">Hierarchical </a></li>
						</ul></li>
				</ul>
			</div>

			<!-- <div class="returnToTop"><a href="#top"><img src="./image/top.png"
					style="width: 40px; height: 40px;"></a>	</div> -->

			<div class="fileInfo">
				<div class="menu_section">
					<h3 class="title">
						样例数&nbsp;&nbsp;&nbsp;<span class="line"></span>
					</h3>
					<p>
						共有<span class="fileNum"></span>个样例
					</p>
				</div>
				<div class="menu_section">
					<h3 class="title">
						species<span class="line"></span>
					</h3>
					<select class="fileSpecies" multiple="multiple">
					</select>
				</div>
				<div class="menu_section">
					<h3 class="title">
						Numeric<span class="line"></span>
					</h3>
					<select class="NumericSelected" id="NumericSelected"
						multiple="multiple">
					</select>

					<div class="selectButton NumericSelectedButtons">
						<input type='button' value='全选' class="selectAll"/> 
						<input type='button'
							value='完成' class="complete"/>
					</div>

					<div class="showNumericToSelect">
						<p>打开候选属性</p>
					</div>

				</div>
				<div class="menu_section">
					<h3 class="title">
						Nominal<span class="line"></span>
					</h3>
					<select class="NominalSelected" id="NominalSelected"
						multiple="multiple">
					</select>

					<div class="selectButton NominalSelectedButtons">
						<input type='button' value='全选' class="selectAll"/> 
						<input type='button'
							value='完成' class="complete"/>
					</div>



					<div class="showSideBottom showNominalToSelect">
						<p>打开候选属性</p>
					</div>
				</div>
				<!--  <div class="menu_section">
					<h3 class="title">
						Attribute selected<span class="line"></span>
					</h3>
					<span class="fileAttselected"></span>
				</div>-->
				<div class="attrToSelected NumericToSelectDiv">
					<select id="NumericToSelect" multiple="multiple">
					</select>
					<div class="selectButton selectButton2 NumericToSelectButtons">
						<input type='button' value='全选' class="selectAll"/> 
						<input type='button'
							value='完成' class="complete"/>
					</div>	
					
				</div>
				<div class="attrToSelected NominalToSelectDiv">
					<select id="NominalToSelect" multiple="multiple">
					</select>
						<div class="selectButton selectButton2 NominalToSelectButtons">
						<input type='button' value='全选' class="selectAll"/> 
						<input type='button'
							value='完成' class="complete"/>
					</div>
				</div>
			</div>



			<div class="returnToTop openFileInfo">
				<a><img src="./image/top.png" style="width: 40px; height: 40px;"></a>

			</div>

		</div>


		<!-- 显示的内容-->
		<div class="resultViewWrap">
				<div class="resultView resultShow" >
				<div class="window">
					<img src="./image/button-close.png" class="closeWindow"> <img
						src="./image/button-refresh.png" class="refreshWindow">
				</div>
				<img src="./image/Datartisan_bg.png" >
			</div>
		</div>
		<!--  
		<div class="resultView resultShow" >
				<div class="window">
					<img src="./image/button-close.png" class="closeWindow"> <img
						src="./image/button-refresh.png" class="refreshWindow">
				</div>
				<iframe class="iframe" src="TilfordTreeServlet" scrolling="no"
					frameBorder="no" width="120px" height="479px"> </iframe>
			</div>
		
		
		
		
			<div class="resultView" id="resultView">
				<div class="window">
					<img src="./image/button-close.png" class="closeWindow"> <img
						src="./image/button-refresh.png" class="refreshWindow">
				</div>
				<iframe class="iframe" src="./ScatterMatrixServlet" scrolling="no"
					frameBorder="no" width="120px" height="479px"> </iframe>
			</div>
		
		
		TilfordTreeServlet
   PieServlet
   HistogramServlet
	  <jsp:include page="/TreeMapServlet" flush="true"   /> 
	  <iframe class="content" src="./TreeMapServlet" 	
    scrolling="no"  frameBorder="no"
    >  </iframe> 
   -->
		<!--footer -->
		<div class="footer"></div>
	</div>
</body>
</html>