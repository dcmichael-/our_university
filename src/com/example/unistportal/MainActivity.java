package com.example.unistportal;

import java.util.ArrayList;

import org.apache.http.client.HttpClient;
import com.example.ouruniversity.R;
import com.nbpcorp.mobilead.sdk.MobileAdListener;
import com.nbpcorp.mobilead.sdk.MobileAdView;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.graphics.Color;
import android.text.Html;
import android.text.Spanned;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity implements MobileAdListener {
	TextView tv2;
	ListView lv;
	ListView lv2;
	httptask ht;

	Button anon;
	Button hacsa;
	Button hacwon;
	Button sug;
	Button QA;
	Button hacsaQA;
	myadapter<Spanned> aa;
	ArrayList<Spanned> arr;
	myadapter<String> aa2;
	ArrayList<String> arr2;
	ArrayList<Button> btnlist;
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

		setContentView(R.layout.splash);
		//startActivity(new Intent(this, SplashActivity.class));

		Handler handler = new Handler () {
			@Override
			public void handleMessage(Message msg) {

				showmain();
			}
		};

		handler.sendEmptyMessageDelayed(0, 3000);
	}
	public void showmain()
	{
		//loaded=false;

		setContentView(R.layout.activity_main);

		adView = (MobileAdView) findViewById(R.id.adview1);;
		//adView.setTest(true);
		adView.setListener(this);
		adView.setTest(false);
		adView.setChannelID("mandroid_634225d2b27d44d3b1e74db6c9238be5");
		adView.start();

		lv = (ListView) findViewById(R.id.listView1);
		arr = new ArrayList<Spanned>();
		arr2 = new ArrayList<String>();
		btnlist = new ArrayList<Button>();

		mTestLayout = (LinearLayout) View.inflate(this, R.layout.readmore, null);
		mTestLayout.setVisibility(View.VISIBLE);
		html = "";
		//arr.add(html);

		aa = new myadapter<Spanned>(this, R.layout.mylist, arr);
		aa2 = new myadapter<String>(this, R.layout.mylist, arr2);
		arr2.add("전체공지");
		arr2.add("학사공지");
		arr2.add("대학원공지");
		arr2.add("개선및 제안");
		arr2.add("Q&A");
		arr2.add("학사Q&A");

		anon = ((Button)findViewById(R.id.anon));
		hacsa = ((Button)findViewById(R.id.hacsa));
		hacwon = ((Button)findViewById(R.id.hacwon));
		sug = ((Button)findViewById(R.id.sug));
		QA = ((Button)findViewById(R.id.QA));
		hacsaQA = ((Button)findViewById(R.id.hacsaQA));
		btnlist.add(anon);
		btnlist.add(hacsa);
		btnlist.add(hacwon);
		btnlist.add(sug);
		btnlist.add(QA);
		btnlist.add(hacsaQA);
		for(Button b:btnlist)
		{
			b.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View arg0) {
					//mTestLayout.setVisibility(View.VISIBLE);
					switch(arg0.getId())
					{
					case R.id.anon:
						selboard = "B200902281833016691048";
						anon.setBackgroundResource(R.drawable.button_01);
						hacsa.setBackgroundResource(R.drawable.button_02_b);
						hacwon.setBackgroundResource(R.drawable.button_03_b);
						sug.setBackgroundResource(R.drawable.button_04_b);
						QA.setBackgroundResource(R.drawable.button_05_b);
						hacsaQA.setBackgroundResource(R.drawable.button_06_b);
						break;
					case R.id.hacsa:
						selboard = "B200902281833482321051";
						anon.setBackgroundResource(R.drawable.button_01_b);
						hacsa.setBackgroundResource(R.drawable.button_02);
						hacwon.setBackgroundResource(R.drawable.button_03_b);
						sug.setBackgroundResource(R.drawable.button_04_b);
						QA.setBackgroundResource(R.drawable.button_05_b);
						hacsaQA.setBackgroundResource(R.drawable.button_06_b);
						break;
					case R.id.hacwon:
						selboard = "B201003111719010571299";
						anon.setBackgroundResource(R.drawable.button_01_b);
						hacsa.setBackgroundResource(R.drawable.button_02_b);
						hacwon.setBackgroundResource(R.drawable.button_03);
						sug.setBackgroundResource(R.drawable.button_04_b);
						QA.setBackgroundResource(R.drawable.button_05_b);
						hacsaQA.setBackgroundResource(R.drawable.button_06_b);
						break;
					case R.id.sug:
						selboard = "B200805221624473331040";
						anon.setBackgroundResource(R.drawable.button_01_b);
						hacsa.setBackgroundResource(R.drawable.button_02_b);
						hacwon.setBackgroundResource(R.drawable.button_03_b);
						sug.setBackgroundResource(R.drawable.button_04);
						QA.setBackgroundResource(R.drawable.button_05_b);
						hacsaQA.setBackgroundResource(R.drawable.button_06_b);
						break;
					case R.id.QA:
						selboard = "B200806120956049151016";
						anon.setBackgroundResource(R.drawable.button_01_b);
						hacsa.setBackgroundResource(R.drawable.button_02_b);
						hacwon.setBackgroundResource(R.drawable.button_03_b);
						sug.setBackgroundResource(R.drawable.button_04_b);
						QA.setBackgroundResource(R.drawable.button_05);
						hacsaQA.setBackgroundResource(R.drawable.button_06_b);
						break;
					case R.id.hacsaQA:
						selboard = "B200903111033027841090";
						anon.setBackgroundResource(R.drawable.button_01_b);
						hacsa.setBackgroundResource(R.drawable.button_02_b);
						hacwon.setBackgroundResource(R.drawable.button_03_b);
						sug.setBackgroundResource(R.drawable.button_04_b);
						QA.setBackgroundResource(R.drawable.button_05_b);
						hacsaQA.setBackgroundResource(R.drawable.button_06);
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
		}
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

		lv.setOnItemClickListener(new ListView.OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int index,
					long arg3) {

				if(index>=board.size())
				{
					ht = new httptask(httpclient);
					httptask.clearPOST();
					//board.clear();
					//aa.clear();
					//arr.clear();
					page++;
					httptask.makehandler(handler);
					httptask.makeHttpPost("boardid",selboard);
					httptask.makeHttpPost("nfirst",""+page);
					ht.execute("dcmichael1256", "bd0775de61645be9a39de434d178580e", "http://portal.unist.ac.kr/EP/web/collaboration/bbs/jsp/BB_BoardLst.jsp");
				}
				else
				{
					Board a = board.get(index);
					ht = new httptask(httpclient);
					httptask.makeHttpPost("boardid",selboard);
					httptask.makeHttpPost("bullid", a.bullid);
					httptask.makeHttpPost("nkid", a.row_id);
					httptask.makeHttpPost("rkid", a.top_bullid);
					httptask.makehandler(bhandler);
					ht.execute("dcmichael1256", "bd0775de61645be9a39de434d178580e", "http://portal.unist.ac.kr/EP/web/collaboration/bbs/jsp/BB_BoardView.jsp");
				}
			}

		});

		html = "";

		selboard = "B200902281833016691048";
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

	public class Board
	{
		Spanned title;
		String num, bullid, row_id, top_bullid;
		int depth;
		public Board(String num, Spanned title, String bullid, String row_id, String top_bullid, int depth)
		{
			this.num = num;
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
				Button btn = (Button) findViewById(R.id.btnback);
				WebView wv = (WebView) findViewById(R.id.content);
				TextView tit = (TextView) findViewById(R.id.title);
				TextView dat = (TextView) findViewById(R.id.date);
				TextView wri = (TextView) findViewById(R.id.writer);
				TextView mai = (TextView) findViewById(R.id.mail);
				btn.setOnClickListener(new Button.OnClickListener(){

					@Override
					public void onClick(View arg0) {
						showmain();
					}

				});
				//Log.e("html3", html);
				String title = html.substring(html.indexOf("제목</td>")+"제목</td>".length());
				title = title.substring(0, title.indexOf("</td>")+"</td>".length());
				title = Html.fromHtml(title).toString();
				tit.setText(""+title);
				String uploaddate = html.substring(html.indexOf("게시일</td>")+"게시일</td>".length());
				uploaddate = uploaddate.substring(0, uploaddate.indexOf("</td>")+"</td>".length());
				uploaddate = Html.fromHtml(uploaddate).toString();
				dat.setText(""+uploaddate);
				String Writer = html.substring(html.indexOf("작성자</td>")+"작성자</td>".length());
				Writer = Writer.substring(0, Writer.indexOf("</td>")+"</td>".length());
				Writer = Html.fromHtml(Writer).toString();
				wri.setText(""+Writer);
				String mail = html.substring(html.indexOf("작성자메일</td>")+"작성자메일</td>".length());
				mail = mail.substring(0,mail.indexOf("</td>")+"</td>".length());
				mail = Html.fromHtml(mail).toString();
				mai.setText(""+mail);
				html = html.substring(html.indexOf("<!--게시물 내용-->"), html.indexOf("<td class=\"td_bg\" colspan=\"2\">"));
				tit.setTextColor(Color.BLACK);
				dat.setTextColor(Color.BLACK);
				wri.setTextColor(Color.BLACK);
				mai.setTextColor(Color.BLACK);
				wv.setBackgroundColor(0);
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
				html = html.substring(html.indexOf("checkbox")+5);
				html = html.substring(html.indexOf("<nobr>")+6);
				html = html.substring(html.indexOf("<nobr>"));
				int end = html.indexOf("</nobr>");

				//Log.println(Log.ASSERT, "num", ""+end);
				//Log.println(Log.ASSERT, "html", html);
				String num;
				if(end<10)
					num = html.substring(6, end);
				else
					num="공지";
				//Log.println(Log.ASSERT, "num", ""+num);
				html = html.substring(html.indexOf("</nobr>"));
				html = html.substring(html.indexOf("clickBulletin")+"clickBulletin(\"".length());

				String bullid = html.substring(0, html.indexOf(",")-1);
				html = html.substring(bullid.length()+3);
				String row_id = html.substring(0, html.indexOf(",")-1);
				html = html.substring(row_id.length()+3);
				String top_bulid = html.substring(0, html.indexOf(",")-1);
				html = html.substring(top_bulid.length()+3);
				String depth = html.substring(0, html.indexOf(",")-1);

				html = html.substring(html.indexOf("<font"));
				String titlesource = html.substring(0, html.indexOf("</font>")+7);
				Spanned title = Html.fromHtml(titlesource);
				bullid = bullid.replace("\'", "");
				row_id = row_id.replace("\'", "");
				top_bulid = top_bulid.replace("\'", "");
				depth = depth.replace("\'", "");
				//Log.println(Log.ASSERT, "html", title + "\n" + bullid + "\n" + row_id + "\n" + top_bulid + "\n" + depth);
				Board b = new Board(num, title, bullid, row_id, top_bulid, Integer.valueOf(depth));
				board.add(b);
				html = html.substring(html.indexOf("\"")+1);
				arr.add(Html.fromHtml(num+" "+titlesource));
				aa.notifyDataSetChanged();
				//mTestLayout.setVisibility(View.INVISIBLE);
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
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event){
		if(keyCode == KeyEvent.KEYCODE_BACK){
			showmain();
			return false;
		}
		return true;
	}
}
