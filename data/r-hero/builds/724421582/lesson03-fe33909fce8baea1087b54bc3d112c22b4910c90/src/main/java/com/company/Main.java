package com.company;

import com.company.util.Checker;
import com.company.util.Game;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        int c = calculator.add(5, 5);
        System.out.println(c);
        int d = calculator.minus(10, 10);
        System.out.println(d);
        long f = calculator.multiply(10, 10);
        System.out.println(f);
        double j = calculator.division(10, 10);
        System.out.println(j);

        Checker checker = new Checker();
        checker.isEven(20);
        checker.isOdd(99);


        Game.toGame();


    }
}