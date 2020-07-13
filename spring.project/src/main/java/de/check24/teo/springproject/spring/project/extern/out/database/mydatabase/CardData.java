package de.check24.teo.springproject.spring.project.extern.out.database.mydatabase;

public class CardData {

    private String pin;
    private int currentAmount;

    public CardData(String pin, int currentAmount) {
        this.pin = pin;
        this.currentAmount = currentAmount;
    }

    public String getPin() {
        return pin;
    }

    public int getCurrentAmount() {
        return currentAmount;
    }
}
