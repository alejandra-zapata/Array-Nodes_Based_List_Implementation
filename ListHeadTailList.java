/**
 * A class that implements the HeadTailListInterface interface.
 * The class uses ArrayList to implement the list.
 * 
 * @author Alejandra Zapata.
 */
import java.util.*;

public class ListHeadTailList<T> implements HeadTailListInterface<T> {
	
	private List<T> list;
	private boolean integrityOK;
	private static final int MAX_CAPACITY = 10000;
	
	//constructor:
	public ListHeadTailList(int initialCapacity) {
	    if(initialCapacity < MAX_CAPACITY) {
		    list = new ArrayList<>(initialCapacity);
		    integrityOK = true;
	    }else {
		    integrityOK = false;
		    checkCapacity(initialCapacity);
	    }
	}
	
	//private helper methods:
	private void checkCapacity(int capacity) {
		if(capacity > MAX_CAPACITY) {
			throw new IllegalStateException("Max capacity is 10000.");
		}
	}
	
	private void checkIntegrity() {
		if(!integrityOK) {
			throw new SecurityException("ArrayHeadTailList has not been instantiated.");
		}
	}
	
	@Override
	public void addFront(T newEntry) {
		checkIntegrity();
		list.add(0, newEntry);	
	}

	@Override
	public void addBack(T newEntry) {
		checkIntegrity();
		list.add(newEntry);
	}

	@Override
	public T removeFront() {
		checkIntegrity();

		if(!isEmpty()) {
			return list.remove(0);
		}
		return null;
	}

	@Override
	public T removeBack() {
		checkIntegrity();
		
		if(!isEmpty()) {
			return list.remove(list.size() - 1);
		}
		return null;
	}

	@Override
	public void clear() {
		checkIntegrity();
		list.clear();
		
	}

	@Override
	public T getEntry(int givenPosition) {
		checkIntegrity();
		
		if(givenPosition >= 0 && givenPosition < list.size()) {
			return list.get(givenPosition);
		}
		return null;
	}

	@Override
	public void display() {
		String s = list.size() + " elements; \t " + list;
		System.out.println(s);
		
	}

	@Override
	public int indexOf(T anEntry) {
		checkIntegrity();
		return list.indexOf(anEntry);
	}

	@Override
	public int lastIndexOf(T anEntry) {
		checkIntegrity();
		return list.lastIndexOf(anEntry);
	}

	@Override
	public boolean contains(T anEntry) {
		checkIntegrity();
		return list.contains(anEntry);
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public boolean isEmpty() {
		return list.size() == 0;
	}

}
