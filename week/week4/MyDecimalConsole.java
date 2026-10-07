/// 제공 코드. 표준 입력의 각 줄을 MyDecimal로 처리해 결과를 출력한다.
/// `END` 줄을 만나거나 입력이 끝나면 멈춘다. 이 파일은 수정하지 않는다.
void main() {
    String line;
    while ((line = IO.readln()) != null) {
        String request = line.strip();
        if (request.equals("END")) {
            return;
        }
        if (request.isEmpty()) {
            continue;
        }
        IO.println(evaluate(request));
    }
}

String evaluate(String request) {
    try {
        int plus = request.indexOf('+', 1);
        if (plus < 0) {
            return new MyDecimal(request).toString();
        }
        MyDecimal left = new MyDecimal(request.substring(0, plus).strip());
        MyDecimal right = new MyDecimal(request.substring(plus + 1).strip());
        return left + " + " + right + " = " + left.add(right);
    } catch (IllegalArgumentException e) {
        return "ERROR VALUE";
    } catch (UnsupportedOperationException e) {
        return "ERROR NEGATIVE";
    }
}
