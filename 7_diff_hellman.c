#include <stdio.h>
#include <stdlib.h>
#include <math.h>

#define pValue 23
#define aValue 5

// Function to perform modular exponentiation (a^b mod m)
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

int main() {
    int p = pValue;
    int a = aValue;

    printf("Enter private key of A: ");
    int Xa;
    scanf("%d", &Xa);

    printf("Enter private key of B: ");
    int Xb;
    scanf("%d", &Xb);

    // Compute Ya and Yb
    int Ya = mod_exp(a, Xa, p);
    int Yb = mod_exp(a, Xb, p);

    // Compute Ka and Kb
    int Ka = mod_exp(Yb, Xa, p);
    int Kb = mod_exp(Ya, Xb, p);

    printf("Public Key for A is : %d\n", Ya);
    printf("Public Key for B is : %d\n", Yb);
    printf("Secret Key for A is : %d\n", Ka);
    printf("Secret Key for B is : %d\n", Kb);

    return 0;
}
