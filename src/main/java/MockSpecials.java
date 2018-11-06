import com.fasterxml.jackson.annotation.JsonProperty;

public class MockSpecials {

    @JsonProperty("special_name")
    private String special;

    @JsonProperty("special_short_description")
    private String shortDescription;

    @JsonProperty("special_short_description")
    private String fullDescription;


    public MockSpecials() {
        //default
    }

    public MockSpecials(String special, String shortDescription, String fullDescription) {
        this.special = special;
        this.shortDescription = shortDescription;
        this.fullDescription = fullDescription;
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }
}
