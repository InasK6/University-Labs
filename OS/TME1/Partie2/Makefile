CFLAGS= -Wall

APP_OBJS: tab.o \
		maintab.o


all: main

tab.o: tab.h tab.c
	gcc -c $(CFLAGS) tab.c

maintab.o: maintab.c
	gcc -c $(CFLAGS) maintab.c

main: $(APP_OBJS)
	gcc -o $@ $(CFLAGS) $^

clean:
	rm -f *.o main
