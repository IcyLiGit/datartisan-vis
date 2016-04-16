package com.lqb.robot;

import ICTCLAS.I3S.AC.ICTCLAS50;

import java.util.*;
import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import multi.patt.match.ac.AcApply;

public class RobotResponse extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String pre_path = new File("").getAbsolutePath() + "\\ICTCLASConf";

	protected void doGet(HttpServletRequest request,
						 HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
						  HttpServletResponse response) throws ServletException, IOException {
		//request.setCharacterEncoding("UTF-8");
		String foranswer = request.getParameter("foranswer");
		//System.out.println("xxxx" + foranswer);

		response.setContentType("text/xml;charset=UTF-8");
		// request.setAttribute("getResponse", getResponse("哈哈"));
		pre_path = request.getRealPath("reply")+File.separatorChar;

		PrintWriter out = response.getWriter();
		out.flush();
		out.println(getResponse(foranswer));
		// request.getRequestDispatcher("").include(request,response);
		out.flush();
		out.close();
	}

	protected static String getResponse(String input) {
		return testIKanalyzer_ParagraphProcess(input);
		//return testICTCLAS_ParagraphProcess(input);
	}
	
	public static String testIKanalyzer_ParagraphProcess(String sInput) {
		int max = 0;
		int lmax = 0;

		try {
			sInput = sInput.replaceAll("[的]", "");
			//分词
			Vector<String> strs1 = IKAnalyzerUtil.participle(sInput);
			String[] stringArr = new String[strs1.size()];
			for (int i = 0; i < strs1.size(); i++) {
				stringArr[i] = strs1.get(i);
			}

			AcApply obj = new AcApply();
			File file = new File(pre_path+"test.txt");
		    InputStreamReader read = new InputStreamReader(new FileInputStream(file),"utf-8");   
		    BufferedReader reader = new BufferedReader(read); 
			String tempString = null;
			int line = 1;
			// 一次读一行，读入null时文件结束
			while ((tempString = reader.readLine()) != null) {
				Set result2 = obj.findWordsInArray(stringArr, tempString);
				// System.out.println(result2);
				if (result2.size() > max) {
					max = result2.size();
					lmax = line;
				}
				line++;
			}
			// System.out.println(max);
			// System.out.println(lmax);
			if (lmax == 0) {
				return "不明白你在讲什么( ⊙ o ⊙ )啊！";
				// System.exit(0);
			}
			reader.close();
			File file1 = new File(pre_path+"answer.txt");
		    InputStreamReader read1 = new InputStreamReader(new FileInputStream(file1),"utf-8");   
		    BufferedReader reader1 = new BufferedReader(read1); 
			String temp = null;
			int line1 = 1;
			// 一次读一行，读入null时文件结束
			while ((temp = reader1.readLine()) != null) {
				if (line1 == lmax) {
					System.out.println(temp);
					return temp;
				}
				line1++;
			}
			reader1.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return "我出故障了啦QAQ，感觉自己萌萌哒";
	}



	public static String testICTCLAS_ParagraphProcess(String sInput) {
		int max = 0;
		int lmax = 0;
		try {
			ICTCLAS50 testICTCLAS50 = new ICTCLAS50();
			String argu = pre_path;
			// 初始化
			if (testICTCLAS50.ICTCLAS_Init(argu.getBytes("GB2312")) == false) {
				System.out.println("Init Fail!");
				return "我出故障了啦QAQ，感觉自己萌萌哒";
			}

			// 设置词性标注集(0 计算所二级标注集，1 计算所一级标注集，2 北大二级标注集，3 北大一级标注集)
			testICTCLAS50.ICTCLAS_SetPOSmap(2);

			// 导入用户词典前分词
			byte nativeBytes[] = testICTCLAS50.ICTCLAS_ParagraphProcess(
					sInput.getBytes("GB2312"), 0, 0);// 分词处理
			// System.out.println(nativeBytes.length);
			String nativeStr = new String(nativeBytes, 0, nativeBytes.length,
					"GB2312");

			String[] stringArr = nativeStr.split(" ");
			// System.out.println("未导入用户词典的分词结果： " + nativeStr);//打印结果
			// for(int i=0;i<stringArr.length;i++)
			// System.out.print(stringArr[i]+"  ");
			AcApply obj = new AcApply();
			File file = new File(pre_path + "\\test.txt");
			BufferedReader reader = null;
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			// 一次读一行，读入null时文件结束
			while ((tempString = reader.readLine()) != null) {
				Set result2 = obj.findWordsInArray(stringArr, tempString);
				// System.out.println(result2);
				if (result2.size() > max) {
					max = result2.size();
					lmax = line;
				}
				line++;
			}
			// System.out.println(max);
			// System.out.println(lmax);
			if (lmax == 0) {
				System.out.println("不明白你在讲什么( ⊙ o ⊙ )啊！");
				return "不明白你在讲什么( ⊙ o ⊙ )啊！";
				// System.exit(0);
			}
			reader.close();
			File file1 = new File(pre_path + "\\answer.txt");
			BufferedReader reader1 = null;
			reader1 = new BufferedReader(new FileReader(file1));
			String temp = null;
			int line1 = 1;
			// 一次读一行，读入null时文件结束
			while ((temp = reader1.readLine()) != null) {
				if (line1 == lmax) {
					System.out.println(temp);
					return temp;
				}
				line1++;
			}
			reader1.close();

			// 导入用户字典
			/*
			 * int nCount = 0; String usrdir = "userdict.txt"; //用户字典路径 byte[]
			 * usrdirb = usrdir.getBytes();//将string转化为byte类型
			 * //导入用户字典,返回导入用户词语个数第一个参数为用户字典路径，第二个参数为用户字典的编码类型 nCount =
			 * testICTCLAS50.ICTCLAS_ImportUserDictFile(usrdirb, 0);
			 * System.out.println("导入用户词个数" + nCount); nCount = 0;
			 * 
			 * 
			 * //导入用户字典后再分词 byte nativeBytes1[] =
			 * testICTCLAS50.ICTCLAS_ParagraphProcess(sInput.getBytes("GB2312"),
			 * 2, 0); System.out.println(nativeBytes1.length); String nativeStr1
			 * = new String(nativeBytes1, 0, nativeBytes1.length, "GB2312");
			 * System.out.println("导入用户词典后的分词结果： " + nativeStr1); //保存用户字典
			 * testICTCLAS50.ICTCLAS_SaveTheUsrDic(); //释放分词组件资源
			 * testICTCLAS50.ICTCLAS_Exit();
			 */
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return "我出故障了啦QAQ，感觉自己萌萌哒";
	}

	/*
	 * public static void testICTCLAS_FileProcess() { try { ICTCLAS50
	 * testICTCLAS50 = new ICTCLAS50(); //分词所需库的路径 String argu = "."; //初始化 if
	 * (testICTCLAS50.ICTCLAS_Init(argu.getBytes("GB2312")) == false) {
	 * System.out.println("Init Fail!"); return; }
	 * 
	 * //输入文件名 String Inputfilename = "test.txt"; byte[] Inputfilenameb =
	 * Inputfilename.getBytes();//将文件名string类型转为byte类型
	 * 
	 * //分词处理后输出文件名 String Outputfilename = "test_result.txt"; byte[]
	 * Outputfilenameb = Outputfilename.getBytes();//将文件名string类型转为byte类型
	 * 
	 * //文件分词(第一个参数为输入文件的名,第二个参数为文件编码类型,第三个参数为是否标记词性集1 yes,0 no,第四个参数为输出文件名)
	 * testICTCLAS50.ICTCLAS_FileProcess(Inputfilenameb, 0, 0, Outputfilenameb);
	 * 
	 * int nCount = 0; String usrdir = "userdict.txt"; //用户字典路径 byte[] usrdirb =
	 * usrdir.getBytes();//将string转化为byte类型
	 * //第一个参数为用户字典路径，第二个参数为用户字典的编码类型(0:type
	 * unknown;1:ASCII码;2:GB2312,GBK,GB10380;3:UTF-8;4:BIG5) nCount =
	 * testICTCLAS50.ICTCLAS_ImportUserDictFile(usrdirb, 0);//导入用户字典,返回导入用户词语个数
	 * System.out.println("导入用户词个数" + nCount); nCount = 0;
	 * 
	 * String Outputfilename1 = "testing_result.txt"; byte[] Outputfilenameb1 =
	 * Outputfilename1.getBytes();//将文件名string类型转为byte类型
	 * 
	 * //文件分词(第一个参数为输入文件的名,第二个参数为文件编码类型,第三个参数为是否标记词性集1 yes,0 no,第四个参数为输出文件名)
	 * testICTCLAS50.ICTCLAS_FileProcess(Inputfilenameb, 0, 0,
	 * Outputfilenameb1);
	 * 
	 * 
	 * 
	 * 
	 * 
	 * } catch (Exception ex) { }
	 * 
	 * }
	 */

}
