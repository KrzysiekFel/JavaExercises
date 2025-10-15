package org.coding.generics;

public class Main {
    public static void main(String[] args) {

        MyStructure<String, Integer> structure = new MyStructure<>();
        structure.addPair("a", 1);
        structure.addPair("b", 1);

        System.out.println(structure.getValue("a"));
    }





}
