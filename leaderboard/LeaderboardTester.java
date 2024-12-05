//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Leaderboard Tester
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
/**
 * This class tests the various methods within the Player and Leaderboard classes
 */
public class LeaderboardTester {
  
  /////////////////////////////////////////// COMPARE TO ///////////////////////////////////////////
  /**
   * Tester method for the compareTo method in the Player class,
   * tests with inputs of players with different scores, players with 
   * the same scores but different names, and players with the same
   * name and score.
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean testPlayerCompareTo() {
    boolean test1 = testCompareToDiffScore();
    boolean test2 = testCompareToSameScoreDiffName();
    boolean test3 = testCompareToEqual();
    if (!test1) System.out.print("diffScore FAIL ");
    if (!test2) System.out.print("diffName FAIL ");
    if (!test3) System.out.print("equals FAIL ");
    return test1 && test2 && test3;
  }
  
  /**
   * Tester method for the compareTo method in the Player class,
   * tests with input of players with different scores.
   * 
   * @return true if result is equal to expected value
   */
  private static boolean testCompareToDiffScore() {
    Player p1 = new Player("Harry");
    Player p2 = new Player("Ron");

    p1.setScore(5);
    p2.setScore(10);

    int result = p1.compareTo(p2);

    return result < 0;
  }
  
  /**
   * Tester method for the compareTo method in the Player class,
   * tests with input of players with the same scores but different 
   * names.
   * 
   * @return true if result is equal to expected value
   */
  private static boolean testCompareToSameScoreDiffName() {
    Player p1 = new Player("Ron");
    Player p2 = new Player("Harry");
    
    p1.setScore(10);
    p2.setScore(10);
    
    int result = p1.compareTo(p2);
    
    return result > 0;
  }
  
  /**
   * Tester method for the compareTo method in the Player class,
   * tests with input of players with the same name and score.
   * 
   * @return true if result is equal to expected value
   */
  private static boolean testCompareToEqual() {
    Player p1 = new Player("Hermione");
    Player p2 = new Player("Hermione");
    
    p1.setScore(10);
    p2.setScore(10);
    
    int result = p1.compareTo(p2);
    
    return result == 0;
  }
  
  ///////////////////////////////////////// LOOKUP: NAME /////////////////////////////////////////
  /**
   * Tester method for the lookup method in the Leaderboard class,
   * tests with inputs of the root player, the player to the left 
   * of the root, the player to the right of the root, and a player
   * that isn't present in the tree.
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean testNameLookup() {
    boolean test1 = testLookupRoot();
    boolean test2 = testLookupLeft();
    boolean test3 = testLookupRight();
    boolean test4 = testLookupNotPresent();
    if (!test1) System.out.print("lookupRoot FAIL ");
    if (!test2) System.out.print("lookupLeft FAIL ");
    if (!test3) System.out.print("lookupRight FAIL ");
    if (!test4) System.out.print("lookupNotPresent FAIL");
    return test1 && test2 && test3 && test4;
  }
  
  /**
   * Tester method for the lookup method in the Leaderboard class,
   * tests with inputs of the root player.
   * 
   * @return true if result is equal to expected value
   */
  private static boolean testLookupRoot() {
    Leaderboard l = new Leaderboard();
    Player rootP = new Player("Harry", 10);

    l.addPlayer(rootP);

    return l.lookup("Harry") == rootP;
  }
  
  /**
   * Tester method for the lookup method in the Leaderboard class,
   * tests with inputs of the player to the left of the root.
   * 
   * @return true if result is equal to expected value
   */
  private static boolean testLookupLeft() {
    Leaderboard l = new Leaderboard();
    Player rootP = new Player("Harry", 10);
    Player leftP = new Player("Ron", 9);
    
    l.addPlayer(rootP);
    l.addPlayer(leftP);

    return l.lookup("Ron") == leftP;
  }
  
  /**
   * Tester method for the lookup method in the Leaderboard class,
   * tests with inputs of the player to the right of the root.
   * 
   * @return true if result is equal to expected value
   */
  private static boolean testLookupRight() {
    Leaderboard l = new Leaderboard();
    Player rootP = new Player("Harry", 10);
    Player rightP = new Player("Hermione", 11);
    
    l.addPlayer(rootP);
    l.addPlayer(rightP);

    return l.lookup("Hermione") == rightP;
  }
  
  /**
   * Tester method for the lookup method in the Leaderboard class,
   * tests with inputs of a player that isn't present in the tree.
   * 
   * @return true if result is equal to expected value
   */
  private static boolean testLookupNotPresent() {
    Leaderboard l = new Leaderboard();
    Player rootP = new Player("Harry", 10);
    Player leftP = new Player("Ron", 9);
    Player rightP = new Player("Hermione", 11);

    l.addPlayer(rootP);
    l.addPlayer(leftP);
    l.addPlayer(rightP);

    return l.lookup("Draco") == null;
  }
  
