package generators;

import homework_mock.algos.repeat.RandomInteger;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

public class RandomData {
    private RandomData() {
    }

    public static String getUsername() {
        return RandomStringUtils.randomAlphabetic(10);
    }

    public static String getPassword() {
        return RandomStringUtils.randomAlphabetic(3).toUpperCase() +
                RandomStringUtils.randomAlphabetic(5).toLowerCase() +
                RandomStringUtils.randomNumeric(3) + "$";
    }

    public static double getAmount() {
        return RandomUtils.nextDouble(0.1, 5000.0);
    }

    public static double getAmountOverLimit() {
        return RandomUtils.nextDouble(5000.01, 10000);
    }

    public static long getNonExistentAccountId() {
        return RandomUtils.nextLong(10000L, 999999L);
    }

    public static double getTransferOverLimit() {
        return RandomUtils.nextDouble(10000.01, 12000);
    }
}
