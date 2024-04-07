#include<stdio.h>
#include<string.h>

int main()
{
	char a[] = "Hello World";
	char b[11];
	int n,i;
	n = strlen(a);

	for(i=0;i<n;i++)
	{
		b[i] = a[i]^0;
	}
	b[i]='\0';
	printf("%s",b);
}
