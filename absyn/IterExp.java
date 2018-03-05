package absyn;

public class IterExp extends Exp {
  public Exp exps;
  public Exp test;
  public IterExp( int pos, Exp exps, Exp test ) {
    this.pos = pos;
    this.exps = exps;
    this.test = test;
  }
}
