package com.fernbanklinks.frames;

import com.fernbanklinks.labels.MatchClock;
import com.fernbanklinks.labels.MatchCounter;
import com.fernbanklinks.main.AudioPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Jonathan on 6/1/2017.
 */
public class ScoringEntryDisplay extends JFrame implements ActionListener {

	private static final long serialVersionUID = -3938203920601301621L;

	private JTable table;

    private JButton incrementMatchButton = new JButton("+1 Match"),
                    incrementRoundButton = new JButton("+1 Round"),
                    decrementMatchButton = new JButton("-1 Match"),
                    decrementRoundButton = new JButton("-1 Round"),
                    stopTimerButton      = new JButton("Stop Timer"),
                    startTimerButton     = new JButton("Start Timer");

    private AudioPlayer player = new AudioPlayer();
    private JPanel buttonPanel = new JPanel();
    private MatchCounter counter;

    private MatchClock clock;

    public ScoringEntryDisplay(JTable workingTable, MatchCounter matchCounter, MatchClock matchClock) {
        table = workingTable;
        table.setCellSelectionEnabled(true);

        setSize(600, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        counter = matchCounter;
        clock = matchClock;

        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        incrementMatchButton.setActionCommand("Add Match");
        incrementRoundButton.setActionCommand("Add Round");
        decrementMatchButton.setActionCommand("Remove Match");
        decrementRoundButton.setActionCommand("Remove Round");
        startTimerButton    .setActionCommand("Start Timer");
        stopTimerButton     .setActionCommand("Stop Timer");

        incrementMatchButton.setAlignmentX(JButton.CENTER);
        incrementRoundButton.setAlignmentX(JButton.CENTER);

        incrementMatchButton.addActionListener(this);
        incrementRoundButton.addActionListener(this);
        decrementMatchButton.addActionListener(this);
        decrementRoundButton.addActionListener(this);
        startTimerButton    .addActionListener(this);
        stopTimerButton     .addActionListener(this);

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        setLayout(new FlowLayout());


        add(table);

        JPanel topButtonPanel = new JPanel(new FlowLayout());
        JPanel bottomButtonPanel = new JPanel(new FlowLayout());

        topButtonPanel   .add(incrementMatchButton);
        topButtonPanel   .add(incrementRoundButton);
        topButtonPanel   .add(startTimerButton);
        bottomButtonPanel.add(decrementMatchButton);
        bottomButtonPanel.add(decrementRoundButton);
        bottomButtonPanel.add(stopTimerButton);

        buttonPanel.add(topButtonPanel);
        buttonPanel.add(bottomButtonPanel);


        add(buttonPanel);
    }

    public void actionPerformed(ActionEvent event){

        if(event.getActionCommand().equals("Add Match")){
            counter.incrementMatchCount();
        } else if(event.getActionCommand().equals("Add Round")){
            counter.incrementRoundCount();
        } else if(event.getActionCommand().equals("Start Timer")){
            clock.startTimer();
        } else if(event.getActionCommand().equals("Stop Timer")){
            player.play("matchpause.wav");
            clock.resetTimer();
        } else if(event.getActionCommand().equals("Remove Match")){
            counter.decrementMatchCount();
        } else if(event.getActionCommand().equals("Remove Round")){
            counter.decrementRoundCount();
        }
    }


}
