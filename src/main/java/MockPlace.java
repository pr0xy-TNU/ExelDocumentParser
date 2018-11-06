import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MockPlace {

    @JsonProperty("id")
    private String id;

    @JsonProperty("place_name")
    private String placeName;

    @JsonProperty("specials")
    private List<MockSpecials> specials;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("website")
    private String website;


    @JsonProperty("address")
    private String address;

    private Date lastRefresh;

    public MockPlace() {
        //default
    }

    public MockPlace(String id, String placeName, List<MockSpecials> specials, String phone, String website, String address) {
        this.id = id;
        this.placeName = placeName;
        this.specials = specials;
        this.phone = phone;
        this.website = website;
        this.address = address;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public List<MockSpecials> getSpecials() {
        return specials;
    }

    public void setSpecials(List<MockSpecials> specials) {
        this.specials = specials;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getLastRefresh() {
        return lastRefresh;
    }

    public void setLastRefresh(String lastRefresh) {
        SimpleDateFormat format = new SimpleDateFormat("dd-M-yyy hh:mm:ss a", Locale.ENGLISH);
        try {
            this.lastRefresh = format.parse(lastRefresh);
        } catch (ParseException e) {
            System.out.println("Error to parse string");
        }
    }

    public boolean isSpecialsPresent() {
        return specials != null && specials.size() != 0;
    }
}
