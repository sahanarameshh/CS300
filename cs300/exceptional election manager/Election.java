//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Election Class
// Course:   CS 300 Fall 2024
//
// Author:   Sahana Ramesh
// Email:    ramesh37@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
// 
// Partner Name:    Anil Duvvuri
// Partner Email:   aduvvuri@wisc.edu
// Partner Lecturer's Name: Hobbes LeGault
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   X Write-up states that pair programming is allowed for this assignment.
//   X We have both read and understand the course Pair Programming Policy.
//   X We have registered our team prior to the team registration deadline.
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons:         Nishka Shah — helped with logic behind removeCandidate method (moving elements backwards post-removal)
// Online Sources:  N/A
//
///////////////////////////////////////////////////////////////////////////////

import java.util.NoSuchElementException;

/**
 * An instantiable class representing an election, which maintains a list of Candidates running in the election and some information about that election.
 */
public class Election {
    private Candidate[] candidates;
    private int numCandidates;
    public final String SEAT_NAME;

    /**
     * Initializes the oversize array for this election's candidates and sets the name of the position for which this election is being held.
     * 
     * @param seatName name of position
     * @param maxCandidates maximum number of candidates
     */
    public Election(String seatName, int maxCandidates) {
        if (maxCandidates <= 0) {
            throw new IllegalArgumentException();
        }
        this.SEAT_NAME = seatName;
        candidates = new Candidate[maxCandidates];
    }

    /**
     * Accessor method for the current number of candidates in this Election
     * 
     * @return number of candidates
     */
    public int getNumCandidates() {
        return this.numCandidates;
    }

    /**
     * Accessor method for the maximum number of candidates in this Election.
     * 
     * @return length of candidates list
     */
    public int capacity() {
        return this.candidates.length;
    }

    /**
     * Adds a candidate to the END of this election's list.
     * 
     * @param candidate candidate object to add to list
     */
    public void addCandidate(Candidate candidate) {
        if(numCandidates >= candidates.length) {
          throw new IllegalArgumentException();
        }
        for (Candidate c : candidates) {
          if (candidate.equals(c)) {
            throw new IllegalArgumentException();
          }
        }

        candidates[numCandidates] = candidate;
        numCandidates++;
    }

    /**
     * Removes the candidate matching the provided candidate exactly from the election's list of candidates
     * 
     * @param candidate candidate object to remove from list
     */
    public void removeCandidate(Candidate candidate) {
        boolean candidateFound = false;
        if (numCandidates == 0) {
            throw new IllegalStateException();
        }

        for (int i = 0; i < numCandidates; i++) {
            if (candidates[i].equals(candidate)) {
                for (int j = i; j < numCandidates - 1; j++) {
                    candidates[j] = candidates[j + 1];
                }

                candidates[numCandidates - 1] = null; 
                numCandidates--;
                candidateFound = true;
                break; 
            }
        }

        if (!candidateFound) {
            throw new NoSuchElementException();
        }
    }

    /**
     * Returns a reference to the Candidate receiving more than 50% of the votes in this election
     * 
     * @return winning candidate object
     */
    public Candidate findWinner() {
        if (numCandidates == 0) {
          throw new IllegalStateException();
        }

        int totalVotes = 0;
        
        for (Candidate c : candidates) {
            totalVotes += c.getNumVotes();
        }
        
        for (Candidate c : candidates) {
            if (c.getNumVotes() > (totalVotes / 2)) {
                return c;
            }
        }

        throw new NoSuchElementException("No Candidates has >50% of votes");
    }

    /**
     * Increases the vote count of the given candidate by one
     * 
     * @param candidate candidate that receives the votes
     */
    public void vote(Candidate candidate) {
        boolean foundCandidate = false;
        for (Candidate c : candidates) {
            if (c.equals(candidate)) {
                foundCandidate = true;
                c.addVote();
            }
        }

        if (!foundCandidate) {
            throw new NoSuchElementException();
        }
    }

    /**
     * Creates and returns a String representation of this Election object, with each Candidate's string representation on a separate line.
     * 
     * @return the String representation of the current state of this Election, which does NOT end with a newline
     */
    public String toString() {
        String str = this.SEAT_NAME;
        for (int i = 0; i < numCandidates; i++) {
            str += candidates[i].toString();
        }
        return str;
    }

    /**
     * Determines whether a provided object is equivalent to this Election.
     * 
     * @param anObject he object to compare this Election against
     * @return true if the given object represents a Election equivalent to this election, false otherwise.
     */
    @Override
    public boolean equals(Object anObject) {
        if (anObject instanceof Election) {
            if (this.SEAT_NAME.equalsIgnoreCase(((Election) anObject).SEAT_NAME)) {
                return true;
            }
        }
        return false;

    }
}