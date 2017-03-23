package com.example.prateek.mystackoverflow;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBAdapter extends SQLiteOpenHelper {
	static String name = "Mystackoverflow.sqlite";
	static String path = "";
	static ArrayList<mydata> a;
	static SQLiteDatabase sdb;

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}


	private DBAdapter(Context v) {
		super(v, name, null, 1);
		path = "/data/data/" + v.getApplicationContext().getPackageName()
				+ "/databases";
	}

	public boolean checkDatabase() {
		SQLiteDatabase db = null;
		try {
			db = SQLiteDatabase.openDatabase(path + "/" + name, null,
					SQLiteDatabase.OPEN_READWRITE);
		} catch (Exception e) {
		}

		if (db == null) {
			return false;
		} else {
			db.close();
			return true;
		}
	}

	public static synchronized DBAdapter getDBAdapter(Context v) {
		return (new DBAdapter(v));
	}

	public void createDatabase(Context v) {
		this.getReadableDatabase();
		try {
			InputStream myInput = v.getAssets().open(name);
			// Path to the just created empty db
			String outFileName = path + "/" + name;
			// Open the empty db as the output stream
			OutputStream myOutput = new FileOutputStream(outFileName);
			// transfer bytes from the inputfile to the outputfile
			byte[] bytes = new byte[1024];
			int length;
			while ((length = myInput.read(bytes)) > 0) {
				myOutput.write(bytes, 0, length);
			}
			// Close the streams
			myOutput.flush();
			myOutput.close();
			myInput.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public void openDatabase() {
		try {
			sdb = SQLiteDatabase.openDatabase(path + "/" + name, null,
					SQLiteDatabase.OPEN_READWRITE);
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public void insertDBQ(String title, String user, String body, int vote, int qid) {
		ContentValues cv = new ContentValues();
		cv.put("user", user);
		cv.put("title", title);
		cv.put("votes", vote);
		cv.put("body", body);
		cv.put("qid", qid);
		sdb.insert("Questions", null, cv);
	}

	public void insertDBA(String answer, String user, int qid, int vote, int gbadge, int sbadge, int bbadge) {
		ContentValues cv = new ContentValues();
		cv.put("answer", answer);
		cv.put("user", user);
		cv.put("qid", qid);
		cv.put("votes", vote);
		cv.put("gbadge", gbadge);
		cv.put("sbadge", sbadge);
		cv.put("bbadge", bbadge);
		sdb.insert("Answer", null, cv);
	}

	public ArrayList<mydata> GetDataQ(int qid) {
		if (qid == 0) {
			Cursor c1 = sdb.rawQuery("select * from Questions", null);
			a = new ArrayList<mydata>();
			while (c1.moveToNext()) {
				mydata q1 = new mydata();
				q1.setUser(c1.getString(1));
				q1.setTitle(c1.getString(2));
				q1.setVotes(c1.getInt(3));
				q1.setBody(c1.getString(4));
				q1.setQid(c1.getInt(5));
				a.add(q1);
			}
		} else {
			Cursor c1 = sdb.rawQuery("select title,body from Questions where qid=" + qid, null);
			a = new ArrayList<mydata>();
			mydata q2 = new mydata();
			q2.setTitle(c1.getString(0));
			q2.setBody(c1.getString(1));
			a.add(q2);
		}
		return a;
	}

	public ArrayList<mydata> GetDataA(int qid) {
		if (qid == 0) {
			Cursor c1 = sdb.rawQuery("select * from Answers where qid=" + qid, null);
			a = new ArrayList<mydata>();
			while (c1.moveToNext()) {
				mydata q1 = new mydata();
				q1.setAnswer(c1.getString(1));
				q1.setTitle(c1.getString(2));
				q1.setVotes(c1.getInt(4));
				q1.setUsera(c1.getString(2));
				a.add(q1);
			}
		}
		return a;
	}

}
