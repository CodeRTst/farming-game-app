package eu.epfc.anc3.vm;

import eu.epfc.anc3.model.*;
import eu.epfc.anc3.model.enums.GameStatus;
import javafx.beans.property.*;


public class PlotViewModel {
    private final int line, col;
    private final Game game;
    private final BooleanProperty isFarmerOnPlot = new SimpleBooleanProperty();


    public PlotViewModel(int line, int col, Game game) {
        this.line =line;
        this.col = col;
        this.game= game;
        isFarmerOnPlot.bind(game.isFarmerOnPlotProperty(line,col));
    }


    public void teleport() {
        if (game.getGameStatus() != GameStatus.END)
            game.teleport(line,col);
    }



    public SimpleListProperty<Vegetable> elementsProperty() {
        return game.elementsProperty(line,col);
    }
    public BooleanProperty isFarmerOnPlotProperty() {return isFarmerOnPlot;}





}
