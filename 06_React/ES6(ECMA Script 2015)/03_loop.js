//1. for문
for(let i = 0; i < 5; i++){
    console.log("for in i : ", i);
}

//2. while문
let j = 0;
while(j < 5){
    console.log("while in j : ", j);
    j++
}

//3. do while문
j=0;
do {
    console.log("do while in j : ", j);
    j++
} while(j < 5);

//4. for ... of
let fruits = ["사과", "배", "딸기", "바나나"];
for(const fruit of fruits){
    console.log(fruit);
}

fruits = [{
    id : 1,
    name : "사과",
    price : 3000
},{
    id : 2,
    name : "배",
    price : 5000
},{
    id : 3,
    name : "딸기",
    price : 7000
},{
    id : 4,
    name : "바나나",
    price : 10000
}]

for(const fruit of fruits){
    console.log(fruit.id + " :" + fruit.name);
}

//5. for ... in

const apple = {
    id : 4,
    name : "사과",
    price : 3000
}

for(let k in apple){
    console.log(k + " : " + apple[k]);
}

//6. forEach
//배열 순회 전용 메서드
fruits = [{
    id : 1,
    name : "사과",
    price : 3000
},{
    id : 2,
    name : "배",
    price : 5000
},{
    id : 3,
    name : "딸기",
    price : 7000
},{
    id : 4,
    name : "바나나",
    price : 10000
}]

fruits.forEach((obj, index) => {
    console.log(`forEach : ${index} -> ${obj.name}`);
})

const numbers = [1, 3, 5, 7, 9];
//7. map()
//기존 배열을 가지고 새로운 배열을 만들고 싶을 때 -> 변형된 새로운 배열을 반환
//서버로부터 받은 데이터를 통해서 대칭되는 UI를 만들어 낼 때 사용용
//[1, 9, 25, 49, 81]
const squared = numbers.map((num) => num*num); //내부함수의 리턴값을 통한 새로운 배열을 반환.
console.log(`map의 결과 : ${squared}`);

//8. filter()
// 조건에 맞는 요소만 추출하고 싶을 때 -> 조건에 맞는 요소만 있는 새로운 배열을 반환
// 서버로부터 데이터를 삭제하고 이를 UI상태에 반영해 줄 때 많이 사용함함
//[3, 9]
const squared2 = numbers.filter((num) => num % 3 === 0); //내부함수의 리턴값이 true인 요소만 있는 새로운 배열을 반환.
console.log(squared2);

//9. find()
// 조건에 맞는  "첫 번째" 요소만 추출하고 싶을 때 -> 조건에 맞는 값 하나 검색
const squared3 = numbers.find((num) => num % 3 === 0);
console.log(`find의 결과 : ${squared3}`);

//10. some()
//하나라도 조건을 만족하면 true
const hasSquared = numbers.some((num) => num % 3 === 0);
console.log(`하나라도 조건을 만족하면면 : ${hasSquared}`);

const hasSquared2 = numbers.some((num) => num % 2 === 0);
console.log(`하나라도 조건을 만족하면면 : ${hasSquared2}`);

//11. every()
//모두가 조건을 만족하면 true
const allSquared = numbers.every((num) => num % 3 === 0);
console.log(`하나라도 조건을 만족하면면 : ${allSquared}`);

const allSquared2 = numbers.every((num) => num % 2 === 1);
console.log(`하나라도 조건을 만족하면면 : ${allSquared2}`);

//12. reduce()
//배열의 값을 누적하여 하나의 결과값을 도출
// 배열.reduce((누적값, 배열요소) => {실행할 코드 return 누적값;}, 누적값의 초기값);

const result = numbers.reduce((sum, num) => {
    console.log("합 : " + sum + " 값 : " + num);
    sum += num;  
    return sum;
}, 0);

console.log(result);

const result2 = numbers.reduce((sum, num) => {
    console.log("합 : " + sum + " 값 : " + num);
    sum.push(num);  
    return sum;
}, []);

console.log(result2);

const stdList = [{
    name : "김지우",
    age : 20,
    score : 80
},{
    name : "이지우",
    age : 21,
    score : 90
},{
    name : "박지우",
    age : 22,
    score : 70
}];

let scoreMap = stdList.reduce((scoreMap, std) => {
    scoreMap[std.name] = std.score;
    return scoreMap;
}, {});

console.log(scoreMap);

//----------------------------------------------------------------------------------------------------------------------
//13. sort()
//배열의 요소를 정렬
// 배열.sort((a, b) => {실행할 코드 return a - b;});
const sorted = numbers.sort((a, b) => a - b);
console.log(sorted);   //오름차순       

const sorted2 = numbers.sort((a, b) => b - a);
console.log(sorted2);   //내림차순

const sorted3 = numbers.sort((a, b) => a - b);
console.log(sorted3);   //오름차순

const sorted4 = numbers.sort((a, b) => b - a);
console.log(sorted4);   //내림차순

//14. reverse()
//배열의 요소를 역순으로 정렬
// 배열.reverse();
const reversed = numbers.reverse();
console.log(reversed);

//15. concat()
//배열을 결합하여 새로운 배열을 반환
// 배열.concat(배열);
const concatenated = numbers.concat(numbers);
console.log(concatenated);

//16. slice()
//배열의 일부를 추출하여 새로운 배열을 반환
// 배열.slice(시작인덱스, 종료인덱스);
const sliced = numbers.slice(0, 3);
console.log(sliced);

//17. splice()
//배열의 일부를 삭제하여 새로운 배열을 반환
// 배열.splice(시작인덱스, 삭제할 요소수);  