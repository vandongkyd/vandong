package com.example.assignment;

import java.util.ArrayList;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabase extends SQLiteOpenHelper {

	public MyDatabase(Context context) {
		super(context, "qlsv", null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String sql = "create table user ("
				+ "_id integer primary key autoincrement, uname text, pword text"
				+ ")";
		String strlop = "create table lop ("
				+ "_id integer primary key autoincrement, tenlop text, malop text"
				+ ")";
		String strsv = "create table student ("
				+ "_id integer primary key autoincrement, tenlop text, malop text, masv text, "
				+ "tensv text, diem integer, gioitinh text"
				+ ")";
		
		
		db.execSQL(sql);
		db.execSQL(strlop);
		db.execSQL(strsv);
		//thembangsinhvien();
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("drop table if exists user");
		onCreate(db);
		
	}
	public void thembanglop()
	{
		SQLiteDatabase db = getWritableDatabase();
		String themlop = "create table lop ("
				+ "_id integer primary key autoincrement, tenlop text, malop text"
				+ ")";
		db.execSQL(themlop);
	}
	public void thembangsinhvien()
	{
		SQLiteDatabase db = getWritableDatabase();
		String themlop = "create table student ("
				+ "_id integer primary key autoincrement, tensv text, masv text, gioitinh text, diem integer, tenlopa text, "
				+ "malopa text"
				+ ")";
		db.execSQL(themlop);
	}
	public ArrayList<String> layuser()
	{
		ArrayList<String> list = new ArrayList<String>();
		SQLiteDatabase db = getReadableDatabase();
		Cursor contro = db.rawQuery("select*from user", null);
		if(contro.moveToFirst())
		{
			do
			{
				String uname = contro.getString(1);
				String pword = contro.getString(2);
				list.add(uname);
				list.add(pword);
			}while(contro.moveToNext());
		}
		return list;
	}

	void Dialog(lop lp)
	{
		SQLiteDatabase db = getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("tenlop", lp.tenlop);
		values.put("malop", lp.malop);
		db.insert("lop", null, values);
	}
	public ArrayList<lop> laylop()
	{
		ArrayList<lop> list = new ArrayList<lop>();
		SQLiteDatabase db = getReadableDatabase();
		Cursor contro = db.rawQuery("select*from lop", null);
		if(contro.moveToFirst())
		{
			do
			{
				int id = contro.getInt(0);
				String tenlop = contro.getString(1);
				String malop = contro.getString(2);
				lop lp = new lop(id,tenlop, malop);
				list.add(lp);
			}while(contro.moveToNext());
		}
		return list;
	}
	public ArrayList<String> laylop2()
	{
		ArrayList<String> list = new ArrayList<String>();
		SQLiteDatabase db = getReadableDatabase();
		Cursor contro = db.rawQuery("select * from lop", null);
		if(contro.moveToFirst())
		{
			do
			{
				String tenlop = contro.getString(1);
				list.add(tenlop);
			}while(contro.moveToNext());
		}
		return list;
	}
	public void Dialogstudent(Student sv)
	{
		SQLiteDatabase db = getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("tenlop", sv.tenlop);
		values.put("malop", sv.malop);
		values.put("tensv", sv.ten);
		values.put("masv", sv.ma);
		values.put("gioitinh", sv.gioitinh);
		values.put("diem", sv.diem);
		db.insert("student", null, values);
	}
	public ArrayList<Student> laysinhvien()
	{
		ArrayList<Student> list = new ArrayList<Student>();
		SQLiteDatabase db = getReadableDatabase();
		Cursor contro = db.rawQuery("select * from student", null);
		if(contro.moveToFirst())
		{
			do{
			String tensv = contro.getString(4);
			String masv = contro.getString(3);
			String gioitinh = contro.getString(6);
			int diem = contro.getInt(5);
			int id = contro.getInt(0);
			Student st = new Student(id,tensv, masv, diem, gioitinh);
			list.add(st);
			}while(contro.moveToNext());
		}
		return list;
	}
	public void XoaLop(int id){
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete("lop", "_id=?", new String[]{id+""});
		
	}
	public void XoaStudent(int id){
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete("student", "_id=?", new String[]{id+""});
	}
	public void SuaLop(lop lp){
		SQLiteDatabase  db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("tenlop", lp.tenlop);
		values.put("malop", lp.malop);
		db.update("lop", values,"_id=?", new String[]{lp._id+""} );
	}
	public void SuaSv(Student sv){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("tensv", sv.ten);
		values.put("masv", sv.ma);
		values.put("diem", sv.diem);
		values.put("gioitinh", sv.gioitinh);
		values.put("tenlop", sv.tenlop);
		values.put("malop", sv.malop);
		db.update("student", values , "_id=?", new String[]{sv.id+""});
	}
}