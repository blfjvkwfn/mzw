package com.mzw.pattern.adapter;

/**
 * @author Jonathan Meng
 * @date 06/05/2019
 */
public class VlcPlayer implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {
        System.out.println("Playing vlc file. Name: " + fileName);
    }

    @Override
    public void playMp4(String fileName) {

    }
}
