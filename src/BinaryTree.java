import java.util.ArrayList ;
import java.util.Scanner ;

/**
 * </h2>BinaryTree.java - Starting point for a Binary Tree class.</h2>
 * <p>Description: Full description of BinaryTree class (from textbook) converted to
 *    a generic class.  (Note: excludes contains and remove)</p>
 * <p>Public Wrapper methods</p>
 * <ul>
 *   <li>constructor()</li>
 *   <li>boolean search(value)            - not a recursive version</li>
 *   <li>boolean add(value)               - calls recursive version</li>
 *   <li>E delete(value)                  - calls recursive version</li>
 *   <li>void showPreorder()              - calls recursive version</li>
 *   <li>void showInorder()               - calls recursive version</li>
 *   <li>void showPostorder()             - calls recursive version</li>
 *   <li>ArrayList<E> toList()            - cheap replacement for an iterator</li>
 * </ul>
 * <p>Helper methods (private unless otherwise specified)</p>
 * <ul>
 *   <li>add(Node *, value)               - recursive method to add a node</li>
 *   <li>delete(Node *, value)            - recursive method to remove a node</li>
 *   <li>searchNode(value)                - non-recursive method (public)</li>
 *   <li>displayPreorder(Node *)          - displays the subtree in preorder sequence</li>
 *   <li>displayInorder(Node *)           - displays the subtree in inorder sequence</li>
 *   <li>displayPostorder(Node *)         - displays the subtree in postorder sequence</li>
 *   <li>toList()                         - builds an ArrayList of values in the tree</li>
 * </ul>
 * @author Chris Merrill
 * @version @Module 8, demonstration
 */

public class BinaryTree<E extends Comparable<E>> {

    private TreeNode<E> root ;          // The top node in the tree
    private boolean addResult ;         // Return value from add method
    private E deleteReturn ;            // Return value from public delete

    /**
     * Constructor just initializes root to null
     */
    public BinaryTree() {                         // Constructor
        root = null ;
    }

    /**
     * Constructor which takes a data element and two subtrees for left and right
     */
    public BinaryTree(E data, BinaryTree<E> leftTree, BinaryTree<E> rightTree) {
        root = new TreeNode<E>(data) ;
        root.left = (leftTree == null) ? null : leftTree.root ;
        root.right = (rightTree == null) ? null : rightTree.root ;
    }

    /** Wrapper method to add a node to this tree
     * @param item Adds the item to this tree maintaining search tree ability
     * @return true if item was inserted successfully
     */
    public boolean add(E item) {
        root = add(root, item) ;
        return addResult ;
    }

    /** Recursive add method.
     * @param tree The local root of the subtree
     * @param toAdd The object to be inserted
     * @return The new local root that now contains the inserted item
     */
    private TreeNode<E> add(TreeNode<E> tree, E toAdd) {
        if (tree == null) {
            // item is not in the tree, make this the new root.
            addResult = true;
            return new TreeNode<E>(toAdd);
        } else if (toAdd.compareTo(tree.data) == 0) {
            // item is already in the list, reject
            addResult = false;
            return tree ;
        } else if (toAdd.compareTo(tree.data) < 0) {
            // item goes on left subtree
            tree.left = add(tree.left, toAdd);
            return tree ;
        } else {
            // item goes on right subtree
            tree.right = add(tree.right, toAdd);
            return tree ;
        }
    }

    /**
     * Wrapper class to display this tree in Inorder.
     */
    public void showInorder() {
        if (root == null) {
            System.out.print("(none)") ;
        } else {
            displayInorder(root) ;
        }
        System.out.println() ;
    }

    //***************************************************
    // Displays the values sorted in inorder sequence.  *
    //***************************************************
    private void displayInorder(TreeNode<E> tree) {
        if (tree != null)    {
            displayInorder(tree.left) ;
            System.out.print(tree.data + " ") ;
            displayInorder(tree.right) ;
        }
    }

    /*
     * Wrapper class to display this tree in Preorder
     */
    public void showPreorder() {
        if (root == null) {
            System.out.print("(none") ;
        } else {
            displayPreorder(root) ;
        }
        System.out.println() ;
    }

