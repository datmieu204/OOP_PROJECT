package MainApp;

import java.io.File;
import java.io.FileOutputStream;

import com.voicerss.tts.AudioFormat;
import com.voicerss.tts.VoiceParameters;
import com.voicerss.tts.VoiceProvider;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class TextToSpeech {

    private static final String API_KEY = "ee1a861047db41e3aed6cca75554a826";
    private static final String AUDIO_PATH = "src/main/resources/voice/voice_.wav";
    public static final double speed = 1;

    public static String languageCode = "";
    public static String languageName = "";

    public static void tts(String word) throws Exception {
        VoiceParameters paramsVoice = new VoiceParameters(word, AudioFormat.Format_44KHZ.AF_44khz_16bit_stereo);
        paramsVoice.setBase64(false);
        paramsVoice.setLanguage(languageCode);
        paramsVoice.setVoice(languageName);
        paramsVoice.setRate((int) Math.round(-2.9936 * speed * speed + 15.2942 * speed - 12.7612));
        
        GoogleAPIController.checkGoogleAPI();
        
        VoiceProvider textToSpeech = new VoiceProvider(API_KEY);
        byte[] voice = textToSpeech.speech(paramsVoice);

        FileOutputStream fileOutput = new FileOutputStream(AUDIO_PATH);
        fileOutput.write(voice, 0, voice.length);
        fileOutput.flush();
        fileOutput.close();
        
        Media media = new Media(new File(AUDIO_PATH).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }
}
