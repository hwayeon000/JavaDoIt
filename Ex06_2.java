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

    public static void main(String[] args) {
        
        int[] x = {5, 7, 8, 2, 6};

        shellSort(x, x.length);            // 배열 x를 셸정렬

        System.out.println("오름차순으로 정렬했습니다.");
        for (int i = 0; i < x.length; i++)
            System.out.println("x[" + i + "]=" + x[i]);
    }

}
