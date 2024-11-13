package edu.curtin.app;
import edu.curtin.app.factory.ElementFactory;
import edu.curtin.app.generics.GenericElement;
import edu.curtin.app.observer.TownRailObserver;
import edu.curtin.app.state.ConstructingDoubleTrack;
import java.util.*;
import java.util.logging.Logger;


public class TownRailManager {

    private static final Logger logger = Logger.getLogger(TownRailManager.class.getName());

    private final Map<String, Railway> railList;
    private final Map<String, Town> townList;

    private final List<TownRailObserver> observers = new ArrayList<>();


    /**
     * Default constructor to initalise the class
     */
    public TownRailManager(){
        this.townList = new HashMap<>();
        this.railList = new HashMap<>();
    }
    
    /**
     * This method validates the messages making sure they are valid town founding, town population, railway construction or railway duplication messages
     * @param String msg - this is the message to be checked
     * @returns Boolean where true means the message is valid and false is invalid
     */
    public Boolean validateMessage(String msg){
        String[] msgSections;
        boolean isValid = false;

        // Parse the message to divide it into 3 sections
        msgSections = msg.split(" ");
        
        if(msgSections.length == 3){
            switch(msgSections[0]){

                case "town-founding":

                        if(!townList.containsKey(msgSections[1])){ // If the town doesnt already exist
                            if (msgSections[1] != null && !msgSections[1].isEmpty()) { // If the new town is not a empty string name or null
                                try {
                                    if(Integer.parseInt(msgSections[2]) > 0){ // See if the 3rd argument is a integer in the form of a string
                                        isValid = true;           // then its a valid message
                                        logger.info("town founding valid");
                                    }
                                } catch (NumberFormatException e) {
                                    logger.info("town-founding has invalid population value");
                                }
                            }
                        }
                    break;

                case "town-population":
                    
                    if(townList.containsKey(msgSections[1])){ // If the town exists
                        try {
                            if(Integer.parseInt(msgSections[2]) > 0){ // See if the 3rd argument is a integer in the form of a string
                                isValid = true;           // then its a valid message
                                logger.info("town population valid");
                            }
                        } catch (NumberFormatException e) {
                            logger.info("town-population has invalid population value");
                        }
                    }
                    break;

                case "railway-construction":
                
                    if(townList.containsKey(msgSections[1]) && townList.containsKey(msgSections[2])){ // if both towns exist
                        if(!railList.containsKey(msgSections[1] + "-" + msgSections[2]) && !railList.containsKey(msgSections[2] + "-" + msgSections[1])){ // if there doesnt exist a rail or theres no rail under construction between the 2 towns
                            isValid = true;
                            logger.info("railway construction valid");
                        }
                    } 
                    break;

                case "railway-duplication":
                    Railway rail;
                    if(townList.containsKey(msgSections[1]) && townList.containsKey(msgSections[2])){ // if both towns exist
                        if(railList.containsKey(msgSections[1] + "-" + msgSections[2])){ // if the rail name starts with the first towns name
                            rail = railList.get(msgSections[1] + "-" + msgSections[2]);
                            if (rail.getRailState().equals("Completed Single Track")) { // if the rail is in the CompletedSingleTrack state
                                isValid = true;
                                logger.info("railway duplication valid");    
                            }
                        }
                        else{ // the rail name must start with the second towns name
                            rail = railList.get(msgSections[2] + "-" + msgSections[1]);
                            if (rail.getRailState().equals("Completed Single Track")) { // if the rail is in the CompletedSingleTrack state
                                isValid = true;
                                logger.info("railway duplication valid");
                            }
                        }
                    } 
                    break;

                default:
                // Default case does nothing
                break;
                } 
        }
    return isValid;
    }

