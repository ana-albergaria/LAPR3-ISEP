#ifndef ASM_H
#define ASM_H
#include <stdio.h>
#include <stdlib.h>
typedef struct{
	int container_id;
	short position_x;
	short position_y;
	short position_z;
	char refrigerated;
} Container; 
void fill_array(FILE *file, Container containers_array[], int array_size);
void print_array(Container containers_array[], int array_size);
Container* get_pointer(FILE *file);
#endif