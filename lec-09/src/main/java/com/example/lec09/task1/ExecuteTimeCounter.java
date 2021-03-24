package com.example.lec09.task1;

import com.example.lec09.task1.sorters.CollectionSorter;
import com.example.lec09.task1.sorters.CollectionSorterImpl;
import com.example.lec09.task1.sorters.ProxySorter;
import com.example.lec09.task1.suppliers.AbstractSupplier;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;


public class ExecuteTimeCounter<T extends Comparable<T>>{

    private final CollectionSorterImpl<T> sorter;
    private ProxySorter proxy;
    private final AbstractSupplier<List<T>> supplier;

    public ExecuteTimeCounter(AbstractSupplier<List<T>> supplier) {
        this.sorter = new CollectionSorterImpl<>();
        this.proxy = new ProxySorter(sorter);
        this.supplier = supplier;
    }


    private CollectionSorter getProxy(){
        return (CollectionSorter) Proxy.newProxyInstance(
                Test.class.getClassLoader(),
                sorter.getClass().getInterfaces(),
                proxy
        );
    }

    public void countTime(int count){
        supplier.setCount(count);
        CollectionSorter proxySorter = getProxy();
        List<Long> sortedList = new ArrayList<>();
        List<Long> bubbleSortedList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            sorter.setList(supplier.get());
            proxySorter.sort();
            sortedList.add(proxy.getTimeResult());
            sorter.setList(supplier.get());
            proxySorter.bubbleSort();
            bubbleSortedList.add(proxy.getTimeResult());
        }

        print(getResult(sortedList), getResult(bubbleSortedList), count);
    }

    private long getResult(List<Long> longs){
        int size = longs.size();
        return longs.stream().reduce(Long::sum).get() / size;
    }

    private void print(long sortResult, long bubbleSortResult, int count){
        System.out.println(String.format("Average result of sort list of %d elements = %d ms", count, sortResult));
        System.out.println(String.format("Average result of bubble sort list of %d elements = %d ms", count, bubbleSortResult));
    }

}
