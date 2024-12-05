//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Election Manager
// Course: CS 300 Fall 2024
//
// Author: Sahana Ramesh
// Email: ramesh37@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons: Hobbes LeGault: explained add method algorithm and syntax for turning an int into a
//////////////// String (and vice versa)
// Online Sources: N/A
//
import java.util.Arrays;

public class ElectionManager {
  /**
   * Determines whether the given candidate, specified uniquely by name and party, is present in the
   * given list of candidates.
   *
   * @param candidates    A two-dimensional oversize array containing the current list of candidates
   *                      as [name, party, numVotes]. This input value is assumed to conform to the
   *                      standards of oversize arrays, and is assumed to be in alphabetical order
   *                      by candidate name.
   * @param numCandidates The current size of the candidates oversize array at the time of input.
   *                      This value is assumed to be accurate.
   * @param name          The name of the candidate to search for
   * @param party         The party affiliation of the candidate to search for
   * @return true if a candidate with the given name AND party affiliation is present in the list;
   *         false otherwise
   */
  public static boolean containsCandidate(String[][] candidates, int numCandidates, String name,
      String party) {
    if (!(candidates.length > 0 && numCandidates == 0)) {
      for (int i = 0; i < numCandidates; i++) {
        if (candidates[i][0].equals(name) && candidates[i][1].equals(party)) {
          return true;
        }
      }
    }

    return false;
  }

  /**
   * Adds a candidate with the given name, party affiliation, and vote count to the given list of
   * candidates, maintaining the candidate list in alphabetical order by NAME, and returns the new
   * total number of candidates in the array. Does NOT add the candidate if another candidate with
   * the same name and party affiliation is already present in the list, or if the provided vote
   * count is a negative value, or if the input array has no room to add another candidate. You may
   * use other methods from this class to help you detect these conditions, if you like. IMPORTANT:
   * this list MUST be maintained in ALPHABETICAL ORDER by CANDIDATE NAME!
   *
   * @param candidates    A two-dimensional oversize array containing the current list of candidates
   *                      as [name, party, numVotes]. This input value is assumed to conform to the
   *                      standards of oversize arrays, and is assumed to be in alphabetical order
   *                      by candidate name.
   * @param numCandidates The current size of the candidates oversize array at the time of input.
   *                      This value is assumed to be accurate.
   * @param name          The name of the candidate to add
   * @param party         The party affiliation of the candidate to add
   * @param numVotes      The number of votes the candidate to add has received
   * @return The size of the candidates oversize array after this method has completed. This value
   *         will be either the same as numCandidates or one larger.
   */
  public static int addCandidate(String[][] candidates, int numCandidates, String name,
      String party, int numVotes) {
    int toAddIndex = -1;

    // check if candidate is in list, votes aren't negative, and list isn't full
    if (containsCandidate(candidates, numCandidates, name, party) || numVotes < 0
        || candidates.length == numCandidates)
      return numCandidates;

    // find index
    for (int i = 0; i < numCandidates; i++) {
      if (candidates[i][0].compareTo(name) > 0) {
        toAddIndex = i;
        break;
      }
    }

    if (toAddIndex == -1) {
      toAddIndex = numCandidates;
    } else {
      // move following elements backwards, add element, and update size
      for (int i = numCandidates; i > toAddIndex; i--) {
        candidates[i] = candidates[i - 1];
      }
    }
    candidates[toAddIndex] = new String[3];
    candidates[toAddIndex][0] = name;
    candidates[toAddIndex][1] = party;
    candidates[toAddIndex][2] = numVotes + "";
    numCandidates++;
    return numCandidates;
  }

