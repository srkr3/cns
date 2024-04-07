#include<stdio.h>
#include<string.h>
int main()
{
	char a[100],b[100],c[100];
	int key,i;

	printf("enter plain text ");
	scanf("%s",a);

	printf("entre key ");
	scanf("%d",&key);

//Encryption
	for (i=0;i<strlen(a);i++)
	{
	    if(a[i] >= 'A' && a[i] <= 'Z')
		{
	        b[i] = (char)((int)(a[i]+key-65+26)%26+65); // A=65
	   }
	    if(a[i] >= 'a' && a[i] <= 'z')
		{
	        b[i] = (char)((int)(a[i] + key - 97 + 26) % 26 + 97); // a=97
	    }
	}
	b[i] ='\0';
	printf("Encryption : %s\n",b);

//decryption
	for(i=0;i<strlen(b);i++)
	{
		if(b[i] >= 'A' && b[i] <= 'Z')//isupper(b[i])
		{
			c[i] = (char)((int)(b[i] - key - 65 + 26) % 26 + 65); // A=65
		}
		if(b[i] >= 'a' && b[i] <= 'z')
		{
	        c[i] = (char)((int)(b[i] - key - 97 + 26) % 26 + 97); // a=97
	    }
	}
	c[i] = '\0';
	printf("Decryption : %s\n",c);
}
