package kelompok2;

import java.math.BigInteger;

/**
 * Hmac using MD5 implementation
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        byte[] hmacMD5 = HMAC.hmacMD5("secret", "You are not supposed to read this!");
        System.out.println(String.format("Hex: %032x", new BigInteger(1, hmacMD5)));
    }
}
