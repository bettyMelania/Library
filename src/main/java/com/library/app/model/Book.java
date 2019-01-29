package com.library.app.model;

import javax.persistence.*;

@Entity
@Table(name="book")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "title", nullable = false)
	private String title;


	@Column(name = "author", nullable = false)
	private String author;


	@Column(name = "nrBooks", nullable = false)
	private Integer nrBooks;

	@Lob
	@Column(length=100000)
	private byte[] pdffile;

	public byte[] getPdffile() {
		return pdffile;
	}

	public void setPdffile(byte[] pdffile) {
		this.pdffile = pdffile;
	}

	public void setId(Integer id) {
		this.id = id;
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
