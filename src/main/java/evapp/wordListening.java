package evapp;

import com.voicerss.tts.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javafx.concurrent.Task;

import java.io.File;
import java.io.FileOutputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class wordListening extends Task<Clip> {
    private String text;
    private String languageCode;

    public wordListening(String text, String languageCode) {
        this.text = text;
        this.languageCode = languageCode;
    }

    @Override
    public Clip call() throws Exception {
        String PATH = "src/main/resources/audio.wav";
        ExecutorService executor = Executors.newSingleThreadExecutor();

        try {
            Future<byte[]> future = executor.submit(() -> {
                VoiceProvider tts = new VoiceProvider("11e9655235c943cebe3df1a1a7abdb14");
                VoiceParameters params = new VoiceParameters(text, languageCode);
                params.setCodec(AudioCodec.WAV);
                params.setFormat(AudioFormat.Format_44KHZ.AF_44khz_16bit_stereo);
                params.setBase64(false);
                params.setSSML(false);
                params.setRate(0);
                return tts.speech(params);
            });

            byte[] voice = future.get();
            FileOutputStream fos = new FileOutputStream(PATH);
            fos.write(voice, 0, voice.length);
            fos.flush();
            fos.close();
            File audioFile = new File(PATH);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            return clip;
        } finally {
            executor.shutdown();
        }
    }
}