package eu.epfc.anc3.model;

import eu.epfc.anc3.model.enums.PlotElement;
import eu.epfc.anc3.model.enums.State;

class Dirt extends Vegetable {

    Dirt() {
        super(PlotElement.DIRT, false);
        setStateVegetable(new DirtState());
    }


    @Override
    void callback(){}
    @Override
    int getMaxPoint() {return 0;}
    @Override
    Vegetable getCopy() {
        return this;
    }


}




//STATE OF DIRT
class DirtState implements StateVegetable {

    private final State state = State.DIRT_STATE1;
    @Override
    public void grow() {/*no impact*/}
    @Override
    public void fertilize() {/*no impact*/}
    @Override
    public int collect() {return 0;}
    @Override
    public State getState() {return state;}

    @Override
    public StateVegetable getCopy(Vegetable vegetableCopy) {
        return null;
    }

    @Override
    public void setDay(int day) {
    }
}
