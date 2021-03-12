# [Week1] JVM은 무엇이며 자바 코드는 어떻게 실행하는 것인가. 

## JVM이란 무엇인가

자바 프로그래밍 환경은 자바 언어 및 지원 런타임으로 구성된다. 이를 JVM(Java Virtual Machine)이라고도 한다. 즉, JVM은 _자바 프로그램을 실행시키기 위해서 필요한 런타임 환경을 제공해주는 프로그램_이다. 자바 프로그램은 하드웨어 및 OS 플랫폼에서 JVM을 지원해야만 실행 가능하다.

다행히도, JVM은 무수히 많은 환경에서 실행되기 위해서 이미 다 포팅되어 있다. 예를 들면, 셋톱박스, 블루레이 플레이어나 매우 거대한 메인 프레임 까지도 아마도 JVM이 사용가능할 것이다. 그러므로, 우리는 플랫폼은 신경쓰지 않고 **오직 자바 코드만 신경쓰면 되는 것**이다. 바로 JVM이 자바를 **플랫폼에 독립적인 언어**로 만들어 주는 것이다. JVM은 자바로 작성된 프로그램으로 하여금 _WORA(Write Once, Run Anywhere)_을 가능케 해주는 **구현체**이다.

> **왜 JVM을 알아야 하는가?**
>
> - 같은 기능을 하는 프로그램이더라도 **메모리** 관리에 따라 성능이 좌우될 수 있다.
> - **메모리**가 관리 되지 않은 경우 성능저하 혹은 프로그램이 뻗어버리는 현상 등이 일어날 수 있다.
> - 한정된 **메모리**를 효율적으로 사용하여 최고의 성능을 내기 위함이다.

- JDK 다운로드 링크 : https://www.oracle.com/java/technologies/javase-downloads.html 

## 컴파일 하는 방법

`javac <program name>` 명령으로 .java 확장자를 파일을 컴파일할 수가 있다. 이때 어떤 경로에서든 `javac` 명령을 실행하기 위해서는 각 운영체제에 맞게 환경변수에 추가해주어야 한다.

```java
public class Hello {
    public static void main(String[] args) {
        System.out.println("This is the Java program");
    }
}
```

위와 같이 간단한 코드를 작성하고 `Hello.java`라는 이름으로 파일을 저장한다. 모든 자바 애플리케이션은 적어도 하나의 클래스가 정의되어 있어야 한다. (`class` 키워드로 정의) 한 가지 중요한 사실은 하나의 자바 파일은 여러 개의 클래스를 가질 수 있지만, **단 하나의 public 클래스만을 가질 수 있으며, 그 클래스 이름과 파일명은 동일해야 한다.**

```sh
$ javac Hello.java
$ ll
-rw-r--r--  1 ham  staff   403B Mar  2 00:16 Hello.class
-rw-r--r--  1 ham  staff   116B Mar  2 00:16 Hello.java
```

자바 컴파일러를 통해 `Hello.java`파일을 컴파일하면 `Hello.class`라는 클래스파일이 만들어진다.

## 실행하는 방법

`java <arguments> <program name>` 로 자바로 작성된 프로그램을 실행할 수 있다. 이때 `java`명령어로 바로 실행할 수 있는 파일은 `.class`파일이다. 위에서 작성한 프로그램을 실행해보면, 정상적으로 프로그램이 실행되는 것을 확인할 수 있다.

```sh
$ java Hello
This is the Java program
```

JVM이 실행하는 파일은 바로  `.class` 파일이라는 점에 유의해야 한다. 그렇다면 하나의 `.java` 파일에 여러개의 클래스가 포함되어 있다면 어떻게 `.class`파일이 생성될까?

### 2개 이상의 클래스를 포함하는 경우

```java
class SampleOne {}

class SampleTwo {}

public class MultipleClasses {
    public static void main(String[] arge) {
        System.out.println("Multiple classes test.");
    }
}
```

```shell
-rw-r--r--  1 ham  staff   446B Mar  2 00:48 MultipleClasses.class
-rw-r--r--  1 ham  staff   180B Mar  2 00:48 MultipleClasses.java
-rw-r--r--  1 ham  staff   198B Mar  2 00:48 SampleOne.class
-rw-r--r--  1 ham  staff   198B Mar  2 00:48 SampleTwo.class
```

