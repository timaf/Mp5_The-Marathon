package at.refugeescode.Mp5_TheMarathon.model;

import java.time.Duration;

public class Runner {
    private String name;
    private Duration time;

    public Runner() {
    }


    public Runner(String name, Duration time) {
        this.name = name;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public Duration getTime() {
        return time;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTime(Duration time) {
        this.time = time;
    }

}

