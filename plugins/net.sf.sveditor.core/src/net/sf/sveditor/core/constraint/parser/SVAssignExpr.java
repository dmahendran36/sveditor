package net.sf.sveditor.core.constraint.parser;

public class SVAssignExpr extends SVExpr {
	private SVExpr					fLhs;
	private String					fOp;
	private SVExpr					fRhs;
	
	public SVAssignExpr(
			SVExpr			lhs,
			String			op,
			SVExpr			rhs) {
		fLhs = lhs;
		fOp  = op;
		fRhs = rhs;
	}

}