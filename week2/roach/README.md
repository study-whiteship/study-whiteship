# Week2 : 자바 데이터 타입, 변수 그리고 배열

## **목표**

자바의 프리미티브 타입, 변수 그리고 배열을 사용하는 방법을 익힙니다.

## **학습할 것**

- 프리미티브 타입 종류와 값의 범위 그리고 기본 값
- 프리미티브 타입과 레퍼런스 타입
- 리터럴
- 변수 선언 및 초기화하는 방법
- 변수의 스코프와 라이프타임
- 타입 변환, 캐스팅 그리고 타입 프로모션
- 1차 및 2차 배열 선언하기
- 타입 추론, var

## Primitive Type

[Primitibe Type Table](https://www.notion.so/a55978f5545b4f638744ddd65b7df068)

여기서 위의 부분은 보통의 외우고 있지는 않지만 다들 알고 있는 테이블일 것이다.

기본형의 경우는 우리가 앞에서 배웠던 JVM 의 해당 값을 default 값으로 저장할때 위의 표를 참조하면 되겠다.

(**Linking 의 prepare 부분 참조!)**

## Primitive Type 의 특징

- Primitive Type 에 대한 약간의 설명을 하자면 해당 수행은 long 과 double 을 빼고는 `원자적 수행` 을 보장한다. ⇒ 이 부분은 내가 volatile 을 정리했을때 설명했던 부분이다.
- 뭐 이부분은 `long` 과 `double` 혹은 `dynamic-computed constant` 의 경우 constant pool 에서 해당 index 를 참조하여 가져오는 과정이 포함되어 있어서 해당 과정에서 다른 Thread 가 끼어들 수 있기에 원자적 수행을 보장하지 못한다고 생각하는게 좋지 않을까 싶다.
- OS 에 따라 자료형의 길이가 변하지 않는다.
- 비객체 타입으로 `null` 값을 가질 수 없다.
- Primitive Type 은 Thread 의 Stack 내 Frame 에 바로 저장된다. 아래 링크 참조해도 좋음

    [Week1 : JVM 이란 무엇이며 자바코드는 어떻게 실행되는 것인가?](https://www.notion.so/Week1-JVM-631ad7a1abae4dc596481f36266a4f42)

## Reference Type

- 참조형은 `java.lang.Object` 를 상속 받고 있어야 한다.
- 컴파일을 진행한 후 class 파일을 `javap` 를 통해서 분석해보면 저런 코드가 나온다.
    - `extends java.lang.Object`

```java
class GenericExample<T extends java.lang.Object> extends java.lang.Object
```

- 우리가 보통 Class 로 선언한 것을 변수 타입으로 선언하면 참조형 변수로서 사용할 수 있다.
- 우리가 아래 Data 라는 Class 를 만들고 참조형 변수로서 사용한다고 하면

```java
class Node {
	int data;

	public void setData(int value) {
		this.data = value;
	}

	public int getData() {
		return this.data;
	}
}

Other class..

public static void main(String[] args) {
	 Node node = new Node();
	 node.setData(3);
	 node.getData() == 3 // true
}
```

저번 주차에서 했던 내용과 연관지어서 정리해보자면, Reference Type 의 값을 Stack Frame 내에 저장할 때는 refernce (**즉, Head 영역의 일정 주소를 가르키도록 저장되는데**) 이런 개념들을 떠올려 보았을때 new Node() 라는 부분이 node (reference) 에게 `return Instance Address` 를 하고 있다고 생각할 수 있다. 즉, 주소값을 주입시켜준다고 생각할 수 있다. 그래서 이제 해당 `node` 는 heap 상의 한 영역을 계속해서 바라보고 있는 상태가 되는 것이다. 아마 생성자가 저러한 역할을 하는것이 아닐까라는 추측을 해본다.

### ByteCode 상에서..

```java
class GenericExample<T> {
     T data;

     public GenericExample(T data) {
             this.data = data;
     }

     public static void main(String[] args) {
             GenericExample<String> ge = new GenericExample<>("Cool");
     }
}
```

```java
0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: aload_0
         5: aload_1
         6: putfield      #7                  // Field data:Ljava/lang/Object;
         9: return
```

생성자가 위와 같이 되어있는데 간단하게 보면 일단 `data` 를 reference 로 stack Index #1 에 저장하고,

5 번줄에서 `aload_1` 을 통해 stack 내의 lva 에서 1번을 꺼내오고, `putfield` 를 해주는데

이때 `putField` 에서 매개변수로 넘어온 data object 의 ref 를 대입해준다. (**틀린 부분이 있을수도..?**)

### 특징

- Reference Type 은 위에 Heap 영역의 주소값을 Reference 하는 것이므로 1주차에 정리한 내용처럼 스레드간 공유될 수 있다.
- `this` 라는 키워드를 이용할 수 있는데 이는 객체 자기 자신을 나타낸다.

## 리터럴

- 리터럴은 변하지 않는 값을 의미하는데 만약 우리가 `int a = 10`  을 한다면 여기서 `10` 이 리터럴이다.
- 그니까 변하지 않는 데이터들 예를 들면 `Primitive Type` 혹은 `불변의 객체` 들이 리터럴이 될 수 있겠다.
- 그럼 여기서 불변의 객체 ( Immutable Object ) 란 무엇인가?
    - 우리 주위에서 가장 찾기 쉬운 예로는 String 이 있겠다.
    - String 의 경우 " " 를 이용하여 생성할 경우 해당 문자가 Heap 영역의 `String Constant Pool` 에 생성된다. 그래서 만약 아래와 같은 코드를 적는다고 했을때

    ```java
    String s = "Hello";
    String b = "Hello";
    ```

    - 처음에 `String s = "Hello"` 를 했을때 Hello 에 대한 String 객체를 생성하여 Heap 영역의 `String Constant Pool` 에 저장하고, b 에서 `"Hello"` 를 쓸때에는 `String Constant Pool` 에 있는 주소값을 가져와서 참조하는 것이다. (이 부분은 `intern()` 을 키워드로 검색해보면 좋다!)
    - 자 그렇다면 우리가 한번 이런걸 생각해보자!
        - `s == b` 는 true 일까? 물론 true 가 나온다.

### 변수 선언 및 초기화 하는 방법

- 자바는 변수를 선언하는 방법은  `타입 변수명 ;` 으로 보통 선언한다.
- 초기화 하는 방법은 변수 선언시에 해당 변수에 타입에 맞는 값을 대입해 주면 된다.

### 변수의 스코프와 라이프 타임

- 변수의 스코프는 그 변수가 어디에 속해있는가에 그리고 어떻게 선언되어 있는가에 따라 다를것 같은데 따라서 특정 상황에 따라서 설명하겠다.

### 메소드 안의 지역 변수일 경우

```java
public void method() {
		int a = 10;
}
```

- 위와 같이 선언되있을 경우 Method 의 Stack Frame 내의 Local variable 에 a 가 저장될 것이다. 그리고 `method` 가 종료되면, 해당 스택도 `pop` 됬으므로 a 의 라이프 타임또한 종료된다.
- 즉 라이프 타임이 : `Method Start ~ Method End`

### 클래스 내에 전역으로 선언된 변수일 경우

```java
public class Do {
	public static int dodo = 10;
}
```

- 이런 변수의 경우 Method Area 의 Class Variable 에 저장된다.
- 이 변수는 인스턴스의 것이 아니라 클래스에 속하게 되며 모든 인스턴스에 공유되는 자원이다. ( 즉 클래스당 하나의 자원이라고 볼 수 있다. )
- 뭐 final 을 붙이면 `Runtime Constant Pool` 에 저장된다고 한다
- JVM 지식을 붙여서 이제 라이프 타임을 설명하면
- Life Time : `Linking 의 prepare ~ Class Unloading` 까지 선언된다고 할 수 있겠다.
- 밑에 글은 Class Unloading 에 관한 좋은 글이다

[Unloading classes in java?](https://stackoverflow.com/questions/148681/unloading-classes-in-java)

### 객체의 경우

- 객체의 경우는 GC 가 수집하는 순간이 죽는 시간이 아닐까 싶다.
- LifeTime : `할당된 순간 ~ 수집된 순간`

### 여담.. 메모리 캐쉬

```java
public class Test {

    private static boolean stopRequested;

    public static void main(String[] args) throws InterruptedException {
        Thread backgroundThread = new Thread(()->{
            int i = 0;
            while(!stopRequested){
                i++;
            }
        });
        backgroundThread.start();

        TimeUnit.SECONDS.sleep(1);
        stopRequested = true;
    }
}
```

위의 클래스는 종료되지 않고 영원히 도는데 그 이유는 `backGroung Thread` 와 `Main Thread` 모두 각 Stack Frame 내에서 stopRequested 라는 값을 가지고 있는데 그걸 Main Memory 에 있는 값을 가져오는 것 보다는 로직 중에는 캐싱 된 값을 이용한다. 그래서 위의 코드가 종료되지 않는 현상이 일어나는데.. 이때 `volatile` 을 이용하면 종료 할 수 있다. 위의 코드도 어떻게 보면 캐싱해서 사용하는 방법으로 인해 문제가 일어날 수 있기에 한번쯤 봐두면 좋을듯 하다..?

## 타입 변환, 캐스팅 그리고 타입 프로모션

- 타입 변환에는 우리가 잘 알고 있듯이 묵시적 / 명시적 타입변환으로 나뉜다.

### 자동 타입 변환 (Promotion)

- 이건 보통 더 큰 타입에 작은 타입을 넣으려고 할때 일어난다.
- 뭐 간단히 예시를 들어본다고 하면

```java
byte a = 10;
int b = a;
```

- int 가 byte 보다 큰값이기에 자동으로 형변환이 일어난다.

### 강제 타입 변환 (Casting)

- `작은 크기 타입 = (작은 크기 타입) 큰 크기 타입`

```java
int a = 10000000;
byte b = (byte) a;
```

- 하지만 byte 의 크기에 맞춰야 하므로 int 의 수가 잘려서 출력될 수 있다.
- 값의 신뢰성을 보장할 수 없다.(이 부분은 MAX_VALUE 등의 것들로 아예 못되게 막거나 여튼 그건 그때 알아보고! )

## 1차 및 2차 배열 선언하기

```java
int[] a = new int[10];
int[][] b = new int[10][10];
```

- 위와 같이 1, 2차 배열을 선언할 수 있으며 위에 `new int[SIZE]` 에 명시한 크기만큼 생성된다.
- 1차원 배열은 메모리 상에 1자로 배치되어 공간 복잡도에서 우위를 가질 수 있다.
- 이 부분은 호눅스가 수업했을때 C 언어로 배열이 어떻게 구현되어 있는지에 대해 보여준적이 있었는데 그게 상당히 도움이 많이 되었다

### 타입 추론, var

- 이 컨벤션은 주로 JavaScript , Python 진영에서 주로 사용하는 것으로 알고 있는데 Java 에도 Java 10 이상 부터 도입된것으로 알고 있다. 사실 나는 이방법을 상당히 좋아하지 않는다. 이렇게 애매한 것들로 과연 코드 공유가 이루어지고 복잡해지는 코드에서 유지보수가 가능할지? 이런 의문점이 든다. 여하튼 이런 점이 있더라도 공부해보는 것은 나쁘지 않으므로 알아보자!
- 간략히 읽은 내용을 정리해보면 컴파일 단계에서 `right-hand side` 의 값을 보고 왼쪽 값의 타입을 추론하는 것이라고 한다. 뭐 JavaScript 처럼 완전한 var 는 아닌것 같다. 왜냐하면 컴파일 단계에서 `right-hand side` 의 값을 기반으로 이루어지므로 `initializer` 가 반드시 있어야 한다.
- 사실 이렇게 사용하면 과연 쓰는 이점이 있나 싶다 ㅋㅋ??
- 뭐 이런 방식을 이용해서 runtime 시에 overhead 가 없다고 하는데 아니 이렇게 컴파일시에 이용하면 쓸 이유가 있나? 난 잘모르겠다..

```java
var idToNameMap = new HashMap<Integer, String>();
```
