package absyn;

public class Type extends Absyn {
	public final static int INT = 0;
	public final static int VOID = 1;
	public String type;

	public Type (int pos, int type) {
		this.pos = pos;
		switch (type) {
			case INT:
				this.type = "INT";
				break;
			case VOID:
				this.type = "VOID";
				break;
			default:
				break;
		}
	}
}