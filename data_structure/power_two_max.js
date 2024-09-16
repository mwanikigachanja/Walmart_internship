class PowerOfTwoMaxHeap {
    constructor(power) {
        this.power = power;  // 'power' represents 'k' in 2^k children
        this.children = 2 ** this.power;  // Calculate the number of children per node
        this.heap = [];
    }

    // Helper function to get the parent index
    getParent(index) {
        return Math.floor((index - 1) / this.children);
    }

    // Helper function to get the child index
    getChild(index, childNum) {
        return this.children * index + childNum + 1;
    }

    // Swap helper function
    swap(i, j) {
        [this.heap[i], this.heap[j]] = [this.heap[j], this.heap[i]];
    }

    // Insert method
    insert(value) {
        // Add the value to the end of the heap
        this.heap.push(value);
        let index = this.heap.length - 1;

        // Heapify up to maintain the heap property
        while (index > 0) {
            let parentIndex = this.getParent(index);
            if (this.heap[parentIndex] < this.heap[index]) {
                this.swap(parentIndex, index);
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    // Pop max method (removes the max value)
    popMax() {
        if (this.heap.length === 0) {
            throw new Error("Heap is empty");
        }

        const max = this.heap[0];
        const last = this.heap.pop();

        // If the heap isn't empty, move the last element to the root and heapify down
        if (this.heap.length > 0) {
            this.heap[0] = last;
            this.heapifyDown(0);
        }

        return max;
    }

    // Heapify down method to maintain the heap property
    heapifyDown(index) {
        let largest = index;

        // Iterate over each child
        for (let i = 0; i < this.children; i++) {
            let childIndex = this.getChild(index, i);

            // Check if the child exists and is larger than the current largest
            if (childIndex < this.heap.length && this.heap[childIndex] > this.heap[largest]) {
                largest = childIndex;
            }
        }

        // If a larger child is found, swap and continue heapifying down
        if (largest !== index) {
            this.swap(index, largest);
            this.heapifyDown(largest);
        }
    }
}

// Example usage:
const heap = new PowerOfTwoMaxHeap(2); // Creates a heap with 2^2 = 4 children per node
heap.insert(10);
heap.insert(20);
heap.insert(30);
heap.insert(40);

console.log("Max:", heap.popMax());  // Should print 40
console.log("Max:", heap.popMax());  // Should print 30
