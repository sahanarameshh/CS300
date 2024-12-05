import java.util.NoSuchElementException;

public class TransactionHeap {
    private Transaction[] transactions;
    private int size;

    public TransactionHeap(int capacity) {
        transactions = new Transaction[capacity];
        size = 0;
    }

    public void addTransaction(Transaction transaction) {
        if (size == transactions.length) {
            throw new IllegalStateException("TransactionHeap is full");
        }
        transactions[size] = transaction;
        heapifyUp(size);
        size++;
    }

    public Transaction getNextTransaction() {
        if (size == 0) {
            throw new NoSuchElementException("Heap is empty");
        }
        Transaction root = transactions[0];
        transactions[0] = transactions[size - 1];
        transactions[size - 1] = null;
        size--;

        if (size > 0) {
            heapifyDown(0);
        }
        return root;
    }

    public void heapifyUp(int index) {
        int parent = (index - 1) / 2;
        while (index > 0 && transactions[index].compareTo(transactions[parent]) > 0) {
            swap(index, parent);
            index = parent;
            parent = (index - 1) / 2;
        }
    }

    public void heapifyDown(int index) {
        while (true) {
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            int largest = index;

            if (left < size && transactions[left].compareTo(transactions[largest]) > 0) {
                largest = left;
            }
            if (right < size && transactions[right].compareTo(transactions[largest]) > 0) {
                largest = right;
            }

            if (largest != index) {
                swap(index, largest);
                index = largest;
            } else {
                break;
            }
        }
    }

    public Transaction peek() {
        if (size == 0) {
            throw new NoSuchElementException("Heap is empty");
        }
        return transactions[0];
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Transaction[] getHeapData() {
        Transaction[] list = new Transaction[this.transactions.length];
        for (int i = 0; i < list.length; i++) {
            list[i] = this.transactions[i];
        }
        return list;
    }

    private void swap(int index1, int index2) {
        Transaction temp = transactions[index1];
        transactions[index1] = transactions[index2];
        transactions[index2] = temp;
    }
}