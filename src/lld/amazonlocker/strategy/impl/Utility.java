package lld.amazonlocker.strategy.impl;

import lld.amazonlocker.enums.LockerSize;
import lld.amazonlocker.enums.PackageSize;

public class Utility {

    public static boolean canFit(LockerSize lockerSize, PackageSize packageSize)
    {
        if (packageSize == PackageSize.SMALL) {
            return true;
        }

        if (packageSize == PackageSize.MEDIUM) {
            return lockerSize == LockerSize.MEDIUM || lockerSize == LockerSize.LARGE;
        }

        return lockerSize == LockerSize.LARGE;
    }
}
