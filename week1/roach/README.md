#Week1 : JVM 이란 무엇이며 자바코드는 어떻게 실행되는 것인가?

## 목표

자바 소스 파일을 JVM 으로 실행하는 과정 이해하기

## 학습할 것

- JVM 이란 무엇인가
- 컴파일 하는 방법
- 실행하는 방법
- 바이트코드란 무엇인가
- JIT 컴파일러란 무엇이며 어떻게 동작하는지
- JVM 구성요소
- JDK 와 JRE 의 차이

## JVM 이란 무엇인가?

- JVM 이란 Java Virtual Machine 의 줄임말으로 추상적인 컴퓨터 머신이라고 이해하는게 좋지 않을까 싶다.

여기서 추상적이라는 표현을 쓴 이유는 JVM 명세 ( 무엇무엇을 필수적으로 구현해야 한다) 이런것들이 적혀있고, 그걸 실제로 구현한것이 Oracle JVM , Amazone JVM 등등이 있는 것으로 알고 있다.

## 컴파일 하는 방법

- 컴파일은 JDK(java development kit) 에 있는 javac 를 이용해서 java byte code 파일로 컴파일 해주어야 한다. JVM 자체는 자바 파일을 실행시키는 것이 아니라 Java Byte Code 를 실행시켜주는 것이므로, 우리는 한 단계의 컴파일 과정을 일단 해주어야 한다.

```java
javac Hello.java
```

- 위와 같은 명령어를 입력하면 `Hello.class` 파일이 생기게 된다. JVM 은 해당 파일을 실행시키게 된다.
- 바이트 코드를 조금 더 이해하기 쉽게 보려면 아래와 같은 명령어를 이용하면 된다.

```java
javap -c Hello.class
```

- 이러면 조금 더 이해하기 쉬울 뿐더러 잘 이해는 가지 않지만 아래 링크를 보고 공부해볼 수 있다.

[Chapter 6. The Java Virtual Machine Instruction Set](https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html)

## 실행 하는 방법

