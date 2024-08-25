// 이진검색트리

import java.util.Comparator;

public class Ex10_BinTreeSt<K, V> {
    // 트리- 데이터 사이의 계층 관계를 나타내는 자료구조

    // 트리의 구성 요소는 노드(node : O)와 가지(edge : -)
    // 각각의 노드는 가지를 통해 다른 노드와 연결되어 있음

    // 루트(root) : 가장 윗부분에 위치하는 노드, 하나의 트리에 하나의 루트
    // 리프(leaf) : 트리의 가장 아랫부분에 위치하는 노드, 즉 더 이상 뻗어나갈 수 없는 마지막에 노드가 위치한다는 의미
    // 안쪽 노드 : 루트 포함, 리프를 제외한 노드
    // 자식(child) : 어떤 노드로부터 가지로 연결된 아래쪽 노드를 자식이라 함
    // 부모(parent) : 어떤 노드에서 가지로 연결된 위쪽 노드, 노드는 1개의 부모를 가짐
    // 형제(sibiling) : 같은 부모를 가지는 노드
    // 조상(ancestor) : 어떤 노드에서 가지로 연결된 위쪽 노드를 모두 조상이라 함
    // 자손(descendant) : 어떤 노드에서 가지로 연결된 아래쪽 노드 모두를 자손이라 함
    // 레벨(level) : 루트로부터 얼마나 떨어져 있는지에 대한 값, 루트의 레벨은 0
    //               가지가 하나씩 아래로 뻗어나갈 때마다 레벨이 1씩 증가
    // 차수(degree) : 노드가 갖는 자식의 수
    // 높이(height) : 루트로부터 가장 멀리 떨어진 리프까지의 거리(리프 레벨의 최댓값)
    // 서브 트리(subtree) : 트리 안에서 다시 어떤 노드를 루트로 정하고 그 자손으로 이루어진 트리
    // 널 트리(null tree) : 노드, 가지가 없는 트리

    // 순서 트리와 무순서 트리
    // 순서 트리(order tree) : 형제 노드의 순서를 따짐
    // 무순서 트리(unorder tree) : 형제 노드의 순서를 따지지 않음

    // 순서 트리 탐색(371p, 예시를 보면 이해하기 편함)
    // 너비 우선 탐색, 깊이 우선 탐색
    // 전위 순회(Preorder) : 노드 방문 -> 왼쪽 자식 -> 오른쪽 자식
    // 중위 순회(Inorder) : 왼쪽 자식 -> 노드 방문 -> 오른쪽 자식
    // 후위 순회(Postorder) : 왼쪽 자식 -> 오른쪽 자식 -> (돌아와)노드 방문


    // 이진트리와 이진검색트리
    // 이진트리(binary tree) : 노드가 왼쪽 자식과 오른쪽 자식이 갖는 트리
    //                        각 노드의 자식은 2 이하만 유지!
    // 완전이진트리(complete binary tree) : 루트부터 노드가 채워져 있으면서 
    //                        같은 레벨에서는 왼쪽에서 오른쪽으로 노드가 채워져 있는 이진트리

    // 1. 마지막 레벨을 제외한 레벨은 노드를 가득 채운다.
    // 2. 마지막 레벨은 왼쪽부터 오른쪽 방향으로 노드를 채우되 반드시 끝까지 채울 필요는 없다.


    // 이진검색트리(binary search tree)는 이진트리가 다음 조건을 만족하면 됨
    // 1. 어떤 노드 N을 기준으로 왼쪽 서브 트리 노드의 모든 키 값은 노드 N의 키 값보다 작아야 함
    // 2. 오른쪽 서브 트리 노드의 키 값은 노드 N의 키 값보다 커야함.
    // 3. 같은 키 값을 갖는 노드는 없음.

    // 중위 순회하면 키 값의 오름차순으로 노드를 얻을 수 있음. 구조 단순. 노드 삽입이 쉬움 - 폭 넓게 사용됨

