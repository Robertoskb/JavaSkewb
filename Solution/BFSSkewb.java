package br.com.javaskewb.core.Solution;

import br.com.javaskewb.core.Mapping.Moves.FLMoves;
import br.com.javaskewb.core.Mapping.Moves.Moves;
import br.com.javaskewb.core.Mapping.Moves.WCAMoves;
import br.com.javaskewb.core.Cube.State;
import br.com.javaskewb.core.Solution.utils.Scramble;
import br.com.javaskewb.core.Solution.utils.StateNode;

import java.util.*;

public class BFSSkewb {
    public static HashMap<State, Scramble> BFS(int max, State initialState, Moves moves){
        PriorityQueue<StateNode> queue = new PriorityQueue<>(Comparator.comparingInt(StateNode::getDistance));
        HashMap<State, Scramble> bfs = new HashMap<>();

        bfs.put(initialState, new Scramble());
        queue.add(new StateNode(initialState, 0));
        HashSet<State> visited = new HashSet<>(State.generatePerspectivesStates(initialState));

        Set<String> notation = moves.getNotation().keySet();

        while (!queue.isEmpty()){
            StateNode node = queue.poll();

            State state = node.getState();
            int distance = node.getDistance();
            Scramble scramble = node.getScramble();

            if (distance >= max)
                continue;

            moves.setState(state);

            for (String move: notation){
                State newState = moves.applyMove(move);
                Scramble newScramble = new Scramble(scramble);
                newScramble.add(move);

                if (!visited.contains(newState)){
                    bfs.put(newState, newScramble);
                    visited.addAll(State.generatePerspectivesStates(newState));

                    queue.add(new StateNode(newState, distance+1, newScramble));

                }
            }
        }

        return bfs;
    }

    public static HashMap<State, Scramble> BFS(int max){
        return BFS(max, State.getSolvedState(), new WCAMoves());
    }

    public static ArrayList<ArrayList<Scramble>> getFLScrambles(Moves moves){
        ArrayList<ArrayList<Scramble>> scrambles = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            scrambles.add(new ArrayList<>());
        }

        State state = State.getSolvedState();

        state.maskSide(3);

        HashMap<State, Scramble> bfs = BFS(8, state, moves);

        for (Scramble scramble: bfs.values())
            scrambles.get(scramble.size()).add(scramble);

        return scrambles;
    }

    public static ArrayList<ArrayList<Scramble>> getFLEG2Scrambles(Moves moves){
        ArrayList<ArrayList<Scramble>> scrambles = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            scrambles.add(new ArrayList<>());
        }

        State state = State.getSolvedState();

        state.maskFace(3);

        HashMap<State, Scramble> bfs = BFS(8, state, moves);

        for (Scramble scramble: bfs.values())
            scrambles.get(scramble.size()).add(scramble);

        return scrambles;
    }

    public static void main(String[] args) {
        State state = State.getSolvedState();

        ArrayList<ArrayList<Scramble>> bfs1 = getFLScrambles(new FLMoves());
        ArrayList<ArrayList<Scramble>> bfs2 = getFLEG2Scrambles(new FLMoves());

        for (int i = 0; i < 8; i++) {
            System.out.println(i + " Moves: " + bfs1.get(i).size() + " -> " + bfs2.get(i).size());
        }

    }
}
