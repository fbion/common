package ebook.designpattern.multiple.example1.test;

import ebook.designpattern.multiple.example1.Goose;
import ebook.designpattern.multiple.example1.GooseAdapter;
import ebook.designpattern.multiple.example1.Quackable;
import ebook.designpattern.multiple.example1.composite.Flock;
import ebook.designpattern.multiple.example1.decorator.QuackCounter;
import ebook.designpattern.multiple.example1.factory.AbstractDuckFactory;
import ebook.designpattern.multiple.example1.factory.CountingDuckFactory;
import ebook.designpattern.multiple.example1.impl.DuckCall;
import ebook.designpattern.multiple.example1.impl.MarllardDuck;
import ebook.designpattern.multiple.example1.impl.RedheadDuck;
import ebook.designpattern.multiple.example1.impl.RubberDuck;

/**
 * 描述： <br>
 * 创建时间: 2017/7/1817:08 <br>
 *
 * @author 周志辉
 */
public class DuckSimulator {

    public static void main(String[] args) {
        DuckSimulator simulator = new DuckSimulator();
        AbstractDuckFactory duckFactory = new CountingDuckFactory();
        simulator.simulateWithFlock(duckFactory);
    }

    private void simulateWithFlock(AbstractDuckFactory duckFactory) {
        Quackable redheadDuck = duckFactory.createRedheadDuck();
        Quackable duckCall = duckFactory.createDuckCall();
        Quackable rubberDuck = duckFactory.createRubberDuck();
        Quackable gooseDuck = new GooseAdapter(new Goose());
        System.out.println("Duck Simulator: with composite - flocks");
        Flock flockOfDucks = new Flock();
        flockOfDucks.add(redheadDuck);
        flockOfDucks.add(duckCall);
        flockOfDucks.add(rubberDuck);
        flockOfDucks.add(gooseDuck);

        Flock flockOfMallards = new Flock();

        Quackable marllardDuck1 = duckFactory.createMarllardDuck();
        Quackable marllardDuck2 = duckFactory.createMarllardDuck();
        Quackable marllardDuck3 = duckFactory.createMarllardDuck();
        Quackable marllardDuck4 = duckFactory.createMarllardDuck();

        flockOfMallards.add(marllardDuck1);
        flockOfMallards.add(marllardDuck2);
        flockOfMallards.add(marllardDuck3);
        flockOfMallards.add(marllardDuck4);

        flockOfDucks.add(flockOfMallards);

        System.out.println("Duck Simulator: Whole Flock Simulation");
        simulate(flockOfDucks);

        System.out.println("Duck Simulator: Mallard Flock Simulation");
        simulate(flockOfMallards);

        System.out.println("The ducks quacked " + QuackCounter.getQuacks() + " times. ");
    }


    private void simulate(AbstractDuckFactory duckFactory) {
        Quackable marllardDuck = duckFactory.createMarllardDuck();
        Quackable redheadDuck = duckFactory.createRedheadDuck();
        Quackable duckCall = duckFactory.createDuckCall();
        Quackable rubberDuck = duckFactory.createRubberDuck();
        Quackable gooseDuck = new GooseAdapter(new Goose());
        System.out.println("Duck Simulator: with abstract factory");
        simulate(marllardDuck);
        simulate(redheadDuck);
        simulate(duckCall);
        simulate(rubberDuck);
        simulate(gooseDuck);
        System.out.println("The ducks quacked " + QuackCounter.getQuacks() + " times. ");
    }


    private void simulate() {
        Quackable marllardDuck = new QuackCounter(new MarllardDuck());
        Quackable redheadDuck = new QuackCounter(new RedheadDuck());
        Quackable duckCall = new QuackCounter(new DuckCall());
        Quackable rubberDuck = new QuackCounter(new RubberDuck());
        Quackable gooseDuck = new GooseAdapter(new Goose());
        System.out.println("Duck Simulator");
        simulate(marllardDuck);
        simulate(redheadDuck);
        simulate(duckCall);
        simulate(rubberDuck);
        simulate(gooseDuck);
        System.out.println("The ducks quacked " + QuackCounter.getQuacks() + " times. ");
    }


    private void simulate(Quackable duck) {
        duck.quack();
    }
}
