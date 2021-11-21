package lapr.project.domain.store;

import lapr.project.BSTesinf.KDTree;
import lapr.project.domain.BST.Ports2DTree;
import lapr.project.domain.model.Port;
import lapr.project.dto.PortFileDTO;

import java.util.ArrayList;
import java.util.List;


public class PortStore {
    private Ports2DTree ports2DTree = new Ports2DTree();

    /**
     * Returns the 2D Tree of Ports
     * @return 2D Tree of Ports
     */
    public Ports2DTree getPorts2DTree() {
        return ports2DTree;
    }

    /**
     * Creates a Port instance
     * @param portFileDTO portfile dto which contains all needed data
     * @return Port instance
     */
    public Port createPort(PortFileDTO portFileDTO){
        return new Port(portFileDTO.getIdentification(),portFileDTO.getName(),portFileDTO.getContinent(),
                portFileDTO.getCountry(), portFileDTO.getLat(), portFileDTO.getLon());
    }

    /**
     * Validates a Port instance
     * @param port port to be validated
     * @return true if it is valid; false otherwise
     */
    public boolean validatePort(Port port){
        if(port == null){
            return false;
        }
        return true;
    }

    /**
     * Method which saves the Port in the 2D Tree
     * and adds its Node for the balancing of the tree later
     *
     * @param port the Port instance
     * @return true if the Port was successfully added,
     * otherwise the Port isn't valid and returns false
     */
    public boolean savePort(Port port){
        if(!validatePort(port)){
            return false;
        }
        ports2DTree.insert(port, port.getLat(), port.getLon());
        return ports2DTree.addNode(port, port.getLat(), port.getLon());
    }

    /**
     * Method to balance the 2D Ports Tree
     */
    public void balancePorts2DTree() {
        List<KDTree.Node<Port>> listOfPortNodes = ports2DTree.getListOfPortNodes();
        //ports2DTree = new Ports2DTree();
        //ports2DTree.balanceTree(listOfPortNodes);
        /*ports2DTree = new Ports2DTree(listOfPortNodes);*/

    }




}
