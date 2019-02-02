package com.session.library.libraryofbooks.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="book")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "title", nullable = false)
	private String title;


	@Column(name = "author", nullable = false)
	private String author;


	@Column(name = "numbooks", nullable = false)
	private int numbooks;

	@Lob
	@Column(length=100000)
	private byte[] pdffile;

	@Column(name = "year", nullable = false)
	private int year;

	@Column(name = "nopages", nullable = false)
	private int nopages;

	@Column(name = "country", nullable = false)
	private String country;

	@Column(name = "summary", nullable = false)
	private String summary;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_book",
			joinColumns = { @JoinColumn(name = "book_id") },
			inverseJoinColumns = { @JoinColumn(name = "user_id") })
	private Set<User> users = new HashSet<>();

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getNopages() {
		return nopages;
	}

	public void setNopages(int nopages) {
		this.nopages = nopages;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

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

	public int getNumbooks() {
		return numbooks;
	}

	public void setNumbooks(int numbooks) {
		this.numbooks = numbooks;
	}
}
