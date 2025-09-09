package com.jiwoo.play;

import java.util.*;

public class KeyboardEngKor{

    // 1) 영문 키보드 → 두벌식 자모 매핑
    private static final Map<Character, String> keyMap = new HashMap<>();
    static {
        String lower = "qwertyuiopasdfghjklzxcvbnm";
        String[] lowerMap = {
            "ㅂ","ㅈ","ㄷ","ㄱ","ㅅ","ㅛ","ㅕ","ㅑ","ㅐ","ㅔ",
            "ㅁ","ㄴ","ㅇ","ㄹ","ㅎ","ㅗ","ㅓ","ㅏ","ㅣ",
            "ㅋ","ㅌ","ㅊ","ㅍ","ㅠ","ㅜ","ㅡ"
        };
        for (int i = 0; i < lower.length(); i++) {
            keyMap.put(lower.charAt(i), lowerMap[i]);
        }
        // Shift(대문자)
        keyMap.put('Q',"ㅃ"); keyMap.put('W',"ㅉ"); keyMap.put('E',"ㄸ");
        keyMap.put('R',"ㄲ"); keyMap.put('T',"ㅆ"); keyMap.put('O',"ㅒ");
        keyMap.put('P',"ㅖ");
        // 나머지 대문자 -> 소문자와 동일
        for (char ch = 'A'; ch <= 'Z'; ch++) {
            if (!keyMap.containsKey(ch) && keyMap.containsKey(Character.toLowerCase(ch))) {
                keyMap.put(ch, keyMap.get(Character.toLowerCase(ch)));
            }
        }
    }

    // 2) 초성 / 중성 / 종성 테이블
    private static final String[] CHO  = {"ㄱ","ㄲ","ㄴ","ㄷ","ㄸ","ㄹ","ㅁ","ㅂ","ㅃ","ㅅ","ㅆ","ㅇ","ㅈ","ㅉ","ㅊ","ㅋ","ㅌ","ㅍ","ㅎ"};
    private static final String[] JUNG = {"ㅏ","ㅐ","ㅑ","ㅒ","ㅓ","ㅔ","ㅕ","ㅖ","ㅗ","ㅘ","ㅙ","ㅚ","ㅛ","ㅜ","ㅝ","ㅞ","ㅟ","ㅠ","ㅡ","ㅢ","ㅣ"};
    private static final String[] JONG = {"","ㄱ","ㄲ","ㄳ","ㄴ","ㄵ","ㄶ","ㄷ","ㄹ","ㄺ","ㄻ","ㄼ","ㄽ","ㄾ","ㄿ","ㅀ","ㅁ","ㅂ","ㅄ","ㅅ","ㅆ","ㅇ","ㅈ","ㅊ","ㅋ","ㅌ","ㅍ","ㅎ"};

    // 복모음/받침 결합
    private static final Map<String,String> V_COMB = new HashMap<>();
    private static final Map<String,String> F_COMB = new HashMap<>();
    static {
        V_COMB.put("ㅗㅏ","ㅘ"); V_COMB.put("ㅗㅐ","ㅙ"); V_COMB.put("ㅗㅣ","ㅚ");
        V_COMB.put("ㅜㅓ","ㅝ"); V_COMB.put("ㅜㅔ","ㅞ"); V_COMB.put("ㅜㅣ","ㅟ");
        V_COMB.put("ㅡㅣ","ㅢ");

        F_COMB.put("ㄱㅅ","ㄳ"); F_COMB.put("ㄴㅈ","ㄵ"); F_COMB.put("ㄴㅎ","ㄶ");
        F_COMB.put("ㄹㄱ","ㄺ"); F_COMB.put("ㄹㅁ","ㄻ"); F_COMB.put("ㄹㅂ","ㄼ");
        F_COMB.put("ㄹㅅ","ㄽ"); F_COMB.put("ㄹㅌ","ㄾ"); F_COMB.put("ㄹㅍ","ㄿ");
        F_COMB.put("ㄹㅎ","ㅀ"); F_COMB.put("ㅂㅅ","ㅄ");
    }

    private static boolean isVowel(String j) {
        return Arrays.asList(JUNG).contains(j);
    }

    // 영문 → 자모 변환
    private static String enToJamo(String text) {
        StringBuilder sb = new StringBuilder();
        for (char ch : text.toCharArray()) {
            sb.append(keyMap.getOrDefault(ch, String.valueOf(ch)));
        }
        return sb.toString();
    }

    // 자모 → 완성형 한글 조합
    private static String compose(String jamoText) {
        List<String> res = new ArrayList<>();
        int i = 0, L = jamoText.length();
        while (i < L) {
            String ch = String.valueOf(jamoText.charAt(i));

            // 자모가 아니면 그대로 출력
            if (!Arrays.asList(CHO).contains(ch) && !isVowel(ch) && !Arrays.asList(JONG).contains(ch)) {
                res.add(ch); i++; continue;
            }

            // 초성
            String cho, jung, jong = "";
            if (isVowel(ch)) {
                cho = "ㅇ"; jung = ch; i++;
            } else {
                cho = ch; i++;
                if (i >= L) { res.add(cho); break; }
                String nxt = String.valueOf(jamoText.charAt(i));
                if (!isVowel(nxt)) { res.add(cho); continue; }
                jung = nxt; i++;
            }

            // 복모음 결합
            if (i < L && isVowel(String.valueOf(jamoText.charAt(i)))) {
                String pair = jung + jamoText.charAt(i);
                if (V_COMB.containsKey(pair)) {
                    jung = V_COMB.get(pair); i++;
                }
            }

            // 종성
            if (i < L) {
                String c1 = String.valueOf(jamoText.charAt(i));
                if (Arrays.asList(CHO).contains(c1) || Arrays.asList(JONG).contains(c1)) {
                    // 다음이 모음이 아니면 종성 처리
                    if (!(i+1 < L && isVowel(String.valueOf(jamoText.charAt(i+1))))) {
                        jong = c1; i++;
                        if (i < L) {
                            String c2 = String.valueOf(jamoText.charAt(i));
                            String comb = F_COMB.get(jong + c2);
                            if (comb != null) { jong = comb; i++; }
                        }
                    }
                }
            }

            if (Arrays.asList(CHO).contains(cho) && Arrays.asList(JUNG).contains(jung) &&
               (jong.equals("") || Arrays.asList(JONG).contains(jong))) {
                int cho_i  = Arrays.asList(CHO).indexOf(cho);
                int jung_i = Arrays.asList(JUNG).indexOf(jung);
                int jong_i = JONG_LIST_INDEX(jong);
                char syll = (char)(0xAC00 + (cho_i * 21 + jung_i) * 28 + jong_i);
                res.add(String.valueOf(syll));
            } else {
                res.add(cho); res.add(jung); if (!jong.isEmpty()) res.add(jong);
            }
        }
        return String.join("", res);
    }

    private static int JONG_LIST_INDEX(String jong) {
        for (int i=0; i<JONG.length; i++) {
            if (JONG[i].equals(jong)) return i;
        }
        return 0;
    }

    // 외부에서 사용하는 함수
    public static String fixTypoEnToKr(String text) {
        return compose(enToJamo(text));
    }

    // 사용 예시
    public static void main(String[] args) {
        String s = "sork roEJrrkxdl dlqfurgoeh ckfEjrrkxdl dkfdkemesmsrnsk?";
        System.out.println(fixTypoEnToKr(s));
        // 기대 출력: 내가 개떡같이 입력해도 찰떡같이 알아듣는구나?
    }
}
