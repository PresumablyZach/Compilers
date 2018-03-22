package absyn;

public class MulOp extends Exp {
    public final static int MUL   = 0;
    public final static int DIV   = 1;
    
    public Exp left;
    public int operation;
    public Exp right;
    
    public MulOp (int pos, Exp left, int operation, Exp right) {
        this.pos = pos;
        this.left = left;
        this.operation = operation;
        this.right = right;
    }
    
}
