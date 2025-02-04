
class A {
    // toString을 정의하지 않음        
}

class B {
    int x;
    // 생성자
    B(int x) { this.x = x; }

    // toString 오버라이드
    public String toString() { return "B[" + x + "]"; }   
}

public class Ex07_집합 {
    // 명확한 조건을 만족하는 자료의 모임. 집합도 자료구조로 표현 할 수 있음.

    // 집합(set)이란 객관적으로 범위를 규정한 '어떤것'의 모임
    // 그 안에서 각각 어떤것을 요소(element)라고 부름.

    // 집합 x의 요소 1, 5를 표현하면, 집합은 순서가 없으므로 바뀌어도 같은 표현
    // x = {1, 5} or x = {5, 1}

    // 일반적으로 자연수의 집합은 N, 정수의 집합을 Z 기호를 많이 사용함
    // N = {1, 2, 4, ...} // Z = {..., 3, 2, 1, 0, -1, -2, -3, ...}

    // 집합의 요소는 중복X, 중복된 요소를 가지는 집합은 다중 집합이라 하고, 집합과 구별하여 부름
    // 집합끼리 포함, 포함되지 않음(∈, ∉,), 같다, 같지않다(=, ≠), 무한 집합(n(Y)=∞), 유한 집합(n(X)=n) 등의 표기 방법 등이 있음

    // 부분집합과 진부분집합
    // 부분집합
    // 예를 들어 A = {1, 3}, B = {1, 3, 5}
    // A ⊂ B 또는 B ⊃ A     // A는 B에 포함된다. A는 B의 부분집합
    // A = {1, 3, 5}, B = {1, 3, 5}
    // A ⊂ B 이면서 B ⊂ A    // A와 B는 서로 부분집합 관계

    // 진부분집합
    // 집합 A의 모든 요소가 집합 B의 요소이면서 집합 A와 B가 같지 않을 때
    // A는 B의 진부분집합(proper subset)이다
    // A ⊊ B 또는 B ⊋ A

    // 합집합, 교집합, 차집합


    // ------------------------------------------------------------

    // 배열로 집합 만들기


    public static void main(String[] args) {
        A a1 = new A();
        A a2 = new A();
        B b1 = new B(11);
        B b2 = new B(22);

        System.out.println(a1.toString());
        System.out.println(a2);
        System.out.println(b1.toString());
        System.out.println(b2);
        /** 출력
         * A@5ccd43c2  // java.lang 패키지의 클래스에서 정의된 메서드
         * A@4aa8f0b4  // 클래스 이름@해시값
         * B[11]       // 오버라이드 한 메서드 출력
         * B[22]
         */

    }
}
