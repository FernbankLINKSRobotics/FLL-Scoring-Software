package com.fernbanklinks.labels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Jonathan on 6/8/2017.
 */
public class MatchCounter extends JPanel{

    private JPanel panel;

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

    public void incrementMatchCount(){
        matchNumber += 1;
        matchLabel.setText("Match " + matchNumber);
    }

    public void incrementRoundCount(){
        roundNumber += 1;
        roundLabel.setText("Round " + roundNumber);
    }

    public void decrementMatchCount(){
        matchNumber -= 1;
        matchLabel.setText("Match " + matchNumber);
    }

    public void decrementRoundCount(){
        roundNumber -= 1;
        roundLabel.setText("Round " + roundNumber);
    }



}
