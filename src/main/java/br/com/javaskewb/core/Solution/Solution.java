package br.com.javaskewb.core.Solution;

import br.com.javaskewb.core.Mapping.Moves.AdvancedMoves;
import br.com.javaskewb.core.Mapping.Moves.Moves;
import br.com.javaskewb.core.Mapping.Moves.WCAMoves;
import br.com.javaskewb.core.Cube.State;
import br.com.javaskewb.core.Solution.utils.Scramble;
import br.com.javaskewb.core.Solution.utils.StateNode;

import java.util.*;

public class Solution {
    private Moves moves;
    private ArrayList<State> targetStates;

    public Solution(Moves moves, ArrayList<State> targetStates){
        setMoves(moves);
        setTargetStates(targetStates);
    }

    public Solution(Moves moves){
        setMoves(moves);
    }

    public Solution(){
        setMoves(new WCAMoves());
    }

    public Scramble findSolution(State initialState, ArrayList<State> targetStates){
        if (targetStates.contains(initialState))
            return new Scramble();

        HashMap<State, Scramble> visitedInitial, visitedTarget;
        PriorityQueue<StateNode> queueInitial, queueTarget;

        visitedInitial = new HashMap<>();
        visitedInitial.put(initialState, new Scramble());

        queueInitial = new PriorityQueue<>(Comparator.comparingInt(StateNode::getDistance));
        queueInitial.add(new StateNode(initialState, 0));

        visitedTarget = new HashMap<>();

        queueTarget = new PriorityQueue<>(Comparator.comparingInt(StateNode::getDistance));

        for (State targetState: targetStates){
            queueTarget.add(new StateNode(targetState, 0));
            visitedTarget.put(targetState, new Scramble());
        }

        Set<String> notation;

        if (!(moves instanceof AdvancedMoves))
            notation = moves.getNotation().keySet();

        else
            notation = new HashSet<>(List.of("r R' r' R b b' B B' x x' x2 y y' y2 z z' z2".split(" ")));


        while (!queueInitial.isEmpty() || !queueTarget.isEmpty()){
            if (queueInitial.peek().getDistance() <= queueTarget.peek().getDistance()){
                StateNode stateNodeInitial = queueInitial.poll();

                moves.setState(stateNodeInitial.getState());
                int distance = stateNodeInitial.getDistance();
                Scramble scramble = stateNodeInitial.getScramble();

                for (String move: notation){
                    State state = moves.applyMove(move);

                    if (!visitedInitial.containsKey(state)){
                        Scramble newScramble = new Scramble(scramble);
                        newScramble.add(move);
                        if (visitedTarget.containsKey(state))
                            return scrambleConstructor(newScramble, visitedTarget.get(state));

                        visitedInitial.put(state, newScramble);
                        queueInitial.add(new StateNode(state, distance + moves.getCost(move), newScramble));
                    }

                }

            }

            else {
                StateNode stateNodeTarget = queueTarget.poll();

                moves.setState(stateNodeTarget.getState());
                int distance = stateNodeTarget.getDistance();
                Scramble scramble = stateNodeTarget.getScramble();

                for (String move: notation){
                    State state = moves.applyMove(move);

                    if (!visitedTarget.containsKey(state)){
                        Scramble newScramble = new Scramble(scramble);
                        newScramble.add(move);

                        if (visitedInitial.containsKey(state))
                            return scrambleConstructor(visitedInitial.get(state), newScramble);

                        visitedTarget.put(state, newScramble);
                        queueTarget.add(new StateNode(state, distance + moves.getCost(move), newScramble));
                    }
                }

            }

        }

        return null;
    }

    public Scramble findSolution(State initialState){
        return findSolution(initialState, targetStates);
    }

    public Scramble findSolution(State initialState, State target){
        return findSolution(initialState, State.generatePerspectivesStates(target));
    }

    public Scramble findScramble(State targetState){
        return findSolution(State.getSolvedState(), State.generatePerspectivesStates(targetState));
    }

    public ArrayList<Integer> FLInfos(State state){
        ArrayList<Integer> infos = new ArrayList<>(6);
        LinkedHashMap<State, State> states = state.getMaskSides();

        states.forEach((initial, solved) ->
           infos.add(findSolution(initial, solved).size())
        );

        return infos;
    }

    public static Scramble invertScramble(ArrayList<String> scramble){
        Scramble newScramble = new Scramble();

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

    public static Scramble scrambleConstructor(ArrayList<String> initial, ArrayList<String> target){
        Scramble scramble = new Scramble(initial);

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
