package com.mzw.pattern.adapter;

/**
 * @author Jonathan Meng
 * @date 06/05/2019
 */
public class AdapterPatternDemo {
    public static void main(String[] args) {
        AudioPlayer audioPlayer = new AudioPlayer();

        audioPlayer.play(AudioType.MP3, "beyond the horizon.mp3");
        audioPlayer.play(AudioType.MP4, "alone.mp4");
        audioPlayer.play(AudioType.VLC, "far far away.vlc");
        audioPlayer.play(AudioType.AVI, "mind me.avi");
    }
}
