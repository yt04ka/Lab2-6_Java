import org.junit.jupiter.api.Test;
import ua.lviv.iot.manager.ServiceWriter;
import ua.lviv.iot.models.Credit;
import ua.lviv.iot.models.Deposit;
import ua.lviv.iot.models.Service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServiceWriterTest {

    @Test
    public void testWritingToSCVEmptyCase() throws IOException {
        ServiceWriter.writeToFile(new ArrayList<>());
        File file = new File("result.csv");
        Scanner scanner = new Scanner(file, StandardCharsets.UTF_8);
        StringBuilder result = new StringBuilder();
        while (scanner.hasNextLine()){
            result.append(scanner.nextLine());
        }
        assertEquals("", result.toString());
    }

    @Test
    public void testWritingToScvOneClassInstancesCase() throws IOException {
        List<Service> services = new ArrayList<>();
        services.add(new Credit(18,true,0,1,12));
        services.add(new Credit(21,true,0,10,12));
        ServiceWriter.writeToFile(services);
        File file = new File("result.csv");
        Scanner scanner = new Scanner(file,StandardCharsets.UTF_8);
        StringBuilder result = new StringBuilder();
        while (scanner.hasNextLine()){
            result.append(scanner.nextLine());
        }
        assertEquals("\"returnPeriodInMonths\",\"minimumClientAge\",\"isAvailableOnline\",\"priceInUAH\",\"interest\"" +
                "\"12\",\"18\",\"true\",\"0.0\",\"1.0\"" +
                "\"12\",\"21\",\"true\",\"0.0\",\"10.0\"", result.toString());
    }

    @Test
    public void testWritingToScvRegularCase() throws IOException {
        List<Service> services = new ArrayList<>();
        services.add(new Credit(18,true,0,1,12));
        services.add(new Deposit(18,true,0,10,36));
        ServiceWriter.writeToFile(services);
        File file = new File("result.csv");
        Scanner scanner = new Scanner(file,StandardCharsets.UTF_8);
        StringBuilder result = new StringBuilder();
        while (scanner.hasNextLine()){
            result.append(scanner.nextLine());
        }
        assertEquals("\"returnPeriodInMonths\",\"minimumClientAge\",\"isAvailableOnline\",\"priceInUAH\",\"interest\"" +
                "\"12\",\"18\",\"true\",\"0.0\",\"1.0\"" +
                "\"termOfDeposit\",\"minimumClientAge\",\"isAvailableOnline\",\"priceInUAH\",\"interest\"" +
                "\"36\",\"18\",\"true\",\"0.0\",\"10.0\"", result.toString());
    }


}
