package com.kh.example.oop6;

public class StudentController {

    private Student[] sArr = new Student[5];
  
    public static final int CUT_LINE = 60;
    
    public StudentController() {
        sArr[0] = new Student("김길동", "자바", 100);
        sArr[1] = new Student("박길동", "디비", 50);
        sArr[2] = new Student("이길동", "화면", 85);
        sArr[3] = new Student("정길동", "서버", 60);
        sArr[4] = new Student("홍길동", "자바", 20);
    }
  
    public Student[] printStudent() {
        return sArr; 
    }

    public int sumScore() {
        int sum = 0;
        for (Student st : sArr) {
        	if(st == null)
        		break;
            sum += st.getScore();
        }
        return sum;
    }

    public double[] avgScore() {
       double[] scoreArr = new double[2];
       
       int sum = sumScore();
       scoreArr[0] = sumScore();
       scoreArr[1] = (double)sum / sArr.length;
       
       return scoreArr;
    }

    public int countPassed() {
        int cnt = 0;
        for (Student s : sArr) {
            if (s.getScore() >= CUT_LINE) cnt++;
        }
        return cnt;
    }
}
