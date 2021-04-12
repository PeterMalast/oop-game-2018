package sk.tuke.kpi.oop.game.vlastne;
/***
 * VZOR COMPOSITE
 */

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.actions.While;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.messages.Topic;
import sk.tuke.kpi.oop.game.behaviours.Behaviour;
import sk.tuke.kpi.oop.game.behaviours.RandomlyMoving;
import sk.tuke.kpi.oop.game.characters.Alien;
import sk.tuke.kpi.oop.game.characters.Alive;
import sk.tuke.kpi.oop.game.characters.Health;

import java.util.ArrayList;
import java.util.List;

public class AlienWizard extends AbstractActor implements Alive {

    private Health health;
    private boolean isAlive;
    private Behaviour<? super AlienWizard> behaviour;

    private List<Alien> aliens;


    public static final Topic<AlienWizard> WITCHER_DIED = Topic.create("witcher died", AlienWizard.class);

    public AlienWizard() {

        super("AlienWizard");
        this.health = new Health(200 );
        setAnimation(new Animation("sprites/spitter_alien.png", 32, 32));
        isAlive = true;

        aliens = new ArrayList<>();
    }
/*
    public AlienWizard(int healthv,Behaviour<? super AlienWizard> behaviour){
        setAnimation(new Animation("sprites/spitter_alien.png", 32, 32));
        this.behaviour = behaviour;
        health = new Health(healthv);
    }
    */

    @Override
    public Health getHealth() {
        return health;
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);

        new While<>(action -> true,
            new ActionSequence<>(
                new Invoke<>(this::Witching),
                new Wait<>(3)
            )).scheduleOn(this);

        this.getHealth().onExhaustion(() -> {
            scene.getMessageBus().publish(WITCHER_DIED, this);
            this.die();
        });

        if (behaviour!=null){
            behaviour.setUp(this);
        }

    }

    void Witching(){
        if (isAlive ){
            Scene scene = getScene();
            Alien alien = new Alien(100,new RandomlyMoving());
            scene.addActor(alien,this.getPosX(), this.getPosY());
            aliens.add(alien);
        }

    }

    public void die(){
        Scene scene = getScene();
       scene.removeActor(this);
        this.getAnimation().resetToFirstFrame();
        this.getAnimation().play();
        isAlive = false;
      /*
        for (Alien alien : aliens){
            alien.getHealth().drain(100);
        }
        */
    }
/*
    public void setBehaviour(Behaviour<? super AlienWizard> behaviour) {
        this.behaviour = behaviour;
    }
*/
}
