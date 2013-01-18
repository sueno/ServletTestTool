package com.hoge.hoge;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.Bank;


/**
 * Servlet implementation class BankServlet
 */
@WebServlet("/BankServlet")
public class BankServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	
    private Bank myBank;
    
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BankServlet() {
		super();
		System.err.println("BankServlet init.");
		myBank = new Bank();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		//コマンドの種類
		String command = request.getParameter("command");	
		
		//出力するコンテントのタイプと文字コードの設定
		response.setContentType("text/html; charset=utf-8");	
		
		PrintWriter out = response.getWriter();
		
		if(command != null){ // commandがnull以外だったとき
		    if(command.equals("menu")){ // commandがmenuだったとき
			// メニュー画面の表示処理をここに記述する
			
			out.println("<html>");
			out.println("<head>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>メニュー</h1><br>");
			out.println("希望する取り引きを選んでクリックしてください。<br>");
			
			//     	----------------フォームの作成-------------------
			out.println("<form method=\"get\" action=\"/HogeTest/BankServlet\">");
			out.println("<input type=\"hidden\" name=\"command\" value=\"opengui\">");
			out.println("<input type=\"submit\" value=\"口座開設\">");
			out.println("</form> ");
			
			out.println("<form method=\"get\" action=\"/HogeTest/BankServlet\">");
			out.println("<input type=\"hidden\" name=\"command\" value=\"closegui\"> ");
			out.println("<input type=\"submit\" value=\"口座解約\"><br> ");
			out.println("</form> ");
			
			out.println("<form method=\"get\" action=\"/HogeTest/BankServlet\">");
			out.println("<input type=\"hidden\" name=\"command\" value=\"depositgui\"> ");
			out.println("<input type=\"submit\" value=\"預金\"><br> ");
			out.println("</form> ");
			
			out.println("<form method=\"get\" action=\"/HogeTest/BankServlet\">");
			out.println("<input type=\"hidden\" name=\"command\" value=\"withdrawgui\"> ");
			out.println("<input type=\"submit\" value=\"引き出し\"><br> ");
			out.println("</form> ");
			
			out.println("<form method=\"get\" action=\"/HogeTest/BankServlet\">");
			out.println("<input type=\"hidden\" name=\"command\" value=\"balancegui\">");
			out.println("<input type=\"submit\" value=\"残高照会\"><br>");
			out.println("</form> ");
			out.println("</body>");
			out.println("</html>");
		    }
		    
		    else if(command.equals("opengui")){ // commandがopenguiだったとき
			// 口座開設画面の表示処理をここに記述する
			out.println("<html>");
			out.println("<body>");
			out.println("<h1>口座開設</h1><br>");
			out.println("開設する口座の名前を入力して「了解」ボタンを押して下さい<br> ");
			//	----------------フォームの作成-------------------
			out.print("名前:");
			out.println("<form method=\"get\" action=\"/HogeTest/BankServlet\">");
			out.println("<input type=\"hidden\" name=\"command\" value=\"open\">");
			out.println("<input type=\"text\" name=\"name\"> ");
			out.println("<input type=\"submit\" value=\"送信\">");
			out.println("<input type=\"reset\" value=\"リセット\">");
			out.println("</form>");
			
			out.println("<form method=\"get\" action=\"/HogeTest/BankServlet\">");
			out.println("<input type=\"hidden\" name=\"command\" value=\"menu\">");
			out.println("<input type=\"submit\" value=\"メニューに戻る\">");
			out.println("</form> ");
			
			out.println("</body>");
			out.println("</html>");
			
			
		    } else if(command.equals("open")){	
			// 口座開設の処理をここに記述する
			out.println("<html>");
			out.println("<head>");
			out.println("</head>");
			out.println("<body>");
			int judge;
			
			String name = request.getParameter("name");	// 口座名
			
			// 文字コードの変換
			if(name != null)
			    name = new String(name.getBytes("ISO-8859-1"), "utf-8");
			
			judge=myBank.doOpen(name); // doOpenメソッドの実行
			
			if(judge==0){    // 実行結果の判定
			    out.println(name+"様の口座を開設しました。");
			}
			else if(judge==-2){
			    out.println(name+"様と同名の口座がすでに存在しています。");            
			}
			//	----------------フォームの作成-------------------
			out.println("<form method=\"get\" action=\"/HogeTest/BankServlet\">");
			out.println("<input type=\"hidden\" name=\"command\" value=\"menu\">");
			out.println("<input type=\"submit\" value=\"メニューに戻る\">");
			out.println("</form> ");
			
			out.println("</body>");
			out.println("</html>");
			
			
			
			
		    } else if(command.equals("closegui")){	
			// 口座解約画面の表示処理をここに記述する   
			out.println("<html>");
			out.println("<body>");
			out.println("<h1>口座解約</h1><br> ");
			out.println("解約する口座の名前を入力して「了解」ボタンを押して下さい<br> ");
			//    ----------------フォームの作成-------------------
			out.print("名前:");
			out.print("<form method=\"get\" action=\"/HogeTest/BankServlet\">");
			out.println("<input type=\"hidden\" name=\"command\" value=\"close\">");
			out.println("<input type=\"text\" name=\"name\"> ");
			out.println("<input type=\"submit\" value=\"了解\">");
			out.println("<input type=\"reset\" value=\"リセット\">");
			out.println("</form>");
			
			out.println("<form method=\"get\" action=\"/HogeTest/BankServlet\">");
			out.println("<input type=\"hidden\" name=\"command\" value=\"menu\">");
			out.println("<input type=\"submit\" value=\"メニューに戻る\">");
			out.println("</form> ");
			out.println("</body>");
			out.println("</html>");
			
		    } else if(command.equals("close")){	
			// 口座解約の処理をここに記述する
			
			out.println("<html>");
			out.println("<body>");
			int judge;
			
			String name = request.getParameter("name");	// 口座名
			
			// 文字コードの変換
			if(name != null)
			    name = new String(name.getBytes("ISO-8859-1"), "utf-8");
			
			judge=myBank.doClose(name); // doCloseメソッドの実行
			
			if(judge==0){    // 実行結果の判定
			    out.println(name+"様の口座を解約しました。");
			}
			else if(judge==-1){
			    out.println(name +"様の口座に残金があるため解約できません。");
			}
			else if(judge==-2){
			    out.println(name+"様の口座は存在しません。");            
			}
			
			// ----------------フォームの作成-------------------		
			
			out.println("<form method=\"get\" action=\"/HogeTest/BankServlet\">");
			out.println("<input type=\"hidden\" name=\"command\" value=\"menu\">");
			out.println("<input type=\"submit\" value=\"メニューに戻る\">");
			out.println("</form> ");
			
			out.println("</body>");
			out.println("</html>");
			
		    } else if(command.equals("depositgui")){	
			// 預金画面の表示処理をここに記述する
			out.println("<html>");
			out.println("<body>");
			
			out.println("<h1>預金</h1><br> ");
			out.println("預金する口座名と預金額を入力して「了解」ボタンを押して下さい<br> ");
			// 	----------------フォームの作成-------------------
			out.print("名前:");
			out.print("<form method=\"get\" action=\"/HogeTest/BankServlet\">");
			out.println("<input type=\"hidden\" name=\"command\" value=\"deposit\">");
			out.println("<input type=\"text\" name=\"name\"> ");
			out.print("預金額");
			out.println("<input type=\"text\" name=\"amount\">");
			out.println("<input type=\"submit\" value=\"了解\">");
			out.println("<input type=\"reset\" value=\"リセット\">");
			out.println("</form>");
			
			out.println("<form method=\"get\" action=\"/HogeTest/BankServlet\">");
			out.println("<input type=\"hidden\" name=\"command\" value=\"menu\">");
			out.println("<input type=\"submit\" value=\"メニューに戻る\">");
			out.println("</form> ");
			
			out.println("</body>");
			out.println("</html>");
			
			
			
		    } else if(command.equals("deposit")){	
			// 預金の処理をここに記述する
			int judge;
			out.println("<html>");
			out.println("<body>");
			
			String name = request.getParameter("name");	// 口座名
			String amo = request.getParameter("amount");  // 預金額
			
			// 文字コードの変換
			if(name != null)
			    name = new String(name.getBytes("ISO-8859-1"), "utf-8");
			int amount = Integer.parseInt(amo); // 文字列をint型に変換する
			judge=myBank.doDeposit(name,amount); // doDepositメソッドを実行する
			if(judge==0){    // 実行結果の判定
			    out.println(name+"様の口座に"+ amount +"円預金しました。");
			}
			else if(judge==-2){
			    out.println(name +"様の口座は存在しません。");
			}
			else if(judge==-3){
			    out.println("不正な金額が入力されました");            
			}
			
			//	----------------フォームの作成-------------------
			out.println("<form method=\"get\" action=\"/HogeTest/BankServlet\">");
			out.println("<input type=\"hidden\" name=\"command\" value=\"menu\">");
			out.println("<input type=\"submit\" value=\"メニューに戻る\">");
			out.println("</form> ");
			
			out.println("</body>");
			out.println("</html>");
			
			
			
		    } else if(command.equals("withdrawgui")){	
			// 引き出し画面の表示処理をここに記述する
			out.println("<html>");
			out.println("<body>");
			
			out.println("<h1>引き出し</h1><br> ");
			out.println("引き出す口座名と引き出し額を入力して「了解」ボタンを押して下さい<br> ");
			//      ----------------フォームの作成-------------------
			out.print("名前:");
			out.print("<form method=\"get\" action=\"/HogeTest/BankServlet\">");
			out.println("<input type=\"hidden\" name=\"command\" value=\"withdraw\">");
			out.println("<input type=\"text\" name=\"name\"> ");
			out.print("引き出し額");
			out.println("<input type=\"text\" name=\"amount\">");
			out.println("<input type=\"submit\" value=\"了解\">");
			out.println("<input type=\"reset\" value=\"リセット\">");
			out.println("</form>");
			
			out.println("<form method=\"get\" action=\"/HogeTest/BankServlet\">");
			out.println("<input type=\"hidden\" name=\"command\" value=\"menu\">");
			out.println("<input type=\"submit\" value=\"メニューに戻る\">");
			out.println("</form> ");
			
			
			out.println("</body>");
			out.println("</html>");
			
			
			
		    } else if(command.equals("withdraw")){	
			// 引き出しの処理をここに記述する
			out.println("<html>");
			out.println("<body>");
			
			int judge;
			
			String name = request.getParameter("name");	// 口座名
			String amo = request.getParameter("amount");  // 預金額
			
			// 文字コードの変換
			if(name != null)
			    name = new String(name.getBytes("ISO-8859-1"), "utf-8");
			
			int amount = Integer.parseInt(amo); // 文字列をint型に変換する
			
			judge=myBank.doWithdraw(name,amount);
			
			if(judge==0){    // 実行結果の判定
			    out.println(name+"様の口座から"+ amount +"円引き出しました。");
			}
			else if(judge==-1){
			    out.println("残高を上回る金額を払い戻すことは出来ません。");
			}
			else if(judge==-2){
			    out.println(name +"様の口座は存在しません。");
			}
			else if(judge==-3){
			    out.println("不正な金額が入力されました");            
			}

			//	----------------フォームの作成-------------------
			out.println("<form method=\"get\" action=\"/HogeTest/BankServlet\">");
			out.println("<input type=\"hidden\" name=\"command\" value=\"menu\">");
			out.println("<input type=\"submit\" value=\"メニューに戻る\">");
			out.println("</form> ");
			
			
			out.println("</body>");
			out.println("</html>");
			
			
		    } else if(command.equals("balancegui")){	
			// 残高照会画面の表示処理をここに記述する   
			out.println("<html>");
			out.println("<body>");
			
			out.println("<h1>残高照会</h1><br> ");
			out.println("照会する口座の名前を入力して「了解」ボタンを押して下さい<br> ");
			//	----------------フォームの作成-------------------
			out.print("名前:");
			out.println("<form method=\"get\" action=\"/HogeTest/BankServlet\">");
			out.println("<input type=\"hidden\" name=\"command\" value=\"balance\">");
			out.println("<input type=\"text\" name=\"name\"> ");
			out.println("<input type=\"submit\" value=\"送信\">");
			out.println("<input type=\"reset\" value=\"リセット\">");
			out.println("</form>");
			
			out.println("<form method=\"get\" action=\"/HogeTest/BankServlet\">");
			out.println("<input type=\"hidden\" name=\"command\" value=\"menu\">");
			out.println("<input type=\"submit\" value=\"メニューに戻る\">");
			out.println("</form> ");		
			out.println("</body>");
			out.println("</html>");
			
			
		    } else if(command.equals("balance")){	
			// 残高照会の処理をここに記述する
			out.println("<html>");
			out.println("<body>");
			
			int judge;
			
			String name = request.getParameter("name");	// 口座名
			
			// 文字コードの変換
			if(name != null)
			    name = new String(name.getBytes("ISO-8859-1"), "utf-8");
			
			judge=myBank.doBalance(name); // doBalanceメソッドの実行
			if(judge>=0){    // 実行結果の判定
			    out.println(name+"様の口座には"+judge+"円残高が存在しています。");
			}
			else if(judge==-2){
			    out.println(name+"様の口座は存在しません。");            
			}
			
			//	----------------フォームの作成-------------------
			out.println("<form method=\"get\" action=\"/HogeTest/BankServlet\">");
			out.println("<input type=\"hidden\" name=\"command\" value=\"menu\">");
			out.println("<input type=\"submit\" value=\"メニューに戻る\">");
			out.println("</form> ");
			out.println("</body>");
			out.println("</html>");
			
		    }
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
