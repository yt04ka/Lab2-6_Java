package ua.lviv.iot.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Credit extends Service {
    private final int returnPeriodInMonths;

    public Credit(int minimumClientAge, boolean isAvailableOnline, float priceInUAH, int interest, int returnPeriodInMonths) {
        super(minimumClientAge, isAvailableOnline, priceInUAH,interest);
        this.returnPeriodInMonths = returnPeriodInMonths;
    }

    public String toCSV() {
        return returnPeriodInMonths + "," + super.toCSV();
    }

    public String getHeaders() {
        return "returnPeriodInMonths," + super.getHeaders();
    }

}
