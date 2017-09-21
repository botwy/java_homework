package com.sgu2057_2;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = Integer.parseInt(scanner.nextLine());
       TreeSet<Integer> set = new TreeSet<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        List<Integer> result_list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            String[] arr = scanner.nextLine().split("[\\s]+");

            if (Integer.parseInt(arr[0]) == 1) {
                int number = Integer.parseInt(arr[1]);
               set.add(number);
            } else if (Integer.parseInt(arr[0]) == 2) {

                if (set.size() > 0) {

                    result_list.add(set.first());


                }

            }
        }

        for (Integer result : result_list
                ) {
            System.out.println(result);
        }

    }


}