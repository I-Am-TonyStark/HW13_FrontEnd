package com.mamalimomen.base.controllers.utilities;

import java.util.Scanner;

public final class SingletonScanner {
    private final static Scanner sc = new Scanner(System.in);

    private SingletonScanner() {
    }

    public static synchronized int readInteger() {
        return sc.nextInt();
    }

    public static synchronized String readLine() {
        return sc.next() + sc.nextLine();
    }

    public static void clearBuffer(){
        sc.nextLine();
    }

    public static synchronized String readParagraph() {
        String paragraph = "";
        String line = "";
        do {
            paragraph += line + "\n";
            line = sc.nextLine();
        } while (!line.trim().equalsIgnoreCase("\\/"));
        return paragraph.trim();
    }
}