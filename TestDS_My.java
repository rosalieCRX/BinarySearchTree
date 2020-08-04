//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: (DS test)
// Description: (ADT testing)
// Course: (001 FALL 2019)
//
// Author: (Rosalie CAI)
// Email: (rcai25@wisc.edu)
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;
// TO TEST A DATA STRUCTURE CLASS:
//
// for each data structure class file you wish to test:
// 1. create a test class (like this one)
// 2. edit the actual type being created (line 16)
// 3. run this test class
// 4. OR, configure Eclipse project to run all tests
// Eclipse: Run->Run Configurations->"Run All Tests..."

@SuppressWarnings("rawtypes")
public class TestDS_My extends DataStructureADTTest {
  /**
   * create an instance of the DS to be tested
   */
  @Override
  protected DataStructureADT createInstance() {
    return new DS_My();
  }
}
