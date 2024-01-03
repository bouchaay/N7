dim(1..16).

barrier(3, 3, -1, 0).
barrier(11, 11, -1, 0).
barrier(13, 3, -1, 0).
barrier(12, 4, 0, -1).
barrier(12, 15, -1, 0).
barrier(13, 1, -1, 0).
barrier(15, 10, -1, 0).
barrier(10, 9, -1, 0).
barrier(2, 3, 0, -1).
barrier(8, 9, -1, 0).
barrier(14, 16, -1, 0).
barrier(4, 7, -1, 0).
barrier(13, 12, -1, 0).
barrier(10, 12, 0, -1).
barrier(16, 7, 0, -1).
barrier(9, 8, 0, -1).
barrier(3, 10, -1, 0).
barrier(2, 16, 0, -1).
barrier(8, 10, 0, -1).
barrier(6, 10, 0, -1).
barrier(7, 5, 0, -1).
barrier(4, 8, 0, -1).
barrier(1, 13, 0, -1).
barrier(6, 14, -1, 0).
barrier(14, 4, 0, -1).
barrier(9, 10, 0, -1).
barrier(5, 2, 0, -1).
barrier(11, 5, -1, 0).
barrier(11, 6, 0, -1).
barrier(8, 4, -1, 0).
barrier(9, 14, -1, 0).
barrier(3, 15, -1, 0).
barrier(8, 8, 0, -1).
barrier(15, 4, -1, 0).
barrier(16, 13, 0, -1).
barrier(13, 6, 0, -1).
barrier(5, 2, -1, 0).
barrier(13, 12, 0, -1).
barrier(3, 10, 0, -1).
barrier(15, 11, 0, -1).
barrier(6, 9, -1, 0).
barrier(8, 8, -1, 0).
barrier(5, 14, 0, -1).
barrier(3, 1, -1, 0).
barrier(10, 8, -1, 0).
barrier(1, 7, 0, -1).
barrier(7, 16, -1, 0).
barrier(9, 14, 0, -1).
barrier(11, 15, 0, -1).
barrier(13, 6, -1, 0).

available_target(yellow, star, 7, 4).
available_target(yellow, moon, 14, 4).
available_target(red, saturn, 12, 3).
available_target(green, star, 11, 5).
available_target(red, moon, 5, 2).
available_target(green, saturn, 10, 11).
available_target(blue, moon, 11, 15).
available_target(green, sun, 2, 3).
available_target(blue, star, 5, 14).
available_target(green, moon, 3, 10).
available_target(yellow, sun, 15, 10).
available_target(blue, saturn, 4, 7).
available_target(yellow, saturn, 6, 9).
available_target(red, star, 13, 12).
available_target(red, sun, 2, 15).
available_target(blue, sun, 13, 6).
available_target(any, sun, 9, 14).

robot(red;green;blue;yellow;black).
initial_pos(blue, 7, 16).
initial_pos(black, 5, 6).
initial_pos(green, 9, 2).
initial_pos(yellow, 15, 10).
initial_pos(red, 9, 4).
