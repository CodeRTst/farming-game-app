package eu.epfc.anc3.model;

public class FarmerMemento {
    private final int line;
    private final int col;

    FarmerMemento(int line, int col){
        this.line = line;
        this.col = col;
    }
    int getLine(){return line;}
    int getCol(){return col;}
}
