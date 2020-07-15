package de.check24.teo.springproject.spring.project.core.dtos;

public class CardId {

    public final String value;

    public CardId(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CardId cardId = (CardId) o;

        return value.equals(cardId.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
