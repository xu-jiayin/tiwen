package com.example.tiwen.httptest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author XUJIAYIN
 * @create 2022-03-17-18:27
 */
public class Test {
    public static void main(String[] args)throws IOException, InterruptedException {
        String PATH = "C:\\Users\\xu\\Desktop\\index\\index.py";
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