    //--- 노드 ---//
    // Node<K, V>는 1개의 생성자, 3개의 메서드가 있음

/*    static class Node<K,V> {
        private K key;              // 키값
        private V data;             // 데이터
        private Node<K,V> left;     // 왼쪽 포인터(왼쪽 자식노드에 대한 참조)
        private Node<K,V> right;    // 오른쪽 포인터(오른쪽 자식노드에 대한 참조)

        //--- 생성자(constructor) ---//
        Node(K key, V data, Node<K,V> left, Node<K,V> right) {
            this.key   = key;
            this.data  = data;
            this.left  = left;
            this.right = right;
        }

        //--- 키값을 반환 ---//
        K getKey() {
            return key;
        }

        //--- 데이터를 반환 ---//
        V getValue() {
            return data;
        }

        //--- 데이터를 표시 ---//
        void print() {
            System.out.println(data);
        }
    }

    private Node<K,V> root;                             // 루트
    private Comparator<? super K> comparator = null;    // 비교자(Comparator)

    //--- 생성자(constructor) ---//
    public Ex10_BinTreeSt() {
        root = null;
    }

    //--- 생성자(constructor) ---//
    public Ex10_BinTreeSt(Comparator<? super K> c) {
        this();
        comparator = c;
    }

    //--- 두 키값을 비교 ---//
    private int comp(K key1, K key2) {
        return (comparator == null) ? ((Comparable<K>)key1).compareTo(key2)
                                    : comparator.compare(key1, key2);
    }

    //--- 키로 검색 ---//
    public V search(K key)    {
        Node<K,V> p = root;                                    // 루트에 주목

        while (true) {
            if (p == null)                                     // 더 이상 나아갈 수 없으면
                return null;                                   // …검색 실패
            int cond = comp(key, p.getKey());                  // key와 노드 p의 키를 비교
            if (cond == 0)                                     // 같으면
                return p.getValue();                           // …검색 성공
            else if (cond < 0)                                 // key 쪽이 작으면
                p = p.left;                                    // …왼쪽 서브트리에서 검색
            else                                               // key 쪽이 크면
                p = p.right;                                   // …오른쪽 서브트리에서 검색
        }
    }

    //--- node를 뿌리로 하는 서브트리에 노드 <K,V>를 삽입 ---//
    private void addNode(Node<K,V> node, K key, V data) {
        int cond = comp(key, node.getKey());
        if (cond == 0)
            return;                                       // key가 이진검색트리에 이미 존재
        else if (cond < 0) {
            if (node.left == null)
                node.left = new Node<K,V>(key, data, null, null);
            else
                addNode(node.left, key, data);            // 왼쪽 서브트리에 주목
        } else {
            if (node.right == null)
                node.right = new Node<K,V>(key, data, null, null);
            else
                addNode(node.right, key, data);           // 오른쪽 서브트리에 주목
        }
    }

    //--- 노드 삽입 ---//
    public void add(K key, V data) {
        if (root == null)
            root = new Node<K,V>(key, data, null, null);
        else
            addNode(root, key, data);
    }

    //--- 키값이 key인 노드를 삭제 --//
    public boolean remove(K key) {
        Node<K,V> p = root;                    // 스캔 중인 노드
        Node<K,V> parent = null;               // 스캔 중인 노드의 부모노드
        boolean isLeftChild = true;            // p는 parent의 왼쪽 자식노드인가?

        while (true) {
            if (p == null)                           // 더 이상 나아갈 수 없으면
                return false;                        // …그 키값은 존재하지 않음
            int cond = comp(key, p.getKey());        // key와 노드 p의 키값을 비교
            if (cond == 0)                           // 같으면
                break;                               // …검색 성공
            else {
                parent = p;                          // 가지로 내려가기 전에 부모를 설정
                if (cond < 0) {                      // key 쪽이 작으면
                    isLeftChild = true;              // …왼쪽의 자식으로 내려감
                    p = p.left;                      // …왼쪽 서브트리에서 검색
                } else {                             // key 쪽이 크면
                    isLeftChild = false;             // …오른쪽의 자식으로 내려감
                    p = p.right;                     // …오른쪽 서브트리에서 검색
                }
            }
        }

        if (p.left == null) {                         // p에 왼쪽의 자식이 없음
            if (p == root)                     
                root = p.right;
            else if (isLeftChild)
                parent.left  = p.right;                    // 부모의 왼쪽 포인터가 오른쪽 자식을 가리킴
            else
                parent.right = p.right;                    // 부모의 오른쪽 포인터가 오른쪽 자식을 가리킴
        } else if (p.right == null) {                      // p에 오른쪽 자식이 없음…
            if (p == root)
                root = p.left;
            else if (isLeftChild)
                parent.left  = p.left;                    // 부모의 왼쪽 포인터가 왼쪽 자식을 가리킴
            else
                parent.right = p.left;                    // 부모의 오른쪽 포인터가 왼쪽 자식을 가리킴
        } else {
            parent = p;
            Node<K,V> left = p.left;                     // 서브트리 가운데 최대 노드
            isLeftChild = true;
            while (left.right != null) {                 // 최대 노드의 left를 검색
                parent = left;
                left = left.right;
                isLeftChild = false;
            }
            p.key  = left.key;                           //  left의 키값을 p로 이동
            p.data = left.data;                          //  left의 데이터를 p로 이동
            if (isLeftChild)
                parent.left  = left.left;                // left를 삭제
            else
                parent.right = left.left;                // left를 삭제
        }
        return true;
    }

    //--- node를 루트로 하는 서브트리의 노드를 키값의 오름차순으로 표시 ---//
    private void printSubTree(Node node) {
        if (node != null) {
            printSubTree(node.left);                            // 왼쪽 서브트리를 키값의 오름차순으로 표시
            System.out.println(node.key + " " + node.data);     // node를 표시
            printSubTree(node.right);                           // 오른쪽 서브트리를 키값의 오름차순으로 표시
        }
    }

    //--- 전체 노드를 키값의 오름차순으로 표시 ---//
    public void print() {
        printSubTree(root);
    }   */


}
