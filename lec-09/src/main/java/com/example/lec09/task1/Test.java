package com.example.lec09.task1;


import com.example.lec09.task1.sorters.CollectionSorter;
import com.example.lec09.task1.sorters.CollectionSorterImpl;
import com.example.lec09.task1.sorters.ProxySorter;
import com.example.lec09.task1.suppliers.IntegersSupplier;

import java.util.Arrays;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        List<String> strings = Arrays.asList("John", "Bob", "Mark", "Jason");
        CollectionSorter sorter = ProxySorter.proxyFactory(new CollectionSorterImpl<>(strings));
        sorter.bubbleSort();

        ExecuteTimeCounter counter = new ExecuteTimeCounter(new IntegersSupplier());
        counter.countTime(10000);
     //   counter.countTime(100000);
      //  counter.countTime(1000000);

    }
}
