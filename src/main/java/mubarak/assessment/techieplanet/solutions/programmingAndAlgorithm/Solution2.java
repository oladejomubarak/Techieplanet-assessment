package mubarak.assessment.techieplanet.solutions.programmingAndAlgorithm;

import mubarak.assessment.techieplanet.solutions.utils.Node;

public class Solution2 {
    public static void removeDuplicates(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            int[] subArray = array[i];
            int numBuckets = getNextPrime(subArray.length * 2);
            Node[] buckets = new Node[numBuckets];

            for (int j = 0; j < subArray.length; j++) {
                int value = subArray[j];
                int hash = ((value % numBuckets) + numBuckets) % numBuckets;

                boolean found = false;
                Node current = buckets[hash];
                while (current != null) {
                    if (Node.value == value) {
                        found = true;
                        break;
                    }
                    current = current.next;
                }

                if (found) {
                    subArray[j] = 0;
                } else {
                    Node newNode = new Node(value);
                    newNode.next = buckets[hash];
                    buckets[hash] = newNode;
                }
            }
        }
    }
    private static int getNextPrime(int n) {
        while (!isPrime(n)) {
            n++;
        }
        return n;
    }
    private static boolean isPrime(int n) {
        if (n <= 1) return false;
        if (n <= 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;
        int sqrtN = (int) Math.sqrt(n) + 1;
        for (int i = 5; i <= sqrtN; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) return false;
        }
        return true;
    }
}
