# [Week2] 자바 데이터 타입, 변수 그리고 배열

*변수는 단지 값을 저장하기 위해 예약된 메모리 위치이다.* 이는 변수를 만들 때 메모리에 약간의 공간을 예약(할당)한다는 것을 의미한다.

변수의 데이터 타입에 따라 운영체제는 메모리를 할당하고 예약된 메모리에 저장할 수 있는 항목을 결정한다. 따라서 변수에 서로 다른 데이터 유형을 할당하여 이러한 변수에 정수, 소수 또는 문자를 저장할 수 있다.

자바에는 두 가지 데이터 유형이 있다.

- 프리미티브 데이터 타입
- 참조 / 객체 데이터 타입

## 프리미티브 타입 종류와 값의 범위 그리고 기본 값

### 프리미티브 데이터 타입

자바에서 지원하는 8가지 기본(프리미티브) 데이터 타입이 있다. 이는 언어별로 사전에 정의되고 키워드로 이름이 지정되어있다.

**byte**

- 8비트 부호있는 2의 보수 정수
- 최소값은 -128 (-2 ^ 7) ~ 최대 값은 127 (포함) (2 ^ 7 -1)
- 기본값은 0
- byte가 int보다 4배 작기때문에 주로 정수 대신 큰 배열의 공간을 절약하는 데 사용
- 예) byte a = 100, byte b = -50

**short**

- 16 비트 부호있는 2의 보수 정수
- 최소값은 -32,768 (-2 ^ 15) ~ 최대 값은 32,767 (포함) (2 ^ 15 -1)
- 메모리를 바이트 데이터 유형으로 저장하는 데 사용할 수 있다. short는 int보다 2배 작다.
- 기본값은 0
- 예) short s = 10000, short r = -20000

**int**

- 32 비트 부호있는 2의 보수 정수
- 최소값은 -2,147,483,648 (-2 ^ 31) ~ 최대 값은 2,147,483,647 (포함) (2 ^ 31 -1)
- 메모리에 대한 우려가 없는 한 정수는 일반적으로 정수 값의 기본 데이터 타입으로 사용
- 기본값은 0
- 예) int a = 100000, int b = -200000

**long**

- 64 비트 부호있는 2의 보수 정수
- 최소값은 -9,223,372,036,854,775,808 (-2 ^ 63) ~ 최대 값은 9,223,372,036,854,775,807 (포함) (2 ^ 63 -1)
- int보다 더 넓은 범위가 필요할 때 사용
- 기본값은 0L
- 예) long a = 100000L, long b = -200000L

**float**

- 단 정밀도 32 비트 IEEE 754 부동 소수점
- 주로 부동 소수점 숫자의 큰 배열에서 메모리를 절약하는 데 사용
- 기본값은 0.0f
- 통화와 같은 정확한 값에는 부동 데이터 유형이 사용되지 않는다. -> 비교적 부정확
- 예) float f1 = 234.5f

**double**

- 배정 밀도 64 비트 IEEE 754 부동 소수점
- 일반적으로 10진수 값의 기본 데이터 유형으로 사용되며 일반적으로 기본 선택사항
- 통화와 같은 정확한 값에는 double을 사용해서는 안된다. -> 비교적 부정확
- 기본값은 0.0d
- 예) double d1 = 123.4

**boolean**

- 1비트 정보를 나타냄
- 가능한 값은 true와 false
- 주로 참/거짓 조건을 추적하는 간단한 플래그에 사용
- 기본값은 false입니다.
- 예) boolean one = true

**char**

- 단일 16 비트 유니 코드 문자
- 최소값은 '\ u0000'(또는 0) ~ 최대 값은 '\ uffff'(또는 65,535 포함)
- 모든 문자를 저장하는 데 사용
- 예) char letterA = 'A'

---

## 프리미티브 타입과 레퍼런스 타입

### 프리미티브 타입

- 프리미티브 타입은 언어별로 사전에 정의되고 키워드로 이름이 지정되어있다.
- 프리미티브 타입 변수는 선언된 타입의 값을 **한 번에 정확히 하나만 저장**할 수 있다.
- 프리미티브 타입 변수는 **기본적으로 초기화**된다. `byte`, `char`, `short`, `int`, `long`, `float`및 `double` 타입의 변수는 0으로 초기화 된다. `boolean` 타입의 변수는 `false`로 초기화 된다.

### 레퍼런스(참조) 타입

자바의 레퍼런스 데이터 타입은 동적으로 생성된 객체의 **레퍼런스/주소를 가지고 있다.** 이는 프리미티브 타입처럼 미리 선언되는 것이 아니다.

- 참조 변수는 클래스의 정의된 생성자를 사용하여 생성된다. 이는 객체에 액세스하는 데 사용된다. 이러한 변수는 변경할 수 없는 특정 유형으로 선언된다. 예) 직원, 강아지 등
- 클래스 객체와 다양한 타입의 배열 변수는 모두 참조 데이터 타입에 속한다.
- 예) `Animal animal = new Animal ( "giraffe");`

#### 레퍼런스 데이터 타입 종류

