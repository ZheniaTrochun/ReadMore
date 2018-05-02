package com.yevhenii.kpi.readmore.model;

public enum State {

    TODO("Todo"), IN_PROGRESS("InProgress"), FINISHED("Finished");

    private String name;

    State(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
