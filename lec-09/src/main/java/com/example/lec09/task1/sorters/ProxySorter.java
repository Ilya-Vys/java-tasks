package com.example.lec09.task1.sorters;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.*;
import java.util.List;

import static java.lang.ClassLoader.getSystemClassLoader;


public class ProxySorter implements InvocationHandler {

    private final CollectionSorter sorter;
    private long timeResult;

    public ProxySorter(CollectionSorter sorter) {
        this.sorter = sorter;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long time = System.currentTimeMillis();
        Object object = method.invoke(sorter,args);
        timeResult = System.currentTimeMillis() - time;
        writeText(method);
        return object;
    }

    public long getTimeResult() {
        return timeResult;
    }

    private void writeText(Method method) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<? extends CollectionSorter> aClass = sorter.getClass();
        Method method1 = aClass.getMethod("getList");
        List<?> list = (List<?>) method1.invoke(sorter);
        int size = list.size();
        Class type = list.stream().findFirst().get().getClass();
        try (PrintWriter pw = new PrintWriter(new FileWriter("lec-09/src/main/resources/destination1.txt", true))) {
            pw.write(String.format("Result of method %s from List<%s> with size %d = %d ms \n"
                    , method.getName(), type, size, timeResult));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T> T proxyFactory(Object delegate) {
        return (T) Proxy.newProxyInstance(getSystemClassLoader(),
                delegate.getClass().getInterfaces(),
                new ProxySorter((CollectionSorter) delegate)
        );
    }

}
