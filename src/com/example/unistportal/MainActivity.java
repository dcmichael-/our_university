package com.example.unistportal;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

import org.apache.http.client.HttpClient;
import com.example.ouruniversity.R;
import com.nbpcorp.mobilead.sdk.MobileAdListener;
import com.nbpcorp.mobilead.sdk.MobileAdView;


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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity implements MobileAdListener {
	TextView tv2;
	ListView lv;
	ListView lv2;
	httptask ht;
	ArrayAdapter<Spanned> aa;
	ArrayList<Spanned> arr;
	ArrayAdapter<String> aa2;
	ArrayList<String> arr2;
	String html;
	String selboard;
	HttpClient httpclient = null;
	private int page;
	public LinearLayout mTestLayout;
	private MobileAdView adView = null;
	boolean a = true;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		httpclient = httptask.getNewHttpClient();
		showmain();
	}
	public void showmain()
	{
		//loaded=false;

		setContentView(R.layout.activity_main);
		
		adView = (MobileAdView) findViewById(R.id.adview1);;
		//adView.setChannelID("mandroid_634225d2b27d44d3b1e74db6c9238be5");
		//adView.setTest(true);
		adView.setListener(this);
		adView.setTest(false);
		adView.setChannelID("mandroid_634225d2b27d44d3b1e74db6c9238be5");
		adView.start();
		  
		lv = (ListView) findViewById(R.id.listView1);
		arr = new ArrayList<Spanned>();
		arr2 = new ArrayList<String>();
		
        mTestLayout = (LinearLayout) View.inflate(this, R.layout.readmore, null);
        mTestLayout.setVisibility(View.VISIBLE);
		html = "";
		//arr.add(html);
		
		aa = new ArrayAdapter<Spanned>(this, android.R.layout.simple_list_item_1, arr);
		aa2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr2);
		arr2.add("전체공지");
		arr2.add("학사공지");
		arr2.add("대학원공지");
		arr2.add("개선및 제안");
		arr2.add("Q&A");
		arr2.add("학사Q&A");
		//lv2 = (ListView) findViewById(R.id.listView2);
		lv.addFooterView(mTestLayout);
		lv.setAdapter(aa);
		//lv2.setAdapter(aa2);
		int max_len=0;
		for(int i=0; i<arr2.size(); i++)
		{
			if(max_len<arr2.get(i).length())
				max_len = arr2.get(i).length();
		}
		
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
				ht = new httptask(httpclient);
				httptask.makeHttpPost("boardid",selboard);
				httptask.makeHttpPost("bullid", a.bullid);
				httptask.makeHttpPost("nkid", a.row_id);
				httptask.makeHttpPost("rkid", a.top_bullid);
				httptask.makehandler(bhandler);
				ht.execute("dcmichael1256", "bd0775de61645be9a39de434d178580e", "http://portal.unist.ac.kr/EP/web/collaboration/bbs/jsp/BB_BoardView.jsp");
				
			}
			
		});

		/*
		 lv2.setOnItemClickListener(new ListView.OnItemClickListener(){
		 

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int index,
					long arg3) {
				switch(index)
				{
				case 0:
					selboard = "B200902281833016691048";
					break;
				case 1:
					selboard = "B200902281833482321051";
					break;
				case 2:
					selboard = "B201003111719010571299";
					break;
				case 3:
					selboard = "B200805221624473331040";
					break;
				case 4:
					selboard = "B200806120956049151016";
					break;
				case 5:
					selboard = "B200903111033027841090";
					break;
				}
				ht = new httptask(httpclient);
				httptask.clearPOST();
				board.clear();
				aa.clear();
				arr.clear();
				page=1;
				httptask.makehandler(handler);
				httptask.makeHttpPost("boardid",selboard);
				httptask.makeHttpPost("nfirst",""+page);
				ht.execute("dcmichael1256", "bd0775de61645be9a39de434d178580e", "http://portal.unist.ac.kr/EP/web/collaboration/bbs/jsp/BB_BoardLst.jsp");
				
			}
			
		});
		*/
		html = "";
		
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
			if(httptask.result==null)
			return;
			html = httptask.result;
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
		    	Log.e("html3", html);
		    	html = html.substring(html.indexOf("<!-- 내용부분 -->"), html.indexOf("<td class=\"td_bg\" colspan=\"2\">"));
		    	wv.loadDataWithBaseURL("", html, "text/html", "utf-8", null);
				//Spanned a = Html.fromHtml(html);
				//tv2.setText(html);
		    }	
		}
	};
	public Handler handler = new Handler()  {

    	public void handleMessage(Message msg)  {
    		if(httptask.result==null)
    			return;
			html = httptask.result;//.substring(ht.result.indexOf("title=")+8);

			html = html.substring(html.indexOf("clickBulletin")+"clickBulletin(\"".length());
			while(html.indexOf("clickBulletin")!=-1)
			{
				html = html.substring(html.indexOf("clickBulletin")+"clickBulletin(\"".length());
				
				String bullid = html.substring(0, html.indexOf(",")-1);
				html = html.substring(bullid.length()+3);
				String row_id = html.substring(0, html.indexOf(",")-1);
				html = html.substring(row_id.length()+3);
				String top_bulid = html.substring(0, html.indexOf(",")-1);
				html = html.substring(top_bulid.length()+3);
				String depth = html.substring(0, html.indexOf(",")-1);
				
				html = html.substring(html.indexOf("<font"));
				
				Spanned title = Html.fromHtml(html.substring(0, html.indexOf("</font>")+7));
				bullid = bullid.replace("\'", "");
				row_id = row_id.replace("\'", "");
				top_bulid = top_bulid.replace("\'", "");
				depth = depth.replace("\'", "");
				Log.println(Log.ASSERT, "html", title + "\n" + bullid + "\n" + row_id + "\n" + top_bulid + "\n" + depth);
				Board b = new Board(title, bullid, row_id, top_bulid, Integer.valueOf(depth));
				board.add(b);
				html = html.substring(html.indexOf("\"")+1);
				arr.add(b.title);
				aa.notifyDataSetChanged();
			} 
    	}

    };
    protected void onDestroy()
    {
    	super.onDestroy();
    	if(adView != null){
    		adView.destroy();
    		adView = null;
    	}
    }
    public void onReceive(int arg0)
    {
    	Log.e("receive", arg0+"");
    }
}
