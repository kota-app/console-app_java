package project;

import java.time.LocalDate;
import java.util.Scanner;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;
import static org.fusesource.jansi.Ansi.*;

public class App {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    AnsiConsole.systemInstall();

    // 入力受け取り
    System.out.println(ansi().eraseScreen());
    System.out.print("年を入力してください：");
    int year = sc.nextInt();
    System.out.print("月を入力してください：");
    int month = sc.nextInt();

    // 指定された年月の月のカレンダーを表示
    printCalender(year, month);

    AnsiConsole.systemUninstall();
    sc.close();
  }

  /**
   * 指定された年月の月のカレンダーを表示します。
   * 
   * @param year  表示するカレンダーの年
   * @param month 表示するカレンダーの月
   */
  public static void printCalender(int year, int month) {
    LocalDate firstDay = LocalDate.of(year, month, 1);
    // 月の日数を取得
    int lengthOfMonth = firstDay.lengthOfMonth();
    // 月の最初の曜日を取得（日=0、月=1）
    int firstDayOfWeek = firstDay.getDayOfWeek().getValue() % 7;

    // タイトルを出力
    System.out.println(year + "年" + month + "月のカレンダー");

    // 曜日のヘッダーを出力
    String[] week = { "日", "月", "火", "水", "木", "金", "土" };
    for (String day : week) {
      System.out.print(day + "\t");
    }
    System.out.println();

    // 最初の日付まで空白で埋める
    for (int i = 0; i < firstDayOfWeek; i++) {
      System.out.print("\t");
    }

    // 現在日時を取得
    LocalDate today = LocalDate.now();

    // 日付を出力
    for (int day = 1; day <= lengthOfMonth; day++) {
      // 曜日を取得
      int dayOfWeek = (day + firstDayOfWeek) % 7;

      // 日付の文字装飾を設定
      Ansi line = ansi();
      if (dayOfWeek == 0) {
        // 土曜日の場合は青色
        line.fgBlue();
      } else if (dayOfWeek == 1) {
        // 日曜日の場合は赤色
        line.fgRed();
      }
      // 今日の場合は背景色を緑色に設定
      LocalDate currentDay = LocalDate.of(year, month, day);
      if (currentDay.equals(today)) {
        line.bgGreen();
      }

      // 日付を出力
      System.out.print(line.a(day + "\t").reset());

      // 土曜日の場合は改行
      if (dayOfWeek == 0) {
        System.out.println();
      }
    }
  }

}
