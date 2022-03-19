package com.example.tiwen.start;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author XUJIAYIN
 * @create 2022-03-18-20:31
 */

public class startPy {
    public void PyService(){
        String PATH = "/tiwen/python/index.py";
        final ProcessBuilder processBuilder = new ProcessBuilder("python", PATH);
        processBuilder.redirectErrorStream(true);
        final Process process;
        try {
            process = processBuilder.start();
            final BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String s = null;
            while ((s = in.readLine()) != null) {
                System.out.println(s);
            }
            final int exitCode;
            try {
                exitCode = process.waitFor();
                System.out.println(exitCode == 0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)throws IOException, InterruptedException {
        String PATH = "/tiwen/python/index.py";
        final ProcessBuilder processBuilder = new ProcessBuilder("python", PATH);
        processBuilder.redirectErrorStream(true);

        final Process process = processBuilder.start();

        final BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));

        String s = null;
        while ((s = in.readLine()) != null) {
            System.out.println(s);
        }

        final int exitCode = process.waitFor();
        System.out.println(exitCode == 0);
    }
}
