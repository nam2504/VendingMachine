package itv.momo;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Coin {
    private static final Set<Integer> coins = Stream.of(10000, 20000, 50000, 100000, 200000).collect(Collectors.toSet());

    public static Boolean isValid(Integer coin) {
        return coins.contains(coin);
    }

    public static final String asString = coins.stream().map(String::valueOf).collect(Collectors.joining(", "));
}