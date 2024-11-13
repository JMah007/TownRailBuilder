package edu.curtin.app.state;

import edu.curtin.app.Railway;
import edu.curtin.app.Town;
import java.util.Map;

public interface RailState{
    
    /**
     * Component method for incrementing the days for a rail depending on the state its in
     * @param Railway rail - pass in instance of a rail to access its number of days 
     */
    void countRailConstructionDays(Railway rail);

    /**
     * Component method for simulating trasport of goods on a rail depending on the state its in
     * @param Railway rail - pass in instance of a rail to access its number of days 
     * @param Map<> townList - contains list of all towns that exist so for the railway it can find what 2 towns are involved that the train is transporting between
     */
    void transportGoodsHandler(Railway rail, Map<String, Town> townList);
    
    /**
     * This component method returns the state of the rail as a string depending on the state the rail is in
     * @return a string with the message depending on the state of the rail
     */
    String getCurrentState();
}