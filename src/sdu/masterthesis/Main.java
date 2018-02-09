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
}
