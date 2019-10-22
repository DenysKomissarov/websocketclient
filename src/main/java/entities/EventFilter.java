package entities;

public enum EventFilter {
    FUTURE("future"),
    RUN_NOW("run now"),
    PAST("past");

    EventFilter(String name) {
        this.name = name;
    }

    private final String name;

    public String getName() {
        return name;
    }
}
