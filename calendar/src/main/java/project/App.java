package project;

import java.util.Calendar;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 入力受け取り
        System.out.print("年を入力してください：");
        int year = sc.nextInt();
        System.out.print("月を入力してください：");
        int month = sc.nextInt();

        // 対象月のカレンダー情報を出力
        printCalender(year, month);

        sc.close();
    }

    /**
     * 指定された年月の月のカレンダーを表示します。
     * 
     * @param year  表示するカレンダーの年
     * @param month 表示するカレンダーの月
     */
    public static void printCalender(int year, int month) {
        // カレンダーを作成
        Calendar calendar = createCalender(year, month);

        // DAY_OF_WEEKの値に対応する曜日の文字列
        String[] week = { "日", "月", "火", "水", "木", "金", "土" };

        // 月の日数を取得
        int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        // 月の最初の曜日を取得
        int firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        // タイトル出力
        System.out.println(
                calendar.get(Calendar.YEAR) +
                        "年" +
                        (calendar.get(Calendar.MONTH) + 1) +
                        "月のカレンダー");

        // 曜日出力
        for (String day : week) {
            System.out.print(day + "\t");
        }
        System.out.println();

        // 最初の日付まで空白で埋める
        for (int i = 1; i < firstDayOfWeek; i++) {
            System.out.print("\t");
        }

        // 日付を出力
        for (int day = 1; day <= lastDay; day++) {
            System.out.print(day + "\t");
            // 土曜日で改行
            if ((day + firstDayOfWeek - 1) % 7 == 0) {
                System.out.println();
            }
        }
    }

    /**
     * 指定された年月に設定したCalendarインスタンスを返します。
     * 
     * @param year  設定に使う年
     * @param month 設定に使う月
     * @return 指定した年月で設定されたCalendarインスタンス
     */
    public static Calendar createCalender(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        // 月は0始まりのため-1する
        calendar.set(year, month - 1, 1);
        return calendar;
    }

}