- **class types** : 클래스의 객체를 **가리킨다.**
- **array types**  : 배열을 **가리킨다.**
- **interface types** : 인터페이스를 구현하는 클래스의 객체를 **가리킨다.**

이러한 타입의 변수를 생성하면 (즉, 배열 또는 객체, 클래스 또는 인터페이스를 생성할 때)

- 이러한 변수는 이러한 값의 **주소만 저장**한다.
- 참조 변수의 기본값은 null이다.
- 참조 변수를 사용하여 선언된 유형 또는 호환 가능한 유형의 모든 개체를 참조할 수 있다. -> 다형성

---

## 리터럴

리터럴은 고정된 값을 소스 코드에서 표현하는 것이다. 이것은 별도의 연산이 없이 직접적으로 코드상에 나타나는 값이다. 리터럴은 다른 프리미티브 타입의 변수에 할당될 수 있다.

byte, int, long, 그리고 short는 10진수, 16진수, 8진수 체계로 나타낼 수 있다.

```java
int decimal = 100;
int octal = 0144;
int hexa = 0x64;
```

String 리터럴은 자바에서 다른 언어에서와 마찬가지로 지정되어있다. `"This is strings"`처럼 쌍 따옴표 사이에 일련의 문자를 묶어서 지정할 수 있다.

```java
"Hello World"
"two\nlines"
"\"This is in quotes\""
```

문자열 및 문자 유형의 리터럴은 모든 유니코드 문자를 포함할 수 있다.

```java
char a = '\u0001';
String a = "\u0001";
```

## 변수 선언 및 초기화하는 방법

```java
class ReferenceType {
    public int member;
}

class DataType {
    public static void main(String[] args) {
        // Primitive Type
        int value = 10;
        // Reference Type 
        ReferenceType referenceVariable = new ReferenceType();
    }
}
```

## 변수의 스코프와 라이프타임

먼저, 자바에서 변수의 스코프와 라이프타임을 비교하려면 변수의 종류를 우선 나누어야 한다.

### 인스턴스 변수

클래스 내부에 선언되면서 모든 메소드 및 블록 외부에 있는 변수는 인스턴스 변수이다. 인스턴스 변수의 일반적인 범위는 정적 메서드를 제외한 클래스 전체이다. 인스턴스 변수의 수명은 객체가 메모리에 남아있을 때 까지이다.

### 클래스 변수

클래스 내부에 선언되면서 모든 블록 외부에 있는데, `static`으로 표시된 변수를 클래스 변수라고 한다. 클래스 변수의 일반적인 범위는 클래스 전체이며 클래스 변수의 수명은 프로그램이 끝날 때까지, 또는 클래스가 메모리에 로드되어있는 동안이다.

### 지역 변수

인스턴스 및 클래스 변수가 아닌 다른 모든 변수는 메서드의 매개 변수를 포함하여 지역 변수로 처리됩니다. 지역 변수의 범위는 **선언된 블록 내**에 있으며, 지역 변수의 수명은 그것이 선언된 **블록에서 제어권이 떠날 때** 까지이다.

```
[Class Loader SubSyetem 완료] --> [클래스의 생성자가 호출될 때] --> [생성된 클래스 내부의 메서드 호출 시]
클래스(static)변수가 메서드 영역에 할당 --> 인스턴스 변수가 힙 영역에 할당 --> 지역 변수가 스택 영역에 할당
[메서드 호출 완료 및 리턴] --> [해당 객체가 GC될 때] --> [프로그램 종료 시]
지역 변수 할당 해제 --> 인스턴스 변수도 할당 해제 --> 클래스 변수 할당 해제
```

```java
class Variables {
    static int classVariable = 1;
    int instanceVariable = 1;

    void method() {
        int localVariable = 1;
        System.out.println(localVariable);
        localVariable++;
        System.out.println(localVariable);
    }
}

class VariableLifetime {
    public static void main(String[] args) {
        System.out.println("Class Variable Lifetime Test");
        for (int i = 0; i < 3; i++) {
            System.out.println(Variables.classVariable);
            Variables.classVariable++;
            System.out.println(Variables.classVariable);    
        }
       
        System.out.println("Instance Variable Lifetime Test");
        Variables variables = new Variables();
        for (int i = 0; i < 3; i++) {
            variables = new Variables();
            System.out.println(variables.instanceVariable);
            variables.instanceVariable++;
            System.out.println(variables.instanceVariable);
        }
        
        System.out.println("Local Variable Lifetime Test");
        variables = new Variables();
        for (int i = 0; i < 3; i++) {
            variables.method();
        }
    }
}
```

간단하게 인스턴스 변수, 클래스 변수, 그리고 지역 변수의 생명주기를 테스트 해보는 예제이다.

```
Class Variable Lifetime Test
1
2
2
3
3
4
Instance Variable Lifetime Test
1
2
1
2
1
2
Local Variable Lifetime Test
1
2
1
2
1
2
```

