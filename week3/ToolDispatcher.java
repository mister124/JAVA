package week3;
public class ToolDispatcher {
    // TODO: validateRequest 결과가 null이 아니면 그 오류를 그대로 반환한다.
    // 정상 요청은 switch 식으로 아래 제공 메서드 중 하나를 호출하고 결과를 반환한다.
    public String dispatch(String tool, String path, String content) {
        String error = validateRequest(tool, path, content);
        if (error != null){
            return error;
        }
        return switch (tool) {
            case "read_file" -> readFile(path);
            case "list_files" -> listFiles(path);
            case "write_file" -> writeFile(path, content);
            default -> "ERROR UNKNOWN_TOOL";
        };
    }

    // 제공 메서드: 이번 실습에서는 호출만 하고 내부 구현을 수정하지 않습니다.
    private String readFile(String path) {
        return tools.readFile(path);
    }

    private String listFiles(String path) {
        return tools.listFiles(path);
    }

    private String writeFile(String path, String content) {
        return tools.writeFile(path, content);
    }

    // 제공 코드: 오류 문자열 또는 정상일 때 null을 반환한다.
    String validateRequest(String tool, String path, String content) {
        if (tool == null || !(tool.equals("read_file") || tool.equals("list_files") || tool.equals("write_file"))) {
            return "ERROR UNKNOWN_TOOL";
        }
        if (tool.equals("list_files")) {
            if (!"/workspace".equals(path)) return "ERROR PATH";
        } else if (!isFilePath(path)) {
            return "ERROR PATH";
        }
        if (tool.equals("write_file") && content == null) return "ERROR CONTENT";
        return null;
    }

    private boolean isFilePath(String path) {
        if (path == null || !path.startsWith("/workspace/")) return false;
        String name = path.substring("/workspace/".length());
        return !name.isBlank() && !name.equals(".") && !name.equals("..")
                && !name.contains("/") && !name.contains("\\");
    }

    // CLI의 한 대화 중 도착한 네 개의 요청을 재현한다. 표준입력은 사용하지 않는다.
    void main() {
        System.out.println(dispatch("read_file", "/workspace/README.md", null));
        System.out.println(dispatch("write_file", "/workspace/notes.txt", "Add a login test."));
        System.out.println(dispatch("read_file", "/workspace/notes.txt", null));
        System.out.println(dispatch("list_files", "/workspace", null));
    }

    // 제공 내부 코드: 도구 상태와 테스트 연결에 사용하며 이번 실습의 학습 대상이 아닙니다.
    private final MockTools tools;

    public ToolDispatcher(MockTools tools) {
        this.tools = tools;
    }

    public ToolDispatcher() {
        this(new MockTools());
    }
}
