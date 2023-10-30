import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws NoChangeException {
        Scanner scanner = new Scanner(System.in);
        VendingMachine vendingMachine = new VendingMachine();

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();

            vendingMachine.lambda();
            vendingMachine.delta(input);
        }
    }
}
