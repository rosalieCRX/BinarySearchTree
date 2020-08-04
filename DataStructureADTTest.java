//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: (DS test)
// Description: (ADT testing)
// Course: (001 FALL 2019)
//
// Author: (Rosalie CAI)
// Email: (rcai25@wisc.edu)
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class for ADT
 * 
 * @author rosaliecarrow
 *
 * @param <T>
 */
abstract class DataStructureADTTest<T extends DataStructureADT<String, String>> {

  private T dataStructureInstance; // the ADT object of the test class

  protected abstract T createInstance(); // the abstract method for creating specific object

  /**
   * setting up before each JUnit test on class
   * 
   * @throws Exception
   */
  @BeforeAll
  static void setUpBeforeClass() throws Exception {
  }

  /**
   * tearing down the object after each JUnit test on class
   * 
   * @throws Exception
   */
  @AfterAll
  static void tearDownAfterClass() throws Exception {
  }

  /**
   * setting up before each JUnit test
   * 
   * @throws Exception
   */
  @BeforeEach
  void setUp() throws Exception {
    dataStructureInstance = createInstance();
  }

  /*
   * tearing down the object after each JUnit test
   */
  @AfterEach
  void tearDown() throws Exception {
    dataStructureInstance = null;
  }


  // ---------------------------testing insertion------------------------
  // --------------------------------------------------------------------
  /**
   * test if size is correct after one insertion
   */
  @Test
  void test01_after_insert_one_size_is_one() {
    dataStructureInstance.insert("KEY01", "VALUE01");// insert one pair
    if (dataStructureInstance.size() != 1) {
      fail("data structure should have 1 pair of key and value, but size="
          + dataStructureInstance.size());
    }
  }

  /**
   * test if exception is thrown after insertion of null key
   */
  @Test
  void test05_insert_null_key_exception_is_thrown() {
    try {
      dataStructureInstance.insert(null, "VALUE01");
      fail("data structure should throw IllegalArgumentException(\"null key\")");// shouldn't reach
                                                                                 // here
    } catch (IllegalArgumentException e) {
      // expected,
      // check if error message is correct
      if (!e.getMessage().equals("null key")) {
        fail("data structure should throw IllegalArgumentException(\"null key\"), but throws"
            + e.getMessage());
      }
    } catch (RuntimeException e) {
      fail("data structure should throw IllegalArgumentException(\"null key\"), but throws"
          + e.getMessage());// shouldn't reach here

    }
  }

  /**
   * test if the right exception is thrown for duplicate insertion
   */
  @Test
  void test03_duplicate_exception_is_thrown() {
    try {
      dataStructureInstance.insert("KEY01", "VALUE01");
      dataStructureInstance.insert("KEY01", "VALUE02");
      fail("data structure should throw RuntimeException here");// shouldn't reach here
    } catch (RuntimeException e) {

      // check if error message is correct
      if (!e.getMessage().equals("duplicate key")) {
        fail("data structure should throw RuntimeException(\"duplicate key\"), but throws"
            + e.getMessage());
      }
    }
  }

  /**
   * test if value null is correctly inserted
   */
  @Test
  void test06_insert_null_value_is_stored() {
    // test if value null is inserted successfully
    try {
      dataStructureInstance.insert("KEY01", null);
    } catch (RuntimeException e) {
      fail("data structure should not throw anything, but throws" + e.getMessage());
    }

    // test if value null is stored
    if (dataStructureInstance.get("KEY01") != (null)) {
      fail("data structure should contain value null");
    }
    // test if value null is stored
    if (dataStructureInstance.size() != 1) {
      fail("data structure should contain value null");
    }
  }

  // ---------------------------testing removal--------------------------
  // --------------------------------------------------------------------
  /**
   * test if size is correct after one deletion
   */
  @Test
  void test02_after_insert_one_remove_one_size_is_0() {
    dataStructureInstance.insert("KEY01", "VALUE01");
    // test if remove is successful
    if (!dataStructureInstance.remove("KEY01")) {
      fail("data structure should be successfully remove the data here");
    }

    // test size
    if (dataStructureInstance.size() != 0) {
      fail("data structure should be empty, but size=" + dataStructureInstance.size());
    }
  }

  /**
   * test if the right exception is thrown for null key removal
   */
  @Test
  void test07_remove_null_key_exception_is_thrown() {
    try {
      dataStructureInstance.insert("KEY01", "VALUE01");
      dataStructureInstance.insert("KEY02", "VALUE02");
      dataStructureInstance.remove(null);
      fail("data structure should throw IllegalArgumentException(\"null key\") here");// shouldn't
                                                                                      // reach here
    } catch (IllegalArgumentException e) {
      // expected,
      // check if error message is correct
      if (!e.getMessage().equals("null key")) {
        fail("data structure should throw IllegalArgumentException(\"null key\"), but throws"
            + e.getMessage());
      }
    } catch (RuntimeException e) {
      // unexpected
      fail("data structure should throw IllegalArgumentException(\"null key\"), but throws"
          + e.getMessage());// shouldn't reach here

    }

    // test if remove failure does not decrease size
    if (dataStructureInstance.size() != 2) {
      fail("data structure should have size 2, but size=" + dataStructureInstance.size());
    }
  }

  /**
   * test if false is returned for non-existent key
   */
  @Test
  void test04_remove_returns_false_when_key_not_present() {
    dataStructureInstance.insert("KEY01", "VALUE01");
    dataStructureInstance.insert("KEY02", "VALUE02");
    if (dataStructureInstance.remove("KEY03")) {
      fail("data structure should fail to remove key, but remove is true");
    }
  }

