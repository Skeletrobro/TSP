/**
 * Name: Baron Ping-Yeh Hsieh
 * Pennkey: bpyhsieh
 * Execution: java Tour
 *
 * Description: A Tour class that models a tour using a chained sequence of nodes
 * containing Point objects. The class is initialized with instance variables nodes
 * head and lastNode and an empty Tour constructor that takes in no arguments. 
 * The class also includes different methods, with toString converting the contents
 * of tour (each point) into strings, draw drawing out lines connecting every 
 * point in Tour, size counting the amount of points in Tour, distance summing 
 * the total distance of the tour (sum of every point to point distance), 
 * insertInOrder inserting a node containing the point inputted in the function 
 * argument between the node before lastNode and lastNode in Tour, insertNearest
 * inserting a new node containing the point inputted in the 
 * function argument after the node in the Tour that contains the point closest 
 * in distance to the input point, and insertSmallest inserting a new node 
 * containing the point inputted in the function argument after the node
 * in Tour that contains the point that has the smallest increment distance
 * to the input point.
**/
public class Tour implements TourInterface {

    // Initializes the two instance variables needed for Tour, the Node objects 
    // head and lastNode.
    private Node head;
    private Node lastNode;

    // Constuctor of Tour, which takes in no arguments and creates an empty Tour 
    // object when called, with both head and lastNode initially being null.
    public Tour() {

    }

    /* A function that converts the contents of Tour into a String representation 
    * by calling Point's toString function on each point contained in the chained 
    * nodes contructing the Tour, and concatenating all string representations of 
    * the points into one String variable pointString that would be returned after
    * all nodes in Tour has been iterated through. If Tour is empty, the function
    * would return an empty string.
    */
    public String toString() {
        if (head == null) {
            return ""; 
        }
        String pointString = "";
        Node curr = head;
        while (curr.next != null) {
            pointString += curr.point.toString();
	        curr = curr.next;
        }
        pointString += lastNode.point.toString();
        return pointString;
    }

    /* A function that draws out the entire Tour using point's drawto function on
    * each point contained in the nodes of Tour. Lines would be drawn between the
    * point stored by the current node and the point stored by the next node in 
    * sequence to current (curr.next) for each iteration through the Tour using
    * a while loop and stops when the next node is null. The function would
    * also take in an argument of Point p, and draw the lines adjacent to point p
    * in a different color compared to the rest of the lines. If the input argument 
    * is null, all lines would be drawn in the same color.
    */
    public void draw(Point p) {
        Node curr = head;
        while (curr.next != null) {
            if (curr.point == p || curr.next.point == p) {
                PennDraw.setPenColor(PennDraw.RED);
            } 
            else {
                PennDraw.setPenColor(PennDraw.BLACK);
            }
            curr.point.drawTo(curr.next.point);
            curr = curr.next;
        }
    }

    /* A function that counts the amount of points in Tour by iterating through the
    * nodes in Tour through a while loop that stops when the current node is 
    * referencing to lastNode (as lastNode references the same point, as the first 
    * node, so stopping before lastNode prevents double counting). If Tour is empty,
    * then the function returns that there are 0 points in Tour. 
    */
    public int size() {
        int pointCount = 0;
        if (head == null) {
            return 0;
        }
        Node curr = head;
        while (curr != lastNode) {
            pointCount++;
	        curr = curr.next;
        }
        return pointCount;
    }

    /* A function that records the total distance/length by iterating through Tour 
    * summing up each point to point distance using the point's distanceTo function
    * between the points stored by the currently iterated node and the next node 
    * in sequence to the current node. If Tour is empty, then the function would
    * return a total distance of 0.
    */
    public double distance() {
        double totalDistance = 0;
        if (head == null) {
            return 0;
        }
        Node curr = head;
        while (curr.next != null) {
            totalDistance += curr.point.distanceTo(curr.next.point);
            curr = curr.next;
        }
        return totalDistance;
    }
    
