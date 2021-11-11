package lapr.project.controller;

import lapr.project.domain.BST.ShipBST;
import lapr.project.domain.model.Company;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.TreeMap;

/** Controller class for showing the positional messages of a ship temporally organized
 * and associated with each of the ships
 *
 *  @author Ana Albergaria <1201518@isep.ipp.pt>
 *
 */
public class ShowPairsOfShipsController {
    /**
     * The company associated to the Controller.
     */
    private Company company;

    /**
     * Builds an empty constructor for having the actual instance of the company when instantiated.
     */
    public ShowPairsOfShipsController() {
        this(App.getInstance().getCompany());
    }


    /**
     * Builds a Show Pairs Of Ship's instance receiving the company.
     *
     * @param company company associated to the Controller.
     */
    public ShowPairsOfShipsController(Company company) {
        this.company = company;
    }

    public List<TreeMap<Double, String>> getPairsOfShips() throws IOException {
        String header = String.format("%-25s%-25s%-25s%-25s%-25s%-25s%-25s%-25s%-25s%n", "Ship1 MMSI", "Ship2 MMSI", "distOrig", "distDest","Movs Ship 1", "TravelDist Ship1", "Movs Ship 2", "TravelDist Ship2", "TravelDist Diff");

        /*File file = new File("us107PairsOfShips.txt");
        if(!file.exists())
            file.createNewFile();


        try {
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(header);

            bw.close();
            //return true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            bw.close();
            fw.close();
        }
         */


        ShipBST shipsBST = this.company.getShipStore().getShipsBstMmsi();
        List<TreeMap<Double, String>> listPairsOfShips = shipsBST.getPairsOfShips();
        return listPairsOfShips;
        //throw new UnsupportedOperationException();
    }




}
