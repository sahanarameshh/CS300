//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Leaderboard
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
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class represents a binary search tree of Player objects with various scores. We sort the
 * Players by a combination of their scores and names (see the Player.compareTo() method).
 * 
 * The class provides methods to insert, search, and remove Player objects from the tree, as well
 * as a couple of methods for viewing its contents in printed form.
 */
public class Leaderboard implements Iterable<Player> {
  private BSTNode<Player> root;

  private int size;
  
  public Leaderboard() {
    this.size = 0;
    this.root = null;
  }
  
  /**
   * An accessor method for the size variable. If everything has been implemented properly, this
   * should produce the same results as count() below, but in O(1) time instead of O(N).
   * @return the number of nodes expected to be present in this BST
   */
  public int size() { 
    return this.size; 
  }
  
  /**
   * Returns the root node of the binary tree, for testing purposes. You can use this reference to
   * manually build a tree structure without using the add method: save this value in a variable in
   * your tester method and use setLeft/setRight to add nodes manually.
   * 
   * @return a reference to the root node of the binary tree
   */
  protected BSTNode<Player> getRoot() { 
    return this.root; 
  }
  
  //////////////////////////////////////////// COUNT ////////////////////////////////////////////
  
  /**
   * PROVIDED:
   * Returns the number of players currently present in the BST.
   * @return the number of players in the leaderboard
   */
  public int count() {
    return countHelper(root);
  }
  
  /**
   * THIS METHOD MUST BE IMPLEMENTED _RECURSIVELY_
   * 
   * Returns the size of the subtree rooted at the given node.
   * @param node the root of this subtree
   * @return the number of players in this subtree
   */
  protected int countHelper(BSTNode<Player> node) {
    if (node == null) {
      return 0;
    }
    
    return 1 + countHelper(node.getLeft()) + countHelper(node.getRight());
  }
  
  ///////////////////////////////////////// LOOKUP: NAME /////////////////////////////////////////
  
  /**
   * PROVIDED:
   * Returns a Player reference with the given name. You may assume that there is AT MOST ONE Player
   * with the name in the tree.
   * @param name the name of the player to find
   * @return a reference to the Player with the given name, or null if no Player with this name is
   * present in the tree
   */
  public Player lookup(String name) {    
    return lookupHelper(root, name);
  }
  
  /**
   * THIS METHOD MUST BE IMPLEMENTED _RECURSIVELY_
   * 
   * Exhaustively searches the Leaderboard tree for a Player with the given name. Note that the tree
   * is ordered primarily by SCORE and binary search cannot be used for this lookup!
   * @param node the root of this subtree
   * @param name the name of the player to find
   * @return a reference to the Player with the given name, or null if no Player with this name is
   * present in the subtree
   */
  protected Player lookupHelper(BSTNode<Player> node, String name) {
    if (node == null) {
      return null;
    }
    
    if (node.getData().getName().equals(name)) {
      return node.getData();
    }

    Player left = lookupHelper(node.getLeft(), name);
    if(left != null) {
      return left;
    }

    return lookupHelper(node.getRight(), name);
  }
  
  //////////////////////////////////////////// ADD ////////////////////////////////////////////
  
  /**
   * PROVIDED:
   * Adds a new Player to this Leaderboard.
   * 
   * If the root is null, creates a new node with the given Player and sets it as the root. Otherwise,
   * calls the addPlayerHelper() method to recursively add the Player to the tree. If the Player is
   * already in the tree, they will NOT be added again (return false).
   * @param player the player to add to the tree
   * @return true if the Player was successfully added, false otherwise
   */
  public boolean addPlayer(Player player) {    
    if (root == null) {
        root = new BSTNode<>(player);
        size++;
        return true;
    }
    
    if (addPlayerHelper(root, player)) {
        size++;
        return true;
    }
    
    return false;
  }
  
