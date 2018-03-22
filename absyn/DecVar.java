package absyn;

public class DecVar extends Dec {
	public Type type;
	public String id;
	public Boolean isArray;
	public int size;

	public DecVar (int pos, Type type, String id) {
		this.pos = pos;
		this.type = type;
		this.id = id;
		this.isArray = false;
		this.size = 0;
	}
	public DecVar (int pos, Type type, String id, int size) {
		this.pos = pos;
		this.type = type;
		this.id = id;
		this.isArray = true;
		this.size = size;
	}
}
