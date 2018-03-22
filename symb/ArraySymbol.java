package symb;

public class ArraySymbol extends IntSymbol {
    private int size;
    
    public ArraySymbol (String name) { super(name); this.size = 0; }
    
    public ArraySymbol (String name, int size) { super(name); this.size = size; }
    
    public ArraySymbol (String name, int address, int size) { super(name, address); this.size = size; }
    
    public int getSize () { return this.size; }
    
    @Override
    public String toString() {
        String out;
        
        out = "Var: " + this.getType().toLowerCase() + " " + this.getName();
        
        if (this.size > 0)
            out.concat("[" + this.size + "]");
        else
            out.concat("[]");
        
        return out;
    }
}
