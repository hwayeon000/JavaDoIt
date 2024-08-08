import java.util.Scanner;
import java.util.Arrays;

// 클래스 메서드와 인터페이스 메서드
class Id {
    private static int counter = 0;        //아이디를 몇 개 부여했는지 저장
    private int id;                        // 아이디

    //-- 생성자(constructor) --//
    public Id() { id = ++counter; }

    //--- counter를 반환하는 클래스 메서드(가장 큰 식별 번호를 얻음) ---//
    public static int getCounter() { return counter; }

    //--- 아이디를 반환하는 인스턴스 메서드(식별 번호를 얻음) ---//
    public int getId() { return id; }
}

public class Ex03 {
    // 검색 알고리즘

    //--- 요솟수가 n인 배열 a에서 key와 값이 같은 요소를 선형 검색 ---//
    static int seqSearch(int[] a, int key) {
        // int i = 0;
        // while (true) {
        //     if (i == a.length)
        //         return -1;            // 검색 실패(-1을 반환)
        //     if (a[i] == key)
        //         return i;             // 검색 성공(인덱스를 반환)
        //     i++;
        // }
        for (int i = 0; i < a.length; i++)
            if (a[i] == key)
                return i;                // 검색 성공(인덱스를 반환)
        return -1;                       // 검색 실패(-1을 반환)
    }

    //--- 요솟수가 n인 배열 a에서 key와 값이 같은 요소를 보초법으로 선형 검색 ---//
    // 배열의 길이 등을 입력받아 진행하는 경우
    // 배열의 마지막에 값key를 넣어 처음 while문의 판단(검색실패 or 검색성공) 횟수를 줄일 수 있음
    static int seqSearchSen(int[] a, int n, int key) {
        int i = 0;
        a[n] = key;             // 보초를 추가
        while (true) {
            if (a[i] == key)    // 검색 성공
                break;
            i++;
        }
        return i == n ? -1 : i;  // 인덱스 값으로 추가한 값인지 배열 안의 값인지 판단
    }

    //--- 요솟수가 n개인 배열 a에서 key와 같은 요소를 이진 검색 ---//
	static int binSearch(int[] a, int n, int key) {
		int pl = 0;			// 검색 범위 맨앞의 인덱스
		int pr = n - 1;		// 　 〃    　맨끝의 인덱스

		do {  //   3     0    6
			int   pc = (pl + pr) / 2;	// 중앙요소의 인덱스
			if (a[pc] == key) {
				for ( ; pc > pl; pc--)	// key와 같은 맨앞의 요소를 검색
					if (a[pc - 1] < key)
						break;
				System.out.println(pc);
				return pc;							// 검색 성공
			} else if (a[pc] < key)
				pl = pc + 1;						// 검색 범위를 앞쪽 절반으로 좁힘
			else
				pr = pc - 1;						// 검색 범위를 뒤쪽 절반으로 좁힘
		} while (pl <= pr);

		return -1;									// 검색 실패
	}



    public static void main(String[] args) {
        // 선형 검색 / 이진 검색 / 해시법 - 체인법, 오픈 주소법

        // 선형 검색 : 직선 배열로 맨 앞부터 순서대로 요소 검색
        int[] arr = {5, 11, 24, 30, 14};

        int idx = seqSearch(arr, 14);    // 배열 arr에서 값이 ky인 요소를 검색

        if (idx == -1)
            System.out.println("검색 값의 요소가 없습니다.");
        else
            System.out.println("검색 값은 x[" + idx + "]에 있습니다.");

        //---------2차
        Scanner scan = new Scanner(System.in);
        // System.out.print("요솟수 : ");
        // int num = scan.nextInt();

        int num = 4;
        // int[] arr1 = new int[num + 1];                        // 요솟수가 num + 1인 배열
        int[] arr1 = {3, 11, 24, 15, 8};
        // for (int i = 0; i < num; i++) {
        //     System.out.print("x[" + i + "] : ");
        //     arr1[i] = scan.nextInt();
        // }

        // System.out.print("검색 값 : ");                    // 키값을 읽력받음
        // int ky = scan.nextInt();
        int ky = 11;
        int idx2 = seqSearchSen(arr1, num, ky);              // 배열 x에서 값이 ky인 요소를 검색

        if (idx2 == -1)
            System.out.println("검색 값의 요소가 없습니다.");
        else
            System.out.println("검색 값은 x[" + idx2 + "]에 있습니다.");


        ky = 1;
        // 이진 검색 : 정렬이 되어 있어야 함
        int[] arr2 = {1 , 5, 11, 13, 15};

        int idx3 = binSearch(arr2, num, ky);    // 배열 x에서 값이 ky인 요소를 검색

        if (idx3 == -1)
            System.out.println("검색 값의 요소가 없습니다.");
        else
            System.out.println("검색 값은 x[" + idx3 + "]에 있습니다.");


        // 복잡도
        // 1. 시간 복잡도 : 실행에 필요한 시간을 평가
        // 2. 공간 복잡도 : 기억 영역과 파일 공간이 얼마나 필요한가 평가

        Id a = new Id();        // 아이디1
        Id b = new Id();        // 아이디2

        System.out.println("a의 아이디: " + a.getId());
        System.out.println("b의 아이디: " + b.getId());

        System.out.println("부여한 아이디의 개수 = " + Id.getCounter());


        // 제네릭
        // 처리해야 할 대상의 자료형에 의존하지 않는 클래스(인터페이스) 구현 방식.
        // class(interface) 클래스(인터페이스)이름 <파라미터1, 파라미터2, ... > { /* ... */ }
        
    }
}