  /**
   * Removes the candidate specified uniquely by name and party from the given array of candidates,
   * maintaining the candidates array in alphabetical order by name. Does not modify the array if
   * the candidate specified is not present in the list.
   *
   * @param candidates    A two-dimensional oversize array containing the current list of candidates
   *                      as [name, party, numVotes]. This input value is assumed to conform to the
   *                      standards of oversize arrays, and is assumed to be in alphabetical order
   *                      by candidate name.
   * @param numCandidates The current size of the candidates oversize array at the time of input.
   *                      This value is assumed to be accurate.
   * @param name          The name of the candidate to drop from the list
   * @param party         The party affiliation of the candidate to drop from the list
   * @return The size of the candidates oversize array after this method has completed. This value
   *         will be either the same as numCandidates or one smaller.
   */
  public static int dropCandidate(String[][] candidates, int numCandidates, String name,
      String party) {
    int removedIndex = 0;

    // check if candidate is in list
    if (!containsCandidate(candidates, numCandidates, name, party))
      return numCandidates;

    // find index, remove element, and update size
    for (int i = 0; i < numCandidates; i++) {
      if (candidates[i][0].equals(name)) {
        removedIndex = i;
        candidates[i] = null;
        numCandidates--;
        break;
      }
    }

    // move elements backwards
    for (int i = removedIndex; i < numCandidates; i++) {
      candidates[i] = candidates[i + 1];
    }
    // remove last element
    candidates[numCandidates] = null;

    return numCandidates;
  }

  /**
   * Finds the candidate with the majority (that is, >50%) of total votes cast. If no candidate in
   * the list has received more than 50% of the total votes, returns the string "CONTINGENT". This
   * method will NOT be tested with a numCandidates of 0. You may wish to create a private helper
   * method to help calculate the total number of votes cast across all candidates in the list,
   * though this is not required.
   *
   * @param candidates    A two-dimensional oversize array containing the current list of candidates
   *                      as [name, party, numVotes]. This input value is assumed to conform to the
   *                      standards of oversize arrays, and is assumed to be in alphabetical order
   *                      by candidate name.
   * @param numCandidates The current size of the candidates oversize array at the time of input.
   *                      This value is assumed to be accurate.
   * @return A string containing the "name (party) - votePct%" values of the candidate receiving a
   *         majority of the votes, or the string "CONTINGENT" if no single candidate has received
   *         more than half of the votes.
   */
  public static String findWinner(String[][] candidates, int numCandidates) {
    double maxVotes = 0.0;
    int totalVotes = 0;
    double maxVotePct = 0.0;
    String name = "";
    String party = "";

    for (int i = 0; i < numCandidates; i++) {
      int votes = Integer.parseInt(candidates[i][2]);
      totalVotes += votes;

      if (votes == maxVotes) {
        maxVotePct = maxVotes / (totalVotes) * 100;
      } else if (votes > maxVotes) {
        maxVotes = votes;
        name = candidates[i][0];
        party = candidates[i][1];
        maxVotePct = maxVotes / (totalVotes) * 100;
      }
    }

    if (maxVotePct < 50.0)
      return "CONTINGENT";
    else
      return name + " (" + party + ") - " + maxVotePct + "%";
  }

  /**
   * Finds the candidate with the smallest number of votes cast. If there are multiple candidates
   * with the same smallest number of votes, this method returns the one whose name is closest to
   * the beginning of the alphabet (smallest index). If there are fewer than two candidates in the
   * election, this method returns the string "UNCONTESTED".
   *
   * @param candidates    A two-dimensional oversize array containing the current list of candidates
   *                      as [name, party, numVotes]. This input value is assumed to conform to the
   *                      standards of oversize arrays, and is assumed to be in alphabetical order
   *                      by candidate name.
   * @param numCandidates The current size of the candidates oversize array at the time of input.
   *                      This value is assumed to be accurate.
   * @return A string containing the "name (party) - numVotes" values of the lowest-index candidate
   *         receiving the smallest number of votes, or "UNCONTESTED" if there are one or zero
   *         candidates in the list.
   */
  public static String findLowestPollingCandidate(String[][] candidates, int numCandidates) {
    String name = candidates[0][0];
    String party = candidates[0][1];
    int minVotes = Integer.parseInt(candidates[0][2]);

    if (numCandidates < 2)
      return "UNCONTESTED";

    for (int i = 0; i < numCandidates; i++) {
      int votes = Integer.parseInt(candidates[i][2]);
      if (votes < minVotes) {
        minVotes = votes;
        name = candidates[i][0];
        party = candidates[i][1];
      }
    }

    return name + " (" + party + ") - " + minVotes;
  }
}
