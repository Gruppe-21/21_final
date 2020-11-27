package com.gruppe21.utils.arrayutils;

import java.lang.reflect.Array;
import java.util.Arrays;

public class OurArrayList<T> {

    private Object[] array;
    private int elementsInArray;

    public OurArrayList() {
        // initialize array with a size of 10
        this(10);
    }

    public <T> T[] toArray(T[] a)
{
        if (a.length < size())
                 a = (T[]) Array.newInstance(a.getClass().getComponentType(), size());
         else if (a.length > size())
                 a[size()] = null;
            System.arraycopy(array, 0, a, 0, size());
        return a;
      }
    public OurArrayList(int n) {
        if (n <= 0) {
            try {
                throw new Exception("Array can't have a size less than 0");
            } catch (Exception e) {
                e.printStackTrace();
                }
        }
        array = new Object[n];
        elementsInArray = 0;
    }

    public T get(int index) {
        T element = null;
        element = (T) array[index];
        return element;
    }

    public void add(T x) {
        if (checkIfArrayFull()) {
            copyArray(2);
        }

        array[elementsInArray] = x;
        elementsInArray++;
    }


    public void remove(T element) {
        for (int i = 0; i < elementsInArray; i++) {
            if (element.equals(array[i])) {
                elementsInArray--;
                array[i] = null;
                copyArray(0);
                return;
            }
        }
    }

    public void removeIndex(int index){
            if(array[index] != null){
                elementsInArray--;
                array[index] = null;
                copyArray(0);
            }
    }

    public void set(int index, T object){
        array[index] = object;
    }

    public int size() {
        return elementsInArray;
    }

    public boolean isEmpty() {
        return elementsInArray == 0;
    }

    private void copyArray(int size) {
        size = array.length + size;
        Object[] temp = new Object[size];

        int tempElement = 0;

        for (int i = 0; i < array.length; i++, tempElement++) {
            if (array[i] == null) {
                tempElement--;
                continue;
            }

            temp[tempElement] = array[i];
        }

        array = null;
        array = new Object[temp.length];
        array = temp;
    }

    private boolean checkIfArrayFull() {
        return array.length == elementsInArray;
    }

// Finds the index of an object in the array. if gets a null, then return first null index, else return index of first instance of the object in array.
    public int indexOf(T object) {
        if (object == null) {
            for (int i = 0; i < size(); i++)
                if (array[i]==null)
                    return i;
        } else {
            for (int i = 0; i < size(); i++)
                if (object.equals(array[i]))
                    return i;
        }
        return -1;
    }
}
