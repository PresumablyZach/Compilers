package absyn;

abstract public class Absyn {
	public int pos;

	final static int SPACES = 4;

	/* A function to create an indent of 4 spaces */
	static private void indent (int spaces) {
		for (int i = 0; i < spaces; i++) System.out.print(" ");
	}
	
	/* Recursive, overloaded functions to show the program's parse tree */

	/* Declaration-list tree */
	static public void showTree (DecList tree, int spaces) {
		while (tree != null) {
			showTree(tree.head, spaces);
			tree = tree.tail;
		}
	}

	/* Local-declarations tree */
	static public void showTree (DecLoc tree, int spaces) {
		while (tree != null) {
			showTree(tree.head, spaces);
			tree = tree.tail;
		}
	}

	/* Expression-list tree */
	static public void showTree (ExpList tree, int spaces) {
		while (tree != null) {
			showTree(tree.head, spaces);
			tree = tree.tail;
		}
	}

	/* Statement-list tree */
	static public void showTree (StmtList tree, int spaces) {
		while (tree != null) {
			showTree(tree.head, spaces);
			tree = tree.tail;
		}
	}

	/* Parameter-list tree */
	static public void showTree (ParamList tree, int spaces) {
		if (tree == null) {
			indent (spaces);
			System.out.println("VOID");
		}
		else {
			while (tree != null) {
				showTree(tree.head, spaces);
				tree = tree.tail;
			}
		}
	}
    
    /* Declaration type trees */
    static public void showTree (Dec tree, int spaces) {
        if (tree instanceof DecVar)
            showTree((DecVar)tree, spaces);
        else if (tree instanceof DecFun)
            showTree((DecFun)tree, spaces);
        else {
            indent(spaces);
            try {
                System.out.println("Illegal Declaration at line " + tree.pos);
            } catch (NullPointerException e) {
                System.out.println("Illegal Declaration (Line unknown)");
            }
        }
    }
    
    /* Statement type trees */
    static public void showTree (Stmt tree, int spaces) {
        if (tree instanceof StmtComp)
            showTree((StmtComp)tree, spaces);
        else if (tree instanceof StmtExp)
            showTree((StmtExp)tree, spaces);
        else if (tree instanceof StmtSel)
            showTree((StmtSel)tree, spaces);
        else if (tree instanceof StmtIter)
            showTree((StmtIter)tree, spaces);
        else if (tree instanceof StmtRet)
            showTree((StmtRet)tree, spaces);
        else {
            indent(spaces);
            try {
                System.out.println("Illegal Statement at line " + (tree.pos+1));
            } catch (NullPointerException e) {
                System.out.println("Illegal Statement (Line unknown)");
            }
        }
    }
    
    /* Expression Type trees */
    static public void showTree (Exp tree, int spaces) {
        if (tree instanceof ExpAssign)
            showTree((ExpAssign)tree, spaces);
        else if (tree instanceof ExpCall)
            showTree((ExpCall)tree, spaces);
        else if (tree instanceof ExpNum)
            showTree((ExpNum)tree, spaces);
        else if (tree instanceof AddOp)
            showTree((AddOp)tree, spaces);
        else if (tree instanceof MulOp)
            showTree((MulOp)tree, spaces);
        else if (tree instanceof RelOp)
            showTree((RelOp)tree, spaces);
        else if (tree instanceof ExpVar)
            showTree((ExpVar)tree, spaces);
        else {
            indent(spaces);
            try {
                System.out.println("Illegal Expression at line " + (tree.pos + 1));
            } catch (NullPointerException e) {
                System.out.println("Illegal Expression (Line unknown)");
            }
        }
    }

	/* Variable-declaration tree */
	static public void showTree (DecVar tree, int spaces) {
		if (tree == null)
			return;
		indent(spaces);
		if (tree.size == 0)
			System.out.println("VarDec: " + tree.type.type + " " + tree.id);
		else if (tree.size > 0)
			System.out.println("VarDec: " + tree.type.type + " " + tree.id + "[]");
	}

	/* Function-declaration tree */
	static public void showTree (DecFun tree, int spaces) {
		indent(spaces);
		System.out.println("FunDec: " + tree.type.type + " " + tree.id);
		spaces += SPACES;
		showTree(tree.params, spaces);
		showTree(tree.body, spaces);
	}

	/* Parameter tree */
	static public void showTree (Param tree, int spaces) {
		indent(spaces);
		if (tree.isArray)
			System.out.println("Param: " + tree.type.type + " " + tree.id + "[]");
		else
			System.out.println("Param: " + tree.type.type + " " + tree.id);
	}

	/* Compound-statement tree */
	static public void showTree(StmtComp tree, int spaces) {
		indent(spaces);
		System.out.println("CompStmt: ");
		spaces += SPACES;
		showTree(tree.local, spaces);
		showTree(tree.body, spaces);
	}

