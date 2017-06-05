package com.fernbanklinks;

import java.util.Arrays;

/**
 * Created by Jonathan on 6/3/2017.
 */
public class Team {
    private String teamName;

    private int run1, run2, run3;

    public int ranking;

    public Team(String name){
        teamName = name;

        run1 = 0;
        run2 = 0;
        run3 = 0;

        ranking = 0;
    }

    public int getHighScore(){
        int[] scores = {run1, run2, run3};
        Arrays.sort(scores);

        System.out.print(scores[0] + ":" + scores[1] + ":" + scores[2]);

        return scores[2];
    }

    public void updateScores(int col, int score){
        System.out.println("New Score:" + score);

        switch(col){
            case 2:
                run1 = score;
                break;
            case 3:
                run2 = score;
                break;
            case 4:
                run3 = score;
                break;
        }




    }

    public Object[] getArrayRepresentation(){
        Object[] returnArray = {0, teamName, 0, 0, 0, 0};
        return returnArray;
    }

}
