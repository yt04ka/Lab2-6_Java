package ua.lviv.iot.models;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public abstract class Service {
    private final int minimumClientAge;
    private final boolean isAvailableOnline;
    private final float priceInUAH;
    private final float interest;

    public String toCSV(){
        return minimumClientAge +","+ isAvailableOnline +","+priceInUAH+","+interest;
    }

    public String getHeaders(){
        return "minimumClientAge,isAvailableOnline,priceInUAH,interest";
    }

}
