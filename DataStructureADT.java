//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: (ScannerPractice)
// Description: (Use Java Scanner and PrintWriter to read from and write to files in addition to
//////////////////// using standard I/O for the user to direct interaction)
// Course: (001 FALL 2019)
//
// Author: (Rosalie CAI)
// Email: (rcai25@wisc.edu)
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
/**
 * A data structure that can store at least 500 key,value pairs.
 *
 * May not use any of Java's built-in Java collection types: such as: List, ArrayList, LinkedList,
 * etc...
 * 
 * May not add any public members (fields, methods, inner classes)
 * 
 * @author deppeler
 *
 * @param <K> The key must not be null and must be Comparable.
 * @param <V> The data value associated with a given key.
 */
public interface DataStructureADT<K extends Comparable<K>, V> {

  /**
   * Add the key,value pair to the data structure and increases size. If key is null, throws
   * IllegalArgumentException("null key"); If key is already in data structure, throws
   * RuntimeException("duplicate key"); can accept and insert null values
   * 
   * @param key   key
   * @param value value
   */
  void insert(K key, V value);



  /**
   * key is found, Removes the key from the data structure and decreases size. If key is null,
   * throws IllegalArgumentException("null key") without decreasing size. If key is not found,
   * returns false.
   * 
   * @param key key
   */
  boolean remove(K key);

  /**
   * Returns the value associated with the specified key get - does not remove key or decrease size
   * If key is null, throws IllegalArgumentException("null key")
   * 
   * @param key key
   */
  V get(K key);

  /**
   * Returns true if the key is in the data structure Returns false if key is null or not present
   * 
   * @param key key
   */
  boolean contains(K key);

  /**
   * Returns the number of elements in the data structure
   */
  int size();

}
