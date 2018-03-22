package absyn;

public class AddOp extends Exp {
    public final static int PLUS  = 0;
    public final static int MINUS = 1;
    
    public Exp left;
    public int operation;
    public Exp right;
    
    public AddOp (int pos, Exp left, int operation, Exp right) {
        this.pos = pos;
        this.left = left;
        this.operation = operation;
        this.right = right;
    }

}
