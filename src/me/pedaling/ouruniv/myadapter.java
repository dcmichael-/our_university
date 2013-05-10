package me.pedaling.ouruniv;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class myadapter<T> extends ArrayAdapter<T> {

	public myadapter(Context context, int textViewResourceId, List<T> objects) {
		super(context, textViewResourceId, objects);
		// TODO Auto-generated constructor stub
	}

	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
      View view = super.getView(position, convertView, parent);
      //view.setBackgroundResource(R.drawable.list);
      return view;
    }
}
