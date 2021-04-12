package sk.tuke.kpi.oop.game.behaviours;

import sk.tuke.kpi.gamelib.messages.Topic;
import sk.tuke.kpi.gamelib.Actor;
import java.util.function.Predicate;

public class Observing<A extends Actor, T> implements Behaviour<A> {

    private Behaviour<A> delegate;
    private Topic<T> topic;
    private Predicate<T> predicate;

    public Observing(Topic<T> topic, Predicate<T> predicate, Behaviour<A> delegate) {
        this.predicate = predicate;
        this.topic = topic;
        this.delegate = delegate;
    }

    @Override
    public void setUp(A actor) {
        if (actor == null) return;
        actor.getScene().getMessageBus().subscribe(this.topic, t -> {
            if (predicate.test(t))delegate.setUp(actor);
        });
    }
}
