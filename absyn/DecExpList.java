package absyn;

public class DecExpList {
	public DecExp head;
	public DecExpList tail;

	public DecExpList (DecExp head, DecExpList tail) {
		this.head = head;
		this.tail = tail;
	}
}