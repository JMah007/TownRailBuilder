package edu.curtin.app.factory;

import edu.curtin.app.Railway;
import edu.curtin.app.generics.GenericElement;

public class RailwayFactory implements Element<Railway> {

    @Override
    /**
    * This concrete method implements the creation of a Railway instance with 2 town names it connects
    * @param String[] msgSections - is divided into sections where 2nd and 3rd section contain 2 town names to be passed into instance of the new Rail
    * @return returns a GenericElement of Railway
    */
    public GenericElement<Railway> createElement(String[] msgSections) {
        Railway railway = new Railway(msgSections[1], msgSections[2]);
        return new GenericElement<>(railway);
    }
}
