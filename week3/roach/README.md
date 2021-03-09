#[Week3] 연산자

# 목표

- 자바가 제공하는 다양한 연산자를 학습하세요.

# 학습할 것

- 산술 연산자
- 비트 연산자
- 관계 연산자
- 논리 연산자
- instanceof
- assignment(=) operator
- 화살표(->) 연산자
- 3항 연산자
- 연산자 우선 순위
- (optional) Java 13. switch 연산자

## 산술 연산자

- 두개 이상의 피 연산자의 연산을 사용할때 보통 사용한다. (단항 연산자 같은 예외도 있으므로 보통이란 단어로 표현함)

- 연산자(operator) ? : 연산자란 프로그래밍 언어에서 지원하는 수학 연산과 유사한 연산자를 뜻합니다.

  - EX ) '+' , '%' , '/' ...

- 때로는 연산자의 Overloading 도 지원하는데 이는 우리가 Java 에서 String `+` 연산을 실행하면 더하기 연산을 하는것 처럼 오버로딩을 지원합니다.
  - `단항 - 연산` 도 마찬가지 `-1` 일시 음수 처리 해줌.

## 비트 연산자

- 비트 연산자와 시프트 연산자는 비트 연산을 가능하게 한다.

### AND(&)

- 두 비트가 모두 같을 때만 리턴한다.

```java
        System.out.println(11 & 11); // 1011 & 1011 => 1011
        System.out.println(10 & 20); // 1010 & 10100 => 0000
```

### OR(|)

- 비트 합 연산을 수행한다.

```java
        bitSum(3, 4); => 7
```

### XOR(^)

- 해당 비트가 같으면 0, 다르면 1을 리턴합니다.

```java
exclusive(11, 11); => 0
```

- 그냥 든 생각인데 같은 Byte 비교할때 참 편할것 같다.

### Left-Shift Opreator(<<)

- 이건 알고리즘 풀때 가끔 사용한적이 있었던것 같은데 기본적으로 제곱과 같은 역할을 한다.

number << n 은 number = number _ (2 _ n)

```
2 << 2 // 8
2 << 3 // 16
```

### Light-Shift Operator

- 오른쪽 연산자는 반대로 나눗셈 역할을 한다고 보면 된다.

number >> n 은 number = number / (2 \* n)

```java
 System.out.println(4 >> 1);
```

## 관계 연산자

- 이 부분은 다른 분들이 정리를 잘하셔서.. 나는 간단하게 하자면 자바에서는 == 연산의 경우 HashCode() 에 정의된 내용을 비교하는것으로 알고있다.

## 논리 연산자

- 관계 연산자 처럼 true / false 를 통해 해당 연산의 결과를 리턴하는 연산입니다.

```
true && false => false
true && true => true

true || true => true
true || false => true

```

## InstanceOf

- 저번 주차에 Dong 이 `instanceOf` 를 통해서 묵시적 형변환이 가능한 것을 알 수 있다. 라고 해서 기억에 남는다.

- `obj instancof class` 식으로 많이 이용되나, 자신이 설계한 로직에 해당 로직이 있다면 좋지 않은 방법으로 설계가 되고 있는건 아닌지 생각해 볼 필요가 있다.

[instanceOf 속도에관한 흥미로운 글](https://stackoverflow.com/questions/103564/the-performance-impact-of-using-instanceof-in-java)

## assignment(=) operator

해당 변수의 주소값에 value 를 할당하는 연산자이다.

자바는 **call by value**

## -> 연산자 (Lamda Expression)

- 람다식이 도입되면서 새롭게 나타난 연산인데, 기존 다른 언어에서 익숙하게 사용되던 화살표이다 `->` 를 통해서 해당 @FunctionalInterface 를 구현한 구문내용을 적는것? 뭐 구현하는것이란 표현이 더 옳은가? (`{}` **안의 구문일 것이다.**)

## 3항 연산자

```java
condition ? true : false
```

위의 condition 이 true 일 경우 `true` 에 해당 되어있는 값을 배출 , false 일 경우 `false` 에 해당되어있는 값을 배출
