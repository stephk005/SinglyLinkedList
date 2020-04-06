import java.util.NoSuchElementException;

public class SinglyLinkedList
{
	private ListNode first;

	public SinglyLinkedList()
	{
		first = null;
	}

	public Object getFirst()
	{
		if (first == null)
		{
			throw new NoSuchElementException();
		}
		else
		{
			return first.getValue();
		}
	}

	public ListNode getFirstNode() {
		return first;
	}

	public Object getLast() {
		int sz = 0;
		ListNode temp = first;
		while (sz < size() - 1)
		{
			temp = temp.getNext();
			sz++;
		}
		return temp.getValue();
	}

	//Just ListTester things
	public void addFirst(Object value)
	{
		first = new ListNode(value, first);
	}

	public void addLast(Object value) {
		if(size() == 0) {
			addFirst(value);
		}
		else {
			ListNode n = new ListNode(value);
			ListNode tmp;
			for(tmp = first ; tmp.getNext() != null ; tmp = tmp.getNext()) {}
			tmp.setNext(n);
		}
	}

	public void deleteIndex(int idx) {
		if(idx >= size()) {
			return;
		}
		if(idx == 0) {
			first = first.getNext();
			return;
		}
		int curr = 0;
		ListNode n;
		for(n = first ; curr<idx-1 ; curr++) {
			n = n.getNext();
		}
		n.setNext(n.getNext().getNext());
	}


	//darren antiorz antiorz
	//megan orz <3
	public void insert(Object value, int idx) {
		if(idx == 0) {
			addFirst(value);
		}
		else if(idx == size()) {
			addLast(value);
		}
		else if(idx > size()) {
			return;
		}
		else {
			ListNode n;
			int curr = 0;
			for(n = first ; curr<idx-1 ; curr++) {
				n = n.getNext();
			}

			ListNode ins = new ListNode(value, n.getNext());
			n.setNext(ins);
		}
	}

	public void deleteValue(Object value) {
		int idx = 0;
		for(ListNode n = first ; n != null ; idx++) {
			if(n.getValue().equals(value)) {
				deleteIndex(idx); idx--;
			}
			n = n.getNext();
		}
	}

	public void swap(int idx1, int idx2) {
		if(idx1 == idx2) return;
		if(idx1 > idx2) {
			int tmp = idx1;
			idx1 = idx2;
			idx2 = tmp;
		}
		ListNode n1 = first; ListNode n2 = first;
		for(int i=0 ; i<idx1-1 ; i++) {
			n1 = n1.getNext();
		}
		for(int i=0 ; i<idx2-1 ; i++) {
			n2 = n2.getNext();
		}
		if(idx1 == 0) {
			deleteIndex(0);
			addFirst(n2.getNext().getValue());
			ListNode nd1 = new ListNode(n1.getValue(), n2.getNext().getNext());
			n2.setNext(nd1);
		}
		else if(idx2-idx1 == 1) {
			ListNode nd1 = new ListNode(n1.getNext().getValue(), n2.getNext().getNext());
			ListNode nd2 = new ListNode(n2.getNext().getValue(), nd1);
			n2.setNext(nd1); n1.setNext(nd2);
		}
		else {
			ListNode nd1 = new ListNode(n1.getNext().getValue(), n2.getNext().getNext());
			ListNode nd2 = new ListNode(n2.getNext().getValue(), n1.getNext().getNext());
			n2.setNext(nd1); n1.setNext(nd2);
		}
	}

	public void reverse() {
		SinglyLinkedList ls = new SinglyLinkedList();
		for(ListNode n = first ; n != null ; n = n.getNext()) {
			ls.addFirst(n.getValue());
		}
		first = ls.first;
	}

	public void selectionSort() {
		SinglyLinkedList list = new SinglyLinkedList();
		int sz = size();
		for(int i=0 ; i<sz ; i++) {
			int lo = 105, itr = 0, idx = 0;
			for(ListNode n = first ; n != null ; n = n.getNext()) {
				if((int)n.getValue() < lo) {
					lo = (int)n.getValue();
					idx = itr;
				}
				itr++;
			}
			deleteIndex(idx);
			list.addLast(lo);
		}
		first = list.first;
	}

	public void printList()
	{
		for(int i=0 ; i<size() ; i++) {
			System.out.print(i + " ");
		}
		System.out.printf("%n");
		ListNode temp = first; // start at the first node
		while (temp != null)
		{
			System.out.print(temp.getValue() + " ");
			temp = temp.getNext(); // go to next node
		}
		System.out.printf("%nNumber of elements: %d%n", size());
	}

	public int size() {
		int sz = 0;
		ListNode temp = first;
		while (temp != null)
		{
			temp = temp.getNext();
			sz++;
		}
		return sz;
	}
}
