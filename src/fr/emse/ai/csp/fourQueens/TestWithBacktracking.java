package fr.emse.ai.csp.fourQueens;

import fr.emse.ai.csp.core.Assignment;
import fr.emse.ai.csp.core.BacktrackingStrategy;
import fr.emse.ai.csp.core.CSP;
import fr.emse.ai.csp.core.CSPStateListener;
import fr.emse.ai.util.QueensDisplay;

public class TestWithBacktracking {
    public static void main(String[] args) {
        FourQueensCSP fq = new FourQueensCSP();
        BacktrackingStrategy btsQ = new BacktrackingStrategy();
        btsQ.addCSPStateListener(new CSPStateListener() {
            int counter = 0;
            @Override
            public void stateChanged(Assignment assignment, CSP csp) {
                counter++;
                System.out.println("Assignment evolved : " + assignment + " | assignment : "+ counter);
            }

            @Override
            public void stateChanged(CSP csp) {
                System.out.println("CSP evolved : " + csp);
            }
        });
        double start = System.currentTimeMillis();
        Assignment sol = btsQ.solve(fq);
        double end = System.currentTimeMillis();
        System.out.println(sol);
        System.out.println("Time to solve = " + (end - start));
        System.out.println(new QueensDisplay(4, sol.toString()).display());
    }
}
