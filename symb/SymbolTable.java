package symb;

import java.util.*;
import absyn.*;

public class SymbolTable {
    /* The table stack */
    private List<HashMap<List<String>, Symbol>> tableList = new ArrayList<>();

    /* Number of spaces to a tab */
    private final static int TAB = 4;

    /* Current function */
    private FuncSymbol currFunc;

    /* does the file have errors */
    public boolean hasError = false;

    /* Offsets */
    private int currentOffset = 0;
    private int globalOffset = 0;

    /* Constructor */
    public SymbolTable () { this.newScope(); }
    /* Pushes a new scope onto the stack */
    public void newScope() { this.tableList.add(0, new LinkedHashMap<>()); }

    /* Handles indentation */
    static private void indent (int spaces) { for (int i = 0; i < spaces; i++) System.out.print(" "); }

    /* Checks specific scopes */
    public boolean isInGlobalScope () { return this.tableList.size() == 1; }
    public boolean isInFuncScope () { return this.tableList.size() == 2; }

    /* Gets Offsets */
    public int getCurrentOffest () { return this.currentOffset; }
    public int getGlobalOffest () { return this.globalOffset; }

    /* Gets the current function */
    public FuncSymbol getCurrentFunction () { return this.currFunc; }

    /* Pops the current scope from the table stack */
    public void leaveScope() {
        if (this.tableList.size() <= 1)
            return;

        this.currentOffset += this.tableList.get(0).size();
        this.tableList.remove(0);

    }

    /* Add a symbol to the stack */
    public boolean addSymbol (Symbol s) {
        List<String> key;
        HashMap<List<String>, Symbol> top;

        if (s.getType().equals("FUNC") && this.tableList.size() != 1) {
            return false;
        }
        if (s.getType().equals("FUNC")) {
            this.currFunc = (FuncSymbol)s;
            this.currentOffset = 0;
        }

        key = Arrays.asList(s.getName(), s.getType());
        top = this.tableList.get(0);

        if (top == null)
            throw new RuntimeException();

        if (top.get(key) == null) {
          s.setScope(this.tableList.size());
          if (s.getClass().equals("INT")) s.setAddress(this.currentOffset);
          top.put(key, s);
          if (!s.getType().equals("FUNC"))
            this.currentOffset--;
        }
        else {
            return false;
        }

        return true;
    }

    /* Gets the matching symbol in the stack */
    public Symbol getMatchingSymbol (Symbol s) throws SymbolException {
        List<String> key = Arrays.asList(s.getName(), s.getType());

        for (HashMap<List<String>, Symbol> t : this.tableList) {
            if (t.get(key) != null) {
                try {
                    sameType(t.get(key), s);
                } catch (SymbolException e) {
                    throw e;
                }

                return t.get(key);
            }
        }
        return null;
    }

    /* Checks the symbol types against each other (declaration to use) */
    public void sameType (Symbol dec, Symbol use) throws SymbolException {
        if (dec == null || use == null || !dec.getName().equals(use.getName()) || !dec.getType().equals(use.getType()))
            throw new SymbolException("Use of undeclared variable!");

        if (dec.getType().equals("INT") && dec.getClass() != use.getClass()) {
            throw new SymbolException("Type mismatch!");
        }
    }

    /* Checks function parameters at declaration vs at use */
    public boolean haveMatchingParameters (FuncSymbol dec, FuncSymbol use) {
        if (dec.getParams() == null && use.getParams() == null)
            return true;
        if (dec.getParams() != null && use.getParams() != null) {
            /* Checks to make sure they have the same number of parameters */
            if (dec.getParams().size() != use.getParams().size())
                return false;

            Iterator<IntSymbol> i = dec.getParams().iterator();
            Iterator<IntSymbol> j = use.getParams().iterator();

            while (i.hasNext() && j.hasNext()) {
                if (i.next().getClass() != j.next().getClass())
                    return false;
            }
            return true;
        }

        return true;
    }

    /* Checks the function return type against its declaration */
    public void checkReturnType (ExpCall call) {
        Symbol sym = new FuncSymbol(call.id, 0, "INT");
        try {
            FuncSymbol checkSym = (FuncSymbol) this.getMatchingSymbol(sym);
            if (checkSym.getReturnType().equals("VOID"))
                this.error(checkSym.getName() + " of type VOID used in expression requiring type INT");
        } catch (Exception e) {}
    }

    /* Prints an error to stderr */
    public void error (String msg) {
        System.err.println("Error: " + msg);
        this.hasError = true;
    }

    /* Prints the scope */
    public void printScope (int spaces) {
        if (this.tableList.get(0).isEmpty()) {
            indent(spaces);
            System.out.println("No variable definitions found.");
        } else {
            for (Symbol sym : this.tableList.get(0).values()) {
                indent(spaces);
                System.out.println(sym);
            }
        }
    }

