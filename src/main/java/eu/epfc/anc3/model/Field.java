package eu.epfc.anc3.model;

import eu.epfc.anc3.model.enums.PlotElement;
import javafx.beans.property.*;

class Field {
    static final int FIELD_WIDTH = 15;
    static final int FIELD_LENGTH = 25;
    private final Plot[][] matrix;

    private final IntegerProperty farmerPosLine = new SimpleIntegerProperty();
    private final IntegerProperty farmerPosCol = new SimpleIntegerProperty();
    private final IntegerProperty dayProperty = new SimpleIntegerProperty();
    private final IntegerProperty scoreProperty = new SimpleIntegerProperty();


    Field(){
        matrix = new Plot[FIELD_WIDTH][];
        for (int i = 0; i < FIELD_WIDTH; ++i) {
            matrix[i] = new Plot[FIELD_LENGTH];
            for (int j = 0; j < FIELD_LENGTH; ++j) {
                matrix[i][j] = new Plot();
                matrix[i][j].dayProperty().bind(dayProperty);
                scoreProperty.bindBidirectional(matrix[i][j].scoreProperty());
                if (i == 0 && j == 0)
                    matrix[0][0].setIsFarmerOnPlot(true);
            }
        }
        configListener();
    }

    private void configListener() {
        farmerPosLine.addListener((observable, oldValue, newValue) -> {
            matrix[oldValue.intValue()][farmerPosCol.get()].setIsFarmerOnPlot(false);
            matrix[newValue.intValue()][farmerPosCol.get()].setIsFarmerOnPlot(true);
        });

        farmerPosCol.addListener((observable, oldValue, newValue) -> {
            matrix[farmerPosLine.get()][oldValue.intValue()].setIsFarmerOnPlot(false);
            matrix[farmerPosLine.get()][newValue.intValue()].setIsFarmerOnPlot(true);
        });
    }


    void fertilize() {matrix[farmerPosLine.get()][farmerPosCol.get()].fertilize();}
    void collect() {matrix[farmerPosLine.get()][farmerPosCol.get()].collect();}
    void plant(PlotElement plotElement) {matrix[farmerPosLine.get()][farmerPosCol.get()].plant(plotElement);}



    void reset() {
        for (int i = 0; i < FIELD_WIDTH; ++i)
            for (int j = 0; j < FIELD_LENGTH; ++j)
                matrix[i][j].reset();
    }


    PlotMemento[][] save() {
        PlotMemento[][] plotMementos = new PlotMemento[FIELD_WIDTH][];
        for (int i = 0; i < FIELD_WIDTH;++i) {
            plotMementos[i] = new PlotMemento[FIELD_LENGTH];
            for (int j = 0;j < FIELD_LENGTH;++j) {
                plotMementos[i][j] = getPlot(i,j).save();
            }
        }
        return plotMementos;
    }

    void restore(PlotMemento[][] plotMementos) {
        for (int i = 0; i < FIELD_WIDTH;++i)
            for (int j = 0;j < FIELD_LENGTH;++j)
                getPlot(i,j).restore(plotMementos[i][j]);
    }




    private Plot getPlot(int i, int j) {return matrix[i][j];}



    IntegerProperty farmerPosLineProperty() {return farmerPosLine;}
    IntegerProperty farmerPosColProperty() {return farmerPosCol;}
    BooleanProperty isFarmerOnPlotProperty(int line, int col) {return matrix[line][col].isFarmerOnPlotProperty();}
    IntegerProperty dayProperty() {return dayProperty;}
    IntegerProperty scoreProperty() {return scoreProperty;}
    SimpleListProperty<Vegetable> elementsProperty(int i, int j) {return matrix[i][j].plotElementsProperty();}



}

