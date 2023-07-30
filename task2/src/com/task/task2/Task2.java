package com.task.task2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

public class Task2 {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Неверное количество аргументов");
            return;
        }

        Path circleFile = Path.of(args[0]);
        Path pointsFile = Path.of(args[1]);

        try {
            // Считываем координаты центра окружности и его радиус из файла
            BufferedReader circleReader = new BufferedReader(new FileReader(circleFile.toFile()));
            String circleLine = circleReader.readLine();
            String[] circleCoordinates = circleLine.split(" ");
            float radius = Float.parseFloat(circleReader.readLine());
            circleReader.close();
            float circleCenterX = Float.parseFloat(circleCoordinates[0]);
            float circleCenterY = Float.parseFloat(circleCoordinates[1]);

            // Считываем координаты точек из файла
            BufferedReader pointsReader = new BufferedReader(new FileReader(pointsFile.toFile()));
            String pointLine;
            int countPoints = 0;
            while ((pointLine = pointsReader.readLine()) != null ) {
                if(countPoints > 100) {
                    System.out.println("mnogo");
                }
                String[] pointCoordinates = pointLine.split(" ");
                float pointX = Float.parseFloat(pointCoordinates[0]);
                float pointY = Float.parseFloat(pointCoordinates[1]);

                // Рассчитываем расстояние от точки до центра окружности
                float distance = (float) Math.sqrt(Math.pow(pointX - circleCenterX, 2) + Math.pow(pointY - circleCenterY, 2));

                // Определяем положение точки относительно окружности
                if (distance == radius) {
                    System.out.println("0");
                } else if (distance < radius) {
                    System.out.println("1");
                } else {
                    System.out.println("2");
                }
                countPoints++;
            }
            pointsReader.close();
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файлов ][");
        }
    }
}
