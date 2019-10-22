package entities;

import com.uidance.websocket.config.MessageConst;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private ResponseMessage() {
    }

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

    public Long getTimeLag() {
        return timeLag;
    }

    public void setTimeLag(Long timeLag) {
        this.timeLag = timeLag;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public static ResponseMessage error(String responseRoute, String desc) {

        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setRoute(responseRoute);
        responseMessage.setStatus(MessageConst.statusError);
        responseMessage.setDesc(desc);
        return responseMessage;
    }

    public static ResponseMessage success(String responseRoute) {

        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setRoute(responseRoute);
        responseMessage.setStatus(MessageConst.statusSuccess);
        return responseMessage;
    }

    public static ResponseMessage success(String responseRoute, String command, long time) {

        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setRoute(responseRoute);
        responseMessage.setCommand(command);
        responseMessage.setCurrentTime(time);
        responseMessage.setStatus(MessageConst.statusSuccess);
        return responseMessage;
    }

    public static ResponseMessage broadcast(String responseRoute, String eventId) {

        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setRoute(responseRoute);
        responseMessage.setCommand(MessageConst.commandBroadcast);
        Params params = new Params();
        params.setEventId(eventId);
        responseMessage.setParams(params);
        return responseMessage;
    }

    public static ResponseMessage buildQuestion(String responseRoute, String interviewId) {
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setRoute(responseRoute);
        responseMessage.setCommand(MessageConst.commandInterview);
        responseMessage.setInterviewId(interviewId);
        return responseMessage;
    }

    public static ResponseMessage getEventRoom(String responseRoute, EventRoomRepoItem eventRoomRepoItem) {
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setRoute(responseRoute);
        responseMessage.setCommand(MessageConst.commandSend);
        responseMessage.setContent(eventRoomRepoItem);
        return responseMessage;
    }

    public static ResponseMessage playerSuccessToLeader(String responseRoute) {
        return success(responseRoute, MessageConst.commandSend, System.currentTimeMillis());
    }

    public static ResponseMessage getTrackState(String responseRoute, String itemId, TrackStatus trackStatus) {
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setRoute(responseRoute);
        responseMessage.setCommand(MessageConst.commandSend);
        responseMessage.setCurrentTime(System.currentTimeMillis());
        Params params = new Params();
        params.setItemId(itemId);
        responseMessage.setParams(params);
        responseMessage.setContent(trackStatus);
        return responseMessage;
    }

    public static ResponseMessage getTrackState(String responseRoute, TrackStatus trackStatus) {
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setRoute(responseRoute);
        responseMessage.setCommand(MessageConst.commandSend);
        responseMessage.setCurrentTime(System.currentTimeMillis());
        responseMessage.setContent(trackStatus);
        return responseMessage;
    }

    public static ResponseMessage partStartListen(String responseRoute, List<TrackInfo> tracks) {
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setRoute(responseRoute);
        responseMessage.setStatus(MessageConst.statusSuccess);
        responseMessage.setTracks(tracks);
        return responseMessage;
    }

    public static ResponseMessage fullMessage(String responseRoute, String command, long time,
                                              Params params, Object content) {
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setRoute(responseRoute);
        responseMessage.setCommand(command);
        responseMessage.setCurrentTime(time);
        responseMessage.setParams(params);
        responseMessage.setContent(content);
        return responseMessage;
    }

    public static ResponseMessage startAction(String responseRoute, long maxLag, long currentTime, Object content) {
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setRoute(responseRoute);
        responseMessage.setCommand(MessageConst.commandBroadcast);
        responseMessage.setTimeLag(maxLag);
        responseMessage.setCurrentTime(currentTime);
        responseMessage.setContent(content);
        return responseMessage;
    }

    public static ResponseMessage userActionBroadcast(String responseRoute, String eventId,
                                                      String itemId, String userId, String userName) {
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setRoute(responseRoute);
        responseMessage.setCommand(MessageConst.commandBroadcast);
        Params params = new Params();
        params.setEventId(eventId);
        responseMessage.setParams(params);
        Map<String, String> content = new HashMap<>(3);
        content.put(MessageConst.listenName, userName);
        content.put(MessageConst.userId, userId);
        content.put(MessageConst.itemId, itemId);
        responseMessage.setContent(content);
        return responseMessage;
    }

    public static ResponseMessage userActionBroadcast(String responseRoute, String eventId,
                                                      String userId, String userName) {
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setRoute(responseRoute);
        responseMessage.setCommand(MessageConst.commandBroadcast);
        Params params = new Params();
        params.setEventId(eventId);
        responseMessage.setParams(params);
        Map<String, String> content = new HashMap<>(2);
        content.put(MessageConst.listenName, userName);
        content.put(MessageConst.userId, userId);
        responseMessage.setContent(content);
        return responseMessage;
    }

    public static ResponseMessage partCloseSocket(String responseRoute, String eventId, String userId, String userName, String mode) {
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setRoute(responseRoute);
        responseMessage.setCommand(MessageConst.commandBroadcast);
        Params params = new Params();
        params.setEventId(eventId);
        responseMessage.setParams(params);
        Map<String, String> content = new HashMap<>(2);
        content.put(MessageConst.listenName, userName);
        content.put(MessageConst.userId, userId);
        responseMessage.setContent(content);
        responseMessage.setStatus(MessageConst.statusSuccess);
        responseMessage.setMode(mode);
        return responseMessage;
    }
}
