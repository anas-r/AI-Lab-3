package fr.emse.ai.csp.nQueens;

import fr.emse.ai.csp.core.CSP;
import fr.emse.ai.csp.core.Domain;
import fr.emse.ai.csp.core.Variable;

public class NQueensCSP extends CSP {

    private final Object[] DomainList;

    // filling the chess board in a iterative way, useful when scaling up to a nxn board
    public void fillDomainList(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                DomainList[n * i + j] = n * i + j;
            }
        }
    }

    public NQueensCSP(int n) {
        DomainList = new Object[n * n];
        fillDomainList(n);

        for (int i = 1; i <= n; i++) {
            addVariable(new Variable("Q" + i));
        }
    // adding constraints between Q_i and Q_j for j > i
        for (int i = 0; i < n - 1; i++) {
            Variable Qi = getVariables().get(i);
            for (int j = i + 1; j < n; j++) {
                addConstraint(new NQueensConstraint(Qi, getVariables().get(j), n));
            }
        }

        Domain nChessBoard = new Domain(DomainList);

        for (Variable var : getVariables()) {
            setDomain(var, nChessBoard);
        }
    }

}