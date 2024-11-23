package eu.epfc.anc3.model;

import eu.epfc.anc3.model.enums.GameStatus;

public class GameMemento {
    private final int score;
    private final int day;
    private final FarmerMemento farmerMemento;
    private final PlotMemento[][] plotMementos;
    private final GameStatus gameStatus;



    GameMemento(PlotMemento[][] plotMementos, GameStatus gameStatus, FarmerMemento farmerMemento, int day, int score){
        this.plotMementos = plotMementos;
        this.gameStatus = gameStatus;
        this.score = score;
        this.day = day;
        this.farmerMemento = farmerMemento;
    }



    int getScore() {
        return score;
    }
    int getDay() {
        return day;
    }
    FarmerMemento getFarmerMemento() {
        return farmerMemento;
    }
    PlotMemento[][] getPlotMementos() {
        return plotMementos;
    }
    GameStatus getGameStatus() {
        return gameStatus;
    }
}
