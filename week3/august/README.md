#  [Week3] 연산자

## 1. 산술 연산자

산술 연산자에는 사칙 연산자(`+,-,*,/`)와 나머지 연산자(`%`)가 있다.

### 사칙 연산자 `+ - * /`

사칙 연산자인 덧셈(`+`), 뺄셈(`-`), 곱셈(`*`), 나눗셈(`/`)은 가장 흔히 사용하는 연산자이다. 이 중에서 연산자 우선순위는 일반적인 수학에서의 우선순위와 동일하다. 즉, 곱셈, 나눗셈, 나머지 연산 > 덧셈, 뺄셈 이다. 나눗셈의 경우 피연산자가 0이먼 divide by zero 에러가 발생한다.

```java
public class Operator {
    public static void main(String[] args) {
        int operand1 = 10;
        int operand2 = 4;

        System.out.printf("%d + %d = %d\n", operand1, operand2, operand1 + operand2);
        System.out.printf("%d - %d = %d\n", operand1, operand2, operand1 - operand2);
        System.out.printf("%d * %d = %d\n", operand1, operand2, operand1 * operand2);
        System.out.printf("%d / %d = %d\n", operand1, operand2, operand1 / operand2);
        System.out.printf("%d / %f = %f\n", operand1, (float) operand2, operand1 / (float) operand2);
    }
}
```

특히 나눗셈은 기본적으로 int형 끼리 나눗셈을 하면 소수점 이하는 버려진다. 즉, 몫만 결과로 얻어진다. (단, 반올림은 발생하지 않는다.)

```java
public class Operator {
		public static void main(String[] args) {
        int start = 2_100_000_000;
        int end = 2_100_000_000;
        int mid = (start + end) / 2;        // => overflow can be occurred!!
        System.out.println(mid);
        mid = start + (end - start) / 2;    // => avoid overflow
        System.out.println(mid);
        mid = (start + end) >>> 1;          // => avoid overflow
        System.out.println(mid);
		}
}
```

```
-47483648
2100000000
2100000000
```





### 나머지 연산자 `%`

나머지 연산자는 왼쪽의 피연산자를 오른쪽 피연산자로 나눈 나머지 값을 결과로 반환한다. 예를 들면, `left % right`는 left를 right로 나눈 나머지를 반환한다. 나눗셈과 마찬가지로 나누는 수(right)는 0을 사용할 수 없다.

## 2. 비트 연산자

### `& | ^ ~`

> - `|` (OR) 피연산자 중 한 쪽의 값이 1이면 1. 그 외에는 0
> - `&` (AND) 피연산자 양 쪽이 모두 1이면 1. 그 외에는 0
> - `^` (XOR) 피연산자 값이 서로 다를 때만 1. 그 외에는 0
> - `~` (NOT) 피연산자를 2진수 상에서 0은 1로, 1은 0으로 바꾼다.

### `>> <<`

> - x << n 은 정수 x의 각 비트를 n만큼 왼쪽으로 이동시킨다. (빈자리는 0으로 채워진다.) => x * 2^n 결과와 같다.
> - x >> n 은 정수 x의 각 비트를 n만큼 오른쪽으로 이동시킨다. (빈자리는 정수 x의 최상위 부호비트와 같은 값으로 채워진다.) => x / 2^n 결과와 같다.

### `>>>`

> - x >>> y 는 정수 x의 각 비트를 y만큼 오른쪽으로 이동시킨다. (빈자리는 0으로 채워진다. => 부호비트 무효화)

## 3. 관계(비교) 연산자

### 대소비교 연산자 `< > <= >=`

두 피연산자의 값의 크기를 비교한다. 참이면 true, 거짓이면 false를 반환. 기본형 중에서는 boolean을 제외하고 모두 사용 가능하지만, 참조형에서는 사용할 수 없다. (무엇을 비교할지 알 수 없으므로)

>참조형의 비교를 위해서는 별도의 비교 기준(방법)을 정의하는 것이 필요하다. 
>
>- Comparable 인터페이스를 구현하여 `compareTo()`를 오버라이드
>- Comparator 인터페이스를 구현하는 클래스를 정의하여 `compare()`를 오버라이드
>- 람다식으로 비교구문에 바로 삽입

