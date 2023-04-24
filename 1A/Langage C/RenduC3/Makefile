# Définition des Variables (à compléter)
CC =c99 # gcc dans mon windows
CFLAGS =-Wextra -pedantic -c 
LDFLAGS =-o
EXE =exemple_file test_file

# Règles (à compléter avec des variables automatiques)
all: $(EXE)

test_file: file.o test_file.o
	$(CC) $^ $(LDFLAGS) $@

exemple_file: file.o exemple_file.o
	$(CC) $^ $(LDFLAGS) $@

makedepend:
	makedepend *.c -Y.

clean:
	rm *.o $(EXE)

.PHONY: clean all makedepend

