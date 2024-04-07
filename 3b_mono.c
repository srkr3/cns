#include<stdio.h>
#include<string.h>
int main()
{
	char sample[27][3] = { { 'a', 'f' }, { 'b', 'a' }, { 'c', 'g' }, { 'd', 'u' }, {'e', 'n' }, { 'f', 'i' }, 
					  { 'g', 'j' }, { 'h', 'k' }, { 'i', 'l' }, {'j', 'm' }, { 'k', 'o' }, { 'l', 'p' }, 
					  { 'm', 'q' }, { 'n', 'r' }, {'o', 's'}, { 'p', 't' }, { 'q', 'v' }, { 'r', 'w' }, 
					  { 's', 'x' }, {'t', 'y' }, { 'u', 'z' }, { 'v', 'b' }, { 'w', 'c' }, { 'x', 'd' }, 
					  {'y', 'e' }, { 'z', 'h' } };
	
	char str1[100],str2[100],str3[100],e,d;
	int i,n,j;
	printf("enter the string \n");
	scanf("%s",str1);

	n = strlen(str1);
	//enc
	for(i=0;i<n;i++)
	{
		e = str1[i];
		for(j=0;j<27;j++)
		{
			if(e == sample[j][0])
			{
				str2[i] = sample[j][1];
				break;
			}
		}
	}
	str2[i]='\0';
	printf("string after encryption %s\n",str2);
	
	//dec
	for(i=0;i<n;i++)
	{
		d = str2[i];
		for(j=0;j<27;j++)
		{
			if(d == sample[j][1])
			{
				str3[i] = sample[j][0];
				break;
			}
		}
	}
	str3[i]='\0';
	printf("string after dencryption %s",str3);
}
