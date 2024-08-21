public class Ex06_2 {
    //--- 배열 요소 a[idx1]과 a[idx2]의 값을 교환 ---//
    static void swap(int[] a, int idx1, int idx2) {
        int t = a[idx1];  a[idx1] = a[idx2];  a[idx2] = t;
    }

    // 6-5. 셸 정렬
    // 단순 삽입 정렬의 장점은 살리고 단점은 보완하여 좀 더 빠르게 정렬하는 알고리즘
    // 장점 - 정렬을 마쳤거나 정렬을 마친 상태에 가까울수록 정렬 속도가 빨라짐
    // 단점 - 삽입할 위치가 멀리 떨어져 있으면 이동(대입)해야 하는 횟수가 많아짐

    // 도널드 셸(D.L. Shell)이 고안. 정렬할 배열의 요소를 그룹으로 나눠 그룹별 단순 삽입 정렬 수행,
    // 그 그룹을 합치며 정렬을 반복하여 요소의 이동 횟수를 줄임
    // 예를 들어 8개의 요소 배열
    //  2개씩 4개의 그룹(4-정렬)-> 4개씩 두개의 그룹(2-정렬)-> 8개 요소 1개의 그룹(1-정렬)
    // 정렬해야 하는 횟수는 늘지만 전체적으로 요소 이동 횟수가 줄어들어 효율적인 정렬이 가능하다

    // 시간 복잡도는 O(n^1.25)
    // 기존보다 개선되었으나 이 알고리즘도 멀리 떨어진 요소를 교환해야 하여 안정적이지 않음

    //--- 셸 정렬 1 ---//
    /* static void shellSort(int[] a, int n) {
        for (int h = n / 2; h > 0; h /= 2)
            for (int i = h; i < n; i++) {
                int j;
                int tmp = a[i];
                for (j = i - h; j >= 0 && a[j] > tmp; j -= h)
                    a[j + h] = a[j];
                a[j + h] = tmp;
            }
    } */

    //--- 셸 정렬 2 ---//
    static void shellSort(int[] a, int n) {
        int h;  // 간격
        for (h = 1; h < n; h = h * 3 + 1)
            ;

        for ( ; h > 0; h /= 3)
            for (int i = h; i < n; i++) {
                int j;
                int tmp = a[i];
                for (j = i - h; j >= 0 && a[j] > tmp; j -= h)
                    a[j + h] = a[j];
                a[j + h] = tmp;
            }
    }

    // 6-6. 퀵 정렬
    // 가장 빠른 정렬 알고리즘 중 하나로 널리 사용되고있음
    // 정렬 속도가 매우 빠른 데서 착안, 찰스 앤터니 리처드 호어(C.A.R. Hore)가 직접 붙인 이름

    // 배열 두 그룹으로 나누기
    //--- 배열을 나눔 ---//
    static void partition(int[] a, int n) {
        int pl = 0;        // 왼쪽 커서
        int pr = n - 1;    // 오른쪽 커서
        int x = a[n / 2];  // 피벗(가운데 요소), 배열을 나누는 기준

        do {
            while (a[pl] < x) pl++;
            while (a[pr] > x) pr--;
            if (pl <= pr)
                swap(a, pl++, pr--);
        } while (pl <= pr);

        System.out.println("피벗의 값은 " + x + "입니다.");

        System.out.println("피벗 이하 그룹 ");
        for (int i = 0; i <= pl - 1; i++)              // a[0] ～ a[pl - 1]
            System.out.print(a[i] + " ");
        System.out.println();

        if (pl > pr + 1) {
            System.out.println("피벗과 일치하는 그룹 ");
            for (int i = pr + 1; i <= pl - 1; i++)    // a[pr + 1] ～ a[pl - 1]
                System.out.print(a[i] + " ");
            System.out.println();
        }

        System.out.println("피벗 이상 그룹 ");
        for (int i = pr + 1; i < n; i++)              // a[pr + 1] ～ a[n - 1]
            System.out.print(a[i] + " ");
        System.out.println();
    }

    // 퀵 정렬
    // 8퀸 문제와 같이 분할 정복 알고리즘, 재귀 호출 사용하여 구현할 수 있다.
 
    //--- 퀵 정렬 ---//
    /* static void quickSort(int[] a, int left, int right) {
        int pl = left;                   // 왼쪽 커서
        int pr = right;                  // 오른쪽 커서
        int x = a[(pl + pr) / 2];        // 피벗(가운데 요소)

        do {
            while (a[pl] < x) pl++;
            while (a[pr] > x) pr--;
            if (pl <= pr)
                swap(a, pl++, pr--);
        } while (pl <= pr);

        if (left < pr)  quickSort(a, left, pr);
        if (pl < right) quickSort(a, pl, right);
    } */


    //--- 퀵 정렬(비재귀 버전)---//
    // 요소의 개수가 많은 그룹을 먼저 푸시, 즉 요소가 적은 그룹을 먼저 분할하면
    // 스택에 동시에 쌓이는 데이터의 최대 개수가 적어짐
    static void quickSort(int[] a, int left, int right) {
        IntStack lstack = new IntStack(right - left + 1);
        IntStack rstack = new IntStack(right - left + 1);

        lstack.push(left);
        rstack.push(right);

        while (lstack.isEmpty() != true) {
            int pl = left  = lstack.pop();        // 왼쪽 커서
            int pr = right = rstack.pop();        // 오른쪽 커서
            int x = a[(left + right) / 2];        // 피벗은 가운데 요소

            do {
                while (a[pl] < x) pl++;
                while (a[pr] > x) pr--;
                if (pl <= pr)
                    swap(a, pl++, pr--);
            } while (pl <= pr);

            if (left < pr) {
                lstack.push(left);           // 왼쪽 그룹 범위의
                rstack.push(pr);             // 인덱스를 푸시
            }
            if (pl < right) {
                lstack.push(pl);             // 오른쪽 그룹 범위의
                rstack.push(right);          // 인덱스를 푸시
            }
        }
    }        

    // 힙 정렬
    // 힙의 특성을 이용한 정렬
    // 힙은 '부모의 값이 자식의 값보다 항상 크다'는 조건을 만족하는 완전이진트리
    // 부모의 값이 자식보다 항상 작아도 힙이라 한다.(부모, 자식 요소의 관계만 일정하면 됨)
    // 트리의 가장 윗부분을 루트(root), 요소의 상하관계를 부모(parent)와 자식(child), 자식간의 관계는 형제(sibiling)
    // 완전이진트리- '완전'은 부모는 자식을 왼쪽부터 추가하는 모양을 유지, '이진'은 부모가 가질 수 있는 자식의 개수는 최대 2개
    // 부모는        a[(i-1)/2]
    // 왼쪽 자식은    a[i*2+1]
    // 오른쪽 자식은  a[i*2+2]

    // 힙의 가장 큰 요소를 선택하는 시간 복잡도는 O(1), 힙 정렬-> 힙으로 만드는 과정은 O(log n)
    // 단순 선택 정렬은 O(n^2), 힙 정렬은 O(n log n)

    //--- a[left]～a[right]를 힙으로 만들기 ---//
    static void downHeap(int[] a, int left, int right) {
        int temp = a[left];        // 뿌리
        int child;                 // 큰 쪽의 자식
        int parent;                // 부모

        for (parent = left; parent < (right + 1) / 2; parent = child) {
            int cl = parent * 2 + 1;        // 왼쪽의 자식
            int cr = cl + 1;                // 오른쪽의 자식
            child = (cr <= right && a[cr] > a[cl]) ? cr : cl;    // 큰 쪽
            if (temp >= a[child])
                break;
            a[parent] = a[child];
        }
        a[parent] = temp;
    }

    //--- 힙 정렬 ---//
    static void heapSort(int[] a, int n) {
        for (int i = (n - 1) / 2; i >= 0; i--)    // a[i]~a[n-1]를 힙으로 만듦
            downHeap(a, i, n - 1);

        for (int i = n - 1; i > 0; i--) {
            swap(a, 0, i);                       // 가장 큰 요소와 아직 정렬되지 않은 부분의 마지막 요소를 교환
            downHeap(a, 0, i - 1);               // a[0] ~ a[i-1]을 힙으로 만듦
        }
    }


    // 도수 정렬
    // 요소의 대소 관계를 판단하지 않고 빠르게 정렬할 수 있는 알고리즘
    // 도수 정렬 알고리즘은 4단계. 도수분포표 작성, 누적도수분포표 작성, 목적 배열 만들기, 배열 복사

    //--- 도수 정렬(배열 요소의 값은 0 이상 max 이하) ---//
    static void countingSort(int[] a, int n, int max) {
        int[] f = new int[max + 1];        // 누적도수
        int[] b = new int[n];              // 작업용 목표 배열

        for (int i = 0;     i < n;    i++) f[a[i]]++;                  // [Step 1]
        for (int i = 1;     i <= max; i++) f[i] += f[i - 1];           // [Step 2]
        for (int i = n - 1; i >= 0;   i--) b[--f[a[i]]] = a[i];        // [Step 3]
        for (int i = 0;     i < n;    i++) a[i] = b[i];                // [Step 4]
    }



    public static void main(String[] args) {
        
        int[] x = {5, 7, 8, 2, 6};

        // shellSort(x, x.length);            // 배열 x를 셸정렬

        // System.out.println("오름차순으로 정렬했습니다.");
        // for (int i = 0; i < x.length; i++)
        //     System.out.println("x[" + i + "]=" + x[i]);


        System.out.println("도수 정렬");
        int nx = x.length;

        int max = x[0];
        for (int i = 1; i < nx; i++)
            if (x[i] > max) max = x[i];  // 배열 x의 최대값을 구해 max에 대입

        countingSort(x, nx, max);        // 배열 x를 도수정렬

        System.out.println("오름차순으로 정렬했습니다.");
        for (int i = 0; i < nx; i++)
            System.out.println("x[" + i + "]=" + x[i]);


    }

}
