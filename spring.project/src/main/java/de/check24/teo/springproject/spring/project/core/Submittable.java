package de.check24.teo.springproject.spring.project.core;

public interface Submittable {

    default void submit(int cardId) {};
    default void submit(int cardId, int amount) {};
    boolean needAmount();

}
