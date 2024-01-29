public class NodeRearrangement {
  
  // Inputs: Nodes a & b both are heads
  // of node chains as described in the readme.
  // Outputs: The node stored in variable a. 
  // - Do not change the final line (return a).
  // - This function doesn't have to work for general
  //   chains of Nodes, just the example provided in the readme.
  // - Do not call any constructors! Your solution must
  //   rearrange the existing objects.
  public static Node weave(Node a, Node b) {
    
    b.next.next = a.next;
    a.next = b.next;
    b.next = a;
    a = b;
    b = null; 


    return a; // don't change this line!
  }
}