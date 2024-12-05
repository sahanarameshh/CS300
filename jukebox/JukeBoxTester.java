import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Tester class for testing the functionality of the LinkedQueue, LinkedStack, Album, Song, and
 * Jukebox classes.
 */
public class JukeBoxTester {

  /**
   * Test the behavior of adding an element to the stack.
   * 
   * @return true if element is correctly added to the stack, false otherwise
   */
  public static boolean testStackAdd() {
    LinkedStack<String> s = new LinkedStack<>();
    
    s.push("hey");
    s.push("hi");
    s.push("hello");
    s.push("what's up");
    
    return s.peek().equals("what's up") && s.getList().size() == 4;
  }


  /**
   * Test the behavior of removing an element from the stack.
   * 
   * @return true if element is correctly removed from the stack, false otherwise
   */
  public static boolean testStackRemove() {
    LinkedStack<String> s = new LinkedStack<>();

    s.push("hey");
    s.push("hi");

    String str = s.pop();

    return s.peek().equals("hey") && s.getList().size() == 1;
  }

  /**
   * Test the behavior of adding an element to the queue.
   * 
   * @return true if element is correctly added to the queue, false otherwise
   */
  public static boolean testQueueAdd() {
    LinkedQueue<String> q = new LinkedQueue<>();

    q.enqueue("hey");
    q.enqueue("hi");
    q.enqueue("hello");
    q.enqueue("what's up");

    return q.peek().equals("hey") & q.getList().size() == 4;
  }

  /**
   * Test the behavior of removing an element from the queue.
   * 
   * @return true if element is correctly removed from the queue, false otherwise
   */
  public static boolean testQueueRemove() {
    LinkedQueue<String> q = new LinkedQueue<>();

    q.enqueue("hey");
    q.enqueue("hi");

    String str = q.dequeue();

    return q.peek().equals("hi") && q.getList().size() == 1;
  }

  /**
   * Test the behavior of peeking at the top element (for stack) and the front element (for queue).
   * 
   * @return true if the correct element returned for both data structures, false otherwise
   */
  public static boolean testPeek() {
    LinkedStack<String> s = new LinkedStack<>();

    s.push("hey");

    LinkedQueue<String> q = new LinkedQueue<>();

    q.enqueue("hi");

    String s1 = s.peek();
    String s2 = q.peek();
    
    return (s1.equals("hey") && s2.equals("hi")) && (s.getList().size() == 1 && q.getList().size() == 1);
  }

  /**
   * This method tests whether the contains method correctly identifies whether a specific element
   * exists in a stack and a queue.
   * 
   * @return true if the test passes, false otherwise
   */
  public static boolean testContains() {
    LinkedStack<String> s = new LinkedStack<>();

    s.push("hey");
    s.push("hi");

    LinkedQueue<String> q = new LinkedQueue<>();

    q.enqueue("hello");
    q.enqueue("what's up");

    return s.contains("hi") && q.contains("what's up");
  }

  /**
   * Test the behavior of getting the list of elements in the stack and queue.
   * 
   * @return true if method returns a correctly ordered list for both data structures, false
   *         otherwise
   */
  public static boolean testGetList() {
    LinkedStack<String> s = new LinkedStack<>();

    s.push("hey");
    s.push("hi");

    ArrayList<String> sElements = new ArrayList<>();

    sElements.add("hi");
    sElements.add("hey");

    LinkedQueue<String> q = new LinkedQueue<>();

    q.enqueue("hello");
    q.enqueue("what's up");

    ArrayList<String> qElements = new ArrayList<>();

    qElements.add("hello");
    qElements.add("what's up");

    return s.getList().equals(sElements) && q.getList().equals(qElements);
  }

  /**
   * Tests adding songs to an Album and verifies the size and content. Checks if songs are correctly
   * added in LIFO order.
   * 
   * @return true if it passes all test cases, false otherwise
   */
  public static boolean testAddSongToAlbum() {
    Album a2 = new Album("In Rainbows");
    Song s1 = new Song("Bodysnatchers", "Radiohead");
    Song s2 = new Song("Bodysnatchers", "Radiohead");
    Song s3 = new Song("Jigsaw Falling Into Place", "Radiohead");
    Song s4 = new Song("Nude", "Radiohead");

    a2.addSong(s1);

    if (a2.size() != 1) {
      System.out.println("1");
      return false;
    }

    if (!a2.firstSong().equals(s1)) {
      System.out.println("2");
      return false;
    }
    
    try {
      a2.addSong(s1);
      System.out.println("3");
      return false; 
    } catch (IllegalArgumentException e) {
      ;
    } catch (Exception e) {
      System.out.println("4");
      return false;
    }
/*
    a2.addSong(s3);
    a2.addSong(s4);
*/
    return true;
  }