### 서브 클래스를 포함하는 경우

```java
public class SubClasses {
    class SubClassOne {}

    class SubClassTwo {}
    
    public static void main(String[] args) {
        System.out.println("Subclasses test.");
    }
}
```

```shell
-rw-r--r--  1 ham  staff   351B Mar  2 00:52 SubClasses$SubClassOne.class
-rw-r--r--  1 ham  staff   351B Mar  2 00:52 SubClasses$SubClassTwo.class
-rw-r--r--  1 ham  staff   579B Mar  2 00:52 SubClasses.class
-rw-r--r--  1 ham  staff   193B Mar  2 00:52 SubClasses.java
```

이외에도 여러가지 경우에 따라서 형태는 다르지만 클래스 키워드 하나 당 하나의 `.class`파일이 생성되는 것은 같다.

## 바이트코드란 무엇인가

자바 바이트 코드는 자바 가상 머신(JVM)이 이해할 수 있는 언어로 변형된 자바 소스코드를 의미한다. 즉, `javac`로  `.java` 파일을 컴파일하면 생성되는 .class파일의 내용이 바로 바이트 코드에 해당된다. 이 바이트 코드는 JVM이 설치되어 있으면, 어떤 운영체제나 시스템에서도 실행될 수 있다. 즉, WORA가 가능하도록 해주는 중간다리 역할을 한다. 

이러한 언어들을 통상적으로 중간 언어(Intermediate Language, IL)이라고 하는데, 대표적으로 C#에서는 CIL(Common Intermediate Language)라는 것이 있다.

