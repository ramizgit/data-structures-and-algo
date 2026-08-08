package lld.amazonlocker.model;

public class PickupCode {

    private String code;
    private int packageId;

    public PickupCode(String code, int packageId) {
        this.code = code;
        this.packageId = packageId;
    }

    public String getCode() {
        return code;
    }

    public int getPackageId() {
        return packageId;
    }
}
