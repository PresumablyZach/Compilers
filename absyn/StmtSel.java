package absyn;

public class StmtSel extends Stmt {
	public Exp condition;
	public Stmt thenpart;
	public Stmt elsepart;

	public StmtSel (int pos, Exp condition, Stmt thenpart, Stmt elsepart) {
		this.pos = pos;
		this.condition = condition;
		this.thenpart = thenpart;
		this.elsepart = elsepart;
	}
}