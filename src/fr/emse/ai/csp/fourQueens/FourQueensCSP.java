package fr.emse.ai.csp.fourQueens;

import fr.emse.ai.csp.core.CSP;
import fr.emse.ai.csp.core.Domain;
import fr.emse.ai.csp.core.Variable;

public class FourQueensCSP extends CSP {
    public final static Variable Q1 = new Variable("Q1");
    public final static Variable Q2 = new Variable("Q2");
    public final static Variable Q3 = new Variable("Q3");
    public final static Variable Q4 = new Variable("Q4");

    public static Object[] DomainList = new Object[16];

    // filling the chess board in a iterative way, useful when scaling up to a nxn board
    public static void fillDomainList() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                DomainList[4 * i + j] = 4 * i + j;
            }
        }
    }

    ;

    public FourQueensCSP() {
        fillDomainList();

        addVariable(Q1);
        addVariable(Q2);
        addVariable(Q3);
        addVariable(Q4);

        addConstraint(new FourQueensConstraint(Q1, Q2));
        addConstraint(new FourQueensConstraint(Q1, Q3));
        addConstraint(new FourQueensConstraint(Q1, Q4));
        addConstraint(new FourQueensConstraint(Q2, Q3));
        addConstraint(new FourQueensConstraint(Q2, Q4));
        addConstraint(new FourQueensConstraint(Q3, Q4));


        Domain chessBoard = new Domain(DomainList);

        for (Variable var : getVariables()) {
            setDomain(var, chessBoard);
        }
    }

}