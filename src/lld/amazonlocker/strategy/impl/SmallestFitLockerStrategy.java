package lld.amazonlocker.strategy.impl;

import lld.amazonlocker.enums.LockerSize;
import lld.amazonlocker.model.Locker;
import lld.amazonlocker.model.Package;
import lld.amazonlocker.strategy.LockerAllocationStrategy;

import java.util.ArrayList;
import java.util.List;

public class SmallestFitLockerStrategy implements LockerAllocationStrategy {

    //Choose the smallest available locker that can fit the package.
    @Override
    public Locker selectLocker(Package packageItem, List<Locker> lockers)
    {
        /*
        Sort by locker size
        ↓
        Small → Medium → Large
                ↓
        Find first available locker
                ↓
        Check if it can fit package
                ↓
        Return it
         */

        // Sort a copy so the original locker list is not modified.
        List<Locker> sortedLockers = new ArrayList<>(lockers);

        sortedLockers.sort((a, b) -> Integer.compare(sizeRank(a.getSize()), sizeRank(b.getSize())));

        // First suitable locker is the smallest fit.
        for (Locker locker : sortedLockers) {

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

    private int sizeRank(LockerSize size)
    {
        if (size == LockerSize.SMALL) {
            return 1;
        }

        if (size == LockerSize.MEDIUM) {
            return 2;
        }

        return 3;
    }
}
