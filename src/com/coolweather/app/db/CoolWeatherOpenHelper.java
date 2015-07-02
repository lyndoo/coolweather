package com.coolweather.app.db;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.coolweather.app.R;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

public class CoolWeatherOpenHelper extends SQLiteOpenHelper {

	private final Context myContext;
	private SQLiteDatabase myDataBase;
	/**
	 * Province表建表语句
	 */
	public static final String CREATE_PROVINCE = "create table Province ("
			+ "id integer primary key autoincrement, " + "province_name text, "
			+ "province_code text)";
	/**
	 * City表建表语句
	 */
	public static final String CREATE_CITY = "create table City ("
			+ "id integer primary key autoincrement, " + "city_name text, "
			+ "city_code text, " + "province_id integer)";
	/**
	 * County表建表语句
	 */
	public static final String CREATE_COUNTY = "create table County ("
			+ "id integer primary key autoincrement, " + "county_name text, "
			+ "county_code text, " + "city_id integer)";

	public CoolWeatherOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO 自动生成的构造函数存根
		this.myContext = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO 自动生成的方法存根
		
		//db.execSQL(CREATE_CITY);
		//db.execSQL(CREATE_COUNTY);
		//db.execSQL(CREATE_PROVINCE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO 自动生成的方法存根

	}

	public void createDatabse() throws IOException{
		init();
	}
	
	@Override
	public synchronized void close() {
		// TODO Auto-generated method stub
		if(myDataBase!=null)
			myDataBase.close();
		super.close();
	}
	
	/**
	方法说明
	* @creator   leixun
	* @create-time 2012-7-13 下午1:28:13  
	* @Title: init
	* @Description: TODO(初始化数据库，并打开)    
	* @return void
	* @throws
	*/
	public void init(){
		try{
			String databasePath = Environment.getExternalStorageDirectory().getAbsolutePath();
			String databaseFilename = databasePath + "/" + "coolweather";
			File dir = new File(databasePath);
			if(!dir.exists()){  //数据库文件目录不存在
				dir.mkdir(); 	//创建目录  
			}
			if(!(new File(databaseFilename)).exists()){ //数据库文件不存在，则复制自带数据库
				System.out.println("jianku");
				InputStream is = myContext.getResources().openRawResource(R.raw.coolweather);
				FileOutputStream fos = new FileOutputStream(databaseFilename);
				byte[] buffer = new byte[1024];
				int count = 0;
				while((count = is.read(buffer))>0){
					fos.write(buffer,0,count);
				}
				fos.flush();
				fos.close();
				is.close();
			}
			this.myDataBase = SQLiteDatabase.openOrCreateDatabase(databaseFilename, null);//打开数据库文件
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