먼저, 클래스 변수의 값은 계속 증가한 것으로 보아, 해당 클래스의 생성과 상관없이 사용할 수 있고, 변수의 값이 유지되는 것을 확인할 수 있다. 인스턴스 변수는 새로 생성된 객체가 매번 할당되므로 매번 다른 객체의 변수값을 증가시키는 것이다. 따라서 계속 1 2를 반복한다. 마지막으로 지역 변수는 method 블록 내에서만 유지되므로 method를 호출할 때마다 초기화된다.

이 예제로는 지역변수의 생명주기는 추정할 수 있지만, 클래스 변수나 인스턴스 변수의 생명주기를 정확히 판단하기는 어렵다. 메모리 디버깅이라던지, 소멸자가 호출될 때(또는 가비지 컬렉션이 일어날 때) 출력문을 지정할 수 있는 메서드가 있다면 활용하여 좀 더 정확한 실험이 가능할 것이다.

## 타입 변환, 캐스팅 그리고 타입 프로모션

### Type Conversion

하나 개의 데이터 입력 (값 변환에있어서 `int`, `float`, `double`다른 데이터 유형 등) 타입 캐스팅으로서 알려져있다.

자바에는 13가지 Type Conversion이 있다. 

- Identity conversions
- Widening primitive conversions
- Narrowing primitive conversions
- Widening reference conversions
- Narrowing reference conversions
- Boxing conversions
- Unboxing conversions
- Unchecked conversions
- Capture conversions
- String conversions
- Value set conversions

### Type Casting

### Type Promotion

## 1차 및 2차 배열 선언하기

1차원 배열과 유사하게 **2 차원 배열** 은 데이터 셀의 모음이다. 2차원 배열은 대부분의 면에서 1차원 배열과 동일한 방식으로 작동한다. 그러나 1차원 배열과 달리 **열 인덱스** 와 **행 인덱스를** 모두 지정할 수 있다 .

2차원 배열의 모든 데이터는 동일한 데이터 타입이다.

```java
class ArrayTeset {  
    public static void main(String args[]) {
      int number_of_rows = 6;
      int number_of_columns = 5;
      int arr[][] = new int[number_of_rows][number_of_columns];

      for (int i = 0; i < number_of_rows; i++) {
          for (int j = 0; j < number_of_columns; j++) {
              System.out.print("[" + i + "," + j + "]:" + arr[i][j] + " ");
          }
          System.out.println();
      }
    }
  }  
```

위의 예에서는 `arr`배열의 이름이며, 이 2차원 배열의 총 요소 수(size)는 다음과 같다.

```java
number_of_rows * number_of_columns
```

따라서 arr의 총 요소의 수는 6 * 5 이므로 30이다.

```
[0,0]:0 [0,1]:0 [0,2]:0 [0,3]:0 [0,4]:0 
[1,0]:0 [1,1]:0 [1,2]:0 [1,3]:0 [1,4]:0 
[2,0]:0 [2,1]:0 [2,2]:0 [2,3]:0 [2,4]:0 
[3,0]:0 [3,1]:0 [3,2]:0 [3,3]:0 [3,4]:0 
[4,0]:0 [4,1]:0 [4,2]:0 [4,3]:0 [4,4]:0 
[5,0]:0 [5,1]:0 [5,2]:0 [5,3]:0 [5,4]:0 
```

실행해보면 위와 같은 출력을 확인할 수 있다. `[row, column] : 배열의 값` 형식을 갖는다.

## 타입 추론, var



---

## References

### 프리미티브 타입 종류와 값의 범위 그리고 기본 값

- https://www.tutorialspoint.com/Java-primitive-data-types

### 프리미티브 타입과 레퍼런스 타입

- https://www.oreilly.com/library/view/javatm-how-to/9780133813036/ch03lev2sec25.html#:~:text=Types%20in%20Java%20are%20divided,of%20objects%2C%20are%20reference%20types.
- https://www.tutorialspoint.com/What-are-reference-data-types-in-Java
- https://www.oreilly.com/library/view/javatm-how-to/9780133813036/ch03lev2sec25.html#:~:text=Types%20in%20Java%20are%20divided,of%20objects%2C%20are%20reference%20types.

### 리터럴

- https://www.tutorialspoint.com/java/java_basic_datatypes.htm

### 변수 선언 및 초기화하는 방법

- https://docs.oracle.com/javase/tutorial/java/javaOO/classvars.html
- https://docs.oracle.com/javase/tutorial/reflect/member/field.html

### 변수의 스코프와 라이프타임

- https://www.tutorialspoint.com/scope-and-lifetime-of-variables-in-java#:~:text=A%20variable%20which%20is%20declared,the%20object%20stays%20in%20memory.

### 타입 변환, 캐스팅 그리고 타입 프로모션

- https://www.geeksforgeeks.org/type-conversion-java-examples/
- [Java Type Conversion (공식 Java 문서)](https://docs.oracle.com/javase/specs/jls/se10/html/jls-5.html)
- https://www.programiz.com/java-programming/typecasting

### 1차 및 2차 배열 선언하기

- https://www.educative.io/edpresso/how-to-use-2-d-arrays-in-java

### 타입 추론, var

- http://openjdk.java.net/jeps/286