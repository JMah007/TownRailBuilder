package edu.curtin.app.factory;

import edu.curtin.app.Town;
import edu.curtin.app.generics.GenericElement;

public class TownsFactory implements Element<Town> {

    @Override
    /**
    * This concrete method implements the creation of a Town instance with name and population of the town
    * @param String[] msgSections - is divided into sections where 2nd and 3rd section contain a name and population of the town
    * @return returns a GenericElement of Town
    */
    public GenericElement<Town> createElement(String[] msgSections) {
        Town town = new Town(msgSections[1], Integer.parseInt(msgSections[2]));
        return new GenericElement<>(town);
    }
}

