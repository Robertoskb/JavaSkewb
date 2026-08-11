package br.com.javaskewb.core.Solution;

import br.com.javaskewb.core.Mapping.Moves.Moves;
import br.com.javaskewb.core.Mapping.Moves.WCAMoves;
import br.com.javaskewb.core.Cube.State;
import br.com.javaskewb.core.Solution.utils.Scramble;
import br.com.javaskewb.core.Solution.utils.StateNode;

import java.util.*;

public class BFSSkewb {
    public static HashMap<State, Scramble> BFS(int max, State initialState, Moves moves){
        PriorityQueue<StateNode> queue = new PriorityQueue<>(Comparator.comparingInt(StateNode::getDistance));
        HashMap<State, Scramble> visited = new HashMap<>();

        queue.add(new StateNode(initialState, 0));
        visited.put(initialState, new Scramble());

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

                if (!visited.containsKey(newState)){
                    visited.put(newState, newScramble);

                    queue.add(new StateNode(newState, distance+1, newScramble));
                }
            }
        }

        return visited;
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

        HashMap<State, Scramble> bfs = BFS(7, state, moves);

        for (Scramble scramble: bfs.values())
            scrambles.get(scramble.size()).add(scramble);

        return scrambles;
    }

    public static void main(String[] args) {


    }
}
