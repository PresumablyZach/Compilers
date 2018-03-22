package absyn;

public class StmtExp extends Stmt {
	public Exp expression;

	public StmtExp (int pos, Exp expression) {
		this.pos = pos;
		this.expression = expression;
	}
}