    /* Show table for Declaration List */
    public void showTable(DecList tree, int spaces) {
        while (tree != null) {
            showTable(tree.head, spaces);
            tree = tree.tail;
        }
        System.out.println("Global Scope:");
        spaces += TAB;
        this.printScope(spaces);
    }

    /* Show table for Local Declarations */
    public void showTable(DecLoc tree, int spaces) {
        while (tree != null) {
            showTable(tree.head, spaces);
            tree = tree.tail;
        }
    }

    /* Show table for Expression List */
    public void showTable(ExpList tree, int spaces, FuncSymbol fun) {
        while (tree != null) {
            if (tree.head instanceof ExpVar) {
                ExpVar v = (ExpVar) tree.head;

                if (v.expression != null) fun.addParam(new IntSymbol("arg", 0));
                else {
                    Symbol checkSym = null;
                    IntSymbol sym = new ArraySymbol(v.id, 0);

                    try {
                        checkSym = this.getMatchingSymbol(sym);
                    } catch (SymbolException e) {
                        /* Catch, But Do Nothing */
                    }
                    fun.addParam(sym);
                }
            }
            else fun.addParam(new IntSymbol("arg", 0));

            showTable(tree.head, spaces);
            tree = tree.tail;
        }
    }

    /* Show table for Statement List */
    public void showTable(StmtList tree, int spaces) {
        while (tree != null) {
            showTable(tree.head, spaces);
            tree = tree.tail;
        }
    }

    /* Show table for Declarations */
    public void showTable(Dec tree, int spaces) {
        if (tree instanceof DecVar)
            showTable((DecVar) tree, spaces);
        else if (tree instanceof DecFun)
            showTable((DecFun) tree, spaces);
    }

    /* Show table for Variable Declarations */
    public void showTable(DecVar tree, int spaces) {
        Symbol sym = new IntSymbol(tree.id);
        if (this.addSymbol(sym) == false) {
            indent(spaces);
            this.error("Variable redefinition.");
        }
    }

    /* Show table for Function Declarations */
    public void showTable(DecFun tree, int spaces) {
        spaces += TAB;
        indent(spaces);

        System.out.println("Entering Function (" + tree.id + ") :");
        Symbol sym = new FuncSymbol(tree.id, 0, tree.type.type);
        if (this.addSymbol(sym) == false) {
            indent(spaces);
            this.error("Function redefintion.");
        }
        this.newScope();
        spaces += TAB;
        showTable(tree.params, spaces);
        showTable(tree.body, spaces);
        this.printScope(spaces);
        this.leaveScope();
    }

    /* Show table for Parameters */
    public void showTable(Param tree, int spaces) {
        if (tree.isArray) {
            ArraySymbol arrSym = new ArraySymbol(tree.id);
            if (this.addSymbol(arrSym) == false) {
                indent(spaces);
                this.error("Parameter redefintion.");
            }
            else
                this.currFunc.addParam(arrSym);
        }
        else {
            IntSymbol sym = new IntSymbol(tree.id);
            if (this.addSymbol(sym) == false) {
                indent(spaces);
                this.error("Parameter redefintion.");
            }
            else
                this.currFunc.addParam(sym);
        }
    }

    /* Show table for Parameter Lists */
    public void showTable(ParamList tree, int spaces) {
        while (tree != null) {
            showTable(tree.head, spaces);
            tree = tree.tail;
        }
    }

    /* Show table for Statements */
    public void showTable(Stmt tree, int spaces) {
        if (tree instanceof StmtComp) {
            indent(spaces);
            System.out.println("Entering Block:");
            this.newScope();
            spaces += TAB;
            showTable((StmtComp)tree, spaces);
            this.printScope(spaces);
            this.leaveScope();
        }
        else if (tree instanceof StmtExp) showTable((StmtExp) tree, spaces);
        else if (tree instanceof StmtRet) showTable((StmtRet) tree, spaces);
        else if (tree instanceof StmtIter) showTable((StmtIter) tree, spaces);
        else if (tree instanceof StmtSel) showTable((StmtSel) tree, spaces);
        else {
            indent(spaces);
            this.error("Illegal statement.");
        }
    }

    /* Show table for Comparative Statements */
    public void showTable(StmtComp tree, int spaces) {
        showTable(tree.local, spaces);
        showTable(tree.body, spaces);
    }

    /* Show table for Expression Statements */
    public void showTable(StmtExp tree, int spaces) { showTable(tree.expression, spaces); }

