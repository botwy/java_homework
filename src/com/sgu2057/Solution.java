package com.sgu2057;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = Integer.parseInt(scanner.nextLine());
        int min = 0;
        int prev_min=0;
        List<Integer> list = new ArrayList<>();
        List<Integer> result_list = new ArrayList<>();
        int prev_oper = -1;
        int qty_del = 0;
        for (int i = 0; i < count; i++) {
            String[] arr = scanner.nextLine().split("[\\s]+");

            if (Integer.parseInt(arr[0]) == 1) {
                int number = Integer.parseInt(arr[1]);
                if (i==0) min=number;

                    list.add(number);
                if (number < min || number==min) {
                    min = number;
                    prev_min=min;
                }
                prev_oper = 1;
            } else if (Integer.parseInt(arr[0]) == 2) {
                if (list.size() > 0) {
                    if (prev_oper == 1 && prev_min==-1) {
                        list.sort(new Comparator<Integer>() {
                            @Override
                            public int compare(Integer o1, Integer o2) {
                                return o1.compareTo(o2);
                            }
                        });
                        result_list.add(list.get(0));
                        list.remove(list.get(0));
                    }
                    else if (prev_oper == 1 && prev_min!=-1) {
                        result_list.add(min);
                        list.remove(min);

                    }else {
                        result_list.add(list.get(0));
                        list.remove(list.get(0));

                    }
                    min=prev_min;
                    prev_min=-1;
                    prev_oper = 2;
                }

            }
        }

        for (Integer result : result_list
                ) {
            System.out.println(result);
        }

    }


}