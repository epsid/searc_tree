package ru.minnumber;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static ru.minnumber.Calculator.mem;


public class MinNaturalNumber {

    private void fileToMassive(String filePath) {

        Input newFile = new Input();
        newFile.buildNewFile(filePath);

        System.out.println("Файл успешно создан!");
        System.out.println("Поиск наименьшего натурального числа... ");

        long before = mem();
        long time = System.nanoTime();

        List<Integer> reference = new ArrayList<>(1_000_001);
        for (int i = 1; i <= 1_000_001; i++) {
            reference.add(i);
        }
        try (BufferedReader in = new BufferedReader(new FileReader(filePath))) {

            for (String part : in.readLine().split("\\s+")) {

                int i = Integer.parseInt(part);
                if (i > 0 && i < 1000001) {

                    reference.set(i - 1, null);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        int result = 1;
        for (int i = 0; i < 1000001; i++) {
            if (reference.get(i) != null) {
                result = reference.get(i);
                break;
            }
        }
        time = System.nanoTime() - time;
        System.out.println("Минимальное натуральное число: " + result);
        System.out.printf("Времени затрачено: %,9.3f s\n", time / 1_000_000_000.0);
        long after = mem();
        System.out.println("Памяти затрачено:      " + (after - before) / 1048576 + " MB");
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите существующую директорию для создания файла ( Например: C:// ) : ");
        String filePath = in.nextLine() + "randomNumbers.txt";
        MinNaturalNumber number = new MinNaturalNumber();
        number.fileToMassive(filePath);
    }
}