package messages;

public final class WebSocketMessages {

    public static final String pauseMessage = "{\"route\":\"clientStreamPause\", \"command\":\"send\", \"params\":{\"itemId\":\"a111111111111111111111111111111\"}}";

    public static final String startMessage = "{\"route\":\"clientStreamStart\", \"command\":\"send\", \"params\":{\"itemId\":\"6b4e7544-7784-49a9-954a-33671566a894\"}}"; // when player status pause

    public static final String trackFromStartMessage = "{\"route\":\"clientTrackFromStart\", \"command\":\"send\", \"params\":{\"itemId\":\"a111111111111111111111111111111\"}}";

    public static final String trackStateMessage = "{\"route\":\"clientTrackState\", \"command\":\"send\", \"params\":{\"itemId\":\"a111111111111111111111111111111\"}}";

    public static final String prewTrackMessage = "{\"route\":\"clientPrevTrack\", \"command\":\"send\", \"params\":{\"itemId\":\"a111111111111111111111111111111\"}}";

    public static final String nextTrackMessage = "{\"route\":\"clientNextTrack\", \"command\":\"send\", \"params\":{\"itemId\":\"a111111111111111111111111111111\"}}";

    public static final String currentTrackSpecificTimeMessage = "{\"route\":\"clientCurrentTrackSpecificTime\", \"command\":\"send\", \"params\":{\"itemId\":\"a111111111111111111111111111111\", \"trackId\":\"tr111111111111111111\", \"specificTime\":-10641000}}";

    public static final String authorizedMessage = "{\"route\":\"clientAuth\", \"userId\":\"0744ba45-dbff-4cb4-ba76-b08bc0bc01\" , \"eventId\":\"0098fbfd-135b-463a-80aa-03820e0661e6\"}";


    // interview messages

    public static final String startInterviewMessage = "{\"interviewId\":\"serverStreamStart1502125355635\",\"route\":\"clientStreamStart\",\"command\":\"interview\"}";


    public static final String pauseRoute = "clientStreamPause";

    public static final String startRoute = "clientStreamStart"; // when player status pause

    public static final String trackFromStartRoute = "clientTrackFromStart";

    public static final String trackStateRoute = "clientTrackState";

    public static final String prewTrackRoute = "clientPrevTrack";

    public static final String nextTrackRoute = "clientNextTrack";


}
