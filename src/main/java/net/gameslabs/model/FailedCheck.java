package net.gameslabs.model;

public class FailedCheck extends RuntimeException {
    public FailedCheck(String info) {
        super(info);
    }
}
