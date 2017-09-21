package com.sgu2056_2;

import java.util.*;

public class Solution {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String text = "";
        while (scanner.hasNextLine())
            text += scanner.nextLine();

        String[] arr = text.split("[\\n \\r \\t]+");
        List<String> list_word = new ArrayList<>();
        List<Integer> list_qwy = new ArrayList<>();
        int max = 0;
        List<String> list_max_word = new ArrayList<>();
        for (String word : arr
                ) {
            word = word.toLowerCase();

            int index = findWordInList(word, list_word);
            if (index >= 0)
                list_qwy.set(index, list_qwy.get(index) + 1);
            else {
                list_word.add(word);
                list_qwy.add(1);
            }
            if (index >= 0 && list_qwy.get(index) > max) {
                max = list_qwy.get(index);
                list_max_word.clear();
                list_max_word.add(word);
            } else if (index >= 0 && list_qwy.get(index) == max) {
                list_max_word.add(word);
            } else if (index == -1 && max == 0) {
                list_max_word.add(word);
                max = 1;
            }
        }
        list_max_word.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });


        for (String str : list_max_word
                ) {
            System.out.println(str);
        }

    }

    static public int findWordInList(String word, List<String> list) {
        for (int i = 0; i < list.size(); i++
                ) {

            if (list.get(i).equals(word))
                return i;
        }
        return -1;
    }
}