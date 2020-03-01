#!/bin/gprolog --consult-file

:- include('data.pl').
:- include('uniq.pl').

% Your code gGREATEREs here.

lte(time(_,_,am),time(_,_,pm)).

lte(time(H1,_,AP),time(H2,_,AP))
        :-H1<H2.

lte(time(H,M1,AP),time(H,M2,AP))
        :-M1=<M2.

same(slot(GREATERS,GREATERE),slot(LESSERS,LESSERE),slot(LESSERS,LESSERE))
        :-lte(GREATERS,LESSERS),lte(LESSERS,GREATERE),lte(LESSERE,GREATERE),LESSERS\==LESSERE.


same(slot(GREATERS,GREATERE),slot(LESSERS,LESSERE),slot(LESSERS,GREATERE))
        :-lte(GREATERS,LESSERS),lte(LESSERS,GREATERE),lte(GREATERE,LESSERE),LESSERS\==GREATERE.

shared(S1,S2,S3)
        :-same(S1,S2,S3).
shared(S1,S2,S3)
        :-same(S2,S1,S3).

meet0(Slot,Slot,[]).

meet0(S1,S2,[H|T])
        :-free(H,Free), shared(S1,Free,RetVal), meet0(RetVal,S2,T).

meetTime(S2,[H|T])
        :-free(H,Slot), meet0(Slot, S2,T).

meet(Slot):-people(People),meetTime(Slot,People).

people([ann,bob,carla]).

main :- findall(Slot, meet(Slot), Slots),
        uniq(Slots, Uniq),
        write(Uniq), nl, halt.

:- initialization(main).
