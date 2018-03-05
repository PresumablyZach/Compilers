package absyn;

public class DecFun extends Exp {
	public Type type;
	public String name;
	public Params params;
	public Exp statement;

	public DecFun (int pos, Type type, String name, Params params, Exp statement) {
		this.pos = pos;
		this.type = type;
		this.name = name;
		this.params = params;
		this.statement = statement;
	}
}