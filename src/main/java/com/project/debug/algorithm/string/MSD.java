package com.project.debug.algorithm.string;

/**
 * 2019/11/18
 * com.project.debug.algorithm.string
 *
 * @author jiaofanghao
 *
 * radix 排序, 根据数据的位置建立索引, 并进行排序
 *      MSD
 **/
public class MSD {
    /**
     * Java 字符 所占空间为 8 bit
     */
    private static final int BITS_PER_BYTE = 8;
    /**
     * Java int 所占空间为 32 bit
     */
    private static final int BITS_PER_INT  = 32;
    /**
     * 继承 ASCII 码大小, 为 256 个字符
     */
    private static final int R             = 256;
    /**
     * 插入排序的分割点
     */
    private static final int CUTOFF        = 15;

    /**
     * 初始化构造函数
     */
    private MSD() {}

    public static void sort(String[] a) {
        int n = a.length;
        String[] aux = new String[n];
        sort(a, 0, n - 1, 0, aux);
    }

    private static int charAt(String s, int d) {
        assert d >= 0 && d <= s.length();
        if (d == s.length()) return -1;
        return s.charAt(d);
    }

    /**
     * 对 字符数组进行排序
     * @param a      需要进行排序的字符串数组
     * @param lo     数组起始 index
     * @param hi     数组结束 index
     * @param d      根据排序的字符字符
     * @param aux
     */
    private static void sort(String[] a, int lo, int hi, int d, String[] aux) {
        // cutoff to insertion sort for small subarrays
        if (hi <= lo + CUTOFF) {
            insertion(a, lo, hi, d);
            return;
        }

        // 计算出现次数
        int[] count = new int[R + 2];
        for (int i = lo; i <= hi; i++) {
            int c = charAt(a[i], d);
            count[c+2]++;
        }

        // 将次数转为 下标索引
        for (int r = 0; r < R+1; r++) {
            count[r+1] = count[r];
        }

        // 分组
        for (int i = lo; i <= hi; i++) {
            int c = charAt(a[i], d);
            aux[count[c+1]++] = a[i];
        }

        // 回写
        for (int i = lo; i <= hi; i++) {
            a[i] = aux[i - lo];
        }

        for (int r = 0; r < R; r++) {
            sort(a, lo + count[r], lo + count[r+1] - 1, d + 1, aux);
        }
    }

    /**
     * 根据给定的 起始 index & 结束 index 进行排序, 对 前 d 个字符进行排序
     * @param a      需要排序的字符串数组
     * @param lo     排序起始位置
     * @param hi     排序结束位置
     * @param d      从开始到 d 个字符进行排序
     */
    private static void insertion(String[] a, int lo, int hi, int d) {
        // 循环数据, 对数据进行排序
        for (int i = lo; i <= hi; i++) {
            // 对数据进行排序, 将其进行替换排序
            for (int j = i; j > lo && less(a[j], a[j-1], d); j--) {
                exch(a, j, j-1);
            }
        }
    }

    /**
     * 替换数据位置
     * @param a    需要替换的数组
     * @param i    替换位置 i
     * @param j    替换位置 j
     */
    private static void exch(String[] a, int i, int j) {
        String temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    /**
     * 比较两个指定位置字符, 哪个字符更小
     * @param v      字符串 v
     * @param w      字符串 w
     * @param d      需要比较的字符位置 d
     * @return       是否 v 比 w 小
     */
    private static boolean less(String v, String w, int d) {
        for (int i = d; i < Math.min(v.length(), w.length()); i++) {
            if (v.charAt(i) < w.charAt(i)) return true;
            if (v.charAt(i) > w.charAt(i)) return false;
        }

        return v.length() < w.length();
    }

    public static void sort(int[] a) {
        int n = a.length;
        int[] aux = new int[n];
        sort(a, 0, n - 1, 0, aux);
    }

    private static void sort(int[] a, int lo, int hi, int d, int[] aux) {

        // 切分插入排序最小的子数组
        if (hi <= lo + CUTOFF) {
            insertion(a, lo, hi, d);
            return;
        }

        // 计算出现次数 需要 R = 256
        int[] count = new int[R+1];
        int mask = R - 1;
        int shift = BITS_PER_INT - BITS_PER_BYTE*d - BITS_PER_BYTE;
        for (int i = lo; i <= hi; i++) {
            // 每个数字取指定位数, 并与 256 进行与, 得到所在槽位
            int c = (a[i] >> shift) & mask;
            count[c + 1]++;
        }

        // 转换为索引
        for (int r = 0; r < R; r++) {
            count[r+1] += count[r];
        }

        // 分组
        for (int i = lo; i <= hi; i++) {
            int c = (a[i] >> shift) & mask;
            aux[count[c]++] = a[i];
        }

        for (int i = lo; i <= hi; i++) {
            a[i] = aux[i - lo];
        }

        if (d == 4) return;

        if (count[0] > 0)
            sort(a, lo, lo + count[0] - 1, d + 1, aux);
        for (int r = 0; r < R; r++) {
            if (count[r + 1] > count[r])
                sort(a, lo + count[r], lo + count[r+1] - 1, d+1, aux);
        }
    }

    private static void insertion(int[] a, int lo, int hi, int d) {
        for( int i = lo; i <= hi; i++) {
            for(int j = i; j > lo && a[i] < a[j - 1]; j--) {
                exch(a, j, j -1);
            }
        }
    }

    private static void exch(int[] a, int i, int j) {
        int temp = a[i];
        a[j] = a[i];
        a[j] = temp;
    }

    public static void main(String[] args) {
        String[] a = args;

        int n = a.length;
        sort(a);

        for (int i = 0; i < n; i++) {
            System.out.println(a[i]);
        }
    }
}
