import java.util.Scanner;
import java.util.ArrayList;

final class ReturningValues{
    final UserAccount user_acc;
    final AdministrativeAccount admin_acc;

    ReturningValues(UserAccount user_acc,AdministrativeAccount admin_acc){
        this.user_acc=user_acc;
        this.admin_acc=admin_acc;
    }
}



//Running the system
public class Main {

    public static ReturningValues login(ArrayList<UserAccount> user_accounts,ArrayList<AdministrativeAccount> admin_accounts ) {
        while (true) {
            System.out.println("Enter userID: ");
            String userId = new Scanner(System.in).nextLine();

            System.out.println("Enter PIN: ");
            String pin = new Scanner(System.in).nextLine();

            for(int i=0;i<user_accounts.size();i++) {

                if (userId.equals((user_accounts.get(i)).userId) && pin.equals(user_accounts.get(i).pin_code)) {
                    System.out.println("Login successful");
                    ReturningValues acc=new ReturningValues(user_accounts.get(i),null);
                    return  acc;
                }
            }

            for(int i=0;i<admin_accounts.size();i++){
                if (userId.equals((admin_accounts.get(i)).userId) && pin.equals(admin_accounts.get(i).pin_code)) {
                    System.out.println("Login successful");
                    ReturningValues acc=new ReturningValues(null,admin_accounts.get(i));
                    return  acc;
                }
            }
            System.out.println("Wrong credentials Enter again");
            System.out.println("Press Enter to continue: ");
            new Scanner(System.in).nextLine();
        }
    }

    public static void main(String[] args) {
        UserAccount x = new UserAccount("abdul", "25896", 50000, "Abdul Majeed Faraz","Current","Active");
        UserAccount y=new UserAccount("faraz","85236",45000, "Faraz ahmed","Current","Active");

        AdministrativeAccount z=new AdministrativeAccount("khan","74125","Khan Sahab");

        //using arraylist to save multiple accounts
        ArrayList<UserAccount> user_accounts=new ArrayList<UserAccount>();
        ArrayList<AdministrativeAccount> admin_accounts=new ArrayList<AdministrativeAccount>();

        user_accounts.add(x);
        user_accounts.add(y);

        admin_accounts.add(z);




       // for continously running program
        while (true) {
            ReturningValues accounts = login(user_accounts,admin_accounts);
            if (accounts.user_acc != null) {
                inner:while (true) {
                    int option = accounts.user_acc.menu();
                    switch (option) {
                        case 1 -> {
                            accounts.user_acc.withdraw();
                        }
                        case 2 -> {
                            accounts.user_acc.cashTransfer(user_accounts);

                        }
                        case 3 -> {
                            accounts.user_acc.deposit();

                        }
                        case 4 -> {
                            accounts.user_acc.displayBalance();

                        }
                        case 5 -> {
                            break inner;
                        }
                    }
                }
            } else if (accounts.admin_acc != null) {
                second_inner: while (true) {
                    int option = accounts.admin_acc.menu();
                    switch (option) {
                        case 1 -> {
                            accounts.admin_acc.new_account(user_accounts);
                        }
                        case 2 -> {
                            accounts.admin_acc.delete_account(user_accounts);
                        }
                        case 3 -> {
                            accounts.admin_acc.update_account_information(user_accounts);
                        }
                        case 4 -> {
                            accounts.admin_acc.search_for_account(user_accounts);
                        }
                        case 5 -> {
                            accounts.admin_acc.view_report(user_accounts);
                        }
                        case 6 -> {
                            break second_inner;
                        }
                    }
                }
            }
        }
    }
}
