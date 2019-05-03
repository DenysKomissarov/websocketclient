package entities;

import java.util.List;

public class ResponseMessage {

    private String route;
    private String command;
    private String status;
    private Long currentTime;
    private Long timeLag;
    private String desc;
    private Params params;
    private String interviewId;
    private Object content;
    private List<TrackInfo> tracks;
    private String mode;

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Long currentTime) {
        this.currentTime = currentTime;
    }

    public Long getTimeLag() {
        return timeLag;
    }

    public void setTimeLag(Long timeLag) {
        this.timeLag = timeLag;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Params getParams() {
        return params;
    }

    public void setParams(Params params) {
        this.params = params;
    }

    public String getInterviewId() {
        return interviewId;
    }

    public void setInterviewId(String interviewId) {
        this.interviewId = interviewId;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public List<TrackInfo> getTracks() {
        return tracks;
    }

    public void setTracks(List<TrackInfo> tracks) {
        this.tracks = tracks;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}
