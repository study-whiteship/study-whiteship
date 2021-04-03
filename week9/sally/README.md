# **학습할 것 (필수)**

- 자바에서 예외 처리 방법 (try, catch, throw, throws, finally)
- 자바가 제공하는 예외 계층 구조
- Exception과 Error의 차이는?
- RuntimeException과 RE가 아닌 것의 차이는?
- 커스텀한 예외 만드는 방법

---

## **예외란?**

- 예외란? Abbreviation for Exceptional Event(which occurs during the execution of a program, that disrupts the normal flow of the program)

    메서드에서 에러가 발생할 경우 어떤 객체를 만들어 런타임 시스템에 전달하는데 여기서 '어떤 객체'가 바로 예외. It carries info about the error, such as its type and the state of the program when the error occured. 이렇게 예외를 만들어 전달하는 과정을 예외를 `throw` 한다고 말한다.

- 예외가 발생한 상황에서 런타임 시스템은 예외를 받아 처리해줄 것을 찾는다. 에러가 발생한 메서드를 사용하기위해 호출된 메서드의 집합을 call stack이라고 하고 런타임 시스템은 exception을 처리해주는 코드블럭 (aka exception handler)를 찾을때까지 콜스택을 거슬러 올라간다. 핸들러를 찾으면 예외를 전달해주고 핸들러는 예외를 받아(catch) 처리한다.

## **자바에서 예외 처리 방법 (try, catch, throw, throws, finally)**

### **Try-catch-finally**

예외가 발생할 수 있는 부분을 try로 감싸고, try에서 예외가 발생하면 해당하는 예외를 잡아주는 catch부분이 실행된다.

```
try {
  // 예외가 발생할 가능성이 있는 코드
} catch (PossibleException1 e) {
  // try에서 발생한 예외가 PossibleException1에 해당한다면 여기서 처리
} catch (PossibleException2 e) {
  // try에서 발생한 예외가 PossibleException2에 해당한다면 여기서 처리
}
```

`catch`는 여러 블럭이 붙을 수 있지만 `try`에서 발생한 예외 타입에 따라 `catch`는 한번만 실행된다.

`finally`는 `try`와 `catch`이후에 붙을 수 있는 코드블럭으로 예외발생 여부에 상관없이 무조건 실행된다. `try`에서 예외가 발생하지 않는 경우 `try`-`finally`, 예외가 발생해서 캐치부분이 실행될 경우 `try`-`catch`-`fianlly`순으로 실행된다. `finally`블럭은 필수가 아니라 얼마든지 생략해도 좋다.

### **throw, throws**

`throw`는 강제적으로 예외를 발생시키는 방법. 원하는 부분에 원하는 예외를 발생시켜주면 된다.

`throws` 키워드가 붙은 메서드는 예외를 던질 수 있다 라는 것을 명시해주고 예외가 발생할 시 해당 메서드를 호출한 메서드에게 예외를 전달해준다. 예외 처리와는 관련이 없고 전달만 해주기때문에 어디서든 반드시 예외처리부분이 필요하다.

```
public void iThrowException(String str) throws Exception1 {
  if (str.length() > 5) {
    throw new Exception1("example exception");
  }
}

public static void main(String[] args) {
  iThrowException("Hello World"); // 예외발생
}
```

위의 예시에는 예외를 발생시키고 던져주기만 하지 처리하는 부분이 없다. 컴파일 에러가 발생한다.

1. try-catch로 잡아주거나

```
public static void main(String[] args) {
  try {
    iThrowException("Hello World");
  } catch(Exception1 e) {
    // 처리할 코드
  }
}
```

1. `throws`를 이용해서 다시 던져버린다.

```
public static void main(String[] args) throws Exception1 {
  iThrowException("Hello World");
}
```

## **Exception과 Error의 차이는?**

에러는 컴퓨터 하드웨어의 오동작 또는 고장으로 인해 응용프로그램에 이상이 생겼거나 JVM 실행에 문제가 생겼을 때 발생하는 것으로 시스템 레벨에서 발생한다.

예외는 사용자의 잘못된 조작 또는 개발자의 잘못된 코딩으로 인해 발생하는 프로그램의 오류이다. 프로그램에서 발생한 예외는 핸들러를 통해 프로그램의 종료를 예방하고 예외처리를 할 수 있다.

## **RuntimeException과 RE가 아닌 것의 차이는?**

Runtime Exception을 Unchecked Exception, 아닌 것을 Checked Exception이라고 한다.  둘의 구분은 ""꼭 처리를 해야하느냐"에 있다.

- Checked Exception은 컴파일 단계에서 확인되고 반드시 처리를 해야하는 예외이다.
- Runtime Exception(Unchecked Exception)은 꼭 처리를 하지 않아도 된다. 그 말인 즉슨 컴파일러가 예외처리를 강제하지 않는다(컴파일 단계에서 확인되지 않는다).

## **커스텀한 예외 만드는 방법**

Exception 클래스를 상속받아 필요한 예외를 정의한다.

```
public class ExampleException extends Exception {
  public ExampleException(String errorMessage) {
    super(errorMessage);
  }
}
```
