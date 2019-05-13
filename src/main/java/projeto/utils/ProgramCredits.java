package projeto.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProgramCredits {

    @JsonProperty("id")
    private int castId;

    @JsonProperty("cast")
    private List<CastDetails> castDetailsList;

    @JsonProperty("crew")
    private List<CrewDetails> crewDetailsList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProgramCredits that = (ProgramCredits) o;
        return Objects.equal(castDetailsList, that.castDetailsList);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(castDetailsList);
    }

    public List<CastDetails> getCastDetailsList() {
        return castDetailsList;
    }

    public void setCastDetailsList(List<CastDetails> castDetailsList) {
        this.castDetailsList = castDetailsList;
    }

    public int getCastId() {
        return castId;
    }

    public void setCastId(int castId) {
        this.castId = castId;
    }

    public List<CrewDetails> getCrewDetailsList() {
        return crewDetailsList;
    }

    public void setCrewDetailsList(List<CrewDetails> crewDetailsList) {
        this.crewDetailsList = crewDetailsList;
    }
}
