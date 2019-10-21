package messages.http;

import java.util.List;

public class ResultStatus<T> {

    public ResultStatus() {
        this.status = Status.error;
    }

    public ResultStatus(Status result) {
        this.status = result;
    }

    public ResultStatus(Status result, String desc) {
        this.status = result;
        this.desc = desc;
    }

    public ResultStatus(String id, Status result) {
        this.id = id;
        this.status = result;
    }

    public ResultStatus(Status result, String desc, String userId) {
        this.status = result;
        this.desc = desc;
        this.userId = userId;
    }

    public ResultStatus(Status result, String desc, List<T> list) {
        this.status = result;
        this.desc = desc;
        this.list = list;
    }

    public ResultStatus(Status result, String desc, String eventId, String itemId) {
        this.status = result;
        this.desc = desc;
        this.eventId = eventId;
        this.itemId = itemId;
    }

    public ResultStatus(Status result, String desc, T content) {
        this.status = result;
        this.desc = desc;
        this.content = content;
    }

    public enum Status {
        error, success
    }

    public Status status;

    public String desc;

    public String userId;

    public String eventId;

    public String itemId;

    public List<T> list;

    public T content;

    public String id;
}
