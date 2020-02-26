package me.kerooker.itc;

import java.io.*;
import java.util.*;

public class AFD {


    private Integer[][] adjTransition;
    private Integer start;
    private Boolean[] accept;

    private AFD(Integer[][] transition, Integer st, Boolean[] acp) {
        adjTransition = transition;
        start = st;
        accept = new Boolean[adjTransition.length];
        System.arraycopy(acp, 0, accept, 0, accept.length);
    }

    public static void main(String args[]) throws IOException {
        AFD afd = readAfdFromFile(args[0]);

        afd = removeInaccessible(afd);


        afd = removeUselessStates(afd);
        afd = removeEqualStates(afd);

        FileWriter out = new FileWriter(args[1]);
        PrintWriter writer = new PrintWriter(out);
        writer.print(afd);
        out.close();

    }

    private static AFD readAfdFromFile(String arq) throws FileNotFoundException {
        Scanner sc = new Scanner(new FileInputStream(new File(arq)));

        Integer numberOfStates = sc.nextInt();

        Integer amountOfSymbols = sc.nextInt();

        Integer start = sc.nextInt();

        Integer[][] transit = new Integer[numberOfStates][amountOfSymbols];


        Boolean[] accepted = new Boolean[numberOfStates];
        for (int i = 0; i < numberOfStates; i++) {
            if (sc.nextInt() == 1) {
                accepted[i] = Boolean.TRUE;
            }else {
                accepted[i] = Boolean.FALSE;
            }
        }
        for (int i = 0; i < transit.length; i++) {
            for (int j = 0; j < transit[0].length; j++) {
                transit[i][j] = sc.nextInt();
            }
        }
        return new AFD(transit, start, accepted);
    }

    private static boolean[] doDfs(Integer st, AFD d) {
        boolean[] b = new boolean[d.cloneTransitions().length];
        recursiveDfs(b, st, d);
        return b;
    }

    private static void recursiveDfs(boolean[] b, Integer into, AFD d) {
        b[into] = true;
        Integer[][] matrix = d.cloneTransitions();
        for (int i = 0; i < matrix[0].length; i++) {
            if (!(matrix[into][i] < 0) && !b[matrix[into][i]]){
                recursiveDfs(b, matrix[into][i], d);
            }
        }
    }

    private static void equivalente(Boolean[] estadosFinais, Integer[][]matrix) {

        int eq = Constants.EQUIVALENTE;
        for (int i = 0; i < estadosFinais.length; i++) {
            for (int j = 0; j < estadosFinais.length; j++) {
                if (estadosFinais[i] == estadosFinais[j] && i != j) {
                    matrix[i][j] = matrix[j][i] = eq;
                }
            }
        }

         for (int i = 0; i < matrix.length; i++) {
            matrix[i][i] = -1;
        }
    }

    private static AFD removeUselessStates(AFD d) {
        ArrayList<Integer> removed = new ArrayList<>();

        Boolean[] b = d.cloneAccepted();
        for (int i = 0; i < b.length; i++) {
            if (!b[i]) {
                boolean[] visitors = doDfs(i, d);
                boolean rm = false;
                for (int j = 0; j < b.length; j++) {
                    if (b[j] && !visitors[j]) {
                        rm = false;
                    } else if (b[j]) {
                        rm = true;
                    }
                }
                if (!rm) {
                    removed.add(i);
                }
            }
        }

        removed.sort(Collections.reverseOrder());
        for (int gone : removed) {
            d.deleteState(gone);
        }
        return d;
    }

    private static AFD removeInaccessible(AFD d) {
        ArrayList<Boolean> dfsResult = new ArrayList<>();
        for (boolean bo :doDfs(d.getStart(), d)) {
            dfsResult.add(bo);
        }
        ArrayList<Integer> removed = new ArrayList<>();

        for (int i = 0; i < dfsResult.size(); i++) {
            if (!dfsResult.get(i))removed.add((Integer)i);
        }

        removed.sort(Collections.reverseOrder());

        removed.forEach(d::deleteState);

        return d;
    }

    private static AFD removeEqualStates(AFD afd) {
        Integer[][] trans;
        trans = afd.cloneTransitions();

        Boolean[] estadosFinais;
        estadosFinais = afd.cloneAccepted();

        Integer[][] matrix;

        int l = trans.length;
        matrix = new Integer[l][l];
        for (int i = 0; i < matrix[0].length; i++){
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = 0;
            }
        }

        equivalente(estadosFinais, matrix);


        int neq = Constants.NAO_EQUIVALENTE;
        int i =0;
        int j = 0;
        while (i < matrix.length) {
            while (j < matrix.length) {
                if (matrix[i][j] == Constants.EQUIVALENTE) {

                    int[] s1 = afd.getSymbols(i);

                    int[] s2 = afd.getSymbols(j);

                    for (int k = 0; k < s1.length; k++) {
                        if (s1[k] != s2[k]) {
                            matrix[i][j] = matrix[j][i] = neq;
                        }
                    }
                }
                j++;
            }
            j = 0;
            i++;
        }

