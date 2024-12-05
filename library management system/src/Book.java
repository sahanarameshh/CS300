//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Book Class
// Course:   CS 300 Fall 2024
//
// Author:   Sahana Ramesh
// Email:    ramesh37@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons:         N/A
// Online Sources:  N/A
//
///////////////////////////////////////////////////////////////////////////////

/**
 * This class models a book with a certain title, author, year of publication, publisher, and number of pages
 */
public class Book {
	private String title;
	private String author;
	private int yearOfPublication;
	private String publisher;
	private int numberOfPages;
	
	/**
	 * Checks whether this winner equals a specific object
	 * 
	 * @param o an object
	 * @throws IllegalArgumentException if publication year or is negative
	 */
	public Book(String title, String author, int yearOfPublication, String publisher, int numberOfPages) {
		if (yearOfPublication < 0 || numberOfPages < 0 || yearOfPublication > 2024) {
			throw new IllegalArgumentException();
		}
		else {
			this.title = title;
			this.author = author;
			this.yearOfPublication = yearOfPublication;
			this.publisher = publisher;
			this.numberOfPages = numberOfPages;
		}
		
	}
	
	/**
	 * Gives the user the title of the book
	 * 
	 * @return the title of the book as a String
	 */
	public String getTitle() {
		return this.title;
	}
	
	/**
	 * Gives the user the author of the book
	 * 
	 * @return the author of the book as a String
	 */
	public String getAuthor() {
		return this.author;
	}
	
	/**
	 * Gives the user the year the book was published
	 * 
	 * @return the title of the book as an int
	 */
	public int getYearOfPublication() {
		return this.yearOfPublication;
	}
	
	/**
	 * Gives the user the publisher of the book
	 * 
	 * @return the publisher of the book as a String
	 */
	public String getPublisher() {
		return this.publisher;
	}
	
	/**
	 * Gives the number of pages the book has
	 * 
	 * @return the number of pages the book has as an int
	 */
	public int getNumberOfPages() {
		return this.numberOfPages;
	}
	
	/**
	 * Replaces the title of the book with one the user gives
	 * 
	 * @param title the title the user gives
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * Replaces the author of the book with one the user gives
	 * 
	 * @param author the author the user gives
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	
	/**
	 * Replaces the year the book was published with one the user gives
	 * 
	 * @param yearOfPublication the year of publication the user gives
	 */
	public void setYearOfPublication(int yearOfPublication) {
		if (yearOfPublication < 0 || yearOfPublication > 2024) {
			throw new IllegalArgumentException();
		}
		else {
			this.yearOfPublication = yearOfPublication;
		}
	}
	
	/**
	 * Replaces the publisher of the book with one the user gives
	 * 
	 * @param publisher the publisher the user gives
	 */
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	/**
	 * Replaces the number of pages the book has with one the user gives
	 * 
	 * @param numberOfPages the number of pages the user gives
	 */
	public void setNumberOfPages(int numberOfPages) {
		if (numberOfPages < 0) {
			throw new IllegalArgumentException();
		}
		else {
			this.numberOfPages = numberOfPages;
		}
	}
}
