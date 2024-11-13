package edu.curtin.app;
import edu.curtin.app.state.ConstructingSingleTrack;
import edu.curtin.app.state.RailState;
import java.util.Map;

public class Railway {
    private RailState railwayState;
    private final String town1;
    private final String town2;
    private int directionAlternator; // Value of 0 means train is at town 1 and 0 means at town 2 // NOPMD just a naming convention breach
    private int numDays;

     /**
     * This is a constructor that initalises this class
     */
    public Railway(String inTown1, String inTown2){
        this.railwayState = new ConstructingSingleTrack();
        this.town1 = inTown1; 
        this.town2 = inTown2; 
        directionAlternator = 0;
        this.numDays = 0;
    }

    public String getRailState(){
        return railwayState.getCurrentState();
    }

    public String getTown1() {
        return town1;
    }

    public String getTown2() {
        return town2;
    }

    public int getNumDays() {
        return numDays;
    }

    public int getDirectionAlternator() {
        return directionAlternator; // NOPMD just a naming convention breach
    }

    public void setRailState(RailState railwayState) {
        this.railwayState = railwayState;
    }

    public void setNumDays(int numDays) {
        this.numDays = numDays;
    }

    public void setDirectionAlternator(int directionAlternator) {
        this.directionAlternator = directionAlternator; // NOPMD just a naming convention breach
    }

    public void setRailwayState(RailState railwayState) {
        this.railwayState = railwayState;
    }

     /**
     * This method can be called to trigger the simulation of the transport of goods
     * @param Map<> townList - is the list of towns that exist so once the goods have been transported, the attributes in the town can be updated to reflect the changes
     */
    public void transportGoods(Map<String, Town> townList){
        railwayState.transportGoodsHandler(this, townList); // NOPMD just a naming convention breach
    }

     /**
     * This method can be called to increment the days of construction only if the rail is in a construction state
     */
    public void incrementDay(){
        railwayState.countRailConstructionDays(this);
    }

    





  
    
}
