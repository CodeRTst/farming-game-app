package eu.epfc.anc3.model;

import eu.epfc.anc3.model.enums.State;

public interface StateVegetable {

    void grow();
    void fertilize();
    int collect();
    State getState();
    StateVegetable getCopy(Vegetable vegetableCopy);
    void setDay(int day);
}
