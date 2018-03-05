package absyn;

public class Type extends Absyn {
	public String type;

	public Type (int pos, String type) {
		this.pos = pos;
		this.type = type;
	}
}