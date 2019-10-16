public enum MessagePriority {

    playlist_state("0"),
    user_join_event("3"),
    user_leave_event("3"),
    user_join_playlist("2"),
    user_leave_playlist("2"),
    event_start("3"),
    event_end("3"),
    playlist_command("1"),
    playlist_end("2");

    MessagePriority(String name) {
        this.name = name;
    }

    private final String name;

    public String getName() {
        return name;
    }
}
