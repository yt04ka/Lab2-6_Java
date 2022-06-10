package ua.lviv.iot.manager;

import com.opencsv.CSVWriter;
import ua.lviv.iot.models.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ServiceWriter {
    public static void writeToFile(List<Service> services) throws IOException {
        File file = new File("result.csv");
        FileWriter outputFile = new FileWriter(file, StandardCharsets.UTF_8);
        CSVWriter writer = new CSVWriter(outputFile);
        String type = "";
        for (Service service : services) {
            if(!type.equals(service.getClass().getTypeName())){
                writer.writeNext(service.getHeaders().split(","));
                type = service.getClass().getTypeName();
            }
                writer.writeNext(service.toCSV().split(","));
        }
        writer.close();
    }
}
