package kelompok2;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Hmac using MD5 implementation
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        String secreyKey = "secret";
        String message = "You are not supposed to read this!";
        Integer hmacLength = 24; // 128bit/8 = 16 bytes = 24 char length in base64

        byte[] hmacMD5 = HMAC.hmacMD5(secreyKey, message);
        String base64HmacMD5 = Base64.getEncoder().encodeToString(hmacMD5);
        String sentMessage = message + base64HmacMD5;

        System.out.println("Message before being sent from sender\n");
        System.out.println("Secret key\t\t: " + secreyKey);
        System.out.println("Message\t\t\t: " + message);
        System.out.println("HMAC\t\t\t: " + base64HmacMD5);
        System.out.println("Sent Message\t\t: " + sentMessage);

        System.out.println("\n-----------------------------------------------------------\n");

        String receivedMessage = sentMessage.substring(0, sentMessage.length()-hmacLength);
        String receivedHmac = sentMessage.substring(sentMessage.length()-hmacLength);

        byte[] calculatedHmacMD5 = HMAC.hmacMD5(secreyKey, message);
        String calculatedBase64HmacMD5 = Base64.getEncoder().encodeToString(calculatedHmacMD5);

        System.out.println("Message arrived at receiver's device\n");
        System.out.println("Received Message\t: " + sentMessage);
        System.out.println("Received Plain Message\t: " + receivedMessage);
        System.out.println("Received HMAC\t\t: " + receivedHmac);
        System.out.println("Calculated HMAC\t\t: " + calculatedBase64HmacMD5);
        System.out.println("Message Authentication \t: " + receivedHmac.equals(calculatedBase64HmacMD5));
    }
}
