//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Library Class Tester
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
import java.util.ArrayList;

/**
 * Tests methods of Book and Library classes.
 */
public class LibraryTester {
  /**
   * PROVIDED TESTER METHOD: example test method for testing the getTitle method.
   *
   * @return true if the test passes, false otherwise
   */
  public static boolean testGetTitle() {
    Book book = new Book("1984", "George Orwell", 1949, "Secker & Warburg", 328);
    return "1984".equals(book.getTitle());
  }

  /**
   * PROVIDED TESTER METHOD: example test method for testing the setTitle method.
   *
   * @return true if the test passes, false otherwise
   */
  public static boolean testSetTitle() {
    Book book = new Book("1984", "George Orwell", 1949, "Secker & Warburg", 328);
    book.setTitle("Animal Farm");
    return "Animal Farm".equals(book.getTitle());
  }

  /**
   * method for testing the getAuthor method
   *
   * @return true if the test passes, false otherwise
   */
  public static boolean testGetAuthor() {
	  Book book = new Book("1984", "George Orwell", 1949, "Secker & Warburg", 328);
	  return "George Orwell".equals(book.getAuthor());
  }

  /**
   * method for testing the setAuthor method
   *
   * @return true if the test passes, false otherwise
   */
  public static boolean testSetAuthor() {
	  Book book = new Book("1984", "George Orwell", 1949, "Secker & Warburg", 328);
	  book.setAuthor("Harry Potter");
	  return "Harry Potter".equals(book.getAuthor());
  }

  /**
   * method for testing the getYearOfPublication method
   *
   * @return true if the test passes, false otherwise
   */
  public static boolean testGetYearOfPublication() {
	  Book book = new Book("1984", "George Orwell", 1949, "Secker & Warburg", 328);
	  return 1949 == book.getYearOfPublication();
  }

  /**
   * method for testing the setYearOfPublication method
   *
   * @return true if the test passes, false otherwise
   */
  public static boolean testSetYearOfPublication() {
	  Book book = new Book("1984", "George Orwell", 1949, "Secker & Warburg", 328);
	  book.setYearOfPublication(2024);
	  return 2024 == book.getYearOfPublication();
  }

  /**
   * method for testing the getPublisher method
   *
   * @return true if the test passes, false otherwise
   */
  public static boolean testGetPublisher() {
	  Book book = new Book("1984", "George Orwell", 1949, "Secker & Warburg", 328);
	  return "Secker & Warburg".equals(book.getPublisher());
  }

  /**
   * method for testing the setPublisher method
   *
   * @return true if the test passes, false otherwise
   */
  public static boolean testSetPublisher() {
	  Book book = new Book("1984", "George Orwell", 1949, "Secker & Warburg", 328);
	  book.setPublisher("Harry Potter");
	  return "Harry Potter".equals(book.getPublisher());
  }

  /**
   * method for testing the getNumberOfPages method
   *
   * @return true if the test passes, false otherwise
   */
  public static boolean testGetNumberOfPages() {
	  Book book = new Book("1984", "George Orwell", 1949, "Secker & Warburg", 328);
	  return 328 == book.getNumberOfPages();
  }

  /**
   * method for testing the setNumberOfPages method
   *
   * @return true if the test passes, false otherwise
   */
  public static boolean testSetNumberOfPages() {
	  Book book = new Book("1984", "George Orwell", 1949, "Secker & Warburg", 328);
	  book.setNumberOfPages(100);
	  return 100 == book.getNumberOfPages();
  }

  /**
   * PROVIDED TESTER METHOD: Retrieves the total number of books in the library.
   * 
   * @return the total number of books
   */
  public static boolean testGetTotalBooks() {
    Library library = new Library();
    library.addBook(new Book("Book 1", "Author A", 2023, "Publisher Y", 200));
    library.addBook(new Book("Book 2", "Author B", 2023, "Publisher Z", 300));

    int expected = 2;
    int result = library.getTotalBooks();

    ArrayList<Book> expectedA = new ArrayList<>();
    expectedA.add(new Book("Book 1", "Author A", 2023, "Publisher Y", 200));
    expectedA.add(new Book("Book 2", "Author B", 2023, "Publisher Z", 300));

    if (expected != result) {
      return false;
    }
    return compareBooks(expectedA, library.getAllBooks());
  }


