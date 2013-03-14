package com.example.unistportal;

import java.util.ArrayList;

import org.apache.http.protocol.HTTP;


import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AbsListView.OnScrollListener;

public class MainActivity extends Activity {
	Button login;
	Button Anon;
	Button hacsa;
	TextView tv2;
	ListView lv;
	httptask ht;
	ArrayAdapter<Spanned> aa;
	ArrayList<Spanned> arr;
	String html;
	String selboard;
	private int totalListNum;
	private int page;
	private boolean isend;
	private boolean loaded=false;
	public LinearLayout mTestLayout;
	boolean a = true;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		showmain();
	}
	public void showmain()
	{
		//loaded=false;
		setContentView(R.layout.activity_main);
		login = (Button) findViewById(R.id.button1);
		Anon = (Button) findViewById(R.id.button2);
		hacsa = (Button) findViewById(R.id.button3);
		lv = (ListView) findViewById(R.id.listView1);
		arr = new ArrayList<Spanned>();
		
        mTestLayout = (LinearLayout) View.inflate(this, R.layout.readmore, null);
        mTestLayout.setVisibility(View.VISIBLE);
		html = "";
		//arr.add(html);
		
		aa = new ArrayAdapter<Spanned>(this, android.R.layout.simple_list_item_1, arr);

		lv.addFooterView(mTestLayout);
		lv.setAdapter(aa);
		/*
		lv.setOnScrollListener(new OnScrollListener() {
			
			// @Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				
			}
			// @Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, final int totalItemCount) {
				Log.e("scroll", loaded + " " + firstVisibleItem +" " + visibleItemCount + " " + totalItemCount);
				if (loaded&&(firstVisibleItem + visibleItemCount) == totalItemCount) {
					if (a) {
						mTestLayout.setVisibility(View.VISIBLE);
						
						
						
						a = false;
					 	ht = new httptask();
						ht.clearPOST();
						board.clear();
						aa.clear();
						arr.clear();
						page++;
						ht.makehandler(handler);
						ht.makeHttpPost("loginid","dcmichael1256");
						ht.makeHttpPost("password","bd0775de61645be9a39de434d178580e");
						ht.makeHttpPost("boardid",selboard);
						ht.makeHttpPost("nfirst",""+page);
						ht.execute("https://portal.unist.ac.kr/EP/web/login/login_chk.jsp", "http://portal.unist.ac.kr/EP/web/collaboration/bbs/jsp/BB_BoardLst.jsp");
						
					} else {
						a=true;
						mTestLayout.setVisibility(View.INVISIBLE);
					}
					
				}
			}
		});   
		*/
		lv.setOnItemClickListener(new ListView.OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int index,
					long arg3) {
				Log.e("board", board.get(index).toString());
				Board a = board.get(index);
				ht = new httptask();
				ht.makehandler(bhandler);
				ht.makeHttpPost("loginid","dcmichael1256");
				ht.makeHttpPost("password","bd0775de61645be9a39de434d178580e");
				ht.makeHttpPost("boardid",selboard);
				ht.makeHttpPost("bullid", a.bullid);
				ht.makeHttpPost("nkid", a.row_id);
				ht.makeHttpPost("rkid", a.top_bullid);
				ht.execute("https://portal.unist.ac.kr/EP/web/login/login_chk.jsp", "http://portal.unist.ac.kr/EP/web/collaboration/bbs/jsp/BB_BoardLst.jsp", "http://portal.unist.ac.kr/EP/web/collaboration/bbs/jsp/BB_BoardView.jsp?");
				
			}
			
		});
		
		html = "";
		
		login.setOnClickListener(new Button.OnClickListener()
		{
			@Override
			public void onClick(View arg0) {

				Message msg = handler.obtainMessage();

				handler.sendMessage(msg);
			}	
		}
		);
		Anon.setOnClickListener(new Button.OnClickListener()
		{
			@Override
			public void onClick(View arg0) {
				selboard = "B200902281833016691048";
				ht = new httptask();
				ht.clearPOST();
				board.clear();
				aa.clear();
				arr.clear();
				page=1;
				ht.makehandler(handler);
				ht.makeHttpPost("loginid","dcmichael1256");
				ht.makeHttpPost("password","bd0775de61645be9a39de434d178580e");
				ht.makeHttpPost("boardid","B200902281833016691048");
				ht.makeHttpPost("nfirst",""+page);
				ht.execute("https://portal.unist.ac.kr/EP/web/login/login_chk.jsp", "http://portal.unist.ac.kr/EP/web/collaboration/bbs/jsp/BB_BoardLst.jsp");
				
			}	
		}
		);
		hacsa.setOnClickListener(new Button.OnClickListener()
		{
			@Override
			public void onClick(View arg0) {
				selboard = "B200902281833482321051";
				ht = new httptask();
				ht.clearPOST();
				board.clear();
				aa.clear();
				arr.clear();
				page=1;
				ht.makehandler(handler);
				ht.makeHttpPost("loginid","dcmichael1256");
				ht.makeHttpPost("password","bd0775de61645be9a39de434d178580e");
				ht.makeHttpPost("boardid","B200902281833482321051");
				ht.makeHttpPost("nfirst",""+page);
				ht.execute("https://portal.unist.ac.kr/EP/web/login/login_chk.jsp", "http://portal.unist.ac.kr/EP/web/collaboration/bbs/jsp/BB_BoardLst.jsp");
				
			}	
		}
		);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public class Board
	{
		Spanned title;
		String bullid, row_id, top_bullid;
		int depth;
		public Board(Spanned title, String bullid, String row_id, String top_bullid, int depth)
		{
			this.title = title;
			this.bullid = bullid;
			this.row_id = row_id;
			this.top_bullid = top_bullid;
			this.depth = depth;
		}
		public String toString()
		{
			return title.toString() +" "+ bullid +" "+ depth;
		}
	};
	ArrayList<Board> board= new ArrayList<Board>();
	
	public Handler bhandler = new Handler()
	{
		public void handleMessage(Message msg)
		{
			html = ht.result;
			//Intent intent = new Intent(MainActivity.this, Boardview.class);
			//intent.putExtra("Data", html);
			//startActivity(intent);
		    setContentView(R.layout.boardview);
		    {
		    	tv2 = (TextView) findViewById(R.id.textView2);
		    	Button btn = (Button) findViewById(R.id.back);
		    	WebView wv = (WebView) findViewById(R.id.webView1);
		    	btn.setOnClickListener(new Button.OnClickListener(){

					@Override
					public void onClick(View arg0) {
					    showmain();
					}
		    		
		    	});
		    	html = html.substring(html.indexOf("<!-- 내용부분 -->"), html.indexOf("<td class=\"td_bg\" colspan=\"2\">"));
		    	wv.loadDataWithBaseURL("", html, "text/html", "utf-8", null);
				//Spanned a = Html.fromHtml(html);
				//tv2.setText(a);
		    }	
		}
	};
	public Handler handler = new Handler()  {

    	public void handleMessage(Message msg)  {
			html = ht.result;//.substring(ht.result.indexOf("title=")+8);

			html = html.substring(html.indexOf("clickBulletin")+"clickBulletin(\"".length());
			while(html.indexOf("clickBulletin")!=-1)
			{
				html = html.substring(html.indexOf("clickBulletin")+"clickBulletin(\"".length());
				
				String bullid = html.substring(0, html.indexOf("\""));
				html = html.substring(bullid.length()+3);
				String row_id = html.substring(0, html.indexOf("\""));
				html = html.substring(row_id.length()+3);
				String top_bulid = html.substring(0, html.indexOf("\""));
				html = html.substring(top_bulid.length()+3);
				String depth = html.substring(0, html.indexOf("\""));
				
				html = html.substring(html.indexOf("title=")+7);
				Spanned title = Html.fromHtml(html.substring(0, html.indexOf("\"")));
				
				Board b = new Board(title, bullid, row_id, top_bulid, Integer.valueOf(depth));
				board.add(b);
				html = html.substring(html.indexOf("\"")+1);
				loaded=true;
				arr.add(b.title);
				aa.notifyDataSetChanged();
			} 
    	}

    };
}
