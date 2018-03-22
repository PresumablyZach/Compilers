package absyn;

public class Param extends Absyn {
	public Type type;
	public String id;
	public boolean isArray;

	public Param (int pos, Type type, String id, boolean isArray) {
		this.pos = pos;
		this.type = type;
		this.id = id;
		this.isArray = isArray;
	}
}