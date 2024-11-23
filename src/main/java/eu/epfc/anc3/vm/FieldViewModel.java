package eu.epfc.anc3.vm;


import eu.epfc.anc3.model.Game;

public class FieldViewModel {

    private final Game game;

    public FieldViewModel(Game game) {this.game = game;}


    public PlotViewModel newPlotViewModel(int i, int j) {
        return new PlotViewModel(i,j,game);
    }

}
