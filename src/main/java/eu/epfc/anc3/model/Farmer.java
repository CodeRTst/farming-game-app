package eu.epfc.anc3.model;

import eu.epfc.anc3.model.enums.KeyValue;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import static eu.epfc.anc3.model.Field.FIELD_LENGTH;
import static eu.epfc.anc3.model.Field.FIELD_WIDTH;

class Farmer {

    private final IntegerProperty line = new SimpleIntegerProperty(0);
    private final IntegerProperty col = new SimpleIntegerProperty(0);


    void reset() {
        setCol(0);
        setLine(0);
    }

    FarmerMemento save(){
        return  new FarmerMemento(getLine(), getCol());
    }
    void restore(FarmerMemento farmerMemento){
        setLine(farmerMemento.getLine());
        setCol(farmerMemento.getCol());
    }





    void move(KeyValue k) {
        switch (k) {
            case RIGHT -> {if (getCol() < FIELD_LENGTH-1) setCol(getCol()+1);}
            case LEFT -> {if (getCol() > 0) setCol(getCol()-1);}
            case UP -> {if (getLine() > 0) setLine(getLine()-1);}
            case DOWN -> {if (getLine() < FIELD_WIDTH-1) setLine(getLine()+1);}

        }

    }

    void teleport(int line, int col) {
        setLine(line);
        setCol(col);
    }


    //SETTER
    private void setLine(int line) {
        this.line.set(line);
    }
    private void setCol(int col) {
        this.col.set(col);
    }


    //GETTER
    private int getLine() {
        return line.get();
    }
    private int getCol() {
         return col.get();
     }



    IntegerProperty colProperty() {return col;}
    IntegerProperty lineProperty() {return line;}

}
