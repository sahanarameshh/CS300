//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Ballot Class
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
// Persons:         N/A
// Online Sources:  N/A
//
///////////////////////////////////////////////////////////////////////////////

import java.util.NoSuchElementException;
/**
* A tester class for the Election Manager project. It contains various tests
* to check the correctness of the Candidate, Election, and Ballot classes.
*
*/
public class ElectionManagerTester {
 /**
  * Tests the Candidate constructor, toString(), and getter method for correctness.
  * This test accounts for the fact that a bad implementation may throw an Exception.
  *
  * @return true if all tests pass, false otherwise
  */
 public static boolean testCandidateConstructorAndGetters() {
   // in case we get an unexpected exception from a broken implementation
   // we handle it with a try-catch to avoid our tester from crashing
   try {
     // test the 2-argument constructor
     Candidate c = new Candidate("lebron james", "basketball");
     // check that the instance data fields have been initialized correctly
     // using the toString to do this we are also checking its correctness!
     // in a bad implementation either:
     //   1) the constructor didn't intialize a data field correctly OR
     //   2) toString() doesn't return the correct value
     if (!c.toString().equals("lebron james (basketball): 0")) return false;
     // let's also verify the one getter method agrees with the toString() output:
     if (c.getNumVotes() != 0) return false;
   } catch (Exception e) {
     // we encountered an exception when we should not have, it is a bad implementation!
     e.printStackTrace();
     return false;
    
   }
  
   // all tests pass:
   return true;
 }
 /**
  * Verifies that the Candidate constructor throws the correct type of exception(s)
  * where an exception is expected. See the Candidate documentation for details.
  *
  * @return true if all tests pass, false otherwise
  */
 public static boolean testCandidateConstructorExceptions() {
   boolean tf1 = false;
   boolean tf2 = false;
   try {
     Candidate c = new Candidate(null,"Basketball");
   } catch(IllegalArgumentException e) {
     tf1 = true;
   }
   try {
     Candidate c = new Candidate("Lebron James",null);
   } catch(IllegalArgumentException e) {
     tf2 = true;
   }
   return tf1&&tf2; // TODO
 }
 /**
  * Tests the Election constructor and associated getter methods for correctness. (Note that
  * SEAT_NAME is a publicly-accessible constant and can be verified directly.)
  * This test accounts for the fact a bad implementation may throw an Exception.
  *
  * @return true if all tests pass, false otherwise
  */
 public static boolean testElectionConstructorAndGetters() {
   try {
     Election e = new Election("Goat", 3);
     Election g = new Election("Goat", 3);
     if(!e.equals(g)) {
       return false;
     }
     if(e.capacity()!=3) {
       return false;
     }
   } catch(IllegalArgumentException c) {
     return false;
   }
   return true; // TODO
 }
 /**
  * Verifies that the Election constructor throws the correct type of exception(s)
  * in situations where an exception is expected. See the Election documentation for
  * details.
  *
  * @return true if all tests pass, false otherwise
  */
 public static boolean testElectionConstructorExceptions() {
   try {
     Election e = new Election("Goat", 0);
   } catch(IllegalArgumentException c) {
     return true;
   }
   return false; // TODO
 }
 /**
  * Tests the Election's addCandidate() method for correctness in non-Exception situations.
  * This test accounts for the fact a bad implementation may throw an Exception.
  *
  * @return true if all tests pass, false otherwise
  */
 public static boolean testAddCandidate() {
   try {
     Election e = new Election("Goat", 3);
     Candidate c1 = new Candidate("Lebron James", "Basketball");
     e.addCandidate(c1);
     String x = e.toString();
     if(x.equals(e.SEAT_NAME)) {
       return false;
     }
   } catch (IllegalArgumentException a) {
     return false;
   }
   return true;// TODO
 }
  /**
  * Verifies that the Election's addCandidate() method throws the correct type of exception(s)
  * in situations where an exception is expected. See the Election documentation for
  * details.
  *
  * @return true if all tests pass, false otherwise
  */
 public static boolean testAddCandidateExceptions() {
   boolean t1 = false;
   try {
     Election e = new Election("Goat", 3);
     Candidate c1 = new Candidate("Lebron James", "Basketball");
     Candidate c2 = new Candidate("Lionel Messi", "Soccer");
     e.addCandidate(c1);
     e.addCandidate(c2);
     e.addCandidate(c1);
   } catch (IllegalArgumentException e) {
     t1 = true;
   }
   return t1;  }
 /**
  * Tests the Election's vote() method for correctness in non-Exception situations.
  * This test accounts for the fact a bad implementation may throw an Exception.
  *
  * @return true if all tests pass, false otherwise
  */
 public static boolean testVote() {
   try {
     Election e = new Election("Goat", 3);
     Candidate c1 = new Candidate("Lebron James", "Basketball");
     e.addCandidate(c1);
     e.vote(c1);
     if(c1.getNumVotes()!=1) {
       return false;
     }
   }catch(NoSuchElementException n) {
     return false;
   }
   return true; // TODO
 }
 /**
  * Verifies that the Election's vote() method throws the correct type of exception(s)
  * in situations where an exception is expected. See the Election documentation for
  * details.
  *
  * @return true if all tests pass, false otherwise
  */
 public static boolean testVoteExceptions() {
   ////////////////////////////////////////////////////////////////////////////////////////
   // we're doing the setup separately, so we can isolate the actual test later.
   // if anything fails HERE, that's a different problem than the one we're trying to test,
   // and the test should fail.
  
   Election election = null; // declare outside of the try block for scope reasons
   try {
     election = new Election("Sportsball", 10);
     Candidate c1 = new Candidate("lebron james", "basketball");
     Candidate c2 = new Candidate("messi", "soccer");
     election.addCandidate(c1); election.addCandidate(c2);
   } catch (Exception e) {
     System.out.println("Unable to continue with this test for unrelated reasons!!");
     e.printStackTrace();
     return false;
   }
   ////////////////////////////////////////////////////////////////////////////////////////
   // THIS part is what we are actually testing:
   try {
     election.vote(new Candidate("usain bolt", "athletics"));
     return false; // this line only runs if NO exception is thrown from the previous line
   } catch(NoSuchElementException e) {
     // this is correct
   } catch(Exception e) {
     // this only runs if an exception other than NoSuchElementException was thrown,
     // which is wrong!
     e.printStackTrace();
     return false;
   }
  
   // all tests pass:
   return true;
 }
  /**
  * Tests the Election's removeCandidate() method for correctness.
  * This test accounts for the fact a bad implementation may throw an Exception.
  *
  * @return true if all tests pass, false otherwise
  */
 public static boolean testRemoveCandidate() {
   try {
     Election e = new Election("Goat", 3);
     Candidate c1 = new Candidate("Lebron James", "Basketball");
     e.addCandidate(c1);
     e.removeCandidate(c1);
   } catch (IllegalArgumentException a) {
     return false;
   }
   try {
     Election e = new Election("Goat", 3);
     Candidate c1 = new Candidate("Lebron James", "Basketball");
     e.addCandidate(c1);
     e.removeCandidate(c1);
   } catch (IllegalStateException a) {
     return false;
   }
   return true;// TODO
 }
 /**
  * Verifies that the Election's removeCandidate() method throws the correct type of exception(s)
  * in situations where an exception is expected.
  *
  * @return true if all tests pass, false otherwise
  */
 public static boolean testRemoveCandidateExceptions() {
   boolean t1 = false;
   boolean t2 = false;
   try {
     Election e = new Election("Goat", 3);
     Candidate c1 = new Candidate("Lebron James", "Basketball");
     e.addCandidate(c1);
     e.removeCandidate(c1);
     e.removeCandidate(c1);
   } catch(IllegalStateException e) {
     t1 = true;
   }
   try {
     Election e = new Election("Goat", 2);
     Candidate c1 = new Candidate("Lebron James", "Basketball");
     Candidate c2 = new Candidate("Lionel Messi", "Soccer");
     e.addCandidate(c1);
     e.removeCandidate(c2);
   } catch(NoSuchElementException e) {
     t2 = true;
   }
  
   return t1&&t2; // TODO
 }
 /**
  * Tests the Ballot two-phase setup process in non-Exception situations.
  * This test accounts for the fact that a bad implementation may throw an Exception.
  *
  * @return true if all tests pass, false otherwise
  */
 public static boolean testBallotSetup() {
   // Phase 1: add elections to the Ballot class
   // Phase 2: create a Ballot and verify that it has the correct number of elections
   // (hint: use toString())
   Ballot.clearElections();
   try {
     Election e = new Election("President", 3);
     Candidate c = new Candidate("Lebron", "Basketball");
     e.addCandidate(c);
     Ballot.addElection(e);
     Ballot b = new Ballot();
   } catch(IllegalStateException e) {
     return false;
   }
   Ballot.clearElections();
   return true; // TODO
 }
 /**
  * Verifies that the Ballot two-phase setup process throws the correct type of exception(s)
  * in situations where an exception is expected. See the Ballot documentation for details.
  *
  * @return true if all tests pass, false otherwise
  */
 public static boolean testBallotSetupExceptions() {
   boolean t1 = false;
   boolean t2 = false;
   try {
     Ballot.clearElections();
     Ballot b = new Ballot();
     return false;
   } catch (IllegalStateException e) {
     t1 = true;
   }
   try {
     Ballot.clearElections();
     Election e = new Election("President", 1);
     Ballot.addElection(e);
     Ballot.addElection(e);
     return false;
   } catch (IllegalArgumentException e) {
     t2 = true;
   }
   Ballot.clearElections();
   return t1&&t2; // TODO
 }
 /**
  * Tests the Ballot vote() and hasVoted() methods in non-Exception situations.
  * This test accounts for the fact that a bad implementation may throw an Exception.
  *
  * @return true if all tests pass, false otherwise
  */
 public static boolean testBallotVote() {
   try {
     Ballot.clearElections();
     Election e = new Election("President", 2);
     Ballot.addElection(e);
     Ballot b = new Ballot();
     Candidate a = new Candidate("Lebron", "Basketball");
     e.addCandidate(a);
     b.vote("President", a);
     if(!b.hasVoted("President")) {
       return false;
     }
     if(a.getNumVotes()!=1) {
       return false;
     }
   } catch (NoSuchElementException e) {
     return false;
   }
   Ballot.clearElections();
   return true; // TODO
 }
 /**
  * Verifies that the Ballot vote() and hasVoted() methods throw the correct type of
  * exception(s) in situations where an exception is expected. See the Ballot documentation
  * for details.
  *
  * @return true if all tests pass, false otherwise
  */
 public static boolean testBallotVoteExceptions() {
   try {
   Election e = new Election("President", 2);
   Ballot.addElection(e);
   Ballot bal = new Ballot();
   Candidate a = new Candidate("Lebron", "Basketball");
   Candidate b = new Candidate("Jordan", "Basketball");
   e.addCandidate(a);
   bal.vote("President", b);
  
 } catch (NoSuchElementException e) {
   Ballot.clearElections();
   return true;
 }
 return false;
}
 /**
  * Runs all testing methods and prints out their results.
  * @return true if and only if all the tests return true, false otherwise
  */
 public static boolean runAllRequiredTests() {
  
   boolean test1 = testCandidateConstructorAndGetters();
   System.out.println("testCandidateConstructorAndGetters(): " + (test1 ? "PASS" : "FAIL"));
  
   boolean test2 = testCandidateConstructorExceptions();
   System.out.println("testCandidateConstructorExceptions(): " + (test2 ? "PASS" : "FAIL"));
   boolean test3 = testElectionConstructorAndGetters();
   System.out.println("testElectionConstructorAndGetters(): " + (test3 ? "PASS" : "FAIL"));
   boolean test4 = testElectionConstructorExceptions();
   System.out.println("testElectionConstructorExceptions(): " + (test4 ? "PASS" : "FAIL"));
   boolean test5 = testAddCandidate();
   System.out.println("testAddCandidate(): " + (test5 ? "PASS" : "FAIL"));
  
   boolean test6 = testAddCandidateExceptions();
   System.out.println("testAddCandidateExceptions(): " + (test6 ? "PASS" : "FAIL"));
   boolean test7 = testVote();
   System.out.println("testVote(): " + (test7 ? "PASS" : "FAIL"));
   boolean test8 = testVoteExceptions();
   System.out.println("testVoteExceptions(): " + (test8 ? "PASS" : "FAIL"));
   boolean test9 = testRemoveCandidate();
   System.out.println("testRemoveCandidate(): " + (test9 ? "PASS" : "FAIL"));
   boolean test10 = testRemoveCandidateExceptions();
   System.out.println("testRemoveCandidateExceptions(): " + (test10 ? "PASS" : "FAIL"));
   boolean test11 = testBallotSetup();
   System.out.println("testBallotSetup(): " + (test11 ? "PASS" : "FAIL"));
   boolean test12 = testBallotSetupExceptions();
   System.out.println("testBallotSetupExceptions(): " + (test12 ? "PASS" : "FAIL"));
   boolean test13 = testBallotVote();
   System.out.println("testBallotVote(): " + (test13 ? "PASS" : "FAIL"));
   boolean test14 = testBallotVoteExceptions();
   System.out.println("testBallotVoteExceptions(): " + (test14 ? "PASS" : "FAIL"));
   return test1 && test2 && test3 && test4 && test5 && test6 && test7 && test8 && test9 && test10
       && test11 && test12 && test13 && test14;
 }
 /**
  * Calls runAllRequiredTests and displays the output. If you add additional private testers, call
  * them directly in the main method rather than adding them to the previous method.
  * @param args unused
  */
 public static void main(String[] args) {
   System.out.println("runAllRequiredTests(): " + runAllRequiredTests());
 }
}