  /**
   * Tests removing a song from an Album and verifies the size and content after removal. Checks if
   * songs are correctly removed in LIFO order.
   * 
   * @return true if it passes all test cases, false otherwise
   */
  public static boolean testRemoveSongFromAlbum() {
    Album a1 = new Album("CTRL");
    try {
      Song s3 = a1.removeSong();
      return false;
    } catch (NoSuchElementException e) {
      ;
    }

    Album a2 = new Album("In Rainbows");
    Song s1 = new Song("Bodysnatchers", "Radiohead");
    Song s2 = new Song("Jigsaw Falling Into Place", "Radiohead");

    a2.addSong(s1);
    a2.addSong(s2);

    Song s4 = a2.removeSong();

    return a2.size() == 1 && a2.firstSong().equals(s1);
  }

  /**
   * Tests the toString method of the Album class. Verifies that the returned string correctly
   * represents all songs in LIFO order.
   * 
   * @return true if it passes all test cases, false otherwise
   */
  public static boolean testAlbumToString() {
    Album a = new Album("In Rainbows");
    Song s1 = new Song("Bodysnatchers", "Radiohead");
    Song s2 = new Song("Jigsaw Falling Into Place", "Radiohead");

    a.addSong(s1);
    a.addSong(s2);

    String s = "In Rainbows" + "\n" + s2.toString() + "\n" + s1.toString();

    return a.toString().equals(s);
  }

  /**
   * Tests adding a song to the Jukebox and verifies the queue contents and size.
   * 
   * @return true if it passes all test cases, false otherwise
   */
  public static boolean testAddSongToJukebox() {
    JukeBox j = new JukeBox(3);
    Song s1 = new Song("Bodysnatchers", "Radiohead");
    Song s2 = new Song("Jigsaw Falling Into Place", "Radiohead");
    Song s3 = new Song("Jigsaw Falling Into Place", "Radiohead");
    Song s4 = new Song("Nude", "Radiohead");
    Song s5 = new Song("Weird Fishes/Arpeggi", "Radiohead");
    
    j.addSongToQueue(s1);
    j.addSongToQueue(s2);
    
    try {
      j.addSongToQueue(s3);
      return false;
    } catch (IllegalArgumentException e) {
      ;
    }

    j.addSongToQueue(s4);

    try {
      j.addSongToQueue(s5);
      return false;
    } catch (IllegalStateException e) {
      ;
    }

    String str =  s1.toString() + " -> " + s2.toString() + " -> " + s4.toString() + " -> END";
    
    return j.size() == 3 && j.toString().equals(str);
  }

  /**
   * Tests adding an album to the Jukebox and verifies the queue contents and size.
   * 
   * @return true if it passes all test cases, false otherwise
   */
  public static boolean testAddAlbumToJukebox() {
    JukeBox j = new JukeBox(2);
    Album a = new Album("In Rainbows");
    Song s1 = new Song("Bodysnatchers", "Radiohead");
    Song s2 = new Song("Jigsaw Falling Into Place", "Radiohead");
    Song s3 = new Song("Nude", "Radiohead");
    
    a.addSong(s1);
    a.addSong(s2);
    a.addSong(s3);

    if (a.size() != 3) {
      return false;
    }

    j.addAlbumToQueue(a);

    if (a.size() != 1) {
      return false;
    }

    String str =  s3.toString() + " -> " + s2.toString() + " -> END";

    //System.out.println(j.toString());
    //System.out.println(str);

    if (j.size() != 2) {
      System.out.println("1");
      return false;
    }
    if (!j.toString().equals(str)) {
      System.out.println("2");
      return false;
    }
    return true;
  }

