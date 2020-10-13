package com.company.util;

import java.util.Scanner;

public class Game {
    public static void toGame(){
        System.out.println("Введите число от 1 до 10");
        Scanner scanner = new Scanner(System.in);
        int nu = scanner.nextInt();
        int compNumber = (int) (Math.random()* 10);

        if (nu == compNumber) {
            System.out.println("Комп угадал ваше число ");}
        else {System.out.println("Комп не угадал ваше число");}
    }
}
