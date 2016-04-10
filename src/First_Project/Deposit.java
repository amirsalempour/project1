package First_Project;

import First_Project.exception.ExceptionNegativeBalance;
import First_Project.exception.ExceptionNotValidClass;

import java.io.File;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;


/**
 * Created by Dotin school 6 on 4/6/2016.
 */
public class Deposit   implements Comparable<Deposit> {


    private BigDecimal payedInterest;
    private BigDecimal depositBalance;
    private int durationInDays;
    private String customerNumber;
    private DepositType depositType;

    public Deposit(String depositType  , BigDecimal depositBalance, int durationInDays, String customerNumber)
            throws ClassNotFoundException, IllegalAccessException, InstantiationException, ExceptionNotValidClass, ExceptionNegativeBalance {

        if (depositBalance.compareTo(new BigDecimal(0))< 0) {
            throw new ExceptionNegativeBalance();
        }

        if (durationInDays <= 0) {
            throw new ExceptionNegativeBalance();

        }

        this.depositBalance = depositBalance;
        this.durationInDays = durationInDays;
        this.customerNumber = customerNumber;


        System.out.println(depositType);
        Class depositTypeClass= Class.forName("First_Project." + depositType);
        this.depositType = (DepositType) depositTypeClass.newInstance();
        calculatePayedInterest();

    }


    BigDecimal calculatePayedInterest() {
        System.out.println(depositType.getInterestRate());
        payedInterest = (depositBalance.multiply(new BigDecimal(depositType.getInterestRate())).multiply(new BigDecimal(durationInDays))).divide(new BigDecimal(36500), 5);

        return payedInterest;
    }



    public BigDecimal getPayedInterest() {
        return payedInterest;

    }

    public void setPayedInterest(BigDecimal payedInterest) {
        this.payedInterest = payedInterest;
    }




    public void setDepositBalance(BigDecimal depositBalance) {
        this.depositBalance = depositBalance;
    }

    public void setDurationInDays(int durationInDays) {
        this.durationInDays = durationInDays;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }


    public static void main(String[] args) throws Exception {
        List<Deposit> deposits =  XmlParser.readXML();
        Collections.sort(deposits, Collections.reverseOrder());

        File file=new File("depositResult.txt");
        file.createNewFile();
        FileWriter writer=new FileWriter(file);
        for(Deposit deposit : deposits){

            writer.write(deposit.customerNumber+"# \t");
            writer.write( deposit.payedInterest.toString() + "\n");

//              System.out.println(deposit.customerNumber+"#"+deposit.calculatePayedInterest());
        }
        writer.flush();
        writer.close();
    }



    @Override
    public int compareTo(Deposit deposit) {

        return  this.calculatePayedInterest().compareTo(deposit.calculatePayedInterest());

    }

    public DepositType getDepositType() {
        return depositType;
    }

    public BigDecimal getDepositBalance() {
        return depositBalance;
    }

    public int getDurationInDays() {
        return durationInDays;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }
}