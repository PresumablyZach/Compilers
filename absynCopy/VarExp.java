package absyn;

public class VarExp extends Exp {
  public String name;
  public Exp e;
  public VarExp( int pos, String name, Exp e ) {
    this.pos = pos;
    this.name = name;
    this.e = e;
  }
}
