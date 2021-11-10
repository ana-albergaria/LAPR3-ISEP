package lapr.project.domain.BST;

import lapr.project.BSTesinf.BST;
import lapr.project.domain.model.Ship;

public class ShipBstImo extends BST<Ship> {
    /*
     * Inserts an element in the tree.
     */
    @Override
    public void insert(Ship element){
        root = insert(element, root);
    }
    private Node<Ship> insert(Ship element, Node<Ship> node){
        if(node == null){
            return new Node(element, null, null);
        }
        if(node.getElement().getIMO().compareTo(element.getIMO()) > 0){
            node.setLeft(insert(element, node.getLeft()));
        }else{
            if(node.getElement().getIMO().compareTo(element.getIMO()) < 0){
                node.setRight(insert(element,node.getRight()));
            }
        }
        return node;
    }

    /**
     * Removes an element from the tree maintaining its consistency as a Binary Search Tree.
     */
    @Override
    public void remove(Ship element){
        root = remove(element, root());
    }

    private Node<Ship> remove(Ship element, Node<Ship> node) {

        if (node == null) {
            return null;    //throw new IllegalArgumentException("Element does not exist");
        }
        if (element.getIMO().compareTo(node.getElement().getIMO())==0) {
            // node is the Node to be removed
            if (node.getLeft() == null && node.getRight() == null) { //node is a leaf (has no childs)
                return null;
            }
            if (node.getLeft() == null) {   //has only right child
                return node.getRight();
            }
            if (node.getRight() == null) {  //has only left child
                return node.getLeft();
            }
            Ship min = smallestElement(node.getRight());
            node.setElement(min);
            node.setRight(remove(min, node.getRight()));
        }
        else if (element.getIMO().compareTo(node.getElement().getIMO()) < 0)
            node.setLeft( remove(element, node.getLeft()) );
        else
            node.setRight( remove(element, node.getRight()) );

        return node;
    }


    /**
     * Method which calls its private method to obtain
     * the ship chosen by the user
     *
     * @param imo the imo code
     *
     * @return Ship chosen by the user
     */
    public Ship getShipByImo(String imo)  {
        return getShipByImo(root, imo);
    }

    private Ship getShipByImo(Node<Ship> node, String imo) {
        if(node == null)
            return null;
        if(node.getElement().getIMO().equals(imo)) {
            return node.getElement();
        }
        if(node.getElement().getIMO().compareTo(imo) > 0) {
            return getShipByImo(node.getLeft(), imo);
        }
        if(node.getElement().getIMO().compareTo(imo) < 0) {
            return getShipByImo(node.getRight(), imo);
        }
        return node.getElement();
    }

}
