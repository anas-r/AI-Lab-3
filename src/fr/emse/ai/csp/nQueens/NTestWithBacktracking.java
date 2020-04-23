package fr.emse.ai.csp.nQueens;

import fr.emse.ai.csp.core.*;
import fr.emse.ai.util.QueensDisplay;

public class NTestWithBacktracking {
    public static void main(String[] args) {
        // chess board size
        int n = 15;
        NQueensCSP fq = new NQueensCSP(n);
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
        System.out.println("Time to solve = " + (end - start) + " ms");
        // displaying the solution with my custom builder (look util.QueensDisplay)
        System.out.println(new QueensDisplay(n,sol.toString()).display());

        /* NOTE ON EXERCISE 3:
        I've noticed that the Minimum Conflicts Strategy is much better than the naive approach of Backtracking.
        At n = 15,
        304095 assignments for Backtracking,
        and even with maxsteps = 200, I'm solving almost all n-queens problems (for as high as 15)

        Meanwhile, Backtracking is completely useless beyond n = 20. (on my computer)
         */

    }
}
