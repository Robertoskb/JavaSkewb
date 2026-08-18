package br.com.javaskewb.DataManager.Manager;

import br.com.javaskewb.DataManager.utils.BitState;
import br.com.javaskewb.DataManager.utils.SaveState;
import br.com.javaskewb.core.Cube.Skewb;
import br.com.javaskewb.core.Cube.State;
import br.com.javaskewb.core.Mapping.Moves.AdvancedMoves;
import br.com.javaskewb.core.Patterns.Methods.NS.NSCase;
import br.com.javaskewb.core.Patterns.Methods.NS.NSCases;
import br.com.javaskewb.core.Solution.Solution;
import br.com.javaskewb.core.Solution.utils.Scramble;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

public class Manager {
    private HashMap<Long, StateInfo> data = new HashMap<>();
    private final String path = "src/resources/br/com/javaskewb/Data/";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private static Manager manager;

    private Manager(){
        File file = new File(path + "data.json");
        System.out.println(file.getAbsoluteFile());
        try {
            if (!file.exists()){
                boolean result = file.createNewFile();
            }
            try (FileReader reader = new FileReader(file)){
                Type hashmap = new TypeToken<HashMap<Long, StateInfo>>(){}.getType();
                data = gson.fromJson(reader, hashmap);

                System.out.println("data carregada com sucesso!");
            } catch (IOException e) {
                System.out.println("Falha ao carregar data " + e.getMessage());
            }
        } catch (IOException e) {
            System.out.println("Erro de criação: " + e.getMessage());
        }
    }

    public static Manager getInstance(){
        if (manager == null)
            manager = new Manager();
        return manager;
    }

    public void importStateInfos(ArrayList<StateInfo> stateInfos){
        for (StateInfo stateInfo: stateInfos){
            if (data.containsKey(stateInfo.getId()))
                data.get(stateInfo.getId()).getAlgorithms().addAll(stateInfo.getAlgorithms());
            else
                data.put(stateInfo.getId(), stateInfo);
        }
    }

    public void addAlg(long id, Scramble alg){
        if (data.containsKey(id)){
            data.get(id).getAlgorithms().add(alg.toString());
        }
    }

    public void removeAlg(long id, int index){
        if (data.containsKey(id)){
            ArrayList<String> algorithms = data.get(id).getAlgorithms();
            if (algorithms.size() > index)
                algorithms.remove(index);
        }
    }

    public StateInfo getStateInfo(State state){
        BitState bitState = SaveState.getBitState(state);

        StateInfo stateInfo;
        if (data.containsKey(bitState.getId()))
            stateInfo = data.get(bitState.getId());
        else
            stateInfo = new StateInfo(false, "Desconhecido", new ArrayList<>());

        stateInfo.setId(bitState.getId());

        return stateInfo;
    }

    public boolean save(){
        try (FileWriter writer = new FileWriter(path + "data.json")){
            gson.toJson(data, writer);
            System.out.println("Salvo com sucesso");
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public HashMap<Long, StateInfo> getData() {
        return data;
    }

    public static void main(String[] args) {
        Manager manager = Manager.getInstance();

        NSCases nsCases = new NSCases();

        HashMap<Long, StateInfo> data = manager.getData();

        for (NSCase nsCase: nsCases.getCases()){
            State state = State.getSolvedState();
            nsCase.applyCase(state);

            BitState bitState = SaveState.getBitState(state);

            StateInfo stateInfo = new StateInfo(true, "Deconhecido", new ArrayList<>());

            data.put(bitState.getId(), stateInfo);
        }

        manager.save();

    }
}
