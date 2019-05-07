package com.mzw.pattern.adapter;

/**
 * @author Jonathan Meng
 * @date 06/05/2019
 */
public class Mp4Player implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {

    }

    @Override
    public void playMp4(String fileName) {
        System.out.println("Playing mp4 file. Name:" + fileName);
    }
}
