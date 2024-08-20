public class Ex06_정렬 {
    /* 정렬 : 이름, 학번, 키 등 해심 항목(key)의 대소 관계에 따라
    데이터의 집합을 일정한 순서로 줄지어 늘어서도록 바꾸는 작업
    오름차순(ascending order), 내림차순(decending order) */

    // 내부정렬 : 정렬할 모든 데이터를 하나의 배열에 저장할 수 있는 경우 사용하는 알고리즘
    // 외부정렬 : 정렬할 데이터가 너무 많아 하나의 배열에 저장할 수 없는 경우 사용하는 알고리즘

    // 책은 모두 내부정렬
    // 정렬 알고리즘 핵심 요소 : 교환, 선택, 삽입

    // 6-2. 버블 정렬
    // 이웃한 두 요소의 대소 관계를 비교하여 교환을 반복
    // 요소 n개의 배열 n-1회 비교, 교환 -> 이런 과정을 패스(pass)하고 한다

    //--- 배열 요소 a[idx1]와 a[idx2]의 값을 교환 ---//
    static void swap(int[] a, int idx1, int idx2) {
        int t = a[idx1]; a[idx1] = a[idx2]; a[idx2] = t;
    }
    
    //--- 버블 정렬 ---//
    // 중간에 정렬이 완료가 되어도 끝까지 for문을 돔 -> 교환 횟수 체크를 통해 교환이 없는 경우 중단
    /* static void bubbleSort(int[] a, int n) {
        for (int i = 0; i < n - 1; i++)
            for (int j = n - 1; j > i; j--)
                if (a[j - 1] > a[j])
                    swap(a, j - 1, j);
    } */
    
    //--- 단순교환정렬(버전 2 : 교환 횟수에 따른 멈춤)---//
    // 앞에 이미 정렬된 부분 반복 비교 -> 범위를 한정하여 이미 정렬된 앞부분 비교 제외
    /* static void bubbleSort(int[] a, int n) {
        for (int i = 0; i < n - 1; i++) {
            int exchg = 0;                          // 패스에서 교환하는 횟수
            for (int j = n - 1; j > i; j--)
                if (a[j - 1] > a[j]) {
                    swap(a, j - 1, j);
                    exchg++;
                }
            if (exchg == 0) break;                // 교환이 이루어지지 않으면 멈춤
        }
    } */

    //--- 버블 정렬(버전 3: 스캔 범위를 한정)---//
    static void bubbleSort(int[] a, int n) {
        int k = 0;                               // a[k]보다 앞쪽은 정렬을 마침
        while (k < n - 1) {
            int last = n - 1;                    // 마지막으로 교환한 위치
            for (int j = n - 1; j > k; j--)
                if (a[j - 1] > a[j]) {
                    swap(a, j - 1, j);
                    last = j;
                }
            k = last;
        }
    }

    // 6-3. 단순 정렬 선택
    // 가장 작은 요소부터 선택해 알맞은 위치로 옮겨 순서대로 정렬하는 알고리즘

    // 요솟값 비교 횟수는 (n^2 - n) / 2 회
    // 이 알고리즘은 서로 떨어져 있는 요소를 교환하므로 안정적이지 않음, 중복 요소의 순서가 두바뀌는 경우가 있음

    //--- 단순 선택 정렬 ---//
    static void selectionSort(int[] a, int n) {
        for (int i = 0; i < n - 1; i++) {
            int min = i;                // 아직 정렬되지 않은 부분에서 가장 작은 요소의 인덱스를 저장
            for (int j = i + 1; j < n; j++) // 최소값 인덱스 찾기
                if (a[j] < a[min])
                    min = j;
            swap(a, i, min);           // 아직 정렬되지 않은 부분의 첫 요소와 가장 작은 요소를 교환
        }
    }

    // 6-4. 단순 삽입 정렬
    // 선택 요소를 그보다 더 앞쪽의 알맞은 위치에 '삽입하는' 작업을 반복하는 알고리즘
    // 단순 선택 정렬은 값이 가장 작은 요소를 선택, 알맞은 위치로 옮김
    // 요소들이 서로 뒤바뀌지 않아 안정적. 비교 횟수와 교환 횟수는 n^2 / 2 회

    // 세가지 단순 정렬(버블, 선택, 삽입)의 시간 복잡도는 모두 O(n^2)
    // 효율이 좋지는 않음.
    
    //--- 단순 삽입 정렬 ---//
    static void insertionSort(int[] a, int n) {
        for (int i = 1; i < n; i++) {
            int j;
            int tmp = a[i];
            for (j = i; j > 0 && a[j - 1] > tmp; j--)
                a[j] = a[j - 1];
            a[j] = tmp;
        }
    }

    public static void main(String[] args) {
        int[] x = {11, 5, 3, 9, 7, 10};

        bubbleSort(x, x.length); // 배열 x를 단순교환정렬

        System.out.println("오름차순으로 정렬했습니다.");
        for (int i = 0; i < x.length; i++)
            System.out.print(x[i] + " ");
    }
}
