package utilities.errors;

public class NotAcceptedValue extends IllegalArgumentException {
    public NotAcceptedValue(Object object) {
        super("La valeur " + object.toString() + " n'est pas acceptée.");
    }
}
