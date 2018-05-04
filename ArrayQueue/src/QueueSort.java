import java.util.*;
import java.io.*;

public class QueueSort<E extends Comparable<E>> {

	private ArrayQueue<ArrayQueue<E>> Q;
	public static final int CAPACITY = 10; // default queue capacity
	private int n;
	private boolean trace;

	public QueueSort() {
		this(CAPACITY);
	}

	public QueueSort(int capacity) {
		n = capacity;
		Q = new ArrayQueue<ArrayQueue<E>>(n);
	}

	private ArrayQueue<E> merge(ArrayQueue<E> q1, ArrayQueue<E> q2) throws ArrayQueueException {
		
		// Take two sorted queues and merge them to produce a third
		// sorted queue
		ArrayQueue<E> q3 = new ArrayQueue<>(q1.size() + q2.size());
		
		// Loop through both q1 and q2
		while (!q1.isEmpty() && !q2.isEmpty()) {
			// Get first element of both queues
			E a = q1.front();
			E b = q2.front();
			
			// If first queue value is less than second queue value
			if(a.compareTo(b) < 0) {
				// Insert first queue value
				q3.enqueue(q1.dequeue());		
			} else {
				// Insert second queue value
				q3.enqueue(q2.dequeue());
			}
		}
		
		// After the code above, it will know that
		// first queue values are always smaller than 
		// second queue values
		
		// Insert first queue remaining values
		while(!q1.isEmpty()) {
			q3.enqueue(q1.dequeue());
		}
		
		// Insert second queue remaining values
		while(!q2.isEmpty()) {
			q3.enqueue(q2.dequeue());
		}
		
		return q3;
	}

	public void sort() {
		// given a queue Q of queues
		// (1) if Q is of size 1 deliver the first queue in Q
		// (2) if Q is of size 2 or more
		// - get the first and second queues off Q
		// - merge these two queues to create a third queue
		// - add the third queue to the queue
		// - go back to (1)
		//
		while(Q.size() != 1) {
			ArrayQueue<E> q1 = Q.dequeue();
			ArrayQueue<E> q2 = Q.dequeue();
			ArrayQueue<E> tmp = merge(q1, q2);
			Q.enqueue(tmp);
		}
	}

	public void add(E element) {
		// create an ArrayQueue<E> that contains the element
		// enqueue it onto Q
		ArrayQueue<E> item = new ArrayQueue<>();
		item.enqueue(element);
		Q.enqueue(item);
	}

	public String toString() {
		return Q.toString();
	}

	public void trace() {
		trace = !trace;
	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new File(args[0]));
		ArrayList<String> data = new ArrayList<String>();
		while (sc.hasNext())
			data.add(sc.next());
		int n = data.size();
		QueueSort<String> QS = new QueueSort<String>(n);
		for (String s : data)
			QS.add(s);
		if (args.length > 1)
			QS.trace();
		QS.sort();
		System.out.println(QS);
	}
}


