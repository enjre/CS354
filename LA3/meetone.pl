#!/bin/gprolog --consult-file
/*
Andre Maldonado
CS354
LA3
*/
:- include('data.pl').

% Your code gGREATEREs here.

%check if am or pm
lte(time(_,_,am),time(_,_,pm)).

%checkl hrs
lte(time(H1,_,AP),time(H2,_,AP))
    :-H1<H2.

%check mins
lte(time(H,M1,AP),time(H,M2,AP))
    :-M1=<M2.


timein(slot(LESSERS,LESSERE),slot(GREATERS,GREATERE))
    :-lte(GREATERS,LESSERS),lte(LESSERE,GREATERE).


meetone(Person,MeetSlot)
    :-free(Person,FreeSlot),timein(MeetSlot,FreeSlot).


main :- findall(Person,
		meetone(Person,slot(time(8,30,am),time(8,45,am))),
		People),
	write(People), nl, halt.

:- initialization(main).