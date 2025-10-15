package org.coding.generics;

import java.util.ArrayList;
import java.util.List;

public class MyStructure<K, V> {
    List<Pair<K, V>> structure;

    public MyStructure() {
        structure  = new ArrayList<>();
    }

    public void addPair(K key, V value) {
        this.structure.add(new Pair<>(key, value));
    }

    public V getValue(K key) {
        for (Pair<K, V> elem : structure) {
            if (elem.getKey().equals(key)) {
                return elem.getValue();
            }
        }
        return null;
    }

}
