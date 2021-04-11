**학습할 것 (필수)**

- enum 정의하는 방법
- enum이 제공하는 메소드 (values()와 valueOf())
- java.lang.Enum
- EnumSet

### Enum 정의하는 방법

- Enum이란?

열거형(=Enum)은 변수를 **미리 정의된 상수의 집합**으로 정의할 수 있는 데이터 타입이다. 변수는 정의된 상수중 하나와 같아야한다. 

- Enum의 장점
  - Enum을 통해 상수들의 타입을 정해줌으로써 이외의 타입을 가진 데이터값이 들어오면 컴파일러가 체크해준다.
  - 타입안정성이 보장된다.
  - 상수가 많으면 코드가 길어진다. 열거형은 이를 개선해줄 수 있다.

```java
class Card {
  static final int CLOVER = 0;
  static final int HEART = 1;
  static final int DIAMOND = 2;
  static final int SPADE = 3;
  
  static final int TWO = 0;
  static final int THREE = 1;
  static final int FOUR = 1;
  
  final int kind;
  final int number;
}
```

```java
class Card {
  enum Kind { CLOVER, HEART, DIAMOND, SPADE }
  enum Number { TWO, THREE, FOUR }
  
  final Kind kind;
  final Number number;
}
```

열거형은 별도의 .java 파일을 만들 수도, 클래스 내부, 또는 외부에서 선언할 수도 있다. 접근제어자, `enum`키워드 이후에 중괄호 안에 상수를 정의해주면 된다. 필드들은 상수이기때문에 변수명은 대문자로 정의한다.

열거형에 선언된 상수들은 순서대로 0부터 임의의 정수값(인덱스)를 가진다. 이 정수값의 타입이나 값을 바꿔주고싶다면 괄호열고 생성자를 호출하면 된다. 이렇게 정의된 생성자는 필드에서 선언해줘야한다. 열거형의 생성자는 묵시적으로 private이기때문에 밖에서 사용할 수 없다.

### Enum이 제공하는 메소드 (values()와 valueOf())

인텔리제이에서 열거형을 만들고 values()와 valueOf()를 까보고싶었으나 볼 수 없었다. 이유를 찾아보니 이 두 메서드는 컴파일러가 자동으로 추가해주는 메서드이기때문이라고 한다.

- `values()`
  - 선언된 순서대로 가지고있는 모든 상수를 배열로 반환시키는 메서드다.
  - foreach를 이용할 때 많이 쓰는 듯하다?
- `valueOf()`
  - 특정 문자열을 넣으면 그와 일치하는 상수를 반환해준다.

```java
enum Kind { CLOVER, HEART, DIAMOND, SPADE }

public class Sample() {
  public static void main(String[] args) {
    Kind kind = Kind.valueOf("CLOVER");
    System.out.println(kind);
    
    for (Kind kind : kind.values()) {
      System.out.print(kind + ", ");
    }
  }
}
```

```java
CLOVER
CLOVER, HEART, DIAMOND, SPADE
```

### java.lang.Enum

모든 열거형은 `java.lang.Enum`을 부모로 가지고 거기서 기본적으로 제공되는 메서드들이 있다.

| 메서드                                    | 설명                                                 |
| ----------------------------------------- | ---------------------------------------------------- |
| Class<E> getDeclaringClass()              | 열거형의 Class 객체를 반환                           |
| String name()                             | 열거형 상수의 이름을 문자열로 반환                   |
| int ordinal()                             | 열거형 상수가 정의된 순서를 반환                     |
| T valueOf(Class<T> enumType, String name) | 지정된 열거형에서 name과 일치하는 열거형 상수를 반환 |

### 


