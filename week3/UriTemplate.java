package week3;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UriTemplate {
    // 제공: 템플릿 안의 {이름} 을 찾는 정규 표현식입니다.
    static final Pattern VARIABLE = Pattern.compile("\\{([A-Za-z][A-Za-z0-9]*)}");

    // 제공: 템플릿을 정규 표현식 문자열로 바꿉니다. 호출만 하고 수정하지 않습니다.
    // Pattern.quote 로 템플릿 전체를 글자로 잠그고, {이름} 자리에서만 잠금을 열어 (?<이름>[^/]+) 를 넣습니다.
    // 예: toRegex("/files/{name}.pdf") 는 "\Q/files/\E(?<name>[^/]+)\Q.pdf\E" 입니다.
    static String toRegex(String template) {
        return VARIABLE.matcher(Pattern.quote(template)).replaceAll("\\\\E(?<$1>[^/]+)\\\\Q");
    }

    // TODO: 경로가 템플릿과 맞으면 변수를 템플릿에 나온 순서대로 "이름=값" 줄로 돌려주세요.
    // 맞지 않으면 "NO MATCH", 변수가 없는 템플릿이 맞으면 "MATCH" 를 돌려줍니다.
    // Pattern.compile(toRegex(template)).matcher(path) 로 Matcher 를 만들어 쓰면 됩니다.
    static String describe(String template, String path) {
    Matcher matcher = Pattern.compile(toRegex(template)).matcher(path);

    if (!matcher.matches()) {
        return "NO MATCH";
    }

    List<String> names = new ArrayList<>();
    Matcher nameMatcher = VARIABLE.matcher(template);

    while (nameMatcher.find()) {
        names.add(nameMatcher.group(1));
    }

    if (names.isEmpty()) {
        return "MATCH";
    }

    List<String> lines = new ArrayList<>();

    for (String name : names) {
        String value = matcher.group(name);
        lines.add(name + "=" + value);
    }

    return String.join("\n", lines);
}

    // 제공: 템플릿과 요청 경로 다섯 쌍을 차례로 출력합니다.
    void main() {
        print("/health", "/health");
        print("/orders/{id}", "/orders/17");
        print("/orders/{orderId}/items/{itemId}", "/orders/17/items/3");
        print("/files/{name}.pdf", "/files/report.pdf");
        print("/orders/{id}", "/orders");
    }

    private static void print(String template, String path) {
        IO.println(template + " <- " + path);
        IO.println(describe(template, path));
    }
}
