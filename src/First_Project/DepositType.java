package First_Project;

/**
 * Created by Dotin school 6 on 4/10/2016.
 */
public abstract class DepositType {
    private int interestRate;
    public abstract int getInterestRate();
    public void setInterestRate(int interestRate) {
        this.interestRate = interestRate;
    }

}
