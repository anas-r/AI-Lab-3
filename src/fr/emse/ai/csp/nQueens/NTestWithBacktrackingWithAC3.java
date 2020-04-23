package fr.emse.ai.csp.nQueens;

import fr.emse.ai.csp.core.*;
import fr.emse.ai.util.QueensDisplay;

public class NTestWithBacktrackingWithAC3 {
    public static void main(String[] args) {
        int n = 15;
        NQueensCSP fq = new NQueensCSP(n);
        BacktrackingStrategyAC3 btsQ = new BacktrackingStrategyAC3();
        btsQ.addCSPStateListener(new CSPStateListener() {
            int counter = 0;

            @Override
            public void stateChanged(Assignment assignment, CSP csp) {
                counter++;
                System.out.println("Assignment evolved : " + assignment + " | assignment : " + counter);
            }

            @Override
            public void stateChanged(CSP csp) {
                // I've changed the listener to give the updated domains for each variable
                StringBuilder newDomainsCSP = new StringBuilder();
                newDomainsCSP.append("CSP evolved : ");
                for (Variable var : csp.getVariables()) {
                    newDomainsCSP.append(var).append(csp.getDomain(var)).append(" ");
                }
                System.out.println(newDomainsCSP);

            }
        });
        double start = System.currentTimeMillis();
        Assignment sol = btsQ.solve(fq);
        double end = System.currentTimeMillis();
        System.out.println(sol);
        System.out.println("Time to solve = " + (end - start) + " ms");
        // displaying the solution with my custom builder (look util.QueensDisplay)
        System.out.println(new QueensDisplay(n, sol.toString()).display());

        // Again, significant improvements. for n = 15: 300000+ to 207 assignments!
    }
}
