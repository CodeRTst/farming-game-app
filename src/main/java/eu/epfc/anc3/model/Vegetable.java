package eu.epfc.anc3.model;



import eu.epfc.anc3.model.enums.PlotElement;
import eu.epfc.anc3.model.enums.State;
import javafx.beans.property.*;

public abstract class Vegetable {

    private final PlotElement plotElement;
    private int nbDayWithGrass;
    private final ObjectProperty<StateVegetable> stateVegetableProperty = new SimpleObjectProperty<>();


    //CONSTRUCTORS
    Vegetable(PlotElement plotElement){this.plotElement = plotElement;}
    Vegetable(PlotElement plotElement, boolean hasGrass) {
        this.plotElement = plotElement;
        setNbDayWithGrass(hasGrass);
    }




    //ABSTRACT METHODS
    abstract void callback();
    abstract int getMaxPoint();
    abstract Vegetable getCopy();




    //GETTER
    public PlotElement getPlotElement() {return plotElement;}
    public State getState() {return stateVegetableProperty.get().getState();}
    ReadOnlyProperty<StateVegetable> stateVegetableProperty() {return stateVegetableProperty;}
    StateVegetable getStateVegetable() { return stateVegetableProperty.get();}
    int getNbDayWithGrass() {return nbDayWithGrass;}





    //ACTIONS
    void fertilize(){stateVegetableProperty.get().fertilize();}
    int collect(){return stateVegetableProperty.get().collect();}
    void grow() {
        if (nbDayWithGrass > 0)
            nbDayWithGrass++;
        getStateVegetable().grow();
    }





    //SETTER
    void setStateVegetable(StateVegetable stateVegetable) {this.stateVegetableProperty.set(stateVegetable);}
    void setNbDayWithGrass(boolean hasGrass){nbDayWithGrass = hasGrass ? 1 : 0;}
    void setNbDayWithGrass(int nbDayWithGrass){this.nbDayWithGrass = nbDayWithGrass ;}

}