import java.util.Random;

public class Ex02 {
    /**
     * 배열 요소 a[idx1]과 a[idx2]의 값을 교환하는 메서드
     * @param a 요소가 들어간 배열
     * @param idx1 교환할 요소의 인덱스 번호1
     * @param idx2 교환할 요소의 인덱스 번호2
     */
    static void swap(int[] a, int idx1, int idx2){
        int t = a[idx1]; a[idx1] = a[idx2]; a[idx2] = t; // a[idx1]과 a[idx2]의 값 교환
    }

    /**
     * 배열 a의 요소를 역순으로 정렬하는 메서드
     * @param a 역순으로 정렬할 배열 a
     */
    static void reverse(int[] a){
        for (int i = 0; i < a.length / 2; i++) {
            swap(a, i, a.length - i - 1);
        }
    }
    
    /**
     * 두 배열 a, b가 같은지 확인하는 메서드
     * @param a 비교할 배열 a
     * @param b 비교할 배열 b
     */
    static boolean equals(int[] a, int[] b){
        if (a.length != b.length) {
            return false;
        }
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 1. 정수값 x를 r진수로 변환하여 배열 d에 아랫자리부터 넣어두고 자릿수를 반환하는 메서드
     * 2. 정수값 x를 r진수로 변환하여 배열 d에 아랫자리부터 넣어 반환하는 메서드
     * @param x r진수로 변경할 정수형 인자
     * @param r 변환할 진수를 나타내는 정수형 인자
     * @param d 변환한 값을 저장할 배열
     */
    // static int cardConvR(int x, int r, char[] d){
    static char[] cardConvR(int x, int r, char[] d){
        int digits = 0;  // 변환 후 자릿수
        String dchar = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        
        do {
            // 문장.charAt(인덱스 번호) : 문장의 인덱스 번호를 가져와 char 타입으로 변경
            d[digits++] = dchar.charAt(x%r);  // r로 나눈 나머지 저장
            x /= r;
        } while (x != 0);
        // return digits;
        // 배열 뒤집기
        for (int i = 0; i < d.length / 2; i++) {
            char t = d[i];
            d[i] = d[d.length-i-1];
            d[d.length-i-1] = t;
        }
        return d;
    }

    
    // 한 해의 경과 일 수 => 윤년, 평년
    static int[][] mdays = {
        {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31},  // 평년
        {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}   // 윤년
    };

    
    // year년이 윤년인가? (평년 : 0, 윤년: 1)
    static int isLeap (int year) {
        return (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) ? 1 : 0;
    }

    // 서기 y년 m월 d일의 그 해의 경과 일 수를 구함
    static int dayOfYear (int y, int m, int d) {
        int days = d;  // 일 수

        for (int i = 1; i < m; i++) {    // 1월 ~ (m-1)월의 일 수를 더함
            days += mdays[isLeap(y)][i-1];  // isLeap(y) : 평년은 0, 윤년 1
        }
        return days;
    }    


    public static void main(String[] args) {
        // 2-1 배열
        // 자료구조 : 데이터 단위와 데이터 자체 사이의 물리적 또는 논리적인 관계
        // 즉 데이터를 구성하는 덩어리(데이터) 자료를 효율적으로 이용하도록 컴퓨터에 저장하는 방법

        // 배열 : 같은 자료형 변수로 이루어진 구성 요소(Component)가 모인 것
        int arr1[];  // int 자료형인 배열 arr1
        int[] arr2 = new int[5];  // int형 배열 생성하고 변수 arr2가 '참조'하도록 설정하는 것

        arr2[1] = 37;
        arr2[2] = 51;
        arr2[4] = arr2[1] * 2;

        for (int i = 0; i < arr2.length; i++) {
            System.out.println("a[" + i + "] = " + arr2[i]);
        }
        // 배열의 구성요소는 자동으로 0으로 초기화
        // int a; System.out.println(a); => 컴파일 오류
        // 메서드 안에서 선언한 지역변수는 자동으로 초기화 되지 않음. 자바에서는 초기화나 대입에 의해 값이 넣어져야 함.

        int arr3[] = {1, 2, 3, 4, 5};  // 배열 초기자에 의해 생성
        for (int i = 0; i < arr3.length; i++) {
            System.out.print(arr3[i] + " ");
        }

        System.out.println(); // 줄바꿈
        
        // 배열의 복제 
        int[] a = {1, 2, 3};
        int[] b = a.clone(); // b는 a의 복제를 참조
        b[1] = 0;

        for (int i = 0; i < b.length; i++) {
            System.out.print(b[i] + " ");
        }

        // 배열의 요소를 하나씩 살펴보는 과정을 '주사(traverse)'라고 한다.
        int arr4[] = {11, 13, 14, 10, 9, 4};
        int max = arr4[0];
        for (int i = 1; i < arr4.length; i++) {
            // arr4 배열 요소의 최댓값
            if (arr4[i]>max) max = arr4[i];
        }
        System.out.println(max); //14

        // 참고 : 접근 제한자
        // public : 모든 접근 허용
        // protected : 같은 패키지(폴더)의 객체, 상속 관계의 객체 허용
        // default : 같은 패키지(폴더)의 객체 허용
        // private : 현재의 객체 안에서만 허용

        Random rand = new Random();
        int[] height = new int[5];

        for (int i = 0; i < height.length; i++) {
            height[i] = 100 + rand.nextInt(90); // 요소 값 난수로 결정, (0 ~ n-1 까지의 범위값), 키 랜덤값
        }
        System.out.print("rand :");
        for (int i = 0; i < height.length; i++) {
            System.out.print(height[i] + " ");
        }

        System.out.println(); // 줄바꿈

        // 진짜 난수, 의사 난수
        // 로또 복권의 공을 하나하나 꺼내 번호를 결정하는 과정은 '진짜 난수'를 생성하는 과정
        // 의사 난수에서 의사는 본뜰 의, 비슷할 사로 실제와 비슷하다는 뜻

        // 컴퓨터 과학에서 난수는 보통 특정 입력값이나 컴퓨터 환경에 따라 무작위로 선택한 것처럼 보이는 난수를 생성.
        // 하지만 srand 메서드의 seed 값과 컴퓨터 환경이 같다면 그 결과값은 항상 같다.
        // 결국 컴퓨터에 의해 성성된 모든 난수는 비리 컴퓨터가 계산해 둔 의사 난수이다.
        // 프로그램에서 매번 다른 난수 생성을 위해서는
        // srand 메서드의 seed값을 보통 현재 시간을 이용하여 매 순간 변경하여 이전에 발생한 의사 난수를 다시 생성하지 않음.

        // 배열 요소 역순 정렬, 두 값의 교환
        int[] arr5 = {1, 2, 3, 4, 5};
        reverse(arr5);
        for (int i = 0; i < arr5.length; i++) {
            System.out.println("arr5[" + i + "] = " + arr5[i]);
        }

        // 두 배열의 비교, equals
        // 두 배열의 모든 요소가 같은가 판단, 반환형은 boolean
        // 1. 두 배열의 요솟수(길이) 비교 -> true: for문 진행 or false : false 반환
        // 2. for문으로 두 배열 요소 비교 반복하여 다르면 false 반환
        // 3. for문의 중단 없이 모두 비교가 끝난 경우, 두 배열은 같다 -> true 반환
        int[] arr6 = {0, 1, 2, 3, 4};
        System.out.println(equals(arr5, arr6));

        // 기수 변환 : 10진수 정수 -> n진수 정수로 변환
        // 10진수: 1234 = 1*10^3 + 2*10^2 + 3*10^1 + 4*10^0
        // 8진수: 5306 = 5*8^3 + 3*8^2 + 0*8^1 + 6*8^0
        // 16진수는 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, A, B, C, D, E, F 16종류로 표현
        // 16진수: 12A0 = 1*16^3 + 2*16^2 + 10*16^1 + 0*16^0 (정수 상수로는 0x12A0으로 표기)
        char[] d = new char[32];
        System.out.println(cardConvR(59, 2, d));

        // String 클래스 : 문자열을 나타내는 java.lang 패키지에 소속된 String클래스, 리터럴
        // String형 인스턴스에 대한 참조, 문자열을 내부에 가진 인스턴스. 
        // final char[]형 배열(문자 배열), final int형 필드(문자의 수), 필드, 생성자, 메서드가 있음.
        // String 제공하는 메서드 중 기억할 것
        // char charAt(int i)  - 인덱스가 i인 곳의 문자를 가져옴
        // int length()  -  문자열의 문자 수를 가져옴
        // boolean equals(String s)  -  문자열 s와 같은가 조사함

        //------------------------------------------------------

        // 소수의 나열 - 소수: 1과 자신 이외의 정수로 나누어 떨어지지 않는 정수
        // 1000 이하의 소수
        int counter = 0;  // 나눗셈 횟수
        int ptr = 0;      // 찾은 소수싀 개수
        int[] prime = new int[500];  // 소수를 저장할 배열

        prime[ptr++] = 2;  // 2는 소수
        prime[ptr++] = 3;  // 3은 소수
        
        for (int n = 5 ; n <= 1000; n += 2) {    // 조사 대상은 홀수만
            boolean flag = false;
            for (int i = 1; prime[i] * prime[i] <= n; i++) {
                counter += 2;                   //  prime[i] * prime[i], n % prime 두 번 계산
                if (n % prime[i] == 0) {        // 나누어떨어지면 소수가 아님
                    flag = true;
                    break;                      // 더 이상 반복은 불필요
                }
            }
            if (!flag) {                       // 마지막까지 나누어떨어지지 않음
                prime[ptr++] = n;              // 소수로 배열에 저장
                counter++;
            }
        }

        for (int i = 0; i < ptr; i++) {
            System.out.print(prime[i] + ", ");
        }
        System.out.println(counter);  // 14,622 -> 3774

        // 정수 n은 n의 제곱근 이하의 어떤 소수로도 나누어 떨어지지 않는다.


        // 다차원 배열
        // 배열을 구성 요소로 하는 것이 2차원 배열, 2차원 배열을 구성 요소로 하는 것이 3차원 배열
        int[][] x = new int [2][4];
        // 행*열이 늘어선 표와 같은 형태로 표현
        x[0][1] = 37;
        x[0][3] = 54;
        x[1][2] = 65;
        System.out.println(x.length);     // 2
        System.out.println(x[0].length);  // 4

        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x[0].length; j++) {
                System.out.printf("%2d ", x[i][j]);
            }
            System.out.println();
        }


        // 1월 1일 포함 111일
        System.out.println(dayOfYear(2024, 4, 20));

        // 확장 for문, for-each문
        double[] numT = {1.0, 2.0, 3.0, 4.0, 5.0};
        double total = 0.0;
        for (double e : numT) {
            total += e;
        }

        System.out.println(total);
    }

}
