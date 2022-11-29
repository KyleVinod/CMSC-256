package cmsc256;

public class MinHeap <E extends Comparable<? super E>>{
    private E[] heap;
    private int size = 0;
    private static final int DEFAULT_CAPACITY = 8;
    @SuppressWarnings("unchecked")
    public MinHeap(int capacity){
        if(capacity > 0) {
            Object[] temp;              // create Object array due to type
            temp = new Comparable[capacity];   // type erasure
            heap = (E[]) temp;                // cast to generic type
        }
        else {
            throw new IllegalArgumentException("Array size must be greater than 0.");
        }
    }
    public MinHeap() {
        this(DEFAULT_CAPACITY);
    }
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
    private void expand() {
        @SuppressWarnings("unchecked")
        E[] newHeap = (E[]) new Comparable[heap.length * 2];
        for (int i = 0; i < size(); i++) {
            newHeap[i] = heap[i];
        }
        heap = newHeap;
    }
    private void swapElements(int index1, int index2) {
        int temp = index1;
        index1 = index2;
        index2 = temp;
    }
    private int getParentIndex(int childIndex) {
        // if odd, child is a left node
        if (childIndex % 2 != 0) {
            return childIndex / 2;
        }
        // if even, child is a right node
        else {
            return childIndex / 2 - 1;
        }
    }
    private int smallerChildIndex(int parentIndex) {
        int smaller = parentIndex;
        // get left child index by calculation based on parent index
        // look back to the binary heap notes if you need help with this formula
        int leftChild = 2 * parentIndex + 1;
        // if the left child index is in bounds of the heap...
        if (leftChild < size() - 1) {
            // set smaller to left if left is smaller than parent
            if(heap[leftChild].compareTo(heap[smaller]) < 0)
                smaller = leftChild;
            // get left child index by calculation based on parent index
            // look back to the binary heap notes if you need a reminder
            int rightChild = 2 * parentIndex + 2;        // get right child index
            // if the right child index is in bounds of the heap...
            if (rightChild < size() - 1) {
                // set smaller to right if right is smaller than parent
                if(heap[rightChild].compareTo(heap[smaller]) < 0)
                    smaller = rightChild;
            }
        }
        return smaller;
    }
    public void insert(E element) {
        int position = size();
        if (position == heap.length) {
            expand();
        }
        size++;
        heap[position] = element;
        int parentIndex = getParentIndex(position);
        while (position > 0 && heap[position].compareTo(heap[parentIndex]) < 0) {
            // if parent is greater, swap parent and node
            swapElements(parentIndex, position);
            // update position of the new element and find next parent up
            position = parentIndex;
            parentIndex = getParentIndex(position);
        }
    }
    public static void main(String[] args) {
        MinHeap<Integer> mh = new MinHeap<>();
        mh.insert(2);
        mh.insert(4);
        mh.insert(1);
        mh.insert(10);
        mh.insert(3);
        mh.insert(6);
        mh.insert(15);
        mh.insert(12);
        mh.insert(16);
        mh.insert(5);
        while(!mh.isEmpty())
            System.out.println(mh.remove());
        System.out.println();
    }
    public E remove() {
        if (isEmpty()) {
            return null;
        }
        // take out root and place last node at root position
        E min = heap[0];
        heap[0] = heap[size() - 1];
        heap[size() - 1] = null; // set the removed location to null
        size--;
        // position of new root and its smaller child
        int position = 0;
        int smallerChildIndex = smallerChildIndex(position) ;

        // while there is a smaller child, swap parent and child
        while (smallerChildIndex != position) {
            swapElements(position, smallerChildIndex);
            // update position of node and get new smaller child
            position = smallerChildIndex;
            smallerChildIndex = smallerChildIndex(position);
        }
        return min;
    }
}