  /**
   * test if an item removed is still present
   */
  @Test
  void test14_remove_after_remove() {
    dataStructureInstance.insert("KEY01", "VALUE01");
    dataStructureInstance.insert("KEY02", "VALUE02");
    dataStructureInstance.insert("KEY03", "VALUE03");

    if (!dataStructureInstance.remove("KEY01")) {
      fail("data structure should be successfully remove the data here");
    }

    // test if remove is successful
    if (dataStructureInstance.contains("KEY01")) {
      fail("data structure should be successfully remove the data here");
    }

    // test size
    if (dataStructureInstance.size() != 2) {
      fail("data structure should be empty, but size=" + dataStructureInstance.size());
    }
  }

  // ---------------------------testing get------------------------------
  // --------------------------------------------------------------------

  /**
   * test if false is returned for non-existent key & true if
   */
  @Test
  void test08_get_right_value() {
    dataStructureInstance.insert("KEY01", "VALUE01");
    dataStructureInstance.insert("KEY02", "VALUE02");
    // test if the right value is returned
    if (!dataStructureInstance.get("KEY01").equals("VALUE01")) {
      fail("data structure should fail to get the right value of a key, value got: "
          + dataStructureInstance.get("KEY01"));
    }

    // test if remove failure does not decrease size
    if (dataStructureInstance.size() != 2) {
      fail("data structure should have size 2, but size=" + dataStructureInstance.size());
    }
  }

  /**
   * test if the right exception is thrown for null key removal
   */
  @Test
  void test09_get_null_key_exception_is_thrown() {
    try {
      dataStructureInstance.insert("KEY01", "VALUE01");
      dataStructureInstance.insert("KEY02", "VALUE02");
      dataStructureInstance.get(null);// test null
      fail("data structure should throw IllegalArgumentException(\"null key\") here");
    } catch (IllegalArgumentException e) {
      // expected,
      // check if error message is correct
      if (!e.getMessage().equals("null key")) {
        fail("data structure should throw IllegalArgumentException(\"null key\"), but throws"
            + e.getMessage());
      }
    } catch (RuntimeException e) {
      // unexpected
      fail("data structure should throw IllegalArgumentException(\"null key\"), but throws"
          + e.getMessage());
    }

  }



  // ---------------------------testing contains-------------------------
  // --------------------------------------------------------------------

  /**
   * test if contains return true for right key
   */
  @Test
  void test10_contains_right_value() {
    dataStructureInstance.insert("KEY01", "VALUE01");
    dataStructureInstance.insert("KEY02", "VALUE02");
    // test if the right value is returned
    if (!dataStructureInstance.contains("KEY01")) {
      fail("data structure should contain the right key");
    }
  }

  /**
   * test if contains return true for right key
   */
  @Test
  void test11_does_not_contains_wrong_value() {
    dataStructureInstance.insert("KEY01", "VALUE01");
    dataStructureInstance.insert("KEY02", "VALUE02");
    // test null
    if (dataStructureInstance.contains(null)) {
      fail("data structure should not contain null");
    }
    // test if contain a non-existent key
    if (dataStructureInstance.contains("KEY03")) {
      fail("data structure should not contain the wrong value");
    }
  }


  // ---------------------------testing size-----------------------------
  // --------------------------------------------------------------------
  /**
   * test if empty data structure has size 0
   */
  @Test
  void test00_empty_ds_size() {
    if (dataStructureInstance.size() != 0)
      fail("data structure should be empty, with size=0, but size=" + dataStructureInstance.size());
  }


  /**
   * test if non-empty data structure has the right size
   */
  @Test
  void test12_non_empty_ds_size() {
    dataStructureInstance.insert("KEY04", "VALUE04");
    dataStructureInstance.insert("KEY05", "VALUE05");
    dataStructureInstance.insert("KEY03", "VALUE03");
    dataStructureInstance.insert("KEY01", "VALUE01");
    dataStructureInstance.insert("KEY02", "VALUE02");
    dataStructureInstance.insert("KEY06", "VALUE06");
    if (dataStructureInstance.size() != 6)
      fail("data structure should have size=6, but size=" + dataStructureInstance.size());
  }



  // ---------------------------testing general functions----------------
  // --------------------------------------------------------------------
  /**
   * test if adding many items is successful and correctly
   */
  @Test
  void test13_insert_contains_delete_bunch() {
    // insert first half
    for (int i = 49; i < 77; i++) {
      dataStructureInstance.insert("KEY" + i, "VALUE" + i);
    }
    // insert second half
    for (int i = 10; i < 49; i++) {
      dataStructureInstance.insert("KEY" + i, "VALUE" + i);
    }

    // check size
    if (dataStructureInstance.size() != 67)
      fail("data structure should have size=67, but size=" + dataStructureInstance.size());

    // check contains
    for (int i = 10; i < 77; i++) {
      if (!dataStructureInstance.contains("KEY" + i)) {
        fail("data structure should have KEY" + i + " but doesn't");
      }
    }

    // check delete
    for (int i = 10; i < 77; i++) {
      if (!dataStructureInstance.remove("KEY" + i)) {
        fail("data structure should remove KEY" + i + " but doesn't");
      }
    }

    // check size again
    if (dataStructureInstance.size() != 0)
      fail("data structure should have size=0, but size=" + dataStructureInstance.size());

  }

  // Tip: consider different numbers of inserts and removes and how different combinations of insert
  // and removes


}
