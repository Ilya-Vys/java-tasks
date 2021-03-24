package com.example.lec09.task1.sorters;

import java.util.Collections;
import java.util.List;

public class CollectionSorterImpl<T extends Comparable<T>> implements CollectionSorter {

    private List<T> list;

    public CollectionSorterImpl() {
    }

    public CollectionSorterImpl(List<T> list) {
        this.list = list;
    }

    @Override
    public void sort() {
        Collections.sort(list);
    }

    @Override
    public void bubbleSort() {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - i - 1; j++) {
                if (list.get(j).compareTo(list.get(j + 1)) > 0){
                    T tempVar = list.get(j + 1);
                    list.set(j + 1, list.get(j));
                    list.set(j, tempVar);
                }
            }
        }

    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

}