    /**
     * This method carries out updates in the towns and rails according to the message
     * @param String msg - this is the message containing the updates to be carried out. It could be either founding a new town, updating town population, building a single railway or duplicating a single railway
     */
    public void carryOutMessage(String msg) {
        String[] msgSections;
        String mainMsg;
        Town town;
        GenericElement<?> newElement;
        Town newTown;
        Railway newRail;
    
        ElementFactory elementFactory = new ElementFactory();
    
        // Parse the message to divide it into sections
        msgSections = msg.split(" ");
        mainMsg = msgSections[0]; // Get the first main message specifying the action
    
        switch (mainMsg) {
            case "town-founding": // Adds a new town object to the list of existing towns
                newElement = elementFactory.createElement(msgSections);
                if (newElement.getElement() instanceof Town) { // Check if it is a Town instance
                    newTown = (Town) newElement.getElement(); 
                    townList.put(newTown.getName(), newTown); // Add to town list
                    notifier(); // Calls observer
                }
                break;
    
            case "town-population": // Finds corresponding town and updates its population
                town = townList.get(msgSections[1]);
                if (town != null) { // Check if the town exists
                    town.setPopulation(Integer.parseInt(msgSections[2]));
                }
                break;
    
            case "railway-construction": // Adds a new rail to the list of existing rails
                newElement = elementFactory.createElement(msgSections);
                if (newElement.getElement() instanceof Railway) { // Check if it is a Railway instance
                    newRail = (Railway) newElement.getElement(); 
                    railList.put(newRail.getTown1() + "-" + newRail.getTown2(), newRail); // Add to rail list
                    notifier(); // Calls observer
                }
                break;
    
            case "railway-duplication": // Finds corresponding rail and changes its state
                // Try looking for existing rail with rail name starting with rail1 name
                if (railList.containsKey(msgSections[1] + "-" + msgSections[2])) {
                    Railway existingRail = railList.get(msgSections[1] + "-" + msgSections[2]);
                    existingRail.setRailState(new ConstructingDoubleTrack());
                    notifier(); // Calls observer
                } else if (railList.containsKey(msgSections[2] + "-" + msgSections[1])) { // Try looking for existing rail with rail name starting with rail2 name
                    Railway existingRail = railList.get(msgSections[2] + "-" + msgSections[1]);
                    existingRail.setRailState(new ConstructingDoubleTrack());
                    notifier(); // Calls observer
                } 
                break;
    
            default:
                // nothing happens here
                break;
        }
    }
    

    /**
     * This method displays all the towns and railways info to the user in the terminal
     */
    public void displayAllInfo(){
        int numSingleRails, numDoubleRails;
        System.out.println("\n");
        for(Town town : townList.values()){
            numSingleRails = calcTownsSingleRails(railList, town.getName());
            numDoubleRails = calcTownsDoubleRails(railList, town.getName());
            System.out.println(town.getName() + " p:" + town.getPopulation() + " rs:" + numSingleRails + " rd:" + numDoubleRails +  " gs:" + town.getNumGoodsStocked() + " gt:" + town.getNumGoodsTransported());
        }
    }

    /**
     * Go through the list of rails in the instance of this class and count how many single railways a specific town has
     * @param Map<> railList - the list of railways where each railway specifies 2 towns it connects 
     * @param String townName - name of the town we are calculating the number of rails for 
     * @return an integer representing the number of single railways that connects a town
     */
    public int calcTownsSingleRails(Map<String, Railway> railList, String townName){
        int numRails = 0;

        for(Railway rail : railList.values()){ // Check through list of rails to see how many single rails connect "townName" to another town
            if((rail.getTown1().equals(townName) || rail.getTown2().equals(townName))){ // if the rail conncts "townName" to somewhere
                if(rail.getRailState().equals("Completed Single Track")){ // if the single rail is completed
                    numRails++;
                }
            }
        }
        return numRails;
    }


    /**
     * Go through the list of rails in the instance of this class and count how many double railways a specific town has
     * @param Map<> railList - the list of railways where each railway specifies 2 towns it connects 
     * @param String townName - name of the town we are calculating the number of rails for 
     * @return an integer representing the number of double railways that connects a town
     */
    public int calcTownsDoubleRails(Map<String, Railway> railList, String townName){
        int numRails = 0;

        for(Railway rail : railList.values()){ // Check through list of rails to see how many double rails connect "townName" to another town
            if((rail.getTown1().equals(townName) || rail.getTown2().equals(townName))){ // if the rail conncts "townName" to somewhere
                if(rail.getRailState().equals("Completed Double Track")){ // if the double rail is completed
                    numRails++;
                }
            }
        }
        return numRails;
    }

    /**
     * This method can be called to trigger all towns in the town list to produce goods 
     */
    public void produceGoodsInTowns(){
        for(Town town : townList.values()){
            town.produceGoods();
        }
    }
   
    /**
     * This method increments all rails in the rail lists day 
     */
    public void incrementAllRailDays(){
        for (Railway rail: railList.values()){
            rail.incrementDay();
        }
    }

    /**
     * This method triggers simulation of moving goods on all rails that exist
     */
    public void transportAllGoodsInTown(){
        for (Railway rail: railList.values()){
            rail.transportGoods(this.townList);
        }
    }

    private void notifier(){
        for (TownRailObserver observer : observers){
            observer.update(this.townList, this.railList);
        }
    }

    public void addObserver(TownRailObserver newObserver){
        observers.add(newObserver);
    }

    public void removeObserver(TownRailObserver newObserver){
        observers.remove(newObserver);
    }    
}