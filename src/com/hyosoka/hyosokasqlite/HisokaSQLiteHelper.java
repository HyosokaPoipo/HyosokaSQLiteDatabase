package com.hyosoka.hyosokasqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class HisokaSQLiteHelper extends SQLiteOpenHelper {

	//Versi database
	private static final int VERSI_DATABASE = 1;
	
	//Nama database
	private static final String NAMA_DATABASE = "HisokaBook";
	
	//Table Name
	private static final String TABLE_NAME = "hisokaBookTable";
	
	//Column buat tabelnya
	private static final String KEY_ID = "id";
	private static final String KEY_JUDUL = "judul";
	private static final String KEY_PENGARANG = "pengarang";
	private static final String KEY_HARGA = "harga";
	private static final String [] COLUMNS = {KEY_ID, KEY_JUDUL, KEY_PENGARANG, KEY_HARGA};
	
	public HisokaSQLiteHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String perintah_create = "CREATE TABLE "+ TABLE_NAME +" (" +
		"id INTEGER PRIMARY KEY AUTOINCREMENT, "+
				"judul TEXT"+
		"pengarang TEXT"+
				"harga TEXT )";
		
		//ngebuat databasenya
		db.execSQL(perintah_create);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Buang tablenya klu udah ada
		db.execSQL("DROP TABLE IF EXISTS hisokaBook");
		
		// Kemudian create table yang baru lagi
		this.onCreate(db);
	}


//*******************************ADD*******************************//
	public void ADDtoDatabase(HisokaBookClass buku)
	{
		Log.i("Data Buku", buku.toString());
		
		//Ngedapatin referensi ke database
		SQLiteDatabase HyosokaDB = this.getWritableDatabase();
		
		//ContentValue untuk masukin nilai baru ke database
		ContentValues CV = new ContentValues();
		CV.put(KEY_JUDUL,buku.getJudul());
		CV.put(KEY_PENGARANG, buku.getPengarang());
		CV.put(KEY_HARGA, buku.getHarga());
		
		//Terus diInsert
		HyosokaDB.insert(TABLE_NAME, null, CV);
		
		//Trakhir close databasenya
		HyosokaDB.close();
		
	}
	
	
	
	
	
	
	
	
	
	
	
	









}
