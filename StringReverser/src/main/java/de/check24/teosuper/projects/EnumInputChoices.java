package de.check24.teosuper.projects;

public enum EnumInputChoices {

    CHOICE_FILE_INPUT("1"),
    CHOICE_COMMANDLINE_INPUT("2");

    private final String value;

    EnumInputChoices(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static EnumInputChoices findByValue(String value) {
        for (EnumInputChoices enumChoice : EnumInputChoices.values()) {
            if (enumChoice.value.equals(value)) {
                return enumChoice;
            }
        }
        throw new IllegalArgumentException("Keinen Enum mit dem Wert: " + value + " gefunden");
    }

}
