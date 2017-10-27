package com.homework5.read_content;

import java.util.Scanner;

public class Main {

    public static void main(String... args) {
        String url_str = getUrlFromConsoleInput();
        UrlImpl url = new UrlImpl();
        url.readContent(url_str);


    }

    public static boolean validate(String url) {
        return true;

    }

    public static boolean tryGet(String url) {
        return true;

    }

    public static String getUrlFromConsoleInput() {
        System.out.println("Введите url:");
        Scanner scanner = new Scanner(System.in);
        String url_str = scanner.nextLine();
        if (!validate(url_str) || !tryGet(url_str))
            getUrlFromConsoleInput();

        return url_str;
    }


}