	/* Expression-statement tree */
	static public void showTree(StmtExp tree, int spaces) {
		indent(spaces);
		System.out.println("ExpStmt: ");
		spaces += SPACES;
		showTree(tree.expression, spaces);
		
	}

	/* Select-statement tree */
	static public void showTree(StmtSel tree, int spaces) {
		indent(spaces);
		System.out.println("SelStmt: ");
		spaces += SPACES;
		showTree(tree.condition, spaces);
		showTree(tree.thenpart, spaces);
		if (tree.elsepart != null)
			showTree(tree.elsepart, spaces);
	}

	/* Iterative-statement tree */
	static public void showTree(StmtIter tree, int spaces) {
		indent(spaces);
		System.out.println("IterStmt: ");
		spaces += SPACES;
		showTree(tree.condition, spaces);
		showTree(tree.body, spaces);
	}

	/* Return-statement tree */
	static public void showTree(StmtRet tree, int spaces) {
		indent(spaces);
		System.out.println("RetStmt: ");
		spaces += SPACES;
		if (tree.retExpression == null) {
			indent(spaces);
			System.out.println("No Return Expression");
		}
		else
			showTree(tree.retExpression, spaces);
	}

	/* Assign-expression tree */
	static private void showTree (ExpAssign tree, int spaces) {
		indent(spaces);
		System.out.println("AssignExp: ");
		spaces += SPACES;
		showTree(tree.var, spaces);
		showTree(tree.expression, spaces);
	}

	/* Call-expression tree */
	static private void showTree (ExpCall tree, int spaces) {
		indent(spaces);
		System.out.println("CallExp: " + tree.id);
		spaces += SPACES;
		if (tree.args != null)
			showTree(tree.args, spaces);
	}

	/* Num-expression tree */
	static private void showTree (ExpNum tree, int spaces) {
		indent(spaces);
		System.out.println("IntExp: " + tree.num);
	}

	/* Mul-Operation-expression tree */
	static private void showTree (MulOp tree, int spaces) {
		indent(spaces);
		System.out.println("MulOp: ");

		spaces += SPACES;
		showTree(tree.left, spaces);
		indent(spaces);

		System.out.print("Op: ");
		if (tree.operation == MulOp.MUL)
			System.out.println(" * ");
		else if (tree.operation == MulOp.DIV)
			System.out.println(" / ");
		else
			try {
				System.out.println("Illegal Operator at line " + (tree.pos + 1));
			} catch (NullPointerException e) {
				System.out.println("Illegal Operator (Line unknown)");
			}

		showTree(tree.right, spaces);
	}
    
    /* Add-Operation-expression tree */
    static private void showTree (AddOp tree, int spaces) {
        indent(spaces);
        System.out.println("AddOp: ");
        
        spaces += SPACES;
        showTree(tree.left, spaces);
        indent(spaces);
        
        System.out.print("Op: ");
        if (tree.operation == AddOp.PLUS)
            System.out.println(" + ");
        else if (tree.operation == AddOp.MINUS)
            System.out.println(" - ");
        else
            try {
                System.out.println("Illegal Operator at line " + (tree.pos + 1));
            } catch (NullPointerException e) {
                System.out.println("Illegal Operator (Line unknown)");
            }
        
        showTree(tree.right, spaces);
    }
    
    /* Rel-Operation-expression tree */
    static private void showTree (RelOp tree, int spaces) {
        indent(spaces);
        System.out.println("RelOp: ");
        
        spaces += SPACES;
        showTree(tree.left, spaces);
        indent(spaces);
        
        System.out.print("Op: ");
        if (tree.operation == RelOp.LT)
            System.out.println(" < ");
        else if (tree.operation == RelOp.GT)
            System.out.println(" > ");
        else if (tree.operation == RelOp.LTE)
            System.out.println(" <= ");
        else if (tree.operation == RelOp.GTE)
            System.out.println(" >= ");
        else if (tree.operation == RelOp.EQUAL)
            System.out.println(" == ");
        else if (tree.operation == RelOp.NOT)
            System.out.println(" != ");
        else
            try {
                System.out.println("Illegal Operator at line " + (tree.pos + 1));
            } catch (NullPointerException e) {
                System.out.println("Illegal Operator (Line unknown)");
            }
        
        showTree(tree.right, spaces);
    }

	/* Variable-expression tree */
	static private void showTree (ExpVar tree, int spaces) {
		indent(spaces);
		if (tree.expression != null) {
			System.out.println("VarExp: " + tree.id + "[]");
			spaces += SPACES;
			showTree(tree.expression, spaces);
		}
		else
			System.out.println("VarExp: " + tree.id);
	}

}



















