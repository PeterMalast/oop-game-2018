package sk.tuke.kpi.oop.game.Builder;

import sk.tuke.kpi.oop.game.behaviours.RandomlyMoving;
import sk.tuke.kpi.oop.game.characters.Alien;
import sk.tuke.kpi.oop.game.characters.Health;

public class MovingAlienBuilder extends  AlienBuilder {

    private Alien alien;

    public MovingAlienBuilder() {

        alien = new Alien();
    }


    @Override
    public void buildHealth() {
        alien.setHealth(new Health(40));
    }


    @Override
    public Alien getAlien() {
        return alien;
    }

    @Override
    public void buildBehaviour() {
        alien.setBehaviour(new RandomlyMoving());
    }

}

