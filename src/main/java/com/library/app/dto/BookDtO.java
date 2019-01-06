package com.library.app.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

public class BookDtO {

	private int id;
	private String title;
	private String author;
	private Integer nrBooks;

	public BookDtO(int id, String title, String author, Integer nrBooks) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.nrBooks = nrBooks;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Integer getNrBooks() {
		return nrBooks;
	}

	public void setNrBooks(Integer nrBooks) {
		this.nrBooks = nrBooks;
	}

	@Override
	public String toString() {
		return "Book{" +
				"id=" + id +
				", title='" + title + '\'' +
				", author='" + author + '\'' +
				", nrBooks=" + nrBooks +
				'}';
	}
}
