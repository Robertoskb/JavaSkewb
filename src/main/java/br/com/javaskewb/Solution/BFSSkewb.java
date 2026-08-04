package br.com.javaskewb.Solution;

import br.com.javaskewb.Mapping.Solve.WCAMoves;
import br.com.javaskewb.Mapping.State;
import br.com.javaskewb.Solution.utils.StateNode;

import java.util.*;

public class BFSSkewb {
    public static HashSet<State> BFS(int max, State initialState){
        PriorityQueue<StateNode> queue = new PriorityQueue<>(Comparator.comparingInt(StateNode::getDistance));
        HashSet<State> visited = new HashSet<>();

        queue.add(new StateNode(initialState, 0));
        visited.add(initialState);

        WCAMoves moves = new WCAMoves();

        while (!queue.isEmpty()){
            StateNode node = queue.poll();

            State state = node.getState();
            int distance = node.getDistance();

            if (distance >= max)
                continue;

            moves.setState(state);

            for (String move: moves.getNotation().keySet()){
                State newState = moves.applyMove(move);

                if (!visited.contains(newState)){
                    visited.add(state);

                    queue.add(new StateNode(newState, distance+1));
                }
            }
        }

        return visited;
    }

    public static HashSet<State> BFS(int max){
        return BFS(max, State.getSolvedState());
    }

    public static void main(String[] args) {
        State state = State.getSolvedState();

        HashSet<State> bfs = BFS(6, state);

        System.out.println(bfs.size());

    }
}
