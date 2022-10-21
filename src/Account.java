//base class for both types of Account
abstract class Account {
    String userId;
    String pin_code;
    String name;
    final int accunt_number;


    static int nth_acc=1;

    Account(String userId,String pin_code,String name){
        this.userId=userId;
        this.pin_code=pin_code;
        this.name=name;
        this.accunt_number=Account.nth_acc;
        Account.nth_acc=Account.nth_acc+1;
    }
    abstract public int menu();

}