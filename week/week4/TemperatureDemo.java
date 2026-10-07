package edu.pnu.pbp;

import static edu.pnu.pbp.Temperature.Unit.CELSIUS;
import static edu.pnu.pbp.Temperature.Unit.FAHRENHEIT;

import java.util.Locale;

/// 실행용 제공 코드. 수정하지 않는다.
public class TemperatureDemo {
    void main() {
        Temperature morning = Temperature.of(20, CELSIUS);
        Temperature afternoon = morning.plus(9, FAHRENHEIT);
        Temperature night = afternoon.plus(-10, CELSIUS);
        Temperature cool = Temperature.of(33, FAHRENHEIT);

        show("morning", morning);
        show("afternoon", afternoon);
        show("night", night);
        show("cool", cool);
        System.out.println("afternoon is warmer than morning: " + afternoon.isWarmerThan(morning));
        System.out.println("night is warmer than morning: " + night.isWarmerThan(morning));
        System.out.println("morning is warmer than morning: " + morning.isWarmerThan(morning));
    }

    private void show(String label, Temperature temperature) {
        System.out.printf(Locale.ROOT, "%s: %.2f C = %.2f F%n", label,
                temperature.in(CELSIUS), temperature.in(FAHRENHEIT));
    }
}
