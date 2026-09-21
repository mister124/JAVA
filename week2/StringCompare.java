package week2;
void main() {
    // TODO: IO.readln()으로 두 줄을 읽고 세 비교 메서드를 순서대로 호출한다.
    String s1 = IO.readln();
    String s2 = IO.readln();
    compareStrings(s1, s2);
    compareIgnoreCase(s1, s2);
    convertAndCompare(s1, s2);
}

void compareStrings(String s1, String s2) {
    // TODO: 내용을 대소문자 구분하여 비교한 결과를 IO.println으로 출력한다.
    IO.println("[equals]: " + s1.equals(s2) +".");
}

void compareIgnoreCase(String s1, String s2) {
    // TODO: 대소문자를 무시한 비교 결과를 IO.println으로 출력한다.
    IO.println("[equalsIgnoreCase]: " + s1.equalsIgnoreCase(s2) +".");
}

void convertAndCompare(String s1, String s2) {
    // TODO: 두 번째 문자열만 소문자로 변환한 뒤 첫 번째 문자열과 비교하여 출력한다.
    IO.println("[toLowerCase]: " + s1.equals(s2.toLowerCase()) +".");
}
