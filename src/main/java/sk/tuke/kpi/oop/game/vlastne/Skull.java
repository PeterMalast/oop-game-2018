package sk.tuke.kpi.oop.game.vlastne;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.TimeBomb;
import sk.tuke.kpi.oop.game.characters.Alive;
import sk.tuke.kpi.oop.game.characters.Armed;
import sk.tuke.kpi.oop.game.items.Usable;

import java.util.Random;

public class Skull extends AbstractActor implements Usable<Armed> {


    public Skull() {
        super("Skull");
       setAnimation(new Animation("sprites/skull.png", 16, 16));
    }



    @Override
    public void useWith(Armed actor) {
        if(actor==null)return;
        if(this.getScene() != null){
            Random rand = new Random();
            int n = rand.nextInt(40) + 1;
            if(n < 10){
                actor.getFirearm().reloadSuperAmmo(50);
            }else if(n >= 10 && n < 20){
                actor.getFirearm().reload(50);
            }else if(n >= 20 && n < 30) {
                TimeBomb bomb = new TimeBomb(0);
                actor.getScene().addActor(bomb, actor.getPosX(), actor.getPosY());
                bomb.activate();
            }
            else {
                Scene scene = getScene();
                for(Actor actor2 : scene.getActors()){
                    if(this.intersects(actor2)&&(actor2 instanceof Alive))((Alive) actor2).getHealth().refill(100);
                }
            }
            this.getScene().removeActor(this);
        }
    }

    @Override
    public Class<Armed> getUsingActorClass() {
        return Armed.class;
    }

}
