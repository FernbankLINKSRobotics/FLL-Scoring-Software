package com.fernbanklinks.main;

import java.util.ArrayList;
import java.util.List;

import com.fernbanklinks.labels.MatchClock;
import com.fernbanklinks.labels.MatchCounter;

/**
 * Created by Jonathan on 6/1/2017.
 */
public class Main {
    public static final int kteamCount = 10;
    public static final String[] names = {"Hello", "My", "Name", "Is", "Simon"};

    public static void main(String[] args){
        //Array where the team names will live
        List<String> teamNames = new ArrayList<String>(kteamCount);
        for(int i=0; i<kteamCount; i++) { teamNames.add(""); }

        MatchCounter counter = new MatchCounter();
        MatchClock   clock   = new MatchClock(150);

        Competition competition = new Competition(names, counter, clock);
    }
}
