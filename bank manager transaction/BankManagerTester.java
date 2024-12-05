public class BankManagerTester {

    /**
     * Tests the constructor for the Transaction class.
     * 
     * @return true if the test passes
     */
    public static boolean testTransactionConstructor() {
		Account a = new Account(021706, 5000);
		Transaction t1 = new Transaction(a, 1000, Transaction.Type.DEPOSIT);
		Transaction t2 = new Transaction(a, 1500, Transaction.Type.WITHDRAWAL);
		Transaction t3 = new Transaction(a, 20000, Transaction.Type.LOAN_APPLICATION);

		if ((t1.getPriority() != Transaction.Priority.HIGH) && (t2.getPriority() != Transaction.Priority.NORMAL) && (t3.getPriority() != Transaction.Priority.URGENT)) {
			return false;
		}

		try {
			Transaction t4 = new Transaction(a, -1000, Transaction.Type.DEPOSIT);
			return false;
		} catch (IllegalArgumentException e) {
			;
		}

        return true;
    }

    /**
     * Tests the Transaction.compareTo for different priorities.
     * 
     * @return true if the test passes
     */
    public static boolean testTransactionCompareToPriority() {
		Account a1 = new Account(021706, 1000.0);
		Account a2 = new Account(110410, 500.0);
		Transaction t1 = new Transaction(a1, 1000000.0, Transaction.Type.LOAN_APPLICATION);
		Transaction t2 = new Transaction(a2, 500.0, Transaction.Type.DEPOSIT);

		try {
			Transaction t3 = new Transaction(a1, -100.0, Transaction.Type.WITHDRAWAL);
			t1.compareTo(t3);
			return false;
		} catch (IllegalArgumentException e) {
			;
		}
	
		return t1.compareTo(t2) < 0;
    }

    /**
     * Tests the Transaction.compareTo for same priority but different balances.
     * 
     * @return true if the test passes
     */
    public static boolean testTransactionCompareToAccountBalance() {
        Account a1 = new Account(021706, 5000);
        Account a2 = new Account(110410, 10000);
        Transaction t1 = new Transaction(a1, 1000, Transaction.Type.WITHDRAWAL);
        Transaction t2 = new Transaction(a2, 1000, Transaction.Type.WITHDRAWAL);

        return t1.compareTo(t2) < 0;
    }

    /**
     * Tests the TransactionHeap.addTransaction() method.
     * 
     * @return true if the test passes
     */
    public static boolean testAddTransactionToHeap() {
		TransactionHeap transactionHeap = new TransactionHeap(10);
		Account a = new Account(021706, 5000);
		Transaction t = new Transaction(a, 1000, Transaction.Type.DEPOSIT);

		transactionHeap.addTransaction(t);

		return transactionHeap.getSize() == 1 && transactionHeap.peek() == t;
    }

    /**
     * Tests the TransactionHeap.heapifyUp() and TransactionHeap.heapifyDown()
     * methods.
     * 
     * @return true if the test passes
     */
    public static boolean testHeapify() {
		TransactionHeap heap = new TransactionHeap(10);
		Account a1 = new Account(021706, 5000);
		Account a2 = new Account(110410, 3000);
		Transaction t1 = new Transaction(a1, 1000, Transaction.Type.DEPOSIT);
		Transaction t2 = new Transaction(a2, 500, Transaction.Type.WITHDRAWAL);

		heap.addTransaction(t2);
		heap.addTransaction(t1); 

		return heap.peek() == t1;
    }

    /**
     * Tests the BankManager.queueTransaction() method.
     * 
     * @return true if the test passes
     */
    public static boolean testQueueTransaction() {
		BankManager m = new BankManager(10);
		Account a = new Account(021706, 5000);
		Transaction t1 = new Transaction(a, 500, Transaction.Type.WITHDRAWAL);
		Transaction t2 = new Transaction(a, 1500, Transaction.Type.WITHDRAWAL);
		Transaction t3 = new Transaction(a, 1000000, Transaction.Type.DEPOSIT);

		m.queueTransaction(t1);
		m.queueTransaction(t2);
		m.queueTransaction(t3);

		return m.low.getSize() == 1 && m.medium.getSize() == 1 && m.high.getSize() == 1;
    }

    /**
     * Runs all tests.
     */
    public static void main(String[] args) {
        System.out.println("Transaction Constructor Tests: " + (testTransactionConstructor() ? "PASS" : "FAIL"));
        System.out.println("CompareTo Tests for Priority: " + (testTransactionCompareToPriority() ? "PASS" : "FAIL"));
        System.out.println(
                "CompareTo Tests for Account Balance: " + (testTransactionCompareToAccountBalance() ? "PASS" : "FAIL"));
        System.out.println("Testing Add Transaction to Heap: " + (testAddTransactionToHeap() ? "PASS" : "FAIL"));
        System.out.println("Testing Heapify: " + (testHeapify() ? "PASS" : "FAIL"));
        System.out.println("Testing Queue Transaction: " + (testQueueTransaction() ? "PASS" : "FAIL"));
    }
}
