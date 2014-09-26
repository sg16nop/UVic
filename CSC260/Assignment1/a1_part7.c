/*
 * a1_part7.c
 *
 *      Created on: Sep 23, 2014
 *          Author: Douglas Wong
 *  Student Number: V00787654
 */
#include<stdio.h>

int main()
{

	int a;
	int b;

	printf("please key in inputs: A, B \nA = : ");
	scanf("%x", &a);
	printf("B = :");
	scanf("%x", &b);

	printf("\n variables: A = %x B = %x \n", a, b);

	//AND operation
	printf("A AND B = 0x%x\n", a&b);

	//OR operation
	printf("A OR B = 0x%x\n", a|b);

	//XOR operation
	printf("A XOR B = 0x%x\n", a^b);

	//2 position left shift on A
	printf("left shift 2 positions on A = 0x%x\n", a<<2);

	//2 position right shift on A
	printf("right shift 2 positions on A = 0x%x\n", a>>2);

	return 0;

}