**좀 더 일반적인 용어로는 컴파일러나 가상머신이 이해할 수 있는 언어인 *[IR(Intermediate Representation)](https://en.wikipedia.org/wiki/Intermediate_representation)*이 있다. IR은 IL을 포괄하는 개념이다. [Intermediate Representation 과 Intermediate Language](https://www.quora.com/What-is-the-dIfference-between-intermediate-representation-and-intermediate-language) 참고

### 자바 바이트 코드 출력

첫 번째 방법으로는 커맨드 라인에서 javap 도구를 활용하는 방법이다. `.class` 파일이 있는 경로에서 `javap -c {target}.class` 를 입력하면 -c 옵션으로 디스어셈블한 코드가 출력된다. 아래는 `javap -c Hello.class`로 디스어셈블한 결과로 출력되는 바이트 코드이다.

```java
Compiled from "Hello.java"
public class Hello {
  public Hello();
    Code:
      0: aload_0
      1: invokespecial #1      // Method java/lang/Object."<init>":()V
      4: return

  public static void main(java.lang.String[]);
    Code:
      0: getstatic     #7      // Field java/lang/System.out:Ljava/io/PrintStream;
      3: ldc           #13     // String This is the Java program
      5: invokevirtual #15     // Method java/io/PrintStream.println:(Ljava/lang/String;)V
      8: return
}
```



## JIT 컴파일러란 무엇이며 어떻게 동작하는지

자바에서 "Write Once, Run Everywhere"의 핵심은 `bytecode`이다. *바이트 코드가 애플리케이션에 대한 적절한 기본 명령어로 변환되는 방식은 애플리케이션 속도에 큰 영향을 미친다.*  이러한 바이트 코드는 해석되거나 원시 코드로 컴파일되거나, 또는 인스트럭션 셋 아키텍처가 바이트 코드 스펙에 맞는 프로세서에서 직접 실행될 수도 있다. JVM의 표준 구현인 바이트 코드를 인터프리리팅 하게되면 프로그램 실행 속도가 느려진다. 그래서 성능을 향상시키기 위해 JIT 컴파일러는 런타임에 JVM과 상호 작용하고 적절한 바이트 코드 시퀀스를 원시 기계 코드로 컴파일한다. JIT 컴파일러를 사용할 때 하드웨어는 JVM이 동일한 바이트 코드 시퀀스를 반복적으로 해석하고 원시 코드를 실행할 수 있다. 이것은 실행 속도의 성능 향상으로 이어질 수 있다. 메소드가 덜 자주 실행되지 않는 한, JIT 컴파일러가 바이트 코드를 컴파일하는 데 걸리는 시간은 전체 실행 시간에 추가되며 JIT에 의해 컴파일 된 메소드가 자주 호출되지 않는 경우 바이트 코드를 실행하는 인터프리터보다 실행 시간이 길어질 수 있다. JIT 컴파일러는 바이트 코드를 원시 코드로 컴파일 할 때 특정 최적화를 수행합니다. JIT 컴파일러는 일련의 바이트 코드를 네이티브 명령어로 변환하므로 몇 가지 간단한 최적화를 수행 할 수 있습니다. JIT 컴파일러가 수행하는 일반적인 최적화 중 일부는 데이터 분석, 스택 작업에서 레지스터 작업으로의 변환, 레지스터 할당에 의한 메모리 액세스 감소, 공통 하위 표현식 제거 등 이다. JIT 컴파일러가 수행하는 최적화 수준이 높을수록 실행 단계에서 더 많은 시간을 소비한다. 따라서 JIT 컴파일러는 실행 시간에 추가되는 오버헤드와 프로그램의 제한된 보기만 있기 때문에 정적 컴파일러가 수행하는 모든 최적화를 수행 할 여유가 없다.

### JIT 동작원리

JIT (Just-In-Time) 컴파일러는 런타임 시 자바 애플리케이션의 성능을 향상시키는 JRE(Java Runtime Environment)의 구성요소이다. 자바 프로그램은 다양한 컴퓨터 아키텍처에서 JVM이 해석 할 수있는 플랫폼 중립 바이트 코드를 포함하는 클래스로 구성된다. 런타임시 JVM은 클래스 파일을 로드하고 각 개별 바이트 코드의 의미를 결정하며 적절한 계산을 수행한다. 해석 중 추가 프로세서 및 메모리 사용량은 자바 응용 프로그램이 기본 응용 프로그램보다 느리게 수행됨을 의미한다. JIT 컴파일러는 런타임 시 바이트 코드를 원시 기계 코드로 컴파일하여 자바 프로그램의 성능을 향상시킨다.

JIT 컴파일러는 기본적으로 사용 가능하며 자바의 메서드가 호출될 때 활성화된다. JIT 컴파일러는 해당 메서드의 바이트 코드를 원시 기계 코드로 컴파일하여 "적시에(Just In Time)"실행되도록 컴파일한다. 메서드가 컴파일되면 JVM은 해석하는 대신 해당 메서드의 컴파일 된 코드를 직접 호출한다. 이론적으로 컴파일에 프로세서 시간과 메모리 사용이 필요하지 않은 경우, 모든 메서드를 컴파일하면 자바 프로그램의 속도가 네이티브 응용 프로그램의 속도에 근접할 수 있다.
JIT 컴파일에는 프로세서 시간과 메모리 사용량이 필요합니다. JVM이 처음 시작되면 수천 개의 메소드가 호출됩니다. 이러한 방법을 모두 컴파일하면 프로그램이 결국 매우 우수한 최고 성능을 달성하더라도 시작 시간에 상당한 영향을 미칠 수 있습니다.

## JVM 구성 요소

![JVM-Architecture](/Users/ham/Pictures/Blog/JVM-Architecture.png)

JVM의 아키텍쳐는 크게 세 가지 구성요소로 나뉜다.

1. ClassLoader Subsystem
2. Runtime Data Area
3. Execution Engine

### 1. ClassLoader Subsystem

자바의 [동적 클래스 로딩](http://www.javainterviewpoint.com/use-of-class-forname-in-java/) 기능은 ClassLoader 하위 시스템에 의해 처리된다. 클래스 파일을 로드, 링크. 초기화를 하는데, 컴파일 타임이 아니라 **런타임 시** 맨 처음 클래스를 참조 하는 시점에 수행한다.

#### 1.1 Loading

이 구성 요소에 의해 클래스가로드됩니다. BootStrap ClassLoader, Extension ClassLoader 및 Application ClassLoader는이를 달성하는 데 도움이되는 세 가지 ClassLoader입니다.

1. **BootStrap ClassLoader** – 3가지 기본 클래스로더 중 최상위 클래스로더이다.(가장 높은 우선 순위가 부여된다) 기본적으로 부트 스트랩 클래스패스에서 *rt.jar*에 담긴 JDK 클래스 파일을 로드한다. → **가장 먼저 로드된다!**
2. **Extension ClassLoader** – *jre/lib/ext* 내에있는 클래스를 로드한다. 또는 *java.ext.dirs* 환경 변수로 지정된 폴더에 있는 클래스 파일을 로드한다.
3. **Application ClassLoader** – 애플리케이션 레벨 클래스패스, 또는 패스가 언급된 환경변수 등을 로드한다. 개발자가 애플리케이션 구동을 위해 직접 작성한 대부분의 크래스는 이 로더에 의해 로딩된다.

참고로, 자바9에서는 기본 클래스로더의 3계층 구조와 3가지 원칙을 동일하나, 클래스로더 일부의 명칭과 구현 내용이 조금 변경되었다.

- https://docs.oracle.com/javase/9/migrate/toc.htm#JSMIG-GUID-A78CC891-701D-4549-AA4E-B8DD90228B4B
- https://homoefficio.github.io/2018/10/13/Java-%ED%81%B4%EB%9E%98%EC%8A%A4%EB%A1%9C%EB%8D%94-%ED%9B%91%EC%96%B4%EB%B3%B4%EA%B8%B0/

#### 1.2 Linking

1. **Verify** – 바이트 코드 검증기는 생성 된 바이트 코드가 적절한 지 여부를 검증한다. 검증이 실패하면 확인 오류가 발생한다.
2. **Prepare** – 모든 정적 변수에 대해 메모리가 할당되고 기본값으로 할당된다.
3. **Resolve** – 모든 심볼릭 메모리 참조가 메서드 영역의 원래 참조로 대체된다.

#### 1.3 Initialization

클래스 로딩의 마지막 단계이다. 여기서 모든 [정적 변수](http://www.javainterviewpoint.com/use-of-static-keyword-in-java/) 는 원래 값으로 할당되고 [정적 블록](http://www.javainterviewpoint.com/java-static-import/) 이 실행된다. 이 시점을 잘 고려한다면 `static `키워드의 사용을 적절하게 할 수 있을 것이다.

### 2. Runtime Data Area

런타임 데이터 영역은 JVM이 프로그램을 수행하기 위해 운영체제로부터 할당 받는 메모리 영역이다. 이는 각 목적에 따라 5개의 주요 구성 요소로 나누어진다.

#### 2.1. 메서드 영역

`static` 변수를 포함하여 모든 클래스 수준 데이터가 여기에 저장된다. JVM 당 하나의 메소드 영역만 있으며, 이는 공유 자원이다.

#### 2.2. 힙 영역

모든 객체와 해당 인스턴스 변수 및 배열이 여기에 저장된다. JVM 당 하나의 힙 영역도 있다. 메서드 및 힙 영역은 여러 스레드에 대한 메모리를 공유하므로 저장된 데이터는 스레드로부터 안전하지 않다.

#### 2.3. 스택 영역

모든 스레드에 대해 별도의 런타임 스택이 생성된다. 모든 메서드 호출에 대해 스택 프레임이라는 스택 메모리에 하나의 항목이 만들어진다. 모든 지역 변수는 스택 메모리에 생성된다. 스택 영역은 공유 리소스가 아니므로 스레드로부터 안전하다. 스택 프레임은 세 가지 하위 항목으로 나뉜다.

- **로컬 변수 배열** – 관련된 로컬 변수 수와 해당 값이 여기에 저장되는 방법과 관련된다.
- **피연산자 스택** – 수행하는 데 중간 작업이 필요한 경우 피연산자 스택은 작업을 수행하는 런타임 작업 영역으로 작동한다.
- **프레임 데이터** – 방법에 해당하는 모든 심볼이 여기에 저장된다. 임의의 경우에 **예외를** 캐치 블록 정보는 프레임 데이터를 유지한다.

#### 2.4. PC 레지스터

각 스레드에는 별도의 PC 레지스터가 있으며, 스레드가 생성될 때 마다 생기는 공간이다. 이는 스레드가 어떤 명령을 다음으로 실행하게 될지 그 주소를 저장한다. 명령이 실행되면 현재 실행중인 명령의 주소를 유지하기 위해 PC 레지스터가 다음 명령으로 업데이트된다.

#### 2.5. 네이티브 메서드 스택

네이티브 메서드 스택은 네이티브 메서드 정보를 가진다. 모든 스레드에 대해 별도의 네이티브 메서드 스택이 생성된다.

### 3. Execution Engine

**Runtime Data Area에** 할당된 바이트 코드는 Execution Engine에 의해 실행된다. Execution Engine은 바이트 코드를 읽고 조각별로 실행합니다.

#### 3.1. 인터프리터 

인터프리터는 바이트 코드를 더 빨리 해석하지만 느리게 실행됩니다. 인터프리터의 단점은 하나의 메서드가 여러 번 호출 될 때마다 새로운 해석이 필요할 때마다 있다는 것입니다.

#### 3.2. JIT 컴파일러

JIT 컴파일러는 인터프리터의 단점을 무력화한다. 실행 엔진은 바이트 코드를 변환하는 데 인터프리터의 도움을 사용하지만 반복 된 코드를 찾으면 전체 바이트 코드를 컴파일하고 네이티브 코드로 변경하는 JIT 컴파일러를 사용한다. 이 네이티브 코드는 반복되는 메서드 호출에 직접 사용되어 시스템 성능을 향상시킨다.

- **Intetmediate Code Generator** – 중간 코드 생성
- **Code Optimizer** – 위에서 생성 된 중간 코드를 최적화
- **Target Code Generator** – 기계 코드 또는 네이티브 코드 생성 담당
- **Profiler** – 핫스팟(메서드가 여러 번 호출되는지 여부) 찾기를 담당하는 특수 구성 요소, 

**3.3. Garbage Collector** : 참조되지 않는 개체를 수거하고 제거한다. Garbage Collection은 `System.gc()`를 호출하여 트리거할 수 있지만 무조건 실행이 보장되지는 않는다. JVM의 Garbage Collection은 생성된 객체를 수거한다.

### 4, NI (Java Native Interface)

JNI는 Native Method Libraries와 상호 작용하며 Execution Engine에 필요한 Native Libraries를 제공

### 5. Native Method Libraries

Execution Engine에 필요한 Native Libraries의 모음

## JDK와 JRE의 차이

JDK(Java Development Kit)는 자바의 full-featured SDK이다. 이것은 JRE가 가지고 있는 모든 것을 포함하며, 컴파일러(javac) 및 각종 개발도구(javadoc과 jdb 등)를 포함한다.

JRE(Java Runtime Environment)는 컴파일된 자바 프로그램을 실행하기 위해서 핅요한 모든 것들의 패키지이다. JVM과 자바 클래스 라이브러리,  `java` 명령, 그리고 다른 인프라를 포함한다. 이것으로 새로운 프로그램을 생성해낼 수는 없다. 

## References

### 자바 클래스 파일

- https://www.geeksforgeeks.org/java-class-file/

### 바이트 코드

- https://docs.oracle.com/en/java/javase/15/docs/specs/man/javap.html
- https://www.baeldung.com/java-class-view-bytecode

### JIT

- https://aboullaite.me/understanding-jit-compiler-just-in-time-compiler/#:~:text=The%20JIT%20compiler%20is%20enabled,directly%20instead%20of%20interpreting%20it.

### JVM 아키텍쳐

- https://www.geeksforgeeks.org/jvm-works-jvm-architecture/
- https://dzone.com/articles/jvm-architecture-explained
- https://en.wikipedia.org/wiki/Java_virtual_machine
- https://homoefficio.github.io/2018/10/13/Java-%ED%81%B4%EB%9E%98%EC%8A%A4%EB%A1%9C%EB%8D%94-%ED%9B%91%EC%96%B4%EB%B3%B4%EA%B8%B0/