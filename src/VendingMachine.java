public class VendingMachine {
    private static class Coins {
        public int quarters = 0;
        public int dimes = 0;
        public int nickles = 0;
    }

    private int value = 0;
    private boolean change = false;
    private final Coins coins;

    public VendingMachine() {
        coins = new Coins();

        coins.quarters = 10;
        coins.dimes = 10;
        coins.nickles = 10;
    }

    public void lambda() throws NoChangeException {
        for (int i = 0; i < value / 100; i++) {
            System.out.print("coffee ");
        }

        if (change) {
            Coins changeCoins = calculateChange();

            for (int i = 0; i < changeCoins.quarters; i++) {
                System.out.print("quarter ");
            }

            for (int i = 0; i < changeCoins.dimes; i++) {
                System.out.print("dime ");
            }

            for (int i = 0; i < changeCoins.nickles; i++) {
                System.out.print("nickle ");
            }
        }

        if (value < 100 && !change) {
            System.out.print("nothing ");
        }

        System.out.println();
    }

    public void delta(String input) throws NoChangeException {
        value %= 100;

        if (change) {
            Coins changeCoins = calculateChange();

            coins.quarters -= changeCoins.quarters;
            coins.dimes -= changeCoins.dimes;
            coins.nickles -= changeCoins.nickles;

            value = 0;
            change = false;
        }

        for (char c : input.toCharArray()) {
            switch (c) {
                case 'q' -> {
                    coins.quarters++;
                    value += 25;
                }

                case 'd' -> {
                    coins.dimes++;
                    value += 10;
                }

                case 'n' -> {
                    coins.nickles++;
                    value += 5;
                }

                case 'c' -> change = true;
            }
        }
    }

    private Coins calculateChange() throws NoChangeException {
        int amountDue = value % 100;
        Coins changeCoins = new Coins();

        while (amountDue >= 25 && changeCoins.quarters < coins.quarters) {
            changeCoins.quarters++;
            amountDue -= 25;
        }

        while (amountDue >= 10 && changeCoins.dimes < coins.dimes) {
            changeCoins.dimes++;
            amountDue -= 10;
        }

        while (amountDue >= 5 && changeCoins.nickles < coins.nickles) {
            changeCoins.nickles++;
            amountDue -= 5;
        }

        if (amountDue > 0) {
            throw new NoChangeException();
        }

        return changeCoins;
    }
}
