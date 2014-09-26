/*
 * a1_part6.c
 *
 *      Created on: Sep 17, 2014
 *          Author: Douglas Wong
 *  Student Number: V00787654
 */

#include <stdio.h>
#include <stdlib.h>
#define MAXROW 5
#define MAXCOL 5

int mymatrix[MAXROW][MAXCOL];

void init_matrix (int m[MAXROW][MAXCOL], int val)
{
	// if val is negative, initialize as a zero matrix
	if (val < 0)
	{
		int i;
		int j;
		for (i = 0; i < MAXROW ; i++)
		{
			for (j = 0; j < MAXCOL ; j++)
				m[i][j] = 0;
		}
	}

	else
	{
		int count = val;

		int i;
		for (i = 0; i < MAXROW ; i++)
		{

			int j;
			for (j = 0; j < MAXCOL ; j++)
			{
				m[i][j] = count;
				count++;
			}
		}
	}
}

void print_matrix (int m[MAXROW][MAXCOL])
{
	int i;
	for (i = 0; i < MAXROW; i++)
	{

		int j;
		for (j = 0; j < MAXCOL; j++)
			printf("%d ", m[i][j]);

		printf("\n");
	}
	printf("\n");
}

void add_matrix (int m[MAXROW][MAXCOL], int m1[MAXROW][MAXCOL], int m2[MAXROW][MAXCOL])
{
	int i;
	for (i = 0; i < MAXROW; i++)
	{

		int j;
		for (j = 0; j < MAXCOL; j++)
			m2[i][j] = m[i][j] + m1[i][j];
	}
}

void multiply_matrix (int m[MAXROW][MAXCOL], int m1[MAXROW][MAXCOL], int m2[MAXROW][MAXCOL])
{
	/*
	 * this function would not work if:
	 * - the input matrices and the result matrix are not squared
	 * - the input matrices and the result matrix don't have the same size
	 */

	if (MAXROW != MAXCOL)
	{
		printf("\n--ERROR: rows and columns don't match\n");
		return;
	}

	int i, j, k;

	for (i=0 ; i<MAXROW ; i++)
	{
		for (j=0 ; j<MAXCOL ; j++)
		{
			for (k=0 ; k<MAXROW ; k++)
			{
				m2[i][j] += m[i][k] * m1[k][j];
			}
		}
	}

}

int main()
{
	int mymtrix[MAXROW][MAXCOL];
	int mymatrix1[MAXROW][MAXCOL];
	int mymatrix2[MAXROW][MAXCOL];

 // initialize matrices
	init_matrix(mymtrix, 1);
	init_matrix(mymatrix1, 50);
	init_matrix(mymatrix2, -1);

 // print matrices
	printf("mymtrix = \n");
	print_matrix(mymtrix);
	printf("mymtrix1 = \n");
	print_matrix(mymatrix1);
	printf("mymtrix2 = \n");
	print_matrix(mymatrix2);

 // add matrices
	printf("processing <mymatrix2 = mymtrix + mymatrix1>...\n");
	add_matrix(mymtrix, mymatrix1, mymatrix2);

 // print output matrix
	printf("mymatrix2 = \n");
	print_matrix(mymatrix2);

 // initialize output matrix
	printf("re-initializing <mymatrix2>\n");
	init_matrix(mymatrix2, -1);

 // multiply matrices
	printf("processing <mymatrix2 = mymtrix + mymatrix1>...\n");
	multiply_matrix(mymtrix, mymatrix1, mymatrix2);

 // print output matrix
	printf("mymtrix2 = \n");
	print_matrix(mymatrix2);

	exit(0);
}
