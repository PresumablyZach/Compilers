package absyn;

public class StmtComp extends Stmt {
	public DecLoc local;
	public StmtList body;

	public StmtComp (int pos, DecLoc local, StmtList body) {
		this.pos = pos;
		this.local = local;
		this.body = body;
	}
}