  /**
   * PROVIDED TESTER METHOD: example test method for adding a single book to the library.
   * 
   * @return true if the test passes, false otherwise
   */
  public static boolean testAddBook() {
    Library library = new Library();
    Book book = new Book("Test Book", "Test Author", 2023, "Publisher X", 100);
    library.addBook(book);

    ArrayList<Book> expected = new ArrayList<>();
    expected.add(new Book("Test Book", "Test Author", 2023, "Publisher X", 100));
    return compareBooks(expected, library.getAllBooks());
  }

  /**
   * method for adding multiple books to the library
   * 
   * @return true if the test passes, false otherwise
   */
  public static boolean testAddMultipleBooks() {
	Library library = new Library();
	library.addBook(new Book("Test Book 1", "Test Author 1", 2021, "Publisher X1", 100));
	library.addBook(new Book("Test Book 2", "Test Author 2", 2022, "Publisher X2", 200));
	library.addBook(new Book("Test Book 3", "Test Author 3", 2023, "Publisher X3", 300));

	ArrayList<Book> expected = new ArrayList<>();
	expected.add(new Book("Test Book 1", "Test Author 1", 2021, "Publisher X1", 100));
	expected.add(new Book("Test Book 2", "Test Author 2", 2022, "Publisher X2", 200));	
	expected.add(new Book("Test Book 3", "Test Author 3", 2023, "Publisher X3", 300));
	  
	// checking resulted library
	if (!compareBooks(expected, library.getAllBooks())) {
		return false;
	}
	  
	return true;
  }

  /**
   * PROVIDED TESTER METHOD: example test method for removing a book by title from the library.
   * 
   * @return true if the test passes, false otherwise
   */
  public static boolean testRemoveBookByTitle() {
	  Library library = new Library();
	  library.addBook(new Book("Test Book", "Test Author", 2023, "Publisher X", 100));
	  boolean result = library.removeBookByTitle("Test Book");

	  // checking result from removeBookByTitle("Test Book")
	  if (result != true) {
		  return false;
	  }
	  
	  // checking resulted number of books
	  if (library.getTotalBooks() != 0) {
		  return false;
	  }
	  
	  ArrayList<Book> expected = new ArrayList<>();
	  
	  // checking resulted library
	  if (!compareBooks(expected, library.getAllBooks())) {
		  return false;
	  }
	  
	  return true;
  }

  /**
   * method for removing multiple books from the library
   * 
   * @return true if the test passes, false otherwise
   */
  public static boolean testRemoveOneOfManyBooks() {
	  Library library = new Library();
	  library.addBook(new Book("Test Book 1", "Test Author 1", 2021, "Publisher X1", 100));
	  library.addBook(new Book("Test Book 2", "Test Author 2", 2022, "Publisher X2", 200));
	  library.addBook(new Book("Test Book 3", "Test Author 3", 2023, "Publisher X3", 300));
	  
	  boolean result = library.removeBookByTitle("Test Book 2");
	
	  // checking result from removeBookByTitle("Test Book")
	  if (result != true) {
	      return false;
	  }
	  // checking resulted number of books
	  if (library.getTotalBooks() != 2) {
	    return false;
	  }
	  
	  ArrayList<Book> expected = new ArrayList<>();
	  expected.add(new Book("Test Book 1", "Test Author 1", 2021, "Publisher X1", 100));
	  expected.add(new Book("Test Book 3", "Test Author 3", 2023, "Publisher X3", 300));
	  
	  // checking resulted library
	  if (!compareBooks(expected, library.getAllBooks())) {
	    return false;
	  }
	  
	  return true;
  }

  /**
   * method for removing a non existent book from the library
   * 
   * @return true if the test passes, false otherwise
   */
  public static boolean testRemoveNonExistentBook() {
	Library library = new Library();
	library.addBook(new Book("Test Book", "Test Author", 2023, "Publisher X", 100));
	boolean result = library.removeBookByTitle("Test Book");

	// checking result from removeBookByTitle("Test Book")
	if (result != true) {
		return false;
	}

	ArrayList<Book> expected = new ArrayList<>();

	// checking resulted library
	if (!compareBooks(expected, library.getAllBooks())) {
	    return false;
	}
	return true;
}

