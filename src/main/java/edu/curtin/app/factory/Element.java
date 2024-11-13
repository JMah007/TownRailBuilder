/* this class is the component class for Factory pattern */
package edu.curtin.app.factory;
import edu.curtin.app.generics.GenericElement;

/**
 * This method is a component method that create any element of more than 1 type 
 */
public interface Element<T> {
     GenericElement<T> createElement(String[] msgSections);
}