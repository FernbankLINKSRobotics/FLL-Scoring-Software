package com.fernbanklinks.main;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Jonathan on 6/3/2017.
 */
public class Team {
    private String teamName;

    private List<Integer> runs;

    public int ranking;

    public Team(String name){
        teamName = name;

        runs = Arrays.asList(0, 0, 0);

        ranking = 0;
    }

    public int getHighScore(){
        int max = 0;
        String tmp = "";
        
        for(int r: runs) {
        		tmp += String.valueOf(r + " ");
        		if(r > max) { max = r; }
        }
        
        System.out.println(tmp);
        return max;
    }

    public void updateScores(int col, int score){
        System.out.println("New Score:" + score);
        runs.set(col-2, score);
    }

    public Object[] getArrayRepresentation(){
        Object[] returnArray = {0, teamName, 0, 0, 0, 0};
        return returnArray;
    }
}
