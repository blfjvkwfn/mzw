package com.mzw.pattern.adapter;

/**
 * @author Jonathan Meng
 * @date 06/05/2019
 */
public class AudioPlayer implements MediaPlayer {
    MediaAdapter mediaAdapter;

    @Override
    public void play(AudioType audioType, String fileName) {
        switch (audioType) {
            case MP3:
                System.out.println("Playing mp3 file. Name:" + fileName);
                break;
            case VLC: case MP4:
                mediaAdapter = new MediaAdapter(audioType);
                mediaAdapter.play(audioType, fileName);
                break;
            default:
                System.out.println("Invalid media ." + audioType + " format not supported");

        }
    }
}
