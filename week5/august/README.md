# [Week5] 클래스

## 클래스 정의하는 방법

```java
class 클래스명 {
	클래스필드;		// 객체의 속성
	클래스메서드;		// 객체의 기능
}
```

자바에서는 `class` 키워드로 간단히 클래스의 정의가 가능하다. 좀 더 실질적인 예를 들어보면 다음과 같다.

```java
class Television {
    String color;
    boolean power;
    int channel;

    void power() { power = !power; }
    void channelUp() { ++channel; }
    void channelDown() { --channel; }
}
```

이런식으로 텔레비전 클래스에 대한 정의가 가능하다. 하지만 이렇게 클래스만 정의해서는 바로 프로그램 상에서 사용할 수 없다. 기존에는 이를 '붕어빵 틀'에 비유하곤 했는데, 이는 정확한 비유라고 보기 힘들다. *왜냐하면 붕어빵 틀은 절대로 붕어빵이 될수가 없기 때문이다.* 하지만 클래스는 그 자체가 new 키워드에 의해 그대로 붕어빵이 된다. 따라서 `영혼은 없고 육체만 있는 붕어빵`이라는 표현이 차라리 더 적절한 것 같다.

추상적인 설명말고 JVM과 메모리의 관점에서 본다면, 인스턴스와는 다르게 클래스 및 클래스 데이터는 메서드 영역에 저장된다. 

'클래스는 객체의 설계도'라는 말은 어느정도 타당하다. 왜냐하면 건축물이 설계도를 바탕으로 만들어지듯이, 객체도 클래스를 기반으로 생성되기 때문이다. 

## 객체 만드는 방법 (new 키워드 이해하기)

자바에서 클래스로부터 객체를 만드는 과정을 클래스의 인스턴스화(instantiate)라고 하며, 클래스로부터 만들어진 객체를 그 클래스의 인스턴스(instance)라고 한다.

클래스의 설명해서 언급했듯이 인스턴스화는 붕어빵에 영혼이 깃드는 것과 같다고 생각할 수 있다. 실제로, `new`라는 키워드로 인스턴스가 생성되면 메모리의 힙(Heap) 영역에 할당된다. 메서드 영역에는 클래스나 클래스 데이터처럼 정적인(static) 요소들이 주로 저장되는 반면, 스택과 힙 영역에는 프로그램 실행 중(런타임)에 동적으로 변경되는 데이터가 저장된다.

```java
public class ClassTest {
    public static void main(String[] args) {
        Television television;          // declare reference type variable
        television = new Television();  // instantiate Television class
        new Television();               // instantiated but cannot be used (lost soul)

        // use the class field and method by instance
        television.channel = 7;
        television.channelUp();
        System.out.println("Current channel is : " + television.channel);
        television.channelDown();
        System.out.println("Current channel is : " + television.channel);
    }
}
```

앞서 정의한 Television 클래스를 사용하려면 먼저 참조변수를 선언해야 한다. 참조변수를 선언하지 않고 그냥 new로 생성하면, 힙 영역의 어딘가에 생성은 될 것이다. 하지만 이를 참조할 방법이 없으므로, 길잃은 영혼처럼 메모리를 떠돌다가 GC에 의해 해제되거나 프로그램이 종료할 때 사라질 것이다. 따라서 **인스턴스는 참조변수를 통해서만 접근할 수 있으며, 참조변수의 타입은 인스턴스의 타입을 담을 수 있어야한다.**

## 메소드 정의하는 방법

메서드(method)는 특정 작업을 수행하는 일련의 문장들을 하나의 블록으로 묶은 것이다. 이는 C언어의 함수, 수학의 함수와 그 개념이 유사하다. 단, 수학에서의 함수는 입력값과 출력값이 반드시 있어야한다. 반면, 자바에서 메서드는 입력값이 또는 출력값이 있거나 없을 수도 있고, 둘 다 없을 수도 있다.

```java
리턴타입 메서드명 (매개변수 선언) {
		메서드의 실행문;
		return 리턴값;
}
```

위와 같이 메서드를 정의할 수 있는데, `리턴타입 메서드명 (매개변수 선언)` 이 부분만 `메서드 선언부 ` 또는 `메서드 헤더`라고 한다. 참고로, `메서드 시그니처`는 **리턴타입을 제외한 선언부를 의미한다.** 메서드 오버로딩에서 서로 다른 메서드라고 구분짓는 기준이 바로 메서드의 시그니처이다.

>The **signature** of a method consists of the name of the method and the description (i.e., type, number, and position) of its parameters.
>
>*Example:*
>
>- `toUpperCase()`
>- `println(String s)`
>
>*Note:* the names of the parameters have no relevance for the signature
>
>The **header** of a method consists of the signature plus the description of (the *type* of) the result.
>
>*Example*:
>
>- `String toUpperCase()`
>- `void println(String s)`
>
>source : https://www.inf.unibz.it/~calvanese/teaching/05-06-ip/lecture-notes/uni02/node7.html

### 메서드 오버로딩

자바에서는 한 클래스 내에 이미 사용중인 이름으로 또다른 메서드를 정의할 수 있도록 하였다. 단, 여기에는 두 가지 조건이 있다.

> 1. 메서드 이름이 같아야 한다.
> 2. 매개변수의 개수 또는 타입이 달라야 한다.

