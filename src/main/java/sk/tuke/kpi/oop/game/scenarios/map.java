package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.*;
import sk.tuke.kpi.oop.game.Iterator.Container;
import sk.tuke.kpi.oop.game.Iterator.Iterator;
import sk.tuke.kpi.oop.game.Locker;
import sk.tuke.kpi.oop.game.Ventilator;
import sk.tuke.kpi.oop.game.behaviours.Observing;
import sk.tuke.kpi.oop.game.behaviours.RandomlyMoving;
import sk.tuke.kpi.oop.game.behaviours.RunForActor;
import sk.tuke.kpi.oop.game.characters.Alien;
import sk.tuke.kpi.oop.game.characters.CustomChar;
import sk.tuke.kpi.oop.game.controllers.CollectorController;
import sk.tuke.kpi.oop.game.controllers.MovableController;
import sk.tuke.kpi.oop.game.controllers.ShooterController;
import sk.tuke.kpi.oop.game.items.AccessCard;
import sk.tuke.kpi.oop.game.items.Ammo;
import sk.tuke.kpi.oop.game.items.Energy;
import sk.tuke.kpi.oop.game.openables.Door;
import sk.tuke.kpi.oop.game.vlastne.*;

public class map implements SceneListener, Container {
    public static class Factory implements ActorFactory {

        @Nullable
        @Override
        public Actor create(@Nullable String type, @Nullable String name) {
            if (name != null) {
                System.out.println(name);
                switch (name) {
                    case "ellen":
                        return new CustomChar();
                    case "ammo":
                        return new Ammo();
                    case "energy":
                        return new Energy();
                    case "tunel":
                        return new blacktunnel();
                    case "skull":
                        return new Skull();
                    case "snack":
                        return new snack();
                    case "front door":
                        return new Door(name,Door.Orientation.VERTICAL);
                    case "alienn":
                        return new AlienWizard();
                    case "acid":
                        return new Acid();
                    case "fire":
                        return new fire();







                        case "alien":
                        switch (type) {
                            case "running":
                                return new Alien(100,
                                    new Observing<>(
                                        World.ACTOR_REMOVED_TOPIC,
                                        actor -> actor instanceof Ammo,
                                        new RunForActor<>()
                                    )
                                );
                            case "waiting1":
                            case "waiting2":
                                return new Alien(100,
                                    new Observing<>(
                                        Door.DOOR_OPENED,
                                        Door::isOpen,
                                        new RunForActor<>()
                                    )
                                );
                            default:
                                return new Alien(100,new RandomlyMoving());
                        }
                    case "boss":
                        switch (type) {

                            case "running":
                                return new Boss(200,
                                    new Observing<>(
                                        World.ACTOR_REMOVED_TOPIC,
                                        actor -> actor instanceof Ammo,
                                        new RunForActor<>()
                                    )
                                );

                            case "waiting1":
                            case "waiting2":

                                return new Boss(200,
                                    new Observing<>(
                                        Door.DOOR_OPENED,
                                        Door::isOpen,
                                        new RunForActor<>()
                                    )
                                );
                            default:
                                return new Boss(200,new RandomlyMoving());
                        }


                }
            }
            return null;
        }
    }

    @Override
    public void sceneInitialized(@NotNull Scene scene) {
        CustomChar Yourchar = (CustomChar)scene.getFirstActorByName("You");
        MovableController movableController = new MovableController(Yourchar);
        CollectorController collectorController = new CollectorController(Yourchar);
        ShooterController shooterController = new ShooterController(Yourchar);
        scene.follow(Yourchar);
        Disposable shooter = scene.getInput().registerListener(shooterController);
        Disposable moves = scene.getInput().registerListener(movableController);
        Disposable collect = scene.getInput().registerListener(collectorController);
        scene.getMessageBus().subscribe(CustomChar.CustomChar_DIED, (Yourchar1 -> {
            shooter.dispose();
            moves.dispose();
            collect.dispose();
        }));
    }

    @Override
    public void sceneUpdating(@NotNull Scene scene) {
        int windowHeight = scene.getGame().getWindowSetup().getHeight();
        int topOffset = GameApplication.STATUS_LINE_OFFSET;
        int yTextPos = windowHeight - topOffset;
        int zoznam = windowHeight - topOffset -40;
        CustomChar yourchar = (CustomChar) scene.getFirstActorByName("You");

        scene.getGame().pushActorContainer(yourchar.getContainer());
        scene.getGame().getOverlay().drawText("| Energy : "+ yourchar.getHealth().getValue(), 100, yTextPos);
        scene.getGame().getOverlay().drawText("| Ammo : "+ yourchar.getFirearm().getAmmo(), 260, yTextPos);

        scene.getGame().getOverlay().drawText("TASK 1: Collect Items", 10, zoznam );
        scene.getGame().getOverlay().drawText("TASK 2: Kill the Witcher", 10, zoznam - 20);
        scene.getGame().getOverlay().drawText("TASK 3: Kill the BOSS", 10, zoznam - 40);


    }

    @Override
    public Iterator getIterator() {
        return null;
    }
}
