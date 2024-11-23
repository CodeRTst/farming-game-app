package eu.epfc.anc3.vm;

import eu.epfc.anc3.model.Game;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class HeadViewModel {
    private final IntegerProperty dayProperty = new SimpleIntegerProperty();
    private final IntegerProperty scoreProperty = new SimpleIntegerProperty();

    public HeadViewModel(Game game){
        dayProperty.bind(game.dayProperty());
        scoreProperty.bind(game.scoreProperty());
    }


    public IntegerProperty dayProperty(){
        return dayProperty;
    }
    public IntegerProperty scoreProperty() {
        return scoreProperty;
    }

}
