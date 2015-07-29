package it.gabrieletondi.salestaxes.doubles;

import it.gabrieletondi.salestaxes.Display;

public class InMemoryDisplay implements Display {
    private String message;

    public String getMessage() {
        return message;
    }

    public void show(String message) {
        this.message = message;
    }
}
