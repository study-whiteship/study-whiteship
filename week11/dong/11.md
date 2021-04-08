# 자바 11주차 - 열거형(스터디 할래)

# **목표**
: 자바의 열거형에 대해 학습하세요.

# **학습할 것 (필수)**
- enum 정의하는 방법
- java.lang.Enum
- EnumSet
---

# 목차
열거형(enum)개요, 필요성
열거형의 prototype, declaration, usage
열거형에 멤버 추가하기
열거형의 이해
java.lang.Enum
EnumSet

---

### Jumper

- [목차로]()
- [열거형(enum)개요, 필요성]()
- [열거형의 prototype, declaration, usage]()
- [열거형에 멤버 추가하기]()
- [열거형의 이해]()
- [java.lang.Enum]()
- [EnumSet]()

---

# 열거형(enum)개요, 필요성

[목차로]()

- 열거형이란 : 상수 여러개를 묶어서 정의할 수 있는 문법요소
  - 서로 관련된 상수를 편리하게 선언하는데 사용됨
  - 데이터와 타입을 일치시킬수 있다!
  - enum은 정의된 상수 이외의 값은 허용하지 않음 (자바 Enum의 특징)
- 사용법
```java
enum 열거형이름 {상수명1 , 상수명2, 상수명3, ...}
enum 커피 {아메리카노 , 라떼, 모카, 카푸치노...}
enum Direction {EAST,WEST,NORTH,SOUTH}
```
```java
public static void printDirection(Direction direction ) {
    switch (direction) {
        case EAST -> System.out.println("동");
        case WEST -> System.out.println("서");
        case NORTH -> System.out.println("북");
        case SOUTH -> System.out.println("남");
        default -> throw new IllegalArgumentException("잘못된 방향");
    }
}
```

- 예제코드()

