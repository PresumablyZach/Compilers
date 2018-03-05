package absyn;

public class StmtRet extends Stmt {
	public Exp retExpression;

	public StmtRet (int pos, Exp retExpression) {
		this.pos = pos;
		this.retExpression = retExpression;
	}
}