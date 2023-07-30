package com.task.task4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class Task4 {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Неверное количество аргументов");
            return;
        }

        String inputFile = args[0];

        try {

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            String line;
            long linesCount;
            try (Stream<String> s = Files.lines(Path.of(inputFile), StandardCharsets.UTF_8)) {
                linesCount = s.count();
            }
            int[] nums = new int[(int)linesCount];
            int i = 0;
            while ((line = reader.readLine()) != null ){
                 nums[i] = Integer.parseInt(line);
                 i++;
            }
            reader.close();

            int minMoves = findMinMoves(nums);
            System.out.println("Минимальное количество ходов: " + minMoves);
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла");
        }
    }

    private static int findMinMoves(int[] nums) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        int minMoves = Integer.MAX_VALUE;
        for (int target = min; target <= max; target++) {
            int moves = 0;
            for (int num : nums) {
                moves += Math.abs(num - target);
            }
            minMoves = Math.min(minMoves, moves);
        }

        return minMoves;
    }


}
