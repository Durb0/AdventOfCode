package utilities.errors;

import java.util.NoSuchElementException;

public class NoSuchElementInListException extends NoSuchElementException {
    public NoSuchElementInListException() {
        super("La liste ne contient pas d'élément correspondant aux critères de sélection");
    }
}
