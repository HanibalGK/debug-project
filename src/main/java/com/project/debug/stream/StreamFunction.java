package com.project.debug.stream;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * 2019/11/14
 * com.project.debug.stream
 *
 * @author jiaofanghao
 **/
public class StreamFunction {

    public static void main(String[] args) {
        List<String> strings = stringsToList("1", "2", "3", "4");

        strings.stream()
                .forEach(str -> System.out.print("\t" + str));
    }

    public static List<String> stringsToList(String... args) {
        List<String> result = Stream.of(args)
                .collect(toList());

        return result;
    }

}
