package calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 計算式取得
        System.out.println("計算式を入力してください");
        System.out.println("使用可能な演算子: +, -, *, /");
        System.out.print(": ");
        String formula = sc.nextLine();

        // 計算
        calculator(formula);

    }

    public static Integer calculator(String formula) {
        Integer result = null;

        /* 計算式の取得 */
        // 文字列中の空白文字を除去し、一文字ずつ配列として格納
        String[] formattedFormula = formula.replaceAll(" ", "").split("");
        // 文字列中の演算子の位置を取得
        List<Integer> additionIndexes = findAllIndex(formula, "+");
        List<Integer> subtractionIndexes = findAllIndex(formula, "-");
        List<Integer> multiplicationIndexes = findAllIndex(formula, "*");
        List<Integer> divisionIndexes = findAllIndex(formula, "/");

        // int index = 0;
        // for (String value : formattedFormula) {
        // System.out.println(index + ":" + value);
        // ++index;
        // }

        // System.out.println("足し算: " + additionIndexes);
        // System.out.println("引き算: " + subtractionIndexes);
        // System.out.println("掛け算: " + multiplicationIndexes);
        // System.out.println("割り算: " + divisionIndexes);

        if (result != null) {
            return null;
        } else {
            throw new Error("計算できませんでした。");
        }
    }

    public static List<Integer> findAllIndex(String formula, String pattern) {
        List<Integer> indexes = new ArrayList<>();
        int searchIndex = 0;

        while (formula.indexOf(pattern, searchIndex) != -1) {
            int findIndex = formula.indexOf(pattern, searchIndex);
            indexes.add(findIndex);
            searchIndex = findIndex + 1;
        }

        return indexes;
    }
}
