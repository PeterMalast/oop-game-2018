package sk.tuke.kpi.oop.game.Builder;

import sk.tuke.kpi.oop.game.characters.Alien;

public abstract class AlienBuilder {

    protected Alien alien;

    public Alien getAlien() {
        return alien;
    }
    public abstract void buildBehaviour();

    public abstract void buildHealth();


}
