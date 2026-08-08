package lld.amazonlocker.model;

import lld.amazonlocker.enums.PackageSize;

public class Package {

    private final int packageId;
    private final int customerId;
    private final PackageSize size;

    public Package(int packageId, int customerId, PackageSize size) {
        this.packageId = packageId;
        this.customerId = customerId;
        this.size = size;
    }

    public int getPackageId() {
        return packageId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public PackageSize getSize() {
        return size;
    }
}
