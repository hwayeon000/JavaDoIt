// javadoc 사용 예제를 위한 클래스
/*
 * @author phy
 * @version 1.0
 */

import java.util.Scanner;

// 기본 알고리즘
public class Ex01 {
    /**
     * 두 정수의 합을 구하는 메서드
     * @param i 합을 구할 첫번째 정수형 인자
     * @param j 합을 구할 두번째 정수형 인자
     * @return 두 정수의 합을 반환
     */
    public static int sum(int i, int j) {
        return i + j;
    }
    
        /**
     * 세 정숫값 중 최댓값을 구하는 메서드
     * @param a 비교할 첫번째 정수형 인자
     * @param b 비교할 두번째 정수형 인자
     * @param c 비교할 세번째 정수형 인자
     * @return 세 정수를 비교하여 가장 큰 값을 반환
     */
    // public 없는 경우
    // default 접근 제한: 같은 패키지에서 사용O, 다른 패키지에서 사용X
    static int max3(int a, int b, int c) {
        // 최댓값
        int max = a;
        if (b>max) {
            max = b;
        }
        if (c>max) {
            c = max;
        }
        return max;
    }
    
    /**
     * 세 정숫값에서 중앙값을 구하는 메서드
     * @param a 비교할 첫번째 정수형 인자
     * @param b 비교할 두번째 정수형 인자
     * @param c 비교할 세번째 정수형 인자
     * @return 세 정수를 비교하여 중앙값을 반환
     */
    public static int med3(int a, int b, int c) {
        if (a>=b) {
            if (b>=c) {
                return b;
            } else if (a<=c) {
                return a;
            } else {
                return c;
            }
        } else if (a>c) {
            return a;
        } else if (b>c) {
            return c;
        } else {
            return b;
        }
    }

    /**
     * 양수 a부터 b까지의 합을 구하는 메서드
     * @param a 시작 값 a 정수형 인자
     * @param b 마지막 값 b 정수형 인자
     * @return n까지 합한 값을 반환
     */
    static int SumFor(int a, int b) {
        int sum = 0;
        for (int i = a; i <= b; i++) {
            sum += i;
        }
        return sum;
    }

    public static void main(String[] args) {
        final int num = 10;
        int num2 = 5;
        int res = max3(3,22,7);
        
        // 알고리즘이란 문제 해결을 위한 것, 명확하게 정의되고 순서가 있는 유한 개의 규칙으로 이루어진 집합
        System.out.println(sum(num, num2) + ", " + res);
        System.out.println(med3(10, 5, 8));

        // 3항 연산자, 조건?참:거짓
        int c = 0;
        System.out.println(c==0 ? "c는 0" : "c는 0이 아님");
        
        
        System.out.println("2부터 숫자 6까지의 합 : " + SumFor(2, 6));
        
        Scanner scan = new Scanner(System.in);
        int n;
        // n이 0보다 클때까지 반복
        do {
            System.out.println("양수 n의 값 : ");
            n = scan.nextInt();
        } while (n <= 0);

        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += i;
        }
        System.out.println("1부터 n까지의 합 : " + sum);
        scan.close();
        
        // 드모르간 법칙 : 각 조건을 부정하고 논리곱을 논리합으로, 논리합을 논리곱으로 바꾸고 다시 전체를 부정하면 원래의 조건과 같다
        // tr || fa 과 !(!tr && !fa) 는 같다.
        boolean tr = true;
        boolean fa = false;
        System.out.println(tr || fa);
        System.out.println(!(!tr && !fa));

        // 곱셈표 출력문제
        System.out.print("    ");
        for (int i = 1; i <= 9; i++) {
            System.out.printf("%3d", i);
        }
        System.out.println();
        System.out.println("---+----------------------------");
        for (int i = 1; i <= 9; i++) {
            System.out.printf("%2d |", i);
            for (int j = 1; j <= 9; j++) {
                System.out.printf("%3d", j*i);
            }
            System.out.println();
        }

        // n단의 * 피라미드 만들기
        // (n*2)-1
        int top = 5;
        int max = top*2-1; // 7
        for (int i = 1; i <= top; i++) {
            for (int j = top-i; j > 0; j--) {
                System.out.print(" ");
            }
            for (int j = 1; j <= (i*2)-1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }

    }
}

// Javadoc 사용방법 테스트
// cmd- .java 파일 위치로 이동
// javadoc -d doc *.java -encoding utf-8