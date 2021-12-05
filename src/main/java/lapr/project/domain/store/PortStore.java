package lapr.project.domain.store;

import lapr.project.BSTesinf.KDTree;
import lapr.project.data.PortStoreDB;
import lapr.project.domain.BST.Ports2DTree;
import lapr.project.domain.model.Port;
import lapr.project.dto.PortFileDTO;

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
        return new Port(portFileDTO.getIdentificationDto(),portFileDTO.getNameDto(),portFileDTO.getContinentDto(),
                portFileDTO.getCountryDto(), portFileDTO.getLatDto(), portFileDTO.getLonDto());
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
        PortStoreDB portStoreDatabase = new PortStoreDB();
        /*portStoreDatabase.save(App.getInstance().getConnection(), port);*/
        return ports2DTree.addNode(port, port.getLat(), port.getLon());
    }

    /**
     * Method to balance the 2D Ports Tree
     */
    public void balancePorts2DTree() {
        List<KDTree.Node<Port>> listOfPortNodes = ports2DTree.getListOfPortNodes();
        ports2DTree = new Ports2DTree(listOfPortNodes);
    }

    /**
     * Method to get the nearest Port given a certain Ship's coordinates
     * @param coordinates Ship's coordinates
     * @return nearest Port from that Ship
     */
    public Port findClosestPort(List<Double> coordinates) {

        return ports2DTree.findClosestPort(coordinates);
    }


}
