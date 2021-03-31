## 목표

- 자바의 예외 처리에 대해 학습하세요.

# 학습할 것 (필수)

- 자바에서 예외 처리 방법 (try, catch, throw, throws, finally)
- 자바가 제공하는 예외 계층 구조
- Exception과 Error의 차이는?
- RuntimeException과 RE가 아닌 것의 차이는?
- 커스텀한 예외 만드는 방법

## 자바에서의 예외 처리 방법

## throws

```java
public void add() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String ss = br.readLine();
}
```

위와 같이 메소드 자체에서 throws 를 사용하면 이 메소드를 사용하는 곳에서 Exception 을 catch 해줘야 한다.

예를 들면 위의 메소드를 쓰는 곳에서 try..catch 처리를 해줘야 한다.

```java
public static void main(String[] args) {
    try {

    } catch(IOException e) {
        e.printStackTra
    }
}
```

## throw

throw 로 던지는 방법은 많이 다들 해봤을 거라고 추측한다. 뭔가 특정 로직에서 의도하지 않은 방식으로 흘러가 예외처리를 해야 한다면
보통 throw new Exception 을 통해서 예외를 리턴하게 한다. 이 경우는 사용하는 부분에서 굳이 예외를 잡지 않아도 된다.
사실 위의 throws 랑 가장 큰 차이는 명시적으로 예외를 잡게하는 것? 이 클 수 있겠다.
Spring 은 ExceptionHandler 를 통해서 spring 이 running 되는 동안, 모든 예외를 catch 할 수 있어 throw new 를 통해서 예외를 발생시켜도 상관없다. (잡을 수 만 있다면..)
하지만 기존 자바 프로그래밍에서 throw 를 써서 예외를 리턴하는 라이브러리를 우리가 사용한다고 한다면, 그냥 종료될 수도 있다..

사실 라이브러리를 만든다면 사용자가 사용하는 함수들은 진짜 종료될 수준의 문제가 아닌이상 throw 를 안쓰고 throws 로 쓰는곳에서 잡도록 할 것 같다.

뭐 잘 생각하면 그런것만도 아닌게 생각해보니 Collection 에 list 들은 일정 바운더리를 넘어가면 IndexOutBoundException 인가를 발생시키는데,

이처럼 복구해서 될 문제가 아닌, 그냥 종료되어야 하는 문제들.. 그니깐 만약 IndexOutBoundException 이 날때 그걸 try..catch 로 잡아서 처리한다고 하면
마땅히 처리해야할 방도가 떠오르지 않는다. 왜냐.. 그냥 애초부터 틀렸으니깐, 그래서 프로그램을 종료시키는 게 맞다.

생각하다보니 정립됬는데 결국 프로그램을 종료 시켜야 하나, 아니면 예외가 나면 정상적인 방법으로 회복 시킬 수 있느냐에 차이로 저 두개를 쓰는 것 같다.

```java
public void get(int index) {
    if(index > this.size) {
        throw new IndexOutBoundeException();
    }
    return ele;
}
```

## finally

finally 는 아래 링크를 보면 알겠지만 저런 지식들이 있어야 좀 잘 사용할 수 있는 것 같다.
그리고 자원을 반환할때도 보통 finally 에서 close 하는 방식보다는 애초에 try-with-resources 를 쓰라고 한다.

- finally 관련 좋은 글 읽어 보는 걸 추천!

[finally 관련 좋은 글](https://tomining.tistory.com/154)

```java

public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println(test());
    }

    static int test() {
        try {
            System.out.println("Try!");
            return 10;
        } catch (Exception e) {
             System.out.println("Catch!");
             return 11;
        } finally {
            System.out.println("Finally!");
            return 12;
        }
    }

}

```

result

```
Try!
Finally!
12
```

뭐 이런식으로 될 수도 있으니, finally 가 먼져 실행된다는 사실도 알아둬야 한다~!

## 자바의 예외 계층 구조

- 다른 글을 보면 좋은 그림들이 많다. 사실 그림을 그릴정도로 성실한 사람은 아니라 그냥 패스하고 큰 틀만 설명하겠다.

크게 Error 와 Exception 으로 나누어져있는데 , 여기서 Error 와 Exception 에 관한 내 생각은, 이제 Error 는 약간 하드웨어 쪽의 문제에 가깝다.
하드웨어 라는 표현보다는 .. 우리가 건드릴 수 없는 부분들이라 해야하나, 여튼 그런쪽에 가깝다. 뭐 예를들면 VirtualMachineError 등등.. 우리가 JVM
에러를 어떻게 처리하겠는가 이런게 에러이고, Exception 은 보통 우리의 로직으로 인해 발생할 수 있는 Error 를 Exception 이라고 표현한다.

뭐 여기서도 Runtime Exception 등등 여러가지 Exception 으로 나뉘는데, RuntimeException 은 Unchecked Exception 은 런타임시점에 확인하게 되며,
따라서 명시적으로 예외처리를 해주지 않아도 된다. Unchecked Exception 은 반대로 컴파일 타입에 보통 확인하며, 예외처리가 반드시 필요하다.

## 커스텀한 예외 만드는 방법

- 기존의 예외를 상속해서 만들면 된다~!

[예시](https://github.com/tmdgusya/spring-boot-qna/blob/mission6/src/main/java/com/codessquad/qna/exception/NotExistLoggedUserInSessionException.java)
