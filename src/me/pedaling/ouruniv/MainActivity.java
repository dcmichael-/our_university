package me.pedaling.ouruniv;

import java.util.ArrayList;

import org.apache.http.client.HttpClient;
import me.pedaling.ouruniv.R;
import com.flurry.android.FlurryAgent;
import com.nbpcorp.mobilead.sdk.MobileAdListener;
import com.nbpcorp.mobilead.sdk.MobileAdView;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.graphics.Color;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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
	public LinearLayout nextLayout;
	public LinearLayout prevLayout;
	private MobileAdView adView = null;
	boolean a = true;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		FlurryAgent.onStartSession(this, "Y9YTZB6SRHVSV7SQRZZ8");
		FlurryAgent.logEvent("start", true);
		httpclient = httptask.getNewHttpClient();

		page=1;
		setContentView(R.layout.splash);
		//startActivity(new Intent(this, SplashActivity.class));

		Handler handler = new Handler () {
			@Override
			public void handleMessage(Message msg) {
				selboard = "B200902281833016691048";

				showmain();
			}
		};

		handler.sendEmptyMessageDelayed(0, 3000);
	}
	public OnClickListener CategoriListner = new OnClickListener(){

		@Override
		public void onClick(View arg0) {
			//mTestLayout.setVisibility(View.VISIBLE);
			switch(arg0.getId())
			{
			case R.id.anon:
				FlurryAgent.logEvent("anon", true);
				selboard = "B200902281833016691048";
				anon.setBackgroundResource(R.drawable.button_01);
				hacsa.setBackgroundResource(R.drawable.button_02_b);
				hacwon.setBackgroundResource(R.drawable.button_03_b);
				sug.setBackgroundResource(R.drawable.button_04_b);
				QA.setBackgroundResource(R.drawable.button_05_b);
				hacsaQA.setBackgroundResource(R.drawable.button_06_b);
				break;
			case R.id.hacsa:
				FlurryAgent.logEvent("hacsa", true);
				selboard = "B200902281833482321051";
				anon.setBackgroundResource(R.drawable.button_01_b);
				hacsa.setBackgroundResource(R.drawable.button_02);
				hacwon.setBackgroundResource(R.drawable.button_03_b);
				sug.setBackgroundResource(R.drawable.button_04_b);
				QA.setBackgroundResource(R.drawable.button_05_b);
				hacsaQA.setBackgroundResource(R.drawable.button_06_b);
				break;
			case R.id.hacwon:
				FlurryAgent.logEvent("hacwon", true);
				selboard = "B201003111719010571299";
				anon.setBackgroundResource(R.drawable.button_01_b);
				hacsa.setBackgroundResource(R.drawable.button_02_b);
				hacwon.setBackgroundResource(R.drawable.button_03);
				sug.setBackgroundResource(R.drawable.button_04_b);
				QA.setBackgroundResource(R.drawable.button_05_b);
				hacsaQA.setBackgroundResource(R.drawable.button_06_b);
				break;
			case R.id.sug:
				FlurryAgent.logEvent("sug", true);
				selboard = "B200805221624473331040";
				anon.setBackgroundResource(R.drawable.button_01_b);
				hacsa.setBackgroundResource(R.drawable.button_02_b);
				hacwon.setBackgroundResource(R.drawable.button_03_b);
				sug.setBackgroundResource(R.drawable.button_04);
				QA.setBackgroundResource(R.drawable.button_05_b);
				hacsaQA.setBackgroundResource(R.drawable.button_06_b);
				break;
			case R.id.QA:
				FlurryAgent.logEvent("QA", true);
				selboard = "B200806120956049151016";
				anon.setBackgroundResource(R.drawable.button_01_b);
				hacsa.setBackgroundResource(R.drawable.button_02_b);
				hacwon.setBackgroundResource(R.drawable.button_03_b);
				sug.setBackgroundResource(R.drawable.button_04_b);
				QA.setBackgroundResource(R.drawable.button_05);
				hacsaQA.setBackgroundResource(R.drawable.button_06_b);
				break;
			case R.id.hacsaQA:
				FlurryAgent.logEvent("hacsaQA", true);
				selboard = "B200903111033027841090";
				anon.setBackgroundResource(R.drawable.button_01_b);
				hacsa.setBackgroundResource(R.drawable.button_02_b);
				hacwon.setBackgroundResource(R.drawable.button_03_b);
				sug.setBackgroundResource(R.drawable.button_04_b);
				QA.setBackgroundResource(R.drawable.button_05_b);
				hacsaQA.setBackgroundResource(R.drawable.button_06);
				break;
			}

			page=1;
			showmain();
//			ht = new httptask(httpclient);
//			httptask.clearPOST();
//			board.clear();
//			aa.clear();
//			arr.clear();
//			aa.notifyDataSetChanged();
//			httptask.makehandler(handler);
//			httptask.makeHttpPost("boardid",selboard);
//			httptask.makeHttpPost("nfirst",""+page);
//			ht.execute("dcmichael1256", "bd0775de61645be9a39de434d178580e", "http://portal.unist.ac.kr/EP/web/collaboration/bbs/jsp/BB_BoardLst.jsp");

		}
	};
	public void setbuttonlist(int view)
	{

		setContentView(view);
		btnlist = new ArrayList<Button>();
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
			b.setOnClickListener(CategoriListner);
		}

		if(0==selboard.compareTo("B200902281833016691048")){
			anon.setBackgroundResource(R.drawable.button_01);
			hacsa.setBackgroundResource(R.drawable.button_02_b);
			hacwon.setBackgroundResource(R.drawable.button_03_b);
			sug.setBackgroundResource(R.drawable.button_04_b);
			QA.setBackgroundResource(R.drawable.button_05_b);
			hacsaQA.setBackgroundResource(R.drawable.button_06_b);
		}
		else if(0==selboard.compareTo("B200902281833482321051")){
			anon.setBackgroundResource(R.drawable.button_01_b);
			hacsa.setBackgroundResource(R.drawable.button_02);
			hacwon.setBackgroundResource(R.drawable.button_03_b);
			sug.setBackgroundResource(R.drawable.button_04_b);
			QA.setBackgroundResource(R.drawable.button_05_b);
			hacsaQA.setBackgroundResource(R.drawable.button_06_b);
		}
		else if(0==selboard.compareTo("B201003111719010571299")){
			anon.setBackgroundResource(R.drawable.button_01_b);
			hacsa.setBackgroundResource(R.drawable.button_02_b);
			hacwon.setBackgroundResource(R.drawable.button_03);
			sug.setBackgroundResource(R.drawable.button_04_b);
			QA.setBackgroundResource(R.drawable.button_05_b);
			hacsaQA.setBackgroundResource(R.drawable.button_06_b);
		}
		else if(0==selboard.compareTo("B200805221624473331040")){
			anon.setBackgroundResource(R.drawable.button_01_b);
			hacsa.setBackgroundResource(R.drawable.button_02_b);
			hacwon.setBackgroundResource(R.drawable.button_03_b);
			sug.setBackgroundResource(R.drawable.button_04);
			QA.setBackgroundResource(R.drawable.button_05_b);
			hacsaQA.setBackgroundResource(R.drawable.button_06_b);
		}
		else if(0==selboard.compareTo("B200806120956049151016")){
			anon.setBackgroundResource(R.drawable.button_01_b);
			hacsa.setBackgroundResource(R.drawable.button_02_b);
			hacwon.setBackgroundResource(R.drawable.button_03_b);
			sug.setBackgroundResource(R.drawable.button_04_b);
			QA.setBackgroundResource(R.drawable.button_05);
			hacsaQA.setBackgroundResource(R.drawable.button_06_b);
		}
		else if(0==selboard.compareTo("B200903111033027841090")){
			anon.setBackgroundResource(R.drawable.button_01_b);
			hacsa.setBackgroundResource(R.drawable.button_02_b);
			hacwon.setBackgroundResource(R.drawable.button_03_b);
			sug.setBackgroundResource(R.drawable.button_04_b);
			QA.setBackgroundResource(R.drawable.button_05_b);
			hacsaQA.setBackgroundResource(R.drawable.button_06);
		}
		else
			selboard = "B200902281833016691048";
	}
	public void showmain()
	{
		//loaded=false;
		FlurryAgent.logEvent("mainlist", true);

		setbuttonlist(R.layout.activity_main);
		Log.println(Log.ASSERT, "aaa", "page : "+page);

		adView = (MobileAdView) findViewById(R.id.adview1);;
		//adView.setTest(true);
		adView.setListener(this);
		adView.setTest(false);
		adView.setChannelID("mandroid_634225d2b27d44d3b1e74db6c9238be5");
		adView.start();

		lv = (ListView) findViewById(R.id.listView1);
		arr = new ArrayList<Spanned>();
		//arr2 = new ArrayList<String>();

		nextLayout = (LinearLayout) View.inflate(this, R.layout.readnext, null);
		nextLayout.setVisibility(View.VISIBLE);
		prevLayout = (LinearLayout) View.inflate(this, R.layout.readprev, null);
		if(page!=1)
		{
			prevLayout.setVisibility(View.VISIBLE);
		}
		if(page==1)
		{
			prevLayout.setVisibility(View.INVISIBLE);
		}
		html = "";
		//arr.add(html);

		aa = new myadapter<Spanned>(this, R.layout.mylist, arr);
		//aa2 = new myadapter<String>(this, R.layout.mylist, arr2);
//		arr2.add("전체공지");
//		arr2.add("학사공지");
//		arr2.add("대학원공지");
//		arr2.add("개선및 제안");
//		arr2.add("Q&A");
//		arr2.add("학사Q&A");
		((TextView)nextLayout.findViewById(R.id.testtext)).setText("로딩중");

		//lv2 = (ListView) findViewById(R.id.listView2);
		lv.addFooterView(nextLayout);
		lv.addHeaderView(prevLayout);
		lv.setAdapter(aa);
		board.clear();
		aa.clear();
		arr.clear();
		aa.notifyDataSetChanged();
		//lv2.setAdapter(aa2);
//		int max_len=0;
//		for(int i=0; i<arr2.size(); i++)
//		{
//			if(max_len<arr2.get(i).length())
//				max_len = arr2.get(i).length();
//		}

		lv.setOnItemClickListener(new ListView.OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int index,
					long arg3) {
				Log.println(Log.ASSERT, "index", "index : "+index+"size : "+board.size());
//				if(index==0)
//				{
//
//					ht = new httptask(httpclient);
//					httptask.clearPOST();
//					//board.clear();
//					//aa.clear();
//					//arr.clear();
//					page--;
//					httptask.makehandler(handler);
//					httptask.makeHttpPost("boardid",selboard);
//					httptask.makeHttpPost("nfirst",""+page);
//					ht.execute("dcmichael1256", "bd0775de61645be9a39de434d178580e", "http://portal.unist.ac.kr/EP/web/collaboration/bbs/jsp/BB_BoardLst.jsp");
//					if(page==1)
//					{
//						prevLayout.setVisibility(View.INVISIBLE);
//					}
//					lv.setSelection(1);
//				}
//				else
				if(index>board.size())
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
					
					//prevLayout.setVisibility(View.VISIBLE);
					//lv.setSelection(1);
				}
				else
				{
					Board a = board.get(index-1);
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

		board.clear();
		aa.clear();
		arr.clear();
		aa.notifyDataSetChanged();
		ht = new httptask(httpclient);
		httptask.clearPOST();
		httptask.makehandler(handler);
		httptask.makeHttpPost("boardid",selboard);
		httptask.makeHttpPost("nfirst",""+page);
		ht.execute("dcmichael1256", "bd0775de61645be9a39de434d178580e", "http://portal.unist.ac.kr/EP/web/collaboration/bbs/jsp/BB_BoardLst.jsp");
		
	}
	public void showcontents()
	{
		FlurryAgent.logEvent("contentsview", true);
		setbuttonlist(R.layout.boardview);
		WebView wv = (WebView) findViewById(R.id.content);
		wv.getSettings().setBuiltInZoomControls(true);
		wv.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);
		TextView tit = (TextView) findViewById(R.id.title);
		TextView dat = (TextView) findViewById(R.id.date);
		TextView wri = (TextView) findViewById(R.id.writer);
		TextView mai = (TextView) findViewById(R.id.mail);
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
		Writer = Writer.replace("	", "");
		wri.setText(""+Writer);
		String mail = html.substring(html.indexOf("작성자메일</td>")+"작성자메일</td>".length());
		mail = mail.substring(0,mail.indexOf("</td>")+"</td>".length());
		mail = Html.fromHtml(mail).toString();
		mai.setText(""+mail);
		html = html.substring(html.indexOf("<!--게시물 내용-->"), html.indexOf("<td class=\"td_bg\" colspan=\"2\">"));
		int indchum=-1;
		
		if((indchum=html.indexOf("<iframe name=\"IFR_ATT_CON\" id=\"IFR_ATT_CON\""))!=-1)
		{
			String html1 = html.substring(0, indchum);
			String html2 = html.substring(html.indexOf("</iframe>"));
			html = html1 + "첨부파일이 있습니다. windows 에서 확인하세요" + html2;
		}
		if((indchum=html.indexOf("첨부파일"))!=-1)
		{
			html = html.substring(0, indchum);
			html += "첨부파일이 있습니다. windows 에서 확인하세요";
		}
		 
		
		tit.setTextColor(Color.BLACK);
		dat.setTextColor(Color.BLACK);
		wri.setTextColor(Color.BLACK);
		mai.setTextColor(Color.BLACK);
		wv.setBackgroundColor(0);
		wv.loadDataWithBaseURL("", html, "text/html", "utf-8", null);

		//Spanned a = Html.fromHtml(html);
		//tv2.setText(html);
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
	public Handler ehandler = new Handler()
	{
		public void handleMessage(Message msg)
		{

            Toast.makeText(getApplicationContext(), "아직 로드중입니다.", Toast.LENGTH_SHORT).show();
		}
	};
	public Handler bhandler = new Handler()
	{
		public void handleMessage(Message msg)
		{
			if(msg.what==1)
			{
	            Toast.makeText(getApplicationContext(), "아직 로드중입니다.", Toast.LENGTH_SHORT).show();
	            return;
			}
			if(httptask.result==null)
				return;
			html = httptask.result;
			//Intent intent = new Intent(MainActivity.this, Boardview.class);
			//intent.putExtra("Data", html);
			//startActivity(intent);
			showcontents();
		}
	};
	public Handler handler = new Handler()  {

		public void handleMessage(Message msg)  {
			if(msg.what==1)
			{
	            Toast.makeText(getApplicationContext(), "아직 로드중입니다.", Toast.LENGTH_SHORT).show();
	            return;
			}
			if(httptask.result==null)
				return;
			html = httptask.result;//.substring(ht.result.indexOf("title=")+8);

			html = html.substring(html.indexOf("clickBulletin")+"clickBulletin(\"".length());
			//arr.clear();
			//aa.clear();
			//board.clear();
			while(html.indexOf("clickBulletin")!=-1)
			{
				html = html.substring(html.indexOf("checkbox")+5);
				html = html.substring(html.indexOf("<nobr>")+6);
				html = html.substring(html.indexOf("<nobr>"));
				int end = html.indexOf("</nobr>");

				//Log.println(Log.ASSERT, "num", ""+end);
				//Log.println(Log.ASSERT, "html", html);
				String num = html.substring("<nobr>".length(), end);
				if(num.compareTo("&nbsp;")==0)
					num = html.substring(6, end);
				else if(num.startsWith("<img"))
					num="공지";
				else
					num = html.substring(6, end);
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
				titlesource = titlesource.replace("color=", "size=12 color=");
				Log.e("title:", titlesource);
				Spanned title = Html.fromHtml(titlesource);
				
				bullid = bullid.replace("\'", "");
				row_id = row_id.replace("\'", "");
				top_bulid = top_bulid.replace("\'", "");
				depth = depth.replace("\'", "");
				//Log.println(Log.ASSERT, "html", title + "\n" + bullid + "\n" + row_id + "\n" + top_bulid + "\n" + depth);
				Board b = new Board(num, title, bullid, row_id, top_bulid, Integer.valueOf(depth));
				board.add(b);
				html = html.substring(html.indexOf("\"")+1);
				aa.add(Html.fromHtml(num+" "+titlesource));
				aa.notifyDataSetChanged();
				//lv.setSelection(1);
				//mTestLayout.setVisibility(View.INVISIBLE);
			} 
			//lv.setSelection(1);
			
			((ProgressBar)nextLayout.findViewById(R.id.testtext1)).setVisibility(View.INVISIBLE);
			((TextView)nextLayout.findViewById(R.id.testtext)).setText("다음글");
			if(page==1)
			{
				page++;
				ht = new httptask(httpclient);
				httptask.clearPOST();
				httptask.makehandler(handler);
				httptask.makeHttpPost("boardid",selboard);
				httptask.makeHttpPost("nfirst",""+page);
				ht.execute("dcmichael1256", "bd0775de61645be9a39de434d178580e", "http://portal.unist.ac.kr/EP/web/collaboration/bbs/jsp/BB_BoardLst.jsp");
			}
		}

	};
	protected void onDestroy()
	{
		super.onDestroy();
		FlurryAgent.onEndSession(this);
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
			page=1;
			Log.println(Log.ASSERT, "aaa", "page : "+page);
			showmain();
			return false;
		}
		return true;
	}
}
