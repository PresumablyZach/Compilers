package symb;

public abstract class Symbol {
    private String name;
    private int address;
    private int scope = -1;
    
    public Symbol (String name) { this.name = name; this.address = 0; }
    
    public Symbol (String name, int address) { this.name = name; this.address = address; }
    
    public void setScope (int scope) { this.scope = scope; }
    
    public int getScope () { return this.scope; }
    
    public boolean isGlobal () { return this.scope == 1; }
    
    public String getName () { return this.name; }
    
    public void setAddress (int address) { this.address = address; }
    
    public int getAddress () { return this.address; }
    
    abstract public String getType ();
}
