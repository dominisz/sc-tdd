package pl.dominisz;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MimeTypeAnalyser {

  private static final String UNKNOWN = "UNKNOWN";

  public List<String> analyse(List<String> mimeTypes, List<String> filenames) {
    Map<String, String> mimeTypeMap = createMimeTypeMap(mimeTypes);

    return filenames.stream()
        .map(filename -> filenameToMimeType(mimeTypeMap, filename))
        .collect(Collectors.toList());
  }

  private String filenameToMimeType(Map<String, String> mimeTypeMap, String filename) {
    int indexOfLastDot = filename.lastIndexOf('.');

    if (indexOfLastDot > -1) {
      String extension = filename.substring(indexOfLastDot + 1).toLowerCase();
      return mimeTypeMap.getOrDefault(extension, UNKNOWN);
    } else {
      return UNKNOWN;
    }
  }

  private Map<String, String> createMimeTypeMap(List<String> mimeTypes) {
    return mimeTypes.stream()
        .collect(
            Collectors.toMap(
                (String mimeType) -> mimeType.split(" ")[0].toLowerCase(),
                (String mimeType) -> mimeType.split(" ")[1]));
  }
}
