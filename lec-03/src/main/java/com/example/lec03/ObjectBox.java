package com.example.lec03;

import java.util.Collection;
import java.util.Objects;

public class ObjectBox {

    Collection<Object> collection;

    public ObjectBox(Collection<Object> collection) {
        this.collection = collection;
    }

    public boolean addObject(Object o) {
        return collection.add(o);
    }


    public boolean deleteObject(Object o) {
        return collection.remove(o);
    }

    public void dump() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return "Box{" +
                "collection=" + collection +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObjectBox objectBox = (ObjectBox) o;
        return Objects.equals(collection, objectBox.collection);
    }

    @Override
    public int hashCode() {
        return Objects.hash(collection);
    }
}