    /* A function that inserts a node containing the point inputted in the function 
    * argument between the node before lastNode and lastNode in Tour. If Tour was
    * initially empty (head and lastNode both being null), then the function would 
    * first redeclare lastNode as a node referencing the input point, and
    * redeclare head as a node that also references the input point whose next node
    * in sequence would be lastNode, creating a chain of nodes with head and 
    * lastNode while also satisfying the criteria of head and lastNode pointing 
    * towards the same point. After that, the function would insert a new node
    * containing the input point before lastNode by iterating through Tour until
    * the node before lastNode is reached (if the next node of the currently 
    * iterated node is lastNode, the while loop stops), then inserting the 
    *  new node between that current node and lastNode. 
    */
    public void insertInOrder(Point p) {
        Node newNode = new Node(p); 
        if (head == null) { 
            lastNode = new Node(p); 
            head = new Node(lastNode, p);
            
        } 
        else { 
            Node curr = head; 
            while (curr.next != lastNode) { 
                curr = curr.next; 
            } 
            newNode.next = lastNode;
            curr.next = newNode;
        } 
    }


    /* A function that inserts a new node containing the point inputted in the 
    * function argument after the node in the Tour that contains the point closest 
    * in distance to the input point. If the Tour is initially empty, the function
    * would handle the tour exactly the same way as the function insertInOrder, 
    * creating head and lastNodes as nodes pointing towards the input point while
    * head.next points to lastNode. Else, the function would create a new node
    + that references to the input point, initialize a control variable
    * that stores the shortest distance between the input point and each 
    * iterated point in tour, and construct a new point called closestpoint that 
    * would reference the point in tour that has the shortest distance to the input 
    * point, which would be used for the condition of a second while loop that would 
    * iterate Tour again to that exact point and insert the new node containing the 
    * input point after the node containing the point in tour that has the shortest  
    * distance to the input point. The function also makes sure that if there are
    * multiple closest points in Tour, the new node would be inserted after the 
    * first such Point in the sequence by setting the currDistance < 
    * shortestDistance instead of currDistance <= shortestDistance so only
    * the first point that has the shortest distance would be referenced
    * by closestPoint.
    */
    public void insertNearest(Point p) {
        Node newNode = new Node(p);
        double shortestDistance = Double.MAX_VALUE;
        Point closestPoint = null; 
        if (head == null) { 
            lastNode = new Node(p); 
            head = new Node(lastNode, p);
        } 
        else {
            Node curr = head;
            while (curr.next != null) {
                double currDistance = curr.point.distanceTo(newNode.point);
                if (currDistance < shortestDistance) {
                    shortestDistance = currDistance;
                    closestPoint = curr.point;
                }
                curr = curr.next;
            }
            Node curr2 = head;
            while (curr2.point != closestPoint) {
                curr2 = curr2.next;
            }
            newNode.next = curr2.next;
            curr2.next = newNode;
        }
    }
    
    /* A function that inserts a new node containing the point inputted in the 
    * function argument after the node in the Tour that contains the point that 
    * has the smallest increment distance to the input point. If the Tour is 
    * initially empty, the function would handle the tour exactly the same way as 
    * the function insertInOrder, creating head and lastNodes as nodes pointing t
    * owards the input point while head.next points to lastNode. 
    * Else, the function would create a new node
    + that references to the input point, initialize a control variable
    * that stores the smallest increment distance between the input point and 
    * each iterated point in tour, and construct a new point called smallestPoint 
    * that would reference the point in tour that has the shortest increment 
    * distance to the  input point, which would be used for the condition of a 
    * second while loop that would iterate Tour again to that exact point and insert 
    * the new node containing input point after the node containing the point in 
    * tour that has the shortest increment distance to the input point. The function 
    * also makes sure that if there are multiple smallest points in Tour, the new 
    * node would be inserted after the first such Point in the sequence by setting 
    * the incrementDistance < smallestIncrementDistanceinstead of incrementDistance  
    * <= smallestIncrementDistance so only the first point that
    * has the shortest increment distance would be referenced by smallestPoint.
    */
    public void insertSmallest(Point p) {
        Node newNode = new Node(p);
        double smallestIncrementDistance = Double.MAX_VALUE;
        Point smallestPoint = null;
        if (head == null) { 
            lastNode = new Node(p); 
            head = new Node(lastNode, p);
        } 
        else {
            Node curr = head;
            while (curr.next != null) {
                double incrementDistance = curr.point.distanceTo(newNode.point) + 
                newNode.point.distanceTo(curr.next.point) - curr.point.distanceTo(
                curr.next.point);
                if (incrementDistance < smallestIncrementDistance) {
                    smallestIncrementDistance = incrementDistance;
                    smallestPoint = curr.point;
                }
                curr = curr.next;
            }
            Node curr2 = head;
            while (curr2.point != smallestPoint) {
                curr2 = curr2.next;
            }
            newNode.next = curr2.next;
            curr2.next = newNode;
        }
    }

}