  /**
   * method for finding a single book in the library by its author
   * 
   * @return true if the test passes, false otherwise
   */
  public static boolean testFindBooksByAuthor() {
	  Library library = new Library();
	  library.addBook(new Book("Test Book", "Test Author", 2023, "Publisher X", 100));
	  
	  ArrayList<Book> expected = new ArrayList<>();
	  expected.add(new Book("Test Book", "Test Author",  2023, "Publisher X", 100));
	  
	  // checking resulted library
	  if (!compareBooks(expected, library.findBooksByAuthor("Test Author"))) {
	    return false;
	  }
	  
	  return true;

  }

  /**
   * method for finding multiple books in the library by their author
   * 
   * @return true if the test passes, false otherwise
   */
  public static boolean testFindBooksByMultipleAuthors() {
	  Library library = new Library();
	  library.addBook(new Book("Test Book 1", "Test Author 1", 2021, "Publisher X1", 100));
	  library.addBook(new Book("Test Book 2", "Test Author 2", 2022, "Publisher X2", 200));
	  library.addBook(new Book("Test Book 3", "Test Author 1", 2023, "Publisher X3", 300));
	  
	  ArrayList<Book> expected = new ArrayList<>();
	  expected.add(new Book("Test Book 1", "Test Author 1", 2021, "Publisher X1", 100));
	  expected.add(new Book("Test Book 3", "Test Author 1", 2023, "Publisher X3", 300));
	  
	  // checking resulted library
	  if (!compareBooks(expected, library.findBooksByAuthor("Test Author 1"))) {
	    return false;
	  }
	  
	  return true;
  }

/**
   * method for updating a single book's title in the library
   * 
   * @return true if the test passes, false otherwise
   */
  public static boolean testUpdateBookTitle() {
	  Library library = new Library();
	  library.addBook(new Book("Test Book", "Test Author", 2023, "Publisher X", 100));
	  
	  boolean result = library.updateBookTitle("Test Book", "Test Book 2");

	  // checking result from removeBookByTitle("Test Book")
	  if (result != true) {
	      return false;
	  }

	  library.updateBookTitle("Test Book", "Test Book 2");
	  
	  ArrayList<Book> expected = new ArrayList<>();
	  expected.add(new Book("Test Book 2", "Test Author", 2023, "Publisher X", 100));
	  
	  // checking resulted library
	  if (!compareBooks(expected, library.getAllBooks())) {
	    return false;
	  }

	  return true;
  }

  /**
   * method for updating multiple books' titles in the library
   * 
   * @return true if the test passes, false otherwise
   */
  public static boolean testUpdateMultipleBookTitles() {
	  Library library = new Library();
	  library.addBook(new Book("Test Book 1", "Test Author 1", 2021, "Publisher X1", 100));
	  library.addBook(new Book("Test Book 2", "Test Author 2", 2022, "Publisher X2", 200));
	  library.addBook(new Book("Test Book 3", "Test Author 3", 2023, "Publisher X3", 300));
	  
	  boolean result = library.updateBookTitle("Test Book 2", "Test Book 4");
		
	  // checking result from removeBookByTitle("Test Book")
	  if (result != true) {
	      return false;
	  }
	  
	  result = library.updateBookTitle("Test Book 3", "Test Book 5");

	  // checking result from removeBookByTitle("Test Book")
	  if (result != true) {
	      return false;
	  }

	  library.updateBookTitle("Test Book 2", "Test Book 4");
	  library.updateBookTitle("Test Book 3", "Test Book 5");

	  ArrayList<Book> expected = new ArrayList<>();
	  expected.add(new Book("Test Book 1", "Test Author 1", 2021, "Publisher X1", 100));
	  expected.add(new Book("Test Book 4", "Test Author 2", 2022, "Publisher X2", 200));
	  expected.add(new Book("Test Book 5", "Test Author 3", 2023, "Publisher X3", 300));

	  // checking resulted library
	  if (!compareBooks(expected, library.getAllBooks())) {
	    return false;
	  }

	  return true;
  }

  /**
   * method for updating a single book's author in the library
   * 
   * @return true if the test passes, false otherwise
   */
  public static boolean testUpdateBookAuthor() {
	  Library library = new Library();
	  library.addBook(new Book("Test Book", "Test Author", 2023, "Publisher X", 100));
	  
	  boolean result = library.updateBookAuthor("Test Book", "Test Author 2");
		
	  // checking result from removeBookByTitle("Test Book")
	  if (result != true) {
	      return false;
	  }
	  
	  library.updateBookAuthor("Test Book", "Test Author 2");

	  ArrayList<Book> expected = new ArrayList<>();
	  expected.add(new Book("Test Book", "Test Author 2", 2023, "Publisher X", 100));

	  // checking resulted library
	  if (!compareBooks(expected, library.getAllBooks())) {
	    return false;
	  }

	  
	  return true;
  }

