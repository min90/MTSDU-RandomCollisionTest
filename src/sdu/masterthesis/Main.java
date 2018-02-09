package sdu.masterthesis;

import java.security.SecureRandom;
import java.util.*;

public class Main {

    private int avgRequestsPerDay;
    private Random rand;

    public Main(int avgRequestsPerDay) {
        this.avgRequestsPerDay = avgRequestsPerDay;
        this.rand = new Random();
    }

    public static void main(String[] args) {
        System.out.println(" ---- Collision testing ---");
        System.out.println("counting random collisions");

        Main ma = new Main(300000);

//        int integerCollisions = ma.countCollisionOfRandomInt();
//        System.out.println("Number of collisions for integers: " + integerCollisions);
//
//        int longCollisions = ma.countCollisionOfRandomLong();
//        System.out.println("Number of collisions of longs: " + longCollisions);
//
//        int UUIDCollisions = ma.countCollisionOfRandomUUID();
//        System.out.println("Number of collisions of UUIDs: " + UUIDCollisions);

    }

    // Add integers to a list until a collision happens
    private void checkIntegerCollisions() {

        Random rand = new Random();
        List<Integer> rr = new ArrayList<>();
        do {
            rr.add(rand.nextInt());
        } while(countDuplicates(rr) == 0);
        System.out.println("Added " + rr.size() + " integers before collision happened");
    }

    // Add longs to a list until a collision happens NB -> (Be careful running it as the heap will overflow)
    private void checkLongCollisions() {

        Random rand = new Random();
        List<Long> rr = new ArrayList<>();
        do {
            rr.add(rand.nextLong());
        } while(countDuplicates(rr) == 0);
        System.out.println("Added " + rr.size() + " integers before collision happened");
    }

    // Add UUID to a list until a collision happens NB -> (Be careful running it as the heap will overflow).
    private void checkUUIDCollisions() {

        List<UUID> rr = new ArrayList<>();
        do {
            rr.add(UUID.randomUUID());
        } while(countDuplicates(rr) == 0);
        System.out.println("Added " + rr.size() + " integers before collision happened");
    }


    // Count how many collisions would happen with the amount of requests
    public int countCollisionOfRandomInt() {
        List<Integer> randomIntegers = new ArrayList<>();
        for (int i = 0; i < avgRequestsPerDay; i++) {
            randomIntegers.add(rand.nextInt());
        }
        return countDuplicates(randomIntegers);
    }

    // Count how many collisions would happen with the amount of requests
    public int countCollisionOfRandomLong() {
        List<Long> randomLongs = new ArrayList<>();
        for (int i = 0; i < avgRequestsPerDay; i++) {
            randomLongs.add(rand.nextLong());
        }
        return countDuplicates(randomLongs);
    }

    // Count how many collisions would happen with the amount of requests
    public int countCollisionOfRandomUUID() {
        List<UUID> randomUUIDs = new ArrayList<>();
        for (int i = 0; i < avgRequestsPerDay; i++) {
            randomUUIDs.add(UUID.randomUUID());
        }
        return countDuplicates(randomUUIDs);
    }

    // Count duplicates by converting to a HashSet.
    private int countDuplicates(List<?> listToCount) {
        HashSet<Object> tmpSet = new HashSet<>(listToCount);
        return listToCount.size()  - tmpSet.size();
    }

    // Base62 encoding algorithm
    private String encodeBase62(long n) {

        final String base62Chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

        StringBuilder builder = new StringBuilder();

        //NOTE:  Appending builds a reverse encoded string.  The most significant value
        //is at the end of the string.  You could prepend(insert) but appending
        // is slightly better performance and order doesn't matter here.

        //perform the first selection using unsigned ops to get negative
        //numbers down into positive signed range.
        long index = Long.remainderUnsigned(n, 62);
        builder.append(base62Chars.charAt((int) index));
        n = Long.divideUnsigned(n, 62);
        //now the long is unsigned, can just do regular math ops
        while (n > 0) {
            builder.append(base62Chars.charAt((int) (n % 62)));
            n /= 62;
        }
        return builder.toString();
    }
}
