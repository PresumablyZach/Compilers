package absyn;

public class StmtIter extends Stmt {
	public Exp condition;
	public Stmt body;

	public StmtIter (int pos, Exp condition, Stmt body) {
		this.pos = pos;
		this.condition = condition;
		this.body = body;
	}
}