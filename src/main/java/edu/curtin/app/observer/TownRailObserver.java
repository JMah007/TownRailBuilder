package edu.curtin.app.observer;

import edu.curtin.app.Railway;
import edu.curtin.app.Town;
import java.util.*;

public interface TownRailObserver {

    /**
     * This method is the component method that updates something in all observers
     * @param Map<> town_list is a list of all the towns that exist so its attributes for each town can be accessed for updating purposes
     * @param Map<> rail_list is a list of all the rails that exist so its attributes for each rail can be accessed for updating purposes
     * */
    void update(Map<String, Town> townList, Map<String, Railway> railList);
}
