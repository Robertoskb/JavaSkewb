package br.com.javaskewb.Solution;

import br.com.javaskewb.Mapping.Solve.WCAMoves;
import br.com.javaskewb.Mapping.State;

import java.util.*;

public class BFSSkewb {
    private static final List<String> moves = Arrays.asList("R R' L L' B B' U U'".split(" "));

    public static List<Set<State>> BFS(int max, State initialState){

        List<Set<State>> stateDistances = new ArrayList<>();
        ArrayList<State> visited = new ArrayList<>();
        visited.add(initialState);

        stateDistances.add(new HashSet<>());

        stateDistances.getFirst().add(initialState);

        PriorityQueue<Map.Entry<State, Integer>> queue = new PriorityQueue<>(
                Comparator.comparingInt(Map.Entry::getValue)
        );

        queue.add(new AbstractMap.SimpleEntry<>(initialState, 0));

        while (!queue.isEmpty()){
            State currentState;
            int newDistance;

            Map.Entry<State, Integer> pair = queue.poll();

            currentState = pair.getKey();
            newDistance = pair.getValue() + 1;

            if (newDistance > max)
                continue;

            if (stateDistances.size() <= newDistance)
                stateDistances.add(new HashSet<>());

            WCAMoves wcaMoves = new WCAMoves(currentState, false);

            visited.add(currentState);

            for (String move: moves){
                State newState = wcaMoves.applyMove(move);

                if (!visited.contains(newState)){
                    queue.add(new AbstractMap.SimpleEntry<>(newState, newDistance));
                    stateDistances.get(newDistance).add(newState);
                    visited.add(newState);
                }
            }
        }

        return stateDistances;
    }

    public static List<Set<State>> BFS(int max){
        return BFS(max, State.getSolvedState());
    }
}
