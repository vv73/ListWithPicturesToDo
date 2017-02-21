package ru.samsung.itschool.dbgame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class ExtResults extends Activity {

	DBManager dbManager;
	final ArrayList<Result> results = new ArrayList<Result>();
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ext_results);
		dbManager = DBManager.getInstance(this);
		lvRefresh();
	}
	private void lvRefresh()
	{
		results.clear();
		results.addAll(dbManager.getAllResults());
		ListView reslv = (ListView) this.findViewById(R.id.listView1);
		reslv.setAdapter(new ResultListAdapter(this, results));
		reslv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent i = new Intent(ExtResults.this, PlayerActivity.class);
				i.putExtra("playerID",
						dbManager.getPlayerIDByName(results.get(position).name));
				ExtResults.this.startActivity(i);

			}

		});
	}
	public void gen5games(View v)
	{
		String[] userNames = new String[]{"Петр", "Алексей", "Вадим", "Григорий", "Вячеслав"};
		Random rand = new Random();
		for (int i = 0; i < 5; i++)
		{
			dbManager.addResult(userNames[rand.nextInt(userNames.length)], rand.nextInt(1000)); 
		}
		lvRefresh();
	}
	
}
