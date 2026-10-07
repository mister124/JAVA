package edu.pnu.pbp;

/// 섭씨나 화씨로 만들고 읽을 수 있는 온도 하나. 한 번 만든 뒤에는 값이 바뀌지 않는다.
public final class Temperature {

    /// 온도 단위. 제공 코드이며 수정하지 않는다.
    /// 단위마다 섭씨와의 관계 `표시값 = 섭씨 × scale + offset` 을 알고 있어 환산을 맡는다.
    public enum Unit {
        CELSIUS(1.0, 0.0),
        FAHRENHEIT(9.0 / 5.0, 32.0);

        private final double scale;
        private final double offset;

        Unit(double scale, double offset) {
            this.scale = scale;
            this.offset = offset;
        }

        /// 이 단위로 표시한 온도를 섭씨 온도로 바꾼다. 화씨 77 → 섭씨 25.
        private double toCelsius(double value) {
            return (value - offset) / scale;
        }

        /// 섭씨 온도를 이 단위로 표시한 값으로 바꾼다. 섭씨 25 → 화씨 77.
        private double fromCelsius(double celsius) {
            return celsius * scale + offset;
        }

        /// 이 단위로 표시한 온도 차이를 섭씨 온도 차이로 바꾼다. 화씨 9도 차이 → 섭씨 5도 차이.
        private double differenceToCelsius(double delta) {
            return delta / scale;
        }
    }

    private final double celsius;

    private Temperature(double celsius) {
        this.celsius = celsius;
    }

    // TODO: 온도 값을 담을 필드를 선언한다.

    // TODO: 필드를 채우는 private 생성자를 작성한다.

    /// `unit` 으로 표시한 온도 `value` 를 나타내는 새 객체를 돌려준다.
    public static Temperature of(double value, Unit unit) {
        // TODO
        return new Temperature(unit.toCelsius(value));
    }

    /// 이 온도를 `unit` 으로 표시한 값을 돌려준다.
    public double in(Unit unit) {
        // TODO
        return unit.fromCelsius(celsius);
    }

    /// `unit` 으로 표시한 온도 차이 `delta` 만큼 바꾼 새 객체를 돌려준다. 이 객체는 바뀌지 않는다.
    public Temperature plus(double delta, Unit unit) {
        // TODO
        return new Temperature(celsius + unit.differenceToCelsius(delta));
    }

    /// 이 온도가 `other` 보다 높으면 true, 같거나 낮으면 false 를 돌려준다.
    public boolean isWarmerThan(Temperature other) {
        // TODO
        return celsius > other.celsius;
    }
}
