package org.example.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PuttingIntoPractice {

    public static void main(String... args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        //1. Найти все транзакции за 2011 год и отсортировать их по сумме (от меньшей
        //к большей).
        List<Transaction> transactions2011Asc = transactions
                .stream()
                .filter(
                        o -> o.getYear() == 2011)
                .sorted(Comparator.comparingInt(Transaction::getValue))
                .toList();
        System.out.println(transactions2011Asc);

        //2. Вывести список неповторяющихся городов, в которых работают трейдеры.
        List<String> cites = transactions
                .stream().map(Transaction::getTrader)
                .map(Trader::getCity)
                .distinct()
                .toList();
        System.out.println(cites);

        //3. Найти всех трейдеров из Кембриджа и отсортировать их по именам.
        List<Trader> cambridgeTraders = transactions
                .stream()
                .map(Transaction::getTrader)
                .filter(o -> o.getCity().equals("Cambridge"))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .toList();
        System.out.println(cambridgeTraders);

        //4. Вернуть строку со всеми именами трейдеров, отсортированными в алфавитном
        //порядке.
        String traderNames = transactions
                .stream()
                .map(o -> o.getTrader().getName())
                .distinct()
                .sorted()
                .collect(Collectors.joining(" "));
        System.out.println(traderNames);

        //5. Выяснить, существует ли хоть один трейдер из Милана.
        boolean isTraderFromMilan = transactions
                .stream()
                .map(Transaction::getTrader)
                .anyMatch(o -> o.getCity().equals("Milan"));
        System.out.println(isTraderFromMilan);

        //6. Вывести суммы всех транзакций трейдеров из Кембриджа.
        int sum = transactions
                .stream()
                .filter(o -> o.getTrader().getCity().equals("Cambridge"))
                .mapToInt(Transaction::getValue)
                .sum();
        System.out.println(sum);

        //7. Какова максимальная сумма среди всех транзакций?
        int maxSum = transactions
                .stream()
                .map(Transaction::getValue)
                .max(Integer::compareTo)
                .get();
        System.out.println(maxSum);

        //8. Найти транзакцию с минимальной суммой.
        Transaction transaction = transactions
                .stream()
                .min(Comparator.comparing(Transaction::getValue))
                .get();
        System.out.println(transaction);
    }
}