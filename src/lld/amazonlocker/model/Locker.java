package lld.amazonlocker.model;

import lld.amazonlocker.enums.LockerSize;
import lld.amazonlocker.enums.LockerStatus;

public class Locker {

    private int lockerId;
    private LockerSize size;
    private LockerStatus status;
    private Package packageItem;
    private PickupCode pickupCode;

    public Locker(int lockerId, LockerSize size, LockerStatus status) {
        this.lockerId = lockerId;
        this.size = size;
        this.status = status;
    }

    public boolean isAvailable() {
        return this.status == LockerStatus.AVAILABLE;
    }

    public void storePackage(Package packageItem, PickupCode pickupCode) {

        if (!isAvailable()) {
            throw new IllegalStateException("Locker is occupied");
        }

        this.packageItem = packageItem;
        this.pickupCode = pickupCode;
        this.status = LockerStatus.OCCUPIED;
    }

    public Package releasePackage() {

        if (isAvailable()) {
            throw new IllegalStateException("Locker is already empty");
        }

        Package packageItem = this.packageItem;

        this.packageItem = null;
        this.pickupCode = null;
        this.status = LockerStatus.AVAILABLE;

        return packageItem;
    }

    public boolean matchesPickupCode(String code) {
        return pickupCode != null && pickupCode.getCode().equals(code);
    }

    public int getLockerId() {
        return lockerId;
    }

    public void setLockerId(int lockerId) {
        this.lockerId = lockerId;
    }

    public LockerSize getSize() {
        return size;
    }

    public void setSize(LockerSize size) {
        this.size = size;
    }

    public LockerStatus getStatus() {
        return status;
    }

    public void setStatus(LockerStatus status) {
        this.status = status;
    }

    public Package getPackageItem() {
        return packageItem;
    }

    public void setPackageItem(Package packageItem) {
        this.packageItem = packageItem;
    }
}
