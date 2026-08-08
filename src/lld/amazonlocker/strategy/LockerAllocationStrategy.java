package lld.amazonlocker.strategy;

import lld.amazonlocker.model.Locker;
import lld.amazonlocker.model.Package;

import java.util.List;

//Goal : Find me a suitable locker
public interface LockerAllocationStrategy {

    Locker selectLocker(Package packageItem, List<Locker> lockers);
}
