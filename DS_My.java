//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: (DS test)
// Description: (ADT testing)
// Course: (001 FALL 2019)
//
// Author: (Rosalie CAI)
// Email: (rcai25@wisc.edu)
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import java.util.NoSuchElementException;

/**
 * Implementation of a binary search tree
 * 
 * @author rosaliecarrow
 * @param <K>
 * @param <V>
 *
 */
public class DS_My<K extends Comparable<K>, V> implements DataStructureADT {
  private int size;
  private node<K, V> root;

  // TODO may wish to define an inner class
  // for storing key and value as a pair
  // such a class and its members should be "private"

  // Private Fields of the class
  // TODO create field(s) here to store data pairs
  /**
   * constructor of DS_My
   */
  public DS_My() {
    // TODO Auto-generated method stub

  }

  /**
   * Add the key,value pair to the data structure and increases size. If key is null, throws
   * IllegalArgumentException("null key"); If key is already in data structure, throws
   * RuntimeException("duplicate key"); can accept and insert null values
   * 
   * @param k key
   * @param v value
   */
  @Override
  public void insert(Comparable k, Object v) {
    // If key is null, throws IllegalArgumentException("null key")
    if (k == null) {
      throw new IllegalArgumentException("null key");
    }
    root = insert(root, new node(k, v));

  }

  /**
   * insert private helper method
   * 
   * @param node    current node
   * @param newNode newNode to be inserted
   * @return the updated root
   */
  private node<K, V> insert(node<K, V> node, node<K, V> newNode) {
    // base case-find the leaf to insert
    if (node == null) {
      size++;
      return newNode;
    }
    if (newNode.getKey().compareTo(node.getKey()) < 0) {
      node.setLeft(insert(node.getLeft(), newNode));
    } // if key smaller than current node, go left
    else if (newNode.getKey().compareTo(node.getKey()) > 0) {
      node.setRight(insert(node.getRight(), newNode));
    } // if key bigger than current node, go right
    else {
      throw new RuntimeException("duplicate key");
    } // if key is already present, throw exception

    return node;

  }

  /**
   * key is found, Removes the key from the data structure and decreases size If key is null, throws
   * IllegalArgumentException("null key") without decreasing size If key is not found, returns
   * false.
   * 
   * @param k key
   */
  @Override
  public boolean remove(Comparable k) {
    // If key is null, throws IllegalArgumentException("null key")
    if (k == null) {
      throw new IllegalArgumentException("null key");
    }
    int size = this.size;
    root = remove(root, k);

    return size - this.size == 1;
  }


  private node<K, V> remove(node<K, V> node, Comparable k) {
    // base case-key not found
    if (node == null) {
      return null;
    }
    if (k.compareTo(node.getKey()) < 0) {
      node.setLeft(remove(node.getLeft(), k));
    } // if key smaller than current node, go left
    else if (k.compareTo(node.getKey()) > 0) {
      node.setRight(remove(node.getRight(), k));
    } // if key bigger than current node, go right
    else {
      size--;// decrease size

      if (node.getLeft() == null) {
        return node.getRight();
      } // if left child is null, return right child
      else if (node.getRight() == null) {
        return node.getLeft();
      } // if right child is null, return left child
      else {
        node<K, V> runner = node.getLeft();
        while (runner.getRight() != null) {
          runner = runner.getRight();
        } // get the in-order predecessor

        // replace value and key
        node.setKey(runner.getKey());
        node.setValue(runner.getValue());

        // delete node
        node.setLeft(remove(node.getLeft(), k));
      } // it has two child, get the in-order predecessor to replace the current node

    } // if value found

    return node;
  }// if key is found, delete and return true



  /**
   * Returns true if the key is in the data structure Returns false if key is null or not present
   * 
   * @param k key
   */
  @Override
  public boolean contains(Comparable k) {
    if (k == null) {
      return false;
    }
    return contains(root, k);
  }

  /**
   * check if the BST contains the key
   * 
   * @param node the root of the subtree to check
   * @param k    key
   * @return whether the key is contained in the tree
   */
  private boolean contains(node<K, V> node, Comparable k) {
    // base case-find the leaf to insert
    if (node == null) {
      return false;
    }
    if (k.compareTo(node.getKey()) < 0) {
      return contains(node.getLeft(), k);
    } // if key smaller than current node, go left
    else if (k.compareTo(node.getKey()) > 0) {
      return contains(node.getRight(), k);
    } // if key bigger than current node, go right

    return true;// if found
  }

  /**
   * Returns the value associated with the specified key get - does not remove key or decrease size
   * If key is null, throws IllegalArgumentException("null key")
   * 
   * @param k key
   */
  @Override
  public Object get(Comparable k) {
    if (k == null) {
      throw new IllegalArgumentException("null key");
    }

    return get(root, k);
  }

  /**
   * get the associated value of a key
   * 
   * @param node the root of a subtree to check
   * @param k    the key
   * @return the associated value
   */
  private Object get(node<K, V> node, Comparable k) {
    // base case-find the leaf to insert
    if (node == null) {
      throw new NoSuchElementException("Not found");
    }
    if (k.compareTo(node.getKey()) < 0) {
      return get(node.getLeft(), k);
    } // if key smaller than current node, go left
    else if (k.compareTo(node.getKey()) > 0) {
      return get(node.getRight(), k);
    } // if key bigger than current node, go right

    return node.getValue();// if found
  }

  /**
   * Returns the number of elements in the data structure
   */
  @Override
  public int size() {
    return size(root);
  }

  /**
   * Returns the number of elements in the subtree
   * 
   * @param node the root of the subtree to check
   * @return the size of the subtree
   */
  private int size(node<K, V> node) {
    if (node == null) {
      return 0;
    }
    return size(node.getLeft()) + size(node.getRight()) + 1;
  }


  /**
   * node for the BST
   * 
   * @author rosaliecarrow
   *
   * @param <K> key
   * @param <V> value
   */
  public class node<K extends Comparable<K>, V> {
    // private fields
    private K key;
    private V value;
    private node<K, V> left;
    private node<K, V> right;


    // constructors--------------------------------
    /**
     * default constructor of the node
     */
    public node() {
    }

    /**
     * overloading constructor of the node
     */
    public node(K key, V value) {
      this.key = key;
      this.value = value;
    }

    // getters and setters--------------------------
    /**
     * get key
     * 
     * @return the key
     */
    public K getKey() {
      return key;
    }

    /**
     * set key
     * 
     * @param key the key to set
     */
    public void setKey(K key) {
      this.key = key;
    }

    // ----------------------------
    /**
     * get value
     * 
     * @return the value
     */
    public V getValue() {
      return value;
    }

    /**
     * set value
     * 
     * @param value the value to set
     */
    public void setValue(V value) {
      this.value = value;
    }

    // ----------------------------
    /**
     * @return the left child
     */
    public node<K, V> getLeft() {
      return left;
    }

    /**
     * @param left the left child to set
     */
    public void setLeft(node<K, V> left) {
      this.left = left;
    }

    // ----------------------------
    /**
     * @return the right child
     */
    public node<K, V> getRight() {
      return right;
    }

    /**
     * @param right the right child to set
     */
    public void setRight(node<K, V> right) {
      this.right = right;
    }



  }

}
