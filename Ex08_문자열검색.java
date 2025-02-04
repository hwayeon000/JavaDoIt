public class Ex08_문자열검색 {
    // 브루트-포스법 / KMP법 / Boyer-Moore법

    // 브루트-포스법 (brute force method)
    // 문자열의 기초. 텍스트에서 패턴(pat)을 검색하여 텍스트의 위치(인덱스)를 반환

    //--- 브루트-포스법에 의한 문자열 검색 ---//
    static int bfMatch(String txt, String pat) {
        int pt = 0;        // txt 커서
        int pp = 0;        // pat 커서

        while (pt != txt.length() && pp != pat.length()) {
            if (txt.charAt(pt) == pat.charAt(pp)) {
                pt++;
                pp++;
            } else {
                pt = pt - pp + 1;
                pp = 0;
            }
        }
        if (pp == pat.length())        // 검색 성공
            return pt - pp;
        return -1;                     // 검색 실패
    }


    // KMP법

    //--- KMP법에 의한 문자열 검색 ---//
    static int kmpMatch(String txt, String pat) {
        int pt = 1;                                // txt를 따라가는 커서
        int pp = 0;                                // pat를 따라가는 커서
        int[] skip = new int[pat.length() + 1];    // 건너뛰기 표(skip 테이블)

        // skip 테이블 작성
        skip[pt] = 0;
        while (pt != pat.length()) {
            if (pat.charAt(pt) == pat.charAt(pp))
                skip[++pt] = ++pp;
            else if (pp == 0)
                skip[++pt] = pp;
            else
                pp = skip[pp];
        }

        // 검색
        pt = pp = 0;
        while (pt != txt.length() && pp != pat.length()) {
            if (txt.charAt(pt) == pat.charAt(pp)) {
                pt++;
                pp++;
            } else if (pp == 0)
                pt++;
            else
                pp = skip[pp];
        }

        if (pp == pat.length())        // 패턴의 모든 문자를 대조
            return pt - pp;
        return -1;                     // 검색 실패
    }

    // Boyer-Moore법
    // R. S. boyer와 J. S. Moore가 만든 Boyer-Moore법은 KMP보다 효율이 더 좋음
    //--- 보이어-무어법으로 문자열 검색 ---//
    static int bmMatch(String txt, String pat) {
        int pt;                           // txt를 따라가는 커서
        int pp;                           // pat를 따라가는 커서
        int txtLen = txt.length();        // txt의 문자 개수
        int patLen = pat.length();        // pat의 문자 개수
        int[] skip = new int[Character.MAX_VALUE + 1];    // 건너뛰기 표(skip 테이블)

        // skip 테이블 작성
        for (pt = 0; pt <= Character.MAX_VALUE; pt++)
            skip[pt] = patLen;
        for (pt = 0; pt < patLen - 1; pt++)
            skip[pat.charAt(pt)] = patLen - pt - 1;  // pt == patLen - 1
        // 검색
        while (pt < txtLen) {
            pp = patLen - 1;                         // pat의 마지막 문자에 주목

            while (txt.charAt(pt) == pat.charAt(pp)) {
                if (pp == 0)
                    return pt;                  // 검색 성공
                pp--;
                pt--;
            }
            pt += (skip[txt.charAt(pt)] > patLen - pp) ? skip[txt.charAt(pt)] : patLen - pp;
        }
        return -1;                             // 검색 실패
    }
    
    
    

    public static void main(String[] args) {
        
        String s1 = "deabfabgabcd";
        String s2 = "ab";

        int idx1 = s1.indexOf(s2);      // 패턴이 일치하는 첫 인덱스
        int idx2 = s1.lastIndexOf(s2);  // 패턴이 일치하는 마지막 위치 문자열 인덱스

        System.out.println(idx1);  // 2
        System.out.println(idx2);  // 8

    }



}
