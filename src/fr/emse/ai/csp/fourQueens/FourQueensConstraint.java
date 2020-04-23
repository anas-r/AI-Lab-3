package fr.emse.ai.csp.fourQueens;

import fr.emse.ai.csp.core.Assignment;
import fr.emse.ai.csp.core.Constraint;
import fr.emse.ai.csp.core.Variable;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class FourQueensConstraint implements Constraint {
    public Variable queen1;
    public Variable queen2;
    private final List<Variable> scope;

    public FourQueensConstraint(Variable queen1, Variable queen2) {
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
        int queen1ValueX = (int)queen1Value/4;
        int queen1ValueY = (int)queen1Value % 4;

        Object queen2Value = assignment.getAssignment(queen2);
        if (queen2Value == null) return true;
        int queen2ValueX = (int)queen2Value/4;
        int queen2ValueY = (int)queen2Value % 4;

        return !(queen1ValueX==queen2ValueX || queen1ValueY==queen2ValueY || Math.abs(queen1ValueX-queen2ValueX) == Math.abs(queen1ValueY-queen2ValueY));
    }

}
