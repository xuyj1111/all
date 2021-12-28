package xu.all.designPattern.adapterPattern;

import xu.all.designPattern.adapterPattern.abstr.MediaPlayer;

/**
 * @Description: 默认支持mp3，现添加适配器支持mp4和vlc
 * @Author: xuyujun
 * @Date: 2021/10/9
 */
public class AudioPlayer implements MediaPlayer {
    @Override
    public void play(String audioType, String fileName) {
        if ("mp3".equals(audioType)) {
            System.out.println("Playing mp3 file. Name: " + fileName);
        } else if ("mp4".equals(audioType) || "vlc".equals(audioType)) {
            MediaAdapter mediaAdapter = new MediaAdapter(audioType);
            mediaAdapter.play(audioType, fileName);
        } else {
            System.out.println("Invalid media. " +
                    audioType + " format not supported");
        }
    }
}
