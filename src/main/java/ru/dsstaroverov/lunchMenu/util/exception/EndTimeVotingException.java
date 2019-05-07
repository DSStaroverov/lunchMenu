package ru.dsstaroverov.lunchMenu.util.exception;

public class EndTimeVotingException extends RuntimeException {
    public EndTimeVotingException() {
        super("Voting close");
    }
}
