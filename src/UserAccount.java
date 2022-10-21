import java.util.ArrayList;
import java.util.Scanner;


class UserAccount extends Account {
    //fields
    long balance;
    //    final int accunt_number;
    int withDrawn = 0;
    static int limit = 20000;

    String type;
    String status;

    //constructor
    UserAccount(String userId, String pin_code, long balance, String name,String type,String status) {
        super(userId,pin_code,name);
        this.balance = balance;
        this.type=type;
        this.status=status;
//        this.accunt_number=Account.nth_acc;
//        Account.nth_acc=Account.nth_acc+1;

    }

    //Menu for Customers acc
    public int menu() {

        while (true) {
            //options available for customers
            System.out.println("""
                    1----Withdraw Cash\s
                    2----Cash Transfer\s
                    3----Deposit Cash\s
                    4----Display Balance\s
                    5----Exit""");
            System.out.println("Enter the option: ");
            int choice = new Scanner(System.in).nextInt();

            //error check for choosing correct option
            if (choice <= 5 && choice >= 1) {
                return choice;
            } else {
                System.out.println("Invalid option");
                System.out.println("Press Enter to continoue");
                new Scanner(System.in).nextLine();
            }
        }
    }

    //Fast Cash method implementation
    private int fastCash() {
        int[] money = {0,500, 1000, 2000, 5000, 10000, 15000, 20000};
        while (true) {
            //menu for fast cash method
            System.out.println("""
                    1----500\s
                    2----1000\s
                    3----2000\s
                    4----5000\s
                    5----10000\s
                    6----15000\s
                    7----20000\s
                    Select one of the denominations of money"""
            );

            int option = new Scanner(System.in).nextInt();
            //error check for fastcash method
            if (option >= 1 && option <= 7) {
                return money[option];
            } else {
                System.out.println("Invalid Option enter again");
                System.out.println("Press Enter to continoue");
                new Scanner(System.in).nextLine();
            }
        }
    }

    //implementation for normal withdraw
    private int normalWithdraw() {
        while (true) {
            System.out.println("Enter the withdrawal amount: ");
            int amount = new Scanner(System.in).nextInt();

            //errorcheck for valid amount
            if (amount < 0) {
                System.out.println("Enter valid amount");
                System.out.println("Press Enter to continoue");
                new Scanner(System.in).nextInt();
                continue;
            }
            return amount;
        }
    }

    //For printing Receipt
    private void receipt(long amount,String action){
        while(true){
            System.out.println("Do you want to print Receipt (Y/N)?");
            String option=new Scanner(System.in).nextLine();

            if(option.equals("Y")){
                System.out.println("Account #"+this.accunt_number);
                System.out.println(action+": "+amount);
                System.out.println("Balance: "+this.balance);
                return;
            }

            else if (option.equals("N")) {
                return;
            }

            //errorcheck
            else{
                System.out.println("Invalid Option enter again");
                System.out.println("Press Enter to continoue");
                new Scanner(System.in).nextLine();
            }
        }
    }


    //implementation for withdrawn
    public void withdraw() {
        while(true) {
            //option for cash withdraw
            System.out.println("""
                    a) Fast Cash\s
                    b) Normal Cash\s
                                        
                    Please Select a method of Withdrawal"""
            );
            String option = new Scanner(System.in).nextLine();

            //if customer chose fastcash
            if (option.equals("a")) {
                int amount = this.fastCash();

                if (this.balance >= amount && this.withDrawn + amount <= limit) {
                    this.balance = this.balance - amount;
                    this.withDrawn = this.withDrawn + amount;

                    System.out.println("Successfully Withdrawn");
                    this.receipt(amount,"Withdrawn");
                    return;
                }
                System.out.println("Unfortunately either you have less balance or have reached your limit");
                return;
            }

            //if customer choses normal cash
            else if (option.equals("b")) {
                int amount = this.normalWithdraw();

                if (this.balance >= amount && this.withDrawn + amount <= limit) {
                    this.balance = this.balance - amount;
                    this.withDrawn = this.withDrawn + amount;

                    System.out.println("Successfully Withdrawn");
                    this.receipt(amount,"Withdrawn");
                    return;
                }
                System.out.println("Unfortunately either you have less balance or have reached your limit");
                return;
            }

            //errorcheck for invalid option
            else {
                System.out.println("Invalid option Enter again");
                System.out.println("Press Enter to continoue ");
                new Scanner(System.in).nextLine();
            }
        }
    }


    //for depositing balance in the account
    public void deposit(){
        while(true) {
            System.out.println("Enter the amount you want to deposti ");
            long amount = new Scanner(System.in).nextLong();

            if(amount>0){
                this.balance=this.balance+amount;
                System.out.println("Cash Deposited Successfully");
                this.receipt(amount,"Depositied");
                return;
            }

            //errorcheck for negative values
            else{
                System.out.println("Invalid amount Please Enter again");
                System.out.println("Press Enter to continoue");
                new Scanner(System.in).nextLine();
            }
        }
    }

    //Implementation for Transfering the cash
    public void cashTransfer(ArrayList<UserAccount> accounts){
        long amount;
        //making sure user enters valid amount
        while(true) {
            System.out.println("Enter the amount in multiples of 500");
            amount=new Scanner(System.in).nextInt();
            if(amount%500==0) {
                break;
            }

            //errorcheck in case of invalid amount
            System.out.println("Invalid amount Please enter the amount in Multiple of 5000");
            System.out.println("Press Enter to continoue");
            new Scanner(System.in).nextLine();
        }

        //Searching for account;
        int acc_no;
        UserAccount object_acc=null;

        outer: while(true) {
            System.out.println("Enter the account number to which you want to transfer:");
            acc_no = new Scanner(System.in).nextInt();


            for (int i = 0; i < accounts.size(); i++) {
                if (acc_no == accounts.get(i).accunt_number) {
                    object_acc = accounts.get(i);
                    break;
                }
                else if(i==accounts.size()-1){
                    System.out.println("Sorry couldn't find the Account please retry");
                    System.out.println("Press Enter to continoue");
                    new Scanner(System.in).nextLine();
                    continue outer;
                }
            }

            //double checking the acc
            System.out.println("You wish to deposit Rs " + amount + """
                    in\s the account held by  """ + object_acc.name+ """
                    ; If this\sinformation is correct please re-\senter the account number:""");
            acc_no=new Scanner(System.in).nextInt();

            //if correct tranfering balance
            if(acc_no==object_acc.accunt_number){
                this.balance=this.balance-amount;
                object_acc.balance= object_acc.balance+amount;
                this.receipt(amount,"Amount Transfered");
                return;
            }
            else {
                System.out.println("Sorry your account numbers don't match Please Try again");
                System.out.println("Press Enter to continoue");
                new Scanner(System.in).nextLine();
            }

        }
    }

    public void displayBalance(){
        System.out.println("Account #"+this.accunt_number);
        System.out.println("Balance: "+this.balance);
    }

}