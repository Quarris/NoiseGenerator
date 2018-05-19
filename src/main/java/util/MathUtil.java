package main.java.util;

public class MathUtil {

    /**
     * @param number The number to be rounded down.
     * @param target The target multiple to be rounded down to. Must be positive.
     * @return The rounded number as a multiple of the target.
     */
    public static int roundDownToNearest(int number, int target) {
        int roundMultiplier = number/target;
        int roundNumber = number%target;
        return 0;
    }
}