즉, 메서드의 시그니처 중에서 매개변수 부분만 다르면 오버로딩 할 수 있다는 뜻이다. 여기서 **메서드 헤더 중 리턴타입은 오버로딩에 아무런 상관이 없다는 것에 주의해야 한다.** 다시 말하면, 오버로딩된 메서드에서 리턴타입은 같아도 되고 달라도 된다. (리턴타입만 다르고 시그니처가 완전히 동일하다면 이미 선언된 메서드라고 오류가 뜬다.)

```java
class Calculator {
    int add(int a, int b) {
        return a + b;
    }

    int add(int a, int b, int c) { // different number of args
        return a + b + c;
    }

    int add(String a, String b) { // different type of args
        return Integer.parseInt(a) + Integer.parseInt(b);
    }

    // error: Duplicate method add(int, int) in type Calculator
    // String add(int a, int b) { 
    //     return a + b;
    // }
}

```

## 생성자 정의하는 방법

생성자는 인스턴스가 생성될 때 **무조건** 호출되는 인스턴스 초기화 기능을 담당하는 일종의 메서드이다. 따라서 인스턴스 변수의 초기화 작업에 주로 사용되며, 인스턴스 생성 시에 가장 먼저 실행되어야 할 작업을 위해서도 사용된다. 의존을 주입 받는 것 또한 '가장 먼저 선행되어야할 작업' 중 하나이므로 생성자에서 수행한다.

생성자 역시 메서드처럼 클래스 내에 선언되며, 구조도 메서드와 유사하지만 리턴값이 없다. 생성자의 조건은 다음과 같다.

>1. 생성자의 이름은 클래스의 이름과 같아야 한다.
>2. 생성자는 리턴값이 없다.

위의 두 조건을 다시 말하면, 클래스의 이름과 동일한 메서드는 생성자가 유일하다. 그리고 모든 생성자는 리턴값이 없으므로 void가 생략된다. 생성자의 정의는 다음과 같이 할 수 있다.

```java
클래스이름(타입 변수명, 타입 변수명, ...) {
		// 인스턴스 생성 시 수행될 코드
		// 주로 인스턴스 변수의 초기화 코드
}
```

```java
class Computer {
    Computer() { }
}
```

위의 코드가 가장 간단한 형태의 생성자이다. 하지만 생성자를 별도로 정의해주지 않아도 new 키워드로 얼마든지 클래스를 인스턴스화 하여 사용할 수 있다. 그 이유는 클래스 내에 정의된 생성자가 하나도 없다면 컴파일러가 자동으로 생성하기 때문이다. 하지만 이는 **클래스 내에 정의된 생성자가 하나도 없을 때** 만 해당한다.

### 생성자 오버로딩

- 생성자 또한 일종의 메서드이다.
- 클래스 내에 정의된 생성자가 하나도 없을 때 컴파일러가 디폴트 생성자를 자동으로 생성해준다.

이 두가지만 보았을 때, 앞서 언급한 메서드 오버로딩이 떠오를 것이다. 실제로 자바에서는 생성자도 일반적인 메서드와 마찬가지로 오버로딩이 된다.

```java
class Computer {
    double cpu = 2.4;
    int core = 2;
    int memory = 256;

//  Computer() { }
  
    Computer(double cpu) {
        this.cpu = cpu;
    }

    Computer(double cpu, int core, int memory) {
        this.cpu = cpu;
        this.core = core;
        this.memory = memory;
    }
}

public class Constructor {
    public static void main(String[] args) {
        Computer computer = new Computer(); // The constructor Computer() is undefined
    }
}

```

위의 코드에서 생성자 여러개가 오버로딩으로 정의되었다. 각 시그니처에 맞는 호출에 대하여 대응하는 생성자가 호출될 것이다. 현재 디폴트 생성자는 주석 처리하여 정의되지 않은 상태이다. 그런데 main 메서드에서 `new Computer()`로 디폴트 생성자를 호출한다.

**디폴트 생성자가 없는데 매개변수를 받는 다른 생성자가 하나라도 정의되어 있다면 컴파일러는 도와주지 않는다.** 그래서 디폴트 생성자를 직접 정의하지 않는다면, 디폴트 생성자로 생성하려고 할 때 에러가 발생한다.

## this 키워드 이해하기

this는 크게 두 가지 용도로 쓰인다. 첫 번째는 생성자에서 다른 생성자를 호출하기 위한 `this()`이다. 그리고 두 번째는 참조변수로서 자기 자신의 인스턴스를 가리키는 `this`이다.

>- this : 인스턴스 자신을 가리키는 참조변수, 인스턴스의 주소가 저장되어 있다.
>- this(), this(매개변수) : 생성자, 같은 클래스의 다른 생성자를 호출할 대 사용된다.

```java
class Car {
    String color;
    String tire;
    int velocity;

    Car() {
        this("Red", "Kumho", 80);
    }

    Car(String color) {
        this(color, "Kumho", 80);
    }

    Car(String color, String tire, int velocity) {
        this.color = color;
        this.tire = tire;
        this.velocity = velocity;
    }

}

public class This {
    public static void main(String[] args) {
        Car car = new Car();
        System.out.println(car.color + " " + car.tire + " " + car.velocity);
        car = new Car("Black");
        System.out.println(car.color + " " + car.tire + " " + car.velocity);
        car = new Car("Pink", "Hankook", 160);
        System.out.println(car.color + " " + car.tire + " " + car.velocity);
    }
}
```

```
Red Kumho 80
Black Kumho 80
Pink Hankook 160
```