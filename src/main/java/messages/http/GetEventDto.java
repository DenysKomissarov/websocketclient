package messages.http;

public class GetEventDto {


    public String eventId;

    public String leaderId;

    public String leaderName;

    public String name;

    public String datetime;

    public String endTime;

    public Double cost;

    public Integer categoryId;

    public String  categoryName;

    public Integer timezoneId;

    public String description;

    public String locationReception;

    public String typeName;

    public String duration;

    public Double locationX;

    public Double locationY;

    public String status;

    public String date;

    public String time;

    public String utcTime;

    public String djMusicStyle;

    public String commentsFromLeader;

    public double eventRating;

    public int playlists;

    public String sinopsis;

    public String locationName;

    public boolean bookStatus;

    public Boolean isValidated;

    public Boolean isPublic;

    public String mediaId;

    public String mediaName;

    public String promoCode;

    public Double mediaCost;

    public GetEventDto(String eventId, String leaderId, String leaderName, String name, String datetime, String endTime, Double cost, Integer categoryId, String categoryName, Integer timezoneId, String description, String locationReception, String typeName, String duration, Double locationX, Double locationY, String status, String date, String time, String utcTime, String djMusicStyle, String commentsFromLeader, double eventRating, int playlists, String sinopsis, String locationName, boolean bookStatus, Boolean isValidated, Boolean isPublic, String mediaId, String mediaName, String promoCode, Double mediaCost) {
        this.eventId = eventId;
        this.leaderId = leaderId;
        this.leaderName = leaderName;
        this.name = name;
        this.datetime = datetime;
        this.endTime = endTime;
        this.cost = cost;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.timezoneId = timezoneId;
        this.description = description;
        this.locationReception = locationReception;
        this.typeName = typeName;
        this.duration = duration;
        this.locationX = locationX;
        this.locationY = locationY;
        this.status = status;
        this.date = date;
        this.time = time;
        this.utcTime = utcTime;
        this.djMusicStyle = djMusicStyle;
        this.commentsFromLeader = commentsFromLeader;
        this.eventRating = eventRating;
        this.playlists = playlists;
        this.sinopsis = sinopsis;
        this.locationName = locationName;
        this.bookStatus = bookStatus;
        this.isValidated = isValidated;
        this.isPublic = isPublic;
        this.mediaId = mediaId;
        this.mediaName = mediaName;
        this.promoCode = promoCode;
        this.mediaCost = mediaCost;
    }

    public GetEventDto() {
    }
}
