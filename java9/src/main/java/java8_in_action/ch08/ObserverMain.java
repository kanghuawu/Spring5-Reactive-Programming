package java8_in_action.ch08;

import java.util.ArrayList;
import java.util.List;

public class ObserverMain {
    interface Observer {
        void notify(String tweet);
    }

    static class NYTimes implements Observer {
        @Override
        public void notify(String tweet) {
            if (tweet != null && tweet.contains("money")) {
                System.out.println("Breaking news in NY! " + tweet);
            }
        }
    }

    static class Guardian implements Observer {
        @Override
        public void notify(String tweet) {
            if (tweet != null && tweet.contains("queen")) {
                System.out.println("Yet another news in London... " + tweet);
            }
        }
    }

    static class LeMonde implements Observer{
        public void notify(String tweet) {
            if(tweet != null && tweet.contains("wine")){
                System.out.println("Today cheese, wine and news! " + tweet);
            }
        }
    }

    interface Subject {
        void addObserver(Observer o);
        void notifyObserver(String tweet);
    }

    static class Feed implements Subject {
        private List<Observer> observers = new ArrayList<>();
        @Override
        public void addObserver(Observer o) {
            observers.add(o);
        }

        @Override
        public void notifyObserver(String tweet) {
            observers.forEach(observer -> observer.notify(tweet));
        }
    }

    public static void main(String[] args) {
        Feed f = new Feed();
        f.addObserver(new NYTimes());
        f.addObserver(new Guardian());
        f.addObserver(new LeMonde());
        f.notifyObserver("The queen said her favourite book is Java 8 in Action!");
        f.addObserver(tweet -> {
            if (tweet != null && tweet.contains("Monday")) {
                System.out.println("Monday is awesome: " + tweet);
            }
        });
        f.notifyObserver("Great week ahead starting with Monday!");
    }
}