  /**
   * Tests playing a song from the JukeboxQueue. Verifies that the song is removed from the queue
   * after playback.
   * 
   * @return true if it passes all test cases, false otherwise
   */
  public static boolean testPlaySongFromJukebox() {
    JukeBox j1 = new JukeBox(3);
    Album a1 = new Album("CTRL");
    j1.addAlbumToQueue(a1);

    try {
      Song s3 = j1.playSong();
      return false;
    } catch (NoSuchElementException e) {
      ;
    }

    JukeBox j2 = new JukeBox(3);
    Album a2 = new Album("In Rainbows");
    Song s1 = new Song("Bodysnatchers", "Radiohead");
    Song s2 = new Song("Jigsaw Falling Into Place", "Radiohead");

    a2.addSong(s1);
    a2.addSong(s2);

    if (a2.size() != 2) {
      System.out.println("3");
      return false;
    }

    j2.addAlbumToQueue(a2);

    Song s4 = j2.playSong();

    String str = s1.toString() + " -> END";

    System.out.println("size: " + j2.size());
    if (j2.size() != 1) {
      System.out.println("1");
      return false;
    }
    if (!j2.toString().equals(str)) {
      System.out.println("2");
      return false;
    }
    return true;
  }

  /**
   * Tests shuffling the JukeBox queue. Verifies that the songs are reordered randomly after the
   * operation.
   * 
   * @return true if it passes all test cases, false otherwise
   */
  public static boolean testJukeboxShuffle() {
    JukeBox j = new JukeBox(4);
    Album a = new Album("In Rainbows");
    Song s1 = new Song("Bodysnatchers", "Radiohead");
    Song s2 = new Song("Jigsaw Falling Into Place", "Radiohead");
    Song s3 = new Song("Nude", "Radiohead");
    Song s4 = new Song("Weird Fishes/Arpeggi", "Radiohead");

    a.addSong(s1);
    a.addSong(s2);
    a.addSong(s3);
    a.addSong(s4);
    j.addAlbumToQueue(a);

    String str1 = j.toString();
    j.shuffleSongQueue();
    String str2 = j.toString();

    return !str1.equals(str2);
  }

  public static void main(String[] args) {
    boolean test1 = testStackAdd();
    System.out.println("testStackAdd: " + (test1 ? "PASS" : "FAIL"));

    boolean test2 = testStackRemove();
    System.out.println("testStackRemove: " + (test2 ? "PASS" : "FAIL"));

    boolean test3 = testQueueAdd();
    System.out.println("testQueueAdd: " + (test3 ? "PASS" : "FAIL"));

    boolean test4 = testQueueRemove();
    System.out.println("testQueueRemove: " + (test4 ? "PASS" : "FAIL"));

    boolean test5 = testPeek();
    System.out.println("testPeek: " + (test5 ? "PASS" : "FAIL"));

    boolean test6 = testContains();
    System.out.println("testContains: " + (test6 ? "PASS" : "FAIL"));

    boolean test7 = testGetList();
    System.out.println("testGetList: " + (test7 ? "PASS" : "FAIL"));

    boolean test8 = testAddSongToAlbum();
    System.out.println("testAddSongToAlbum: " + (test8 ? "PASS" : "FAIL"));

    boolean test9 = testRemoveSongFromAlbum();
    System.out.println("testRemoveSongFromAlbum: " + (test9 ? "PASS" : "FAIL"));

    boolean test10 = testAlbumToString();
    System.out.println("testAlbumToString: " + (test10 ? "PASS" : "FAIL"));

    boolean test11 = testAddSongToJukebox();
    System.out.println("testAddSongToJukebox: " + (test11 ? "PASS" : "FAIL"));

    boolean test12 = testAddAlbumToJukebox();
    System.out.println("testAddAlbumToJukebox: " + (test12 ? "PASS" : "FAIL"));

    boolean test13 = testPlaySongFromJukebox();
    System.out.println("testPlaySongFromJukebox: " + (test13 ? "PASS" : "FAIL"));

    boolean test14 = testJukeboxShuffle();
    System.out.println("testJukeboxShuffle: " + (test14 ? "PASS" : "FAIL"));

    System.out.println("ALL TESTS: " + (test1 && test2 && test3 && test4 && test5 && test6 && test7
        && test8 && test9 && test10 && test11 && test12 && test13 && test14 ? "PASS" : "FAIL"));
  }
}
