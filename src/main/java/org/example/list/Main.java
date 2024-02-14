package org.example.list;

public class Main {
    public static void main(String[] args) {
        MyArrayList<String> myArrayList = new MyArrayList<>();
        for (int i = 0; i < 11; i++) {
            myArrayList.add((int) (Math.random() * 50) + " item");
        }
        MyArrayList.bubbleSort(myArrayList);
        System.out.println(myArrayList);

        myArrayList.add(1, "another item");

        MyArrayList newTypeList = new MyArrayList(myArrayList);

        System.out.println("Collection from constructor with collection of another type");
        System.out.println(newTypeList);

        System.out.println("----------------");
        newTypeList.bubbleSort();
        System.out.println(newTypeList);
        myArrayList.addAll(newTypeList);
        myArrayList.remove(1);
        System.out.println(myArrayList);
        MyArrayList.bubbleSort(myArrayList);
        System.out.println(myArrayList);

    }
}