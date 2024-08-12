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

// int형 고정 길이 큐
class IntQueue {
    private int[] que;            // 큐용 배열
    private int capacity;         // 큐의 크기
    private int front;            // 맨 처음 요소 커서
    private int rear;             // 맨 끝 요소 커서
    private int num;              // 현재 데이터 개수, 큐 가득 찼는지 구분

    //--- 실행시 예외: 큐가 비어있음 ---//
    public class EmptyIntQueueException extends RuntimeException {
        public EmptyIntQueueException() { }
    }

    //--- 실행시 예외: 큐가 가득 찼음 ---//
    public class OverflowIntQueueException extends RuntimeException {
        public OverflowIntQueueException() { }
    }

    //--- 생성자(constructor) ---//
    public IntQueue(int maxlen) {
        num = front = rear = 0;
        capacity = maxlen;
        try {
            que = new int[capacity];          // 큐 본체용 배열을 생성
        } catch (OutOfMemoryError e) {        // 생성할 수 없음
            capacity = 0;
        }
    }

    
    //--- 큐에 데이터를 인큐 ---//
    public int enque(int x) throws OverflowIntQueueException {
        if (num >= capacity)
            throw new OverflowIntQueueException();            // 큐가 가득 찼음
        que[rear++] = x;
        num++;
        if (rear == capacity)  // 큐의 크기와 끝 커서가 같아지면 배열의 처음인 0으로 변경
            rear = 0;
        return x;
    }

    //--- 큐에서 데이터를 디큐 ---//
    public int deque() throws EmptyIntQueueException {
        if (num <= 0)
            throw new EmptyIntQueueException();            // 큐가 비어있음
        int x = que[front++];
        num--;
        if (front == capacity) // 큐의 크기와 같아지면 0으로
            front = 0;
        return x;
    }

    //--- 큐에서 데이터를 피크(프런트 데이터를 들여다봄) ---//
    public int peek() throws EmptyIntQueueException {
        if (num <= 0)
            throw new EmptyIntQueueException();            // 큐가 비어있음
        return que[front];
    }

    //--- 큐를 비움 ---//
    public void clear() {
        num = front = rear = 0;
    }

    //--- 큐에서 x를 검색하여 인덱스(찾지 못하면 –1)를 반환 ---//
    public int indexOf(int x) {
        for (int i = 0; i < num; i++) {
            int idx = (i + front) % capacity;
            if (que[idx] == x)                // 검색 성공
                return idx;
        }
        return -1;                            // 검색 실패
    }

    //--- 큐의 크기를 반환 ---//
    public int getCapacity() {
        return capacity;
    }

    //--- 큐에 쌓여 있는 데이터 개수를 반환 ---//
    public int size() {
        return num;
    }

    //--- 큐가 비어있는가? ---//
    public boolean isEmpty() {
        return num <= 0;
    }

    //--- 큐가 가득 찼는가? ---//
    public boolean isFull() {
        return num >= capacity;
    }

    //--- 큐 안의 모든 데이터를 프런트 → 리어 순으로 출력 ---//
    public void dump() {
        if (num <= 0)
            System.out.println("큐가 비어있습니다.");
        else {
            for (int i = 0; i < num; i++)
                System.out.print(que[(i + front) % capacity] + " ");
            System.out.println();
        }
    }
}


public class Ex04 {
    // 스택
    // 스택이란? 데이터를 일시적으로 저장하기 위한 자료구조로 후입선출. LIFO : Last In First Out
    // 데이터를 넣는 것은 푸시(push), 꺼내는 작업은 팝(pop)
    // 푸시와 팝을 하는 위치를 탑(top), 스택의 가장 아랫부분은 바닥(bottom)

    // 자바 프로그램에서 메서드 호출하고 실행할 때 프로그램 내부에서 '스택'을 사용함


    // 큐
    // 데이터를 일시적으로 쌓아두기 위한 자료구조로 선입선출. FIFO : First In First Out
    // 데이터를 넣는 것은 인큐(enqueue), 꺼내는 작업을 디큐(dequeue)
    // 꺼내는 쪽을 프런트(front), 넣는 쪽은 리어(rear)
    // 인큐 복잡도 O(1), 디큐는 요소를 앞으로 옮김, O(n)-> 효율이 떨어짐
    // 링 버퍼로 큐를 만들경우 요소 이동 문제 해결 가능. 복잡도 O(1)?
}
