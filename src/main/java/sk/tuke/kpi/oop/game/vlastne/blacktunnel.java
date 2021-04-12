package sk.tuke.kpi.oop.game.vlastne;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.characters.Alien;
import sk.tuke.kpi.oop.game.characters.Alive;
import sk.tuke.kpi.oop.game.characters.Enemy;
import sk.tuke.kpi.oop.game.characters.Ripley;

public class blacktunnel extends AbstractActor implements Enemy {

    public blacktunnel(){

        setAnimation(new Animation("sprites/tunnel_black.png", 32,32,0.1F,Animation.PlayMode.LOOP_PINGPONG));
    }
    public void collidedWithRipley(){
        for(Actor actor: getScene()){
            if(this.intersects(actor)&&(actor instanceof Alive) &&!(actor instanceof Enemy)){
                ((Alive) actor).getHealth().drain(2);

            }
        }
    }
    @Override
    public void addedToScene(@NotNull Scene scene){
        super.addedToScene(scene);
        new Loop<>(new Invoke<>(this::collidedWithRipley)).scheduleOn(this);
    }


}
