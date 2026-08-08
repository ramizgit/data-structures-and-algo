package lld.amazonlocker.driver;

import lld.amazonlocker.enums.LockerSize;
import lld.amazonlocker.enums.LockerStatus;
import lld.amazonlocker.enums.PackageSize;
import lld.amazonlocker.exception.LockerNotAvailableException;
import lld.amazonlocker.model.*;
import lld.amazonlocker.model.Package;
import lld.amazonlocker.service.LockerService;
import lld.amazonlocker.strategy.LockerAllocationStrategy;
import lld.amazonlocker.strategy.impl.SmallestFitLockerStrategy;

import java.util.List;

public class AmazonLockerApplication {

    public static void main(String[] args) {

        // Create customer
        Customer customer = new Customer(1, "Alice");

        // Create lockers
        Locker locker1 = new Locker(1, LockerSize.SMALL, LockerStatus.AVAILABLE);
        Locker locker2 = new Locker(2, LockerSize.MEDIUM, LockerStatus.AVAILABLE);
        Locker locker3 = new Locker(3, LockerSize.LARGE, LockerStatus.AVAILABLE);

        // Create locker station
        LockerStation lockerStation = new LockerStation(1, "Phoenix Mall", List.of(locker1, locker2, locker3));

        // Create allocation strategy
        LockerAllocationStrategy strategy = new SmallestFitLockerStrategy();

        // Create service
        LockerService lockerService = new LockerService(lockerStation, strategy);

        // Create package
        Package packageItem = new Package(101, customer.getCustomerId(), PackageSize.MEDIUM);

        // Store package
        PickupCode pickupCode = lockerService.storePackage(packageItem);

        System.out.println("Package stored successfully.");
        System.out.println("Pickup Code: " + pickupCode.getCode());
        System.out.println("Locker Status: " + locker2.getStatus());

        // Customer picks up package
        Package pickedUpPackage = lockerService.pickupPackage(pickupCode.getCode());

        System.out.println("Package picked up: " + pickedUpPackage.getPackageId());

        // Verify locker is available again
        System.out.println("Locker Status after pickup: " + locker2.getStatus());
    }
}
