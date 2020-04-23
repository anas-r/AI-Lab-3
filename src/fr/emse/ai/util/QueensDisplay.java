package fr.emse.ai.util;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// my custom solution builder for an easy visualization of the solution (look at the queens problem for an example)
public class QueensDisplay {
    int n;
    String sol;

    public QueensDisplay(int n, String sol) {
        this.n = n;
        this.sol = sol;
    }

    public String display() {
        ArrayList<String> solutionAssignment = new ArrayList<>();
        Matcher m = Pattern.compile("\\{?Q[0-9]+=([0-9]+)[},]?").matcher(sol);
        while (m.find()) solutionAssignment.add(m.group(1));
        StringBuilder board = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (solutionAssignment.contains(n * i + j + "")) {
                    board.append("Q  ");
                } else {
                    board.append("_  ");
                }
            }
            board.append("\n");
        }
        return board.toString();
    }
}