### 등가비교 연산자 `== !=`

두 피연산자의 값이 같은지 or 다른지를 비교하는 연산자. 대소비교 연산자와는 달리, 기본형은 물론 참조형에서도 사용할 수 있다. 단, 참조형의 등가비교는 두 피연산자가 가지는 주소값을 비교한다. 즉, `동일성(완전히 같은지)`을 비교한다. 하지만 논리적으로 동등한 경우 같은 객체로 보기위해서는 `==`연산자로 비교를 할 수는 없다. 따라서 Object에서 상속받은 `equals() and hashCode()` 메서드를 재정의하여 `동등성(논리적으로/의미상으로 같은지)`를 비교할 수 있도록 보장해주는 것이 바람직하다.

## 4. 논리 연산자

### `- && || !`

> - `||` (OR 결합) : 피연산자 중 어느 한 쪽만 true이면 true
> - `&&` (AND 결합) : 피연산자 양쪽 모두 true이어야만 true
> - `!` (논리 부정) : true와 false를 반대로 전환

## 5. instanceof

`instanceof`는 말 그대로 해당 인스턴스가 검증하고자 하는 타입의 인스턴스가 맞는지를 검증하는 연산자이다. 이는 참조변수가 참조하고 있는 인스턴스의 실제 타입을 알아보기 위해 사용한다. 주로 조건문에 사용되며, `참조변수 instanceof 타입(클래스명` 형태로 사용한다. 연산의 결과로 boolean값을 반환한다. 연산 결과로 true를 얻었다는 것은 참조변수가 검사한 타입과 같거나 형변환 가능한 인스턴으라는 것을 뜻한다.

```java
void method(List<T> list) {
	if (list instanceof ArrayList) {
		doSomething();
	}
}
```

## 6. assignment(=) operator

### 대입 연산자 `=`

오른쪽에서 왼쪽으로 값이 저장된다. 그러므로, **왼쪽은 값을 저장할 수 있는 것이어야 한다.**

### 복합 대입 연산자 `op=`

대입 연산자는 다른 연산자와 결합하여 `op=`와 같은 형태로 사용될 수 있다.

## 7. 화살표(->) 연산자

자바에서 `->` 연산자는 자바8에서 처음 소개된 람다식 표현에서 사용된다. 기본적으로, `->`는 왼쪽의 파라미터를 오른쪽의 구현으로부터 분리시켜준다. 일반적인 람다식 표기방식은 다음과 같다. `(Parameters) -> { Body }`

```java
Runnable r = new Runnable() {
        @Override
        public void run() {
            System.out.print("Run method");
        }
};
```

 위와 같이 함수적 인터페이스를 구현할 때 `->` 연산자를 유용하게 사용할 수 있다.

```java
Runnable r = ()-> System.out.print("Run method");
```

## 8. 3항 연산자

삼항 연산자는 많은 프로그래밍 언어에서 지원해주는 연산자이다. 이를 이용하면 조건문의 참/거짓에 따라 실행할 문장을 한줄로 정의할 수 있다.

```java
(조건문) ? 참 일때의 값 : 거짓일 때의 값
```

```java
if (max > temp) {
} else {
	max = temp;
}
```

이와 같은 조건문을 삼항 연산자로 변경한다면 다음과 같다.

```java
max = max > temp ? max : temp;
```

만약 max가 temp보다 크면 max를 그대로 대입하고, temp가 크거나 같다면 temp를 대입하게 된다.

## 9. 연산자 우선 순위

식에 사용된 연산자가 둘 이상인 경우, 연산자의 우선순위에 의해서 연산순서가 결정된다. 간혹 이 부분이 헷갈려서 예상치 못한 논리적인 오류가 발생하여 (디버깅 하기도 어려운) 매우 혼란에 빠질 수 있으므로 주의해야한다. 연산자 우선순위가 헷갈린다면 먼저 하고자하는 연산을 그냥 괄호`()`로 묶어주면 된다.

### 연산자 결합규칙

