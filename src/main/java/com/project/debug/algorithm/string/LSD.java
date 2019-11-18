package com.project.debug.algorithm.string;

/**
 * 2019/11/18
 * com.project.debug.algorithm.string
 *
 * @author jiaofanghao
 *
 *  least-significant-digit first (字符逆序排序)
 *
 *    对固定字符个数的字符串按照由底到高的顺序进行排序
 *
 **/
public class LSD {

    private static final int BITS_PER_BYTE = 8;

    private LSD() {}

    /**
     *
     * @param a    字符数组
     * @param w    要排序的字符个数
     */
    public static void sort(String[] a, int w) {
        int n = a.length;
        int R = 256;
        String[] aux = new String[n];

        // 根据排序字符进行倒序循环
        // 1. 根据字符最后的字符排序, 排序过后, 再根据第二个字符顺序进行排序
        for (int d = w-1; d >= 0; d--) {
            // 对数值 key 进行索引, 根据 char 所属的字符进行索引
            int[] count = new int[R+1];
            for (int i = 0; i < n; i++) {
                // 对字符 + 1 让其自动转型为 int, 然后再对数值进行 ++
                count[a[i].charAt(d) + 1] ++;
            }

            for (int r = 0; r < R; r++) {
                // 对后续的数字进行相加 对应每个数组的 index 位置
                count[r+1] += count[r];
            }

            // 移动数据
            for (int i = 0; i < n; i++) {
                // 对每个数据所属的位置进行替换
                aux[count[a[i].charAt(d)] ++] = a[i];
            }

            // 回写
            for (int i = 0; i < n; i++) {
                // 对数据进行回写, 并进行下一次循环
                a[i] = aux[i];
            }
        }
    }

    public static void sort(int[] a) {
        final int BITS = 32;
        final int R    = 1 << BITS_PER_BYTE;
        final int MASK = R - 1;
        final int w    = BITS / BITS_PER_BYTE;

        int n = a.length;
        int[] aux = new int[n];

        for (int d = 0; d < w; d++) {

            // 计算出现次数
            int[] count = new int[R+1];
            for (int i = 0; i < n; i++) {
                int c = (a[i] >> BITS_PER_BYTE * d) & MASK;
                count[c + 1]++;
            }

            //
            for (int r = 0; r < R; r++) {
                count[r+1] += count[r];
            }

            if (d == w-1) {
                int shift1 = count[R] - count[R/2];
                int shift2 = count[R/2];
                for (int r = 0; r < R/2; r++){
                    count[r] += shift1;
                }
                for (int r = R/2; r < R; r++) {
                    count[r] -= shift2;
                }

                for(int i = 0; i < n; i++) {
                    int c = (a[i] >> BITS_PER_BYTE*d) & MASK;
                    aux[count[c]++] = a[i];
                }

                for (int i = 0; i < n; i++) {
                    a[i] = aux[i];
                }
            }
        }
    }

    public static void main(String[] args) {
        sortStringArray();
    }

    public static void sortStringArray() {
        String[] a = {
                "bad",
                "zoo",
                "yet",
                "bug",
                "dad",
                "yes",
                "all",
                "bed"
        };

        int n = a.length;

        int w = a[0].length();
        for (int i = 0; i < n; i++) {
            assert a[i].length() == w : "Strings must have fixed length";
        }

        sort(a, w);

        for(int i = 0; i < n; i++) {
            System.out.println(a[i]);
        }
    }

    public static void sortIntArray() {
        int[] a = {
                256,357,358,698,259
        };

        sort(a);

        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }

}
