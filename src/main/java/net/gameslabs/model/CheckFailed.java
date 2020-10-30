package net.gameslabs.model;

public class CheckFailed extends RuntimeException {
    public CheckFailed(String info) {
        super(info);
    }
}
