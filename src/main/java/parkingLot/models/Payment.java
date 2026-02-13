package parkingLot.models;

import java.util.Date;

public class Payment extends BaseModals{
    private int amount;
    private PaymentMode paymentMode;
    private PaymentStatus paymentStatus;
    private Date time;
    private String referenceNumber;
}
