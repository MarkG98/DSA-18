import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


// this is our implementation of a rubiks cube. It is your job to use A* or some other search algorithm to write a
// solve() function
public class RubiksCube {

    private BitSet cube;
    private State initialState;
    private State solutionState;
    private int cost = 0;
    private boolean solved = false;
    private char[] turns = {'u', 'U', 'r', 'R', 'f', 'F'};

    private class State{
        private RubiksCube rubiksCube;
        private int moves;
        private double cost;
        private State prevState;
        private char turn;

        public State(RubiksCube rubiksCube, int moves, State prevState, char turn)
        {
            this.rubiksCube = rubiksCube;
            this.moves = moves;
            this.prevState = prevState;
            this.turn = turn;
            this.cost = (double)moves + this.rubiksCube.manhattan3D();
        }

        public boolean equals(State s) {
            if (s == this) return true;
            if (s == null) return false;
            return (s.rubiksCube.equals(this.rubiksCube));
        }
    }

    // initialize a solved rubiks cube
    public RubiksCube() {
        // 24 colors to store, each takes 3 bits
        cube = new BitSet(24 * 3);
        for (int side = 0; side < 6; side++) {
            for (int i = 0; i < 4; i++) {
                setColor(side * 4 + i, side);
            }
        }
    }

    // initialize a rubiks cube with the input bitset
    private RubiksCube(BitSet s) {
        cube = (BitSet) s.clone();
    }

    // creates a copy of the rubics cube
    public RubiksCube(RubiksCube r) {
        cube = (BitSet) r.cube.clone();
    }

