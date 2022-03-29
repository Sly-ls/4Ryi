package fr.game.mechanics.controller;

import fr.game.main.AppConstants;
import fr.game.constants.game.SoundDescriptionEnum;
import fr.game.mechanics.SoundControll;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class SoundController implements SoundControll {

    private Sound music = new Sound();
    public Sound soundEffect = new Sound();
    static SoundController instance;

    private SoundController() {
    }

    public static SoundControll getInstance() {
        if(instance == null){
            instance = new SoundController();
        }
        return instance;
    }


    @Override
    public void playMusic(SoundDescriptionEnum soundDescriptionEnum){
        if(!AppConstants.DEBUG || (AppConstants.DEBUG && AppConstants.DEBUG_MUSIC)) {
            this.music.setFile(soundDescriptionEnum);
            this.music.play();
            this.music.loop();
        }
    }

    @Override
    public void stopMusic(){
        this.music.stop();
    }
    @Override
    public void playSoundEffect(SoundDescriptionEnum soundDescriptionEnum){
        if(!AppConstants.DEBUG || (AppConstants.DEBUG && AppConstants.DEBUG_SOUND)) {
            soundEffect.setFile(soundDescriptionEnum);
            soundEffect.play();
        }
    }
    public class Sound {

        Clip clip;
        URL soundURL[] = new URL[30];

        public Sound() {

            for(SoundDescriptionEnum soundDescriptionEnum : SoundDescriptionEnum.values()){
                soundURL[soundDescriptionEnum.getCode()] = getClass().getResource(soundDescriptionEnum.getPath());
            }
        }

        public void setFile(SoundDescriptionEnum soundDescriptionEnum){
            try{
                AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[soundDescriptionEnum.getCode()]);
                clip = AudioSystem.getClip();
                clip.open(ais);
                ais.close();
            }catch (Exception exception){
                exception.printStackTrace();
            }
        }
        public void play(){
            if(!AppConstants.DEBUG || (AppConstants.DEBUG && AppConstants.DEBUG_SOUND)) {
                clip.start();
            }
        }
        public void loop(){
            if(!AppConstants.DEBUG || (AppConstants.DEBUG && AppConstants.DEBUG_SOUND)) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
        }
        public void stop(){
            if(!AppConstants.DEBUG || (AppConstants.DEBUG && AppConstants.DEBUG_SOUND)) {
                if(clip!= null && clip.isActive()){
                    clip.stop();
                }
            }
        }
    }
}

