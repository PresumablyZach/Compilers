package absyn;

public class DecFun extends Dec {
	public Type type;
	public String id;
	public Params params;
	public StmtComp body;

	public DecFun (int pos, Type type, String id, Params params, StmtComp body) {
		this.pos = pos;
		this.type = type;
		this.id = id;
		this.params = params;
		this.body = body;
	}
}
