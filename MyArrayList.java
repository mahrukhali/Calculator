import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 * Resizable-array implementation of the MyList interface.
 * @author Mahrukh Ali
 * @uni ma4203
 * @version 1.0 September 27, 2022
 */
public class MyArrayList<E> implements MyList<E>,MyStack<E> {
    /**
     * Default initial capacity.
     */
    private static final int DEFAULT_CAPACITY = 10;
    /**
     * The size of the ArrayList (the number of elements it contains).
     */
    private int size;
    /**
     * The array buffer into which the elements of the ArrayList are stored.
     * The capacity of the ArrayList is the length of this array buffer.
     */
    Object[] elementData; // non-private to simplify nested class access
    /**
     * Constructs an empty list with the specified initial capacity.
     * @param  initialCapacity  the initial capacity of the list
     * @throws IllegalArgumentException if the specified initial capacity
     *         is negative
     */
    public MyArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal Capacity: " +
                    initialCapacity);
        }
        this.elementData = new Object[initialCapacity];
    }
    /**
     * Constructs an empty list with an initial capacity of ten.
     */
    public MyArrayList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }
    /**
     * Returns the number of elements in this list.
     * @return the number of elements in this list
     */
    public int size() {
        return size;
    }
    /**
     * Returns true if this list contains no elements.
     * @return true if this list contains no elements
     */
    public boolean isEmpty() {
        return size == 0;
    }
    /**
     * Appends the specified element to the end of this list.
     * @param element  element to be appended to this list
     * @return true
     */
    public boolean add(E element) {
        if (size + 1 > elementData.length) {
            Object[] newData = new Object[size * 2 + 1];
            for (int i = 0; i < size; i++) {
                newData[i] = elementData[i];
            }
            elementData = newData;
        }
        elementData[size++] = element;
        return true;
    }
    /**
     * Returns the element at the specified position in this list.
     * @param index  index of the element to return
     * @return       the element at the specified position in this list
     * @throws       IndexOutOfBoundsException - if the index is out of range
     *               (index < 0 || index >= size())
     */
    @SuppressWarnings("unchecked")
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    "Index: " + index + ", list size: " + size);
        }
        return (E)elementData[index];
    }
    /**
     * Replaces the element at the specified position in this list with the
     * specified element.
     * @param index    index of the element to return
     * @param element  element to be stored at the specified position
     * @return  the element at the specified position in this list
     * @throws  IndexOutOfBoundsException - if the index is out of range
     *          (index < 0 || index >= size())
     */
    @SuppressWarnings("unchecked")
    public E set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    "Index: " + index + ", list size: " + size);
        }
        E oldValue = (E)elementData[index];
        elementData[index] = element;
        return oldValue;
    }
    /**
     * Removes all of the elements from this list.
     */
    public void clear() {
        // clear to let GC do its work
        for (int i = 0; i < size; i++) {
            elementData[i] = null;
        }
        size = 0;
    }
    /**
     * Returns an iterator over the elements in this list (in proper
     * sequence).
     *
     * The returned list iterator is fail-fast -- modification of the elements
     * is not permitted during iteration.
     */
    public Iterator<E> iterator() {
        return new ListItr();
    }
    /**
     * Returns a string representation of the list. The string will begin with
     * a '[' and end with a ']'. Inside the square brackets will be a comma-
     * separated list of values, such as [Brian, Susan, Jamie].
     * @return a string representation of the list
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append('[');
        if (size > 0) {
            builder.append(elementData[0].toString());
        }
        for (int i = 1; i < size; i++) {
            builder.append(", " + elementData[i].toString());
        }
        builder.append(']');
        return builder.toString();
    }
    /**
     * Inserts the specified element at the specified position in this list.
     * Shifts the element currently at that position (if any) and any subsequent
     * elements to the right (adds one to their indices).
     * @param index    index at which the specified element is to be inserted
     * @param element  element to be inserted
     * @throws IndexOutOfBoundsException if the index is out of range
     *         (index < 0 || index > size())
     * The exception message must be:
     * "Index: " + index + ", list size: " + size
     */
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(
                    "Index: " + index + ", list size: " + size);
        }
        int i = 0;
        if (size + 1 > elementData.length) {
            Object[] newData = new Object[size * 2 + 1];
            for (i = 0; i < index; i++) {
                newData[i] = elementData[i];
            }
            for (int j = size - 1; j >= index; j--) {
                newData[j + 1] = elementData[j];
            }
            elementData = newData;
        } else {
            for (int j = size - 1; j >= index; j--) {
                elementData[j + 1] = elementData[j];
            }
        }
        elementData[index] = element;
        size++;
    }
    /**
     * Removes the element at the specified position in this list.
     * @param index  the index of the element to be removed
     * @return the element that was removed from the list
     * @throws IndexOutOfBoundsException if the index is out of range
     *         (index < 0 || index >= size())
     * The exception message must be:
     * "Index: " + index + ", list size: " + size
     */
    @SuppressWarnings("unchecked")
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    "Index: " + index + ", list size: " + size);
        }
        E oldValue = (E)elementData[index];
        for (int i = index; i < size; i++) {
            elementData[i] = elementData[i + 1];
        }
        size--;
        return oldValue;
    }
    /**
     * Returns the index of the first occurrence of the specified element in
     * this list, or -1 if this list does not contain the element. More
     * formally, returns the lowest index i such that Objects.equals(o, get(i)),
     * or -1 if there is no such index.
     * @param element element to search for
     * @return the index of the first occurrence of the specified element in
     * this list, or -1 if this list does not contain the element
     */
    public int indexOf(E element) {
        for (int i = 0; i < size; i++) {
            if (elementData[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }
    /**
     * Returns an array of indexes of each occurrence of the specified element
     * in this list, in ascending order. If the specified element is not found,
     * a non-null empty array (not null) is returned.
     * @param element element to search for
     * @return an array of each occurrence of the specified element in this
     * list
     */
    public int[] indexesOf(E element) {
        List<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            if (elementData[i].equals(element)) {
                indexes.add(i);
            }
        }
        return indexes.stream().mapToInt(i -> i).toArray();
    }
    /**
     * Reverses the data in the list.
     * For MyArrayList, the data inside the underlying array is moved. For
     * MyLinkedList, the tail must become the head, and all the pointers are
     * reversed. Both implementations must run in Theta(n).
     */
    public void reverse() {
        for (int i = 0, mid = size / 2, opposite = size - 1; i < mid;
             i++, opposite--) {
            Object temp = elementData[i];
            elementData[i] = elementData[opposite];
            elementData[opposite] = temp;
        }
    }

    /**
     * Add an element to the top of the stack
     * @param item  the item to be pushed onto this stack
     */

    public void push(E item){
        Object[] elementDatas;
        if (size==0){
            size=size+1;
            elementDatas=new Object[size];
            elementDatas[0]=item;
            elementData=elementDatas;
        }
        else{
            size++;
            elementDatas=new Object[size];
            for (int i=0; i<size-1;i++){
                elementDatas[i]=elementData[i];
            }
            elementDatas[size-1]=item;
            elementData=elementDatas;
        }

    }

    /**
     * Deletes the element from the top of the stack
     * @return the top element that was removed from the stack
     * @throws StackException
     */
    @SuppressWarnings("unchecked")
    public E pop() throws StackException{
        if (isEmpty()==true){
            throw new StackException("Attempt to pop from empty stack." );
        }
        E value=(E)elementData[size-1];
        elementData[size-1]=null;
        size--;
        return value;


    }
    /**
     * Looks at the object at the top of this stack without removing it from the
     * stack.
     * @return the object at the top of the stack
     * @throws StackException
     */
    @SuppressWarnings("unchecked")
    public E peek() throws StackException{
        if (isEmpty()==true){
            throw new StackException("Attempt to peek at empty stack." );
        }
        E value =(E)elementData[size-1];
        return value;
    }
    private class ListItr implements Iterator<E> {
        private int current;
        ListItr() {
            current = 0;
        }
        @Override
        public boolean hasNext() {
            return current < size;
        }
        @Override
        @SuppressWarnings("unchecked")
        public E next() {
            return (E)elementData[current++];
        }
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}





//Start

//import java.util.Iterator;
//
///**
// * Resizable-array implementation of the MyList interface.
// * @author Mahrukh Ali
// * @uni: Ma4203
// * @version 1.0 September 27, 2022
// */
//public class MyArrayList<E> implements MyList<E>,MyStack<E> {
//    /**
//     * Default initial capacity.
//     */
//    private static final int DEFAULT_CAPACITY = 10;
//
//    /**
//     * The size of the ArrayList (the number of elements it contains).
//     */
//    private int size;
//
//    /**
//     * The array buffer into which the elements of the ArrayList are stored.
//     * The capacity of the ArrayList is the length of this array buffer.
//     */
//    Object[] elementData; // non-private to simplify nested class access
//
//    /**
//     * Constructs an empty list with the specified initial capacity.
//     * @param  initialCapacity  the initial capacity of the list
//     * @throws IllegalArgumentException if the specified initial capacity
//     *         is negative
//     */
//    public MyArrayList(int initialCapacity) {
//        if (initialCapacity < 0) {
//            throw new IllegalArgumentException("Illegal Capacity: " +
//                    initialCapacity);
//        }
//        this.elementData = new Object[initialCapacity];
//    }
//
//    /**
//     * Constructs an empty list with an initial capacity of ten.
//     */
//    public MyArrayList() {
//        this.elementData = new Object[DEFAULT_CAPACITY];
//    }
//
//    /**
//     * Returns the number of elements in this list.
//     * @return the number of elements in this list
//     */
//    public int size() {
//        return size;
//    }
//
//    /**
//     * Returns true if this list contains no elements.
//     * @return true if this list contains no elements
//     */
//    public boolean isEmpty() {
//        return size == 0;
//    }
//
//    /**
//     * Appends the specified element to the end of this list.
//     * @param element  element to be appended to this list
//     * @return true
//     */
//    public boolean add(E element) {
//        if (size + 1 > elementData.length) {
//            Object[] newData = new Object[size * 2 + 1];
//            for (int i = 0; i < size; i++) {
//                newData[i] = elementData[i];
//            }
//            elementData = newData;
//        }
//        elementData[size++] = element;
//        return true;
//    }
//
//    /**
//     * Returns the element at the specified position in this list.
//     * @param index  index of the element to return
//     * @return       the element at the specified position in this list
//     * @throws       IndexOutOfBoundsException - if the index is out of range
//     *               (index < 0 || index >= size())
//     */
//    public E get(int index) {
//        if (index < 0 || index >= size) {
//            throw new IndexOutOfBoundsException(
//                    "Index: " + index + ", list size: " + size);
//        }
//        return (E)elementData[index];
//    }
//
//    /**
//     * Replaces the element at the specified position in this list with the
//     * specified element.
//     * @param index    index of the element to return
//     * @param element  element to be stored at the specified position
//     * @return  the element at the specified position in this list
//     * @throws  IndexOutOfBoundsException - if the index is out of range
//     *          (index < 0 || index >= size())
//     */
//    public E set(int index, E element) {
//        if (index < 0 || index >= size) {
//            throw new IndexOutOfBoundsException(
//                    "Index: " + index + ", list size: " + size);
//        }
//        E oldValue = (E)elementData[index];
//        elementData[index] = element;
//        return oldValue;
//    }
//
//    /**
//     * Removes all of the elements from this list.
//     */
//    public void clear() {
//        // clear to let GC do its work
//        for (int i = 0; i < size; i++) {
//            elementData[i] = null;
//        }
//        size = 0;
//    }
//
//    /**
//     * Returns a string version of the array
//     * @return
//     */
//    public String toString(){
//        String apple =("[" );
//        String n="";
//        for(int i=0; i<size;i++){
//            if (i==size-1){
//                n= n + elementData[i];
//            }
//            else {
//                n = n + elementData[i] + "," + " ";
//            }
//        }
//        apple= apple + n + ("]");
//        return apple;
//    }
//
//    /**
//     * Adds the element in the list at the specified index
//     * @param index    index at which the specified element is to be inserted
//     * @param element  element to be inserted
//     */
//    public void add(int index, E element){
//        E temp=null;
//        E temp1=null;
//        if(index==0 && size==0){
//            size=size+1;
//        }
//        else if(index==-1 && size==0){
//            size=size;
//        }
//        else if(index>size){
//            size=size;
//        }
//        else if(index==size){
//            size=size+1;
//        }
//        else{
//            size=size+1;
//        }
//        if (index < 0 || index >= size()){
//            throw new IndexOutOfBoundsException(
//                    "Index: " + index + ", list size: " + size);
//        }
//        //size=size+1;
//        Object[] newData = new Object[size];
//        for (int i = 0; i < size; i++) {
//            if (i == index) {
//                temp = (E)elementData[i];
//                newData[i] = element;
//            }
//            else if (i > index) {
//                temp1 = (E)elementData[i];
//                newData[i] = temp;
//                temp = temp1;
//            }
//            else {
//                newData[i] = (E)elementData[i];
//            }
//        }
//        elementData = newData;
//        }
//
//    /**
//     * Removes the element from the list at the specified index
//     * @param index  the index of the element to be removed
//     * @return the element that was removed from the list
//     */
//    public E remove(int index){
//
//        if (index < 0 || index >= size()){
//            throw new IndexOutOfBoundsException(
//                    "Index: " + index + ", list size: " + size);
//        }
//        E value=(E)elementData[index];
//        size=size-1;
//        int count=size-1;
//
//        Object[] newData = new Object[size];
//        //E value=(E)elementData[index];
//        //elementData[index]=null;
//        for (int i=0;i<size;i++){
//            if (i==index){
//                newData[i]=(E)elementData[i+1];
//            }
//            else if(i>index){
//                newData[i]=elementData[i+1];
//            }
//            else {
//                newData[i] = elementData[i];
//            }
//        }
////
//        elementData=newData;
//
//        return value;
//
//    }
//
//    /**
//     * Finds the index of where the element first appears in the list
//     * @param element element to search for
//     * @return the index of where the element first appears
//     */
//    public int indexOf(E element){
//        for (int i=0; i<size; i++){
//            if(element.equals(get(i))){
//
//                return i;
//            }
//        }
//        return -1;
//    }
//
//    /**
//     * Creates a list of all the indexes of where the element appears in the list
//     * @param element element to search for
//     * @return a list of the indexes of where the element is in the list
//     */
//    public int[] indexesOf(E element){
//        int j=0;
//        int[] newArray= new int[size];
//        for(int i=0; i<size;i++){
//            if(elementData[i]==element){
//                newArray[j]=i;
//                j++;
//            }
//        }
//        int[] newArrays= new int[j];
//        for(int i=0; i<j;i++){
//            newArrays[i]=newArray[i];
//        }
//        newArray=newArrays;
//        return newArray;
//    }
//
//    /**
//     * Reverses the data in the list.
//     */
//    public void reverse() {
//        Object[] newData = new Object[size];
//        int j=0;
//        for(int i=size-1;i>=0;i--){
//            newData[j]=elementData[i];
//            j++;
//        }
//        elementData=newData;
//    }
//
//    /**
//     * Add an element to the top of the stack
//     * @param item  the item to be pushed onto this stack
//     */
//
//    public void push(E item){
//        Object[] elementDatas;
//        if (size==0){
//            size=size+1;
//            elementDatas=new Object[size];
//            elementDatas[0]=item;
//            elementData=elementDatas;
//            //size++;
//        }
//        else{
//            size++;
//            elementDatas=new Object[size];
//            for (int i=0; i<size-1;i++){
//                    elementDatas[i]=elementData[i];
//            }
//            elementDatas[size-1]=item;
//            elementData=elementDatas;
//           // size++;
//        }
//
//    }
//
//    /**
//     * Deletes the element from the top of the stack
//     * @return the top element that was removed from the stack
//     * @throws StackException
//     */
//    public E pop() throws StackException{
//        //Object[] elementData1=new Object[size--];
//        if (isEmpty()==true){
//            throw new StackException("Attempt to pop from empty stack." );
//        }
//        E value=(E)elementData[size-1];
//        elementData[size-1]=null;
//        size--;
////        for(int i=0;i<elementData.length-1;i++) {
////            elementData1[i] = elementData[i];
////        }
//        return value;
//
//
//    }
//
//    /**
//     * Looks at the object at the top of this stack without removing it from the
//     * stack.
//     * @return the object at the top of the stack
//     * @throws StackException
//     */
//    public E peek() throws StackException{
//        if (isEmpty()==true){
//            throw new StackException("Attempt to peek at empty stack." );
//        }
//        E value =(E)elementData[size-1];
//        return value;
//    }
//
//    /**
//     * Returns an iterator over the elements in this list (in proper
//     * sequence).
//     *
//     * The returned list iterator is fail-fast -- modification of the elements
//     * is not permitted during iteration.
//     */
//    public Iterator<E> iterator() {
//        return new ListItr();
//    }
//
//    private class ListItr implements Iterator<E> {
//        private int current;
//
//        ListItr() {
//            current = 0;
//        }
//
//        @Override
//        public boolean hasNext() {
//            return current < size;
//        }
//
//        @Override
//        public E next() {
//            return (E)elementData[current++];
//        }
//
//        @Override
//        public void remove() {
//            throw new UnsupportedOperationException();
//        }
//    }
//}
