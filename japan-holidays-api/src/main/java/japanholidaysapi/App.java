package japanholidaysapi;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;

public class App {
  public static void main(String[] args) {
    String apiBaseUri = "https://holidays-jp.github.io/api/v1/?year/date.json";

    try (Scanner sc = new Scanner(System.in);) {
      // 入力受け取り
      System.out.println("取得する年を入力してください");
      Integer year = sc.nextInt();

      // apiのuriを構築
      String apiUri = apiBaseUri.replace("?year", year.toString());

      // httpクライアント作成
      HttpClient client = HttpClient.newHttpClient();

      // httpリクエスト作成
      HttpRequest request = HttpRequest.newBuilder()
          .uri(URI.create(apiUri))
          .GET()
          .build();

      // リクエスト送信
      HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
      String json = response.body();

      // jsonをmapに変換
      ObjectMapper mapper = new ObjectMapper();
      Map<String, String> holidays = mapper.readValue(json, Map.class);

      // 日付のフォーマット設定
      DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
      DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("yyyy年M月d日");

      // ログ出力
      holidays.forEach((dateStr, name) -> {
        // 日付のフォーマット
        LocalDate date = LocalDate.parse(dateStr, inputFormat);
        String formattedDate = date.format(outputFormat);

        // 出力
        System.out.println(formattedDate + "：" + name);
      });
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }
  }
}