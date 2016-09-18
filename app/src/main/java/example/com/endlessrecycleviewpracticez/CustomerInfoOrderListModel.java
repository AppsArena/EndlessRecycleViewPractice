package example.com.endlessrecycleviewpracticez;

/**
 * Created by mitu on 9/18/16.
 */
public class CustomerInfoOrderListModel {
    public int CouponId;
    public int CouponPrice;
    public int CouponQtn;
    public int IsDone;
    public String BookingCode;
    public int DealId;
    public String DealTitle;
    public String CouponExpiryDate;
    public String SignupClosingDate;
    public String FolderName;
    public int DealPrice;
    public String PostedOn;
    public String DeliveryDate;
    public String CompanyName;
    public String CustomerBillingAddress;
    public boolean IsSoldOut;

    public CustomerInfoOrderListModel(int couponId, int couponPrice, int couponQtn, int isDone, String bookingCode, int dealId, String dealTitle, String couponExpiryDate, String signupClosingDate, String folderName, int dealPrice, String postedOn, String deliveryDate, String companyName, String customerBillingAddress, boolean isSoldOut) {
        CouponId = couponId;
        CouponPrice = couponPrice;
        CouponQtn = couponQtn;
        IsDone = isDone;
        BookingCode = bookingCode;
        DealId = dealId;
        DealTitle = dealTitle;
        CouponExpiryDate = couponExpiryDate;
        SignupClosingDate = signupClosingDate;
        FolderName = folderName;
        DealPrice = dealPrice;
        PostedOn = postedOn;
        DeliveryDate = deliveryDate;
        CompanyName = companyName;
        CustomerBillingAddress = customerBillingAddress;
        IsSoldOut = isSoldOut;
    }

    public int getCouponId() {
        return CouponId;
    }

    public int getCouponPrice() {
        return CouponPrice;
    }

    public int getCouponQtn() {
        return CouponQtn;
    }

    public int getIsDone() {
        return IsDone;
    }

    public String getBookingCode() {
        return BookingCode;
    }

    public int getDealId() {
        return DealId;
    }

    public String getDealTitle() {
        return DealTitle;
    }

    public String getCouponExpiryDate() {
        return CouponExpiryDate;
    }

    public String getSignupClosingDate() {
        return SignupClosingDate;
    }

    public String getFolderName() {
        return FolderName;
    }

    public int getDealPrice() {
        return DealPrice;
    }

    public String getPostedOn() {
        return PostedOn;
    }

    public String getDeliveryDate() {
        return DeliveryDate;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public String getCustomerBillingAddress() {
        return CustomerBillingAddress;
    }

    public boolean isSoldOut() {
        return IsSoldOut;
    }
}


