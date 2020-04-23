package fr.emse.ai.csp.core;

public class BacktrackingStrategyMRVandDEG extends SolutionStrategy {

    private final static AC3Strategy AC3 = new AC3Strategy();

    public Assignment solve(CSP csp) {
        return recursiveBackTrackingSearch(csp, new Assignment());
    }

    /**
     * Template method, which can be configured by overriding the three
     * primitive operations below.
     */
    private Assignment recursiveBackTrackingSearch(CSP csp,
                                                   Assignment assignment) {
        Assignment result = null;
        if (assignment.isComplete(csp.getVariables())) {
            result = assignment;
        } else {
            Variable var = selectUnassignedVariable(assignment, csp);
            for (Object value : orderDomainValues(var, assignment, csp)) {
                assignment.setAssignment(var, value);
                fireStateChanged(assignment, csp);
                if (assignment.isConsistent(csp.getConstraints(var))) {
                    DomainRestoreInfo info = inference(var, assignment, csp);
                    if (!info.isEmpty())
                        fireStateChanged(csp);
                    if (!info.isEmptyDomainFound()) {
                        result = recursiveBackTrackingSearch(csp, assignment);
                        if (result != null)
                            break;
                    }
                    info.restoreDomains(csp);
                }
                assignment.removeAssignment(var);
            }
        }
        return result;
    }

    /**
     * Minimum remaining values + Degree heuristics for tie breaking
     */
    protected Variable selectUnassignedVariable(Assignment assignment, CSP csp) {
        int minCount = Integer.MAX_VALUE;
        int deg = Integer.MIN_VALUE;
        Variable selectedVar = null;
        for (Variable var : csp.getVariables()) {
            if (!(assignment.hasAssignmentFor(var))){
                int currCount = csp.getDomain(var).size();
                // updating the degree and the count;
                if (currCount < minCount){
                    minCount = currCount;
                    selectedVar = var;
                    deg = csp.getConstraints(var).size();
                }
                if (currCount == minCount) {
                    // if we have a tie, break it with degree heuristics and update the degree in case we have
                    // a tie for more variables to come
                    int currDeg = csp.getConstraints(var).size();
                    if (currDeg > deg) {
                        selectedVar = var;
                        deg = currDeg;
                    }
                }
            }
        }
        return selectedVar;
    }

    /**
     * Primitive operation, ordering the domain values of the specified
     * variable. This default implementation just takes the default order
     * provided by the CSP.
     */
    protected Iterable<?> orderDomainValues(Variable var, Assignment assignment, CSP csp) {
        return csp.getDomain(var);
    }

    /**
     * Primitive operation, which tries to prune out values from the CSP which
     * are not possible anymore when extending the given assignment to a
     * solution. This default implementation just leaves the original CSP as it
     * is.
     *
     * @return An object which provides informations about (1) whether changes
     * have been performed, (2) possibly inferred empty domains , and
     * (3) how to restore the domains.
     */

    protected DomainRestoreInfo inference(Variable var, Assignment assignment,
                                          CSP csp) {
        return AC3.reduceDomains(var,assignment.getAssignment(var),csp);
    }
}
