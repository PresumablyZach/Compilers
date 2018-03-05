package absyn;

public class ReturnExp extends Exp {
  public Exp output;
  public ReturnExp( int pos, Exp output ) {
    this.pos = pos;
    this.output = output;
  }
}
