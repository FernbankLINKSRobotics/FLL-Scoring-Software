package com.fernbanklinks.main;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 * Created by tynanpurdy on 6/9/17.
 */
public class SoundPlayingThread extends Thread{
    private AudioStream sound;

    public SoundPlayingThread(AudioStream matchSound){
        sound = matchSound;
    }

    public void run(){
        AudioPlayer.player.start(sound);
    }



}