    // return true if this rubik's cube is equal to the other rubik's cube
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof RubiksCube))
            return false;
        RubiksCube other = (RubiksCube) obj;
        return other.cube.equals(cube);
    }

    /**
     * return a hashCode for this rubik's cube.
     *
     * Your hashCode must follow this specification:
     *   if A.equals(B), then A.hashCode() == B.hashCode()
     *
     * Note that this does NOT mean:
     *   if A.hashCode() == B.hashCode(), then A.equals(B)
     */
    @Override
    public int hashCode() {
        return cube.hashCode();
    }

    public boolean isSolved() {
        return this.equals(new RubiksCube());
    }


    // takes in 3 bits where bitset.get(0) is the MSB, returns the corresponding int
    private static int bitsetToInt(BitSet s) {
        int i = 0;
        if (s.get(0)) i |= 4;
        if (s.get(1)) i |= 2;
        if (s.get(2)) i |= 1;
        return i;
    }

    // takes in a number 0-5, returns a length-3 bitset, where bitset.get(0) is the MSB
    private static BitSet intToBitset(int i) {
        BitSet s = new BitSet(3);
        if (i % 2 == 1) s.set(2, true);
        i /= 2;
        if (i % 2 == 1) s.set(1, true);
        i /= 2;
        if (i % 2 == 1) s.set(0, true);
        return s;
    }
    // 0 1 2 3     4 5 6 7   8 9 10 11
    // index from 0-23, color from 0-5
    private void setColor(int index, int color) {
        BitSet colorBitset = intToBitset(color);
        for (int i = 0; i < 3; i++)
            cube.set(index * 3 + i, colorBitset.get(i));
    }


    // index from 0-23, returns a number from 0-5
    private int getColor(int index) {
        return bitsetToInt(cube.get(index * 3, (index + 1) * 3));
    }

    // given a list of rotations, return a rubik's cube with the rotations applied
    public RubiksCube rotate(List<Character> c) {
        RubiksCube rub = this;
        for (char r : c) {
            rub = rub.rotate(r);
        }
        return rub;
    }


    // Given a character in ['u', 'U', 'r', 'R', 'f', 'F'], return a new rubik's cube with the rotation applied
    // Do not modify this rubik's cube.
    public RubiksCube rotate(char c) {
        int[] faceFrom = null;
        int[] faceTo = null;
        int[] sidesFrom = null;
        int[] sidesTo = null;
        // colors move from the 'from' variable to the 'to' variable
        switch (c) {
            case 'u': // clockwise
            case 'U': // counterclockwise
                faceFrom = new int[]{0, 1, 2, 3};
                faceTo = new int[]{1, 2, 3, 0};
                sidesFrom = new int[]{4, 5, 8, 9, 17, 16, 21, 20};
                sidesTo = new int[]{21, 20, 4, 5, 8, 9, 17, 16};
                break;
            case 'r':
            case 'R':
                faceFrom = new int[]{8, 9, 10, 11};
                faceTo = new int[]{9, 10, 11, 8};
                sidesFrom = new int[]{6, 5, 2, 1, 17, 18, 13, 14};
                sidesTo = new int[]{2, 1, 17, 18, 13, 14, 6, 5};
                break;
            case 'f':
            case 'F':
                faceFrom = new int[]{4, 5, 6, 7};
                faceTo = new int[]{5, 6, 7, 4};
                sidesFrom = new int[]{3, 2, 8, 11, 14, 15, 23, 20};
                sidesTo = new int[]{8, 11, 14, 15, 23, 20, 3, 2};
                break;
            default:
                System.out.println(c);
                assert false;
        }
        // if performing a counter-clockwise rotation, swap from and to
        if (Character.isUpperCase(c)) {
            int[] temp;
            temp = faceFrom;
            faceFrom = faceTo;
            faceTo = temp;
            temp = sidesFrom;
            sidesFrom = sidesTo;
            sidesTo = temp;
        }
        RubiksCube res = new RubiksCube(cube);
        for (int i = 0; i < faceFrom.length; i++) res.setColor(faceTo[i], this.getColor(faceFrom[i]));
        for (int i = 0; i < sidesFrom.length; i++) res.setColor(sidesTo[i], this.getColor(sidesFrom[i]));
        return res;
    }

    // returns a random scrambled rubik's cube by applying random rotations
    public static RubiksCube scrambledCube(int numTurns) {
        RubiksCube r = new RubiksCube();
        char[] listTurns = getScramble(numTurns);
        for (int i = 0; i < numTurns; i++) {
            r= r.rotate(listTurns[i]);
        }
        return r;
    }

    public static char[] getScramble(int size){
        char[] listTurns = new char[size];
        for (int i = 0; i < size; i++) {
            switch (ThreadLocalRandom.current().nextInt(0, 6)) {
                case 0:
                    listTurns[i] = 'u';
                    break;
                case 1:
                    listTurns[i] = 'U';
                    break;
                case 2:
                    listTurns[i] = 'r';
                    break;
                case 3:
                    listTurns[i] = 'R';
                    break;
                case 4:
                    listTurns[i] = 'f';
                    break;
                case 5:
                    listTurns[i] = 'F';
                    break;
            }
        }
        return listTurns;
    }



    public double manhattan3D() {
        double sum = 0;
        for (int i = 0; i < 24; i ++) {
            int toFace = this.getColor(i);
            int onFace = i/4;

            if (toFace == onFace) {
                sum += 0;
            } else if (!(Math.abs(toFace - onFace) == 3)) { // not opposite
                sum += 1;
            } else {
                sum += 2;
            }
        }

        //System.out.println(sum/8);
        return sum/8;
    }

    public ArrayList<RubiksCube> neighbors()
    {
        ArrayList<RubiksCube> n = new ArrayList<>();
        for (int i = 0; i < turns.length; i++) {
            n.add(this.rotate(turns[i]));
        }
        return n;
    }


    private List<Character> getTurns(State solutionState)
    {
        LinkedList<Character> turns = new LinkedList<>();
        while (solutionState.prevState.turn != 'x')
        {
            turns.addFirst(solutionState.turn);
            solutionState = solutionState.prevState;
        }
        turns.addFirst(solutionState.turn);

        System.out.println(turns);
        return turns;
    }

    public List<Character> solve() {

        this.initialState = new State(this, 0, null,'x');
        this.initialState.cost = 0;

        ArrayList<State> open = new ArrayList<>();
        ArrayList<State> closed = new ArrayList<>();

        open.add(initialState);

        while (!open.isEmpty() && !solved)

        {
            int minI = 0;
            for (int i = 0; i < open.size(); i++) {
                if (open.get(i).cost < open.get(minI).cost)
                {
                    minI = i;
                }
            }

            State q = open.remove(minI);

            for (int i = 0; i < q.rubiksCube.neighbors().size(); i++) {

                State uState = new State (q.rubiksCube.neighbors().get(i),q.moves + 1, q, turns[i]);

                if (uState.rubiksCube.isSolved())
                {
                    solutionState = uState;
                    solved = true;
                }


                boolean ignore = false;

                for (State n : open)
                {
                    if (n.equals(uState) && (n.cost < uState.cost))
                    {
                        ignore = true;
                    }
                }
                for (State n : closed)
                {
                    if (n.equals(uState) && (n.cost < uState.cost))
                    {
                        ignore = true;
                    }
                }

                if (ignore == false)
                {
                    open.add(uState);
                    uState.prevState = q;
                }
            }
            closed.add(q);
        }
        return getTurns(solutionState);
    }
}