package me.pedaling.ouruniv;

import android.app.Activity;
import android.os.Bundle;
import me.pedaling.ouruniv.R;

public class Boardview extends Activity {
	String html;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    Bundle extras = getIntent().getExtras();
	    html = extras.getString("Data");
	    setContentView(R.layout.boardview);
	    // TODO Auto-generated method stub
	}

}
