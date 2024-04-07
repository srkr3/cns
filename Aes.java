import javax.crypto.*;
import java.util.Base64;
import java.util.Scanner;

public class Aes {
    private static Cipher encryptCipher;
    private static Cipher decryptCipher;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text to be encrypted: ");
        String plainText = sc.nextLine();

        // Generate AES key
        SecretKey secretKey = generateAESKey();
        setupEncryptor(secretKey);

        String encryptedString = encrypt(plainText);
        System.out.println("Encrypted: " + encryptedString);

        String decryptedString = decrypt(encryptedString);
        System.out.println("Decrypted: " + decryptedString);

        sc.close();
    }

    public static SecretKey generateAESKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128); // 128 bit key
        return keyGen.generateKey();
    }

    public static void setupEncryptor(SecretKey secretKey) throws Exception {
        encryptCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        encryptCipher.init(Cipher.ENCRYPT_MODE, secretKey);

        decryptCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        decryptCipher.init(Cipher.DECRYPT_MODE, secretKey);
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