package fr.emse.ai.csp.nQueens;

import fr.emse.ai.csp.core.Assignment;
import fr.emse.ai.csp.core.CSP;
import fr.emse.ai.csp.core.CSPStateListener;
import fr.emse.ai.csp.core.MinConflictsStrategy;
import fr.emse.ai.csp.fourQueens.FourQueensCSP;
import fr.emse.ai.util.QueensDisplay;

public class NTestWithMinConfs {
    public static void main(String[] args) {
        // chess board size
        int n = 15;
        NQueensCSP fq = new NQueensCSP(n);
        MinConflictsStrategy mcs = new MinConflictsStrategy(200);
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
        System.out.println(new QueensDisplay(n, sol.toString()).display());

        /* NOTE ON EXERCISE 3:
        I've noticed that the Minimum Conflicts Strategy is much better than the naive approach of Backtracking.
        At n = 15,
        304095 assignments for Backtracking,
        and even with maxsteps = 200, I'm solving almost all n-queens problems (for as high as 15)

        Meanwhile, Backtracking is completely useless beyond n = 20. (on my computer)
         */

    }
}
