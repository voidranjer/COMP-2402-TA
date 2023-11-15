package comp2402a4;

import java.util.*;

public class UltraFast implements UltraStack {
    public int n;
    public int h;
    public int capacity;
    long[] sum;
    int[] max;

    public UltraFast() {
        n = 0;
        h = 7;
        capacity = 1 << h;
        sum = new long[(capacity << 1) - 1];
        max = new int[(capacity << 1) - 1];
    }

    public void push(int x) {
        if (n == capacity) expand();
        int insertPosition = capacity + n - 1;
        sum[insertPosition] = x;
        max[insertPosition] = x;
        n++;
        updateTree(insertPosition);
    }

    protected void expand() {
        h++;
		int l = (1 << (h + 1)) - 1;
        int[] newMax = new int[l];
        long[] newSum = new long[l];
        int blockStart = 0;
        int shift = 1;

        while (blockStart < max.length) {
            System.arraycopy(max, blockStart, newMax, blockStart + shift, shift);
            System.arraycopy(sum, blockStart, newSum, blockStart += shift, shift);
            shift <<= 1;
        }
        max = newMax;
        sum = newSum;
        capacity <<= 1;
    }

    public Integer pop() {
        if (n == 0) return null;
        int popPosition = capacity + n - 2;
        int goner = max[popPosition];
        sum[popPosition] = 0;
        max[popPosition] = 0;
        n--;
        updateTree(popPosition);
        return goner;
    }

    public Integer get(int i) {
        return max[capacity + i - 1];
    }

    public Integer set(int i, int x) {
        int setPosition = capacity + i - 1;
        int goner = max[setPosition];
        max[setPosition] = x;
        sum[setPosition] = x;
        updateTree(setPosition);
        return goner;
    }

    public Integer max() {
        if (n == 0) return null;
        return max[0];
    }

    public void updateTree(int index) {
        int leftChildIndex;
        int rightChildIndex;
        while (index > 0) {
            index = (index - 1) >> 1;
            leftChildIndex = (index << 1) + 1;
            rightChildIndex = leftChildIndex + 1;
            sum[index] = sum[leftChildIndex] + sum[rightChildIndex];
            max[index] = (max[leftChildIndex] > max[rightChildIndex]) ? max[leftChildIndex] : max[rightChildIndex];
        }
    }

    protected long ksumComplement(int k) {
        int current = 0;
        int depth = 0;
        long complement = 0;
        int elementsInSubtree;
        int leftIndex;
        int rightIndex;
        while (true) {
            if (depth == h) {
                complement += sum[current];
                break;
            }
            elementsInSubtree = 1 << (h - depth - 1);
            leftIndex = (current << 1) + 1;
            rightIndex = leftIndex + 1;
            if (k >= elementsInSubtree) {
                if (sum[rightIndex] != 0) {
                    complement += sum[leftIndex];
                    k -= elementsInSubtree;
                    current = rightIndex;
                } else {
                    current = leftIndex;
                }
            } else {
                current = leftIndex;
            }
            depth++;
        }
        return complement;
    }

    public long ksum(int k) {
		if (k >= n) {
            return sum[0];
        }
        return sum[0] - ksumComplement(n - k - 1);
    }

    public int size() {
        return n;
    }

    public Iterator<Integer> iterator() {
        List<Integer> list = new ArrayList<>();
        for (int i = capacity - 1; i < capacity + n - 1; i++) {
            list.add(max[i]);
        }
        return list.iterator();
    }
}
