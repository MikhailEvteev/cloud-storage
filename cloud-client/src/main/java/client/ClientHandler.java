package client;

import java.util.List;

public class ClientHandler {
    private String userDirectory;
    private List<Callback> callbackList;

    public ClientHandler(List<Callback> callbackList) {
        this.callbackList = callbackList;
    }

    public enum State {
        IDLE, NAME_LENGTH, NAME, FILE_LENGTH, FILE

    }



}