  /**
   * method for updating multiple books's authors in the library
   * 
   * @return true if the test passes, false otherwise
   */
  public static boolean testUpdateMultipleBookAuthors() {
	  Library library = new Library();
	  library.addBook(new Book("Test Book 1", "Test Author 1", 2021, "Publisher X1", 100));
	  library.addBook(new Book("Test Book 2", "Test Author 2", 2022, "Publisher X2", 200));
	  library.addBook(new Book("Test Book 3", "Test Author 3", 2023, "Publisher X3", 300));
	  
	  boolean result = library.updateBookAuthor("Test Book 2", "Test Author 4");
		
	  // checking result from removeBookByTitle("Test Book")
	  if (result != true) {
	      return false;
	  }
	  
	  result = library.updateBookAuthor("Test Book 3", "Test Author 5");
		
	  // checking result from removeBookByTitle("Test Book")
	  if (result != true) {
	      return false;
	  }

	  library.updateBookAuthor("Test Book 2", "Test Author 4");
	  library.updateBookAuthor("Test Book 3", "Test Author 5");

	  ArrayList<Book> expected = new ArrayList<>();
	  expected.add(new Book("Test Book 1", "Test Author 1", 2021, "Publisher X1", 100));
	  expected.add(new Book("Test Book 2", "Test Author 4", 2022, "Publisher X2", 200));
	  expected.add(new Book("Test Book 3", "Test Author 5", 2023, "Publisher X3", 300));

	  // checking resulted library
	  if (!compareBooks(expected, library.getAllBooks())) {
	    return false;
	  }
	  return true;
  }

  /**
   * Compares two lists of books for equality.
   * 
   * @param expected the expected list of books
   * @param result   the list of books to compare
   * @return true if both lists contain the same books, false otherwise
   */
  private static boolean compareBooks(ArrayList<Book> expected, ArrayList<Book> result) {
    if (expected.size() != result.size()) {
      return false;
    }
    for (int i = 0; i < expected.size(); i++) {
      Book expectedBook = expected.get(i);
      Book resultBook = result.get(i);
      if (!expectedBook.getTitle().equals(resultBook.getTitle())
          || !expectedBook.getAuthor().equals(resultBook.getAuthor())
          || !(expectedBook.getPublisher().equals(resultBook.getPublisher()))
          || !(expectedBook.getNumberOfPages() == resultBook.getNumberOfPages())
          || !(expectedBook.getYearOfPublication() == resultBook.getYearOfPublication())) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    // test two functions in book.class
    System.out.println("Test getTitle: " + testGetTitle());
    System.out.println("Test setTitle: " + testSetTitle());
    System.out.println("Test getAuthor: " + testGetAuthor());
    System.out.println("Test setAuthor: " + testSetAuthor());
    System.out.println("Test getYearOfPublication: " + testGetYearOfPublication());
    System.out.println("Test setYearOfPublication: " + testSetYearOfPublication());
    System.out.println("Test getPublisher: " + testGetPublisher());
    System.out.println("Test setPublisher: " + testSetPublisher());
    System.out.println("Test getNumberOfPages: " + testGetNumberOfPages());
    System.out.println("Test setNumberOfPages: " + testSetNumberOfPages());
    System.out.println("Test addBook: " + testAddBook());
    System.out.println("Test addMultipleBooks: " + testAddMultipleBooks());
    System.out.println("Test removeBookByTitle: " + testRemoveBookByTitle());
    System.out.println("Test removeOneOfManyBooks: " + testRemoveOneOfManyBooks());
	System.out.println("Test removeNonExistentBook: " + testRemoveNonExistentBook());
    System.out.println("Test findBooksByAuthor: " + testFindBooksByAuthor());
    System.out.println("Test findBooksByMultipleAuthors: " + testFindBooksByMultipleAuthors());
    System.out.println("Test updateBookTitle: " + testUpdateBookTitle());
    System.out.println("Test updateMultipleBookTitles: " + testUpdateMultipleBookTitles());
    System.out.println("Test updateBookAuthor: " + testUpdateBookAuthor());
    System.out.println("Test updateMultipleBookAuthors: " + testUpdateMultipleBookAuthors());
  }
}
