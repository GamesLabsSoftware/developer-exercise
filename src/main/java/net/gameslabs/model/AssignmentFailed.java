package net.gameslabs.model;

public class AssignmentFailed extends RuntimeException {
    public AssignmentFailed(String info) {
        super(info);
    }
}
