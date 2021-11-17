package lapr.project.BSTesinf;

import java.awt.geom.Point2D;
import java.util.*;

/**
 * Generic Class for Kd Tree
 *
 * @author Ana Albergaria <1201518.isep.ipp.pt>
 */

public class KDTree<T> {

    /** Nested static class for a 2d tree node. */
    protected static class Node<T> {
        /**
         * a point with x and y coordinates
         */
        private Point2D.Double coords;
        /**
         * a reference to the left child (if any)
         */
        private Node<T> left;
        /**
         * a reference to the right child (if any)
         */
        private Node<T> right;
        /**
         * an element stored at this node
         */
        private T element;
        /**
         * Constructs a node with the given element and coordinates x and y.
         *
         * @param element  the element to be stored
         * @param x   coordinate x
         * @param y coordinate y
         */
        public Node(T element, double x, double y) {
            this.coords = new Point2D.Double(x,y);
            this.element = element;
        }
        /**
         * Returns the element of node
         * @return the element of node
         */
        public T getElement() {
            return element;
        }

        /**
         * Sets the element of node
         * @param element the element of node
         */
        public void setElement(T element) {
            this.element = element;
        }
        /**
         * Returns x coordinate
         * @return x coordinate
         */
        public Double getX() {
            return coords.x;
        }

        /**
         * Returns y coordinate
         * @return y coordinate
         */
        public Double getY() {
            return coords.y;
        }
    }
    //----------- end of nested Node class -----------

    /**
     * Comparator for the x coordinates
     */
    private final Comparator<Node<T>> cmpX = new Comparator<Node<T>>() {

        @Override
        public int compare(Node<T> p1, Node<T> p2) {
            return Double.compare(p1.getX(), p2.getX());
        }
    };
    /**
     * Comparator for the y coordinates
     */
    private final Comparator<Node<T>> cmpY = new Comparator<Node<T>>() {

        @Override
        public int compare(Node<T> p1, Node<T> p2) {
            return Double.compare(p1.getY(), p2.getY());
        }
    };

    /**
     * Root of the tree
     */
    private Node<T> root;
    /**
     * Constructs an empty Kd Tree
     */
    public KDTree() {
        root = null;
    }

    /**
     * Finds the nearest neighbour
     * @param x x coordinate
     * @param y y coordinate
     * @return the nearest neighbour from point(x,y)
     */
    public T findNearestNeighbour(double x, double y) {
        Node<T> closestNode = root;
        return findNearestNeighbour(root, x, y, closestNode, true);
    }

    private T findNearestNeighbour(Node<T> node, final double x, final double y, Node<T> closestNode, boolean divX) {

        if (node == null)
            return null;

        double d = Point2D.distanceSq(node.coords.x, node.coords.y, x, y);
        double closestDist = Point2D.distanceSq(closestNode.coords.x,
                closestNode.coords.y, x, y);

        if (closestDist > d) {
            closestNode.setElement(node.getElement());
        }
        double delta = divX ? x - node.coords.x : y - node.coords.y;
        double delta2 = delta * delta;
        Node<T> node1 = delta < 0 ? node.left : node.right;
        Node<T> node2 = delta < 0 ? node.right : node.left;
        findNearestNeighbour(node1, x, y, closestNode, !divX);
        if (delta2 < closestDist) {
            findNearestNeighbour(node2, x, y, closestNode, !divX);
        }
        return closestNode.getElement();
    }

    /**
     * Inserts an element in the tree
     * @param element the element to be inserted
     * @param x x coordinate
     * @param y y coordinate
     */
    public void insert(T element, double x, double y) {
        Node<T> node = new Node<>(element, x, y);
        if (root == null)
            root = node;
        else
            insert(node, root, true);
    }

    //Node<Port> node = new Node(port, latitude, longitude);
    private void insert(Node<T> node, Node<T> currentNode, boolean divX) {
        if (node == null)
            return;
        int cmpResult = (divX ? cmpX : cmpY).compare(node, currentNode);
        if (cmpResult == -1)
            if(currentNode.left == null)
                currentNode.left = node;
            else
                insert(node, currentNode.left, !divX);
        else
        if(currentNode.right == null)
            currentNode.right = node;
        else
            insert(node, currentNode.right, !divX);
    }
}

