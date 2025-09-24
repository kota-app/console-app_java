package F;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    // 入力受け取り
    System.out.print("年を入力してください：");
    int year = sc.nextInt();
    System.out.print("月を入力してください：");
    int month = sc.nextInt();

    // カレンダーを作成
    Calendar calendar = createCalender(year, month);

    // カレンダーログ出力
    printCalender(calendar);

  }

  public static Calendar createCalender(int year, int month) {
    Calendar calendar = Calendar.getInstance();
    calendar.set(year, month - 1, 1);
    return calendar;
  }

  public static void printCalender(Calendar calendar) {
    // Calenderクラスの曜日の数値に対応する文字
    String[] week = { "日", "月", "火", "水", "木", "金", "土" };

    // 月の日数を取得
    int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

    // 月の最初の曜日を取得（日=1）
    int firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

    // タイトル出力
    System.out.println(calendar.get(Calendar.YEAR) + "年" + (calendar.get(Calendar.MONTH) + 1) + "月のカレンダー");

    // 曜日出力
    for (String day : week) {
      System.out.print(day + "\t");
    }
    System.out.println();

    // 最初の日付まで空白埋め
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
}
