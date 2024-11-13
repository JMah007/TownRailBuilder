/* This class is a user made generic class with 1 parameter */
package edu.curtin.app.generics;

public class GenericElement<T> {
    private final T element;

    public GenericElement(T element) {
        this.element = element;
    }

    public T getElement() {
        return element;
    }
}

