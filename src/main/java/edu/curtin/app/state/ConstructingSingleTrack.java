/* This class is a concrete class in the state pattern */
package edu.curtin.app.state;

import edu.curtin.app.Railway;
import edu.curtin.app.Town;
import java.util.Map;

public class ConstructingSingleTrack implements RailState{

    @Override
    /**
     * This method is the concrete method that increments the number of days a rail has been constructing for by 1. If the days equals 5, then the state of the rail will transition to a completed single track state
     * @param Railway rail - an instance of a rail to count the number of days for and check if its finished completing 
     */
    public void countRailConstructionDays(Railway rail) {
        rail.setNumDays(rail.getNumDays() + 1);
        if(rail.getNumDays() == 5){
            rail.setRailwayState(new CompletedSingleTrack());
            rail.setNumDays(0);  // reset days back to 0 after construction is complete
        }
    }

    @Override
     /**
     * This method doesnt implement handling of goods as the rail is in construction state
     */
    public void transportGoodsHandler(Railway rail, Map<String, Town> townList) {
        // Cant transport goods while single track is being constructed
    }  

    @Override
    /**
     * This method is the concrete method that gets the current state of the rail as a String message. 
     * @return a string that says "Constructing Single Track"
     */
    public String getCurrentState() {
        return "Constructing Single Track";
    }
}