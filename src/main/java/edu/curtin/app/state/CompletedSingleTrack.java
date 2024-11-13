/* This class is a concrete class in the state pattern */
package edu.curtin.app.state;

import edu.curtin.app.Railway;
import edu.curtin.app.Town;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CompletedSingleTrack implements RailState{
    public static final int MAX_NUM_GOODS_TRANSPORTED = 100; // the maximum number of goods that can be transported by a train per day

    private static final Logger logger = Logger.getLogger(CompletedSingleTrack.class.getName());

    @Override
    /**
     * This method is a concrete method but doesnt count construction days as its not constructing 
     */
    public void countRailConstructionDays(Railway rail) {
        // does nothing
    }   

    @Override
     /**
     * This method is the concrete method that simulates the transport of goods for a single track. It can move 100 goods per day max so it deducts this from num of goods stocked in the town and adds this to num ofgppds transported for the town
     * @param Railway rail - an instance of a rail to to find out what towns are involved
     * @param Map<> townList - contains lsit of all existing towns so it can update the attributes in the town to reflect transport of goods
     */
    public void transportGoodsHandler(Railway rail, Map<String, Town> townList) {
        Town town1, town2;
        int numGoodsStockedTown1, numGoodsStockedTown2;

        if(rail.getDirectionAlternator() == 0){ // Simulate goods being transported from town1 as 0 means train is currently at town1
            town1 = townList.get(rail.getTown1());
            numGoodsStockedTown1 = town1.getNumGoodsStocked();
            
            if (numGoodsStockedTown1 <= MAX_NUM_GOODS_TRANSPORTED){
                town1.setNumGoodsStocked(0); // since the train can always take max of 100 goods per day, we set the stocks back to 0 if less than 100 as all of it has been taken
                town1.setNumGoodsTransported(town1.getNumGoodsTransported() + numGoodsStockedTown1); // The amount of goods taken away gets added to the total amount of goods transported by the town
            }
            else{
                town1.setNumGoodsStocked(numGoodsStockedTown1 - MAX_NUM_GOODS_TRANSPORTED); // number of goods left is more than 100 so we deduct 100 goods from the stockpile
                town1.setNumGoodsTransported(town1.getNumGoodsTransported() + MAX_NUM_GOODS_TRANSPORTED); // The amount of goods taken away gets added to the total amount of goods transported by the town. 

            }
            rail.setDirectionAlternator(1); // set flag to say train is at town2 after the goods have been transported
            logger.log(Level.INFO, () -> "Goods transported from " + town1.getName());
        }
        else{ // Simulate goods being transported from town2 as 1 means train is currently at town2
            town2 = townList.get(rail.getTown2());
            numGoodsStockedTown2 = town2.getNumGoodsStocked();
            
            if (numGoodsStockedTown2 <= MAX_NUM_GOODS_TRANSPORTED){
                town2.setNumGoodsStocked(0); // since the train can always take max of 100 goods per day, we set the stocks back to 0 if less than 100 as all of it has been taken
                town2.setNumGoodsTransported(town2.getNumGoodsTransported() + numGoodsStockedTown2); // The amount of goods taken away gets added to the total amount of goods transported by the town
            }
            else{
                town2.setNumGoodsStocked(numGoodsStockedTown2 - MAX_NUM_GOODS_TRANSPORTED); // number of goods left is more than 100 so we deduct 100 goods from the stockpile
                town2.setNumGoodsTransported(town2.getNumGoodsTransported() + MAX_NUM_GOODS_TRANSPORTED); // The amount of goods taken away gets added to the total amount of goods transported by the town. 
                }
        rail.setDirectionAlternator(0); // set flag to say train is at town1 after the goods have been transported
        logger.log(Level.INFO, () -> "Goods transported from " + town2.getName());
        }
    }

    @Override
    /**
     * This method is the concrete method that gets the current state of the rail as a String message. 
     * @return a string that says "Completed Single Track"
     */
    public String getCurrentState() {
        return "Completed Single Track";
    }    
}
