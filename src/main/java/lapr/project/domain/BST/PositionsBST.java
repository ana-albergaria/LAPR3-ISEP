package lapr.project.domain.BST;

import lapr.project.BSTesinf.BST;
import lapr.project.domain.model.ShipPosition;

import java.util.*;

public class PositionsBST extends BST<ShipPosition> {
    public void createBstShipPosition() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<ShipPosition> getPositionalMessages(Date initialDate, Date finalDate) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void getPositionalMessages(Node<ShipPosition> node,
                                       List<String> listPositionalMessages,
                                       Date initialDate,
                                       Date finalDate) {
        /*if(node == null)
            return;

        getPositionalMessages(node.getLeft(), listPositionalMessages, initialDate, finalDate);

        Date currentBaseDateTime = node.getElement().getBaseDateTime();

        if( !(currentBaseDateTime.before(initialDate) || currentBaseDateTime.after(finalDate)) ) {
            listPositionalMessages.add(node.getElement().toString());
        }

        getPositionalMessages(node.getRight(), listPositionalMessages, initialDate, finalDate);

         */


        //criar e retornar Map só no controller

        throw new UnsupportedOperationException("Not supported yet.");


    }
}
