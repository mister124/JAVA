import java.util.Scanner;

Scanner scanner = new Scanner(System.in);
int[] numbers;

void main() {
    inputNumbers();
    displaySum();
}

void inputNumbers() {
    var n = scanner.nextInt();

    numbers = new int[n];

    for (var i = 0; i < n; i++) {
        numbers[i] = scanner.nextInt();
    }
}

int calculateSum() {
    var sum = 0;

    for (var i = 0; i < numbers.length; i++) {
        sum += numbers[i];
    }

    return sum;
}

void displaySum() {
    IO.println("Sum: " + calculateSum());
}