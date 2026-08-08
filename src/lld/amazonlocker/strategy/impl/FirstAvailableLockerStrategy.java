package lld.amazonlocker.strategy.impl;

import lld.amazonlocker.model.Locker;
import lld.amazonlocker.model.Package;
import lld.amazonlocker.strategy.LockerAllocationStrategy;

import java.util.List;

public class FirstAvailableLockerStrategy implements LockerAllocationStrategy {
    @Override
    public Locker selectLocker(Package packageItem, List<Locker> lockers)
    {
        for (Locker locker : lockers) {

            //check availability
            if (!locker.isAvailable()) {
                continue;
            }

            //can fit?
            if (Utility.canFit(locker.getSize(), packageItem.getSize())) {
                return locker;
            }
        }

        return null;
    }
}
