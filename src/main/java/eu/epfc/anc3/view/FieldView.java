package eu.epfc.anc3.view;

import eu.epfc.anc3.vm.FieldViewModel;
import javafx.geometry.Insets;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;


import static eu.epfc.anc3.view.FarmingView.FIELD_LENGTH;
import static eu.epfc.anc3.view.FarmingView.FIELD_WIDTH;
import static eu.epfc.anc3.view.FarmingView.FIELD_VIEW_HEIGHT;
import static eu.epfc.anc3.view.FarmingView.FIELD_VIEW_WIDTH;

class FieldView extends GridPane {

     FieldView(FieldViewModel fieldViewModel) {
        configFieldSize();
        setPadding(new Insets(10));

        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setPercentHeight(100.0 / FIELD_WIDTH);
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setPercentWidth(100.0 / FIELD_LENGTH);



        for (int i = 0; i < FIELD_LENGTH; ++i) {
            if (i < FIELD_WIDTH) {
                getRowConstraints().add(rowConstraints);
            }
            getColumnConstraints().add(columnConstraints);
        }

        for (int i = 0; i < FIELD_WIDTH; ++i) {
            for (int j = 0; j < FIELD_LENGTH; ++j) {
               PlotView caseView = new PlotView(fieldViewModel.newPlotViewModel(i,j));
                add(caseView, j, i);
            }
        }


    }



    private void configFieldSize() {
        setMinHeight(FIELD_VIEW_HEIGHT);
        setMaxHeight(FIELD_VIEW_HEIGHT);
        setMinWidth(FIELD_VIEW_WIDTH);
        setMaxWidth(FIELD_VIEW_WIDTH);
    }

}