    //****************************************************
    // Displays the values sorted in preorder sequence.  *
    //****************************************************
    private void displayPreorder(TreeNode<E> tree) {
        if (tree != null) {
            System.out.print(tree.data + " ") ;
            displayPreorder(tree.left) ;
            displayPreorder(tree.right) ;
        }
    }

    /*
     * Wrapper class to display this tree in Postorder
     */
    public void showPostorder() {
        if (root == null) {
            System.out.print("(none)") ;
        } else {
            displayPostorder(root) ;
        }
        System.out.println() ;
    }

    //****************************************************
    // Displays the values sorted in postorder sequence. *
    //****************************************************
    private void displayPostorder(TreeNode<E> tree) {
        if (tree != null) {
            displayPostorder(tree.left) ;
            displayPostorder(tree.right) ;
            System.out.print(tree.data + " ") ;
        }
    }

    /**
     * Wrapper class to tell if an items is in the tree or not
     * @return true if the item is in the tree (must implement Comparable<E>
     */

    public boolean search(E dataToFind) {
        return searchNode(dataToFind) ;
    }

    //***************************************************
    // search determines if a value is present in       *
    // the tree. If so, the method returns true.      *
    // Otherwise, it returns false.                     *
    //                                                  *
    // Recursion is NOT used in this method, since    *
    // it just traverses the appropriate subtrees (not  *
    // the entire tree) and stops once value is found   *
    //***************************************************
    private boolean searchNode(E toFind) {

        TreeNode<E> tree = root ;                // start at the top

        while (tree != null) {
            if (tree.data.equals(toFind)) {
                return true ;
            } else if (toFind.compareTo(tree.data) < 0) {      // look only at appropriate subtree
                tree = tree.left ;
            } else {
                tree = tree.right ;
            }
        }
        return false ;
    }
    /**
     * Wrapper method delete.
     * @param target The object to be deleted
     * @return The object deleted from the tree
     *         or null if the object was not in the tree
     */
    public E delete(E target) {
        root = delete(root, target);
        return deleteReturn;
    }

    /**
     * <p>Recursive delete method.</p>
     * <p>Postcondition: The item is not in the tree;
     *       deleteReturn is equal to the deleted item
     *       as it was stored in the tree or null
     *       if the item was not found.</p>
     * @param localRoot The root of the current subtree
     * @param item The item to be deleted
     * @return The modified local root that does not contain
     *         the item
     */
    private TreeNode<E> delete(TreeNode<E> localRoot, E item) {
        if (localRoot == null) {
            // item is not in the tree.
            deleteReturn = null;
            return localRoot;
        }

        // Search for item to delete.
        int compResult = item.compareTo(localRoot.data);
        if (compResult < 0) {
            // item is smaller than localRoot.data.
            localRoot.left = delete(localRoot.left, item);
            return localRoot;
        } else if (compResult > 0) {
            // item is larger than localRoot.data.
            localRoot.right = delete(localRoot.right, item);
            return localRoot;
        } else {
            // item is at local root.
            deleteReturn = localRoot.data;
            if (localRoot.left == null) {
                // If there is no left child, return right child
                // which can also be null.
                return localRoot.right;
            } else if (localRoot.right == null) {
                // If there is no right child, return left child.
                return localRoot.left;
            } else {
                // Node being deleted has 2 children, replace the data
                // with inorder predecessor.
                if (localRoot.left.right == null) {
                    // The left child has no right child.
                    // Replace the data with the data in the
                    // left child.
                    localRoot.data = localRoot.left.data;
                    // Replace the left child with its left child.
                    localRoot.left = localRoot.left.left;
                    return localRoot;
                } else {
                    // Search for the inorder predecessor (ip) and
                    // replace deleted node's data with ip.
                    localRoot.data = findLargestChild(localRoot.left);
                    return localRoot;
                }
            }
        }
    }

