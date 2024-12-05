//////////////////////////// UNIT QUIZ FILE HEADER /////////////////////////////
// Full Name:   TODO
// Campus ID #: TODO
// WiscEmail:   TODO
////////////////////////////////////////////////////////////////////////////////

// Note: you are NOT required to use the Arrays class but it is provided for your convenience

////////////////////////////////////////////////////////////////////////////////
//
// This file contains ONE class. You will need to complete the implementation
// this class with respect to the provided requirements in the TODO tags for
// full credit.
//
// NO variables outside of any method may be added to this class.
//
// Any additional methods added to this class must be PRIVATE.
//
// You are NOT required to submit a perfect solution. Do your best to submit a
// source file with no compiler errors within the allotted time.
//
// Commenting and style are NOT graded.
//
// BE SURE TO SAVE YOUR SOURCE FILE BEFORE SUBMITTING IT TO GRADESCOPE.
//
////////////////////////////////////////////////////////////////////////////////

/**
 * This class contains TWO (2) utility methods for interacting with an ordered collection of 
 * characters representing a student's responses to a multiple choice question (MCQ) test.
 * 
 * We use a perfect-size array to store the answers, but in particular the array of CORRECT answers
 * should be compact. A student's responses may not be, for example if they skip a question.
 * 
 * The only permissible MCQ answers are: 'A','B','C', and 'D'
 * The value '\0' is the default value for a char variable and represents an uninitialized value.
 * 
 * You will be responsible for writing two (2) tester methods and the utility methods that they test.
 * The test methods are called in this class' main method; printed output will not be graded on this
 * program so you may add whatever print statements you like for debugging purposes.
 */
public class MCQAnswerUtility {
  
  ////// PART 1: Grading the test
  
  /**
   * Tester method that verifies the behavior of the percentCorrect method defined below.
   * 
   * @return true if the method behaves correctly, false otherwise
   */
  public static boolean testPercentCorrect() {
    // 1. TODO: given the following perfect-size array of correct answers, create TWO perfect-size
    //    arrays of the same length containing student answers:
    //    - one which answers all 10 questions correctly
    //    - one which contains at least one INCORRECT answer and one UNANSWERED question
    
    char[] correct = new char[] {'C', 'C', 'A', 'C', 'D', 'B', 'A', 'D', 'A', 'C'};
    
    char[] studentCorrect = new char[] {'C', 'C', 'A', 'C', 'D', 'B', 'A', 'D', 'A', 'C'};       // update to contain an array of length 10 with all correct answers
    char[] studentSomeIncorrect = new char[] {'B', '\0', 'A', 'C', 'D', 'B', 'A', 'D', 'A', 'C'}; // update to contain an array with at least 1 incorrect & 1 unanswered
    
    // 2. TODO: verify that percentCorrect() correctly returns 1.0 for the student who answered all
    //    questions correctly, and returns the correct value between 0.0 and 1.0 for the incorrect array
    if (percentCorrect(studentCorrect, correct) == 1.0) {
    	return true;
    }
    
    if (percentCorrect(studentSomeIncorrect, correct) == 0.8) {
    	return true;
    }
    
    return false; // default return statement, feel free to change.
  }
  
  /**
   * Calculates the percent (between 0.0 and 1.0, inclusive) of student answers which match the correct
   * answers provided. You may assume that both input arrays have the same length; both arrays MAY contain
   * uninitialized values.
   * 
   * The percent is calculated as:
   *     number of answers in studentAnswers which match correctAnswers / total number of questions
   * 
   * The only permissible MCQ answers are: 'A','B','C', and 'D'
   * The value '\0' is the default value for a char variable and represents an uninitialized value.
   * 
   * @param studentAnswers a perfect-size array of characters representing VALID MCQ answers or
   *   uninitialized values only, containing a student's responses
   * @param correctAnswers a perfect-size array of characters representing VALID MCQ answers or
   *   uninitialized values only, containing the correct answers
   * @return the percent of values in studentAnswers which match the values in correctAnswers, between
   *   0.0 (no matches) and 1.0 (all values match)
   */
  public static double percentCorrect(char[] studentAnswers, char[] correctAnswers) {
    // 3. TODO: complete the implementation of this method
	  
	double sum = 0;
    for (int i = 0; i < correctAnswers.length; i++) {
      if (correctAnswers[i] == studentAnswers[i]) {
    			sum++;
    	}
    }
    
    return sum / correctAnswers.length;
  }
  
  // Checkpoint: MAKE SURE TO SAVE YOUR SOURCE FILE (Ctrl/Cmd + S) 
  
  ////// PART 2: Compactness
  
  /**
   * Tester method that verifies the behavior of the isCompact() method defined below.
   * We strongly recommend implementing this FIRST to understand the intended behavior!
   * 
   * @return true if the method behaves correctly, false otherwise
   */
  public static boolean testIsCompact() {
    // 4. TODO: using these two perfect-size arrays of length 10, add permissible MCQ answer values such
    //    that the first array is compact and the second is not
    
    char[] compact = new char[] {'C', 'B', 'A', 'C', 'D', 'C', 'A', 'D', 'B', 'C'};    // update to contain a COMPACT array of length 10
    char[] notCompact = new char[] {'C', 'C', '\0', 'C', 'D', 'B', '\0', 'D', 'A', 'C'}; // update to contain a NON-COMPACT array of length 10
    
    // 5. TODO: verify that isCompact() correctly returns true for the compact array and false for the
    //    notCompact array
    if (isCompact(compact) == true) {
    	return true;
    }
    
    if (isCompact(notCompact) == false) {
    	return true;
    }
    
    return false; // default return statement, feel free to change.
  }
  
  /**
   * Verifies whether a provided array is considered to be COMPACT.
   * 
   * The only permissible MCQ answers are: 'A','B','C', and 'D'
   * The value '\0' is the default value for a char variable and represents an uninitialized value.
   * 
   * @param hand a perfect-size array of characters representing VALID MCQ answers or
   *   uninitialized values only
   * @return true if and only if the provided perfect-size array is compact, false otherwise
   */
  public static boolean isCompact(char[] answers) {
    // 6. TODO: complete the implementation of this method
    for (int i = 0; i < answers.length; i++) {
    	if (answers[i] == '\0') {
    		return false;
    	}
    }
    return true; // default return statement, feel free to change.
  }
  
  // Checkpoint: MAKE SURE TO SAVE YOUR SOURCE FILE (Ctrl/Cmd + S) 
  
  /**
   * PROVIDED: this method calls the tester methods above and displays their results to the console.
   * This method is not tested and may be modified as you wish.
   * 
   * @param args unused
   */
  public static void main(String[] args) {
    System.out.println("Correct check test: "+testPercentCorrect());
    System.out.println("Compactness test: "+testIsCompact());
  }

}
