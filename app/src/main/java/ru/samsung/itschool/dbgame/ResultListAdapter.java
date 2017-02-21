package ru.samsung.itschool.dbgame;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultListAdapter extends ArrayAdapter<Result> {

	private final Context context;
	private final ArrayList<Result> results;

	public ResultListAdapter(Context context, ArrayList<Result> results) {
		super(context, R.layout.rowlayout, results);
		this.context = context;
		this.results = results;
	}

	@Override
	public View getView(int position, View rowView, ViewGroup parent) {
		if (rowView == null)
		{	
		   LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		   rowView = inflater.inflate(R.layout.rowlayout, parent, false);
		}
		TextView resulttv = (TextView) rowView.findViewById(R.id.result2);
		resulttv.setText(results.get(position).score + " " + results.get(position).name);
		return rowView;
	}
}
