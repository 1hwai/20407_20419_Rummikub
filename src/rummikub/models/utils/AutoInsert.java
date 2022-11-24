package rummikub.models.utils;

import rummikub.models.tile.Tile;
import rummikub.models.tile.TileList;

import java.util.Queue;

public final class AutoInsert {

    public static void autoInsert(TileList list, Queue<Tile> queue, Boolean useAutoSorting) {
        while (!queue.isEmpty()) list.add(queue.poll());
        if (useAutoSorting) quickSort(list, 0, list.size() - 1);
    }

    public static void quickSort(TileList list, int left, int right) {
        int pl = left;
        int pr = right;
        Tile tile = list.get((pl + pr) / 2);

        do {
            while (list.get(pl).number < tile.number) pl++;
            while (list.get(pr).number > tile.number) pr--;
            if (pl <= pr) swap(list, pl++, pr--);
        } while (pl <= pr);

        if (left < pr) quickSort(list, left, pr);
        if (right > pl) quickSort(list, pl, right);
    }

    private static void swap(TileList list, int idx1, int idx2) {
        Tile temp = list.get(idx1);
        list.set(idx1, list.get(idx2));
        list.set(idx2, temp);
    }
}
