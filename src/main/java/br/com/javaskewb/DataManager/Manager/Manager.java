package br.com.javaskewb.DataManager.Manager;

import br.com.javaskewb.DataManager.utils.BitState;
import br.com.javaskewb.DataManager.utils.StateRank;
import br.com.javaskewb.core.Cube.State;
import br.com.javaskewb.core.Patterns.Methods.NS.NSCase;
import br.com.javaskewb.core.Patterns.Methods.NS.NSCases;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Manager {
    private HashMap<Long, StateInfo> data = new HashMap<>();

    private final Path folderPath = Paths.get(System.getProperty("user.home"), ".javaskewb");
    private final File file = folderPath.resolve("data.json").toFile();

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static Manager manager;

    private Manager(){
        try {
            if (!Files.exists(folderPath)) {
                Files.createDirectories(folderPath);
            }

            if (!file.exists()){
                boolean result = file.createNewFile();
            }

            try (FileReader reader = new FileReader(file)){
                Type hashmap = new TypeToken<HashMap<Long, StateInfo>>(){}.getType();
                data = gson.fromJson(reader, hashmap);

                if (data == null)
                    data = new HashMap<>();

                System.out.println("Data carregada com sucesso de: " + file.getAbsolutePath());
            } catch (IOException e) {
                System.out.println("Falha ao carregar data " + e.getMessage());
            }
        } catch (IOException e) {
            System.out.println("Erro de criação de diretórios/arquivos: " + e.getMessage());
        }
    }

    public static Manager getInstance(){
        if (manager == null)
            manager = new Manager();
        return manager;
    }

    public HashMap<Long, HashSet<String>> getAlgorithms(File file){
        HashMap<Long, HashSet<String>> algorithms = new HashMap<>();

        try (FileReader reader = new FileReader(file)){
            Type hashmap = new TypeToken<HashMap<Long, ArrayList<String>>>(){}.getType();
            algorithms = gson.fromJson(reader, hashmap);

            System.out.println("data carregada com sucesso!");
        } catch (IOException e) {
            System.out.println("Falha ao carregar data " + e.getMessage());
        }

        return algorithms;
    }

    public boolean importAlgorithms(File file){
        HashMap<Long, HashSet<String>> algorithms = getAlgorithms(file);

        if (file != null) {
            for (long id : algorithms.keySet()) {
                if (data.containsKey(id))
                    data.get(id).getAlgorithms().addAll(algorithms.get(id));
                else
                    data.put(id, new StateInfo(false, "Desconhecido", new HashSet<>(algorithms.get(id))));
            }

            return save();
        }

        return false;
    }

    public boolean exportAlgorithms(Set<Long> subSet, File file){
        HashMap<Long, HashSet<String>> export = new HashMap<>();

        for (long id: subSet){
            StateInfo stateInfo = getStateInfo(id);
            if (stateInfo != null) {
                HashSet<String> algorithms = stateInfo.getAlgorithms();

                if (!algorithms.isEmpty())
                    export.put(id, algorithms);
            }
        }


        return saveAlgorithm(export, file);
    }

    private StateInfo getStateInfo(long id) {
        return data.get(id);
    }

    public StateInfo getStateInfo(State state){
        BitState bitState = StateRank.getBitState(state);

        StateInfo stateInfo;
        if (data.containsKey(bitState.getId())){
            stateInfo = data.get(bitState.getId());
            stateInfo.setId(bitState.getId());
        }
        else{
            stateInfo = new StateInfo(false, "Desconhecido", new HashSet<>());
            stateInfo.setId(bitState.getId());
            data.put(stateInfo.getId(), stateInfo);
        }


        return stateInfo;
    }

    public boolean save(){
        try (FileWriter writer = new FileWriter(file)){
            gson.toJson(data, writer);
            System.out.println("Salvo com sucesso");
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean saveAlgorithm(HashMap<Long, HashSet<String>> json, File file){
        try (FileWriter writer = new FileWriter(file)){
            gson.toJson(json, writer);
            return true;
        }
        catch (IOException e){
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

            BitState bitState = StateRank.getBitState(state);

            StateInfo stateInfo = new StateInfo(true, "Desconhecido", new HashSet<>());

            data.put(bitState.getId(), stateInfo);
        }

        manager.save();

    }
}
