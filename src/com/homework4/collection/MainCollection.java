package com.homework4.collection;

import java.util.*;

public class MainCollection {

    public static void main(String[] args) {
        String text = "The root interface in the collection hierarchy. A collection represents a group of objects, known as its elements. Some collections allow duplicate elements and others do not. Some are ordered and others unordered. The JDK does not provide any direct implementations of this interface: it provides implementations of more specific subinterfaces like Set and List. This interface is typically used to pass collections around and manipulate them where maximum generality is desired. \n" +
                "Bags or multisets (unordered collections that may contain duplicate elements) should implement this interface directly. \n" +
                "All general-purpose Collection implementation classes (which typically implement Collection indirectly through one of its subinterfaces) should provide two \"standard\" constructors: a void (no arguments) constructor, which creates an empty collection, and a constructor with a single argument of type Collection, which creates a new collection with the same elements as its argument. In effect, the latter constructor allows the user to copy any collection, producing an equivalent collection of the desired implementation type. There is no way to enforce this convention (as interfaces cannot contain constructors) but all of the general-purpose Collection implementations in the Java platform libraries comply. \n" +
                "The \"destructive\" methods contained in this interface, that is, the methods that modify the collection on which they operate, are specified to throw UnsupportedOperationException if this collection does not support the operation. If this is the case, these methods may, but are not required to, throw an UnsupportedOperationException if the invocation would have no effect on the collection. For example, invoking the addAll(Collection) method on an unmodifiable collection may, but is not required to, throw the exception if the collection to be added is empty. \n" +
                "Some collection implementations have restrictions on the elements that they may contain. For example, some implementations prohibit null elements, and some have restrictions on the types of their elements. Attempting to add an ineligible element throws an unchecked exception, typically NullPointerException or ClassCastException. Attempting to query the presence of an ineligible element may throw an exception, or it may simply return false; some implementations will exhibit the former behavior and some will exhibit the latter. More generally, attempting an operation on an ineligible element whose completion would not result in the insertion of an ineligible element into the collection may throw an exception or it may succeed, at the option of the implementation. Such exceptions are marked as \"optional\" in the specification for this interface. \n" +
                "It is up to each collection to determine its own synchronization policy. In the absence of a stronger guarantee by the implementation, undefined behavior may result from the invocation of any method on a collection that is being mutated by another thread; this includes direct invocations, passing the collection to a method that might perform invocations, and using an existing iterator to examine the collection. \n" +
                "Many methods in Collections Framework interfaces are defined in terms of the equals method. For example, the specification for the contains(Object o) method says: \"returns true if and only if this collection contains at least one element e such that (o==null ? e==null : o.equals(e)).\" This specification should not be construed to imply that invoking Collection.contains with a non-null argument o will cause o.equals(e) to be invoked for any element e. Implementations are free to implement optimizations whereby the equals invocation is avoided, for example, by first comparing the hash codes of the two elements. (The Object.hashCode() specification guarantees that two objects with unequal hash codes cannot be equal.) More generally, implementations of the various Collections Framework interfaces are free to take advantage of the specified behavior of underlying Object methods wherever the implementor deems it appropriate. \n" +
                "This interface is a member of the Java Collections Framework.";

        String[] arr = text.split("\\p{P}?[\\n \\r \\t]+\"?");
        //  List<String> list_word = new ArrayList<String>();

        Comparator<String> byLengthAndTextComparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() < o2.length()) return -1;
                else if (o1.length() > o2.length()) return 1;
                else return 0;
            }
        }.thenComparing(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });


        TreeSet<String> treeSet_word = new TreeSet<String>(byLengthAndTextComparator);
        HashMap<String, Integer> hashMap_word = new HashMap<String, Integer>();
        for (String word : arr
                ) {
            //       System.out.println(word);
            //list_word.add(word);
            treeSet_word.add(word);
            if (hashMap_word.containsKey(word)) {
                Integer new_value = hashMap_word.get(word) + 1;
                hashMap_word.put(word, new_value);
            } else hashMap_word.put(word, 1);
        }

        for (String word : treeSet_word
                ) {
            System.out.println(word);
        }
        System.out.println("Количество слов: " + arr.length);

        System.out.println("Количество различных слов: " + treeSet_word.size());

        for (Map.Entry<String, Integer> item : hashMap_word.entrySet()
                ) {
            System.out.println(item.getKey() + " - " + item.getValue());
        }

    }
}
