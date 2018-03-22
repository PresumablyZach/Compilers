package absyn;

public class ExpAssign extends Exp {
	public ExpVar var;
	public Exp expression;

	public ExpAssign (int pos, ExpVar var, Exp expression) {
		this.pos = pos;
		this.var = var;
		this.expression = expression;
	}
}