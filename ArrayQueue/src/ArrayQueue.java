import java.util.*;
import java.io.*;

public class ArrayQueue<E> {

    public static final int CAPACITY = 10;  // default queue capacity
    private E[] Q;                          // E array used to implement the queue  
    private int n;                          // actual capacity of queue
    private int front;                      // index for the top of queue
    private int rear;                       // rear of the queue
    private int size;                       // size of the queue
    
	
    public ArrayQueue() { this(CAPACITY); }

    @SuppressWarnings("unchecked")
    public ArrayQueue(int capacity) {
		n = capacity;
		Q = (E[]) new Object [n]; 
		front = rear = size = 0;
    }
    
    public int size() {	
    	return this.size;
	}
	 
    public boolean isEmpty() {
    	return size == 0;
	}

    public E front() throws ArrayQueueException {
    	if (isEmpty()) return null;
    	return Q[front];
    }
	
    public void enqueue(E element) throws ArrayQueueException {
    	if (size == n) {
    		throw new ArrayQueueException("enqueue: Reach the queue limit.");
    	}

        Q[rear] = element;
        rear = (rear + 1) % n;  // Circular Array
    	
        size++;
    }
    
    public E dequeue() throws ArrayQueueException {
    	if (isEmpty()) throw new ArrayQueueException("dequeue: Queue is empty; Nothing to dequeue.");
    	
    	E tmp = Q[front];
    	Q[front] = null;
    	
    	front = (front + 1) % n; // Circular Array
    	size--;
    	
    	return tmp;
    }
    
    public String toString() {
    	// Initialize StringBuilder with first character '['
    	StringBuilder builder = new StringBuilder("[");
    	int k = front;
    	for(int j = 0; j < size; j++) {
    		if (j > 0) {
    			builder.append(",");
    		}
    		builder.append(Q[k]);
    		
    		k = (k + 1) % n;
    	}
    	builder.append("]");
    	return builder.toString();
    }
    //
    // IMPLEMENT ME
    //
    //
    // NOTE: if the queue contains 1,2,3 then return "[1,2,3]"
    //       if the queue contains 1 then return "[1]"
    //       if the queue is empty return "[]"
    //
}
	
