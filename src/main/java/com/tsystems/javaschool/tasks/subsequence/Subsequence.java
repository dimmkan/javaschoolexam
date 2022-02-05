package com.tsystems.javaschool.tasks.subsequence;

import java.util.List;

public class Subsequence {

    /**
     * Checks if it is possible to get a sequence which is equal to the first
     * one by removing some elements from the second one.
     *
     * @param x first sequence
     * @param y second sequence
     * @return <code>true</code> if possible, otherwise <code>false</code>
     */
    @SuppressWarnings("rawtypes")
    public <T> boolean find(List<T> x, List<T> y) {
        // TODO: Implement the logic here
        if(x == null || y == null){
            throw new IllegalArgumentException();
        }
        boolean allExist = true;
        int temp = 0;
        for (int j = 0; j < x.size(); j++) {
            if (allExist == true) {
                allExist = false;
                for (int i = temp; i < y.size(); i++) {
                    if (y.get(i).equals(x.get(j))) {
                        temp = i;
                        allExist = true;
                        break;
                    }
                }
            } else break;
        }
        return allExist;
    }
}

