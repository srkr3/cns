#include <stdio.h>
#include <stdlib.h>

int isprime(int a) {
    int i;
    for(i = 2; i < a; i++) {
        if(a % i == 0) {
            return 1;
        }
    }
    return 0;
}

int gcd(int a, int b) {
    if (b == 0)
        return a;
    return gcd(b, a % b);
}

int mod_exp(int base, int exponent, int modulus) {
    int result = 1;
    base = base % modulus;
    while (exponent > 0) {
        if (exponent % 2 == 1)
            result = (result * base) % modulus;
        base = (base * base) % modulus;
        exponent /= 2;
    }
    return result;
}

int mod_inverse(int a, int m) {
    int m0 = m, t, q, x0 = 0, x1 = 1;

    if (m == 1)
        return 0;

    while (a > 1) {
        q = a / m;
        t = m;
        m = a % m, a = t;
        t = x0;
        x0 = x1 - q * x0;
        x1 = t;
    }
    if (x1 < 0)
        x1 += m0;

    return x1;
}

int main() {
    int p, q, n, phi, e, d;
    int M, C;

    printf("Enter two primes p and q: \n");
    printf("p = ");
    scanf("%d", &p);
    printf("q = ");
    scanf("%d", &q);

    if(isprime(p)) {
        printf("p %d is not prime\n", p);
        return 1;
    } else if(isprime(q)) {
        printf("q %d is not prime\n", q);
        return 1;
    } else {
        n = p * q;
        phi = (p - 1) * (q - 1);

        for(e = 2; e < phi; e++) {
            if(gcd(phi, e) == 1)
                break;
        }

        d = mod_inverse(e, phi);

        printf("Public key: (n=%d, e=%d)\n", n, e);
        printf("Private key: (n=%d, d=%d)\n\n", n, d);

        printf("Enter the message: ");
        scanf("%d", &M);

        // Encryption
        C = mod_exp(M, e, n);
        printf("Ciphertext: %d\n", C);

        // Decryption
        M = mod_exp(C, d, n);
        printf("Decrypted message: %d\n", M);
    }

    return 0;
}
