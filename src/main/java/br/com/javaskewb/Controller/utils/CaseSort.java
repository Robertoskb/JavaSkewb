package br.com.javaskewb.Controller.utils;

import br.com.javaskewb.core.Patterns.base.Case;

import java.util.List;
import java.util.function.Function;


public class CaseSort {
    public static void heapSort(List<Case> arr, Function<Case, ? extends Number> extractor) {
        int n = arr.size();

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i, extractor);
        }

        for (int i = n - 1; i > 0; i--) {
            Case temp = arr.getFirst();
            arr.set(0, arr.get(i));
            arr.set(i, temp);

            heapify(arr, i, 0, extractor);
        }
    }

    private static void heapify(
            List<Case> arr,
            int heapSize,
            int rootIndex,
            Function<Case, ? extends Number> extractor) {

        int current = rootIndex;

        while (true) {
            int largest = current;
            int left = 2 * current + 1;
            int right = 2 * current + 2;

            if (left < heapSize && extractor.apply(arr.get(left)).doubleValue() > extractor.apply(arr.get(largest)).doubleValue()) {
                largest = left;
            }

            if (right < heapSize && extractor.apply(arr.get(right)).doubleValue() > extractor.apply(arr.get(largest)).doubleValue()) {
                largest = right;
            }

            if (largest == current) {
                break;
            }

            Case swap = arr.get(current);
            arr.set(current, arr.get(largest));
            arr.set(largest, swap);

            current = largest;
        }
    }


}
