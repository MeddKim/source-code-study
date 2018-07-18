package com.base.socket.bio;

import java.util.Scanner;

public class TestClient {
    public static void main(String[] args) {
//        BIOClient.send("你好");
        Scanner scanner = new Scanner(System.in);
        System.out.println(scanner.nextLine());

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Scanner scanner = new Scanner(System.in);
                System.out.println(scanner.nextLine());
            }
        });

        thread.start();
    }
}
