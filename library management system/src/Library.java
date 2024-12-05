//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Library Class
// Course:   CS 300 Fall 2024
//
// Author:   Sahana Ramesh
// Email:    ramesh37@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons:         Nishka Shah helped with pseudocode for addBook method
// Online Sources:  N/A
//
///////////////////////////////////////////////////////////////////////////////
import java.util.ArrayList;
/**
 * This class models a library containing books and their information
 */
public class Library {
	private ArrayList<Book> books;
	public Library() {
		books = new ArrayList<Book>();
	}
	
	/**
	 * method for adding a book the user gives to the library in acending order of publication date
   	 * 
   	 * @param book the book the user gives
   	 */
	public void addBook(Book book) {
		int index = books.size();
		for (int i = 0; i < books.size(); i++) {
			if (book.getYearOfPublication() < books.get(i).getYearOfPublication()) {
				index = i;
			}
		}
		books.add(index, book);
	}
	
	/**
	 * method for finding a book the user gives in the library by its author
   	 * 
   	 * @param author the author the user gives
	 * @return an ArrayList of Book objects with the author the user gives
   	 */
	public ArrayList<Book> findBooksByAuthor(String author) {
		ArrayList<Book> authorsBooks = new ArrayList<>();
		for (Book book : books) {
			if (book.getAuthor().equalsIgnoreCase(author)) {
				authorsBooks.add(book);
			}
		}
		return authorsBooks;
	}
	
	/**
	 * method for giving the user all the Books in the library
   	 * 
	 * @return an ArrayList of all the Book objects
   	 */
	public ArrayList<Book> getAllBooks() {
		return books;
	}
	
	/**
	 * method for giving the user the size of the library
   	 * 
	 * @return an the size of the library
   	 */
	public int getTotalBooks() {
		return books.size();
	}
	
	/**
	 * method for printing the title and author of all the Books in the library
   	 */
	public void printAllBooks() {
		for (Book book : books) {
			System.out.println("Title: " + book.getTitle() + ", Author: " + book.getAuthor());
		}
	}
	
	/**
	 * method for removing a book the user gives by its title
   	 * 
   	 * @param title the title the user gives
	 * @return true if the book is removed
   	 */
	public boolean removeBookByTitle(String title) {
		for (Book book : books) {
			if (book.getTitle().equalsIgnoreCase(title)) {
				books.remove(book);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * method for replacing the author of the book by a title given by the user
   	 * 
   	 * @param title the title the user wants to replace
	 * @param newAuthor the author the user gives
	 * @return true if the author is replaced
   	 */
	public boolean updateBookAuthor(String title, String newAuthor) {
		for (Book book : books) {
			if (book.getTitle().equalsIgnoreCase(title)) {
				book.setAuthor(newAuthor);
				return true;
			}
		}
		return false;
	}

	/**
	 * method for replacing the title of the book with a new title given by the user
   	 * 
   	 * @param oldTitle the title the user wants to replace
	 * @param newTitle the title the user gives
	 * @return true if the title is replaced
   	 */
	public boolean updateBookTitle(String oldTitle, String newTitle) {
		for (Book book : books) {
			if (book.getTitle().equalsIgnoreCase(oldTitle)) {
				book.setTitle(newTitle);
				return true;
			}
		}
		return false;
	}
}
