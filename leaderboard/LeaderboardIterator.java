//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Leaderboard Iterator
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
import java.util.Stack;
/**
 * This class iterates, in-order, the binary search tree of Players,
 * keeping track of the nodes using a stack.
 */
public class LeaderboardIterator implements Iterator<Player> {
    private Stack<BSTNode<Player>> stack = new Stack<>();

    /**
     * Constructs a new LeaderboardIterator for the BST starting at the
     * given root node.
     *
     * @param root the root of the BST
     */
    public LeaderboardIterator(BSTNode<Player> root) {
        pushLeft(root);
    }

    /**
     * Determines if there are more Players to iterate over in the tree.
     *
     * @return true if there are more Players to visit, false otherwise
     */
    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /**
     * Finds the next Player in the tree.
     *
     * @return the next Player
     * @throws NoSuchElementException if there are no more Players in the tree
     */
    @Override
    public Player next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more Players in the tree.");
        }
        
        BSTNode<Player> node = stack.pop();
        pushLeft(node.getRight()); 
        
        return node.getData();
    }

    /**
     * Helper method that pushes all left nodes of a given node onto 
     * the stack, so that smallest element is always on top.
     *
     * @param node the given node to push left nodes on
     */
    private void pushLeft(BSTNode<Player> node) {
        while (node != null) {
            stack.push(node);
            node = node.getLeft();
        }
    }
}
