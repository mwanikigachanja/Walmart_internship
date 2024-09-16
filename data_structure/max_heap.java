import java.util.ArrayList;

public class PowerOfTwoMaxHeap {
    private final int numChildren;  // 2^k children per node
    private final ArrayList<Integer> heap;

    public PowerOfTwoMaxHeap(int power) {
        this.numChildren = (int) Math.pow(2, power);  // Calculate number of children based on power
        this.heap = new ArrayList<>();
    }

    // Helper method to get parent index
    private int getParent(int index) {
        return (index - 1) / numChildren;
    }

    // Helper method to get child index
    private int getChild(int index, int childNum) {
        return numChildren * index + childNum + 1;
    }

    // Swap two elements in the heap
    private void swap(int i, int j) {
        int temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    // Insert a value into the heap
    public void insert(int value) {
        heap.add(value);  // Add the value to the end
        int index = heap.size() - 1;

        // Heapify up
        while (index > 0) {
            int parentIndex = getParent(index);
            if (heap.get(parentIndex) < heap.get(index)) {
                swap(parentIndex, index);
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    // Remove and return the max value (root)
    public int popMax() {
        if (heap.isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }

        int max = heap.get(0);
        int last = heap.remove(heap.size() - 1);

        if (!heap.isEmpty()) {
            heap.set(0, last);
            heapifyDown(0);
        }

        return max;
    }

    // Heapify down from a given index
    private void heapifyDown(int index) {
        int largest = index;

        // Iterate over each child
        for (int i = 0; i < numChildren; i++) {
            int childIndex = getChild(index, i);
            if (childIndex < heap.size() && heap.get(childIndex) > heap.get(largest)) {
                largest = childIndex;
            }
        }

        // If the largest child is greater, swap and continue heapifying down
        if (largest != index) {
            swap(index, largest);
            heapifyDown(largest);
        }
    }

    // Utility method to check if the heap is empty
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    // Example usage
    public static void main(String[] args) {
        PowerOfTwoMaxHeap heap = new PowerOfTwoMaxHeap(2);  // Creates a heap with 2^2 = 4 children per node
        heap.insert(10);
        heap.insert(20);
        heap.insert(30);
        heap.insert(40);

        System.out.println("Max: " + heap.popMax());  // Should print 40
        System.out.println("Max: " + heap.popMax());  // Should print 30
    }
}
