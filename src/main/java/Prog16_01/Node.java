package Prog16_01;

/**
 * Node class. Implements the node of a binary search tree.
 *
 * @author A. Hernandez. COP4534 Algorithm Techniques
 */
public class Node {

    private int info; // element stored in this node
    private Node left; // link to left child
    private Node right; // link to right child

    Node() {
        info = 0;
        left = right = null;
    }

    void setNode(int x, Node l, Node r) {
        info = x;
        left = l;
        right = r;
    }

    int getInfo() {
        return info;
    }

    void setInfo(int x) {
        info = x;
    }

    Node getLeftChild() {
        return left;
    }

    void setLeftChild(Node l) {
        left = l;
    }

    Node getRightChild() {
        return right;
    }

    void setRightChild(Node r) {
        right = r;
    }
}
