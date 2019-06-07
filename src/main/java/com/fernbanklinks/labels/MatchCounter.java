package com.fernbanklinks.labels;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Jonathan on 6/8/2017.
 */
public class MatchCounter extends JPanel{

	private static final long serialVersionUID = 413750559470338056L;

    private int matchNumber;
    private int roundNumber;

    private JLabel matchLabel;
    private JLabel roundLabel;

    public MatchCounter(){
        setLayout(new GridLayout(2, 1, 2, 2));

        matchNumber = 1;
        roundNumber = 1;

        matchLabel = new JLabel();
        roundLabel = new JLabel();

        matchLabel.setHorizontalAlignment(JLabel.CENTER);
        roundLabel.setHorizontalAlignment(JLabel.CENTER);

        matchLabel.setFont(new Font("Bebas Neue", Font.BOLD, 70));
        roundLabel.setFont(new Font("Bebas Neue", Font.BOLD, 50));

        matchLabel.setText("Match " + matchNumber);
        roundLabel.setText("Round " + roundNumber);

        add(matchLabel);
        add(roundLabel);
    }

    public void incrementMatchCount(){ matchLabel.setText("Match " + (matchNumber += 1)); }
    public void incrementRoundCount(){ roundLabel.setText("Round " + (roundNumber += 1)); }

    public void decrementMatchCount(){ 
        if(matchNumber-1 <= 0) { matchNumber = 2; } 
        matchLabel.setText("Match " + (matchNumber -= 1)); 
        System.out.println(matchNumber);
    }
    
    public void decrementRoundCount(){
        if(roundNumber-1 <= 0) { roundNumber = 2; } 
        roundLabel.setText("Round " + (roundNumber -= 1));
    }
}
