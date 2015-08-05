package com.hyosoka.hyosokasqlite;

public class HisokaBookClass {

	private int id;
	private String judul;
	private String pengarang;
	private String harga;
	
	
	public HisokaBookClass()
	{
		
	}
	
	public HisokaBookClass(int id, String jdl, String pngrng, String hrg)
	{
		super();
		this.id = id;
		this.judul = jdl;
		this.pengarang = pngrng;
		this.harga = hrg;
	}
	
	
	@Override
	public String toString()
	{
		return "HisokaBook [id=" +id +", judul= "+ judul +", pengarang="
				+pengarang+ ", harga="+ harga + "]";
	}
	
	
	
	public int getID()
	{
		return this.id;
	}
	
	public String getJudul()
	{
		return this.judul;
	}
	
	public String getPengarang()
	{
		return this.pengarang;
	}
	
	public String getHarga()
	{
		return this.harga;
	}
	
	public void setID(int mID)
	{
		this.id = mID;
	}
	
	public void setJudul(String mJudul)
	{
		this.judul = mJudul;
	}
	
	public void setPengarang(String mPengarang)
	{
		this.pengarang = mPengarang;
	}
	
	public void setHarga(String mHarga)
	{
		this.harga = mHarga;
	}
}
