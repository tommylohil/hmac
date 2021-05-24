package kelompok2;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

public class HMAC {

    public static byte[] hmacMD5(String secretKey, String message) {
        try {
            // Convert string to byte[]
            byte[] secretKeyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
            byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);
            byte[] result = null;

            // Setup HmacMD5
            Mac mac = Mac.getInstance("HmacMD5");
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKeyBytes, "HmacMD5");
            mac.init(secretKeySpec);
            result = mac.doFinal(messageBytes);

            return result;

        } catch (Exception e) {
            throw new RuntimeException("Failed to hash message!");
        }
    }
}
