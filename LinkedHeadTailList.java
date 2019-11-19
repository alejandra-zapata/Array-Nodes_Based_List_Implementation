
public class LinkedHeadTailList<T extends Comparable<? super T>> implements HeadTailListInterface<T>, Comparable<LinkedHeadTailList<T>> {

	private Node head, tail;
	
	public LinkedHeadTailList() {
		head = null;
		tail = null;
	}
   
	@Override
	public void addFront(T newEntry) {
		Node newNode = new Node(newEntry);
		
		if(isEmpty()) {
			addingToEmpty(newNode);
		}else {
			newNode.next = head;
			head = newNode;
		}	
	}

	@Override
	public void addBack(T newEntry) {
		Node newNode = new Node(newEntry);
		
		if(isEmpty()) {
			addingToEmpty(newNode);
		}else {
			tail.next = newNode;
			tail = newNode;
		}
		
	}
	
	private void addingToEmpty(Node newNode) {
		head = newNode;
		tail = newNode;
	}

	@Override
	public T removeFront() {
		T toRemove = null;
		
		if(!isEmpty()) {
			if(size() == 1) {
				tail = tail.next;
			}
			toRemove = head.data;
			head = head.next;
		}
		
		return toRemove;	
	}

	@Override
	public T removeBack() {
		T toRemove = null;
		
		if(!isEmpty()) {
			toRemove = tail.data;
			if(size() == 1) {
				head = head.next;
			}else {
				Node newTail = head;
				while(newTail.next != tail) {
					newTail = newTail.next;
				}
				newTail.next = null;
				tail = newTail;
			}
		}
		return toRemove;
	}

	@Override
	public void clear() {
		head = null;
		tail = null;
		
	}

	@Override
	public T getEntry(int givenPosition) {
		T entry = null;
		
		if(givenPosition > -1 && givenPosition < size()) {
			Node currentNode = head;
			int index = -1;
			while(currentNode != null) {
				index++;
				if(index == givenPosition) {
					entry = currentNode.data;
				}
				currentNode = currentNode.next;
			}
		}
		return entry;
	}
	
	@Override
	public void display() {
		if(!isEmpty()) {
			String data = "";
			String headAndTail = "head=" + head.data + " tail=" + tail.data;
			
			Node current = head;
			while(current != null) {
				data = data + current.data + ", ";
				current = current.next;
			}
			data = data.substring(0, data.length() - 2);
			System.out.println("[" + data + "] \t" + headAndTail);
		}else { //isEmpty()
			System.out.println("[]");
		}
	}
	
	@Override
	public int indexOf(T anEntry) {
		int indexFound = -1;
		int index = -1;
		Node currentNode = head;
		
		while(currentNode != null) {
			index++;
			if(currentNode.data.equals(anEntry)){
				indexFound = index;
				return indexFound;
			}
			currentNode = currentNode.next;
		}	
		return indexFound;
	}

	@Override
	public int lastIndexOf(T anEntry) {
		int indexFound = -1;
		int index = -1;
		Node currentNode = head;
		
		while(currentNode != null) {
			index++;
			if(currentNode.data.equals(anEntry)){
				indexFound = index;
			}
			currentNode = currentNode.next;
		}	
		return indexFound;
	}

	@Override
	public boolean contains(T anEntry) {
		Node currentNode = head;
		
		while(currentNode != null) {
			if(currentNode.data.equals(anEntry)) {
				return true;
			}
			currentNode = currentNode.next;
		}
		return false;
	}

	@Override
	public int size() {
		int counter = 0;
		Node currentNode = head;
		
		while(currentNode != null) {
			counter++;
			currentNode = currentNode.next;
		}		
		return counter;
	}

	@Override
	public boolean isEmpty() {
		return head==null;
	} 
	
	public int compareTo(LinkedHeadTailList<T> otherList) {
		Node thisCurrent = head;
		Node otherCurrent = otherList.head;
		
		Integer thisSize = size();
		Integer otherListSize = otherList.size();
		
		while(thisCurrent != null && otherCurrent != null) {
			int elementComparison = thisCurrent.data.compareTo(otherCurrent.data);
			
			if(elementComparison > 0) {
				return 1;
			}else if(elementComparison < 0) {
				return -1;
			}else {// elementComparison == 0;
				thisCurrent = thisCurrent.next;
				otherCurrent = otherCurrent.next;
			}
		}
		return Integer.compare(thisSize, otherListSize);
	}


	private class Node {
		private T data; 
		private Node next; 

		private Node(T dataPortion) {
			data = dataPortion;
			next = null;
		}

		private Node(T dataPortion, Node nextNode) {
			data = dataPortion;
			next = nextNode;
		}

		private T getData() {
			return data;
		}

		private void setData(T newData) {
			data = newData;
		}

		private Node getNextNode() {
			return next;
		}

		private void setNextNode(Node nextNode) {
			next = nextNode;
		} 
	}
}
