package lld.amazonlocker.service;

/*
Package arrives
↓
Select Locker using strategy
↓
Generate PickupCode
↓
Store package + PickupCode
↓
Return PickupCode
 */

import lld.amazonlocker.exception.LockerNotAvailableException;
import lld.amazonlocker.model.Locker;
import lld.amazonlocker.model.LockerStation;
import lld.amazonlocker.model.Package;
import lld.amazonlocker.model.PickupCode;
import lld.amazonlocker.strategy.LockerAllocationStrategy;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class LockerService {

    private final LockerStation lockerStation;
    private final LockerAllocationStrategy strategy;

    private final Random random = new Random();

    private final Map<String, Locker> pickupCodeLockerMap = new HashMap<>();

    public LockerService(LockerStation lockerStation, LockerAllocationStrategy strategy) {
        this.lockerStation = lockerStation;
        this.strategy = strategy;
    }

    public PickupCode storePackage(Package packageItem) {
        //find locker
        Locker locker = strategy.selectLocker(packageItem, lockerStation.getLockers());

        if (locker == null) {
            throw new LockerNotAvailableException();
        }

        //generate pick up code
        String code = String.valueOf(100000 + random.nextInt(900000));
        PickupCode pickupCode = new PickupCode(code, packageItem.getPackageId());

        //store package
        locker.storePackage(packageItem, pickupCode);

        // Add lookup mapping
        this.pickupCodeLockerMap.put(code, locker);

        return pickupCode;
    }

    public Package pickupPackage(String code) {

        // Find locker using pickup code
        Locker locker = pickupCodeLockerMap.get(code);

        if (locker == null) {
            throw new IllegalArgumentException("Invalid pickup code");
        }

        // Release package and make locker available
        Package packageItem = locker.releasePackage();

        // Remove pickup code mapping
        pickupCodeLockerMap.remove(code);

        return packageItem;
    }
}
