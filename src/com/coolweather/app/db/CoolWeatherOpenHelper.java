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
	 * Province�������
	 */
	public static final String CREATE_AREA = "create table area ("
			+ "id integer primary key, " + "NameEN text, " + "NameCN text,"
			+ "DistrictEN text," + "DistrictCN text," + "ProvEN text,"
			+ "ProvCN text," + "NationEN text," + "NationCN text)";

	public CoolWeatherOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO �Զ����ɵĹ��캯�����
		this.myContext = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO �Զ����ɵķ������
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
		// TODO �Զ����ɵķ������

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
		String DB_NAME = "coolweather.db"; // ��������ݿ��ļ���
		String PACKAGE_NAME = "com.coolweather.app";// ����
		String DB_PATH = "/data"
				+ Environment.getDataDirectory().getAbsolutePath() + "/"
				+ PACKAGE_NAME; // ���ֻ��������ݿ��λ��

		String dbfile = DB_PATH + "/" + DB_NAME;
		try {
			if (!(new File(dbfile).exists())) { // �ж����ݿ��ļ��Ƿ���ڣ�����������ִ�е��룬����ֱ�Ӵ����ݿ�
				InputStream is = this.myContext.getResources().openRawResource(
						R.raw.coolweather); // ����������ݿ�
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
