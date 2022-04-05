package Prog16_01;

/**
 * BinarySearchTree class. Implements a binary search tree.
 *
 * @author A. Hernandez. COP4534 Algorithm Techniques
 */
public class BinarySearchTree {
    private final Node root; // root of the bst; implemented as a dummy node.

    public BinarySearchTree() {
        root = new Node(); // dummy node as the root
        root.setLeftChild(null);
        root.setRightChild(null);
        root.setInfo(-1);
    }

    public boolean isEmpty() {
        return root.getLeftChild() == null;
    }

    public void preorder() {
        preorder(root.getLeftChild());
        System.out.println();
    }

    public void BFS() {
        Queue q = new Queue();

        if (root.getLeftChild() != null) {
            q.enqueue(root.getLeftChild());
            while (!q.isEmpty()) {
                Node p = q.getFront();
                q.dequeue();

                System.out.print(p.getInfo() + " ");
                if (p.getLeftChild() != null) q.enqueue(p.getLeftChild());
                if (p.getRightChild() != null) q.enqueue(p.getRightChild());
            }

            System.out.println();
        }
    }

    public String toString() {
        return toString(root.getLeftChild());
    }

    public void add(int x) {
        if (root.getLeftChild() == null) {
            Node p = new Node();
            p.setNode(x, null, null);
            root.setLeftChild(p);
        } else add(x, root.getLeftChild());
    }

    private void preorder(Node p) {
        if (p != null) {
            System.out.print(p.getInfo() + " ");
            preorder(p.getLeftChild());
            preorder(p.getRightChild());
        }
    }

    private String toString(Node p) {
        if (p != null) {
            return toString(p.getLeftChild()) + p.getInfo() + " " + toString(p.getRightChild());
        } else return "";
    }

    private void add(int x, Node p) {
        if (x < p.getInfo())
            if (p.getLeftChild() == null) {
                Node q = new Node();
                q.setNode(x, null, null);
                p.setLeftChild(q);
            } else add(x, p.getLeftChild());
        else if (p.getRightChild() == null) {
            Node q = new Node();
            q.setNode(x, null, null);
            p.setRightChild(q);
        } else add(x, p.getRightChild());
    }
}
