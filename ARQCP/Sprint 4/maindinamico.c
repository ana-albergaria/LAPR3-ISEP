#include <stdio.h>
#include <stdlib.h>
#include "main.h"

//US 409:::
extern int maxX, maxY, maxZ;

void fill_array(FILE *file, Container containers_array[], int array_size){							//method to extract information from cargo manifest and fill the array
	int id =0;
	
	int x=0, y=0, z=0, i=0;
	int is_refrigerated=0;
	
	while(fscanf(file, "id: %d\t\tx: %d\t\ty: %d\t\tz: %d\t\ttemperature: %d\t\t", &id, &x, &y, &z, &is_refrigerated) != EOF){			//loop to search for the containers information to add to the array
		containers_array[i].container_id=id;
		containers_array[i].position_x=x;
		containers_array[i].position_y=y;
		containers_array[i].position_z=z;
		containers_array[i].refrigerated=is_refrigerated;
		i++;
	};
}

Container* get_pointer(FILE *file){						//method to get the pointer of the array
	
	if(!file) {																						//check to see if file exists
		printf("File not found!");
		exit(-1);
	}
	
	fscanf(file, "ship capacity -> x: %d\t\ty: %d\t\tz: %d\t\t", &maxX, &maxY, &maxZ);				//extract information related to the ship's size	
	
	
	int array_size = maxX*maxY*maxZ;
	Container *ptr = NULL;
	
	ptr = (Container *) malloc(array_size * sizeof(Container));															//calculate array size using the max of each coordinate and adding the necessary memory slots
																									//for each value of the struct
	printf("Total array size: %d", array_size);
	
	
	fill_array(file, ptr, array_size);																//call for method to fill the array
	
	print_array(ptr, array_size);  /*call for method to print the matrix*/
	
	return ptr;																	//return pointer of array
}

void print_array(Container containers_array[], int array_size){							//method to print array
	printf("\n");
	printf("ARRAY SIZE: %d\n", array_size);

	for(int j=0; j<array_size; j++){
		printf("id: %d\n", (containers_array+j)->container_id);
		printf("x: %d\n", (containers_array+j)->position_x);
		printf("y: %d\n", (containers_array+j)->position_y);
		printf("z: %d\n", (containers_array+j)->position_z);
		printf("Refrigerated: %d\n", (containers_array+j)->refrigerated);
		printf("ARRAY SIZE: %d\n", j);
		printf("\n");
	}
	
}

