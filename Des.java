import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.util.Base64;
import java.util.Scanner;

public class Des {
    private static Cipher encryptCipher;
    private static Cipher decryptCipher;

    public static void main(String[] args) throws Exception {
        String secretKey = "ERwduthg"; // must be 8 characters for DES
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text to be encrypted: ");
        String plainText = sc.nextLine();

        setupEncryptor(secretKey);

        String encryptedString = encrypt(plainText);
        System.out.println("Encrypted: " + encryptedString);

        String decryptedString = decrypt(encryptedString);
        System.out.println("Decrypted: " + decryptedString);

        sc.close();
    }

    public static void setupEncryptor(String secretKey) throws Exception {
        DESKeySpec desKeySpec = new DESKeySpec(secretKey.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey key = keyFactory.generateSecret(desKeySpec);

        encryptCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        encryptCipher.init(Cipher.ENCRYPT_MODE, key);

        decryptCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        decryptCipher.init(Cipher.DECRYPT_MODE, key);
    }

    public static String encrypt(String str) throws Exception {
        byte[] utf8 = str.getBytes("UTF8");
        byte[] enc = encryptCipher.doFinal(utf8);
        return Base64.getEncoder().encodeToString(enc);
    }

    public static String decrypt(String str) throws Exception {
        byte[] dec = Base64.getDecoder().decode(str);
        byte[] utf8 = decryptCipher.doFinal(dec);
        return new String(utf8, "UTF8");
    }
}