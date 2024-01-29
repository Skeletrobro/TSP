/**
 * Name: Baron Ping-Yeh Hsieh
 * Pennkey: bpyhsieh
 * Execution: java TourTest
 *
 * Description: JUnit test for Tour function
**/
import org.junit.Test;
import static org.junit.Assert.*;

public class TourTest {
    
    // Do not change this!
    // Use DELTA as the tolerance for your assertEquals statements.
    public static final double DELTA = 1e-2;
    
    /*
    * REQUIRED TESTS
    */
    
    // toString tests

    // Test toString function in empty tour
    @Test
    public void testToStringEmpty() {
        Tour t = new Tour();
        
        String expected = "";
        String actual = t.toString();
        assertEquals(expected, actual);
        
    }
    
    // Test toString function in tour containing one point
    @Test
    public void testToStringOnePoint() {
        Tour t = new Tour();
        Point a = new Point(0, 0);
        t.insertInOrder(a);
        String expected = "(0.0, 0.0)\n(0.0, 0.0)\n";
        String actual = t.toString();
        assertEquals(expected, actual);

    }
    
    // Test toString function in tour containing four points forming a square
    @Test
    public void testToStringSquare() {
        Tour t = new Tour();
        Point a = new Point(0, 0);
        Point b = new Point(1, 0);
        Point c = new Point(1, 1);
        Point d = new Point(0, 1);
       Point[] toInsert = {a, b, c, d};
        
        for (int i = 0; i < toInsert.length; i++) {
            t.insertInOrder(toInsert[i]);
        }
        String expected = 
            "(0.0, 0.0)\n(1.0, 0.0)\n(1.0, 1.0)\n(0.0, 1.0)\n(0.0, 0.0)\n";
        String actual = t.toString();
        assertEquals(expected, actual);
    }
    
    // Distance tests

    // Test distance function in empty tour
    @Test
    public void testDistanceEmpty() {
        Tour t = new Tour();
        double expected = 0;
        double actual = t.distance();
        assertEquals(expected, actual, DELTA);
    }
    
    // Test distance function in tour containing four points forming a square
    @Test
    public void testDistanceSquare() {
        Tour t = new Tour();
        Point a = new Point(0, 0);
        Point b = new Point(1, 0);
        Point c = new Point(1, 1);
        Point d = new Point(0, 1);
        Point[] toInsert = {a, b, c, d};
        
        for (int i = 0; i < toInsert.length; i++) {
            t.insertInOrder(toInsert[i]);
        }
        double expected = 4;
        double actual = t.distance();
        assertEquals(expected, actual, DELTA);
    }
    
    // Size tests

    // Test size function in empty tour
    @Test
    public void testSizeEmpty() {
        Tour t = new Tour();

        int expected = 0;
        int actual = t.size();
        assertEquals(expected, actual);
        
    }
    
    // Test size function in tour containing four points forming a square
    @Test
    public void testSizeSquare() {
        Tour t = new Tour();
        Point a = new Point(0, 0);
        Point b = new Point(1, 0);
        Point c = new Point(1, 1);
        Point d = new Point(0, 1);
        Point[] toInsert = { a, b, c, d };
        
        for (int i = 0; i < toInsert.length; i++) {
            t.insertInOrder(toInsert[i]);
        }
        int expected = 4;
        int actual = t.size();
        assertEquals(expected, actual);
    }
    
    // insertInOrder tests

    // Test insertInOrder and size function of point contents in tsp4.txt file 
    @Test
    public void testInsertInOrderTSP4CorrectSize() {
        Tour t = new Tour();
        
        Point a = new Point(200, 400);
        Point b = new Point(300, 100);
        Point c = new Point(100, 100);
        Point d = new Point(300, 200);
        Point[] toInsert = { a, b, c, d };
        
        for (int i = 0; i < toInsert.length; i++) {
            t.insertInOrder(toInsert[i]);
        }
        int expected = 4;
        int actual = t.size();
        assertEquals(expected, actual);
    }
    
