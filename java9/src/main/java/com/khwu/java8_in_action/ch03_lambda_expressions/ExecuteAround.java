package com.khwu.java8_in_action.ch03_lambda_expressions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.FunctionalInterface;

public class ExecuteAround {
    public static void main(String[] args) throws IOException {
        String res = processFileLimited();
        System.out.println(res);

        System.out.println("---");

        String oneLine = processFile(BufferedReader::readLine);
        String twoLine = processFile(b -> b.readLine() + b.readLine());
        System.out.println(oneLine);
        System.out.println(twoLine);
    }

    public static String processFileLimited() throws IOException {
        try (BufferedReader br =
                     new BufferedReader(new FileReader("java8_in_action/ch03_lambda_expressions/data.txt"))) {
            return br.readLine();
        }
    }

    public static String processFile(BufferedReaderProcessor p) throws IOException{
        try (BufferedReader bf =
                     new BufferedReader(new FileReader("java8_in_action/ch03_lambda_expressions/data.txt"))) {
            return p.process(bf);
        }
    }

    @FunctionalInterface
    interface BufferedReaderProcessor {
        String process(BufferedReader b) throws IOException;
    }
}
