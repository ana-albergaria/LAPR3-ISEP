package lapr.project.ui;

import lapr.project.controller.*;
import lapr.project.data.dataControllers.CreateFreightNetworkController;
import lapr.project.domain.dataStructures.FreightNetwork;
import lapr.project.domain.model.*;
import lapr.project.dto.PortFileDTO;
import lapr.project.dto.ShipsFileDTO;
import lapr.project.utils.PortsFileUtils;
import lapr.project.utils.ShipsFileUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EsinfDemoSprint3 {
    public static void main(String[] args) throws IOException, ParseException {
        Company comp = App.getInstance().getCompany();
        CreateFreightNetworkController createFreightNetworkController = new CreateFreightNetworkController(comp);
        //US 301: PRINTING FREIGHT NETWORK TO FILE;
        createFreightNetworkController.createFreightNetworkFromDb();
        FreightNetwork freightNetwork = comp.getFreightNetwork();
        try(PrintWriter out = new PrintWriter("FreightNet.txt")){
            out.println(freightNetwork.getFreightNetwork());
        }

        //US302
        ColorMapController controller = new ColorMapController(comp);
        Map<Capital, Integer> map = controller.colorMap();
        for(Capital c : map.keySet()){
            System.out.print("Capital: " + c);
            System.out.print(" Color: " + map.get(c));
            System.out.println();
        }


        //US303
        ClosenessPlacesController closenessPlacesController = new ClosenessPlacesController(comp);
        Map<String, List<Map.Entry<Location, Double>>> closenessMap = closenessPlacesController.getClosenessPlacesByContinent();
        System.out.println();
        for (String cont : closenessMap.keySet()){
            System.out.println(cont+":");
            System.out.println(closenessMap.get(cont));
        }
    }
}
