package com.example.movie;

public class Cinema {
	private String name, address;
	private int price;
	Cinema() {}
	Cinema(String n, String a, int p) {
		name = n;
		address = a;
		price = p;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAddress(String add) {
		this.address = add;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public String getAddress() {
		return address;
	}
	public int getPrice() {
		return price;
	}
}