package ru.samsung.itschool.dbgame;

import java.io.File;
import java.util.ArrayList;

import ru.samsung.itschool.task.Task;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

public class DBManager {
	/*
	 * TABLES:
	 * 
	 * RESULTS USERID INTEGER SCORE INTEGER
	 * 
	 * USERS USERID INTEGER PRIMARY KEY ASC NAME TEXT PIC TEXT
	 */
	private Context context;
	private String DB_NAME = "game.db";

	private SQLiteDatabase db;

	private static DBManager dbManager;

	public static DBManager getInstance(Context context) {
		if (dbManager == null) {
			dbManager = new DBManager(context);
			Task.showMessage(context, "Теперь используется две таблицы. Исправьте методы getAllResults и userUpdate");
		}
		return dbManager;
	}

	private DBManager(Context context) {
		this.context = context;
		db = context.openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
		createTablesIfNeedBe();
	}

	void addResult(String username, int score) {
		
	}

	ArrayList<Result> getAllResults() {

		ArrayList<Result> data = new ArrayList<Result>();
		//напишите запрос правильно
		Cursor cursor = db.rawQuery("SELECT 'NameStub' AS USERNAME, SCORE FROM RESULTS LIMIT 100", null);
		boolean hasMoreData = cursor.moveToFirst();

		while (hasMoreData) {
			String name = cursor.getString(cursor.getColumnIndex("USERNAME"));
			int score = Integer.parseInt(cursor.getString(cursor
					.getColumnIndex("SCORE")));
			data.add(new Result(name, score));
			hasMoreData = cursor.moveToNext();
		}
		return data;
	}

	int getPlayerIDByName(String username) {
		Cursor cursor = db.rawQuery("SELECT USERID FROM USERS WHERE NAME='"
				+ username + "'", null);
		if (!cursor.moveToFirst()) {
			return -1;
		}
		return cursor.getInt(cursor.getColumnIndex("USERID"));
	}

	void userUpdate(int userid, String username, String pic)
	{
		//напишите запрос правильно
		db.execSQL("UPDATE USERS SET NAME = 'NameStub' WHERE USERID = -1;");
	}
	
	String getUserName(int userid) {
		Cursor cursor = db.rawQuery("SELECT NAME FROM USERS WHERE USERID='"
				+ userid + "'", null);
		if (cursor.moveToFirst()) return cursor.getString(0);
		return "";
	}
	
	String getUserPic(int userid) {
		Cursor cursor = db.rawQuery("SELECT PIC FROM USERS WHERE USERID='"
				+ userid + "'", null);
		cursor.moveToFirst();
		//Если нет фото - возвращаем пустую строку
		if (!cursor.moveToFirst() || cursor.isNull(0)) return "";  
		else return cursor.getString(0);
	}

	private void createTablesIfNeedBe() {
		db.execSQL("CREATE TABLE IF NOT EXISTS RESULTS (USERID INTEGER, SCORE INTEGER);");
		db.execSQL("CREATE TABLE IF NOT EXISTS USERS(USERID INTEGER PRIMARY KEY ASC, NAME TEXT, PIC TEXT);");
	}

	private boolean dbExist() {
		File dbFile = context.getDatabasePath(DB_NAME);
		return dbFile.exists();
	}
	
}
