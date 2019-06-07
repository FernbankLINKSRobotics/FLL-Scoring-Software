package com.fernbanklinks.labels;

import com.fernbanklinks.main.AudioPlayer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Jonathan on 6/7/2017.
 */
public class MatchClock extends JLabel implements ActionListener{

	private static final long serialVersionUID = -5262752269064677710L;

    public AudioPlayer player = new AudioPlayer();

    private double timeRemaining;
    private double initialTime;

    private Timer timer;

    public MatchClock(int startTime){
        super();

        timeRemaining = startTime;
        initialTime = startTime;

        timer = new Timer(1000, this);

        int minutes = (int)(timeRemaining/60);
        int seconds = (int)(timeRemaining%60);

        String start;

        if(seconds == 0){
            start = minutes + ":00"; //Formats the time to show 2 zeros
        } else {
            start = minutes + ":" + seconds; //Otherwise use the normal notation
        }
        setText(start);
    }

    public void actionPerformed(ActionEvent event){
        if(timeRemaining - 1 <= 0) {
            player.play("matchend.wav");
            resetTimer();
        } else {
            timeRemaining -= 1;

            if(timeRemaining == 30){
                player.play("endgame.wav");
            }
        }

        int minutes = (int)(timeRemaining/60);
        int seconds = (int)(timeRemaining%60);


        if(seconds < 10){
            setText(minutes + ":0" + seconds);
        } else {
            setText(minutes + ":" + seconds);
        }


    }

    public void pauseTimer(){
        timer.stop();
    }

    public void resetTimer(){
        timer.stop();
        timeRemaining = initialTime;

        int minutes = (int)(timeRemaining/60);
        int seconds = (int)(timeRemaining%60);

        String start;

        if(seconds == 0){
            start = minutes + ":00"; //Formats the time to show 2 zeros
        } else {
            start = minutes + ":" + seconds; //Otherwise use the normal notation
        }

        setText(start);
    }

    public void startTimer() {
        timer.start();
        player.play("matchstart.wav");
    }

    public boolean isRunning(){
        return timer.isRunning();
    }
}
