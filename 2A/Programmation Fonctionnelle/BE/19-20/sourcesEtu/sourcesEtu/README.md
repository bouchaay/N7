orrobot Ocaml Ricochet Robot
============================

Requirements
------------

Requires OCaml >= 4.04 and dune >= 1.6.

Compilation
-----------

% make

Usage
-----

The board and initial position of the robots are described in a file
./board.lp. A default board is provided as an example.

% ./orrobot  # in the directory containing board.lp

This will ask for the first target, then compute a solution and offer
to solve another target from the resulting position.

Type C-c or C-d to exit.
