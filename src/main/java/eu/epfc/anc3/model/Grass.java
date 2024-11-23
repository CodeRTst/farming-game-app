package eu.epfc.anc3.model;

import eu.epfc.anc3.model.enums.PlotElement;
import eu.epfc.anc3.model.enums.State;

public class Grass extends Vegetable {

    private final Runnable callback;


    Grass(Runnable callback) {
        super(PlotElement.GRASS, true);
        this.callback = callback;
        setStateVegetable(new GrassState(this));
    }


    //Deep Clone
    private Grass(Grass grass){
        super(PlotElement.GRASS);
        this.callback = grass.callback;
        this.setNbDayWithGrass(grass.getNbDayWithGrass());
        this.setStateVegetable(grass.getStateVegetable().getCopy(this));
    }


    @Override
    void callback() {callback.run();}
    @Override
    int getMaxPoint() {return 0;}
    @Override
    Vegetable getCopy() {
        return new Grass(this);
    }
}




//STATE OF GRASS
class GrassState implements StateVegetable {

    private final Vegetable vegetable;
    private final State state = State.GRASS_STATE1;
    private int day = 1;
    public GrassState(Vegetable vegetable) {this.vegetable = vegetable;}

    @Override
    public void grow() {
        day++;
        if (day == 13)
            vegetable.callback();
    }

    @Override
    public void fertilize() {/*no impact*/}
    @Override
    public int collect() {return 0;}
    @Override
    public State getState() {
        return state;
    }

    @Override
    public StateVegetable getCopy(Vegetable vegetableCopy) {
        StateVegetable state = new GrassState(vegetableCopy);
        state.setDay(this.day);
        return state;
    }

    @Override
    public void setDay(int day) {this.day = day;}
}
