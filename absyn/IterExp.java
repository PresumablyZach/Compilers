package absyn;

public class IterExp extends Exp {
  public ExpList exps;
  public Exp test;
  public IterExp( int pos, ExpList exps, Exp test ) {
    this.pos = pos;
    this.exps = exps;
    this.test = test;
  }
}
