package com.homework4.count_map;

import java.util.HashMap;
import java.util.Map;


public class CountMapImpl<E> implements CountMap<E> {

    private final Map<E, Integer> map;

    public CountMapImpl() {
        this.map=new HashMap<E,Integer>();
    }

    @Override
    public void add(E o) {
        if (!map.containsKey(o)) {
            map.put(o, 1);
            return;
        }
        map.put(o, map.get(o) + 1);
    }

    @Override
    public int getCount(E o) {
        if (map.containsKey(o))
            return map.get(o);

        return 0;
    }

    @Override
    public int remove(E o) {
        if (map.containsKey(o))
            return map.remove(o);

        return 0;

    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public void addAll(CountMap<E> source) {
        for (E elem : source.toMap().keySet())
            add(elem);


    }

    @Override
    public Map<E, Integer> toMap() {
        return map;
    }

    @Override
    public void toMap(Map<E, Integer> destination) {
        destination.clear();
        for (E elem : toMap().keySet()
                ) {
            if (!destination.containsKey(elem))
                destination.put(elem, 1);
            else
                destination.put(elem, destination.get(elem) + 1);
        }
    }
}
