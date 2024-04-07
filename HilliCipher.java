import java.util.*;

public class HilliCipher {
    static float[][] decrypt = new float[3][1];
    static float[][] a = new float[3][3];
    static float[][] b = new float[3][3];
    static float[][] mes = new float[3][1];
    static float[][] res = new float[3][1];
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.print("\nEnter a 3 letter string: ");
        String msg = sc.next();
        for (int i = 0; i < 3; i++)
            mes[i][0] = msg.charAt(i) - 97;
        System.err.println("Enter key matrix of 3x3 matrix: ");
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                a[i][j] = sc.nextFloat();

        // enc
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 1; j++)
                for (int k = 0; k < 3; k++) {
                    res[i][j] = res[i][j] + a[i][k] * mes[k][j];
                }
        System.out.print("\nEncrypted string is : ");
        for (int i = 0; i < 3; i++) {
            System.out.print((char) (res[i][0] % 26 + 97));
        }
        // dec
        inverse();
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 1; j++)
                for (int k = 0; k < 3; k++) {
                    decrypt[i][j] = decrypt[i][j] + b[i][k] * res[k][j];
                }
        System.out.print("\nDecrypted string is : ");
        for (int i = 0; i < 3; i++) {
            System.out.print((char) (decrypt[i][0] % 26 + 97));
        }
        System.out.print("\n");
    }
    // inv

    public static void inverse() {

        float det = a[0][0] * (a[1][1] * a[2][2] - a[1][2] * a[2][1])
                - a[0][1] * (a[1][0] * a[2][2] - a[1][2] * a[2][0])
                + a[0][2] * (a[1][0] * a[2][1] - a[1][1] * a[2][0]);

        if (det == 0) {
            System.out.println("Matrix is singular, cannot find inverse.");
        }

        float invDet = 1 / det;

        b[0][0] = (a[1][1] * a[2][2] - a[1][2] * a[2][1]);
        b[0][1] = (a[0][2] * a[2][1] - a[0][1] * a[2][2]);
        b[0][2] = (a[0][1] * a[1][2] - a[0][2] * a[1][1]);
        b[1][0] = (a[1][2] * a[2][0] - a[1][0] * a[2][2]);
        b[1][1] = (a[0][0] * a[2][2] - a[0][2] * a[2][0]);
        b[1][2] = (a[0][2] * a[1][0] - a[0][0] * a[1][2]);
        b[2][0] = (a[1][0] * a[2][1] - a[1][1] * a[2][0]);
        b[2][1] = (a[0][1] * a[2][0] - a[0][0] * a[2][1]);
        b[2][2] = (a[0][0] * a[1][1] - a[0][1] * a[1][0]);

        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                b[i][j] = b[i][j] * invDet;
                if (b[i][j] < 0)
                    b[i][j] = (b[i][j] % 26) + 26;
                else
                    b[i][j] = b[i][j] % 26;
            }

    }

}