package eu.epfc.anc3.model;

public class CareTaker {


    private GameMemento gameMemento;

    public void saveMemento(GameMemento gameMemento){
        this.gameMemento = gameMemento;
    }
    public GameMemento getGameMemento() {
        return gameMemento;
    }

}
