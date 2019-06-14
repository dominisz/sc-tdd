package pl.dominisz;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

public class MimeTypeAnalyserTests {

  private static final List<String> MIME_TYPE_TABLE =
      ImmutableList.of(
          "html text/html", "png image/png", "json application/json", "mp3 audio/mpeg");

  @ParameterizedTest
  @MethodSource("testDataProvider")
  void shouldReturnMimeTypes(List<String> filenames, List<String> expectedMimeTypes) {
    MimeTypeAnalyser mimeTypeAnalyser = new MimeTypeAnalyser();

    List<String> actualMimeTypes = mimeTypeAnalyser.analyse(MIME_TYPE_TABLE, filenames);

    Assertions.assertIterableEquals(expectedMimeTypes, actualMimeTypes);
  }

  static Stream<Arguments> testDataProvider() {
    return Stream.of(
        Arguments.arguments(
            ImmutableList.of("test.html", "portrait.png", "user.json"),
            ImmutableList.of("text/html", "image/png", "application/json")),
        Arguments.arguments(
            ImmutableList.of("test", "my.portrait.png", "backend.application.json"),
            ImmutableList.of("UNKNOWN", "image/png", "application/json")),
        Arguments.arguments(
            ImmutableList.of("word.docx", "application.exe", "mimetypeanalyser.class"),
            ImmutableList.of("UNKNOWN", "UNKNOWN", "UNKNOWN")),
        Arguments.arguments(
            ImmutableList.of("test.HTML", "portrait.Png", "user.JsOn"),
            ImmutableList.of("text/html", "image/png", "application/json")));
  }
}
