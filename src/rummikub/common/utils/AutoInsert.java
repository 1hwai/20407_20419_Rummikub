package rummikub.common.utils;

import rummikub.common.tile.Tile;
import rummikub.common.tile.TileList;

import java.util.LinkedList;
import java.util.Queue;

public final class AutoInsert {

    public static void autoInsert(TileList list, Queue<Tile> queue) {

        if (!list.isEmpty() && list.isASC()) binaryInsert(list, queue);
        else {
//            TileList onHand = new TileList();
//            onHand.addAll(queue);
//            if (!onHand.isSIB()) {
//                quickSort(onHand, 0, onHand.size() - 1);
//                queue.clear();
//                queue.addAll(onHand);
//            }
            while (!queue.isEmpty()) list.add(queue.poll());
        }
    }

    private static void binaryInsert(TileList list, Queue<Tile> queue) {
        Queue<Tile> jokers = new LinkedList<>();
        while (!queue.isEmpty()) {
            Tile tile = queue.poll();
            if (tile.isJoker()) {
                jokers.add(tile);
            } else list.add(binarySearch(list, tile), tile);
        }
        while (!jokers.isEmpty()) {
            if (list.isASC()) {
                while (!jokers.isEmpty()) list.add(jokers.poll());
            } else {
                int number = 1;
                for (Tile tile : list)
                    if (!tile.isJoker()) {
                        number = tile.number;
                        break;
                    }
                int i = 0;
                for (Tile tile : list) {
                    if (tile.isJoker()) break;
                    if (tile.number != number) {
                        list.add(i, jokers.poll());
                        break;
                    };
                    i++;
                    number++;
                }
            }
        }
    }

    private static int binarySearch(TileList list, Tile tile) {
        int pl = 0;
        int pr = list.size() - 1;
        int pc;
        do {
            pc = (pl + pr) / 2;
            if (list.get(pc).number == tile.number) return pc;
            else if (list.get(pc).number < tile.number) pl = pc + 1;
            else pr = pc - 1;
        } while (pl <= pr);

        return pc;
    }

    private static void quickSort(TileList list, int left, int right) {
        int pl = left, pr = right;
        Tile tile = list.get((pl + pr) / 2);

        do {
            System.out.println("do while");
            while (list.get(pl).number < tile.number) pl++; System.out.println("++");
            while (list.get(pl).number > tile.number) pr--; System.out.println("--");
            if (pl <= pr) swap(list, pl++, pr--);
        } while (pl <= pr);

        if (left < pr) quickSort(list, left, pr);
        if (right > pl) quickSort(list, pl, right);
    }

    private static void swap(TileList list, int idx1, int idx2) {
        System.out.println(list.get(idx1).number + " : " + list.get(idx2).number);
        Tile temp = list.get(idx1);
        list.set(idx1, list.get(idx2));
        list.set(idx2, temp);
        System.out.println(list.get(idx1).number + " : " + list.get(idx2).number);
    }
}
