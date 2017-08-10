package com.fernbanklinks.labels;

import com.fernbanklinks.main.SoundPlayingThread;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by Jonathan on 6/7/2017.
 */
public class MatchClock extends JLabel implements ActionListener{

    private InputStream startMatchInputStream,
                        endMatchInputStream,
                        warningInputStream;

    private AudioStream startMatchSoundStream,
                        endMatchSoundStream,
                        warningSoundStream;

    public SoundPlayingThread startMatchThread,
                               endMatchThread,
                               warningMatchThread;

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

        try {
            setupSounds();
        } catch(Exception e){
            System.out.println(e.getMessage());
        }

        setText(start);
    }

    private void setupSounds() throws Exception{
        //We have to setup this way otherwise an exception will be incurred
        startMatchSoundStream = new AudioStream(getClass().getClassLoader().getResourceAsStream("com/fernbanklinks/labels/matchstart.wav"));
        endMatchSoundStream = new AudioStream(getClass().getClassLoader().getResourceAsStream("com/fernbanklinks/labels/matchend.wav"));
        warningSoundStream = new AudioStream(getClass().getClassLoader().getResourceAsStream("com/fernbanklinks/labels/endgame.wav"));

        startMatchThread = new SoundPlayingThread(startMatchSoundStream);
        endMatchThread = new SoundPlayingThread(endMatchSoundStream);
        warningMatchThread = new SoundPlayingThread(warningSoundStream);

    }

    public void actionPerformed(ActionEvent event){
        if(timeRemaining - 1 <= 0) {
            endMatchThread.start();
            resetTimer();
        } else {
            timeRemaining -= 1;

            if(timeRemaining == 30){
                warningMatchThread.start();
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

        try {
            setupSounds();
        } catch (Exception e) {
            e.printStackTrace();
        }

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

    public void startTimer(){


        timer.start();
        startMatchThread.start();
    }

    public boolean isRunning(){
        return timer.isRunning();
    }
}
