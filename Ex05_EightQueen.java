// 8퀸 문제 풀이

class Ex05_EightQueen {
    static boolean[] flag_a = new boolean[8];     // 각 행에 퀸이 이미 배치되었는가?
    static boolean[] flag_b = new boolean[15];    // 대각선 ↙에 퀸이 이미 배치되었는가?
    static boolean[] flag_c = new boolean[15];    // 대각선 ↘에 퀸이 이미 배치되었는가?
    static int[] pos = new int[8];    // 각 열의 퀸의 위치

    //--- 각 열의 퀸의 위치를 출력 ---//
    static void print() {
        for (int i = 0; i < 8; i++)
            System.out.printf("%2d", pos[i]);
        System.out.println();
    }

    //--- i열의 알맞은 위치에 퀸을 배치 ---//
    static void set(int i) {
        for (int j = 0; j < 8; j++) {
            if (flag_a[j] == false &&                    // 가로(j행)에 아직 배치하지 않음
                    flag_b[i + j] == false &&            // 대각선 ↙에 아직 배치하지 않음
                    flag_c[i - j + 7] == false) {        // 대각선 ↘에 아직 배치하지 않음
                pos[i] = j;                              // 퀸을 j행에 배치함
                if (i == 7)                              // 모든 열에 배치함
                    print();
                else {
                    flag_a[j] = flag_b[i + j] = flag_c[i - j + 7] = true;
                    set(i + 1);
                    flag_a[j] = flag_b[i + j] = flag_c[i - j + 7] = false;
                }
            }
        }
    }

    public static void main(String[] args) {
        set(0);
    }
}