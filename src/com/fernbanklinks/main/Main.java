package com.fernbanklinks.main;

import com.fernbanklinks.labels.MatchClock;
import com.fernbanklinks.labels.MatchCounter;

/**
 * Created by Jonathan on 6/1/2017.
 */
public class Main {

    public static void main(String[] args){
        String[] teamNames = {"Mega Robot", "Snail Bots", "Willie Wonka Chocolate Factory", "Techno Paths",
                                "Team Name", "RoboRobots", "?", "Cool Awesome Machine", "C4BX", "Pizza", "Bearacougars",
                                "Dewpiders", "Killer Kittens", "NR", "R.O.V.E.R."};

        MatchCounter counter = new MatchCounter();
        MatchClock clock = new MatchClock(150);

        Competition competition = new Competition(teamNames, counter, clock);
    }
}
