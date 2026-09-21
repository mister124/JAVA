package week3;
import java.io.PrintStream;
import java.util.Locale;
import java.util.Scanner;

public class BasicCalculator {
    enum Operation { ADD, SUBTRACT, MULTIPLY, DIVIDE, REMAINDER, POWER }

    // TODO 1: hasNextLine()으로 반복하고 빈 줄은 건너뛴다.
    // processLine(line, out)이 false를 돌려주면 즉시 반복을 끝낸다.
    public void run(Scanner input, PrintStream out) {
        while (input.hasNextLine()) {
            String line = input.nextLine();
            if (line.isBlank()) {
                continue;
            }

            boolean keepGoing = processLine(line, out);

            if (!keepGoing) {
                break;
            }
        }
    }

    // TODO 2: DIVIDE/REMAINDER의 b가 0이면 ArithmeticException을 던진다.
    // switch 식으로 연산값을 반환한다. POWER는 Math.pow(a, b)를 사용한다.
    public double calculate(Operation operation, double a, double b) {
        if ((operation == Operation.DIVIDE || operation == Operation.REMAINDER) && b == 0.0) {
            throw new ArithmeticException();
        }
        return switch (operation) {
            case ADD -> a+b;
            case SUBTRACT -> a-b;
            case MULTIPLY -> a * b;
            case DIVIDE -> a/b;
            case REMAINDER -> a % b;
            case POWER -> Math.pow(a,b);
        };
    }

    // 이하 제공 코드: 입력 해석, 개수 검사, 오류 복구, 출력.
    boolean processLine(String line, PrintStream out) {
        String[] parts = line.strip().split("\\s+");
        if (parts[0].equals("QUIT")) {
            if (parts.length != 1) {
                out.println("ERROR ARGUMENT_COUNT");
                return true;
            }
            out.println("BYE");
            return false;
        }
        Operation operation = parseOperation(parts[0]);
        if (operation == null) {
            out.println("ERROR UNKNOWN_COMMAND");
            return true;
        }
        if (parts.length != 3) {
            out.println("ERROR ARGUMENT_COUNT");
            return true;
        }
        try {
            double a = Double.parseDouble(parts[1]);
            double b = Double.parseDouble(parts[2]);
            if (!Double.isFinite(a) || !Double.isFinite(b)) {
                out.println("ERROR NUMBER");
                return true;
            }
            double result = calculate(operation, a, b);
            if (!Double.isFinite(result)) out.println("ERROR RESULT");
            else out.printf(Locale.ROOT, "%.2f%n", result == 0.0 ? 0.0 : result);
        } catch (NumberFormatException error) {
            out.println("ERROR NUMBER");
        } catch (ArithmeticException error) {
            out.println("ERROR ZERO_DIVISOR");
        }
        return true;
    }

    private Operation parseOperation(String symbol) {
        return switch (symbol) {
            case "+" -> Operation.ADD;
            case "-" -> Operation.SUBTRACT;
            case "*" -> Operation.MULTIPLY;
            case "/" -> Operation.DIVIDE;
            case "%" -> Operation.REMAINDER;
            case "^" -> Operation.POWER;
            default -> null;
        };
    }

    void main() {
        run(new Scanner(System.in), System.out);
    }
}
