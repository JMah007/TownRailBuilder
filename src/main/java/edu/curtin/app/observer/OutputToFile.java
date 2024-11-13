/* This class is a concrete observer class for TownRailObserver */
package edu.curtin.app.observer;

import edu.curtin.app.Railway;
import edu.curtin.app.Town;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;


public class OutputToFile implements TownRailObserver {

    private static final Logger logger = Logger.getLogger(TownRailObserver.class.getName());

    @Override
    /**
     * This method is the concrete method that creates and updates a file simoutput.dot with information about the towns/rail network in a format thats compatible with GraphViz
     * @param Map<> townList is a list of all the towns that exist so its attributes for each town can be accessed for updating purposes
     * @param Map<> railList is a list of all the rails that exist so its attributes for each rail can be accessed for updating purposes
     */
    public void update(Map<String, Town> townList, Map<String, Railway> railList) {
        String railState;

        try (FileWriter writer = new FileWriter("simoutput.dot")) { // Try creating the file and if not successful then close the stream

            writer.write("graph Towns {\n");

            for(Town town : townList.values()){ // Print all the towns
                writer.write("  " + town.getName() + "\n");
            }

            writer.write("\n");

            for(Railway rail: railList.values()){ // Print all the railways 
                railState = rail.getRailState();
                switch(railState){
                    case "Constructing Single Track":
                        writer.write("  " + rail.getTown1() + " -- " + rail.getTown2() + " [style=\"dashed\"]\n");
                        break;
                    case "Completed Single Track":
                        writer.write("  " + rail.getTown1() + " -- " + rail.getTown2() + "\n");
                        break;
                    case "Constructing Double Track":
                    writer.write("  " + rail.getTown1() + " -- " + rail.getTown2() + " [style=\"dashed\",color=\"black:black\"]\n");
                        break;
                    case "Completed Double Track":
                        writer.write("  " + rail.getTown1() + " -- " + rail.getTown2() + " [color=\"black:black\"]\n");	
                        break;
                    default:
                    // Default case does nothing
                    break;
                }
                logger.info("Wrote update to file");
            }

            writer.write("}");

        } catch (IOException e) {
            logger.severe("Could not create simoutput.dot");
        }
    }
}