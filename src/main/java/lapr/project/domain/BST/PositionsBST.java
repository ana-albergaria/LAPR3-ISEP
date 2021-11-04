package lapr.project.domain.BST;

import lapr.project.BSTesinf.BST;
import lapr.project.domain.model.Ship;
import lapr.project.domain.model.ShipPosition;

import java.time.LocalDate;
import java.util.*;

public class PositionsBST extends BST<ShipPosition> {
    public void createBstShipPosition() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<ShipPosition> getPositionalMessages(LocalDate initialDate, LocalDate finalDate) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void getPositionalMessages(Node<ShipPosition> node,
                                       List<String> listPositionalMessages,
                                       LocalDate initialDate,
                                       LocalDate finalDate) {
        /*if(node == null)
            return;

        getPositionalMessages(node.getLeft(), listPositionalMessages, initialDate, finalDate);

        LocalDate currentBaseDateTime = node.getElement().getBaseDateTime();

        if( !(currentBaseDateTime.isBefore(initialDate) || currentBaseDateTime.isAfter(finalDate)) ) {
            listPositionalMessages.add(node.getElement().toString());
        }

        getPositionalMessages(node.getRight(), listPositionalMessages, initialDate, finalDate);
         */

        //criar e retornar Map só no controller

        //throw new UnsupportedOperationException("Not supported yet.");


    }
}
