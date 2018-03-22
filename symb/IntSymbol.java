package symb;

public class IntSymbol extends Symbol {
    
    public IntSymbol (String name) { super(name); }
    
    public IntSymbol (String name, int address) { super(name, address); }
    
    @Override
    public String getType() { return "INT"; }
    
    @Override
    public String toString() { String out = "Var: int " + this.getName(); return out; }
}