    /* Show table for Selection Statement */
    public void showTable(StmtSel tree, int spaces) {
        if (tree.condition instanceof ExpCall) {
            ExpCall call = (ExpCall) tree.condition;
            Symbol sym = new FuncSymbol(call.id, 0, "INT");
            try {
                FuncSymbol checkSym = (FuncSymbol) this.getMatchingSymbol(sym);
                if (checkSym.getReturnType().equals("VOID")) {
                    indent(spaces);
                    this.error(checkSym.getName() + " of type VOID used in condition requiring INT.");
                }
            } catch (Exception e) { }
        }

        showTable(tree.condition, spaces);
        showTable(tree.thenpart, spaces);
        if (tree.elsepart != null) showTable(tree.elsepart, spaces);
    }

    /* Show table for Iterative Statements */
    public void showTable(StmtIter tree, int spaces) {
        if (tree.condition instanceof ExpCall) {
            ExpCall call = (ExpCall) tree.condition;
            Symbol sym = new FuncSymbol(call.id, 0, "INT");
            try {
                FuncSymbol checkSym = (FuncSymbol) this.getMatchingSymbol(sym);
                if (checkSym.getReturnType().equals("VOID")) {
                    indent(spaces);
                    this.error(checkSym.getName() + " of type VOID used in condition requiring INT.");
                }
            } catch (Exception e) { }
        }

        showTable(tree.condition, spaces);
        showTable(tree.body, spaces);
    }

    /* Show table for Return Statements */
    public void showTable(StmtRet tree, int spaces) {
        if (tree.retExpression != null) {
            if (!currFunc.getReturnType().equals("INT")) {
                indent(spaces);
                this.error("Invalid return type (Expected INT).");
            }
            showTable(tree.retExpression, spaces);
        }
        else {
            if (!currFunc.getReturnType().equals("VOID")) {
                indent(spaces);
                this.error("Invalid return type (Expected VOID).");
            }
        }
    }

    /* Show table for Expressions */
    public void showTable(Exp tree, int spaces) {
        if      (tree instanceof ExpAssign) showTable((ExpAssign) tree, spaces);
        else if (tree instanceof ExpCall)   showTable((ExpCall)   tree, spaces);
        else if (tree instanceof ExpVar)    showTable((ExpVar)    tree, spaces);
    }

    /* Show table for Assignment Expressions */
    public void showTable(ExpAssign tree, int spaces) {
        showTable(tree.var, spaces);

        if (tree.expression instanceof ExpCall) {
            ExpCall call = (ExpCall) tree.expression;
            Symbol sym = new FuncSymbol(call.id, 0, "INT");
            try {
                FuncSymbol checkSym = (FuncSymbol) this.getMatchingSymbol(sym);
                if (checkSym.getReturnType().equals("VOID")) {
                    indent(spaces);
                    this.error(checkSym.getName() + " of type VOID used in condition requiring INT.");
                }
            } catch (Exception e) { }
        }

        showTable(tree.expression, spaces);
    }

    /* Show table for Call Expressions */
    public void showTable(ExpCall tree, int spaces) {
        FuncSymbol sym = new FuncSymbol(tree.id, 0, null);
        FuncSymbol checkSym = null;

        try {
            checkSym = (FuncSymbol) getMatchingSymbol(sym);
        } catch (Exception e) {
            indent(spaces);
            System.err.println(e.getMessage());
        }
        if (tree.args != null) showTable(tree.args, spaces, sym);
        if (!haveMatchingParameters(checkSym, sym)) {
            indent(spaces);
            this.error("Arguments in call of function " + checkSym.getName() + " do not match the function defintion.");
        }
    }

    /* Show table for Variable Expressions */
    public void showTable(ExpVar tree, int spaces) {
        if (tree.expression == null) {
            Symbol sym = new IntSymbol(tree.id, 0);

            try {
                this.getMatchingSymbol(sym);
            } catch (Exception e) {
                indent(spaces);
                this.error(e.getMessage());
            }
        }
        else {
            Symbol arrSym = new ArraySymbol(tree.id, 0);

            try {
                Symbol checkSym = this.getMatchingSymbol(arrSym);
            }
            catch (Exception e) {
                indent(spaces);
                this.error(e.getMessage());
            }
        }

        if (tree.expression instanceof ExpCall) {
            ExpCall call = (ExpCall) tree.expression;
            Symbol funSym = new FuncSymbol(call.id, 0, "INT");
            try {
                FuncSymbol checkSym = (FuncSymbol) this.getMatchingSymbol(funSym);
                if (checkSym.getReturnType().equals("VOID")) {
                    indent(spaces);
                    this.error(checkSym.getName() + " of type VOID used in array indexing (expected INT)");
                }
            } catch (Exception e) {  }
        }
        showTable(tree.expression, spaces);
    }
}
