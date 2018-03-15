package com.example.assignment;


public class Student {
	String ten,ma,gioitinh,tenlop,malop;
	int diem;
	int id;
	public Student(String ten, String ma,int diem, String gioitinh, String tenlop, String malop) {
		this.ten = ten;
		this.ma = ma;
		this.gioitinh = gioitinh;
		this.diem = diem;
		
	}
	public Student(int id,String ten, String ma,int diem, String gioitinh) {
		
		this.id=id;
		this.ten = ten;
		this.ma = ma;
		this.gioitinh = gioitinh;
		this.diem = diem;
	}

}
