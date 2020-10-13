package com.mamalimomen.base.controllers.utilities;

import com.mamalimomen.base.controllers.guis.DialogProvider;
import com.mamalimomen.base.dtos.BaseDTO;
import com.mamalimomen.controllers.utilities.AppManager;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public final class ClientManager {
    private Socket socket;
    private ObjectOutputStream outputObject;
    private ObjectInputStream inputObject;
    private final short port = 8000;
    private final String server = "localhost";

    public ClientManager() {
        this.turnOnClient();
    }

    private void turnOnClient() {
        try {
            socket = new Socket(server, port);
            DialogProvider.createAndShowTerminalMessage("%s %s %s %d%n", "Connect to", server, "by port", port);
            outputObject = new ObjectOutputStream(socket.getOutputStream());
            inputObject = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            DialogProvider.createAndShowErrorDialog("Connection is lost!", "Server not found");
            System.exit(-1);
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
            DialogProvider.createAndShowErrorDialog("Connection is lost!", "Server not found");
            System.exit(-1);
        }
        return null;
    }
}
