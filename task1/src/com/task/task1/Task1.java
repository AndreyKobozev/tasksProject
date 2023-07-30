package com.task.task1;

import java.util.LinkedList;
import java.util.List;

public class Task1 {

    public static void main(String[] args) {
        int roundArrayInitiator = Integer.parseInt(args[0]);
        int intervalLength = Integer.parseInt(args[1]);
        int [] roundArray = new int [roundArrayInitiator];
        int [] tempStepArray = new int [intervalLength];
        List<Integer> resultRoute = new LinkedList<Integer>();


        for(int i = 1; i <= roundArrayInitiator; i++){
            roundArray[i - 1] = i;
        }


        int savedPosition = 0;
        int resultRouteIndex = 0;


        while (tempStepArray[intervalLength - 1] != 1) {

            for (int i = savedPosition, j = 0; j < intervalLength; j++) {
                tempStepArray[j] = roundArray[i];

                if(i == roundArray.length - 1) {
                    savedPosition = i;
                    i = 0;
                    continue;
                }
                savedPosition = i;

                i++;
            }
            resultRoute.add(resultRouteIndex, tempStepArray[0]);
            resultRouteIndex++;

        }

        System.out.println(resultRoute.toString());
    }
}
