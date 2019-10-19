/**
 * A class that implements the HeadTailListInterface interface.
 * The class uses Arrays to implement the list.
 * 
 * @author Alejandra Zapata.
 * 
 */

import java.util.Arrays;

public class ArrayHeadTailList<T> implements HeadTailListInterface<T> {
	
	private T[] listArray;
	private int numberOfElements;
	private boolean integrityOK;
	private static final int MAX_CAPACITY = 10000;
	
	//constructor:
	public ArrayHeadTailList(int initialCapacity) {
		if(initialCapacity < MAX_CAPACITY) {
			@SuppressWarnings("unchecked")
			T[] tempList = (T[])new Object[initialCapacity];
			listArray = tempList;
			numberOfElements = 0;
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
	
	private void doubleCapacity() {
		int newLength = listArray.length * 2;
		checkCapacity(newLength);
		listArray = Arrays.copyOf(listArray, newLength);
	}
	
	private boolean isArrayFull() {
		return numberOfElements >= listArray.length;
	}
	
    //inherited methods:
	@Override
	public void addFront(T newEntry) {
		checkIntegrity();
		if(isArrayFull()) {
			doubleCapacity();
		}
		for(int i = numberOfElements - 1; i >= 0; i--) {
			listArray[i +1] = listArray[i];
		}
		listArray[0] = newEntry;
		numberOfElements++;	
	}

	@Override
	public void addBack(T newEntry) {
		checkIntegrity();
		if(isArrayFull()) {
			doubleCapacity();
		}
		listArray[numberOfElements] = newEntry;
		numberOfElements++;
	}

	@Override
	public T removeFront() {
		checkIntegrity();
		
		if(!isEmpty()) {
			T elementToRemove = listArray[0];
			listArray[0] = null;
			
			for(int i = 0; i < numberOfElements -1; i++) {
				listArray[i] = listArray[i+1];
			}
			listArray[numberOfElements -1] = null;
			numberOfElements--;
			return elementToRemove;			
		}		
		return null;
	}

	@Override
	public T removeBack() {
		checkIntegrity();
		
		if(!isEmpty()) {
			T elementToRemove = listArray[numberOfElements - 1];
			listArray[numberOfElements - 1] = null;
			numberOfElements --;
			return elementToRemove;
		}

		return null;
	}

	@Override
	public void clear() {
		checkIntegrity();
		for(int i = 0 ; i < numberOfElements ; i ++) {
			listArray[i] = null;
		}
		numberOfElements = 0;
		
	}

	@Override
	public T getEntry(int givenPosition) {
		checkIntegrity();
		if(givenPosition >= 0 && givenPosition < numberOfElements) {
			return listArray[givenPosition];
		}
		return null;
	}

	@Override
	public void display() {
		//trimming array to size for printing
		@SuppressWarnings("unchecked")
		T[] trimmedArray = (T[]) new Object[numberOfElements];
		int trimmedLength = numberOfElements;
		trimmedArray = Arrays.copyOf(listArray, trimmedLength);
		
		String s = numberOfElements + " elements; capacity = " + listArray.length + "\t" + Arrays.toString(trimmedArray);
		System.out.println(s);
		
	}

	@Override
	public int indexOf(T anEntry) {
		checkIntegrity();
		int index = -1;
		
		if(!isEmpty()){
			for(int i = 0; i< numberOfElements; i++) {
				if(listArray[i].equals(anEntry)) {
					index = i;
					return index;
				}
			}
		}
		return index;
	}

	@Override
	public int lastIndexOf(T anEntry) {
		checkIntegrity();
		int index = -1;
		
		if(!isEmpty()) {
			for(int i = numberOfElements - 1; i >= 0; i--) {
				if(listArray[i].equals(anEntry)) {
					index = i;
					return index;
				}
			}
		}		
		return index;
	}

	@Override
	public boolean contains(T anEntry) {
		checkIntegrity();
		return indexOf(anEntry) > -1;
	}

	@Override
	public int size() {
		return numberOfElements;
	}

	@Override
	public boolean isEmpty() {
		return numberOfElements == 0;
	}
}
