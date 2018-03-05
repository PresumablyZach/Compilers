package absyn;

public class Params extends Exp {
	public DecExpList list;

	public Params(int pos, DecExpList list) {
		this.pos = pos;
		this.list = list;
	}
}