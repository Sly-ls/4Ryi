package fr.game.mechanics;

import fr.game.constants.game.SoundDescriptionEnum;

public interface SoundControll {
    void playMusic(SoundDescriptionEnum soundDescriptionEnum);

    void stopMusic();

    void playSoundEffect(SoundDescriptionEnum soundDescriptionEnum);
}
