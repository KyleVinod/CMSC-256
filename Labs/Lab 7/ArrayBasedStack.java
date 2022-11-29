package cmsc256;
import java.util.Arrays;
public class ArrayBasedStack<T> implements StackInterface {
    private T[] data;
    private int topOfStack;
    private static final int INITIAL_CAPACITY = 5;
    public ArrayBasedStack(int capacity) {
        if(capacity <= 0) {
            throw new IllegalArgumentException("Array initial size error.");
        }
        @SuppressWarnings("unchecked")
        T[] tempStack = (T[])new Object[capacity];
        data = tempStack;
        topOfStack = -1;
    }
    public ArrayBasedStack() {
        clear();
    }
    private void expandArray() {
        T[] newData = Arrays.copyOf(data, (data.length) * 2);
        data = newData;
    }
    private boolean isArrayFull() {
        if (topOfStack >= data.length - 1) {
            return true;
        }
        else {
            return false;
        }
    }
    @Override
    public void push(Object newEntry) {
        if (isArrayFull() == true) {
            expandArray();
        }
        else {
            data[++topOfStack] = (T) newEntry;
        }
    }

    @Override
    public Object pop() {
        if (isEmpty() == true) {
            throw new EmptyStackException();
        }
        T num = data[topOfStack--];
        return num;
    }

    @Override
    public Object peek() {
        if (isEmpty() == true) {
            throw new EmptyStackException();
        }
        T num = data[topOfStack];
        return num;
    }

    @Override
    public boolean isEmpty() {
        if (topOfStack < 0) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public void clear() {
        @SuppressWarnings("unchecked")
        T[] tempStack = (T[])new Object[INITIAL_CAPACITY];
        data = tempStack;
        topOfStack = -1;
    }
}
