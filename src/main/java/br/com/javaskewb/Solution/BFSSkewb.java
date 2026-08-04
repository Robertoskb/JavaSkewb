package br.com.javaskewb.Solution;

import br.com.javaskewb.Mapping.Solve.WCAMoves;
import br.com.javaskewb.Mapping.State;
import br.com.javaskewb.Solution.utils.StateNode;

import java.util.*;

public class BFSSkewb {
    public static HashMap<State, ArrayList<String>> BFS(int max, State initialState){
        PriorityQueue<StateNode> queue = new PriorityQueue<>(Comparator.comparingInt(StateNode::getDistance));
        HashMap<State, ArrayList<String>> visited = new HashMap<>();

        queue.add(new StateNode(initialState, 0));
        visited.put(initialState, new ArrayList<>());

        WCAMoves moves = new WCAMoves();

        while (!queue.isEmpty()){
            StateNode node = queue.poll();

            State state = node.getState();
            int distance = node.getDistance();
            ArrayList<String> scramble = node.getScramble();

            if (distance >= max)
                continue;

            moves.setState(state);

            for (String move: moves.getNotation().keySet()){
                State newState = moves.applyMove(move);

                if (!visited.containsKey(newState)){
                    ArrayList<String> newScramble = new ArrayList<>(scramble);
                    newScramble.add(move);
                    visited.put(state, newScramble);

                    queue.add(new StateNode(newState, distance+1, newScramble));
                }
            }
        }

        return visited;
    }

    public static HashMap<State, ArrayList<String>> BFS(int max){
        return BFS(max, State.getSolvedState());
    }

    public static void main(String[] args) {
        State state = State.getSolvedState();

        HashMap<State, ArrayList<String>> bfs = BFS(9, state);

        System.out.println(bfs.size());

    }
}
