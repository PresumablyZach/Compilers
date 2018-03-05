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

	/* Variable-declaration tree */
	static public void showTree (DecVar tree, int spaces) {
		if (tree == null)
			return;
		indent(spaces);
		if (tree.size == 0)
			System.out.println("Variable-Declaration: " + tree.type.type + " " + tree.id);
		else if (tree.size > 0)
			System.out.println("Variable-Declaration: " + tree.type.type + " " + tree.id + "[]");
	}

	/* Function-declaration tree */
	static public void showTree (DecFun tree, int spaces) {
		indent(spaces);
		System.out.println("Function-Declaration: " + tree.type.type + " " + tree.id);
		spaces += SPACES;
		showTree(tree.params, spaces);
		showTree(tree.body, spaces);
	}

	/* Parameter tree */
	static public void showTree (Param tree, int spaces) {
		indent(spaces);
		if (tree.isArray)
			System.out.println("Parameter: " + tree.type.type + " " + tree.id + "[]");
		else
			System.out.println("Parameter: " + tree.type.type + " " + tree.id);
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

	/* Compound-statement tree */
	static public void showTree(StmtComp tree, int spaces) {
		indent(spaces);
		System.out.println("Compound-Statement: ");
		spaces += SPACES;
		showTree(tree.local, spaces);
		showTree(tree.body, spaces);
	}

	/* Expression-statement tree */
	static public void showTree(StmtExp tree, int spaces) {
		indent(spaces);
		System.out.println("Expression-Statement: ");
		spaces += SPACES;
		showTree(tree.expression, spaces);
		
	}

	/* Select-statement tree */
	static public void showTree(StmtSel tree, int spaces) {
		indent(spaces);
		System.out.println("Select-Statement: ");
		spaces += SPACES;
		showTree(tree.condition, spaces);
		showTree(tree.thenpart, spaces);
		if (tree.elsepart != null)
			showTree(tree.elsepart, spaces);
	}

	/* Iterative-statement tree */
	static public void showTree(StmtIter tree, int spaces) {
		indent(spaces);
		System.out.println("Iterative-Statement: ");
		spaces += SPACES;
		showTree(tree.condition, spaces);
		showTree(tree.body, spaces);
	}

	/* Return-statement tree */
	static public void showTree(StmtRet tree, int spaces) {
		indent(spaces);
		System.out.println("Return-Statement: ");
		spaces += SPACES;
		if (tree.retExpression == null) {
			indent(spaces);
			System.out.println("No Return Expression");
		}
		else
			showTree(tree.retExpression, spaces);
	}

	/* Expression Type trees */
	static public void showTree (Exp tree, int spaces) {
		if (tree instanceof ExpAssign)
			showTree((ExpAssign)tree, spaces);
		else if (tree instanceof ExpCall)
			showTree((ExpCall)tree, spaces);
		else if (tree instanceof ExpNum)
			showTree((ExpNum)tree, spaces);
		else if (tree instanceof ExpOp)
			showTree((ExpOp)tree, spaces);
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

	/* Assign-expression tree */
	static private void showTree (ExpAssign tree, int spaces) {
		indent(spaces);
		System.out.println("Assignment-Expression: ");
		spaces += SPACES;
		showTree(tree.var, spaces);
		showTree(tree.expression, spaces);
	}

	/* Call-expression tree */
	static private void showTree (ExpCall tree, int spaces) {
		indent(spaces);
		System.out.println("Call-Expression: " + tree.id);
		spaces += SPACES;
		if (tree.args != null)
			showTree(tree.args, spaces);
	}

	/* Num-expression tree */
	static private void showTree (ExpNum tree, int spaces) {
		indent(spaces);
		System.out.println("Integer-Expression: " + tree.num);
	}

	/* Operation-expression tree */
	static private void showTree (ExpOp tree, int spaces) {
		indent(spaces);
		System.out.println("Operation-Expression: ");

		spaces += SPACES;
		showTree(tree.left, spaces);
		indent(spaces);

		System.out.print("Operator: ");
		if (tree.operation == ExpOp.PLUS)
			System.out.println(" + ");
		else if (tree.operation == ExpOp.MINUS)
			System.out.println(" - ");
		else if (tree.operation == ExpOp.MUL)
			System.out.println(" * ");
		else if (tree.operation == ExpOp.DIV)
			System.out.println(" / ");
		else if (tree.operation == ExpOp.LT)
			System.out.println(" < ");
		else if (tree.operation == ExpOp.GT)
			System.out.println(" > ");
		else if (tree.operation == ExpOp.LTE)
			System.out.println(" <= ");
		else if (tree.operation == ExpOp.GTE)
			System.out.println(" >= ");
		else if (tree.operation == ExpOp.EQUAL)
			System.out.println(" == ");
		else if (tree.operation == ExpOp.NOT)
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
			System.out.println("Variable-Expression: " + tree.id + "[]");
			spaces += SPACES;
			showTree(tree.expression, spaces);
		}
		else
			System.out.println("Variable-Expression: " + tree.id);
	}

}



