  //////////////////////////////////////////// ADD ////////////////////////////////////////////
  /**
   * Tester method for the add method in the Leaderboard class,
   * tests with inputs of the root player, the player to the left 
   * and the right of the root, and a duplicate player.
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean testAdd() {
    boolean test1 = testAddPlayerEmpty();
    boolean test2 = testAddPlayer();
    boolean test3 = testAddPlayerDuplicate();
    if (!test1) System.out.print("addEmpty FAIL ");
    if (!test2) System.out.print("addPlayer FAIL ");
    if (!test3) System.out.print("addDuplicate FAIL ");
    return test1 && test2 && test3;
  }
  
  /**
   * Tester method for the add method in the Leaderboard class,
   * tests with inputs of the root player.
   * 
   * @return true if result is equal to expected value
   */
  private static boolean testAddPlayerEmpty() {
    Leaderboard l = new Leaderboard();
    
    Player rootP = new Player("Harry", 10);
    l.addPlayer(rootP);
    if (l.size() != 1) return false;

    return l.getRoot().getData().equals(rootP);
}

/**
 * Tester method for the add method in the Leaderboard class,
 * tests with inputs of the player to the left and the right 
 * of the root.
 * 
 * @return true if result is equal to expected value
 */
private static boolean testAddPlayer() {
  Leaderboard l = new Leaderboard();

  Player rootP = new Player("Harry", 10);
  l.addPlayer(rootP);
  if (l.size() != 1) return false;
  
  Player leftP = new Player("Ron", 9);
  l.addPlayer(leftP);
  if (l.getRoot().getLeft().getData().compareTo(rootP) >= 0) return false;
  if (l.size() != 2) return false;

  Player rightP = new Player("Hermione", 11);
  l.addPlayer(rightP);
  if (l.getRoot().getRight().getData().compareTo(rootP) <= 0) return false;
  if (l.size() != 3) return false;

  return true;
}
  
/**
 * Tester method for the add method in the Leaderboard class,
 * tests with inputs of a duplicate player.
 * 
 * @return true if result is equal to expected value
 */
private static boolean testAddPlayerDuplicate() {
  Leaderboard l = new Leaderboard();

  Player rootP = new Player("Harry", 10);
  l.addPlayer(rootP);
  if (l.size() != 1) return false;

  Player duplicateP = new Player("Harry", 10);
  if (l.addPlayer(duplicateP)) return false;
  if (l.size() != 1) return false;

  return true;
}

  
  //////////////////////////////////////////// REMOVE ////////////////////////////////////////////
  /**
   * Tester method for the remove method in the Leaderboard class,
   * tests with inputs of the player to the left of the root, the 
   * player to the right of the root, the player at the root,
   * and a player not in the tree.
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean testRemove() {
    boolean test1 = testRemoveLeaf();
    boolean test2 = testRemoveOneChild();
    boolean test3 = testRemoveTwoChildren();
    boolean test4 = testRemoveNotInTree();
    if (!test1) System.out.print("remove FAIL ");
    if (!test2) System.out.print("removeOneChild FAIL ");
    if (!test3) System.out.print("removeTwoChildren FAIL ");
    if (!test4) System.out.print("removeNotInTree FAIL ");
    return test1 && test2 && test3 && test4;
  }
  
  /**
   * Tester method for the remove method in the Leaderboard class,
   * tests with inputs of the player to the left of the root.
   * 
   * @return true if result is equal to expected value
   */
  private static boolean testRemoveLeaf() {
    Leaderboard l = new Leaderboard();

    Player rootP = new Player("Harry", 10);
    Player leftP = new Player("Ron", 8);

    l.addPlayer(rootP);
    l.addPlayer(leftP);

    l.removePlayer(leftP);
    if (l.size() != 1) return false;
    if (l.getRoot() == null) return false;
    if (l.getRoot().getLeft() != null) return false;

    return true;
}

/**
 * Tester method for the remove method in the Leaderboard class,
 * tests with inputs of the player to the right of the root.
 * 
 * @return true if result is equal to expected value
 */
private static boolean testRemoveOneChild() {
  Leaderboard l = new Leaderboard();

  Player rootP = new Player("Harry", 10);
  Player leftP = new Player("Ron", 9);
  Player rightP = new Player("Hermione", 11);

  l.addPlayer(rootP);
  l.addPlayer(leftP);
  l.addPlayer(rightP);
  l.removePlayer(rightP);

  if (l.size() != 2) return false;
  if (l.getRoot() == null) return false;
  if (l.getRoot().getRight() != null) return false;

  return true;
}

/**
 * Tester method for the remove method in the Leaderboard class,
 * tests with inputs of the player at the root.
 * 
 * @return true if result is equal to expected value
 */ 
private static boolean testRemoveTwoChildren() {
  Leaderboard l = new Leaderboard();

  Player rootP = new Player("Harry", 10);
  Player leftP = new Player("Ron", 8);
  Player rightP = new Player("Hermione", 12);
  
  l.addPlayer(rootP);
  l.addPlayer(leftP);
  l.addPlayer(rightP);
  l.removePlayer(rootP);
  
  if (l.size() != 2) return false;
  if (l.getRoot() == null) return false;
  if (!l.getRoot().getData().equals(rightP)) return false;

  return true;
}

/**
 * Tester method for the remove method in the Leaderboard class,
 * tests with inputs of a player not in the tree.
 * 
 * @return true if result is equal to expected value
 */ 
private static boolean testRemoveNotInTree() {
  Leaderboard l = new Leaderboard();

  Player rootP = new Player("Harry", 10);
  Player notInTree = new Player("Draco", 15);
  
  l.addPlayer(rootP);

  if (l.removePlayer(notInTree)) return false;
  if (l.size() != 1) return false;
  if (!l.getRoot().getData().equals(rootP)) return false;

  return true;
}

 
  //////////////////////////////////////////// GET NEXT ////////////////////////////////////////////
  /**
   * Tester method for the next method in the Leaderboard class,
   * tests with inputs of the player at the root, the player to the
   * right of the left node, and the player to the left of the right node.
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean testGetNext() {
    boolean test1 = testGetNextAfterRoot();
    boolean test2 = testGetNextAfterLeftSubtree();
    boolean test3 = testGetNextAfterRightSubtree();
    if (!test1) System.out.print("afterRoot FAIL ");
    if (!test2) System.out.print("afterLeft FAIL ");
    if (!test3) System.out.print("afterRight FAIL ");
    return test1 && test2 && test3;
  }
  
  /**
   * Tester method for the next method in the Leaderboard class,
   * tests with inputs of the player at the root.
   * 
   * @return true if result is equal to expected value
   */
  private static boolean testGetNextAfterRoot() {
    Leaderboard l = new Leaderboard();
    
    Player rootP = new Player("Harry", 10);
    Player leftP = new Player("Ron", 9);
    Player rightP = new Player("Hermione", 11);
    
    l.addPlayer(rootP);
    l.addPlayer(leftP);
    l.addPlayer(rightP);
    
    return l.next(rootP).equals(rightP);
}

/**
 * Tester method for the next method in the Leaderboard class,
 * tests with inputs of the player to the right of the left node.
 * 
 * @return true if result is equal to expected value
 */
private static boolean testGetNextAfterLeftSubtree() {
  Leaderboard l = new Leaderboard();
  
  Player rootP = new Player("Harry", 10);
  Player leftP1 = new Player("Ron", 8);
  Player leftP2 = new Player("Ginny", 9);
  
  l.addPlayer(rootP);
  l.addPlayer(leftP1);
  l.addPlayer(leftP2);
  
  return l.next(leftP1).equals(leftP2);
}

/**
 * Tester method for the next method in the Leaderboard class,
 * tests with inputs of the player to the left of the right node.
 * 
 * @return true if result is equal to expected value
 */
private static boolean testGetNextAfterRightSubtree() {
  Leaderboard l = new Leaderboard();
  
  Player rootP = new Player("Harry", 10);
  Player rightP1 = new Player("Hermione", 12);
  Player rightP2 = new Player("Luna", 11);
  
  l.addPlayer(rootP);
  l.addPlayer(rightP1);
  l.addPlayer(rightP2);
  
  return l.next(rootP).equals(rightP2);
}

  //////////////////////////////////////////// MAIN ////////////////////////////////////////////
  
  public static void main(String[] args) {
    System.out.print("Player compareTo(): ");
    System.out.println(testPlayerCompareTo()?"PASS":"");
    System.out.print("Leaderboard lookup(): ");
    System.out.println(testNameLookup()?"PASS":"");
    
    System.out.print("Leaderboard add(): ");
    System.out.println(testAdd()?"PASS":"");

    System.out.print("Leaderboard remove(): ");
    System.out.println(testRemove()?"PASS":"");

    System.out.print("Leaderboard next(): ");
    System.out.println(testGetNext()?"PASS":"");
  } 
}