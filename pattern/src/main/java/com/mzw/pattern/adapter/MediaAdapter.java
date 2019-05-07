package com.mzw.pattern.adapter;

/**
 * @author Jonathan Meng
 * @date 06/05/2019
 */
public class MediaAdapter implements MediaPlayer {
    AdvancedMediaPlayer advancedMediaPlayer;
    public MediaAdapter(AudioType audioType) {
        switch (audioType) {
            case VLC:
                advancedMediaPlayer = new VlcPlayer();
                break;
            case MP4:
                advancedMediaPlayer = new Mp4Player();
                break;
        }
    }

    @Override
    public void play(AudioType audioType, String fileName) {
        switch (audioType) {
            case VLC:
                advancedMediaPlayer.playVlc(fileName);
                break;
            case MP4:
                advancedMediaPlayer.playMp4(fileName);
                break;
        }
    }
}