- 아까도 말했듯이 실행은 class 파일이 존재해야만 실행이 가능하다.
- [Hello.java](http://hello.java) 라는 파일을 실행시킨다고 했을때 Step 은 아래와 같다.

```java
1. javac Hello.java
2. java Hello
```

- 뭐 만약에 외부 라이브러리의 경로를 지정해줘야한다면 `classpath` 를 이용해서 지정해줄 수도 있을 것이다.
- 뭐 만약 Package 가 있다면 실행할때 package 명을 입력해줘야 할것이다

```java
java test.roach.Hello
```

## 바이트 코드란 무엇인가

- 자바 바이트 코드란, 우리가 작성한 자바 언어를 JVM 이 해석할 수 있는 언어로 번역한 것을 말하는 것 같다.

기계어는 아니지만, 우리가 조금은 읽기 힘들게 되어 있는 것 같다. 자바 코드 → 바이트코드로 컴파일 되지만, 컴파일 코드 → 자바 코드로 변환도 가능하다. 이 부분은 조금 신기한 부분인데, 보통 `LOMBOK` 이나 `Annotation` 등을 통해서 바이트 코드를 조작할때가 있는데, 이때 class 된 파일을 역 으로 코드로 돌리면 조작된 자바코드가 나온다. 이건 직접눈으로 보면 신기하니 한번 해보는것도 좋다.

## JIT 컴파일러란 무엇이며 어떻게 동작하는가

- 기존의 컴파일러는 컴파일 하는 과정에서 한줄 한줄씩 읽는다. 뭐 파이썬이나 보통의 스크립트 언어들이 이런 방식이 많다고 생각된다. 해당 방식은 한줄 한줄 읽어야 하기에 느릴 수 밖에 없다. 그래서 Java 는 JIT 이란 개념을 도입했는데, Just In Time 이라는 말처럼, 적절 한 시점 에서 바이트 코드를 전체를 컴파일하여 네이티브 코드로 변경하고 이후에는 네이티브 코드로 직접 실행한다. 중요한 점은 캐싱을 한다는 점인것 같은데, 처음에는 인터프리팅을 하다가 같은 함수가 여러 번 불릴 때 같은 코드를 생성하는것을 금지한다.

## JVM 구성요소

### ClassLoader

- Loading : BootStrap, Extension, Application 컴포넌트에 의해 클래스들이 로더 됩니다.
    - BootStrap ClassLoader : jre 의 lib 폴더에 있는 rt.jar 파일을 탐색하여 기본 java API 라이브러리들을 로드합니다. 제일 최우선으로 로드됩니다.
    - Extension ClassLoader : jre 의 lib 폴더에 있는 ext 폴더에 있는 모든 확장 코어 클래스 파일들을 로드합니다. BootStrap ClassLoader 의 자식이며  jdk 확장 디렉토리(java.ext.dirs)에서 로드 됩니다.
    - Application ClassLoader : Extension ClassLoader 의 child 이며 어플리케이션 Level 의 클래스들을 로드합니다. 우리가 `-cp` , `-classpath` 등으로 입력한 파일들을 로드합니다.
- Linking
    - verify : 바이트 코드 검증기는 자바 바이트 코드가 적절한지 아닌지에 대해서 검증하며 검증이 실패할 경우 검증 오류를 내보냅니다.
    - prepare : 모든 정적변수의 메모리가 할당되며 기본 default 값으로 할당됩니다. (int 일 경우 0)
    - resolve : 모든 심볼릭한 메모리 참조를 메소드 영역에 있는 타입으로 직접 참조 합니다.
- Initialization : Class Loading 의 마지막 단계로써 모든 정적 변수가 자바 코드에 명시된 값으로 초기화 되며 정적 블록이 실행됩니다.
    - 이 부분에서 `static block` 에 관한 내용을 공부해도 좋다~!

### Runtime Data Area

- **Method Area**
    - 모든 클래스 수준의 데이터가 저장됩니다. 해당 영역의 데이터들을 공유될 수 있으며 JVM 당 하나의 영역밖에 존재하지 않습니다. 코드스쿼드 수업에서 들었을때는 짬통 영역같은 느낌이였는데, 이제 보통의 클래스 수준의 데이터들이 저장되서 그런 것 같다.
- **Heap Area**
    - 힙 영역은 동적 영역으로 모든 인스턴스들이 저장되는 공간이다. Heap 공간도 Method Area 와 같은 공유 영역입니다. 힙 영역은 Eden, Survivor, Tenured, Perm 영역으로 구성되어 있다.
        - Eden : Object 가 최초로 Heap 에 할당되는 장소입니다. 만일 Eden 영역이 가득 찰 경우, Object 의 참조를 확인하고 살아남은 Object 들을 Survivor 영역으로 옮긴다. 이름 한번 참 추상적으로 잘 지은것 같다. **(Minor GC)**
        - Tenured : 새로 Heap 에 할당된 Object 가 들어오는 것이 아닌, Survivor 영역에서 GC 에게 당하지 않고 오랫동안 살아남아 앞으로도 사용될 확률이 높은 Object 들을 저장하는 영역이다. 이런 과정에서도 만약 Old Generation 의 영역이 충분치 않으면 GC 가 발생하는데 이를 **Major GC** 라고 한다.
        - Perm : 보통 Class Mata 정보나 Method 의 Meta 정보, static 변수와 상수 정보들이 저장되는 공간으로 흔히 메타데이터 저장 영역으로 이동하였다. 뭐 자바 새로운 버전부터는 `MetaSpace` 라고 부르는 것 같다. 저번에 자바 바이트 코드를 공부하다가 constant pool 이라는 곳을 봤는데 그런것도 여기에 포함되어 있는 것 같다.
- **Stack Area**
    - 스택 영역은 프로세스와 스레드 부분을 공부하다 보면 더욱더 와닿는 곳이 아닐까 싶다.
    - 각각의 스레드 마다의 고유 영역을 가지는데 각 스레드의 고유 영역에는 Frame 이 생겨서 저장된다.
    - 그래서 Frame 이 stack 을 넘치도록 저장될 경우 StackOverFlowError 가 발생한다. ㅋㅋ 우리가 자주 사용하는 싸이트..
    - **Frame**
        - **Local Variables Array (Simply lva)**
            - 뭐 예를 들면 Test java 파일에 `int a` `int b` 두 변수가 있으면 해당 지역 변수들이 로컬변수 배열에 저장된다. 뭐 바이트 코드를 보다보면 `lva` 라는 것이 나오는데 local variable array 일것 같다. 뭐 여튼 그냥 primitive 파일들은 그냥 저장되지만, reference 들은 heap 영역을 reference 해야 하기에 다르게 저장되는 것 같다.
            - 이러한 지역성(폐쇄성?) 뭐 여튼 이런것들 때문에 스레드 간의 서로 값을 확인할 수 없다.
        - **Operand Stack (Simply Os)**
            - 해당 영역은 메소드 내 계산을 위한 작업 공간이다.
            - 뭐 이건 바이트 코드를 열어봤을때 `iload` 등등의 어질어질한 내용들이 있는데 뭐 간단히 얘기하면 해당 연산들의 작업 공간? 이라고 생각하면 된다.
        - **Frame Data (Simply FD)**
            - 뭐 여기는 Constant pool 과 같은 Symbolic Reference 나 normal method 들을 연관있거나 관련있는 메소드드로 리턴하여 저장하는 곳 같은데..? (이 내용은 틀릴 수도 있다.) 뭐 여하튼 아까 Linking 에서 resolve 된 애들을 저장한다는건가..? 좀 어렵다 나중에 더 깊게 공부해보자!
    - **PC Register**
        - JVM 은 CPU 에 직접 명령을 수행하지 않고, Stack 에서 Operand 를 뽑아내어 이를 별도의 메모리 공간에 저장하는 방식을 취하는데 이 저장 영역을 PC Register 라고 한다. PC Register 는 각 Thread 마다 하나씩 존재한다. Thread 가 Method 를 실행할때 현재 수행되고 있는 명령어의 주소를 포함하고 있다. 뭐 바이트 코드를 열어보면 `0: i load` 이런 게 있는데 아마 `0` 이런 것이 명령어의 위치를 나타내므로 비슷한 내용들을 저장하고 있지 않을 까 싶다.
    - **Native Method Stack**
        - 스레드에서 Native Method 가 실행되는 경우 해당 Stack 에 쌓인다고 한다.
        - 뭐 Thread 의 경우 `@native` 가 붙은 Method 들이 많던데, 이런 Method 들이 Native Method Stack 에 쌓인다는 것 같다.

    ### Excution Engine

    - 인터프리터 / JIT Compiler / GC 가 포함되어 있다.
        - 각 부분에 대한 내용은 위에 참조!

    ## JDK 와 JRE 의 차이

    ### JDK

    - JDK ( Java DevelopMent kit) 으로 자바 개발자 도구이다. 그니까 실행과는 별로 연관이 없다. 우리가 자바라는 언어를 개발하기 위해서 필요한 소프트 웨어들이 담겨 있는 하나의 패키지 이다.
    - 예를 들면 `javac` 이런 것들이 JDK 에 포함되어 있다.
    - 내가 자주 쓰는 Debuger 이런것들도 JDK 일것 같다.

    ### JRE

    - JRE ( Java Runtime Environment ) 는 자바 어플리케이션을 구동하는데 필요한 요소들이 담겨 있다. 실제 폴더를 한번 찾아보면  JDK 폴더아래에 나온다. 요즘 합쳐서 나온다는게 이소리인가 싶다.

    - 뭐 여튼 찾아봤으니 아까 위해서 언급한 BootStrap ClassLoader 가 최우선적으로 불러오는 라이브러리를 한번 찾아보자.  위의 사진을 보면 jre 에 위치해있다~!

