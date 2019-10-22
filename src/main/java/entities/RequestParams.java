package entities;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestParams {

    private String itemId;
    private Long specificTime;
    private String trackId;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public Long getSpecificTime() {
        return specificTime;
    }

    public void setSpecificTime(Long specificTime) {
        this.specificTime = specificTime;
    }

    public String getTrackId() {
        return trackId;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }

    @Override
    public String toString() {
        return "RequestParams{" +
                "itemId='" + itemId + '\'' +
                ", specificTime=" + specificTime +
                ", trackId='" + trackId + '\'' +
                '}';
    }
}
