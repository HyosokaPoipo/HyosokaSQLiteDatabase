package com.hyosoka.hyosokasqlite;

public class HisokaBookClass {

	private int id;
	private String judul;
	private String pengarang;
	private String harga;
	
	
	public HisokaBookClass()
	{
		
	}
	
	public HisokaBookClass(String jdl, String pngrng, String hrg)
	{
		super();
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
	
}
