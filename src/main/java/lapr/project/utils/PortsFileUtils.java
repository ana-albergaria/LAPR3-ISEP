package lapr.project.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PortsFileUtils {

    private List<String> dataLabels;

    public PortsFileUtils (){
        dataLabels = new ArrayList<>();
    }

    /*public List<PortsFileDTO> getPortsDataToDto(String filePath){
        File csvFile = new File(filePath);
        List<PortsFileDTO> processedListData = new ArrayList<>();
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(csvFile))) {
            String line = bufferedReader.readLine();
            if (line==null || !line.equals("continent,country,code,port,lat,lon")){
                throw new IllegalArgumentException("Incompatible file format.");
            }
            dataLabels = Arrays.asList(line.split(","));
            line = bufferedReader.readLine();
            while(line != null){
                String [] attributes = line.split(",");
                processedListData.add(attributesToDto(attributes));
                line = bufferedReader.readLine();
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return processedListData;
    }

    private  PortsFileDTO attributesToDto(String[] shipData) throws ParseException {
        return new PortsFileDTO(shipData[dataLabels.indexOf("continent")],
                shipData[dataLabels.indexOf("country")],
                Integer.parseInt(shipData[dataLabels.indexOf("code")]),
                shipData[dataLabels.indexOf("port")],
                Double.parseDouble(shipData[dataLabels.indexOf("lat")]),
                Double.parseDouble(shipData[dataLabels.indexOf("lon")]));
    }*/

}
