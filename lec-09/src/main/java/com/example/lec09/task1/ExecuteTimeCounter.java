package com.example.lec09.task1;

import com.example.lec09.task1.sorters.CollectionSorter;
import com.example.lec09.task1.sorters.CollectionSorterImpl;
import com.example.lec09.task1.sorters.ProxySorter;
import com.example.lec09.task1.suppliers.AbstractSupplier;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;


public class ExecuteTimeCounter {

    private final CollectionSorterImpl sorter;
    private ProxySorter proxy;
    private final AbstractSupplier supplier;

    public ExecuteTimeCounter(AbstractSupplier supplier) {
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
            sorter.setList((List) supplier.get());
            proxySorter.sort();
            sortedList.add(proxy.getTimeResult());
            sorter.setList((List) supplier.get());
            proxySorter.bubbleSort();
            bubbleSortedList.add(proxy.getTimeResult());
        }

        print(getResult(sortedList), getResult(bubbleSortedList), count);
    }

    private long getResult(List<Long> longs){
        return longs.stream().reduce((aLong, aLong2) -> (aLong + aLong2) / 2).get();
    }

    private void print(long sortResult, long bubbleSortResult, int count){
        System.out.println(String.format("Average result of sort list of %d elements = %d ms", count, sortResult));
        System.out.println(String.format("Average result of bubble sort list of %d elements = %d ms", count, bubbleSortResult));
    }

}
