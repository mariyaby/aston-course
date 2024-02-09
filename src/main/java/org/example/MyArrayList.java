package org.example;

import java.util.Arrays;

public class MyArrayList<T extends Comparable<T>> {

    private T[] elementData;
    private int size = 0;
    private final static int CAPACITY = 10;

    public MyArrayList() {
        this.elementData = (T[]) new Comparable[CAPACITY];
    }

    public MyArrayList(MyArrayList<T> collection) {
        this.elementData = (T[]) new Comparable[collection.size * 3 / 2 + 1];
        for (int i = 0; i < collection.size; i++) {
            this.elementData[i] = collection.get(i);
        }
        this.size = collection.size;
    }

    public int getSize() {
        return size;
    }

    private void grow() {
        elementData = Arrays.copyOf(elementData, elementData.length * 3 / 2 + 1);
    }

    public void add(T element) {
        if (size == elementData.length) {
            grow();
        }
        elementData[size++] = element;
    }

    public void add(int index, T element) {
        checkIndex(index);
        if (size == elementData.length) {
            grow();
        }
        for (int i = size; i > index; i--) {
            elementData[i] = elementData[i - 1];
        }
        elementData[index] = element;
        size++;
    }

    public boolean addAll(MyArrayList<T> collection) {
        int newSize = collection.size;
        if (newSize == 0) {
            return false;
        }
        if (elementData.length - size < newSize) {
            elementData = Arrays.copyOf(elementData, (size + newSize) * 3 / 2 + 1);
        }
        for (int i = 0; i < newSize; i++) {
            elementData[size++] = collection.elementData[i];
        }
        return true;
    }

    public T get(int index) {
        checkIndex(index);
        return elementData[index];
    }

    public T remove(int index) {
        checkIndex(index);
        final T oldElement = elementData[index];
        if (index < size) {
            for (int i = index; i < size; i++) {
                if (elementData.length - 1 > i) {
                    elementData[i] = elementData[i + 1];
                } else {
                    elementData[i] = null;
                }
            }
        }
        size--;
        return oldElement;
    }

    public String toString() {
        if (size == 0) {
            return "[]";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append('[');
            for (int i = 0; i < size; i++) {
                sb.append(elementData[i]);
                if (i == size - 1) {
                    sb.append(']');
                } else {
                    sb.append(',').append(' ');
                }
            }
            return sb.toString();
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public void bubbleSort() {
        for (int i = size - 1; i >= 0; i--) {
            int count = 0;
            for (int j = 0; j < i; j++) {
                if (elementData[j].compareTo(elementData[j + 1]) > 0) {
                    T temp = elementData[j];
                    elementData[j] = elementData[j + 1];
                    elementData[j + 1] = temp;
                    count++;
                }
            }
            if (count == 0) {
                break;
            }
        }
    }

    static public void bubbleSort(MyArrayList collection) {
        collection.bubbleSort();
    }

    public static void main(String[] args) {
        MyArrayList<Integer> firstList = new MyArrayList<>();
        for (int i = 0; i < 11; i++) {
            firstList.add((int) (Math.random() * 50));
        }
        System.out.println("Start collection:");
        System.out.println(firstList);
        MyArrayList.bubbleSort(firstList);
        System.out.println("Sorted collection:");
        System.out.println(firstList);

        firstList.add(2, 5555);
        System.out.println("Added element 5555 at sixth position");
        System.out.println(firstList);

        MyArrayList<Integer> secondList = new MyArrayList<>();
        for (int i = 0; i < 5; i++) {
            secondList.add((int) (Math.random() * 1000));
        }
        System.out.println("Second collection:");
        System.out.println(secondList);

        firstList.addAll(secondList);
        System.out.println("Added second collection to first collection:");
        System.out.println(firstList);

        System.out.println("Sixth element: " + firstList.get(5));
        firstList.remove(5);
        System.out.println("Sixth element after delete: " + firstList.get(5));

        MyArrayList newTypeList = new MyArrayList(firstList);

        System.out.println("Collection from constructor with collection of another type");
        System.out.println(newTypeList);

        MyArrayList.bubbleSort(newTypeList);
        System.out.println("Sorted collection with static method:");
        System.out.println(newTypeList);
    }
}