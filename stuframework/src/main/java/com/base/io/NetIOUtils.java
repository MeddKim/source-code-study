package com.base.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class NetIOUtils {

    public static void close(BufferedReader in, PrintWriter out, Socket socket){

        if(in != null){
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                in = null;
            }
        }

        if(out != null){
            try {
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                out = null;
            }
        }
        if(socket != null){
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                socket = null;
            }
        }
    }

    public static void close(BufferedReader in, BufferedWriter out, Socket socket){

        if(in != null){
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                in = null;
            }
        }
        if(out != null){
            try {
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                out = null;
            }
        }
        if(socket != null){
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                socket = null;
            }
        }
    }
}
