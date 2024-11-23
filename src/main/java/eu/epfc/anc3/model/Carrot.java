package eu.epfc.anc3.model;

import eu.epfc.anc3.model.enums.PlotElement;
import eu.epfc.anc3.model.enums.State;

class Carrot extends Vegetable {

    private final Runnable callback;


    Carrot(boolean hasGrass, Runnable callback) {
        super(PlotElement.CARROT, hasGrass);
        this.callback = callback;
        setStateVegetable(new CarrotSowing(this));
    }

    //Deep Clone
    private Carrot( Carrot carrot){
        super(PlotElement.CARROT);
        this.callback = carrot.callback;
        this.setNbDayWithGrass(carrot.getNbDayWithGrass());
        this.setStateVegetable(carrot.getStateVegetable().getCopy(this));
    }


    @Override
    void callback(){callback.run();}
    @Override
    int getMaxPoint() {return 100;}
    @Override
    Vegetable getCopy() {
        return new Carrot(this);
    }
}



//STATES OF CARROT

//SOWING
class CarrotSowing implements StateVegetable {

    private final Vegetable vegetable;
    private final State state = State.SOWING;
    private int day = 1;

    public CarrotSowing(Vegetable vegetable) {this.vegetable = vegetable;}

    @Override
    public void grow() {
        this.day++;
        if (this.day == 4)
            vegetable.setStateVegetable(new CarrotState2(vegetable));
    }
    @Override
    public void fertilize() {
        vegetable.setStateVegetable(new CarrotState3(vegetable));
    }
    @Override
    public int collect() {return vegetable.getMaxPoint() / 10;}
    @Override
    public State getState() {return state;}

    @Override
    public StateVegetable getCopy(Vegetable vegetableCopy) {
        StateVegetable state = new CarrotSowing(vegetableCopy);
        state.setDay(this.day);
        return state;
    }

    @Override
    public void setDay(int day) {
        this.day = day;
    }
}




//STATE2
class CarrotState2 implements StateVegetable {

    private final Vegetable vegetable;
    private final State state = State.STATE2;
    private int day = 1;

    public CarrotState2(Vegetable vegetable) {this.vegetable = vegetable;}

    @Override
    public void grow() {
        this.day++;
        if (this.day == 4)
            vegetable.setStateVegetable(new CarrotState3(vegetable));
    }
    @Override
    public void fertilize() {vegetable.setStateVegetable(new CarrotState3(vegetable));}
    @Override
    public int collect() {return vegetable.getMaxPoint() / 5;}
    @Override
    public State getState() {return state;}
    @Override
    public StateVegetable getCopy(Vegetable vegetableCopy) {
        StateVegetable state = new CarrotState2(vegetableCopy);
        state.setDay(this.day);
        return state;
    }

    @Override
    public void setDay(int day) {
        this.day = day;
    }
}



//STATE3
class CarrotState3 implements StateVegetable {

    private final Vegetable vegetable;
    private final State state = State.STATE3;
    private int day = 1;

    public CarrotState3(Vegetable vegetable) {this.vegetable = vegetable;}

    @Override
    public void grow() {
        day++;
        if (day == 4)
            vegetable.setStateVegetable(new CarrotState4(vegetable));
    }
    @Override
    public void fertilize() {/*no impact on carrots with this state*/}
    @Override
    public int collect() {return vegetable.getMaxPoint() / 2;}
    @Override
    public State getState() {return state;}

    @Override
    public StateVegetable getCopy(Vegetable vegetableCopy) {
        StateVegetable state = new CarrotState3(vegetableCopy);
        state.setDay(this.day);
        return state;
    }

    @Override
    public void setDay(int day) {
        this.day = day;
    }
}




//STATE4
class CarrotState4 implements StateVegetable {

    private final Vegetable vegetable;
    private final State state = State.STATE4;
    private int day = 1;

    public CarrotState4(Vegetable vegetable) {this.vegetable = vegetable;}

    @Override
    public void grow() {
        day++;
        if (day == 4)
            vegetable.setStateVegetable(new CarrotRotten(vegetable));
    }
    @Override
    public void fertilize() {/*no impact on carrots with this state*/}
    @Override
    public int collect() {return vegetable.getMaxPoint();}
    @Override
    public State getState() {return state;}
    @Override
    public StateVegetable getCopy(Vegetable vegetableCopy) {
        StateVegetable state = new CarrotState4(vegetableCopy);
        state.setDay(this.day);
        return state;
    }

    @Override
    public void setDay(int day) {
        this.day = day;
    }
}




//STATE ROTTEN
class CarrotRotten  implements StateVegetable{

    private final Vegetable vegetable;
    private final State state = State.ROTTEN;
    private int day = 1;

    public CarrotRotten(Vegetable vegetable) {this.vegetable = vegetable;}

    @Override
    public void grow() {
        day++;
        if (day == 11)
            vegetable.callback();
    }

    @Override
    public void fertilize() {/*no impact on carrots with this state*/}
    @Override
    public int collect() {return -(vegetable.getMaxPoint() * (day - 1)) / 10;}
    @Override
    public State getState() {return state;}

    @Override
    public StateVegetable getCopy(Vegetable vegetableCopy) {
        StateVegetable state = new CarrotRotten(vegetableCopy);
        state.setDay(this.day);
        return state;
    }

    @Override
    public void setDay(int day) {
        this.day = day;
    }
}
