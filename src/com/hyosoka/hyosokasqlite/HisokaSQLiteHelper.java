package com.hyosoka.hyosokasqlite;

import java.util.LinkedList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
	
	public HisokaSQLiteHelper(Context ctx)
	{
		super(ctx,NAMA_DATABASE, null, VERSI_DATABASE);
	}	
	
	/*
	public HisokaSQLiteHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}
   */
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String perintah_create = "CREATE TABLE "+ TABLE_NAME +"(" +
		"id INTEGER PRIMARY KEY, "+
				"judul TEXT,"+
		"pengarang TEXT,"+
				"harga TEXT)";
		
		Log.i("PerintahCreate", perintah_create);
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
		CV.put(KEY_ID, buku.getID());
		CV.put(KEY_JUDUL,buku.getJudul());
		CV.put(KEY_PENGARANG,buku.getPengarang());
		CV.put(KEY_HARGA,buku.getHarga());
		
		
		
		
		//Terus diInsert
		if(HyosokaDB.insert(TABLE_NAME, null, CV) == -1) Log.i("InserFailed", "Add data error");
		
		//Trakhir close databasenya
		HyosokaDB.close();
		
	}
	
//***************************UPDATE BOOK****************************//
	public int UpdateDatabase(HisokaBookClass buku)
	{
		SQLiteDatabase HyosokaDB = this.getWritableDatabase();
		
		ContentValues CV = new ContentValues();
		CV.put(KEY_JUDUL,buku.getJudul());
		CV.put(KEY_PENGARANG, buku.getPengarang());
		CV.put(KEY_HARGA, buku.getHarga());
		
		//Updating...
		int i = HyosokaDB.update(TABLE_NAME, CV, KEY_ID+" = ?", new String[]{String.valueOf(buku.getID())});
		
		HyosokaDB.close();
		
		//i disini adalah nomor baris yang diubah atau diupdate
		return i;		
	}
	
//***************************DELETE BOOK*****************************//
	public void DeleteBook(HisokaBookClass buku)
	{
		SQLiteDatabase HisokaDB = this.getWritableDatabase();
		
		HisokaDB.delete(TABLE_NAME, KEY_ID+" =?",new String[]{String.valueOf(buku.getID())});
		
		HisokaDB.close();
	}
	

//***************************GET INFO**********************************//
	public HisokaBookClass getBook(int id)
	{
		SQLiteDatabase HisokaDB = this.getReadableDatabase();
		
		//Query
		Cursor HisokaCursor  = HisokaDB.query(TABLE_NAME,/*Nama Table*/
				COLUMNS,/*Nama Coloumn*/
				" id = ?",/*Seleksi*/
				new String[]{String.valueOf(id)}, /*ID buku yang akan diambil*/
				null, null, null);
		
		HisokaBookClass buku = new HisokaBookClass();
		//Jika id yang dicari ada...
		if(HisokaCursor != null)
		{
			HisokaCursor.moveToFirst();
			
			buku.setID(Integer.parseInt(HisokaCursor.getString(0)));
			buku.setJudul(HisokaCursor.getString(1));
			buku.setPengarang(HisokaCursor.getString(2));
			buku.setHarga(HisokaCursor.getString(3));
		}else
		{
			//Return g' ada klu yang dicari g' ada
			buku = null;
		}
		
		
		
		return buku;		
	}
	
//****************************GET ALL BOOOKS*****************************//
	public List<HisokaBookClass> getAllBooks()
	{
		List<HisokaBookClass> listBuku = new LinkedList<HisokaBookClass>();
		
		String query = "SELECT * FROM "+ TABLE_NAME;
		
		SQLiteDatabase HisokaDB = this.getWritableDatabase();
		Cursor kursor = HisokaDB.rawQuery(query, null);
		
		//Looping utk ngambil semua data2 datal database
		HisokaBookClass buku = null;
		if(kursor.moveToFirst())
		{
			do{
				buku = new HisokaBookClass();
				buku.setID(Integer.parseInt(kursor.getString(0)));
				buku.setJudul(kursor.getString(1));
				buku.setPengarang(kursor.getString(2));
				buku.setHarga(kursor.getString(3));
				
				//Masukin infonya ke listBuku
				listBuku.add(buku);
			}while(kursor.moveToNext());
		}
		
		return listBuku;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	









}
