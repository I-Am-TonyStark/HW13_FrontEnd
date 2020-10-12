package com.mamalimomen.base.controllers.utilities;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public final class IOManager {

    private IOManager() {
    }

    public static synchronized <T extends Serializable> boolean writeObjectsToFile(String filePath, T... objects) {
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }

        try (ObjectOutputStream outputObject = new ObjectOutputStream(new FileOutputStream(new File(filePath)))) {

            for (T t : objects) {
                outputObject.writeObject(t);
            }

            outputObject.flush();

            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static synchronized <T extends Serializable> List<T> readObjectsFromFile(String filePath) {
        List<T> tList = new ArrayList<>();

        try (ObjectInputStream inputObject = new ObjectInputStream(new FileInputStream(new File(filePath)))) {

            while (inputObject.available() != 0) {
                tList.add((T) inputObject.readObject());
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return tList;
    }
}
