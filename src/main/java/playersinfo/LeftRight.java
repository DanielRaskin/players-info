package playersinfo;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum LeftRight {
    @JsonProperty("L")
    LEFT("L"),

    @JsonProperty("R")
    RIGHT("R"),

    @JsonProperty("B")
    BOTH("B"),

    @JsonProperty("S")
    SPECIAL("S");

    private String shortName;

    LeftRight(String shortName) {
        this.shortName = shortName;
    }

    public static LeftRight fromShortName(String shortName) {
        for (LeftRight leftRight : LeftRight.values()) {
            if (leftRight.shortName.equals(shortName)) {
                return leftRight;
            }
        }
        throw new IllegalArgumentException("Not LeftRight: " + shortName);
    }
}
