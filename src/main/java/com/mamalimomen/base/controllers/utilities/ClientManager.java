package com.mamalimomen.base.controllers.utilities;

import com.mamalimomen.base.controllers.guis.DialogProvider;
import com.mamalimomen.base.dtos.BaseDTO;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public final class ClientManager {
    private Socket socket;
    private ObjectOutputStream outputObject;
    private ObjectInputStream inputObject;
    private final short port = 8000;

    public ClientManager() {
        this.turnOnClient();
    }

    private void turnOnClient() {
        try {
            socket = new Socket("localhost", port);
            DialogProvider.createAndShowTerminalMessage("%s %d%n", "Connect to server by port", port);
            outputObject = new ObjectOutputStream(socket.getOutputStream());
            inputObject = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void turnOffClient() {
        try {
            inputObject.close();
            outputObject.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object sendRequestAndGetAnswer(MAMP<? extends BaseDTO<Long>> request) {
        try {
            outputObject.writeObject(request);
            return inputObject.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
