package absyn;

public class DecLoc {
	public DecVar head;
	public DecLoc tail;

	public DecLoc (DecVar head, DecLoc tail) {
		this.head = head;
		this.tail = tail;
	}
}