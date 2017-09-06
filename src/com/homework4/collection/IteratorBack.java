package com.homework4.collection;

import java.util.List;

public class IteratorBack<E>{
 List<E> list;
 int cursor=0;

    public IteratorBack(List<E> list) {
        this.list = list;
        cursor=list.size();
    }

    public boolean hasPrev() {
        if (cursor>0)
            if(list.get(cursor-1)!=null)
                return true;

        return false;
    }

    public E prev() {
        cursor--;
        return list.get(cursor);
    }
}
