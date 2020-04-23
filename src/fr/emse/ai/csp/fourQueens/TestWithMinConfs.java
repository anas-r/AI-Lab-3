package fr.emse.ai.csp.fourQueens;

import fr.emse.ai.csp.core.Assignment;
import fr.emse.ai.csp.core.CSP;
import fr.emse.ai.csp.core.CSPStateListener;
import fr.emse.ai.csp.core.MinConflictsStrategy;
import fr.emse.ai.util.QueensDisplay;

public class TestWithMinConfs {
    public static void main(String[] args) {
        FourQueensCSP fq = new FourQueensCSP();
        MinConflictsStrategy mcs = new MinConflictsStrategy(100);
        mcs.addCSPStateListener(new CSPStateListener() {
            int counter = 0;
            @Override
            public void stateChanged(Assignment assignment, CSP csp) {
                System.out.println("Assignment evolved : " + assignment + " | assignment : "+ counter);
                counter++;
            }

            @Override
            public void stateChanged(CSP csp) {
                System.out.println("CSP evolved : " + csp);
            }
        });
        double start = System.currentTimeMillis();
        Assignment sol = mcs.solve(fq);
        double end = System.currentTimeMillis();
        System.out.println(sol);
        System.out.println("Time to solve = " + (end - start));
        System.out.println(new QueensDisplay(4, sol.toString()).display());
    }
}
