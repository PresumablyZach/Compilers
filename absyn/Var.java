package absyn;

public class Var extends Exp {
	public String id;
	public Exp expression;

	public Var (int pos, String id, Exp expression) {
		this.pos = pos;
		this.id = id;
		this.expression = expression;
	}
}