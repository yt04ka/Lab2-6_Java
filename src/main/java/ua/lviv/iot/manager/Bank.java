package ua.lviv.iot.manager;

import ua.lviv.iot.models.Credit;
import ua.lviv.iot.models.Deposit;
import ua.lviv.iot.models.Service;

import java.util.*;
import java.util.stream.Collectors;

public class Bank {
    private final List<Service> services = new ArrayList<>();

    public void addNewService(Service service) {
        services.add(service);
    }

    public List<Service> getAllServices() {
        return services;
    }

    public List<Service> getAllAvailableOnlineServices() {
        return services.stream().filter(Service::isAvailableOnline).collect(Collectors.toList());
    }

    public List<Credit> getCreditsSortedByInterest(boolean reverse){
        List<Credit> result = services.stream().
                filter(service -> service.getClass().getTypeName().equals("ua.lviv.iot.models.Credit")).
                map(service -> (Credit)service).
                sorted(Comparator.comparingDouble(Service::getInterest)).collect(Collectors.toList());
        if(reverse) {
            List<Credit> list = new ArrayList<>(result);
            Collections.reverse(list);
            result = new ArrayList<>(list);
        }
        return result;
    }

    public List<Deposit> getDepositsSortedByTerm(boolean reverse){
        List<Deposit> result = services.stream().
                filter(service -> service.getClass().getTypeName().equals("ua.lviv.iot.models.Deposit")).
                map(service -> (Deposit)service).
                sorted(Comparator.comparingDouble(Deposit::getTermOfDeposit)).collect(Collectors.toList());
        if(reverse) {
            List<Deposit> list = new ArrayList<>(result);
            Collections.reverse(list);
            result = new ArrayList<>(list);
        }
        return result;
    }

    public List<Service> getAllServicesSortedByInterest() {
        return services.stream().sorted(Comparator.comparingDouble(Service::getInterest)).toList();
    }
}
