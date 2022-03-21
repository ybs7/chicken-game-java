import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class PlayMusic {

    private Clip clip;

    public PlayMusic(URL path){
        try{
            AudioInputStream ais =
                    AudioSystem.getAudioInputStream(path);
            AudioFormat baseFormat = ais.getFormat();
            AudioFormat decodeFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
                    baseFormat.getSampleRate(),16,baseFormat.getChannels(), baseFormat.getChannels() * 2,
            baseFormat.getSampleRate(),false);

            AudioInputStream dais =
                    AudioSystem.getAudioInputStream(decodeFormat,ais);
            clip = AudioSystem.getClip();
            clip.open(dais);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void play(){
        if(clip == null)return;
        stop();
        clip.setFramePosition(0);
        clip.loop(5);
    }
    public void sound(){
        if(clip == null)return;
        stop();
        clip.setFramePosition(0);
        clip.start();
    }
    public void stop(){
        if(clip.isRunning())
            clip.stop();
    }

    public void close(){
        stop();
        clip.close();
    }
}
