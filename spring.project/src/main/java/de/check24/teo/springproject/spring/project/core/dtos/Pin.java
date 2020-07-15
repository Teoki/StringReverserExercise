package de.check24.teo.springproject.spring.project.core.dtos;

public class Pin {

    public final String pin;

    public Pin(String pin) {
        this.pin = pin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pin pin1 = (Pin) o;

        return pin.equals(pin1.pin);
    }

    @Override
    public int hashCode() {
        return pin.hashCode();
    }
}
