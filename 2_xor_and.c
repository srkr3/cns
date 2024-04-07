#include <stdio.h>
#include<string.h>

int main() {
    char a[20] = "Hello world";
    char b[20],c[20];
    int n,i;
    n  = strlen(a);
   
    for(i=0;i<n;i++)
    {
    	b[i] = a[i] & 127;
	}
	b[i] = '\0';
	printf("%s\n",b );

	for(i=0;i<n;i++)
    {
	    c[i] = a[i] ^ 127;
	}
	c[i] = '\0';
	printf("%s",c );   
}