    // Test insertInOrder and distance function of point contents in tsp0.txt file 
    // to tsp4.txt file
    @Test
    public void testInsertInOrderTSP0Through4CorrectDistance() {
        Tour t = new Tour();
        
        Point a = new Point(200, 400);
        Point b = new Point(300, 100);
        Point c = new Point(100, 100);
        Point d = new Point(300, 200);
        
        /*
        * Study this test. It is completed for you.
        * We use two parallel arrays and a for loop to insert points a through e 
        * using Tour.insertInOrder(). After each insertion, we check that the 
        * distance of the tour was computed correctly. These are the points
        * points contained in the file "tsp4.txt".
        */
        Point[] toInsert = { a, b, c, d };
        double[] distanceAfterInsert = { 0, 632.46, 832.46, 963.44 };
        
        for (int i = 0; i < toInsert.length; i++) {
            t.insertInOrder(toInsert[i]);
            double expected = distanceAfterInsert[i];
            double actual = t.distance();
            // System.out.println(expected + " " + actual);
            assertEquals("mismatch after calling insertInOrder on point at index " + 
            i, expected, actual, DELTA);
        }
    }
    
    // insertNearest tests

    // Test insertNearest and size function of point contents in tsp4.txt file 
    @Test
    public void testInsertNearestTSP4CorrectSize() {
        Tour t = new Tour();
        
        Point a = new Point(200, 400);
        Point b = new Point(300, 100);
        Point c = new Point(100, 100);
        Point d = new Point(300, 200);
        
        Point[] toInsert = { a, b, c, d };
        
        for (int i = 0; i < toInsert.length; i++) {
            t.insertNearest(toInsert[i]);
        }
        int expected = 4;
        int actual = t.size();
        assertEquals(expected, actual);
    }
    
    // Test insertNearest and distance function of point contents in tsp0.txt file 
    // to tsp4.txt file
    @Test
    public void testInsertNearestTSP0Through4CorrectDistance() {
        Tour t = new Tour();
        
        Point a = new Point(200, 400);
        Point b = new Point(300, 100);
        Point c = new Point(100, 100);
        Point d = new Point(300, 200);
        Point[] toInsert = { a, b, c, d };
        double[] distanceAfterInsert = { 0, 632.46, 832.46, 956.06 };
        
        for (int i = 0; i < toInsert.length; i++) {
            t.insertNearest(toInsert[i]);
            double expected = distanceAfterInsert[i];
            double actual = t.distance();
            // System.out.println(expected + " " + actual);
            assertEquals("mismatch after calling insertNearest on point at index " + 
            i, expected, actual, DELTA);
        }
        
    }
    
    // insertSmallest tests

     // Test insertSmallest and size function of point contents in tsp4.txt file 
    @Test
    public void testInsertSmallestTSP4CorrectSize() {
        Tour t = new Tour();
        
        Point a = new Point(200, 400);
        Point b = new Point(300, 100);
        Point c = new Point(100, 100);
        Point d = new Point(300, 200);
        Point[] toInsert = { a, b, c, d };
        
        for (int i = 0; i < toInsert.length; i++) {
            t.insertSmallest(toInsert[i]);
        }
        int expected = 4;
        int actual = t.size();
        assertEquals(expected, actual);
      
    }
    
    // Test insertSmallest and distance function of point contents in tsp0.txt file 
    // to tsp4.txt file
    @Test
    public void testInsertSmallestTSP0Through4CorrectDistance() {
        Tour t = new Tour();
        
        Point a = new Point(200, 400);
        Point b = new Point(300, 100);
        Point c = new Point(100, 100);
        Point d = new Point(300, 200);
        
        Point[] toInsert = { a, b, c, d };
        double[] distanceAfterInsert = { 0, 632.46, 832.46, 839.83 };
        
        for (int i = 0; i < toInsert.length; i++) {
            t.insertSmallest(toInsert[i]);
            double expected = distanceAfterInsert[i];
            double actual = t.distance();
            // System.out.println(expected + " " + actual);
            assertEquals("mismatch after calling insertSmallest on point at index" + 
            i, expected, actual, DELTA);
        }
        
    }

    
    /*
    * You should feel free to write more tests than the ones required.
    * Please keep them below this line.
    */
    
}