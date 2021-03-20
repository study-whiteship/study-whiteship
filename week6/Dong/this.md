# 자바 공부 - 6주차

## 목차

- 자바 상속의 특징
- super 키워드
- 메소드 오버라이딩
- 다이나믹 메소드 디스패치 (Dynamic Method Dispatch)
- 추상 클래스
- final 키워드
- Object 클래스
- 자율학습
    - Java vector
    - Java arraylist
    - throws IOException
    - BufferedReader
    - StringTokenizer
    - Integer.parseInt
    - +5주차 피드백

<br>
<br>
<br>

# 본문 시작

<br>

# 자바 상속의 특징

- 상속이란?

    부모 클래스에서 정의된 필드와 메소드를 자식 클래스가 물려받는 것

    자바에서는 부모클래스를 Super, 자식을 Sub 라고 부른다

- 자바상속만의 특징
    1. 다중상속을 지원하지 않는다.
    2. 상속의 횟수에 제한을 두지 않는다
    3. 최상위에 "Object" 라는 클래스가 존재한다. 
        - Object 클래스는 super class가 없는 유일한 클래스 (C++은 그런거 없다!, 그리고 이부분은 C#이 떠오른다...)
        - 자바의 모든 클래스들은 Object 클래스의 자식클래스
- 상속 관련 Code

```java
public class Gogi{
    //부모클래스인 gogi
    // ....(코드)....
}
public class Chicken extends Gogi{
    // gogi를 상속받는 자식 클래스 chicken을 선언
    // ....(코드)....
}
```

- 상속 관련 접근 지정자
    1. public : 모두 다 가능
    2. private : 나혼자만 
    3. default : 같은 패키지의 클래스에서만
    4. protected : 상속 받은 클래스끼리만

# super 키워드

- 상속한 하위클래스에서 부모클래스의 메소드나 필드를 참조해야 할때 사용
- 예제

```java
#include <iostream>
#include <string>
#include <cstdio>

using namespace std;

struct timmmmm
{
    int h;
    int m;
    int s;
}typedef TIM;

int main() {

    TIM t1,t2,ans;
    string s1, s2,buf;

    cin >> s1 >> s2;
    //cout << s1 << s2 << endl;

    buf = s1.substr(0, 2);
    t1.h = atoi(buf.c_str());

    buf = s1.substr(3, 2);
    t1.m = atoi(buf.c_str());

    buf = s1.substr(6, 2);
    t1.s = atoi(buf.c_str());

    //

    
    buf = s2.substr(0, 2);
    t2.h = atoi(buf.c_str());

    buf = s2.substr(3, 2);
    t2.m = atoi(buf.c_str());

    buf = s2.substr(6, 2);
    t2.s = atoi(buf.c_str());

    ans.h = t2.h - t1.h;
    ans.m = t2.m - t1.m;
    ans.s = t2.s - t1.s;
    

    if (ans.s < 0) {
        ans.s += 60;
        ans.m--;
    }

    if (ans.m < 0) {
        ans.m += 60;
        ans.h--;
    }

    if (ans.h < 0) {
        ans.h += 24;
        //ans.m--;
    }

    printf("%02d:%02d:%02d\n", ans.h, ans.m, ans.s);
    return 0;
	
/*
실행 결과
10

10

10
*/

}
```

- super

# 메소드 오버라이딩

### 오버라이딩 Overriding

- 부모자식간의 상속관계의 두 클래스 사이에서 메소드를 다시 정의
- 부모의 클래스와 시그니쳐가 동일한 자식 클래스의 메소드를 다시 정의한다

    시그니쳐 : 함수명, 함수인자

- 부모클래스의 메소드를 자식클래스에서 새로 정의해서 사용
- 메소드의 선언부는 같지만 하는일이 달라짐
    1. 메소드의 선언부(리턴타입, 메소드명, 인자값)는 반드시 일치해야함
    2. 접근지정자는 상속받은 부모보다 범위가 같거나 넓어야함

```java
class Controller{
	public void print(){
    	System.out.println("1. Controller");
    }
}

class Motor_con extends Controller{
	public void print(){
    	System.out.println("Motor_con");
    }
}

class Lcd_con extends Controller{
	public void print(){
    	System.out.println("Lcd_con");
    }
}

public class OverridingExam {
	public static void main(String[] args) {
		
		Controller con= new Motor_con();
    con.print();

		
		Controller con= new Lcd_con();
    con.print();

	}
}
```

- 위의 코드에서 출력은 아래와 같다!

    >> Motor_con

    >> Lcd_con

### 오버로딩 Overloading

- 같은 이름의 다른 메소드
    - 입력 파라미터의 형식이 다르거나(int, long, float, string)
    - 입력 파라미터의 갯수가 다르거나(int하나, int int 두개)
    - 하나의 메소드명으로 여러가지 형태의 메소드를 정의할 수 있음

        → C언어에서 함수명이 전체 프로젝트에서 유일한것과는 대조된다

    - 사용자 입장(클래스 외부)에서는 여러가지 타입의 데이터를 하나의 메소드로 처리할 수 있는것처럼 보임

        → 사실은 여러개의 대응할 수 있는 함수를 모조리 만들어놔야 한다.. 

- 상속과는 전혀 연관이 없는, 메서드에 관한 이야기

```java
public class Overloading {
	public static void main(String[] args) {
		test(1);		  // 정수형 타입 int
		test(1l);		  // 뒤에 l을 붙이면 long으로 들어감, 1과 l의 외형이 비슷함을 유의
		test(1.1);		// 소수점 타입의 기본형 double
		test(1.1f);	  // 뒤에 f를 붙이면 float으로 들어감
		test(1, 2);   // 입력파라미터가 두개인경우
	}

	public static void test(int a) {
		System.out.println("int");
	}

	public static void test(long a) {
		System.out.println("long");
	}

	public static void test(double a) {
		System.out.println("double");
	}

	public static void test(float a) {
		System.out.println("float");
	}

	public static void test(int a, int b) {
		System.out.println("int int");
	}

}
```

- 오버로딩? 오버라이딩? 혼동은 그만!

# 다이나믹 메소드 디스패치 (Dynamic Method Dispatch)

# 추상 클래스

# final 키워드

# Object 클래스

```java

```

# Java Arraylist

- 참조 : [https://coding-factory.tistory.com/551](https://coding-factory.tistory.com/551)
- ArrayList는 List 인터페이스를 상속받아 만들어진 Class
- 크기가 가변적으로 변하는 선형리스트(Linked List) 구조로 동작
- 특징
    1. 일반적인 배열과 같은 순차리스트(메모리 연속 할당) 이며 인덱스로 내부의 객체를 관리합니다.
    2. 배열은 한번 생성되면 크기가 변하지 않는것에 비교해서 장점이 있는데,
    3. ArrayList는 객체들이 추가되어 저장 capacity(용량)을 초과한다면 자동으로 부족한 크기만큼 capacity가 커지도록 설정
    4. Capacity 확장이 빈번하게 늘어나면 성능이 하락하므로, 처음 선언시 대략적인 크기를 지정해 메모리를 미리 확보하는게 유리함.

### ArrayList 의 method 정리

```java
ArrayList<Integer> myal = new ArrayList<Integer>()
myal.add(3);     //값 추가
myal.add(1,10);  //index지정 값추가 : 1위치에 10 삽입
myal.remove(1);  //index 1 제거
myal.clear();    //모든 값 제거
System.out.println(myal.size());      //list 크기 : 3
System.out.println(myal.contains(1)); //list에 1이 있는지 검색 : true
System.out.println(myal.indexOf(1));  //1이 있는 index반환 없으면

for(Integer i : myal) {               //for문을 통한 전체출력
    System.out.println(i);
}

Iterator iter = myal.iterator();      //Iterator 선언 
while(iter.hasNext()){                //다음값이 있는지 체크
    System.out.println(iter.next());  //값 출력
}
```

- 아래는 ArrayList  snippet들 정리

### ArrayList 선언

```java
ArrayList list = new ArrayList();                       //타입 미설정 Object로 선언된다.
ArrayList<Gogi> ma_si_zzung = new ArrayList<Gogi>();    //타입설정 Gogi객체만 사용가능
ArrayList<Integer> AL1 = new ArrayList<Integer>();      //타입설정 int타입만 사용가능
ArrayList<Integer> AL3 = new ArrayList<Integer>(10);    //초기 용량(capacity)지정
ArrayList<Integer> AL4 = new ArrayList<Integer>(Arrays.asList(1,2,3));  //생성시 값추가
```

### ArrayList 추가1 - Integer

```java
ArrayList<Integer> list = new ArrayList<Integer>();
list.add(3); //값 추가
list.add(null); //null값도 add가능
list.add(1,10); //index 1위치에 10 삽입
```

- Integer  선언시 wrapper클래스인 Integer 만 사용 가능

### ArrayList 추가2 - Class

```java
ArrayList<Student> members = new ArrayList<Student>();
Student student = new Student(name,age);
members.add(student);
members.add(new Member("홍길동",15));
```

- add(value) : index를 생략시 ArrayList 맨 뒤에 데이터가 추가
- add(index, value) : ArrayList에 값을 추가하려 를 사용(=push_back메서드와 유사 )

    : **index중간에 값을 추가하면 해당 인덱스부터 마지막 인덱스까지 모두 1씩 뒤로 밀려납니다.**

### ArrayList 삭제

```java
ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(1,2,3));
list.remove(1);  //index 1 제거
list.clear();  //모든 값 제거
```

- remove(index) : ArrayList에 원소를 제거하
- **remove()함수를 사용하여 특정 인덱스의 객체를 제거하면 바로 뒤 인덱스부터 마지막 인덱스까지 모두 앞으로 1씩 당겨집니다.**
- clear() : 모든 원소를 제거

### ArrayList 크기 구하기/ 검색하기

```java
ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(1,2,3));
System.out.println(list.size()); //list 크기 : 3

System.out.println(list.contains(1)); //list에 1이 있는지 검색 : true
System.out.println(list.indexOf(1)); //1이 있는 index반환 없으면 -1
```

- size() 메소드 : 원소의 갯수(크기)를 구할 수 있습니다.
- contains(value) 메소드 : 찾고자 하는 value을 검색
    - 존재시 true가 리턴, 없으면 false가 리턴됩니다.
- indexOf(value) 메소드 :  value가 위치한 index가 리턴됨, 없다면 -1을 리턴합니다.

### ArrayList 값 출력

```java
ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(1,2,3));

System.out.println(list.get(0));//0번째 index 출력
		
for(Integer i : list) { //for문을 통한 전체출력
    System.out.println(i);
}

Iterator iter = list.iterator(); //Iterator 선언 
while(iter.hasNext()){//다음값이 있는지 체크
    System.out.println(iter.next()); //값 출력
}
```

- get(index) : ArrayList의 원하는 index의 값이 리턴

    : 전체출력은 대부분 for문을 통해서 출력을하고 Iterator를 사용해서 출력을 할수도 있습니다.

### ArrayList

```java

```

- ㅇㄹ

# Java Vector

- 참조: [https://coding-factory.tistory.com/553](https://coding-factory.tistory.com/553)
- Vector와 Arraylist는 동일한 내부구조
- 동작또한 비슷한데

    값이 추가되면 자동으로 크기가 확장(조절)됨

- 다른점이 있다면 동기화
    1. Vector는 동기화된 메소드로 구성되어 있음
    2. 멀티 스레드가 동시에 이 메소드들을 실행할 수 없음
    3. 하나의 스레드가 실행을 완료해야만 다른 스레드들이 실행가능
    4. 그래서 Vector는멀티 스레드 환경에서 안전하게 객체를 추가하고 삭제할 수 있습니다.
    5. 동기화는 Ttread-safe라는 장점이자 동시에 단점인데, 스레드가 1개일때도 동기화를 하기 때문에 ArrayList보다 성능이 떨어짐

- 반면에 Arraylist는 Vector와  거의 비슷하지만, 자동으로 동기화되지 않음
    1. 그럼에도! 동기화를 할지 안할지 사용자가 결정할수 있는 옵션이 존재함
    2. 벡터에 비해 속도가 조금이나마 더 좋은 성능(=빠른속도)이 있어서
    3. 자바에서는 Arraylist를 상대적으로 더 많이 많이 사용한다
- Vector 선언

```java
Vector v = new Vector();
Vector<Integer> v1= new Vector<Integer>();         //Lval과 Rval 형식이 같아야 함 
Vector<Integer> v2 = new Vector<>();               //타입 파라미터 생략 가능
Vector<Integer> v3 = new Vector<Integer>(10);      //초기 용량(capacity)지정
Vector<Integer> v4 = new Vector<Integer>(Arrays.asList(1,2,3)); //초기값 지정
Vector<String> strvec = new Vector<String>(10);    //초기 용량(capacity)지정
Vector<Gogi> BBQ = new Vector<Gogi>();             //타입설정 Gogi객체만 사용가능
```

- Vector 의  elem 추가

```java
//사용
Vector v = new Vector();
v.add(3); //값 추가
v.add(null); //null값도 add가능
v.add(1,10); //index 1뒤에 10 삽입
```

- Vector 의  elem사용

```java
Vector v = new Vector();
Student student = new Student(name,age);
v.add(student);
v.add(new Member("홍길동",15));
```

- Vector 의  elem 삭제

```java
Vector<Integer> v = new Vector<Integer>(Arrays.asList(1,2,3));
v.remove(1);  //index 1 제거
v.removeAllElements(); //모든 값 제거
v.clear();  //모든 값 제거
```

- Vector선언시 타입을 지정하지 않고 임의의 타입의 값을 넣고 사용할 수도 있지만 이렇게 사용할 경우 벡터 내부의 값을 사용하려면 캐스팅(Casting) 연산이 필요
- 잘못된 타입으로 캐스팅을 한 경우에는 에러가 발생하기에 Vector를 사용할때에는 타입을 명시해주는 것이 좋습니다.
- 제너릭스(Generics) : 선언할 수 있는 타입이 객체 타입입니다. int는 기본자료형이기 때문에 들어갈 수 없으므로 int를 객체화시킨 wrapper클래스를 사용해야 합니다.
- Wrapper클래스 : 기본 자료타입(primitive type)을 객체로 다루기 위해서 사용하는 클래스들을 래퍼 클래스(wrapper class)라고 합니다.
    - 참조: [https://coding-factory.tistory.com/547](https://coding-factory.tistory.com/547)

# Java의 vector 그리고 c++ vector가 다른 이유는..

- 짧은 생각이지만, 개인적이고 편협한 나의 생각으로는
1. C/C++은 만들때 멀티쓰레드에 관해서 별로 생각하지 않았고 심각한 문제도 없었음.
2. 단순히 파워풀한 메인쓰레드+시간이 오래걸리는 몇몇 서브스레드 정도 구조로 대부분의 문제들이 해결 가능했음. 
3. 더해서  C/C++ 개발자라면 멀티쓰레드가 돌아야 하는 상황에서 >> 리눅스 같은 OS를 올려서 멀티 프로세스로 문제를 해결하거나, MMU가 없는 MCU급 컴퓨터에서는 RTOS를 돌려서 Task단위로 관리를 했음.
4. 그래서 단순히 멀테쓰레드에 대한 지원을 과장을 조금 더 보태서 : API 몇개 만들어주고 쓸수 있도록 뚫어준 정도
5. 그러다 보니 C/C++ 의 대부분 함수들 STL 포함, 시스템콜 등은 대부분 Thread-unsafe함. 리눅서라면 man 페이지에서 thread safe/unsafe 정보를 확인가능.
6. 자바가 본격적으로 이름을 날리던 20세기 말부터 본격적으로 멀티쓰레드가 많이많이 필요해졌고, Java는 쓰레드에 대한 고민이 언어차원에서 잘 고려되어 설계가 되어있음.
7. **결론**:  멀티쓰레드를 오지게 돌려야한다면 그냥 Java를 쓰자. 

- 

# throws IOException

- throws IOException

# BufferedReader

- BufferedReader br=new BufferedReader(new InputStreamReader([System.in](http://system.in/)));

# StringTokenizer

- StringTokenizer st=new StringTokenizer(br.readLine());

# Integer.parseInt

- Integer.parseInt

## +5주차 피드백

- 콜바이벨류 VS 콜바이 레퍼런스
- this() 가 뭐하는거니?
- 생성자에 super가 숨어있는데 어디있게?
- 더블 디스패치
- final 키워드를

    매서드에 :

    클래스에 : 상속을 막아줌 ,근데 어떤경우에 왜 상속을 막니?

- 다형성이란? 다형성이랑 오버로딩이랑 다른거니?
- 캡슐화 :
- 롬복이 뭐임?
- 네스티드 클래스 : 클래스 안에 클래스
    - 클래스의 모든 종류 : (정리하신분 링크)