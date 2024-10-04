# fun01labyrinth
Simple java project about finding the exit in a 0-1 text file labyrinth.

Labyrinth is given as a text file of 0 and 1, where 0 is considered path, 1 is considered wall, Ε is the entrance point in the labyrinth.

About the program:
	Ex, Ey the entrance coordinates,x, y the current coordinates of the player searching for the exit.
	While (exitNotFound) loop: the player starts walking in the labyrinth, checking (in this order) right, down, left and upper cell if it can move. If it can move it proceeds to this direction and at every cell it looks again right, down, left, up if it can move. As long it can the pathStack is being pushed with the cells visited and the cells visited are marked as  CFL, CFU, CFR, CFD showing the direction from where the player came to this cell (so it can backtrack to its previous one if it finds a dead end). If dead end is found, the player returns to its previous cell (current cell is popped out of the pathStack) and checks the rest of its options (e.g. if at the previous cell it went down, then the next choice is going left). Every time the player backtracks-returns to a previous cell because it finds a dead end, before leaving the current cell it blocks the cell by marking it as a 1-valued cell (Wall) so it may never be chosen again. 
	c81-visual example.txt and c81-stack.txt visualize the way the player moves inside the labyrinth file c81.txt. The  c81-visual example.txt shows the path of the player beginning from first (0) line and the forth (3) row and the c81-stack.txt shows how the stack works while this is happening.

 TL;DR
	You can create your own 0 and 1 labyrinths and let the pc find a way out of them. Just keep in mind the “Readme about the labyrinth files” txt. See the "running it with cmd" png. 
 Have fun ! 😁😁😁
