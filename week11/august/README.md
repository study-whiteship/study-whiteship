# [Week11] Enum

## Enum 사용의 장점

>The enums are **type-safe** means that an enum has its own namespace, we can’t assign any other value other than specified in enum constants. Typesafe enums are introduced in **Java 1.5 Version**. Additionally, an enum is a reference type, which means that it behaves more like a class or an interface. As a programmer, we can create methods and variables inside the enum declaration.

>**Advantages of enums**
>
>1. **Type safety:** Enums provide compile-time type safety and prevent from comparing constants in different enums.
>2. **Limits inputs:** The compiler won’t allow parsing a constant of a wrong enum to a method that expects an enum type. When using enum in switch statement, the enum constants are the only allowed labels for cases. IDEs like eclipse even provide autocomplete feature for filling them. When using enum in switch statement, the enum constants are the only allowed labels for cases. IDEs like eclipse even provide auto complete feature for filling them.
>3. Enums **groups things** in a set.
>4. Enums are **iterable**.

>**Use cases of enums**
>
>1. Enumeration can be used as a **singleton** in java. A single element enum type is considered as the best way to implement a singleton by many.
>2. Enumerations can also be used:
>   1. In place of String constants
>   2. In place of integers used as types

## Enum 정의하는 방법

열거 타입을 선언하기 위해서는 먼저 열거 타입의 이름을 정하고 열거 타입 이름으로 소스파일(.java)을 생성해야 한다. 열거 타입 이름은 관례적으로 첫 문자를 대문자로 하고 나머지는 소문자로 구성한다. 만약 여러 단어로 구성된 이름이라면 단어 첫 문자는 대문자로 하는 것이 관례이다. (Camel Case)

```java
Week.java
MemberGrade.java
ProductKind.java
```

소스 파일의 내용으로는 다음과 같이 열거 타입 선언이 온다. public enum 키워드는 열거 타입응ㄹ 선언하기 위한 키워드이다. 반드시 소문자로 작성해야 한다. 열거 타입 이름은 클래스와 마찬가지로 소스 파일명과 대소문자가 완전히 일치해야 한다.

```java
public enum Week {
  	MONDAY,
  	TUESDAY,
  	WEDNESDAY,
  	THURSDAY,
  	FRIDAY,
  	SATURDAY,
  	SUNDAY
}
```

---

## Enum이 제공하는 메소드 - values()와 valueOf()

### valueOf() 메소드

`valueOf()`메소드는 매개값으로 주어지는 문자열과 동일한 문자열을 가지는 열거 객체를 리턴한다. 이 메소드는 외부로부터 문자열을 입력받아 열거 객체로 변환할 때 유용하게 사용할 수 있다.

```java
Week weekDay = Week.valueOf("SATURDAY");
```

위 코드에서 weekDay 참조변수는 Week 열거형에서 SATURDAY에 해당하는 열거 객체를 참조하게 된다.

### value() 메소드

`values()`메소드는 열거 타입의 모든 열거 객체들을 배열로 만들어 리턴한다.

```java
Week[] days = Week.values(); // values를 통해 전체 배열을 얻어낸다
for (Week day : days) {
		System.out.println(day);
}
```

이것은 열거형의 장점 중에 하나인데, 배열처럼 사용할 수 있다는 것이다. Week 배열의 인덱스는 열거 객체의 순번과 같고, 각 인덱스 값은 해당 순번의 열거 객체 번지수를 가진다.

```java
package enumeration;

public class EnumTest {
    enum Week {
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY,
        SUNDAY
    }

    public static void main(String[] args) {
        Week today = Week.SUNDAY;
        String name = today.name();
        System.out.println(name);

        int ordinal = today.ordinal();
        System.out.println(ordinal);

        if (args.length == 1) {
            String day = args[0];
            Week weekDay = Week.valueOf(day);

            if (weekDay == Week.SATURDAY || weekDay == Week.SUNDAY) {
                System.out.println("It's Weekend!");
            } else {
                System.out.println("It's Weekdays!");
            }
        }

        Week[] days = Week.values();
        for (Week day : days) {
            System.out.println(day);
        }
    }
}
```

---

## java.lang.Enum

enum 클래스는 다음과 같은 상속 관계를 가진다. 따라서 Object가 가지고 있는 메소드에 대해서는 일반적인 클래스와 마찬가지로 다 가지고 있으며, enum의 고유한 메소드도 가지고 있다.

```
java.lang.Object
└── java.lang.Enum<E>
```

> Sole constructor. Programmers cannot invoke this constructor. It is for use by code emitted by the compiler in response to enum type declarations.

- https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/Enum.html

---

## EnumSet

```
java.lang.Object
└── java.util.AbstractCollection<E>
		└── java.util.AbstractSet<E>
				└── java.util.EnumSet<E>
```

enum 타입을 사용하도록 구현된 특수한 Set이다. 이것은 Set 인터페이스를 구현하면서 AbstractSet으로부터 상속을 받는다.

>When we plan to use an *EnumSet* we have to take into consideration some important points:
>
>- **It can contain only \*enum\* values** and all the values have to belong to the same *enum*
>- **It doesn't allow to add null values**, throwing a *NullPointerException* in an attempt to do so
>- **It's not thread-safe**, so we need to synchronize it externally if required
>- **The elements are stored following the order in which they are declared in the \*enum\***
>- **It uses a fail-safe iterator** that works on a copy, so it won't throw a *ConcurrentModificationException* if the collection is modified when iterating over it

```java
public enum Color {
    RED, YELLOW, GREEN, BLUE, BLACK, WHITE
}
```

```java
EnumSet.allOf(Color.class);
```

```java
EnumSet.noneOf(Color.class);
```

```java
EnumSet.range(Color.YELLOW, Color.BLUE);
```

```java
EnumSet.complementOf(EnumSet.of(Color.BLACK, Color.WHITE));
```

```java
EnumSet.copyOf(EnumSet.of(Color.BLACK, Color.WHITE));
```



## Referencces

### Enum Advantages

- https://woowabros.github.io/tools/2017/07/10/java-enum-uses.html
- https://www.geeksforgeeks.org/enum-in-java/#:~:text=Enums%20are%20used%20when%20we,represented%20using%20enum%20data%20type.
- https://www.tutorialspoint.com/what-is-a-type-safe-enum-in-java#:~:text=The%20enums%20are%20type%2Dsafe,a%20class%20or%20an%20interface.
- https://www.javajee.com/blog/enums-in-java-with-examples-advantages-use-cases

### EnumSet

- https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/EnumSet.html
- https://www.javatpoint.com/java-enumset
- https://www.geeksforgeeks.org/enumset-class-java/
- https://www.baeldung.com/java-enumset