import java.util.Scanner;

class BankAccount {

	String name;
	String userName;
	String password;
	String accountNo;
	float balance = 1000f;
	int transactions = 0;
	String transactionHistory = "";

	public void register() {
		Scanner sc = new Scanner(System.in);
		System.out.print("\nEnter Name");
		this.name = sc.nextLine();
		System.out.print("\nEnter Username");
		this.userName = sc.nextLine();
		System.out.print("\nEnter Password ");
		this.password = sc.nextLine();
		System.out.print("\nEnter Account Number");
        this.accountNo = sc.nextLine();
		System.out.println("\nRegistered Successfully");
	}

	public boolean login() {
		boolean isLogin = false;
		Scanner sc = new Scanner(System.in);
		while ( !isLogin ) {
			System.out.print("\nEnter Your Username - ");
			String Username = sc.nextLine();
			if ( Username.equals(userName) ) {
				while ( !isLogin ) {
					System.out.print("\nEnter Your Password - ");
					String Password = sc.nextLine();
					if ( Password.equals(password) ) {
						System.out.print("\nLogin successful!!");
						isLogin = true;
					}
					else {
						System.out.println("\nIncorrect Password");
					}
				}
			}
			else {
				System.out.println("\nUsername not found");
			}
		}
		return isLogin;
	}

	public void withdraw() {

		System.out.print("\nEnter amount to withdraw - ");
		Scanner sc = new Scanner(System.in);
		float amount = sc.nextFloat();
		try {

			if ( balance >= amount ) {
				transactions++;
				balance -= amount;
				System.out.println("\nWithdraw Successfully");
				String str = amount + " Rs Withdrawed\n";
				transactionHistory = transactionHistory.concat(str);

			}
			else {
				System.out.println("\nInsufficient Balance");
			}

		}
		catch ( Exception e) {
		}
	}

	public void deposit() {

		System.out.print("\nEnter amount to deposit - ");
		Scanner sc = new Scanner(System.in);
		float amount = sc.nextFloat();

		try {
			if ( amount <= 10000f ) {
				transactions++;
				balance += amount;
				System.out.println("\nSuccessfully Deposited");
				String str = amount + " Rs deposited\n";
				transactionHistory = transactionHistory.concat(str);
			}
			else {
				System.out.println("\nSorry...Limit is 100000.00");
			}

		}
		catch ( Exception e) {
		}
	}

	public void transfer() {

		Scanner sc = new Scanner(System.in);
		System.out.print("\nEnter Recipient's Name - ");
		String recipient = sc.nextLine();
		System.out.print("\nEnter Recipient's Account number - ");
		String AccountNum = sc.nextLine();
		System.out.print("\nEnter amount to transfer - ");
		float amount = sc.nextFloat();

		try {
			if ( balance >= amount ) {
				if ( amount <= 50000f ) {
					transactions++;
					balance -= amount;
					System.out.println("\nSuccessfully Transferred to " + recipient);
					String str = amount + " Rs transferred to " + recipient + "\n";
					transactionHistory = transactionHistory.concat(str);
				}
				else {
					System.out.println("\nSorry.Out of limit.Enter amount less than 50000");
				}
			}
			else {
				System.out.println("\nInsufficient Balance");
			}
		}
		catch ( Exception e) {
		}
	}

	public void checkBalance() {
		System.out.println("\n" + balance + " Rs");
	}

	public void transHistory() {
		if ( transactions == 0 ) {
			System.out.println("\nNo Transactions");
		}
		else {
			System.out.println("\n" + transactionHistory);
		}
	}
}


public class AtmInterface {

	public static int takeIntegerInput(int limit) {
		int input = 0;
		boolean flag = false;

		while ( !flag ) {
			try {
				Scanner sc = new Scanner(System.in);
				input = sc.nextInt();
				flag = true;

				if ( flag && input > limit || input < 1 ) {
                    System.out.println("Choose the number between 1 to " + limit);
					flag = false;
				}
			}
			catch ( Exception e ) {
				System.out.println("Enter only integer value");
				flag = false;
			}
		};
		return input;
	}

	public static void main(String[] args) {

		System.out.println("\nWE WELCOME YOU TO OUR ATM SYSTEM\n");
		System.out.println("1.Register \n2.Exit \nEnter Your Choice - ");
		int choice = takeIntegerInput(2);

		if ( choice == 1 ) {
			BankAccount b = new BankAccount();
			b.register();
			while(true) {
				System.out.println("\n1.Login \n2.Exit");
				System.out.print("Enter Your Choice - ");
				int ch = takeIntegerInput(2);
				if ( ch == 1 ) {
					if (b.login()) {
						System.out.println("\n\nWELCOME BACK " + b.name + " \n");
						boolean stop = false;
						while (!stop) {
							System.out.println("\n1.Withdraw \n2.Deposit \n3.Transfer amount \n4.Check Balance \n5.Transaction History \n6.Exit");
							System.out.print("\nEnter Your Choice - ");
							int c = takeIntegerInput(6);
							switch(c) {
								case 1:
								b.withdraw();
								break;
								case 2:
								b.deposit();
								break;
								case 3:
								b.transfer();
								break;
								case 4:
								b.checkBalance();
								break;
								case 5:
								b.transHistory();
								break;
								case 6:
								boolean isFinished = true;
								break;
							}
						}
					}
				}
				else {
					System.exit(0);
				}
			}
		}
		else {
			System.exit(0);
		}

	}
}