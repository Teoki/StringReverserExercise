package de.check24.teo.springproject.spring.project.core.dtos;

public class CardData { //Dto object

    private Pin pin;
    private Amount currentAmount;

    public CardData(String pin, int currentAmount) {
        this.pin = new Pin(pin);
        this.currentAmount = new Amount(currentAmount);
    }

    public Pin getPin() {
        return pin;
    }

    public Amount getCurrentAmount() {
        return currentAmount;
    }
}
