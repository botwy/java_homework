package com.sgu2056;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeSet;

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text;
        text = scanner.nextLine();
       // text += scanner.nextLine();
        String[] arr = text.split("[\\n \\r \\t]+");
        HashMap<String, Integer> hashMap_word = new HashMap<String, Integer>();
        TreeSet<String> treeSet_word = new TreeSet<String>();

        for (String word : arr
                ) {
            word=word.toLowerCase();
            //       System.out.println(word);
            //list_word.add(word);
            if (hashMap_word.containsKey(word)) {
                Integer new_value = hashMap_word.get(word) + 1;
                hashMap_word.put(word, new_value);
            } else hashMap_word.put(word, 1);
        }

        int max_item=0;
        for (Map.Entry<String, Integer> item : hashMap_word.entrySet()
                ) {
            System.out.println(item.getKey() + " - " + item.getValue());
            if (item.getValue()>max_item) max_item=item.getValue();
        }
        System.out.println(max_item);

        for (Map.Entry<String, Integer> item : hashMap_word.entrySet()
                ) {
            if (item.getValue()==max_item) treeSet_word.add(item.getKey());
        }

        for (String str:treeSet_word
             ) {
            System.out.println(str);
        }

    }
}