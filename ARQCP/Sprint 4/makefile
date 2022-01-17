#Makefile

INCLUDES = 
SOURCES = main.c maindinamico.c 
OBJFILES = main.o maindinamico.o
EXEC = matrix

.SUFFIXES : .c .o

.c.o:
	gcc -Wall -g -c $<
	
.s.o:
	gcc -Wall -g -c $<
	
${EXEC}: ${OBJFILES}	
	gcc -Wall -g -o ${EXEC} ${OBJFILES}
	
${OBJFILES}: ${SOURCES} ${INCLUDES}

run: ${EXEC}
		./${EXEC}
		
clean:
		rm -f ${OBJFILES} ${EXEC}
		
debug: 
		gdb -tui ./${EXEC}	
		
vg: 
		valgrind ./${EXEC}		
