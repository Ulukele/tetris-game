package common;

import java.util.Vector;

public class Publisher implements IPublisher {
    Vector<ISubscriber> subscribers = new Vector<>();

    @Override
    public void addSubscriber(ISubscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public boolean removeSubscriber(ISubscriber subscriber) {
        return subscribers.remove(subscriber);
    }

    @Override
    public void publishNotify() {
        for (final ISubscriber subscriber : subscribers) {
            subscriber.reactOnNotify();
        }
    }
}
