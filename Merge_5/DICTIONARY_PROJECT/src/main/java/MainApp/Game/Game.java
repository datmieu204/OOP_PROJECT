package MainApp.Game;

import java.io.IOException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public abstract class Game {
    public ImageView soundOnImage;
    public ImageView soundOffImage;
    public ImageView instructionImage;

    public String pathSoundOn;
    public String pathSoundOff;
    public String pathInstruction;

    public Image soundOnImg;
    public Image soundOffImg;
    public Image instructionImg;

    public String correct_sound;
    public String wrong_sound;
    public String background_sound;
    public String option_sound;
    public String gameSelect_sound;

    public Media correct_media;
    public Media wrong_media;
    public Media background_media;
    public Media option_media;
    public Media gameSelect_media;

    public MediaPlayer correctSound;
    public MediaPlayer wrongSound;
    public MediaPlayer backgroundSound;
    public MediaPlayer optionSound;
    public MediaPlayer gameSelectSound;

    public void setup() {
        pathSoundOn = "/image/soundOn.png";
        pathSoundOff = "/image/soundOff.png";
        pathInstruction = "/image/instruction.png";

        soundOnImg = new Image(getClass().getResourceAsStream(pathSoundOn));
        soundOffImg = new Image(getClass().getResourceAsStream(pathSoundOff));
        instructionImg = new Image(getClass().getResourceAsStream(pathInstruction));

        soundOnImage = new ImageView(soundOnImg);
        soundOffImage = new ImageView(soundOffImg);
        instructionImage = new ImageView(instructionImg);

        correct_sound = getClass().getResource("/sound/correct.mp3").toExternalForm();
        wrong_sound = getClass().getResource("/sound/wrong.mp3").toExternalForm();
        background_sound = getClass().getResource("/sound/background.mp3").toExternalForm();
        option_sound = getClass().getResource("/sound/option.mp3").toExternalForm();
        gameSelect_sound = getClass().getResource("/sound/gameSelect.mp3").toExternalForm();

        correct_media = new Media(correct_sound);
        wrong_media = new Media(wrong_sound);
        background_media = new Media(background_sound);
        option_media = new Media(option_sound);
        gameSelect_media = new Media(gameSelect_sound);

        correctSound = new MediaPlayer(correct_media);
        wrongSound = new MediaPlayer(wrong_media);
        backgroundSound = new MediaPlayer(background_media);
        optionSound = new MediaPlayer(option_media);
        gameSelectSound = new MediaPlayer(gameSelect_media);

        correctSound.play();
        correctSound.stop();
        correctSound.seek(Duration.ZERO);

        wrongSound.play();
        wrongSound.stop();
        wrongSound.seek(Duration.ZERO);

        optionSound.play();
        optionSound.stop();
        optionSound.seek(Duration.ZERO);

        gameSelectSound.play();
        gameSelectSound.stop();
        gameSelectSound.seek(Duration.ZERO);

        backgroundSound.setCycleCount(MediaPlayer.INDEFINITE);
        backgroundSound.setVolume(0.5);
        backgroundSound.play();
        backgroundSound.stop();
    }

    abstract public void showResult() throws IOException;

    abstract public void makeClearTransition();
}
