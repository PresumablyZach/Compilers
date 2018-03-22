package absyn;

public class DecList extends Dec {
	public Dec head;
	public DecList tail;

	public DecList (Dec head, DecList tail) {
		this.head = head;
		this.tail = tail;
	}
}