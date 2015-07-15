/* File: csvfunctions2.c

   Name: Douglas Wong
   Student Number: V00787654

*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "csvfunctions2.h"

#define MAXINPUTLINELEN     256
#define MAXITEMSPERROW		128

#define CHECKMALLOC(p)	if ((p)==NULL) { fprintf(stderr,"out of memory!"); exit(1); } else { }

static int debug = 0;


// forward declarations
static int extractItems(char *line, char *row[]);
char *mystrdup(char *s);

void SS_SetDebug(int dbg) {
    debug = dbg;
}

SPREADSHEET *SS_ReadCSV(char *fileName) {
    char line[MAXINPUTLINELEN];
    char *tempRow[MAXITEMSPERROW];
    SPREADSHEET *result;
    struct OneRow *lastRow = NULL;
    int i;

	result = malloc(sizeof(SPREADSHEET));
	CHECKMALLOC(result);
    result->fileName = mystrdup(fileName);
    result->firstRow = NULL;
    result->numRows = result->numCols = 0;

    FILE *f = fopen(fileName, "r");
    if (f == NULL) {
        fprintf(stderr, "Unable to read from file %s\n", fileName);
        perror(fileName);
        return NULL;
    }
    for( i = 0; ; i++) {
        if (fgets(line, MAXINPUTLINELEN, f) == NULL)
            break;
        int k = extractItems(line, tempRow);
        if (result->numCols == 0) {
            result->numCols = k;
        } else
        if (result->numCols != k) {
            fprintf(stderr, "Row %d has different number of columns from first row\n", i);
            continue;	// ignore this row
        }

        // instantiate the storage for the new row and copy k cells into it
        char **rc = calloc(k, sizeof(char *));
        CHECKMALLOC(rc);
        struct OneRow *newrow = malloc(sizeof(struct OneRow));
        CHECKMALLOC(newrow);
        newrow->row = rc;
        newrow->nextRow = NULL;
        int ix;
        for( ix = 0; ix < k; ix++ ) {
            rc[ix] = tempRow[ix];
        }
        
        // add the new row as the last row in the spreadsheet
        if (lastRow == NULL) {
            result->firstRow = newrow;
        } else {
            lastRow->nextRow = newrow;
        }
        lastRow = newrow;

    }
    result->numRows = i;
    fclose(f);
    return result;
}

// Write the spreadsheet in CSV format to the specified file 
void SS_SaveCSV(SPREADSHEET *ss, char *fileName) {
    if (debug)
        fprintf(stderr, "DEBUG: Call to SS_SaveCSV(--)\n");
	// IMPLEMENT ME
	// Note: you will find this easier to implement if you enclose every cell
	// value in doublequotes when writing it out. If there are any doublequotes
	// in the string being output, these should be duplicated.
	// You have got this right if the file just written can be read back in
	// again and all the values appear to be unchanged when displayed with
	// the printrow command.
}

// Free all storage being use by the spreadsheet instance.
extern void SS_Unload(SPREADSHEET *ss) {
    if (debug)
        fprintf(stderr, "DEBUG: Call to SS_Unload(--)\n");
	// IMPLEMENT ME
}

// Reads one item from the csv row.
// line is a string where reading should begin; tok reference a char array into
// which the characters for the item are copied.
// On return, the result references the remainder of the original string which has
// not been read, or is NULL if no item could be read before the end of the line.
static char *getOneItem(char *line, char *tok) {
    char *tokSaved = tok;
    char c;
    c = *line++;
S1: if (c == '\"') {
        c = *line++;
        goto S2;
    }
    if (c == ',' || c == '\0' || c == '\n' || c == '\r') {
        goto S4;
    }
    *tok++ = c;
    c = *line++;
    goto S1;
S2: if (c == '\"') {
        c = *line++;
        goto S3;
    }
    if (c == '\0' || c == '\n' || c == '\r') {
        // unexpected end of input line
        fprintf(stderr, "mismatched doublequote found");
        goto S4;
    }
    *tok++ = c;
    c = *line++;
    goto S2;
S3: if (c == '\"') {
        *tok++ = '\"';
        c = *line++;
        goto S2;
    }
    if (c == ',' || c == '\0' || c == '\n' || c == '\r') {
        goto S4;
    }
    *tok++ = c;
    c = *line++;
    goto S1;
S4: if (c == '\0' || c == '\n' || c == '\r') {
        if (tokSaved == tok)
            return NULL;  // nothing was read
        line--;
    }
    *tok = '\0';
    return line;
}

// Extracts items one by one from line, copies them into heap storage,
// and stores references to them in the row array.
// The function result is the number of items copied.
static int extractItems(char *line, char *row[]) {
    char t[MAXINPUTLINELEN];
    int col = 0;
    for( ; ; ) {
        line = getOneItem(line,t);
        if (line == NULL) break;
        char *s = mystrdup(t);
        row[col++] = s;
        
    }
    return col;
}

// prints filename, number of rows and number of columns for
// this spreadsheet
void SS_PrintStats(SPREADSHEET *ss) {
    if (debug)
        fprintf(stderr, "DEBUG: Call to SS_PrintStats(--)\n");
	
	printf("File: %s\n", ss->fileName);
	printf("Rows: %d\n",ss->numRows);
	printf("Columns: %d\n",ss->numCols);
}


// Transfers rows from spreadsheet ss2 to the end of spreadsheet ss1
// then releases any storage for ss2 which is no longer needed
void SS_MergeCSV(SPREADSHEET *ss1, SPREADSHEET *ss2) {
    if (debug)
        fprintf(stderr, "DEBUG: Call to SS_MergeCSV(--, --)\n");
	// IMPLEMENT ME
}

// Deletes the specified row from the spreadsheet.
// Any storage used by the row is freed.
void SS_DeleteRow(SPREADSHEET *ss, int rowNum) {
    if (debug)
        fprintf(stderr, "DEBUG: Call to SS_DeleteRow(--,%d)\n", rowNum);
	// IMPLEMENT ME
}

// Sorts the rows of the spreadsheet into ascending order, based on
// the strings in the specified column
extern void SS_Sort(SPREADSHEET *ss, int colNum) {
    if (debug)
        fprintf(stderr, "DEBUG: Call to SS_Sort(--,%d)\n", colNum);
	// IMPLEMENT ME
}

// Sorts the rows of the spreadsheet into ascending order, based on
// the values of the floating point numbers in the specified column
extern void SS_SortNumeric(SPREADSHEET *ss, int colNum) {
    if (debug)
        fprintf(stderr, "DEBUG: Call to SS_SortNumeric(--,%d)\n", colNum);
	// IMPLEMENT ME
}


// Searches down the specified column for a row which contains text.
// The search starts at row number startNum;
// The result is the row number (where the first row is numbered 0).
// If the text is not found, the result is -1.
int SS_FindRow(SPREADSHEET *ss, int colNum, char *text, int startNum) {
    if (debug)
        fprintf(stderr, "DEBUG: Call to SS_FindRow(--,%d,%s,%d)\n",
            colNum, text, startNum);
	
	
	// Error messages + handling
	if (startNum < 0) {
		printf("-- specified starting row(%d) is invalid\n", startNum);
		return -1;
	}
	if (startNum+1 > ss->numRows) {
		printf("-- specified starting row(%d) is out of range\n", startNum);
		return -1;
	}
	if (colNum+1 > ss->numCols) {
		printf("-- specified column(%d) out of range\n", colNum);
		return -1;
	}
	
	
	
	struct OneRow *rowIt = ss->firstRow;					// initialize iterator to head
	int i;
	for (i=0; i < startNum ; i++) {							// this loop move iterator to starting row
		rowIt = rowIt->nextRow;
	}
	
		
	for (i=startNum; i < ss->numRows; i++) {				// this loop traverse through rows
		printf(rowIt->row[colNum]);
		printf("\n");
		
		if (strcmp(rowIt->row[colNum],text) == 0)	{		// directly check specified column
			printf("match !!i is %d\n", i);
			return i;
		}
		rowIt = rowIt->nextRow;
	}
	
    return -1;
}

// Outputs the specified row of the spreadsheet.
// It is printed as one line on standard output.
void SS_PrintRow(SPREADSHEET *ss, int rowNum) {
    if (debug)
        fprintf(stderr, "DEBUG: Call to SS_PrintRow(--,%d)\n", rowNum);
    if (rowNum >= ss->numRows || rowNum < 0) {
        printf("Row number (%d) is out of range\n", rowNum);
        return;
    }
    struct OneRow *rp = ss->firstRow;
    while(rowNum > 0 && rp != NULL) {
        rp = rp->nextRow;
        rowNum--;
    }
    if (rp == NULL) {
        printf("Row number (%d) is out of range??\n", rowNum);
        return;        
    }
    int k;
    for( k = 0 ; k<ss->numCols; k++ ) {
        if (k>0)
            printf(", ");
        printf("%s", rp->row[k]);
    }
    putchar('\n');
    return;
}

// The specified column must contain the textual representations of numbers
// (either integer or floating point). The sum of the numbers in the column
// is returned as a floating point number.
double SS_ColumnSum(SPREADSHEET *ss, int colNum) {
    if (debug)
        fprintf(stderr, "DEBUG: Call to SS_ColumnSum(--,%d)\n", colNum);
    
	if (colNum+1 > ss->numCols) {
		printf("-- specified column(%d) out of range\n", colNum);
		return -1;
	}
	double sum = 0.0;
    struct OneRow *rowIt = ss->firstRow;
	int i;
	for (i=0; i<ss->numRows; i++) {
		sum =+ atof(rowIt->row[colNum]);
		rowIt = rowIt->nextRow;
	}
    return sum;
}

double SS_ColumnAvg(SPREADSHEET *ss, int colNum) {
    if (debug)
        fprintf(stderr, "DEBUG: Call to SS_ColumnAvg(--,%d)\n", colNum);
    double sum = SS_ColumnSum(ss,colNum);
    return sum/ss->numRows;
}


// The strdup function is provided in many but not all variants of the
// C library. Here it is, renamed as mystrdup, just in case.
char *mystrdup(char *s) {
	int len = strlen(s);
	char *result = malloc(len+1);
	CHECKMALLOC(result);
	return strcpy(result, s);
}