하나의 식에 같은 우선순위의 연산자들이 여러 개 있는 경우, 어떤 순서로 연산을 수행할 것인가? 이에 대해서는 각 언어별로 규칙을 미리 정해놓고 그 규칙을 따라 연산을 수행한다. 이러한 규칙을 **'연산자 결합규칙'**이라고 한다.

## 10. Java 13 - switch 연산자

```java
public class JavaSwitch {
    public static void main(String[] args) {
        System.out.println(getValueViaYield("a"));
        System.out.println(getValueViaYield("c"));
        System.out.println(getValueViaYield("e"));
        System.out.println(getValueViaYield("z"));
    }

    // Traditional switch
    private static int getValueBefore12(String mode) {
        int result;
        switch (mode) {
            case "a":
            case "b":
                result = 1;
                break;
            case "c":
                result = 2;
                break;
            case "d":
            case "e":
            case "f":
                result = 3;
                break;
            default:
                result = -1;
        }
        ;
        return result;
    }

    // Java 12, multiple comma-separated labels
    private static int getValueMultipleLabels(String mode) {
        int result;
        switch (mode) {
            case "a", "b":
                result = 1;
                break;
            case "c":
                result = 2;
                break;
            case "d", "e", "f":
                result = 3;
                break;
            default:
                result = -1;
        }
        ;
        return result;
    }

    // Java 13, value breaks are superseded by 'yield' statements
    // Java 12, switch expression returning value via break
    /*private static int getValueViaBreak(String mode) {
        int result = switch (mode) {
            case "a":
            case "b":
                break 1;
            case "c":
                break 2;
            case "d":
            case "e":
            case "f":
                break 3;
            default:
                break -1;
        };
        return result;
    }*/

    // Java 12, switch expression returns a value via label rules (arrow)
    private static int getValueViaArrow(String mode) {
        int result = switch (mode) {
            case "a", "b" -> 1;
            case "c" -> 2;
            case "d", "e", "f" -> {
                // do something here...
                System.out.println("Supports multi line block!");
                yield 3;
            }
            default -> -1;
        };
        return result;
    }

    // Java 13, switch expression returns a value via yield
    private static int getValueViaYield(String mode) {
        int result = switch (mode) {
            case "a", "b":
                yield 1;
            case "c":
                yield 2;
            case "d", "e", "f":
                // do something here...
                System.out.println("Supports multi line block!");
                yield 3;
            default:
                yield -1;
        };
        return result;
    }
}

```

```
1
2
Supports multi line block!
3
-1
```

기존의 switch문은 존재하지만 Java 13 이후에는 별도로 switch 연산자를 지원해준다. '연산자'라는 의미는 간단히 말하자면 switch에 해당하는 블록 전체가 변수에 담을 수 있는 값을 반환 한다는 것이다. (물론, 메서드도 같은 동작을 하지만, 연산자는 스택 프레임을 형성하지 않는다. => 바이트 코드 확인)

- https://mkyong.com/java/java-12-switch-expressions/
- https://mkyong.com/java/java-13-switch-expressions/
- https://docs.oracle.com/en/java/javase/13/language/switch-expressions.html

---

```java
public class Operator {
    // numbers라는 int형 배열이 있다.
    // 해당 해병에 들어있는 숫자들은 오직 한 숫자를 제외하고는 모두 두 번씩 들어있다.
    // 오직 한 번만 등장하는 숫자를 찾는 코드를 작성하라.

    public static void main(String[] args) {
        Operator operator = new Operator();
        int result = operator.solution(new int[]{5, 2, 4, 1, 2, 4, 5});
        System.out.println(result);
    }

    // XOR
    // 5 ^ 0 = 5
    // 5 ^ 5 = 0
    // 101
    // 101
    // ---
    // 000
    // 5 ^ 1 ^ 5 => (5 ^ 5) ^ 1 = 0 ^ 1 = 1
    private int solution(int[] numbers) {
        int result = 0;
        for (int number : numbers) {
            result ^= number;
        }
        return result;
    }
}
```

- TIP : `ita`, `iter` 로 바로 반복문 생성 가능