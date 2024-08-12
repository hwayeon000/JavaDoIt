import java.util.Scanner;

public class Ex05_재귀 {
    // 재귀란?
    // 어떤 사건이 자기 자신을 포함하고 다시 자기 자신을 사용하여 정의될 때 재귀적(recursive)이라 한다.
    
    // 직접 재귀와 간접 재귀
    // 직접 재귀는 자신과 같은 메서드를 호출
    // 간접 재귀는 메서드 A가 메서드 B를 호출하고 다시 메서드 A를 호출하는 구조

    // factorial
    // 양의 정수 n의 팩토리얼을 반환
    static int factorial(int n) {
        if (n > 0) {
            return n * (factorial(n - 1));
        }
        else
           return 1;
    }

    // 유클리드 호제법으로 최대공약수를 구함
    //--- 정수 x, y의 최대공약수를 구하여 반환 ---//
    static int gcd(int x, int y) {
        if (y == 0)
            return x;
        else
            return gcd(y, x % y);
    }

    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);

        // System.out.print("정수를 입력하세요 : ");
        // int x = stdIn.nextInt();

        // System.out.println(x + "의 팩토리얼은 " + factorial(x) + "입니다.");

        System.out.println("두 정수의 최대공약수를 구합니다.");

        System.out.print("정수를 입력하세요 : ");  int x = stdIn.nextInt();
        System.out.print("정수를 입력하세요 : ");  int y = stdIn.nextInt();

        System.out.println("최대공약수는 " + gcd(x, y) + "입니다.");
    }

}
