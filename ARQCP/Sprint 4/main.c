#include "main.h"
#include <stdio.h>
#include <stdlib.h>

int array_size;
int maxX, maxY, maxZ;	
int *ptr;

int main(void) {
    FILE *file = fopen("manifest2.txt", "r");
    Container *pointer = NULL;
    pointer = get_pointer(file);

    // printf("id: %d\n", pointer->container_id);
    // printf("x: %d\n", pointer->position_x);
    // printf("y: %d\n", pointer->position_y);
    // printf("z: %d\n", pointer->position_z);
    // printf("Refrigerated: %d\n", pointer->refrigerated);
}

