package absyn;

public class ExpOp extends Exp {
	public final static int PLUS  = 0;
	public final static int MINUS = 1;
	public final static int MUL   = 2;
	public final static int DIV   = 3;
	public final static int LT    = 4;
	public final static int GT    = 5;
	public final static int LTE   = 6;
	public final static int GTE   = 7;
	public final static int EQUAL = 8;
	public final static int NOT   = 9;

	public Exp left;
	public int operation;
	public Exp right;

	public ExpOp (int pos, Exp left, int operation, Exp right) {
		this.pos = pos;
		this.left = left;
		this.operation = operation;
		this.right = right;
	}
}