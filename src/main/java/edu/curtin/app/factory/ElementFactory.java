/* this class is a context class for factory pattern */
package edu.curtin.app.factory;
import edu.curtin.app.generics.GenericElement;

public class ElementFactory {

    private final TownsFactory townFactory;
    private final RailwayFactory railFactory;

    public ElementFactory() {
        this.townFactory = new TownsFactory();
        this.railFactory = new RailwayFactory();
    }

    /**
    * This method creates an element of either Town or Rail type depending on what strings are passed in
    * @param String[] msgSections - is divided into sections where first messsage contaisn the determinant string for what type is made
    * @return returns a GenericElement of either Town or Railway
    */
    public GenericElement<?> createElement(String[] msgSections) {
        GenericElement<?> element = null;
        switch(msgSections[0]) {
            case "town-founding":
                element = townFactory.createElement(msgSections);
                break;
            case "railway-construction":
                element = railFactory.createElement(msgSections);
                break;
            default:
                // no need default
        }
        return element;
    }
    
}