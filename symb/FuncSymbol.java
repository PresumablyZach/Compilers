package symb;

import java.util.*;

public class FuncSymbol extends Symbol {
    private List<IntSymbol> params;
    private List<IntSymbol> localDec;
    private String returnType;
    
    public FuncSymbol (String name, String returnType) { super(name); this.returnType = returnType; }
    
    public FuncSymbol (String name, int address, String returnType) { super(name, address); this.returnType = returnType; }
    
    @Override
    public String getType () { return "FUNC"; }
    
    public String getReturnType () { return this.returnType; }
    
    public List<IntSymbol> getParams () { return this.params; }
    
    public void addParam (IntSymbol sym) {
        /* Creates the parameter list if there isn't already one */
        if (this.params == null) {
            this.params = new ArrayList<>();
        }
        this.params.add(sym);
    }
    
    public List<IntSymbol> getLocalDec () { return localDec; }
    
    public void addLocalDec (IntSymbol sym) {
        /* Creates the local declaration list if there isn't already one */
        if (this.localDec == null) {
            this.localDec = new ArrayList<>();
        }
        this.localDec.add(sym);
    }
    
    @Override
    public String toString() {
        String out;
        String strParams;
        
        out = "Func: " + this.returnType.toLowerCase() + " " + this.getName();
        
        if (this.params == null)
            out.concat("(void)");
        else {
            strParams = " (";
            
            for (IntSymbol s : this.params) {
                strParams.concat(s.toString() + " ");
            }
            
            strParams.concat(")");
            strParams.replace(" )", ")");
            out.concat(strParams);
        }
        return out;
    }
    
}

























