import java.util.Date;

public class Customer implements Runnable {

    int customerId;
    Date inTime;

    Bshop shop;

    public Customer(Bshop shop) {

        this.shop = shop;
    }

    public int getCustomerId() {                                        //getter and setter methods
        return customerId;
    }

    public Date getInTime() {
        return inTime;
    }

    public void setcustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    public void run() {                                                    //customer thread goes to the shop for the haircut

        goForHairCut();
    }

    private synchronized void goForHairCut() {                            //customer is added to the list

        shop.add(this);
    }
}
