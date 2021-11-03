
package lapr.project.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DEI-ESINF
 */
public class Utils {
    public static <E extends Comparable<E>> Iterable<E> sortByBST(List<E> listUnsorted){
        BST<E> bstFromList = new BST<>();
        for(E item : listUnsorted){
            bstFromList.insert(item);
        }
        return bstFromList.inOrder();
    }    
}
