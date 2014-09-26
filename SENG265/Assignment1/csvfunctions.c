/* File: csvfunctions.c

   ==> THIS IS THE FILE YOU CHANGE <==

   IMPORTANT:  Replace the line above with your student number and your name.
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "csvfunctions.h"

#define MAXINPUTLINELEN     256

static SPREADSHEET theSS;   // the spreadsheet to work on
static int debug = 0;
static int tooWide = 0;


// forward declaration
static int extractItems(char *line, char row[][MAXLEN]);

void SS_SetDebug(int dbg) {
    debug = dbg;
}

SPREADSHEET *SS_ReadCSV(char *fileName) {
    char line[MAXINPUTLINELEN];
    SPREADSHEET result;
    int i;

    result.fileName = fileName;
    result.numRows = result.numCols = 0;

    FILE *f = fopen(fileName, "r");
    if (f == NULL) {
        fprintf(stderr, "Unable to read from file %s\n", fileName);
        perror(fileName);
        return NULL;
    }
    for( i = 0; i < MAXROWS; i++) {
        if (fgets(line, MAXINPUTLINELEN, f) == NULL) {
            // i--;    <=== this was a mistake!!
            break;
        }
        int k = extractItems(line, result.contents[i]);
        if (result.numCols == 0) {
            result.numCols = k;
        } else
        if (result.numCols != k) {
            fprintf(stderr, "Row %d has different number of columns from first row\n", i);
        }
    }
    result.numRows = i;
    fclose(f);
    memcpy(&theSS, &result, sizeof(SPREADSHEET));
    if (tooWide)
        fprintf(stderr, "Warning, number of columns exceeded the max of %d\n", MAXCOLS);
    return &theSS;
}

static int extractItems(char *line, char row[][MAXLEN]) {

	int col = 0;
	int i = 0;	//i iterates through row (write iterator)
	int j = 0;	//j iterates through line (read iterator)

	// determines if there was an open ". (to distinguish the commas)
	int isQuoteOpen = 0;

	// isLengthExceed is 1 or higher indicates that maximum length of the cell is exceeded.
	// IT DOES NOTHING FOR NOW. But it might be useful in the future if a error message
	// needs to be printed to indicated a string cutoff
	int isLengthExceed = 0;

	for (;;)
	{
		switch (line[j])
		{

			case '\0': //fall-through intended

			case '\n':
			{
				row[col][i] = '\0';
				row[col][MAXLEN-1] = '\0';
				if (isQuoteOpen == 1)
					printf("INITIALIZATION ERROR(%d): closing double quote(\") is missing somewhere in column %d\n",j, col);
				col++;
				return col;
			}

			case '"':
			{
				if (isQuoteOpen == 0)
				{
					isQuoteOpen = 1;
					j++;
				}
				else
				{
					if(line[j+1] == '"')
					{
						row[col][i] = line[j];
						if(i+1 < MAXLEN)
							i++;
						else
							isLengthExceed++;
						j+=2;
					}

					else
					{
						isQuoteOpen = 0;
						j++;
					}
				}

				break;
			}

			case ',':
			{
				if (isQuoteOpen == 1)
				{
					row[col][i] = line[j];
					if(i+1 < MAXLEN)
						i++;
					else
						isLengthExceed++;
					j++;
				}

				else
				{
					row[col][i] = '\0';
					row[col][MAXLEN-1] = '\0';
					col++;
					i = 0;
					// isLengthExceed is reset when program start writing on next column
					isLengthExceed = 0;
					j++;
				}

				break;
			}

			default:
			{
				row[col][i] = line[j];

				if(i+1 < MAXLEN)
					i++;
				else
					isLengthExceed++;
				j++;
				break;
			}
		}
	}

	return -1;
}


// Searches down the specified column for a row which contains text.
// The search starts at row number rowNum;
// The result is the row number (where the first row is numbered 0).
// If the text is not found, the result is -1.
int SS_FindRow(SPREADSHEET *ss, int colNum, char *text, int startNum) {
    if (debug)
        fprintf(stderr, "DEBUG: Call to SS_FindRow(--,%d,%s,%d)\n",
            colNum, text, startNum);

    if (startNum < 0)
    {
    	printf("-- INVALID INPUT: Negative row number \n");
    	return -1;
    }

    if (startNum >= ss->numRows)
    {
    	printf("-- INVALID INPUT: Specified row is out of range \n");
    	return -1;
    }

    if (colNum < 0)
    {
    	printf("-- INVALID INPUT: Negative column number \n");
    }

    if (colNum >= ss->numCols)
    {
    	printf("-- INVALID INPUT: Specified column is out of range \n");
    }

    int i;
    for (i = startNum; i < ss->numRows; i++)
    {
    	if ( strcmp( ss->contents[i][colNum] , text) == 0 )
    		return i;
    }
    return -1;
}

// Outputs the specified row of the spreadsheet.
// It is printed as one line on standard output.
void SS_PrintRow(SPREADSHEET *ss, int rowNum) {
    if (debug)
        fprintf(stderr, "DEBUG: Call to SS_PrintRow(--,%d)\n", rowNum);

    printf("row%d: ", rowNum);

    if (rowNum < 0)
    {
    	printf("-- INVALID INPUT: negative row number (%d)\n", rowNum);
    	return;
    }

    if ((rowNum+1) > ss->numRows)
    {
    	printf("-- INVALID INPUT: specified row (%d) is out of range\n", rowNum);
    	return;
    }

    int i;
    for(i = 0; i < ss->numCols ; i++)
    {
    	if (ss->contents[rowNum][i] == NULL)
    		break;

    	printf("|| %s ", ss->contents[rowNum][i]);
    }

    printf("||\n");
    return;
}

// The specified column must contain the textual representations of numbers
// (either integer or floating point). The sum of the numbers in the column
// is returned as a floating point number.
double SS_ColumnSum(SPREADSHEET *ss, int colNum) {
    if (debug)
        fprintf(stderr, "DEBUG: Call to SS_ColumnSum(--,%d)\n", colNum);

    if (colNum >= ss->numCols)
    	printf("-- specified column out of range, unexpected result may appear\n");

    double sum = 0;

    int i;
    for ( i = 0; i < ss->numRows; i++)
    {
    	sum += atof(ss->contents[i][colNum]);
    }

    return sum;
}

double SS_ColumnAvg(SPREADSHEET *ss, int colNum) {
    if (debug)
        fprintf(stderr, "DEBUG: Call to SS_ColumnAvg(--,%d)\n", colNum);

    double avg = SS_ColumnSum(ss, colNum) / ss->numRows;

    return avg;
}

