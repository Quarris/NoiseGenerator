package main.java.util;

public class Util {

    /**
     * Scrambles a seed in a predictable, always-equal, but seemingly random
     * way, making the output seem like a completely different number. This can
     * be used for java.java.world generation when you need a different seed that,
     * nevertheless, should still be influenced by the java.java.world's seed. To scramble
     * a seed based on some other value, see {@link #scrambleSeed(int, long)}.
     *
     * @param l The long to scramble
     *
     * @return The scrambled long
     *
     * @see #scrambleSeed(int, long)
     */
    public static long shiftScramble(long l){
        l ^= (l << 21);
        l ^= (l >> 35);
        l ^= (l << 4);
        return l;
    }

    /**
     * Scrambles an x and a y value together into a seed that can be used for
     * java.java.world gen. Note that this is not influenced by another seed. Obviously,
     * these values do not have to be actual positions in the java.java.world, but can
     * just be two arbitrary numbers.
     *
     * @param x The x value
     * @param y The y value
     *
     * @return The seed
     */
    public static long scrambleSeed(int x, int y){
        return scrambleSeed(x, y, 0L);
    }

    /**
     * Scrambles a single value into a seed that can be used for java.java.world gen. Note
     * that this is not influenced by another seed.
     *
     * @param i The value
     *
     * @return The seed
     */
    public static long scrambleSeed(int i){
        return scrambleSeed(i, 0L);
    }

    /**
     * Scrambles an x and a y value into a seed based on a different seed. This
     * can be used for java.java.world gen. Obviously, these values do not have to be
     * actual positions in the java.java.world, but can just be two arbitrary numbers.
     *
     * @param x    The x value
     * @param y    The y value
     * @param seed The original seed
     *
     * @return The new seed
     */
    public static long scrambleSeed(int x, int y, long seed){
        return shiftScramble(shiftScramble(x)+Long.rotateLeft(shiftScramble(y), 32))+seed;
    }

    /**
     * Scrambles a value into a seed based on a different seed. This can be used
     * for java.java.world gen.
     *
     * @param i    The value
     * @param seed The original seed
     *
     * @return The new seed
     */
    public static long scrambleSeed(int i, long seed){
        return shiftScramble(i)+seed;
    }

}