        i = 0;
        j = 0;
        while (i < matrix.length) {
            while (j < matrix.length) {
                if (matrix[i][j] == Constants.EQUIVALENTE) {
                    for (int k = 0; k < trans[0].length; k++) {
                        if (!((trans[i][k] == trans[j][k]) || (matrix[trans[i][k]][trans[j][k]] == Constants.EQUIVALENTE && j != i))) {
                            matrix[i][j] = matrix[j][i] = neq;
                        }
                    }
                }

                j++;
            }
            j = 0;
            i++;
        }



        int ii = 0;
        List<Integer> classeEq = new ArrayList<>(trans.length);

        for (i = 0; i < trans.length; i++) {
            classeEq.add(-1);
        }
        for (i = 0; i < classeEq.size(); i++) {
            if (classeEq.get(i) == -1 || classeEq.get(i) == null) {
                ii++;
                classeEq.set(i, ii - 1);
                for (j = 0; j < matrix[0].length; j++) {
                    if (matrix[i][j] == 1) {
                        classeEq.set(j, classeEq.get(i));
                    } else {
                        classeEq.set(j, classeEq.get(j));
                    }
                }
            }
        }


        Integer[][] neew = new Integer[ii][trans[0].length];
        for (i = 0; i < neew.length; i++) {
            for (j = 0; j < neew[0].length; j++) {
                neew[i][j] = -1;
            }
        }


        for (i = 0; i < trans.length; i++) {
            for (j = 0; j < trans[0].length; j++) {
                if (neew[classeEq.get(i)][j] == -1) {
                    if (trans[i][j] != -1) {
                        neew[classeEq.get(i)][j] = classeEq.get(trans[i][j]);
                    }else {
                        neew[classeEq.get(i)][j] = -1;
                    }

                    neew[classeEq.get(i)][j] = trans[i][j] != -1 ? classeEq.get(trans[i][j]) : -1;
                }
            }
        }

        Boolean[] acceptArr = new Boolean[ii];

        Boolean[] ac = afd.cloneAccepted();

        for (i = 0; i < ac.length; i++) {
            acceptArr[classeEq.get(i)] = ac[i];
        }


        return new AFD(neew, classeEq.get(afd.getStart()), acceptArr);
    }

    @Override
    public String toString() {
        final String[] s = {""};
        s[0] += adjTransition.length;
        s[0] += " ";
        s[0] += adjTransition[0].length;
        s[0] += " ";
        s[0] += start;
        s[0] +="\n";
        if (accept[0]) {
            s[0] +="1";
        }else {
            s[0] +="0";
        }
        Arrays.asList(accept).subList(1, accept.length).forEach(p -> {
            if (p) {
                s[0] += " 1";
            } else {
                s[0] += " 0";
            }
        });

        s[0]+= "\n";

        Integer t[][] = adjTransition;
        for (int i = 0; i < t.length; i++) {
            for (int i1 = 0; i1 < t[i].length; i1++) {
                if (i1 == 0) {
                    s[0]+= t[i][i1];
                }else {
                    s[0] += " " + t[i][i1];
                }
            }

            if (i != t.length - 1) {
                s[0]+= "\n";
            }
        }
        return s[0];
    }

    private int getStart() {
        return start;
    }

    private Boolean[] cloneAccepted() {
        Boolean[] cloned = new Boolean[accept.length];
        System.arraycopy(accept, 0, cloned, 0, accept.length);

        return cloned;
    }

    private Integer[][] cloneTransitions() {
        return adjTransition.clone();
    }

    private void deleteState(int dead) {
        int t = 0;
        t+= dead;
        t+= 1;

        while (t < adjTransition.length) {
            System.arraycopy(adjTransition[t], 0, adjTransition[t - 1], 0, adjTransition[0].length);
            t++;
        }

        for (int i = 0; i < adjTransition.length; i++) {
            for (int j = 0; j < adjTransition[0].length; j++) {
                if (adjTransition[i][j] == dead) {
                    adjTransition[i][j] = -1;
                } else if (adjTransition[i][j] > dead) {
                    adjTransition[i][j]--;
                }
            }
        }


        Integer[][] iArr = new Integer[adjTransition.length - 1][adjTransition[0].length];

        for (int i = 0; i < iArr.length; i++) {
            System.arraycopy(adjTransition[i], 0, iArr[i], 0, iArr[0].length);
        }

        adjTransition = iArr.clone();


        System.arraycopy(accept, dead + 1, accept, dead + 1 - 1, accept.length - (dead + 1));

        Boolean[] acp = new Boolean[accept.length - 1];
        System.arraycopy(accept, 0, acp, 0, acp.length);

        accept = acp.clone();
        if (start > dead) {
            start--;
        }else {
            //Nothing
        }
    }

    private int[] getSymbols(int state) {
        int[] definition = new int[this.adjTransition[0].length];
        for (int i = 0; i < definition.length; i++) {
            definition[i] = -1;
        }
        for (int i = 0; i < adjTransition[state].length; i++) {
            if (adjTransition[state][i] != -1) {
                definition[i] = 1;
            }
        }

        return definition;
    }

    public static class Constants {
        static final int EQUIVALENTE = 1;
        static final int NAO_EQUIVALENTE = 0;
    }

}
