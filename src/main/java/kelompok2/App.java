package kelompok2;

import com.sun.jna.*;
import com.sun.jna.platform.win32.WinDef.*;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import java.util.Base64;
import java.util.Scanner;

import static com.diogonunes.jcolor.Ansi.colorize;
import static com.diogonunes.jcolor.Attribute.*;

/**
 * Hmac using MD5 implementation
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        while (true) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Type \"exit\" to quit the shell.");
            System.out.print("Input message \t\t: ");
            String message = scan.nextLine();

            if ("exit".equals(message.trim())) {
                break;
            }

            System.out.println("\n-----------------------------------------------------------");

            String secreyKey = "secret";
            Integer hmacLength = 24; // 128bit/8 = 16 bytes = 24 char length in base64

            byte[] hmacMD5 = HMAC.hmacMD5(secreyKey, message);
            String base64HmacMD5 = Base64.getEncoder().encodeToString(hmacMD5);
            String sentMessage = message + base64HmacMD5;

            System.out.println("Message before being sent from sender\n");
            System.out.println("Secret key\t\t: " + secreyKey);
            System.out.println("Message\t\t\t: " + message);
            System.out.println("HMAC\t\t\t: " + base64HmacMD5);
            System.out.println("Sent Message\t\t: " + sentMessage);

            System.out.println("-----------------------------------------------------------");

            String receivedMessage = sentMessage.substring(0, sentMessage.length() - hmacLength);
            String receivedHmac = sentMessage.substring(sentMessage.length() - hmacLength);

            byte[] calculatedHmacMD5 = HMAC.hmacMD5(secreyKey, message);
            String calculatedBase64HmacMD5 = Base64.getEncoder().encodeToString(calculatedHmacMD5);

            System.out.println("Message arrived at receiver's device\n");
            System.out.println("Received Message\t: " + sentMessage);
            System.out.println("Received Plain Message\t: " + receivedMessage);
            System.out.println("Received HMAC\t\t: " + receivedHmac);
            System.out.println("Calculated HMAC\t\t: " + calculatedBase64HmacMD5);
            System.out.println("Message Authentication \t: " + receivedHmac.equals(calculatedBase64HmacMD5));

            System.out.println("-----------------------------------------------------------\n");
        }
    }
}
