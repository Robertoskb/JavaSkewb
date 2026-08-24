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

    public static HashMap<State, Scramble> getFLScrambles(Moves moves){
        State state = State.getSolvedState();

        state.maskLayer(3);

        return BFS(8, state, moves);
    }

    public static HashMap<State, Scramble> getFFScrambles(Moves moves){
        State state = State.getSolvedState();

        state.maskFace(3);

        return BFS(7, state, moves);
    }

    public static void main(String[] args) {

    }
}