```java
enum Gogi { Pork, Beef, Chicken, Turkey }

public class ExEnum {
    public static void main(String[] args) {
        
        Gogi pok = Gogi.Pork;
        //이렇게 점으로 에넘상수 쓸 수 있고

        Gogi kfc = Gogi.valueOf("Chicken");
        //문자열을 에넘상수로 변경할 수도 있고

        Gogi Sirloin = Enum.valueOf(Gogi.class, "Beef");
        //Sirloin 이라는 에넘변수에 담아둘수 있고

        Gogi samgyeobsal = Gogi.Pork;
        //상하관계 수평관계를 고려한 예제변수명 고치기 필요함
        //@Todo

        System.out.println("pok = " + pok);
        System.out.println("kfc = " + kfc);
        System.out.println("Sirloin = " + Sirloin);
        

        //비교연산도 가능하다
        System.out.println("compare : pok == kfc? : " + (pok == kfc));
        System.out.println("compare : pok == Sirloin? : " + (pok == Sirloin));
        System.out.println("compare : pok == samgyeobsal? : " + (pok == samgyeobsal));
        System.out.println("compare : Sirloin == beef? : " + (Sirloin == Gogi.Beef));
        System.out.println("");

        System.out.println("pok equals Sirloin : " + pok.equals(Sirloin));
        System.out.println("pok equals samgyeobsal : " + pok.equals(samgyeobsal));
        System.out.println("pok comp kfc : " + (pok.compareTo(kfc)));
        System.out.println("pok.comp Gogi.Beef : " + (pok.compareTo(Gogi.Beef)));

        System.out.println("\nfoorloop Enum.values() method");
        Gogi[] darr = Gogi.values();

        for (Gogi d : darr) {
            System.out.printf("%s = %d%n", d.name(), d.ordinal());
        }
    }
}
```
# 코쿼 Enum 수업
- Enum 이란 : 불연속적이고, 갯수에 제한이 있는 값들을 열거한 type → 열거형
###  Type-Safe
  - C 언어의 enum 은 type-safe 하지 않다
  - Java 의 enum 은 type-safe 하다
  - [type-safe](https://en.wikipedia.org/wiki/Type_safety) 하다는 다는 말의 의미 다른 타입으로 연산을 하려고 하면 컴파일 에러가 난다.
    - java 의 enum 은 타입체크가 가능하고
    - c 언어의 enum 은 int 상수(정수형) 전혀 다른 타입끼리라도 상수값이 같으면 true 가 뜬다.
    ### C 언어 예제
    ```c
    #include <stdio.h>

    enum Animal {CAT, DOG, MONKEY};
    enum Fruit {APPLE, PEAR, BANANA};

    int main() {
        enum Animal animal;
        enum Fruit fruit;

        animal = MONKEY;
        fruit = BANANA;

        printf("%d\n", animal);
        printf("%d\n", fruit);

        if (animal == fruit) {
            printf("Monkey is Banana!");
        } else {
            printf("Monkey is not Banana!");
        }

        return 0;
    }
    ```
    - 실행결과
    ```
    2
    2
    Monkey is Banana!
    ```
    <br><br>

    ### 자바 예제
    ```java
    public class Main {
        private enum Animal {CAT, DOG, MONKEY}
        private enum Fruit {APPLE, PEAR, BANANA}

        public static void main(String[] args) {
            Animal animal = Animal.MONKEY;
            Fruit fruit = Fruit.BANANA;

            System.out.println(animal);
            System.out.println(fruit);

            if (animal.equals(fruit)) {
                System.out.println("Monkey is Banana!");
            } else {
                System.out.println("Monkey is not Banana!");
            }

        }
    }
    ```
    ```
    MONKEY
    BANANA
    Monkey is not Banana!
    ```
  - 추가적으로 
    - Enum 간에 대소비교가 가능하다
      - 사이즈 같은 것을 enum 으로 정의할 때, {SMALL, MEDIUM, BIG} 의 순서
    - Enum 을 내부에서만 쓰는 경우 클래스에 종속적인 경우에 내부에 선언하는게 좋다.

---
# 타입에 대해서
- TMI : 열거형과 타입정의 (다른언어들과의 타입 비교)
    - C/C++에서 제공하던 enum과 다른점이 있다
        : 자바의 enum에서는 열거형의 값 뿐만 아니라, 타입까지 관리한다. 이를 **엄격한 타입 정의(strogly typed / strictly)**라고 한다 자바는 언어 기본스펙인데 과거의 C/C++은 아니였다
        : C/C++은 enum이 컴파일타임에 정수값으로 변환된다
        ```c++
        enum Color { 
            COLOR_BLACK, // assigned 0 
            COLOR_RED, // assigned 1 
            COLOR_BLUE, // assigned 2 
            COLOR_GREEN, // assigned 3 
            COLOR_WHITE, // assigned 4 
            COLOR_CYAN, // assigned 5 
            COLOR_YELLOW,// assigned 6 
            COLOR_MAGENTA// assigned 7 
        }; 
        Color paint(COLOR_WHITE); std::cout << paint; // 4
        ```
    - Type에 대해서 얼마나 엄격하냐에 따라 두가지로 나뉘는데
      - strongly typed 
      - weakly typed (loosely typed)
    - 또 Type결정 시기에 따라 타입은 두가지로 나뉘는데
      - 컴파일타임에 변수의 타입이 결정되면 : Statically typed, 정적타입
          - C/C++ , Java, TS(살짝 다르지만..)
      - 런타임(코드 실행중)에 타입이 결정되면 : Dayamically typed, 동적타입
          - Python, JS
    - 일반적으로 strongly typed 컴파일타임에 변수의 타입이 결정되는 Statically typed 이며, 컴파일타임중에 오류/예외사항을 모조리 검사한다
      - 이쯤하면 또 돌고돌아 Pointer 이야기를 하고싶은데 나중에.. ㅠㅠ 나도잘 몰라 무서워
      - 또 c언어에 가면 union(공용체) 같은것도 있는데 단순하게 "C언어로 파이썬같은거 만들때 쓰는 문법" 이고, 깊게 들어가면 맥락이랑 안맞으므로 생략한다.
    - 그리고 아무리 Statically typed, 정적타입이라도, 컴파일타임에 결정할 수 없는 경우가 종종 있는데, 대표적으로 "타입 변환" 동작코드에서는 런타임에 변수의 타입이 결정된다.
        - 그래서 결국 Statically typed 언어를 사용할지라도 일정부분은 불가피하게 Dayamically typed하는 부분이 발생할수 있고, **이 부분에서 논리적 에러가 발생할 가능성이 존재한다!**
        - 프로그래밍 언어차원에서 타입 언제 어느시점에서나 모든 타입오류를 발견해 낼 수 있는 경우를, "엄격한 타입 정의 언어(strogly typed Lang)" 이라고 부르며 Java가 대표적이다.
    - 반대의 경우 
        - cpp에서는 enum type과 실제 정수값을 동일시하고, 공용체(union)등의 요소는 type과 bit단위의 해석을 오로지 프로그래머에게 전적으로 위임하는 방식이기에 에러의 발생 가능성이 높다.
        - 심지어 동적할당을 위해 쓰이는 malloc함수는 (void *) 같은걸 리턴해준다.....

---
# Java Enum의 특징
- enum에 정의된 상수들은 해당 enum type의 객체
- C 등의 다른 언어에도 열거형이 존재한다. 하지만 다른 언어들과 달리 Java의 enum은 단순한 정수 값이 아닌 해당 enum type의 객체


---

# 열거형의 prototype, declaration, usage

[목차로]()

- 위 예제코드에서 열거형을 하나씩 분리해서 살펴보면 총 3단계가 있는데,

```java
package enum.study;

enum Gogi { Pork, Beef, Chicken, Turkey }

public class ExEnum {
    public static void main(String[] args) {
        Gogi pok = Gogi.Pork;
        Gogi kfc = Gogi.valueOf("Chicken");
        Gogi Sirloin = Enum.valueOf(Gogi.class, "Beef");
        Gogi samgyeobsal = Gogi.Pork;

        System.out.println("insert  : pok = " + pok);
        System.out.println("insert  : kfc = " + kfc);
        System.out.println("insert  : Sirloin = " + Sirloin);
        System.out.println("");

        System.out.println("compare : pok == kfc? : " + (pok == kfc));
        System.out.println("compare : pok == Sirloin? : " + (pok == Sirloin));
        System.out.println("compare : pok == samgyeobsal? : " + (pok == samgyeobsal));
        System.out.println("compare : Sirloin == beef? : " + (Sirloin == Gogi.Beef));
        System.out.println("");

        System.out.println("pok equals Sirloin : " + pok.equals(Sirloin));
        System.out.println("pok equals samgyeobsal : " + pok.equals(samgyeobsal));
        System.out.println("pok comp kfc : " + (pok.compareTo(kfc)));
        System.out.println("pok.comp Gogi.Beef : " + (pok.compareTo(Gogi.Beef)));

        System.out.println("\nfoorloop Enum.values() method");
        Gogi[] darr = Gogi.values();

        for (Gogi d : darr) {
            System.out.printf("%s = %d%n", d.name(), d.ordinal());
        }
    }
}
```

### prototype

```java
enum Gogi { Pork, Beef, Chicken, Turkey }
```

### declaration

```java
Gogi pok = Gogi.Pork;
Gogi kfc = Gogi.valueOf("Chicken");
Gogi Sirloin = Enum.valueOf(Gogi.class, "Beef");
Gogi samgyeobsal = Gogi.Pork;
```

### usage

```java
System.out.println("compare : pok == kfc? : " + (pok == kfc));
System.out.println("compare : pok == Sirloin? : " + (pok == Sirloin));
System.out.println("compare : pok == samgyeobsal? : " + (pok == samgyeobsal));
System.out.println("compare : Sirloin == beef? : " + (Sirloin == Gogi.Beef));
System.out.println("");

System.out.println("pok equals Sirloin : " + pok.equals(Sirloin));
System.out.println("pok equals samgyeobsal : " + pok.equals(samgyeobsal));
System.out.println("pok comp kfc : " + (pok.compareTo(kfc)));
System.out.println("pok.comp Gogi.Beef : " + (pok.compareTo(Gogi.Beef)));
```

- 추가로  for문도 돌릴수 있는데

```java
Gogi[] darr = Gogi.values();

for (Gogi d : darr) {
    System.out.printf("%s = %d%n", d.name(), d.ordinal());
}
```

---

---

# 열거형에 멤버 추가하기

[목차로]()

 

---

---

# 열거형의 이해

[목차로]()

---

---

# java.lang.Enum

[목차로]()

- 모든 열거형의 조상(최 상위 클래스)
- protected Enum(String name, int ordinal)

    : 유일한 생성자로 프로그래머는 이 생성자를 호출할 수 없고, 열거형 선언(enum 키워드 사용)에 대한 응답으로 컴파일러에서 내보낸 코드에 사용됨

- Enum 파일에서 확인할 수 있는 지원 메서드 리스트

```
compareTo(E o) : ordinal을 기준으로 지정된 객체와 비교합니다.크면 양수 작으면 음수 같으면 0을 반환
eqauls(Object other) : 지정된 객체(other)가 열거형 상수와 같으면 true를 반환합니다.
finalize() : 해당 Enum클래스가 final 메서드를 가질 수 없게 합니다.
getDeclaring() : 열거형 상수의 열거형 타입에 해당하는 Class 객체를 반환합니다.
hashCode() : 열거형 상수의 해시 코드를 반환합니다.
name() : 열거형 상수의 이름을 반환합니다.
ordinal() : 이 열거형 상수가 정의된 순서를 반환합니다.
toString() : 열거형 상수의 이름을 반환합니다. (재정의 해서 개발자에게 더욱 친근하게 사용할 수 있습니다.)
values() : 열거형의 모든 상수를 배열에 담아 반환합니다.
	- Direction[] arr = Direction.values();
valueOf(String name) : 열거형 상수의 이름으로 문자열 상수에 대한 참조를 얻을 수 있게 해줍니다.
	- Direction.WEST == Direction.valueOf("WEST"); // true 반환
```

---

---

# EnumSet

[목차로]()

: 열거형 타입과 함께 사용하기 위한 특별한 Set 구현체

- 특징
    - 동기화되지 않음 (멀티쓰레드에서 사용시 주의)
    - iterator를 활용한 순회 가능(Enum으로 for문을 돌릴수 있다)
    - 생성자가 존재하지 않음
    - 

- 예제코드

```java
import java.util.EnumSet;
import java.util.EnumMap;

public class Enset_EX {
    public static void main(String[] args) {
        EnumSet<Flower> es1 = EnumSet.allOf(Flower.class);
        EnumSet<Flower> es2 = EnumSet.range(Flower.marigold_3, Flower.willow_5);

        for (Flower flo : es1) {
            System.out.print(flo + "  , ");
        }

        System.out.println();

        for (Flower flo : es2) {
            System.out.print(flo + "  , ");
        }
    }
}

enum Flower {
    rose_1, iris_2, marigold_3, primrose_4, willow_5
}
```

- 실행결과

![%E1%84%8C%E1%85%A1%E1%84%87%E1%85%A1%2011%E1%84%8C%E1%85%AE%E1%84%8E%E1%85%A1%20-%20%E1%84%8B%E1%85%A7%E1%86%AF%E1%84%80%E1%85%A5%E1%84%92%E1%85%A7%E1%86%BC(%E1%84%89%E1%85%B3%E1%84%90%E1%85%A5%E1%84%83%E1%85%B5%20%E1%84%92%E1%85%A1%E1%86%AF%E1%84%85%E1%85%A2)%207c1ffa227e5448179a17a497a8329e4d/Untitled%201.png](%E1%84%8C%E1%85%A1%E1%84%87%E1%85%A1%2011%E1%84%8C%E1%85%AE%E1%84%8E%E1%85%A1%20-%20%E1%84%8B%E1%85%A7%E1%86%AF%E1%84%80%E1%85%A5%E1%84%92%E1%85%A7%E1%86%BC(%E1%84%89%E1%85%B3%E1%84%90%E1%85%A5%E1%84%83%E1%85%B5%20%E1%84%92%E1%85%A1%E1%86%AF%E1%84%85%E1%85%A2)%207c1ffa227e5448179a17a497a8329e4d/Untitled%201.png)

- 링크 : 더 알아보기 about Enumset

    : EnumSet에 new 연산자를 사용하지 않는 이유, EnumSet은 생성자를 사용자가 호출불가이유

    - [https://parkadd.tistory.com/50](https://parkadd.tistory.com/50)
- 자매품으로 EnumMap 도 있다

---

---

# 끝