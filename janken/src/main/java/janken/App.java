package janken;

import java.util.Random;
import java.util.Scanner;

public class App {
  static final Scanner sc = new Scanner(System.in);
  static final Random random = new Random();
  static final String[] HANDS = { "グー", "チョキ", "パー" };

  public static void main(String[] args) {
    // 起動メッセージ
    // @formatter:off
    printTitle("じゃんけんアプリ", "=");

    while (true) {
      // メニュー選択
      int selectedMenu = promptInt("""
          メニューを選択してください。
          0: じゃんけん
          1: アプリの終了
          """, 0, 1);

      switch (selectedMenu) {
        // じゃんけんを行う
        case 0 -> playJanken();
        // アプリを終了する
        case 1 -> {
          print("アプリを終了します。");
          return;
        }
      }
    }
  }

  /**
   * じゃんけんを行う。
   */
  private static void playJanken() {
    while (true) {
      // プレイヤーの手を選択
      int playerHand = promptInt("""
          出す手を選択してください。
          0: グー
          1: チョキ
          2: パー
          """, 0, 2);

      // コンピューターの手を選択
      int computerHand = random.nextInt(HANDS.length);

      // お互いの手を表示
      print(String.format("プレイヤーの手: %s vs コンピュータの手: %s", HANDS[playerHand], HANDS[computerHand]));

      // 勝敗判定
      String result = judge(playerHand, computerHand);
      print(result);

      // あいこ以外の場合は終了する
      if (!(result.equals("あいこ"))) {
        break;
      }
    }
  }

  /**
   * じゃんけんの勝敗判定
   */
  private static String judge(int playerHand, int computerHand) {
    String playerHandName = HANDS[playerHand];
    String computerHandName = HANDS[computerHand];

    if (playerHandName.equals(computerHandName)) {
      return "あいこ";
    } else if (playerHandName.equals("グー") && computerHandName.equals("チョキ") ||
        playerHandName.equals("チョキ") && computerHandName.equals("パー") ||
        playerHandName.equals("バー") && computerHandName.equals("グー")) {
      return "勝ち！";
    } else {
      return "負け";
    }
  }

  /**
   * 整数の入力受け付けを行い、その値を返す。
   * 指定範囲内の整数が入力されるまで、入力受け付けを繰り返す。
   *
   * @param message プロンプトとして表示される文字列
   * @param min     許容される最小値（この値を含む）
   * @param max     許容される最大値（この値を含む）
   * @return ユーザーから入力された指定範囲内の整数値
   */
  private static int promptInt(String message, int min, int max) {
    // プロンプトメッセージの設定
    message = "\n" + message + "\n>";
    // エラーメッセージ設定
    String errorMessage = String.format("無効な入力値です。%dから%dの数値を入力してください。", min, max);

    while (true) {
      // メッセージ表示
      System.out.print(message);

      // 入力受け取り
      String input = sc.nextLine().trim();
      Integer inputNumber = null;

      // 入力値が整数に変換できない場合
      try {
        inputNumber = Integer.parseInt(input);
      } catch (NumberFormatException e) {
        print(errorMessage);
        continue;
      }

      // 入力値が範囲外の場合
      if (!(inputNumber >= min && inputNumber <= max)) {
        print(errorMessage);
        continue;
      }

      return inputNumber;
    }
  }

  /**
   * コンソールにメッセージを出力する（前後に1行改行を入れる）。
   */
  static void print(String message) {
    System.out.print("\n" + message + "\n");
  }

  /**
   * アプリケーションのタイトルを装飾付きで出力する
   */
  static void printTitle(final String title, String decorator) {
    final int decoratorLength = title.length() * 2;

    String decoratedTitle = 
      decorator.repeat(decoratorLength) + "\n"+
      title + "\n" +
      decorator.repeat(decoratorLength) + "\n";

    System.out.println(decoratedTitle);
  }
}