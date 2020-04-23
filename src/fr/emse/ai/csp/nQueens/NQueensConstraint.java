package fr.emse.ai.csp.nQueens;

import fr.emse.ai.csp.core.Assignment;
import fr.emse.ai.csp.core.Constraint;
import fr.emse.ai.csp.core.Variable;

import java.util.ArrayList;
import java.util.List;

public class NQueensConstraint implements Constraint {
    int n;
    public Variable queen1;
    public Variable queen2;
    private final List<Variable> scope;

    public NQueensConstraint(Variable queen1, Variable queen2, int n) {
        this.n = n;
        this.queen1 = queen1;
        this.queen2 = queen2;
        scope = new ArrayList<Variable>(2);
        scope.add(queen1);
        scope.add(queen2);
    }

    @Override
    public List<Variable> getScope() {
        return scope;
    }

    @Override
    public boolean isSatisfiedWith(Assignment assignment) {
        Object queen1Value = assignment.getAssignment(queen1);
        if (queen1Value == null) return true;
        int queen1ValueX = (int) queen1Value / n;
        int row = Integer.parseInt(queen1.toString().substring(1))-1;
        if (row != queen1ValueX) return false;
        int queen1ValueY = (int) queen1Value % n;

        Object queen2Value = assignment.getAssignment(queen2);
        if (queen2Value == null) return true;
        int queen2ValueX = (int) queen2Value / n;
        row = Integer.parseInt(queen2.toString().substring(1))-1;
        if (row != queen2ValueX) return false;
        int queen2ValueY = (int) queen2Value % n;

        return !(queen1ValueX == queen2ValueX || queen1ValueY == queen2ValueY || Math.abs(queen1ValueX - queen2ValueX) == Math.abs(queen1ValueY - queen2ValueY));
    }
}
