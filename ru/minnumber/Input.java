package ru.minnumber;

import java.io.*;
import java.util.Random;


class Input {
    void buildNewFile(String path) {
        int max = 1_000_000_000;
        int min = -1_000_000_000;

        /*int mas[]= new int[1000000];
        for (int i=0;i<1000000;i++){
            mas[i]=i;
        }*/

        Random rand = new Random();
        StringBuilder s = new StringBuilder();
        for (int i = 1; i < 1_000_001; i++) {
            int randomize = (rand.nextInt((max - min) + 1) + min);
            s.append(randomize).append(" ");

            /* s.append(mas[i-1]).append(" ");*/

        }
        File file = new File(path);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(s.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
