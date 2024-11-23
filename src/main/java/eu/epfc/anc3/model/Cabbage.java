package eu.epfc.anc3.model;

import eu.epfc.anc3.model.enums.PlotElement;
import eu.epfc.anc3.model.enums.State;

class Cabbage extends Vegetable {

    private final Runnable callback;

    Cabbage(boolean hasGrass, Runnable callback) {
        super(PlotElement.CABBAGE, hasGrass);
        this.callback = callback;
        setStateVegetable(new CabbageSowing(this));
    }

    //Deep Clone
    private Cabbage(Cabbage cabbage){
        super(PlotElement.CABBAGE);
        this.callback = cabbage.callback;
        this.setNbDayWithGrass(cabbage.getNbDayWithGrass());
        this.setStateVegetable(cabbage.getStateVegetable().getCopy(this));
    }


    @Override
    void callback(){callback.run();}
    @Override
    int getMaxPoint() {return 200;}
    @Override
    Vegetable getCopy() {
        return new Cabbage(this) ;
    }

}



//STATES OF CABBAGE

//SOWING
 class CabbageSowing implements StateVegetable{

    private final Vegetable vegetable;
    private final State state = State.SOWING;
    private int day = 1;

    public CabbageSowing(Vegetable vegetable) {this.vegetable = vegetable;}

    @Override
    public void grow() {
        this.day++;
        if (day > 4)
            if (vegetable.getNbDayWithGrass() > 4 || day == 6)
                vegetable.setStateVegetable(new CabbageState2(vegetable));
    }

    @Override
    public void fertilize() {/*no impact*/}
    @Override
    public int collect() {return 0;}
    @Override
    public State getState() {return state;}

    @Override
    public StateVegetable getCopy(Vegetable vegetableCopy) {
        StateVegetable state = new CabbageSowing(vegetableCopy);
        state.setDay(this.day);
        return state;
    }
    @Override
    public void setDay(int day) {
        this.day = day;
    }
}



//STATE2
 class CabbageState2 implements StateVegetable {

    private final Vegetable vegetable;
    private final State state = State.STATE2;
    private int day = 1;

    public CabbageState2(Vegetable vegetable) {this.vegetable = vegetable;}

    @Override
    public void grow() {
        this.day++;
        if (day > 3)
            if (vegetable.getNbDayWithGrass() > 3 || day == 5)
                vegetable.setStateVegetable(new CabbageState3(vegetable));
    }

    @Override
    public void fertilize() {/*no impact*/}
    @Override
    public int collect() {return 0;}
    @Override
    public State getState() {return state;}
    @Override
    public StateVegetable getCopy(Vegetable vegetableCopy) {
        StateVegetable state = new CabbageState2(vegetableCopy);
        state.setDay(this.day);
        return state;
    }
    @Override
    public void setDay(int day) {
        this.day = day;
    }

}



//STATE3
 class CabbageState3 implements StateVegetable {

    private final Vegetable vegetable;
    private final State state = State.STATE3;
    private int day = 1;

    public CabbageState3(Vegetable vegetable) {this.vegetable = vegetable;}

    @Override
    public void grow() {
        this.day++;
        if (day > 2)
            if (vegetable.getNbDayWithGrass() > 2 || day == 4)
                vegetable.setStateVegetable(new CabbageState4(vegetable));
    }

    @Override
    public void fertilize() {/*no impact*/}
    @Override
    public int collect() {return vegetable.getMaxPoint()/ 4*3;}
    @Override
    public State getState() {return state;}

    @Override
    public StateVegetable getCopy(Vegetable vegetableCopy) {
        StateVegetable state = new CabbageState3(vegetableCopy);
        state.setDay(this.day);
        return state;
    }
    @Override
    public void setDay(int day) {
        this.day = day;
    }
 }




 //STATE4
 class CabbageState4 implements StateVegetable {

    private final Vegetable vegetable;
    private final State state = State.STATE4;
    private int day = 1;

    public CabbageState4(Vegetable vegetable) {this.vegetable = vegetable;}

    @Override
    public void grow() {
        this.day++;
        if (day > 1)
            if (vegetable.getNbDayWithGrass() > 1 || day == 3)
                vegetable.setStateVegetable(new CabbageRotten(vegetable));
    }

    @Override
    public void fertilize() {/*no impact*/}
    @Override
    public int collect() {return vegetable.getMaxPoint();}
    @Override
    public State getState() {return state;}

     @Override
     public StateVegetable getCopy(Vegetable vegetableCopy) {
         StateVegetable state = new CabbageState4(vegetableCopy);
         state.setDay(this.day);
         return state;
     }
     @Override
     public void setDay(int day) {
         this.day = day;
     }
}




//STATE ROTTEN
class CabbageRotten implements StateVegetable {

    private final Vegetable vegetable;
    private final State state = State.ROTTEN;
    private int day = 1;

    public CabbageRotten(Vegetable vegetable) {this.vegetable = vegetable;}

    @Override
    public void grow() {
        this.day++;
        if (day == 6 && vegetable.getNbDayWithGrass() > 5) {

            vegetable.callback();
        }
        else if (day == 11)
            vegetable.callback();
    }

    @Override
    public void fertilize() {/*no impact*/}
    @Override
    public int collect() {return -(vegetable.getMaxPoint() * (day - 1)) / 10;}
    @Override
    public State getState() {return state;}

    @Override
    public StateVegetable getCopy(Vegetable vegetableCopy) {
        StateVegetable state = new CabbageRotten(vegetableCopy);
        state.setDay(this.day);
        return state;
    }

    @Override
    public void setDay(int day) {
        this.day = day;
    }
}

