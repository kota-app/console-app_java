package calendar;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class HolidaysApi {
  public static Map<LocalDate, String> getHolidaysApi(int year) {
    String apiBaseUri = "https://holidays-jp.github.io/api/v1/?year/date.json";

    try {
      // apiのuriを構築
      String apiUri = apiBaseUri.replace("?year", String.valueOf(year));

      // httpクライアント作成
      HttpClient client = HttpClient.newHttpClient();

      // httpリクエスト作成
      HttpRequest request = HttpRequest.newBuilder()
          .uri(URI.create(apiUri))
          .GET()
          .build();

      // リクエスト送信
      HttpResponse<String> response = client.send(request,
          HttpResponse.BodyHandlers.ofString());
      String json = response.body();

      // jsonをmapに変換
      ObjectMapper mapper = new ObjectMapper();
      mapper.registerModule(new JavaTimeModule());
      Map<LocalDate, String> holidays = mapper.readValue(json, new TypeReference<Map<LocalDate, String>>() {
      });

      return holidays;
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
      return Map.of();
    }
  }
}
