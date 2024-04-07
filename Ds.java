import java.security.*;
import java.util.Scanner;

public class Ds {
    public static void main(String[] args) throws Exception {
        // Generate a key pair
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        keyGen.initialize(1024, random);
        KeyPair pair = keyGen.generateKeyPair();
        PrivateKey priv = pair.getPrivate();
        PublicKey pub = pair.getPublic();

        // Sign some data
        Signature dsa = Signature.getInstance("SHA1withDSA");
        dsa.initSign(priv);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the text to sign: ");
        String str = scanner.nextLine();
        byte[] strByte = str.getBytes("UTF8");
        dsa.update(strByte);
        byte[] realSig = dsa.sign();

        // receivers side

        System.out.println("enter received messsage: ");
        String rcv = scanner.nextLine();

        dsa.initVerify(pub);
        dsa.update(rcv.getBytes("UTF8"));
        boolean verifies = dsa.verify(realSig);
        System.out.println("Signature verifies: " + verifies);
        scanner.close();
    }
}