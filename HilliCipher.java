#include <stdio.h>

float a[3][3];
float b[3][3];
float mes[3][1];
float res_encrypted[3][1];
float res_decrypted[3][1];

void inverse();

int main() {
    int i, j, k;
    char msg[4];

    printf("\nEnter a 3 letter string: ");
    scanf("%s", msg);
    for (i = 0; i < 3; i++)
        mes[i][0] = msg[i] - 'a';

    printf("Enter key matrix of 3x3 matrix: ");
    for (i = 0; i < 3; i++)
        for (j = 0; j < 3; j++)
            scanf("%f", &a[i][j]);

    // Encryption
    for (i = 0; i < 3; i++)
        for (j = 0; j < 1; j++)
            for (k = 0; k < 3; k++) {
                res_encrypted[i][j] = res_encrypted[i][j] + a[i][k] * mes[k][j];
            }
    printf("\nEncrypted string is : ");
    for (i = 0; i < 3; i++) {
        printf("%c", (char)(((int)(res_encrypted[i][0]) % 26) + 'a'));
    }

    // Decryption
    inverse();
    for (i = 0; i < 3; i++)
        for (j = 0; j < 1; j++)
            for (k = 0; k < 3; k++) {
                res_decrypted[i][j] = res_decrypted[i][j] + b[i][k] * res_encrypted[k][j];
            }
    printf("\nDecrypted string is : ");
    for (i = 0; i < 3; i++) {
        printf("%c", (char)(((int)(res_decrypted[i][0]) % 26) + 'a'));
    }
    printf("\n");
    return 0;
}

// inv
void inverse() {
    int i, j;
    float det = a[0][0] * (a[1][1] * a[2][2] - a[1][2] * a[2][1]) -
                a[0][1] * (a[1][0] * a[2][2] - a[1][2] * a[2][0]) +
                a[0][2] * (a[1][0] * a[2][1] - a[1][1] * a[2][0]);

    if (det == 0) {
        printf("Matrix is singular, cannot find inverse.");
        return;
    }

    float invDet = 1 / det;

    b[0][0] = (a[1][1] * a[2][2] - a[1][2] * a[2][1]) * invDet;
    b[0][1] = (a[0][2] * a[2][1] - a[0][1] * a[2][2]) * invDet;
    b[0][2] = (a[0][1] * a[1][2] - a[0][2] * a[1][1]) * invDet;
    b[1][0] = (a[1][2] * a[2][0] - a[1][0] * a[2][2]) * invDet;
    b[1][1] = (a[0][0] * a[2][2] - a[0][2] * a[2][0]) * invDet;
    b[1][2] = (a[0][2] * a[1][0] - a[0][0] * a[1][2]) * invDet;
    b[2][0] = (a[1][0] * a[2][1] - a[1][1] * a[2][0]) * invDet;
    b[2][1] = (a[0][1] * a[2][0] - a[0][0] * a[2][1]) * invDet;
    b[2][2] = (a[0][0] * a[1][1] - a[0][1] * a[1][0]) * invDet;

    for (i = 0; i < 3; i++)
        for (j = 0; j < 3; j++) {
            b[i][j] = (int)(b[i][j]) % 26;
            if (b[i][j] < 0)
                b[i][j] += 26;
        }
}

