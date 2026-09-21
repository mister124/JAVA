package week3;
import java.util.Map;
import java.util.TreeMap;

/** 제공 파일: 실제 디스크와 무관한 메모리 속 작업 폴더입니다. 수정하지 않습니다. */
public class MockTools {
    private final Map<String, String> files = new TreeMap<>();
    private int calls;
    private String lastCall = "";
    private String lastPath;

    public MockTools() {
        files.put("/workspace/README.md", "My CLI project");
    }

    public String readFile(String path) {
        calls++;
        lastCall = "read_file";
        lastPath = path;
        return files.getOrDefault(path, "ERROR FILE_NOT_FOUND");
    }

    public String writeFile(String path, String content) {
        calls++;
        lastCall = "write_file";
        lastPath = path;
        files.put(path, content);
        return "OK written";
    }

    public String listFiles(String path) {
        calls++;
        lastCall = "list_files";
        lastPath = path;
        return String.join(", ", files.keySet());
    }

    // 공개 테스트용 관찰 메서드. 도구 호출 횟수를 증가시키지 않습니다.
    public int callCount() { return calls; }
    public String lastCall() { return lastCall; }
    public String lastPath() { return lastPath; }
    public Map<String, String> snapshot() { return Map.copyOf(files); }
}
