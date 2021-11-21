package lapr.project.domain.BST;

import lapr.project.BSTesinf.KDTree;
import lapr.project.domain.model.Port;
import lapr.project.domain.model.Ship;

import java.util.ArrayList;
import java.util.List;

public class Ports2DTree extends KDTree<Port> {
    private List<Node<Port>> listOfPortNodes = new ArrayList<>();

    public Ports2DTree() {
        root = null;
    }

    /**
     * Constructs a balanced Port 2D Tree
     * @param nodes the list of elements
     */
    public Ports2DTree(List<Node<Port>> nodes) {
        balanceTree(nodes);
    }

    /**
     * Add node to the list of nodes
     * @param port port
     * @param lat latitude of the port
     * @param lon longitude of the port
     */
    public boolean addNode(Port port, double lat, double lon) {
        Node<Port> node = new Node<>(port, lat, lon);
        if(!listOfPortNodes.contains(node)) {
            listOfPortNodes.add(node);
            return true;
        }
        return false;
    }

    /**
     * Returns the list of valid nodes to be inserted to to the balanced KD Tree.
     *
     * @return list of valid nodes to be inserted to to the balanced KD Tree
     */
    public List<Node<Port>> getListOfPortNodes() {
        return listOfPortNodes;
    }
}
