//js class

//1. 클래스 선언
// - 객체를 생성하기 위한 설계도
// - 데이터(변수)와 기능(함수), 생성자를 포함하고 있다.

//class Person{
const Person = class{
    //js에서 생성자는 명확하게 이름을 constructor로 정해둠.
    constructor(name, age){
        this.name = name;
        this.age = age;   
        this.gender = "M";
    }

    //메서드
    getAge(){
        return this.age;
    }

    setAge(age){
        this.age = age;
    }

    printInfo(){
        console.log(`제 이름은 ${this.name}이고, 나이는 ${this.age}살 입니다.`);
    }
}       

const user1 = new Person("김지우", 20);
user1.printInfo();
console.log(user1);

//클래스 상속

class Student extends Person{
    constructor(name, age, grade){
        super(name, age);
        this.grade = grade;
    }

    introduce(){
        console.log(`제 이름은 ${this.name}이고, 학년은 ${this.grade}입니다.`);
    }
}

const st1 = new Student("김지우", 20, 1);
st1.printInfo();
st1.introduce();

// function Student(){}
// Student.prototype.introduce = function(){console.log(`제 이름은 ${this.name}이고, 학년은 ${this.grade}입니다.`);};

// 자바스크립트의 object로도 필드와 메서드를 포함하는 객체를 만들 수 있음
const car = {
    name : "쏘나타",
    brand : "현대",
    drive : function(){
        console.log("부릉부릉~");
    }
}

console.log(car.name);
console.log(car.brand);
car.drive();

const newCar = {
    ...car,
    name : "아반떼"
}
console.log(newCar.name);

//static 키워드 사용
class Math{
    static add(a, b){
        return a + b;
    }
}

console.log(Math.add(1, 7));

// function Math(){}
// Math.add = function(a, b){
//     return a + b;
// }