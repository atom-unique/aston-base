package com.iglaz.astonbase.lesson5.homework;

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

        //1. Найти все транзакции за 2011 год и отсортировать их по сумме (от меньшей к большей).
        System.out.println("Все транзакции за 2011 год:");
        transactions
                .stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .forEach(System.out::println);
        System.out.println("------");

        //2. Вывести список неповторяющихся городов, в которых работают трейдеры.
        System.out.println("Список городов, в которых работают трейдеры:");
        transactions
                .stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .forEach(System.out::println);
        System.out.println("------");

        //3. Найти всех трейдеров из Кембриджа и отсортировать их по именам.
        System.out.println("Трейдеры из кембриджа:");
        transactions
                .stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .sorted(Comparator.comparing(Trader::getName))
                .distinct()
                .forEach(System.out::println);
        System.out.println("------");

        //4. Вернуть строку со всеми именами трейдеров, отсортированными в алфавитном порядке.
        System.out.println("Строка с именами трейдеров:");
        String tradersNamesLine = transactions
                .stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .collect(Collectors.joining());
        System.out.println(tradersNamesLine);
        System.out.println("------");

        //5. Выяснить, существует ли хоть один трейдер из Милана.
        boolean isAnyTraderFromMilan = transactions
                .stream()
                .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));
        System.out.println(isAnyTraderFromMilan ? "Есть трейдер из Милана." : "Нет трейдера из Милана.");
        System.out.println("------");

        //6. Вывести суммы всех транзакций трейдеров из Кембриджа.
        System.out.println("Сумма всех транзакций трейдеров из Кембриджа:");
        System.out.println(transactions
                .stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .mapToInt(Transaction::getValue)
                .sum());
        System.out.println("------");

        //7. Какова максимальная сумма среди всех транзакций?
        System.out.println("Транзакция с максимальной суммой:");
        System.out.println(transactions
                .stream()
                .mapToInt(Transaction::getValue)
                .max()
                .orElse(0));
        System.out.println("------");

        //8. Найти транзакцию с минимальной суммой.
        System.out.println("Транзакция с минимальной суммой:");
        System.out.println(transactions
                .stream()
                .mapToInt(Transaction::getValue)
                .min()
                .orElse(0));

    }
}
