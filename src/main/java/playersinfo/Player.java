package playersinfo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public record Player(
        @JsonProperty("ID")
        String id,

        @JsonProperty("birthDate")
        YearMonthDay birthDate,

        @JsonProperty("birthLocation")
        Location birthLocation,

        @JsonProperty("deathDate")
        YearMonthDay deathDate,

        @JsonProperty("deathLocation")
        Location deathLocation,

        @JsonProperty("firstName")
        String firstName,

        @JsonProperty("lastName")
        String lastName,

        @JsonProperty("givenName")
        String givenName,

        @JsonProperty("weight")
        Integer weight,

        @JsonProperty("height")
        Integer height,

        @JsonProperty("bats")
        LeftRight bats,

        @JsonProperty("throws")
        LeftRight throwz,

        @JsonProperty("debut")
        LocalDate debut,

        @JsonProperty("finalGame")
        LocalDate finalGame,

        @JsonProperty("retroID")
        String retroId,

        @JsonProperty("bbrefID")
        String bbrefId) {
}
