class IntStack {
    private int[] stk;           // 스택용 배열
    private int capacity;        // 스택의 크기
    private int ptr;             // 스택 포인터

    //--- 실행시 예외: 스택이 비어있음 ---//
    public class EmptyIntStackException extends RuntimeException {
        public EmptyIntStackException() { }
    }

    //--- 실행시 예외: 스택이 가득 참 ---//
    public class OverflowIntStackException extends RuntimeException {
        public OverflowIntStackException() { }
    }

    //--- 생성자(constructor) ---//
    public IntStack(int maxlen) {
        ptr = 0;
        capacity = maxlen;
        try {
            stk = new int[capacity];          // 스택 본체용 배열을 생성
        } catch (OutOfMemoryError e) {        // 생성할 수 없음
            capacity = 0;
        }
    }

    //--- 스택에 x를 푸시 ---//
    public int push(int x) throws OverflowIntStackException {
        // if (ptr == capacity) // ==도 가능, 하지만 프로그래밍 실수와 같은 이유로 ptr 값이 잘못 입력되는 경우 max를 초과 할 수도 있음.
                                // 부등호 판단 시 스택 본체 배열의 상한, 하한을 벗어나 접근하는 것을 막을 수 있다.
        if (ptr >= capacity)                                // 스택이 가득 참
            throw new OverflowIntStackException();
        return stk[ptr++] = x;
    }

    //--- 스택에서 데이터를 팝(정상에 있는 데이터를 꺼냄) ---//
    public int pop() throws EmptyIntStackException {
        if (ptr <= 0)                                          // 스택이 빔
            throw new EmptyIntStackException();
        return stk[--ptr];
    }

    //--- 스택에서 데이터를 피크(peek, 정상에 있는 데이터를 들여다봄) ---//
    public int peek() throws EmptyIntStackException {
        if (ptr <= 0)                                        // 스택이 빔
            throw new EmptyIntStackException();
        return stk[ptr - 1];
    }

    //--- 스택을 비움 ---//
    public void clear() {
        ptr = 0;
    }
    //--- 스택에서 x를 찾아 인덱스(없으면 –1)를 반환 ---//
    public int indexOf(int x) {
        for (int i = ptr - 1; i >= 0; i--)     // 꼭대기 쪽부터 선형 검색
            if (stk[i] == x)
                return i;         // 검색 성공
        return -1;                // 검색 실패
    }

    //--- 스택의 크기를 반환 ---//
    public int getCapacity() {
        return capacity;
    }

    //--- 스택에 쌓여있는 데이터 갯수를 반환 ---//
    public int size() {
        return ptr;
    }

    //--- 스택이 비어있는가? ---//
    public boolean isEmpty() {
        return ptr <= 0;
    }

    //--- 스택이 가득 찼는가? ---//
    public boolean isFull() {
        return ptr >= capacity;
    }

    //--- 스택 안의 모든 데이터를 바닥 → 정상 순서로 표시 ---//
    public void dump() {
        if (ptr <= 0)
            System.out.println("스택이 비어있습니다.");
        else {
            for (int i = 0; i < ptr; i++)
                System.out.print(stk[i] + " ");
            System.out.println();
        }
    }
}

public class Ex04 {
    // 스택과 큐
    public static void main(String[] args) {
        // 스택이란? 데이터를 일시적으로 저장하기 위한 자료구조로 후입선출. LIFO : Last In First Out
        // 데이터를 넣는 것은 푸시(Push), 꺼내는 작업은 팝(Pop)

        // 자바 프로그램에서 메서드 호출하고 실행할 때 프로그램 내부에서 '스택'을 사용함

    }
}
