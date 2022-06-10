import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.lviv.iot.manager.Bank;
import ua.lviv.iot.models.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class BankTest {

    private static Bank bank;

    @Test
    public void testAddingNewServices(){
        Bank bank = new Bank();
        assertEquals(bank.getAllServicesSortedByInterest().size(),0);
        bank.addNewService(new Credit(18,true,0,1,12));
        bank.addNewService(new Credit(21,true,0,1,36));
        assertEquals(bank.getAllServicesSortedByInterest().size(),2);
    }

    @Test
    public void testGettingAllAvailableOnlineServices(){
        List<Service> result = bank.getAllAvailableOnlineServices();
        for (Service service :result) {
            assertTrue(service.isAvailableOnline());
        }
    }


    @Test
    public void testGettingCreditsSortedByInterestNullCase(){
        Bank bank = null;
        assertThrows(NullPointerException.class,()-> bank.getCreditsSortedByInterest(true));
    }

    @Test
    public void testGettingCreditsSortedByInterestOneElementCase(){
        Bank bank = new Bank();
        Credit credit = new Credit(18,true,0,1,12);
        bank.addNewService(credit);
        List<Credit> list = bank.getCreditsSortedByInterest(true);
        assertEquals(1,list.size());
        assertTrue(list.contains(credit));
    }

    @Test
    public void testGettingCreditsSortedByInterestRegularCase(){
        List<Credit> list = bank.getCreditsSortedByInterest(true);
        assertTrue(list.size() > 1);
        float a = Float.MAX_VALUE;
        for (Service service :list) {
            assertTrue(service.getInterest() <= a);
            a = service.getInterest();
        }
    }

    @Test
    public void testGettingDepositsSortedByTermNullCase(){
        Bank bank = null;
        assertThrows(NullPointerException.class,()-> bank.getDepositsSortedByTerm(true));
    }

    @Test
    public void testGettingDepositsSortedByTermOneElementCase(){
        Bank bank = new Bank();
        Deposit deposit = new Deposit(18,true,0,10,12);
        bank.addNewService(deposit);
        List<Deposit> list = bank.getDepositsSortedByTerm(true);
        assertEquals(1,list.size());
        assertTrue(list.contains(deposit));
    }

    @Test
    public void testGettingDepositsSortedByTermRegularCase(){
        List<Deposit> list = bank.getDepositsSortedByTerm(true);
        assertTrue(list.size() > 1);
        int a = Integer.MAX_VALUE;
        for (Deposit deposit :list) {
            assertTrue(deposit.getTermOfDeposit() <= a);
            a = deposit.getTermOfDeposit();
        }
    }

    @BeforeEach
    void initBank(){
        bank = new Bank();
        bank.addNewService(new Credit(18,true,0,1,12));
        bank.addNewService(new Credit(21,true,0,1,36));
        bank.addNewService(new Credit(18,false,1000,0,12));
        bank.addNewService(new Credit(21,true,0,5,10));
        bank.addNewService(new Credit(18,false,0,2,24));
        bank.addNewService(new Credit(18,true,0,10,6));

        bank.addNewService(new Deposit(18,false,100,20,24));
        bank.addNewService(new Deposit(18,true,500,10,12));
        bank.addNewService(new Deposit(21,true,50,10,48));
        bank.addNewService(new Deposit(18,true,0,5,36));
    }

}
