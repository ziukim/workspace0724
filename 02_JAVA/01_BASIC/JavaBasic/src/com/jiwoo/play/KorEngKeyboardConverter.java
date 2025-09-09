package com.jiwoo.play;

import java.util.*;

public class KorEngKeyboardConverter {
    // 자판 매핑 (영 → 한)
    private static final Map<Character, String> enToKo = new HashMap<>();
    private static final Map<String, Character> koToEn = new HashMap<>();

    // 두벌식 자모 배열
    private static final String[] CHO  = {"ㄱ","ㄲ","ㄴ","ㄷ","ㄸ","ㄹ","ㅁ","ㅂ","ㅃ","ㅅ","ㅆ","ㅇ","ㅈ","ㅉ","ㅊ","ㅋ","ㅌ","ㅍ","ㅎ"};
    private static final String[] JUNG = {"ㅏ","ㅐ","ㅑ","ㅒ","ㅓ","ㅔ","ㅕ","ㅖ","ㅗ","ㅘ","ㅙ","ㅚ","ㅛ","ㅜ","ㅝ","ㅞ","ㅟ","ㅠ","ㅡ","ㅢ","ㅣ"};
    private static final String[] JONG = {"","ㄱ","ㄲ","ㄳ","ㄴ","ㄵ","ㄶ","ㄷ","ㄹ","ㄺ","ㄻ","ㄼ","ㄽ","ㄾ","ㄿ","ㅀ","ㅁ","ㅂ","ㅄ","ㅅ","ㅆ","ㅇ","ㅈ","ㅊ","ㅋ","ㅌ","ㅍ","ㅎ"};

    static {
        // 영어 → 한글 자모
        String[] en = {"q","w","e","r","t","y","u","i","o","p",
                       "a","s","d","f","g","h","j","k","l",
                       "z","x","c","v","b","n","m"};
        String[] ko = {"ㅂ","ㅈ","ㄷ","ㄱ","ㅅ","ㅛ","ㅕ","ㅑ","ㅐ","ㅔ",
                       "ㅁ","ㄴ","ㅇ","ㄹ","ㅎ","ㅗ","ㅓ","ㅏ","ㅣ",
                       "ㅋ","ㅌ","ㅊ","ㅍ","ㅠ","ㅜ","ㅡ"};
        for (int i=0; i<en.length; i++) {
            enToKo.put(en[i].charAt(0), ko[i]);
            koToEn.put(ko[i], en[i].charAt(0));
        }
    }

    // 영어 → 한글 자모
    private static String enToJamo(String text) {
        StringBuilder sb = new StringBuilder();
        for (char c : text.toCharArray()) {
            sb.append(enToKo.getOrDefault(c, String.valueOf(c)));
        }
        return sb.toString();
    }

    // 자모 → 음절 조합
    private static String compose(String jamo) {
        StringBuilder result = new StringBuilder();
        int i = 0;
        while (i < jamo.length()) {
            String choChar = String.valueOf(jamo.charAt(i));
            int choIndex = Arrays.asList(CHO).indexOf(choChar);

            if (choIndex == -1) { // 초성 아님
                result.append(choChar);
                i++;
                continue;
            }

            if (i + 1 >= jamo.length()) { // 중성 없음
                result.append(choChar);
                i++;
                continue;
            }

            String jungChar = String.valueOf(jamo.charAt(i + 1));
            int jungIndex = Arrays.asList(JUNG).indexOf(jungChar);

            if (jungIndex == -1) { // 모음 아님
                result.append(choChar);
                i++;
                continue;
            }

            i += 2; // 초성+중성 소비

            // 종성(받침) 후보 확인
            int jongIndex = 0;
            if (i < jamo.length()) {
                String jongChar = String.valueOf(jamo.charAt(i));
                int tmpIndex = Arrays.asList(JONG).indexOf(jongChar);

                if (tmpIndex != -1) {
                    // 뒤에 모음이 있으면 받침 아님 → 초성으로 남김
                    if (i + 1 < jamo.length() && Arrays.asList(JUNG).contains(String.valueOf(jamo.charAt(i + 1)))) {
                        // skip
                    } else {
                        jongIndex = tmpIndex;
                        i++;
                    }
                }
            }

            char syll = (char)(0xAC00 + (choIndex * 21 + jungIndex) * 28 + jongIndex);
            result.append(syll);
        }
        return result.toString();
    }

    // 영어 입력 → 한글 변환
    public static String engToKor(String text) {
        return compose(enToJamo(text));
    }

    // 한글 입력 → 영어 키보드 값 변환
    public static String korToEng(String text) {
        StringBuilder sb = new StringBuilder();
        for (char ch : text.toCharArray()) {
            // 한글이면 분해
            if (ch >= 0xAC00 && ch <= 0xD7A3) {
                int code = ch - 0xAC00;
                int cho = code / 28 / 21;
                int jung = (code / 28) % 21;
                int jong = code % 28;

                sb.append(koToEn.getOrDefault(CHO[cho], '?'));
                sb.append(koToEn.getOrDefault(JUNG[jung], '?'));
                if (jong != 0) sb.append(koToEn.getOrDefault(JONG[jong], '?'));
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    // 실행
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("입력: ");
        String input = sc.nextLine();

        if (input.matches("[a-zA-Z ]+")) { // 영어로만 입력 → 한글 변환
            System.out.println("변환 결과: " + engToKor(input));
        } else { // 한글 포함 입력 → 영어 변환
            System.out.println("변환 결과: " + korToEng(input));
        }
    }
}
