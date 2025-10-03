package calendar;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

  private static final ObjectMapper objectMapper = new ObjectMapper();

  private static Path getProjectRoot() {
    // 実行中クラスのある場所（target/classes/calendar/ など）を取得
    Path codeLocation = Paths.get(JsonUtils.class.getProtectionDomain()
        .getCodeSource().getLocation().getPath())
        .toAbsolutePath();

    // classes ディレクトリから 2 つ親を辿るとプロジェクトルートに戻れる
    // target/classes -> target -> プロジェクトルート
    Path projectRoot = codeLocation.getParent().getParent();

    // 念のため pom.xml があるか確認
    if (Files.exists(projectRoot.resolve("pom.xml"))) {
      return projectRoot;
    }
    throw new IllegalStateException("プロジェクトルート (pom.xml) が見つかりません: " + projectRoot);
  }

  private static Path getBaseDir() {
    return getProjectRoot().resolve("src/main/resources/holidays");
  }

  public static Map<LocalDate, String> loadJson(int year) {
    Path filePath = getBaseDir().resolve(year + ".json");

    if (Files.exists(filePath)) {
      try {
        String json = Files.readString(filePath);
        return objectMapper.readValue(
            json,
            new TypeReference<Map<LocalDate, String>>() {
            });
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return new HashMap<>();
  }

  public static void saveJson(String json, int year) {
    Path dirPath = getBaseDir();
    Path filePath = dirPath.resolve(year + ".json");

    try {
      Files.createDirectories(dirPath);
      Files.writeString(filePath, json,
          StandardOpenOption.CREATE,
          StandardOpenOption.TRUNCATE_EXISTING);

      System.out.println("保存完了: " + filePath.toAbsolutePath());

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
