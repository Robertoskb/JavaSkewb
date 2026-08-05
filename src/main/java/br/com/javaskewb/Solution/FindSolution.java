package br.com.javaskewb.Solution;

import br.com.javaskewb.Mapping.Solve.Moves;
import br.com.javaskewb.Mapping.Solve.WCAMoves;
import br.com.javaskewb.Mapping.State;
import br.com.javaskewb.Solution.utils.StateNode;

import java.lang.reflect.Array;
import java.util.*;

public class FindSolution {
    private Moves moves;
    private ArrayList<State> targetStates;

    public FindSolution(Moves moves, ArrayList<State> targetStates){
        setMoves(moves);
        setTargetStates(targetStates);
    }

    public FindSolution(){
        setMoves(new WCAMoves());
        setTargetStates(new ArrayList<>(List.of(State.getSolvedState())));
    }

    public ArrayList<String> find(State initialState, ArrayList<State> targetStates){
        HashMap<State, ArrayList<String>> visitedInitial, visitedTarget;
        PriorityQueue<StateNode> queueInitial, queueTarget;

        visitedInitial = new HashMap<>();
        visitedInitial.put(initialState, new ArrayList<>());

        queueInitial = new PriorityQueue<>(Comparator.comparingInt(StateNode::getDistance));
        queueInitial.add(new StateNode(initialState, 0));

        visitedTarget = new HashMap<>();

        queueTarget = new PriorityQueue<>(Comparator.comparingInt(StateNode::getDistance));

        for (State targetState: targetStates){
            queueTarget.add(new StateNode(targetState, 0));
            visitedTarget.put(targetState, new ArrayList<>());
        }

        while (!queueInitial.isEmpty() || !queueTarget.isEmpty()){
            if (!queueInitial.isEmpty()){
                StateNode stateNodeInitial = queueInitial.poll();

                moves.setState(stateNodeInitial.getState());
                int distance = stateNodeInitial.getDistance();
                ArrayList<String> scramble = stateNodeInitial.getScramble();

                for (String move: moves.getNotation().keySet()){
                    State state = moves.applyMove(move);

                    if (!visitedInitial.containsKey(state)){
                        ArrayList<String> newScramble = new ArrayList<>(scramble);
                        newScramble.add(move);
                        if (visitedTarget.containsKey(state))
                            return scrambleConstructor(newScramble, visitedTarget.get(state));

                        visitedInitial.put(state, newScramble);
                        queueInitial.add(new StateNode(state, distance+ moves.getCost(move), newScramble));
                    }

                }

            }

            if (!queueTarget.isEmpty()){
                StateNode stateNodeTarget = queueTarget.poll();

                moves.setState(stateNodeTarget.getState());
                int distance = stateNodeTarget.getDistance();
                ArrayList<String> scramble = stateNodeTarget.getScramble();

                for (String move: moves.getNotation().keySet()){
                    State state = moves.applyMove(move);

                    if (!visitedTarget.containsKey(state)){
                        ArrayList<String> newScramble = new ArrayList<>(scramble);
                        newScramble.add(move);

                        if (visitedInitial.containsKey(state))
                            return scrambleConstructor(visitedInitial.get(state), newScramble);

                        visitedTarget.put(state, newScramble);
                        queueTarget.add(new StateNode(state, distance+ moves.getCost(move), newScramble));
                    }
                }

            }

        }

        return null;
    }

    public ArrayList<String> find(State initialState){
        return find(initialState, targetStates);
    }

    public static ArrayList<String> invertScramble(ArrayList<String> scramble){
        ArrayList<String> newScramble = new ArrayList<>();

        List<String> noInvert = Arrays.stream("x2 y2 z2".split(" ")).toList();

        for (String move: scramble.reversed())
            if (move.contains("'"))
                newScramble.add(move.replace("'", ""));
            else if (!noInvert.contains(move))
                newScramble.add(move + "'");
            else
                newScramble.add(move);

        return newScramble;

    }

    public static ArrayList<String> scrambleConstructor(ArrayList<String> initial, ArrayList<String> target){
        ArrayList<String> scramble = new ArrayList<>(initial);

        scramble.addAll(invertScramble(target));

        return scramble;
    }

    public Moves getMoves() {
        return moves;
    }

    public void setMoves(Moves moves) {
        this.moves = moves;
    }

    public ArrayList<State> getTargetStates() {
        return targetStates;
    }

    public void setTargetStates(ArrayList<State> targetStates) {
        this.targetStates = targetStates;
    }
}
