/**
 * Solver definition for the 8 Puzzle challenge
 * Construct a tree of board states using A* to find a path to the goal
 */

import java.sql.SQLOutput;
import java.util.*;

public class Solver {

    public int minMoves = -1;
    private State initialState;
    private State solutionState;
    private boolean solved = false;
    int a = 0;

    /**
     * State class to make the cost calculations simple
     * This class holds a board state and all of its attributes
     */
    private class State {
        // Each state needs to keep track of its cost and the previous state
        private Board board;
        private int moves; // equal to g-cost in A*
        public int cost; // equal to f-cost in A*
        private State prev;

        public State(Board board, int moves, State prev) {
            this.board = board;
            this.moves = moves;
            this.prev = prev;
            // TODO
            this.cost = moves + this.board.manhattan();
        }

        @Override
        public boolean equals(Object s) {
            if (s == this) return true;
            if (s == null) return false;
            if (!(s instanceof State)) return false;
            return ((State) s).board.equals(this.board);
        }

    }

    /*
     * Return the root state of a given state
     */
    private State root(State state) {
        // TODO: Your code here
        if(state != null) {
            while(state.prev != null) {
                return root(state.prev);
            }
        }
        return state;
    }

    /*
     * A* Solver
     * Find a solution to the initial board using A* to generate the state tree
     * and a identify the shortest path to the the goal state
     */
    public Solver(Board initial) {
        // TODO: Your code here

        this.initialState = new State(initial, 0, null);
        this.initialState.cost = 0;

        if (isSolvable()) { // If the initial board is solvable

            ArrayList<State> open = new ArrayList<>();
            ArrayList<State> closed = new ArrayList<>();


            open.add(initialState);



            while (!open.isEmpty() && !solved) {
                //System.out.println(a++);
                int minI = 0;
                for (int i = 0; i < open.size(); i++) { // Get minimum cost state of all current states in open
                    if (open.get(i).cost < open.get(minI).cost) {
                        minI = i;
                    }
                }

                State q = open.remove(minI);

                for (Board u : q.board.neighbors()) { // For all neighbors of minimum cost state q

                    State uState = new State(u, q.moves + 1, q);

                    if(u.isGoal()) { // If a solution has been reached, exit
                        solutionState = uState;
                        solved = true;
                    }


                        // If the current state is found in either visited or unvisited sets, but has lower cost, we can ignore current element
                        boolean ignore = false;
                        for (State n : open) {
                            if (n.equals(uState) && (n.cost < uState.cost)) {
                                ignore = true;
                            }
                        }
                        for (State n : closed) {
                            if (n.equals(uState) && (n.cost < uState.cost)) {
                                ignore = true;
                            }
                        }

                        if (!ignore) {
                            open.add(uState); // If we have the lowest cost version of the current state, add it to the list of explored states
                            uState.prev = q;
                        }


                }

                closed.add(q);

            }
            minMoves = solutionState.moves;
        } else {
            solutionState = null;
        }
    }

    /*
     * Is the input board a solvable state
     * Research how to check this without exploring all states
     */
    public boolean isSolvable() {
        // TODO: Your code here
        return initialState.board.solvable();
    }

    /*
     * Return the sequence of boards in a shortest solution, null if unsolvable
     */
    public Iterable<Board> solution() {
        // TODO: Your code here
        if (solutionState == null)
            return null;
        LinkedList<Board> solutionPath = new LinkedList<>();
        while (solutionState.prev != null)
        {
            solutionPath.addFirst(solutionState.board);
            solutionState = solutionState.prev;
        }
        solutionPath.addFirst(solutionState.board);
        return solutionPath;

    }

    public State find(Iterable<State> iter, Board b) {
        for (State s : iter) {
            if (s.board.equals(b)) {
                return s;
            }
        }
        return null;
    }

    /*
     * Debugging space
     */
    public static void main(String[] args) {
        int[][] initState = {{0, 3, 5}, {2, 1, 8}, {4, 7, 6}};

        Board initial = new Board(initState);

        Solver solver = new Solver(initial);
    }


}


