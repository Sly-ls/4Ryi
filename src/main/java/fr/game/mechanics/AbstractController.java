package fr.game.mechanics;

import fr.game.panel.AbstractPanel;

public abstract class AbstractController {
    protected AbstractPanel panel;

    protected AbstractController(AbstractPanel titlePanel){
        this.panel= titlePanel;
    }

    public AbstractPanel getPanel() {
        return panel;
    }
}
