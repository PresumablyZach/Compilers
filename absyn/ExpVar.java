package absyn;

public class ExpVar extends Exp {
	public String id;
	public Exp expression;

	public ExpVar (int pos, String id, Exp expression) {
		this.pos = pos;
		this.id = id;
		this.expression = expression;
	}
}