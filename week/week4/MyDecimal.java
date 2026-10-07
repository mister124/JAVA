/// 문자열로만 십진수를 표현하고 더하는 클래스.
///
/// - 부호, 정수부, 소수부를 각각 따로 보관한다
/// - 숫자 자료형(`int`, `long`, `double`, `BigDecimal` 등)을 쓰지 않는다
public class MyDecimal {

    /// 음수이면 `true`
    private boolean negative;

    /// 정수부. 앞의 불필요한 0을 뺀 문자열
    private String integerPart;

    /// 소수부. 뒤의 불필요한 0을 뺀 문자열이며 없으면 빈 문자열
    private String fractionPart;

    /// 문자열을 부호·정수부·소수부로 나누어 저장한다.
    ///
    /// @param value 십진수 문자열 (`"123.45"`, `"-0.500"`)
    /// @throws IllegalArgumentException `value`가 `null`이거나 공백뿐일 때
    public MyDecimal(String value) {
        // TODO: 부호를 떼고 소수점으로 나눈 뒤 두 제거 메서드로 정규화한다.
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException();
        }

        String s = value.strip();

        negative = false;

        if (s.startsWith("-")) {
            negative = true;
            s = s.substring(1);
        } else if (s.startsWith("+")){
            s = s.substring(1);
        }

        int dot = s.indexOf('.');

        if (dot < 0) {
            integerPart = removeLeadingZeros(s);
            fractionPart = "";
        } else {
            String integer = s.substring(0,dot);
            String fraction = s.substring(dot + 1);

            integerPart = removeLeadingZeros(integer);
            fractionPart = removeTrailingZeros(fraction);
        }

        if (isZero()) {
            negative = false;
        }

    }

    /// 앞의 불필요한 0을 뺀다. `"00123"` → `"123"`, `"000"` → `"0"`
    private String removeLeadingZeros(String s) {
        // TODO: 구현
        int i = 0;
        while (i<s.length() && s.charAt(i) == '0') {
            i++;
        }

        if (i == s.length()) {
            return "0";
        }
        return s.substring(i);
    }

    /// 뒤의 불필요한 0을 뺀다. `"4500"` → `"45"`, `"000"` → `""`
    private String removeTrailingZeros(String s) {
        // TODO: 구현
        int i = s.length();

        while (i > 0 && s.charAt(i-1) == '0') {
            i--;
        }
        return s.substring(0, i);
    }

    /// 저장된 값을 문자열로 돌려준다. 소수부가 비어 있으면 소수점을 찍지 않는다.
    @Override
    public String toString() {
        // TODO: 구현
        String result;

        if (fractionPart.isEmpty()) {
            result = integerPart;
        } else {
            result = integerPart + "." + fractionPart;
        }

        if (negative) {
            return "-" + result;
        }
        return result;
    }

    /// 값이 0이면 `true`
    public boolean isZero() {
        // TODO: 구현
        return integerPart.equals("0") && fractionPart.isEmpty();
    }

    /// 두 수의 정수부·소수부 길이를 맞춰 소수점 없는 문자열 두 개로 돌려준다.
    ///
    /// @return `{a의 자릿수 문자열, b의 자릿수 문자열}`
    private static String[] alignParts(MyDecimal a, MyDecimal b) {
        // TODO: 구현
        int integerLength = Math.max(
        a.integerPart.length(),
        b.integerPart.length()
    );

    int fractionLength = Math.max(
        a.fractionPart.length(),
        b.fractionPart.length()
    );

    String aInteger = padLeft(a.integerPart, integerLength, '0');
    String bInteger = padLeft(b.integerPart, integerLength, '0');

    String aFraction = padRight(a.fractionPart, fractionLength, '0');
    String bFraction = padRight(b.fractionPart, fractionLength, '0');

    return new String[] {
        aInteger + aFraction,
        bInteger + bFraction
    };
    }

    /// 왼쪽을 `c`로 채워 길이를 `len`으로 맞춘다.
    private static String padLeft(String s, int len, char c) {
        // TODO: 구현
        if (s.length() >= len) {
            return s;
        }
        return String.valueOf(c).repeat(len-s.length()) + s;
    }

    /// 오른쪽을 `c`로 채워 길이를 `len`으로 맞춘다.
    private static String padRight(String s, int len, char c) {
        // TODO: 구현
        if (s.length() >= len) {
            return s;
        }
        return s+String.valueOf(c).repeat(len-s.length());
    }

    /// 두 수를 더한 새 객체를 돌려준다. 양수 덧셈만 지원한다.
    ///
    /// @throws UnsupportedOperationException 두 수 중 하나라도 음수일 때
    public MyDecimal add(MyDecimal other) {
        // TODO: 자릿수를 맞춘 뒤 뒤에서부터 더하고 carry를 처리한다.
        if (negative || other.negative) {
        throw new UnsupportedOperationException();
    }

    String[] aligned = alignParts(this, other);

    String a = aligned[0];
    String b = aligned[1];

    int fractionLength = Math.max(
        fractionPart.length(),
        other.fractionPart.length()
    );

    StringBuilder result = new StringBuilder();

    int carry = 0;

    for (int i = a.length() - 1; i >= 0; i--) {
        int digitA = a.charAt(i) - '0';
        int digitB = b.charAt(i) - '0';

        int sum = digitA + digitB + carry;

        result.append((char) ('0' + (sum % 10)));
        carry = sum / 10;
    }

    if (carry > 0) {
        result.append((char) ('0' + carry));
    }

    result.reverse();

    String digits = result.toString();
    String value;

    if (fractionLength == 0) {
        value = digits;
    } else {
        int point = digits.length() - fractionLength;

        if (point <= 0) {
            digits = "0".repeat(-point + 1) + digits;
            point = 1;
        }

        value = digits.substring(0, point)
                + "."
                + digits.substring(point);
    }

    return new MyDecimal(value);
    }
}
