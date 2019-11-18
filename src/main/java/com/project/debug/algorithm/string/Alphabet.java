package com.project.debug.algorithm.string;

/**
 * 2019/11/18
 * com.project.debug.algorithm.string
 *
 * @author jiaofanghao
 **/
public class Alphabet {

    // Binary Alphabet
    public static final Alphabet BINARY = new Alphabet("01");

    // Octal Apphabet
    public static final Alphabet OCTAL  = new Alphabet("01234567");

    // Decimal Alphabet
    public static final Alphabet DECIMAL = new Alphabet("0123456789");

    // HexDecimal Alphabet
    public static final Alphabet HEXDECIMAL = new Alphabet("0123456789ABCDEF");

    // DNA Alphabet
    public static final Alphabet DNA      = new Alphabet("ACGT");

    // lowercase Alphabet
    public static final Alphabet LOWERCASE = new Alphabet("abcdefghijklmnopqrstuvwxyz");

    // UPPERCASE Alphabet
    public static final Alphabet UPPERCASE = new Alphabet("ABCDEFGHIJKLMNOPQRSTUVWXYZ");

    // PROTEIN
    public static final Alphabet PROTEIN   = new Alphabet("ACDEFGHIKLMNPQRSTVWY");

    // BASE-64 Alphabet
    public static final Alphabet BASE64    = new Alphabet("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/");

    // ASCII Alphabet (0 - 127)
    public static final Alphabet ASCII = new Alphabet(128);

    // extended ASCII (0 - 255)
    public static final Alphabet EXTENDED_ASCII = new Alphabet(256);

    // Unicode 16
    public static final Alphabet UNICODE16 = new Alphabet(65536);

    private char[] alphabet;
    private int[] inverse;
    private final int R;

    public Alphabet(String alpha) {
        // 检查是否有重复的字符
        boolean[] unicode = new boolean[Character.MAX_VALUE];
        for (int i = 0; i < alpha.length(); i++) {
            char c = alpha.charAt(i);
            if(unicode[c])
                throw new IllegalArgumentException("Illegal aphabet: repeated character = '" + c + "'");
            unicode[c] = true;
        }

        alphabet = alpha.toCharArray();
        R = alpha.length();
        inverse = new int[Character.MAX_VALUE];
        for (int i = 0; i < inverse.length; i++) {
            inverse[i] = -1;
        }

        // 不能直接使用从 R 开始的字符
        for (int c = 0; c < R; c++) {
            inverse[alphabet[c]] = c;
        }
    }

    /**
     * 作为一个新的 alphabet 使用 char 0 ~ R-1
     * @param radix
     */
    private Alphabet(int radix) {
        this.R = radix;
        alphabet = new char[R];
        inverse = new int[R];

        for (int i = 0; i < R; i++) {
            alphabet[i] = (char) i;
        }
        for (int i = 0; i < R; i++) {
            inverse[i] = i;
        }
    }

    /**
     * 默认初始化, 使用 0 ` 256 字符
     */
    public Alphabet() {
        this(256);
    }

    public boolean contains(char c) {
        return inverse[c] != -1;
    }

    public int radix() {
        return R;
    }

    public int lgR() {
        int lgR = 0;
        for (int t = R-1; t >= 1; t /= 2) {
            lgR++;
        }
        return lgR;
    }

    public int toIndex(char c) {
        if(c >= inverse.length || inverse[c] == -1) {
            throw new IllegalArgumentException("Character " + c + " not in alphabet");
        }
        return inverse[c];
    }

    public int[] toIndices(String s) {
        char[] source = s.toCharArray();
        int[] target  = new int[s.length()];
        for (int i = 0; i < source.length; i++) {
            target[i] = toIndex(source[i]);
        }
        return target;
    }

    public char toChar(int index) {
        if (index < 0 || index >= R) {
            throw new IllegalArgumentException("index must be between 0 and " + R + ": " + index);
        }
        return alphabet[index];
    }

    /**
     * 将所指定的 indices 转为字符串
     * @param indices
     * @return
     */
    public String toChars(int[] indices) {
        StringBuilder s = new StringBuilder(indices.length);
        for (int i = 0; i < indices.length; i++) {
            s.append(toChar(indices[i]));
        }

        return s.toString();
    }

    public static void main(String[] args) {
        int[]  encoded1 = Alphabet.BASE64.toIndices("NowIsTheTimeForAllGoodMen");
        String decoded1 = Alphabet.BASE64.toChars(encoded1);
        System.out.println(decoded1);

        int[]  encoded2 = Alphabet.DNA.toIndices("AACGAACGGTTTACCCCG");
        String decoded2 = Alphabet.DNA.toChars(encoded2);
        System.out.println(decoded2);

        int[]  encoded3 = Alphabet.DECIMAL.toIndices("01234567890123456789");
        String decoded3 = Alphabet.DECIMAL.toChars(encoded3);
        System.out.println(decoded3);
    }

}
