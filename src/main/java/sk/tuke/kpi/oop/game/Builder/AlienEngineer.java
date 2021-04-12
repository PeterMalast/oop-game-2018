package sk.tuke.kpi.oop.game.Builder;

import sk.tuke.kpi.oop.game.characters.Alien;

public class AlienEngineer {

    private AlienBuilder alienBuilder;
    public void buildAlien(){
        this.alienBuilder.buildBehaviour();
        this.alienBuilder.buildHealth();
    }

    public void setAlienBuilder(AlienBuilder alienBuilder){
        this.alienBuilder=alienBuilder;
    }

    public Alien getAlien(){
        return this.alienBuilder.getAlien();
    }


}
