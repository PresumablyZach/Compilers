package absyn;

public class DecExp extends Exp {
	public Type type;
	public String name;
	public int size;

	public DecExp (int pos, Type type, String name, int size) {
		this.pos = pos;
		this.type = type;
		this.name = name;
		this.size = size;
	}
	public DecExp (int pos, Type type, String name) {
		this.pos = pos;
		this.type = type;
		this.name = name;
		this.size = 0;
	}
}