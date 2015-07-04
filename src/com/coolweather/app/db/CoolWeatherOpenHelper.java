package com.coolweather.app.db;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.coolweather.app.R;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

public class CoolWeatherOpenHelper extends SQLiteOpenHelper {

	private final Context myContext;
	private SQLiteDatabase myDataBase;
	
	/**
	 * Province表建表语句
	 */
	public static final String CREATE_AREA = "create table area ("
			+ "id integer primary key, " + "NameEN text, " + "NameCN text,"
			+ "DistrictEN text," + "DistrictCN text," + "ProvEN text,"
			+ "ProvCN text," + "NationEN text," + "NationCN text)";

	public CoolWeatherOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO 自动生成的构造函数存根
		this.myContext = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO 自动生成的方法存根
		// db.execSQL(CREATE_AREA);
		// db.execSQL(CREATE_COUNTY);
		// db.execSQL(CREATE_PROVINCE);
	}

	@Override
	public synchronized SQLiteDatabase getWritableDatabase() {
		// TODO Auto-generated method stub
		return openDatabase();
	}

	@Override
	public synchronized SQLiteDatabase getReadableDatabase() {
		// TODO Auto-generated method stub
		return openDatabase();
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO 自动生成的方法存根

	}

	@Override
	public synchronized void close() {
		// TODO Auto-generated method stub
		if (myDataBase != null)
			myDataBase.close();
		super.close();
	}

	private SQLiteDatabase openDatabase() {
		int BUFFER_SIZE = 400000;
		String DB_NAME = "coolweather.db"; // 保存的数据库文件名
		String PACKAGE_NAME = "com.coolweather.app";// 包名
		String DB_PATH = "/data"
				+ Environment.getDataDirectory().getAbsolutePath() + "/"
				+ PACKAGE_NAME; // 在手机里存放数据库的位置

		String dbfile = DB_PATH + "/" + DB_NAME;
		try {
			if (!(new File(dbfile).exists())) { // 判断数据库文件是否存在，若不存在则执行导入，否则直接打开数据库
				InputStream is = this.myContext.getResources().openRawResource(
						R.raw.coolweather); // 欲导入的数据库
				FileOutputStream fos = new FileOutputStream(dbfile);
				byte[] buffer = new byte[BUFFER_SIZE];
				int count = 0;
				while ((count = is.read(buffer)) > 0) {
					fos.write(buffer, 0, count);
				}
				fos.close();
				is.close();
			}
			SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(dbfile,
					null);
			return db;
		} catch (FileNotFoundException e) {
			Log.e("Database", "File not found");
			e.printStackTrace();
		} catch (IOException e) {
			Log.e("Database", "IO exception");
			e.printStackTrace();
		}
		return null;
	}
}