    /**
     * <p>Find the node that is the inorder predecessor and replace it
     * with its left child (if any).</p>
     * <p>Postcondition: The inorder predecessor is removed from the tree.</p>
     * @param parent The parent of possible inorder predecessor (ip)
     * @return The data in the ip
     */
    private E findLargestChild(TreeNode<E> parent) {
        // If the right child has no right child, it is
        // the inorder predecessor.
        if (parent.right.right == null) {
            E returnValue = parent.right.data;
            parent.right = parent.right.left;
            return returnValue;
        } else {
            return findLargestChild(parent.right);
        }
    }

    /**
     * Wrapper method to returns an ArrayList of values in the binary tree
     * using inorder sequence.
     * @return an ArrayList containing the element in the tree in inorder sequence.
     */
    public ArrayList<E> toList() {
        ArrayList<E> list = new ArrayList<>() ;
        toList(list, root) ;
        return list ;
    }

    /*
     * Helper method to create an ArrayList of items in the binary tree
     */
    private void toList(ArrayList<E> list, TreeNode<E> tree) {

        if (tree != null) {
            toList(list, tree.left) ;
            list.add(tree.data) ;
            toList(list, tree.right) ;
        }
    }

    /**
     * Use pre-order to create a sequence of strings indented by level
     */
    public String toString() {
        StringBuilder sb = new StringBuilder() ;
        preOrderTraverse(root, 1, sb) ;
        return sb.toString() ;
    }

    /**
     * Perform a preorder traversal.
     * @param node The local root
     * @param depth The depth
     * @param sb The string buffer to save the output
     */
    private void preOrderTraverse(TreeNode<E> node, int depth, StringBuilder sb) {
        for (int i = 1 ; i < depth ; i++) {
            sb.append("  ") ;
        }
        if (node == null) {
            sb.append("null\n") ;
        } else {
            sb.append(node.data) ;
            sb.append("\n") ;
            preOrderTraverse(node.left, depth + 1, sb) ;
            preOrderTraverse(node.right, depth + 1, sb) ;
        }
    }

    /**
     * Method to read a binary tree in preorder sequence.
     * @param reader The input file
     * @return The binary tree
     */
    public static BinaryTree<String> readBinaryTree(Scanner reader) {
        // Read a line and trim leading and trailing spaces.
        String data = reader.nextLine().trim() ;
        if (data.equals("null")) {
            return null ;
        } else {
            BinaryTree<String> leftTree = readBinaryTree(reader) ;
            BinaryTree<String> rightTree = readBinaryTree(reader) ;
            return new BinaryTree<String>(data, leftTree, rightTree) ;
        }
    }


    /*
    public static String decodeToMorse(BinaryTree<String> morseCodeTree, String message){
        TreeNode root = morseCodeTree.root;
        if(root == null || message == null || message.isEmpty())
            return "";

        StringBuilder morseCode = new StringBuilder();

        for(char c: message.toCharArray()){
            String morseChar = decodeCharToMorse(root, c);
            if(!morseChar.isEmpty()){
                morseCode.append(morseChar).append(" ");
            }
        }

        return morseCode.toString();
    }

    public static String decodeCharToMorse(TreeNode node, char c){
        if(node == null)
            return "";

        //Below is a line of java code that gets the character of the binary tree from the node Data, by passing it through a method made to regex everything that isnt a-z, A-Z, and casts it to a string
        String nodeData = getString(String.valueOf(node.data));

        if(nodeData.equals(c))
            return "";

        String leftDecode = decodeCharToMorse(node.left, c);
        if(!leftDecode.isEmpty())
            return "." + leftDecode;

        String rightDecode = decodeCharToMorse(node.right, c);
        if(!rightDecode.isEmpty())
            return "-" + rightDecode;


        return "";
    }

     */


    /**
     * Decodes a morse code message into a readable String of characters
     * @param morse The morse code entry
     * @param morseCode The morse code binary tree
     * @return The message, decoded from morse code and into english
     */
    public static String decode(String morse, BinaryTree<String> morseCode){
        //0 is dot ( . ) 1 is dash ( - )
        String currentChar = "";
        TreeNode currentNode = morseCode.root;
        StringBuffer result = new StringBuffer("");

        for(int i = 0; i < morse.length(); i++){
            //go through string of morse character by character
            currentChar = morse.substring(i, i+1);

            //if character is .
            if(currentChar.equals(".")){

                //if the next left node isnt null, move currentNode to that node
                if(currentNode.left != null) {
                    currentNode = currentNode.left;
                } //if currentNode left is null, keep currentNode where it is
            } else if(currentChar.equals("-")){

                if(currentNode.right != null){
                    currentNode = currentNode.right;
                }
            } else{
                //add the currentNode data to the String
                result = result.append(currentNode.data);
                currentNode = morseCode.root;
            }
        }
        result = result.append(currentNode.data);

        return result.toString();
    }


