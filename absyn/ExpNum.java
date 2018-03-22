package absyn;

public class ExpNum extends Exp {
	public int num;

	public ExpNum (int pos, int num) {
		this.pos = pos;
		this.num = num;
	}
}