import java.io.PrintStream;
import java.util.NoSuchElementException;



public class StringStackImpl implements StringStack {
	public Node head = null; 
	public int size_counter = 0;
	public class Node {
		String keimeno;
		Node next;
		Node (String keimeno, Node next) {
			this.keimeno = keimeno;
			this.next = next;
		}
	}
	public boolean isEmpty () {
		return (head == null);
	}
	public void push (String keimeno) {
		head = new Node (keimeno, head);
		size_counter++;
	}
	public String pop () {
		if (isEmpty()) {throw new NoSuchElementException ("dokimasate pop se adeia stoiva \n");}
		else {
			String v = head.keimeno;					// to return the value of the node we pop out of the stack
			Node t = head.next;
			head = t; //head deixnei ston epomeno
			size_counter--;
			return v;
		}	
	}
	public String peek () {
		if (isEmpty()) {throw new NoSuchElementException ("dokimasate peek se adeia stoiva \n");}
		else {
			String u = head.keimeno;
			return u;
		}
	}

	public void printStack (PrintStream stream) {
		if (isEmpty()) {throw new NoSuchElementException ("h stoiva einai adeia \n");}
		else {
			while (head != null) {
				String data = head.keimeno;
				// try - catch kai edw
				stream.println(data);
				pop();
			}
		}
	}
	public int size() { return size_counter; }
}