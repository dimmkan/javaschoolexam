package com.tsystems.javaschool.tasks.pyramid;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PyramidBuilder {

    /**
     * Builds a pyramid with sorted values (with minumum value at the top line and maximum at the bottom,
     * from left to right). All vacant positions in the array are zeros.
     *
     * @param inputNumbers to be used in the pyramid
     * @return 2d array with pyramid inside
     * @throws {@link CannotBuildPyramidException} if the pyramid cannot be build with given input
     */
    public int[][] buildPyramid(List<Integer> inputNumbers) {
        // TODO : Implement your solution here
        Collections.sort(inputNumbers);
        int[][] pyramid;
        double D = Math.sqrt(1 + 8 * inputNumbers.size());
        try{
        if(D > 0 && D % 1 == 0){
            int n = (int)((D - 1) / 2);
            pyramid = new int[n][n+(n-1)];
            int k = 0;
            for(int i = 0; i<n; i++) {
                for (int j = 0; j < n + (n - 1); j++) {
                    pyramid[i][j]=0;
                }
                int b = n - i - 1;
                for (int j = 0; j <= i; j++) {
                    pyramid[i][b]=inputNumbers.get(k++);
                    b += 2;
                }
            }
            return pyramid;
        }else{
            throw new CannotBuildPyramidException("Can't build pyramid");
        }
        }catch (Exception e){
            throw new CannotBuildPyramidException("Can't build pyramid");
        }
    }
}
