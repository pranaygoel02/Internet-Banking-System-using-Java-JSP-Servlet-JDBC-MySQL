package Model;

import java.security.SecureRandom;
import java.util.Random;

public class GenerateID {
    static final private String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    final static private Random rng = new SecureRandom();

    static char randomChar(){
        return ALPHABET.charAt(rng.nextInt(ALPHABET.length()));
    }

    public static String randomUUID(int length){
        StringBuilder sb = new StringBuilder();
        int spacer = 0;
        while(length > 0){
            length--;
            sb.append(randomChar());
        }
        return sb.toString();
    }
}
