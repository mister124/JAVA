import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UriMatcher {
    // 제공: 템플릿 안의 {이름} 을 찾는 정규 표현식입니다.
    static final Pattern VARIABLE =
            Pattern.compile("\\{([A-Za-z][A-Za-z0-9]*)}");

    // TODO 1
    static String toRegex(String template) {
        StringBuilder regex = new StringBuilder();
        Matcher m = VARIABLE.matcher(template);

        int last = 0;

        while (m.find()) {
            String textPart = template.substring(last, m.start());
            regex.append(Pattern.quote(textPart));

            String name = m.group(1);
            regex.append("(?<")
                 .append(name)
                 .append(">[^/]+)");

            last = m.end();
        }

        String tail = template.substring(last);
        regex.append(Pattern.quote(tail));

        return regex.toString();
    }

    // TODO 2: 0303에서 만든 describe
    static String describe(String template, String path) {
        Matcher matcher =
                Pattern.compile(toRegex(template)).matcher(path);

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

    // TODO 3
    public static String findRoute(
            String[] routes,
            String method,
            String path) {

        for (String route : routes) {
            if (!methodOf(route).equals(method)) {
                continue;
            }

            String template = pathOf(route);
            String result = describe(template, path);

            if (!result.equals("NO MATCH")) {
                return route;
            }
        }

        return null;
    }

    // 제공
    static String methodOf(String route) {
        return route.split(" ", 2)[0];
    }

    static String pathOf(String route) {
        return route.split(" ", 2)[1];
    }

    // 제공
    void main() {
        String[] routes = {
            "GET /health",
            "GET /orders/{id}",
            "GET /orders/{orderId}/items/{itemId}",
            "GET /files/{name}.pdf"
        };

        print(routes, "GET", "/health");
        print(routes, "GET", "/orders/17");
        print(routes, "GET", "/orders/17/items/3");
        print(routes, "GET", "/files/report.pdf");
        print(routes, "POST", "/health");
        print(routes, "GET", "/orders");
    }

    private static void print(
            String[] routes,
            String method,
            String path) {

        String route = findRoute(routes, method, path);

        if (route == null) {
            IO.println(method + " " + path + " => NO MATCH");
            return;
        }

        String variables = describe(pathOf(route), path);

        IO.println(
                method + " " + path
                + " => "
                + route
                + " | "
                + variables.replace("\n", ", ")
        );
    }
}