package absyn;

public class DecVar extends Dec {
	public Type type;
	public String id;
	public int size;

	public DecVar (int pos, Type type, String id) {
		this.pos = pos;
		this.type = type;
		this.id = id;
		this.size = 0;
	}
	public DecVar (int pos, Type type, String id, int size) {
		this.pos = pos;
		this.type = type;
		this.id = id;
		this.size = size;
	}
}