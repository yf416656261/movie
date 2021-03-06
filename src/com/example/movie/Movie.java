package com.example.movie;

public class Movie {
    private String title;
	private String comment;
	private int ID;
	private String imageID;
	public Movie() {}
	public Movie(String title, String comment, int ID, String imageID) {
		this.title = title;
		this.comment = comment;
		this.ID = ID;
		this.imageID = imageID;
	}
	public void setName(String title) {
		this.title = title;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public void setID(int ID) {
		this.ID = ID;
	}
	public void setImageID(String imageID) {
		this.imageID = imageID;
	}
	public String getTitle() {
		return this.title;
	}
	public String getComment() {
		return this.comment;
	}
	public int getID() {
		return this.ID;
	}
	public String getImageID() {
		return this.imageID;
	}
}