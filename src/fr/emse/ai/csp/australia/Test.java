package fr.emse.ai.csp.australia;

import fr.emse.ai.csp.core.*;

public class Test {
    public static void main(String[] args) {
        MapCSP map = new MapCSP();
        BacktrackingStrategyLCV bts = new BacktrackingStrategyLCV();
        bts.addCSPStateListener(new CSPStateListener() {
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
        Assignment sol = bts.solve(map);
        double end = System.currentTimeMillis();
        System.out.println(sol);
        System.out.println("Time to solve = " + (end - start));

        // A fun way to look at the map with ASCII art!
        char NT = ((String) sol.getAssignment(new Variable("NT"))).charAt(0);
        char Q = ((String) sol.getAssignment(new Variable("Q"))).charAt(0);
        char WA = ((String) sol.getAssignment(new Variable("WA"))).charAt(0);
        char SA = ((String) sol.getAssignment(new Variable("SA"))).charAt(0);
        char NSW = ((String) sol.getAssignment(new Variable("NSW"))).charAt(0);
        char V= ((String) sol.getAssignment(new Variable("V"))).charAt(0);
        char T = ((String) sol.getAssignment(new Variable("T"))).charAt(0);

        System.out.println(String.format(
                "                   _,__        .:\n" +
                "                  <*  /        | \\\n" +
                "               .-./   | |.     :  :,\n" +
                "              /       |   '-._/     \\_\n" +
                "             /        |      |'       \\\n" +
                "           .'         |  %s   |          \\\n" +
                "        .-'           |      |     %s    ;\n" +
                "        |      %s      |______|__         |\n" +
                "        \\             |        |_________/\n" +
                "         |            |   %s    |   %s    /\n" +
                "          \\*         _|.--._   |_______/\n" +
                "           \\     _.'         \\:|  %s    |\n" +
                "           >__,-'               \\_/*_.-'\n" +
                "                                          \n" +
                "                                :-----,\n" +
                "                                \\ %s /",NT,Q,WA,SA,NSW,V,T));
    }
}