    /**
     * Encodes a string message into morse code with the help of searchTree
     * @param str The string to encode into morse code
     * @param morseCode The morse code tree
     * @return The message, in morse code
     */
    public static String encode(String str, BinaryTree<String> morseCode){
        TreeNode currentNode = morseCode.root;
        String result = "";
        String morseCodeString = "";
        String character;

        for(int i = 0; i < str.length(); i++){
            //search through str char by char and search morseCode tree for that character
            character = str.substring(i,i+1);
            //System.out.println("    Char: " + character);
            result += searchTree(currentNode, character, morseCodeString) + " ";
            //System.out.println("Morse code: " + result);
        }
        return result;
    }
/*
    public static String searchTree(TreeNode currentNode, char character, String morseCodeString){
        String data = getString((String) currentNode.data);
        System.out.println("DATA: " + data);

        if(data.equals(String.valueOf(character))){
            return morseCodeString;
        } else {
            String leftResult = "";
            String rightResult = "";

            if(currentNode.left != null){
                //left is .
                leftResult = searchTree(currentNode.left, character, morseCodeString + ".");
            }
            if(!leftResult.isEmpty()){
                return leftResult;
            }

            if(currentNode.right != null){
                //right is -
                rightResult = searchTree(currentNode.right, character, morseCodeString + "-");
            }
            if(!rightResult.isEmpty()){
                return rightResult;
            }

            return morseCodeString;
        }
    }
    */

    /**
     * Search tree recursively searches the morse code binary tree for a specific character, adding a "." for left traversals and a "-" for right traversals
     * @param currentNode The current node in the Binary Tree
     * @param character The character we are looking for
     * @param morseCodeString The resulting morse code of that character
     * @return The morse code of a character
     */
    public static String searchTree(TreeNode currentNode, String character, String morseCodeString){
        if(currentNode == null) {
            return null;
        }
        String leftNode = searchTree(currentNode.left, character, morseCodeString + ".");
        String rightNode = searchTree(currentNode.right, character, morseCodeString + "-");

        String data = getString((String) currentNode.data);
        //System.out.println("DATA: " + data + ", Character: " + character);
        //


        if(data.equals(character)){
            return morseCodeString;
        }
        if(leftNode != null){
            return leftNode;
        } else{
            return rightNode;
        }

        /*else{
            if(currentNode.data == null)

            if(currentNode.left != null){
                //left is .
                return searchTree(currentNode.left, character, morseCodeString + ".");
            }
            if(currentNode.right != null){
                //right is -
                return searchTree(currentNode.right, character, morseCodeString + "-");
            }

             */


    }


    /**
     * This replaces all non alphabetical characters with "", shearing the numbers off of the string input
     * @param string The input to remove any non alphabetical characters
     * @return The string with only alphabetical characters
     */
    public static String getString(String string){
        if(string == null)
            return "";
        return string.replaceAll("[^a-zA-Z]", "");
    }


/*
    private TreeNode nextNode(TreeNode current, String zeroOrOne){

    }

     */

    // Inner Node class with two references to other nodes (a left and a right)
    private static class TreeNode<E> {

        // The TreeNode class is used to build the tree.  It is internal to the BinaryTree
        // class and therefore does not need to be specified as <E>
        E data ;
        TreeNode<E> left ;
        TreeNode<E> right ;

        // For creating leaves
        TreeNode(E dataElement) {
            this(dataElement, null, null) ;
        }

        // Full constructor
        TreeNode(E dataElement, TreeNode<E> leftTree, TreeNode<E> rightTree) {
            data = dataElement ;
            left = leftTree ;
            right = rightTree ;
        }


    }
}


