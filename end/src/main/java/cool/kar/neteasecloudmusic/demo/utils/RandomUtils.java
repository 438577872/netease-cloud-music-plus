package cool.kar.neteasecloudmusic.demo.utils;

import java.util.Random;

public class RandomUtils {
    public String generalRandom() {
        Random random = new Random();
        double v = random.nextDouble() * 100000000;
        int i = (int) v;
        return String.valueOf(i);
    }
}