  /**
   * THIS METHOD MUST BE IMPLEMENTED _RECURSIVELY_
   * 
   * Adds a new player to the leaderboard rooted at the given node. If the player is already in the
   * tree, they will NOT be added again.
   * 
   * @param node the root node of the tree to add the player to
   * @param player the player to add to the tree
   * @return true if they player was successfully added, false otherwise
   */
  protected boolean addPlayerHelper(BSTNode<Player> node, Player player) {
    if (node == null) {
      return false;
    }

    if (node.getData().getName().equals(player.getName())) {
      return false;
    }

    if (player.getScore() < node.getData().getScore()) {
        if (node.getLeft() == null) {
            node.setLeft(new BSTNode<>(player));
            return true;
        } else {
            return addPlayerHelper(node.getLeft(), player);
        }
    } 
    else if (player.getScore() > node.getData().getScore()) {
        if (node.getRight() == null) {
            node.setRight(new BSTNode<>(player));
            return true;
        } else {
            return addPlayerHelper(node.getRight(), player);
        }
    }

    return false;
  }
  
  //////////////////////////////////////////// REMOVE ////////////////////////////////////////////
  
  /**
   * PROVIDED:
   * Removes the given Player from this Leaderboard.
   * 
   * This method handles the case where the tree is empty, and otherwise uses the helper method to
   * recursively remove nodes from the tree.
   * 
   * @param player the player to remove from the tree
   * @return true if the player was successfully removed, false otherwise
   * @throws NoSuchElementException if the player is not found in the tree
   */
  public boolean removePlayer(Player player) {
    if (root == null) {
      return false;
    }
    
    try {   
      root = removePlayerHelper(root, player);
      size--;
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }
  
  /**
   * THIS METHOD MUST BE IMPLEMENTED _RECURSIVELY_
   * 
   * Removes a player from the BST rooted at the given node and returns the new root of the subtree
   * (which may or may not be the same as the old root). When you have a choice of replacements for
   * a removed node, this method should use the NEXT player - as there is a helper method to find 
   * that value.
   * 
   * @param node the current root node of the subtree
   * @param player the player to remove from the tree
   * @return the root node of this subtree after the player has been removed (may be the same as the
   * old root of this subtree)
   * @throws NoSuchElementException if the player is not found in the tree
   */
  protected BSTNode<Player> removePlayerHelper(BSTNode<Player> node, Player player) {
    if (node == null) {
      throw new NoSuchElementException("Player is not found in the tree");
    }

    if (player.compareTo(node.getData()) < 0) {
        node.setLeft(removePlayerHelper(node.getLeft(), player));
    } else if (player.compareTo(node.getData()) > 0) {
        node.setRight(removePlayerHelper(node.getRight(), player));
    } else {
        if (node.getLeft() == null && node.getRight() == null) {
            return null;
        } else if (node.getLeft() == null) {
            return node.getRight();
        } else if (node.getRight() == null) {
            return node.getLeft();
        } else {
            node = new BSTNode<>(next(node.getData()), node.getLeft(), removePlayerHelper(node.getRight(), next(node.getData())));
        }
    }

    return node;
}

  
  //////////////////////////////////////////// GET MIN ////////////////////////////////////////////
  
  /**
   * PROVIDED:
   * Finds the player with the lowest score in the leaderboard. If the tree is empty, returns null.
   * @return the Player with the lowest score, or null if the tree is empty.
   */
  public Player getMinScore() {
    if (root == null) {
      return null;
    }
    
    return getMinScoreHelper(root);
  }
  
  /**
   * THIS METHOD MUST BE IMPLEMENTED _RECURSIVELY_
   * 
   * Returns the Player with the smallest score in the current subtree.
   * @param node the root node of the tree to search; must not be null
   * @return the Player with the smallest score in the current subtree
   */
  protected Player getMinScoreHelper(BSTNode<Player> node) {
    BSTNode<Player> current = root;

    while (current.getLeft() != null) {
        current.setLeft(current);
    }

    return current.getData();
  }
  
  //////////////////////////////////////////// GET MAX ////////////////////////////////////////////
  
  /**
   * PROVIDED:
   * Finds the player with the highest score in the leaderboard. If the tree is empty, returns null.
   * @return the Player with the highest score, or null if the tree is empty.
   */
  public Player getMaxScore() {
    if (root == null) {
      return null;
    }
    
    return getMaxScoreHelper(root);
  }
  
  /**
   * THIS METHOD MUST BE IMPLEMENTED _RECURSIVELY_
   * 
   * Returns the Player with the largest score in the current subtree.
   * @param node the root node of the tree to search; must not be null
   * @return the Player with the largest score in the current subtree
   */
  protected Player getMaxScoreHelper(BSTNode<Player> node) {
    BSTNode<Player> current = node;

    while (current.getRight() != null) {
      current.setRight(current);
    }

    return current.getData();
  }
  
  //////////////////////////////////////////// NEXT ////////////////////////////////////////////
  
  /**
   * PROVIDED:
   * Finds the "smallest" Player in the leaderboard whose object is still "larger" than the provided
   * Player (see Player.compareTo()). If the tree is empty, returns null. 
   * @param player the player whose successor value we are searching for
   * @return the next largest player in the leaderboard, or null if no such player exists
   */
  public Player next(Player player) {
    if (root == null) {
      return null;
    }

    return nextHelper(root, player);
  }
  
  /**
   * THIS METHOD MUST BE IMPLEMENTED _RECURSIVELY_
   * 
   * Returns the Player with the next largest score (or equal score but next largest name) in the
   * subtree rooted at the current node.
   * 
   * Hint: consider the three places that this player might be:
   *   (1) the left subtree,
   *   (2) `node` itself, or
   *   (3) the right subtree
   * Find the best candidate Player in each of those three places and choose the best among them.
   * 
   * @param node the root of the relevant subtree
   * @param player the player whose successor value we are searching for
   * @return the next largest player in the leaderboard, or null if no player in this subtree is
   * larger than the given player
   */
  protected Player nextHelper(BSTNode<Player> node, Player player) {
    if (node == null) {
      return null;
    }

    if (player.compareTo(node.getData()) < 0) {
        Player left = nextHelper(node.getLeft(), player);
        if (left != null && left.compareTo(node.getData()) < 0) {
          return left;
        } else {
          return node.getData();
        }
    } else {
        return nextHelper(node.getRight(), player);
    }
}

  
  //////////////////////////////////////////// STRINGS ////////////////////////////////////////////
  @Override
  public Iterator<Player> iterator() {
      return new LeaderboardIterator(root);
  }
    
  /**
   * PROVIDED:
   * Creates a list of the players in this leaderboard from lowest to highest score.
   *
   * @return a list of all players in increasing order of score
   */
  @Override
  public String toString() {
    String leaders = "";
    for (Player p : this) {
      leaders += p.toString()+"\n";
    }
    return leaders.trim();
  }
  
  /**
   * PROVIDED:
   * Creates a tree-formatted version of the current state of the leaderboard
   * @return a tree-formatted string representation of this leaderboard
   */
  public String prettyPrint() { return prettyPrintHelper("", root, false); }

  /**
   * PROVIDED:
   * Recursive helper method for the prettyPrint() method
   */
  private String prettyPrintHelper(String prefix, BSTNode<Player> node,
                                  boolean isLeft) {
    if (node == null) {
      return "";
    }
    String str =
        prefix + (isLeft ? "├──" : "└──") + node.getData().toString() + "\n";
    str += prettyPrintHelper(prefix + (isLeft ? "│   " : "    "), node.getLeft(),
                          true);
    str += prettyPrintHelper(prefix + (isLeft ? "│   " : "    "), node.getRight(),
                          false);
    return str;
  }
}