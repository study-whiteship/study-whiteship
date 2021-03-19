## 목표

자바의 Class에 대해 학습하세요.

## 학습할 것 (필수)

- 클래스 정의하는 방법
- 객체 만드는 방법 (new 키워드 이해하기)
- 메소드 정의하는 방법
- 생성자 정의하는 방법
- this 키워드 이해하기

## 클래스 정의 하는 방법

- 아래와 같은 방식으로 정의가 가능하다.

```java
public class Main {

}
```

- public 은 class File 당 하나밖에 적지 못한다.

- 내부 클래스에 `static` 붙이는건 캡슐화를 하느냐 안하느냐에 차이도 있는 것 같다.

### static inner class

```java
public class Outer {

    class Inner {

    }
}

------------------------

public class Other {

    Outer.Inner inner = new Outer.Inner();
}
```

- **Debug 명령어**

```shell
javac -d ./ -classpath ./ Main.java
```

## 객체 만드는 방법

- 일반적으로 객체를 만들때에는 생성자를 이용해서 new 연산자를 이용해서 만든다.
- new A() 주소값을 a 변수에 할당해주는 형태로 진행된다.
- reflection 을 이용하면 newInstance() 메소드를 통해서도 만들 수 있다.

```
A a = new A();
```

## 메소드 정의하는 방법

- 클래스내에서 public / private 접근 제한자를 설정해준다.

- 메소드 리턴타입을 정한다.

```java
public int returnInteger(int a) {
    return a;
}
```

## 생성자 정의하는 방법

```
public ClassName(Arguments..) {
    Field initialize...
}
```

- Overloading 이 가능하다.

## this 키워드 이해하기

- this 는 간단하게 자기 자신을 참조하는것을 말한다.
