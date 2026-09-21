package week2;
import java.util.Scanner;

Scanner scanner = new Scanner(System.in);
int[] numbers;

void main() {
    // TODO: 입력 → 합계 출력 순서로 메서드를 호출한다.
    inputNumbers();
    displaySum();
}

void inputNumbers() {
    // TODO: 배열 크기와 정수 원소를 읽어 numbers를 초기화한다.
    int n = scanner.nextInt();
    numbers = new int[n];
    for (int i = 0; i < numbers.length; i++) {
        numbers[i] = scanner.nextInt();
    }
}

int calculateSum() {
    // TODO: 모든 배열 원소의 합을 반환한다.
    int sum = 0;
    for (int i = 0; i < numbers.length; i++) {
        sum += numbers[i];
    }
    return sum;
}

void displaySum() {
    // TODO: IO.println으로 "Sum: 합계"를 출력한다.
    IO.println("Sum: "+calculateSum());
}
