import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

public class AirlinereservationsWithPay {

        private static final int NUMBER_OF_SEATS=11;
        private static final boolean[] seats = new boolean[NUMBER_OF_SEATS];
        private static final int FIRST=1;
        private static final int ECONOMY =2;
        private static boolean check = false;
        private static final int priceFirstClass=2035;
        private static final int priceEconomyClass=386;


    public static void main(String[] args) throws IOException {
        int choice = 0;
        int seatNumber = 0;
        double income = 0.0;
        double incomeFirstClass = 0.0;
        double incomeEconomyClass = 0.0;
        double money_given = 0;
        String fullName = " ";
        String choose = " ";
        PrintWriter outputFile = new PrintWriter("flightReport.txt");

        Scanner keyboard = new Scanner(System.in);
        ArrayList<String> passengerNames = new ArrayList<String>();

        while (choice != -1) {
            System.out.print("Airline Reservations System\n");
            System.out.println("\nPlease type 1 for First Class\nPlease type 2 for Economy or -1 to quit: \n");
            choice = keyboard.nextInt();
            if (choice == -1) {
                if (choice == -1) {
                    break;
                }
            }
            System.out.println("Insert your money (in euros): ");
            money_given = keyboard.nextDouble();

            System.out.println("\nEnter your full name: ");
            keyboard.nextLine();
            fullName = keyboard.nextLine();

            check = CheckIfFull();
            if (choice == FIRST) {

                if (!check) {
                    MoneyChange payment = new MoneyChange(priceFirstClass, money_given);
                    while (money_given < priceFirstClass) {
                        System.out.println("You inserted insufficient money into the machine. Insert at least 2035 euros: ");
                        money_given = keyboard.nextDouble();
                        payment = new MoneyChange(priceFirstClass, money_given);
                    }
                    payment.get_change(priceFirstClass, money_given);
                    payment.print_change();
                    seatNumber = assignSeat(choice, fullName);
                    incomeFirstClass += priceFirstClass;
                }
                {
                    if (seatNumber >= 5) {
                        System.out.print("No more available seats!\n");
                    }
                }
            } else if (choice == ECONOMY) {
                if (!check) {
                    MoneyChange payment = new MoneyChange(priceEconomyClass, money_given);
                    while (money_given < priceEconomyClass) {
                        System.out.println("You inserted insufficient money into the machine. Insert at least 386 euros: ");
                        money_given = keyboard.nextDouble();
                        payment = new MoneyChange(priceEconomyClass, money_given);
                    }
                    payment.get_change(priceEconomyClass, money_given);
                    payment.print_change();
                    seatNumber = assignSeat(choice, fullName);
                    incomeEconomyClass += priceEconomyClass;
                }

                {
                    if (seatNumber >= 10) {
                        System.out.print("No more available seats!\n");
                    }
                }
            } else {
                break;
            }
            passengerNames.add(fullName);

            outputFile = writeFlightReport(seatNumber, choice, fullName, outputFile);


        }
        outputFile = showPassengerNames(passengerNames, outputFile);

        closeFile(outputFile);

        income = incomeFirstClass + incomeEconomyClass;
        System.out.println("\nThe corporate earnings from the tickets are: " + income);
        System.out.println("\nFlight report written to the file \"flightReport.txt\"\n\n ");

        choose = JOptionPane.showInputDialog("Do you want to see the flight report?\nEnter yes if you want to or not if you do not want to: ");

        if (choose.equalsIgnoreCase("yes")) {

            JFileChooserOpenFile();
        }
        else{
            JOptionPane.showMessageDialog(null, "No action");
        }
        System.out.println("The program has been terminated.");
    }

    public static boolean CheckIfFull(){
            boolean isfull = false;
            for(boolean s : seats) {
                if (!s) {
                    isfull = false;
                }

                else{
                    isfull = true;
                }
            }
            return isfull;
        }

        public static int assignSeat(int choice, String name) throws IOException {
            int seatNumber =5;
            if(choice == FIRST){
                for(int i=1; i<6; i++){
                    if(!seats[i]){
                        seats[i] = true;
                        PrintBoardingPass(i, choice, name);
                        seatNumber = i;
                        break;
                    }
                }
            }
            else if(choice == ECONOMY){
                for(int i=6; i<11; i++){
                    if(!seats[i]){
                        seats[i] = true;
                        PrintBoardingPass(i, choice, name);
                        seatNumber=i;
                        break;
                    }
                }
            }

            return seatNumber;
        }

        public static void PrintBoardingPass(int seatNum, int Class, String name) throws IOException {
            String first= "FIRST";
            String economy = "ECONOMY";

            if(Class == 1){
                System.out.printf("Class: %s%nseat number: %d%npassenger name: %s%n%n%n",first, seatNum, name);

            }
            else if(Class == 2){
                System.out.printf("Class: %s%nseat number: %d%npassenger name: %s%n%n%n",economy, seatNum, name);

            }
        }

        public static PrintWriter showPassengerNames(Collection<String> names, PrintWriter outputFile){
            SortedSet<String> sortedNames = new TreeSet<String>(names);
            System.out.println("\nPassenger names in alphabetical order:");
            System.out.print(sortedNames.stream().map(i -> String.valueOf(i))
                    .collect(Collectors.joining(", ")));
            System.out.println("\n");

            outputFile.println("Passenger names in alphabetical order:");
            outputFile.println(sortedNames.stream().map(i -> String.valueOf(i))
                    .collect(Collectors.joining(", ")));
            return outputFile;
        }
        public static PrintWriter writeFlightReport(int seatNum, int Class, String name, PrintWriter outputFile)throws IOException {
            String first = "FIRST";
            String economy = "ECONOMY";

            outputFile.printf("Class: %s%nseat number: %d%npassenger name: %s%n%n", (Class == 1) ? first : economy , seatNum, name);
            return outputFile;

        }

        public static void closeFile(PrintWriter outputFile){
            outputFile.close();
        }

    public static void JFileChooserOpenFile(){

            JFileChooser fileChooser=new JFileChooser();
            int a=fileChooser.showOpenDialog(null);

            if(a== JFileChooser.APPROVE_OPTION)
            {
                File fileToOpen=fileChooser.getSelectedFile();

                try
                {
                    Desktop.getDesktop().open(fileToOpen);
                    JFileChooserDemo application = new JFileChooserDemo();
                    application.setSize(400, 400);
                    application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    application.setVisible(true);
                }
                catch(Exception exception)
                {
                    System.out.println("Problem occur trying to open the file");
                }
            }
        }
}
