import javax.xml.transform.Source;
import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.text.DateFormat;
import  java.text.SimpleDateFormat;

//bluprint for administrative account
class AdministrativeAccount extends Account{

    AdministrativeAccount(String userId,String pin_code,String name){
        super(userId,pin_code,name);
    }

    public int menu(){
        while (true) {
            //options available for customers
            System.out.println("""
                    1----Create new Account\s
                    2----Delete Existing Account\s
                    3----Update Account Information\s
                    4----Search for Account\s
                    5----View Reports\s
                    6----Exit""");
            System.out.println("Enter the option: ");
            int choice = new Scanner(System.in).nextInt();

            //error check for choosing correct option
            if (choice <= 6 && choice >= 1) {
                return choice;
            } else {
                System.out.println("Invalid option");
                System.out.println("Press Enter to continoue");
                new Scanner(System.in).nextLine();
            }
        }
    }

    //checking if String is numeric or not
    private static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }

    //Implementation for creating accounts
    public void new_account(ArrayList<UserAccount> user_accounts){
        System.out.print("Login: ");
        String userID=new Scanner(System.in).nextLine();

        //making sure pin is always numeric
        while(true) {
            System.out.println("Pin Code: ");
            String pin_code = new Scanner(System.in).nextLine();
            if(isNumeric(pin_code)){
                break;
            }

            System.out.println("Pin ccode can only be numeric Try again");
            System.out.println("Press Enter to continoue");
            new Scanner(System.in).nextLine();
        }

        System.out.println("Holder Name: ");
        String name=new Scanner(System.in).nextLine();

        String type;

        while (true){
            System.out.println("Type (Saving or Current): ");
            type=new Scanner(System.in).nextLine();
            if(type.equals("Current") || type.equals("Saving")){
                break;
            }
            //error checking for current and saving
            System.out.println("Please Enter either Current or Saving");
            System.out.println("Press enter to continoue");
            new Scanner(System.in).nextLine();
        }

        System.out.println("Starting Balance: ");
        long balance=new Scanner(System.in).nextLong();

        System.out.println("Status: ");
        String status=new Scanner(System.in).nextLine();

        UserAccount account=new UserAccount(userID,pin_code,balance,name,type,status);
        System.out.println("Account succesfully created-the account number assigned is "+account.accunt_number);
        user_accounts.add(account);


    }

    public void delete_account(ArrayList<UserAccount> user_accounts){
        int account_no;
        UserAccount to_delet_account=null;
        int index_of_to_delete_account=0;

        outer:while (true){
            System.out.println("Enter the account number which you want to delete: ");
            account_no=new Scanner(System.in).nextInt();

            for(int i=0;i<user_accounts.size();i++){
                if(user_accounts.get(i).accunt_number==account_no){
                    to_delet_account=user_accounts.get(i);
                    index_of_to_delete_account=i;
                    break;
                }

                else if (i==user_accounts.size()-1) {
                    System.out.println("Sorry couldn't find the Account please retry");
                    System.out.println("Press Enter to continoue");
                    new Scanner(System.in).nextLine();
                    continue outer;
                }
            }

            //double checking the account
            System.out.println("You wish to delete the account held by "+to_delet_account.name+"; If" +
                    " this information is correct please re-enter the account number: ");
            account_no=new Scanner(System.in).nextInt();

            if(account_no==to_delet_account.accunt_number){
                user_accounts.remove(index_of_to_delete_account);
                System.out.println("Account Deleted Successfully");
                return;
            }
            else {
                System.out.println("Sorry your account numbers don't match Please Try again");
                System.out.println("Press Enter to continoue");
                new Scanner(System.in).nextLine();
            }
        }
    }

    //implementation for updating account_information
    public void update_account_information(ArrayList<UserAccount> user_accounts){
        int accont_no;
        UserAccount user_account;

        outer: while(true){
            System.out.print("Enter the account number: ");
            accont_no=new Scanner(System.in).nextInt();

            //iterating for finding account
            for (int i=0;i<user_accounts.size();i++){
                if(accont_no==user_accounts.get(i).accunt_number){
                    user_account=user_accounts.get(i);

                    //showing old info
                    System.out.println("Account # "+user_account.accunt_number);
                    System.out.println("Type: "+user_account.type);
                    System.out.println("Holder: "+user_account.name);
                    System.out.println("Balance: "+user_account.balance);
                    System.out.println("Status: "+user_account.status);

                    break outer;
                }
            }
            //error checking
            System.out.println("Sorry cant find the Account Try again");
            System.out.println("Press Enter to continoue ");
            new Scanner(System.in).nextLine();
        }

        //new fields
        String new_userId;
        String new_pin_code;
        String new_holder_name;
        String new_status;

        System.out.println("Please Enter in the fields you wish to update (leave blank otherwise): ");

        //inputing the new fields
        System.out.print("Login: ");
        new_userId=new Scanner(System.in).nextLine();

        //checking to make sure the pincode is numeric
        while (true) {
            System.out.print("Pin Code: ");
            new_pin_code = new Scanner(System.in).nextLine();
            if(isNumeric(pin_code)){
                break;
            }

            System.out.println("Pin code can only be numeric try again ");
            System.out.println("Press enter to continoue");
            new Scanner(System.in).nextLine();
        }

        System.out.print("Holder Name: ");
        new_holder_name=new Scanner(System.in).nextLine();

        while (true) {
            System.out.print("Status: ");
            new_status=new Scanner(System.in).nextLine();

            if (new_status.equals("Active") || new_status.equals("Blocked") || new_status.isEmpty()) {
                break;
            }

            System.out.println("Please Enter the status as Active or Blocked");
            System.out.println("Press Enter to continoue");
            new Scanner(System.in).nextLine();
        }

        //updating information
        if(!new_userId.isEmpty()){
            user_account.userId=new_userId;
        }
        if(!new_pin_code.isEmpty()){
            user_account.pin_code=new_pin_code;
        }
        if(!new_holder_name.isEmpty()){
            user_account.name=new_holder_name;
        }
        if(!new_status.isEmpty()){
            user_account.status=new_status;
        }

    }

    //implementation for searching the account
    public void search_for_account(ArrayList<UserAccount> user_accounts){

        //arraylist for storing accounts which satisfy the search
        ArrayList<UserAccount> sorted_accounts=new ArrayList<UserAccount>(user_accounts);

        //fields
        String account_number;
        String user_id;
        String holder_name;
        String type;
        String balance;
        String status;

        System.out.println("SEARCH MENU");

        //making sure user input correct account id
        while(true) {
            System.out.print("Account ID: ");
            account_number = new Scanner(System.in).nextLine();

            if(!isNumeric(account_number) || account_number.isEmpty()){
                break;
            }
            System.out.println("Enter valid account number Try again");
            System.out.println("Press Enter to continoue");
            new Scanner(System.in).nextLine();
        }

        System.out.print("User ID: ");
        user_id=new Scanner(System.in).nextLine();

        System.out.print("Holder Name:");
        holder_name=new Scanner(System.in).nextLine();

        //making sure user input correct account type
        while (true){
            System.out.print("Type (Saving or Current): ");
            type=new Scanner(System.in).nextLine();

            if(type.equals("Saving") || type.equals("Current") || type.isEmpty()){
                break;
            }
            System.out.println("Enter either Current or Saving Try again");
            System.out.println("Press Enter to continoue");
            new Scanner(System.in).nextLine();
        }

        //making sure user input correct amount
        while (true){
            System.out.print("Balance: ");
            balance=new Scanner(System.in).nextLine();
            if(isNumeric(balance) || balance.isEmpty()){
                break;
            }
            System.out.println("Enter valid balance Try again");
            System.out.println("Press Enter to continoue");
            new Scanner(System.in).nextLine();
        }

        //making sure user input correct status
        while (true){
            System.out.print("Status: ");
            status=new Scanner(System.in).nextLine();
            if(status.equals("Active") || status.equals("Blocked") || status.isEmpty()){
                break;
            }
            System.out.println("Enter valid status Try again");
            System.out.println("Press Enter to continoue");
            new Scanner(System.in).nextLine();
        }

        //sorting now

        for(int i=0;i<sorted_accounts.size();i++){

            //checking account number
            if(!account_number.isEmpty() && sorted_accounts.get(i).accunt_number!=Integer.parseInt(account_number)){
                sorted_accounts.remove(i);
            }
            //checking user id
            if(!user_id.isEmpty() && !(sorted_accounts.get(i).userId.equals(user_id))){
                sorted_accounts.remove(i);
            }

            //checking holder name
            if(!holder_name.isEmpty() && !(sorted_accounts.get(i).name.equals(holder_name))){
                sorted_accounts.remove(i);
            }

            //checking type
            if(!type.isEmpty() && !(sorted_accounts.get(i).type.equals(type))){
                sorted_accounts.remove(i);
            }

            //checking balance
            if(!balance.isEmpty() && sorted_accounts.get(i).balance!=Integer.parseInt(balance)){
                sorted_accounts.remove(i);
            }

            //checking status
            if(!status.isEmpty() && !(sorted_accounts.get(i).status.equals(status))){
                sorted_accounts.remove(i);
            }
        }

        System.out.println("==== SEARCH RESULTS ====");
        for(int i=0;i<sorted_accounts.size();i++){
            UserAccount sorted_account=sorted_accounts.get(i);
            System.out.println(sorted_account.accunt_number+"\t "+sorted_account.userId+"\t "+sorted_account.name);
        }
    }

    private ArrayList<UserAccount> by_amount(long starting, long ending, ArrayList<UserAccount> users_accounts){
            ArrayList<UserAccount> sorted_accounts=new ArrayList<UserAccount>();
            for (int i=0;i<users_accounts.size();i++){
                UserAccount indexed_account=users_accounts.get(i);

                if(indexed_account.balance>=starting && indexed_account.balance<=ending){
                    sorted_accounts.add(indexed_account);
                }
            }
            return sorted_accounts;
    }

    private ArrayList<Transactions> by_date(){
        ArrayList<Transactions> sorted_transaction=new ArrayList<Transactions>();
        Date start_date;
        Date end_date;

        while (true) {
            System.out.print("Enter the startin date dd/MM/yyyy");
            String str_start_date = new Scanner(System.in).nextLine();
            System.out.print("Enter the ending date dd/MM/yyyy");
            String str_end_date = new Scanner(System.in).nextLine();

            DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");

            try {
                start_date = sourceFormat.parse(str_start_date);
                end_date = sourceFormat.parse(str_end_date);
            }
            catch (Exception e){
                System.out.println("Invalid Date format Try again");
                System.out.println("Press Enter to continoue");
                new Scanner(System.in).nextLine();
                continue;
            }
            break;
        }

        for(int i=0;i<UserAccount.total_transactions.size();i++){
            if(UserAccount.total_transactions.get(i).date.compareTo(start_date)>=0 && UserAccount.total_transactions.get(i).date.compareTo(end_date)<=0){
                sorted_transaction.add(UserAccount.total_transactions.get(i));
            }
        }
        return sorted_transaction;
    }

    public void view_report(ArrayList<UserAccount> user_accounts){
        ArrayList<UserAccount> sorted_accounts;
        ArrayList<Transactions> sorted_transactions;

        while(true) {
            System.out.println("1---- Accounts By Amount");
            System.out.println("2---- Accounts By Date");

            int choice = new Scanner(System.in).nextInt();

            if(choice==1){
                System.out.print("Enter minimum amount: ");
                long min=new Scanner(System.in).nextLong();
                System.out.println("Enter maximum amount");
                long max=new Scanner(System.in).nextLong();

                sorted_accounts=by_amount(min,max,user_accounts);
                System.out.println("==== SEARCH RESULTS ====");
                System.out.println("Account ID\t User ID\t Holder Name\t Type \t Balance\t Status\t");

                for(int i=0;i<sorted_accounts.size();i++){
                    UserAccount account=sorted_accounts.get(i);
                    System.out.println(account.accunt_number+"\t"+account.userId+"\t"+account.name+"\t"+account.type+"\t"+account.balance+"\t"+account.status);
                }
                break;
            }

            else if(choice==2){
                sorted_transactions=by_date();
                System.out.println("==== SEARCH RESULTS ====");
                System.out.println("Transaction Type \t User ID \t Holder name \t Amount \t Date \t");
                for(int i=0;i<sorted_transactions.size();i++){
                    System.out.print(sorted_transactions.get(i).transaction_type);
                    System.out.print(sorted_transactions.get(i).userid);
                    System.out.print(sorted_transactions.get(i).holder_name);
                    System.out.print(sorted_transactions.get(i).amount);
                    System.out.print(sorted_transactions.get(i).date.toString());
                    System.out.println();

                }
                break;

            }
            System.out.println("Invalid option Try again");
            System.out.println("Press Enter to continoue");
            new Scanner(System.in).nextLine();
        }


    }

}