package absyn;

public class RelOp extends Exp {
    public final static int LT    = 0;
    public final static int GT    = 1;
    public final static int LTE   = 2;
    public final static int GTE   = 3;
    public final static int EQUAL = 4;
    public final static int NOT   = 5;
    
    public Exp left;
    public int operation;
    public Exp right;
    
    public RelOp (int pos, Exp left, int operation, Exp right) {
        this.pos = pos;
        this.left = left;
        this.operation = operation;
        this.right = right;
    }
}
