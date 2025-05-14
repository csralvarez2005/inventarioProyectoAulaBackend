package inventarioEquiposBackend.config;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

public class GenerateSecret {
    public static void main(String[] args) throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
        keyGen.init(256);
        SecretKey secretKey = keyGen.generateKey();
        String encoded = Base64.getEncoder().encodeToString(secretKey.getEncoded());
        System.out.println(encoded);
    }
}
