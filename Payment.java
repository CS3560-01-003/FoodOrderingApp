/**
 * A class for the customer's payment methods.
 * Language: Java
 * @author Melody
 * Date modified: 10/23/22
 */
public class Payment {

    private String customerID;
    private String customerName;
    private String cardType;
    private int cardNum;
    private int cardExp;
    private int cardSecure;

    /**
     * Constructor to create a customer's payment object.
     * @param customerID
     * @param customerName
     * @param cardType
     * @param cardNum
     * @param cardExp
     * @param cardSecure
     */
    public Payment(String customerID, String customerName, String cardType, int cardNum, int cardExp, int cardSecure) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.cardType = cardType;
        this.cardNum = cardNum;
        this.cardExp = cardExp;
        this.cardSecure = cardSecure;
    }

    //setter methods for payment attributes
    public void setID(String ID) {

    }
    public void setName(String name) {

    }
    public void setCardType(String card) {

    }
    public void setCardNum(int num) {

    }
    public void setCardExp(int exp) {

    }
    public void setCardSecure(int security) {

    }
    
    //getter methods for payment attributes
    public String getID() {
        return customerID;
    }
    public String getName() {
        return customerName;
    }
    public String getCardType() {
        return cardType;
    }
    public int getCardNum() {
        return cardNum;
    }
    public int getCardExp() {
        return cardExp;
    }
    public int getCardSecure() {
        return cardSecure;
    }
}
