import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

void main() {
    // TODO: IO.readln()으로 시간대와 두 날짜·시간을 읽는다.
    // TODO: 아래 메서드로 차이, 선후 관계, 뉴욕 시간을 계산하여 출력한다.
    String zone = IO.readln();
    String dateTimeStr1 = IO.readln();
    String dateTimeStr2 = IO.readln();

    ZonedDateTime zdt1 = getZonedDateTime(zone, dateTimeStr1);
    ZonedDateTime zdt2 = getZonedDateTime(zone, dateTimeStr2);

    calculateDifference(zdt1, zdt2);
    compareTimes(zdt1, zdt2);

    ZonedDateTime newYork1 = convertToNewYork(zdt1);
    ZonedDateTime newYork2 = convertToNewYork(zdt2);

    IO.println("New York Time 1: " + formatDateTime(newYork1));
    IO.println("New York Time 2: " + formatDateTime(newYork2));
}

ZonedDateTime getZonedDateTime(String zone, String dateTimeStr) {
    // TODO: LocalDateTime.parse(), ZoneId.of(), atZone()으로 변환한다.
    LocalDateTime localDateTime = LocalDateTime.parse(dateTimeStr);
    ZoneId zoneId = ZoneId.of(zone);
    return localDateTime.atZone(zoneId);
}

void calculateDifference(ZonedDateTime zdt1, ZonedDateTime zdt2) {
    // TODO: 날짜의 전체 일수 차이와 경과 시간의 나머지 시·분·초를 출력한다.
    long days = ChronoUnit.DAYS.between(zdt1.toLocalDate(), zdt2.toLocalDate());
    Duration duration = Duration.between(zdt1, zdt2);
    long hours = duration.toHours() % 24;
    long minutes = duration.toMinutes() % 60;
    long seconds = duration.getSeconds() % 60;
    IO.println("Day difference: " + days + " days");
    IO.println("Time difference: " + hours + " hours "+ minutes + " minutes "+ seconds + " seconds");
}

void compareTimes(ZonedDateTime zdt1, ZonedDateTime zdt2) {
    // TODO: 더 미래인 날짜·시간을 출력한다.
    ZonedDateTime later;
    if (zdt1.isAfter(zdt2)) {
        later = zdt1;
    } else {
        later = zdt2;
    }

    IO.println("Later time: " + later.toLocalDateTime());
}

ZonedDateTime convertToNewYork(ZonedDateTime zdt) {
    // TODO: 같은 시점을 America/New_York 시간대로 변환한다.
    ZoneId newYorkZone = ZoneId.of("America/New_York");
    return zdt.withZoneSameInstant(newYorkZone);
}

String formatDateTime(ZonedDateTime zdt) {
    // TODO: yyyy-MM-dd HH:mm:ss z 형식의 문자열을 반환한다.
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z", Locale.ENGLISH);
    return zdt.format(formatter);
}
