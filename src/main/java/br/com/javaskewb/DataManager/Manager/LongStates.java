package br.com.javaskewb.DataManager.Manager;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.OptionalLong;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class LongStates {
    private static final Long[] idsWCA = loadIdsWCA();

    private static Long[] loadIdsWCA() {
        Path file = Paths.get(System.getProperty("user.home"), ".javaskewb", "idsWCA.bin");
        if (!Files.exists(file)) return new Long[0];

        try {
            if (Files.size(file) % Long.BYTES != 0)
                throw new IOException("idsWCA.bin possui tamanho inválido.");

            Long[] ids = new Long[(int) (Files.size(file) / Long.BYTES)];
            try (DataInputStream input = new DataInputStream(Files.newInputStream(file))) {
                for (int i = 0; i < ids.length; i++) ids[i] = input.readLong();
            }
            return ids;
        } catch (IOException e) {
            return new Long[0];
        }
    }

    public static boolean hasIds() {
        return idsWCA.length > 0;
    }

    public static boolean contains(long id) {
        if (id == 0) return true;

        int left = 0;
        int right = idsWCA.length - 1;

        while (left <= right) {
            int middle = left + (right - left) / 2;
            long current = idsWCA[middle];

            if (current == id) return true;
            if (current < id) left = middle + 1;
            else right = middle - 1;
        }

        return false;
    }

    public static OptionalLong getRandomId() {
        if (idsWCA.length == 0) return OptionalLong.empty();

        int index = ThreadLocalRandom.current().nextInt(idsWCA.length);
        return OptionalLong.of(idsWCA[index]);
    }


    public static void main(String[] args) {
        Path path = Paths.get(System.getProperty("user.home"), ".javaskewb");

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Nome do arquivo .bin: ");
            Path file = path.resolve(scanner.nextLine().trim()).normalize();

            if (!file.startsWith(path.normalize()) || !Files.isRegularFile(file)) {
                System.out.println("Arquivo não encontrado em " + path);
                return;
            }
            if (Files.size(file) % Long.BYTES != 0) {
                System.out.println("O arquivo não contém apenas IDs long válidos.");
                return;
            }

            ArrayList<Long> ids = new ArrayList<>();
            try (DataInputStream entrada = new DataInputStream(Files.newInputStream(file))) {
                while (entrada.available() >= Long.BYTES) {
                    ids.add(entrada.readLong());
                }
            }

            Collections.sort(ids);

            try (DataOutputStream out = new DataOutputStream(Files.newOutputStream(file))) {
                for (long id : ids) {
                    out.writeLong(id);
                }
            }

            System.out.println(ids.size() + " IDs ordenados em " + file);
        } catch (IOException e) {
            System.out.println("Erro ao ordenar o arquivo: " + e.getMessage());
        }
    }
}
