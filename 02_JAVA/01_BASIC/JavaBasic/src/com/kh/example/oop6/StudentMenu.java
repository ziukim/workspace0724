package com.kh.example.oop6;

public class StudentMenu {
    private StudentController sc = new StudentController();

    public StudentMenu() {
    	Student[] stArr = sc.printStudent();
    	
    	System.out.println("==========학생 정보 출력==========");
        for (Student st : stArr) {
        	if(st == null) {
        		break;
        	}
            System.out.println(st.inform()); 
        }

        double[] scoreArr = sc.avgScore();
        System.out.println("==========학생 성적 출력==========");
        System.out.println("학생 점수 합계 : " + scoreArr[0]);
        System.out.println("학생 점수 평균 : " + scoreArr[1]);
        
        System.out.println("==========성적 결과 출력==========");
        for(Student st : stArr) {
        	if(st.getScore() < sc.CUT_LINE){
        		System.out.println(st.getName() + "학생은 재시험 대상입니다.");
        	} else {
        		System.out.println(st.getName() + "학생은 통과입니다.");
        	}
                        
        }
    }
}