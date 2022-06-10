package ua.lviv.iot.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Deposit extends Service {
    private final int termOfDeposit;

    public Deposit(int minimumClientAge, boolean isAvailableOnline, float priceInUAH, int interest, int termOfDeposit) {
        super(minimumClientAge, isAvailableOnline, priceInUAH,interest);
        this.termOfDeposit = termOfDeposit;
    }

    public String toCSV(){
        return termOfDeposit +","+super.toCSV();
    }

    public String getHeaders(){
        return "termOfDeposit,"+super.getHeaders();
    }

}
