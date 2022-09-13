package ch2;

import java.util.Map;
import java.util.TreeMap;

// Funciona bem!
public class LetterBinaryJava {
    public static void main(String[] args) {
        TreeMap<Character, String> binaryReps = new TreeMap<Character, String>();
        char l = 'A';
        char r = 'F';
        for (int i = 0; (l + i) <= r; i++) {
            String binary = Integer.toBinaryString(l + i);
            binaryReps.put((char) (l + i), binary);
        }
        for (Map.Entry<Character, String> e : binaryReps.entrySet()) {
            System.out.println(e.getKey() + " = " + e.getValue());
        }
    }
}
