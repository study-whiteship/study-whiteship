# [Week8] 인터페이스

## 인터페이스 정의하는 방법

### 인터페이스

인터페이스는 일종의 추상클래스이다. 인터페이스는 추상클래스처럼 추상메서드를 갖지만 추상클래스보다 추상화 정도가 높다. 다시 말해, 추상클래스와 달리 바디를 가지는 일반 메서드 또는 멤버 변수를 필드로 가질 수 없다. 즉, **오직 추상메서드와 상수만을 멤버로 가질 수 있다.**

추상클래스를 부분적으로만 완성된 '미완성 설계도'라고 한다면, 인터페이스는 구현된 것은 아무것도 없고 밑그림만 그려져 있는 '기본 설계도'라 할 수 있다.

### 인터페이스의 정의

```
interface {interface name} {
		public static final {type} {constant name} = {initial value};
		public abstract {method name}({parameters});
}
```

### 인터페이스 제약 사항

- 모든 멤버변수는 public static final 이어야 하며, 생략가능
- 모든 메서드는 public abstract 이어야 하며, 생략 가능
  - 단, static 메서드와 디폴트 메서드는 예외 (Java8 이상)

이러한 제어자는 모든 필드에 공통적으로 적용되는 사항이기 때문에 생략 가능하다. 이렇게 생략된 제어자는 컴파일러가 자동을 추가를 해준다.

---

## 인터페이스 구현하는 방법

인터페이스도 추상클래스처럼 그 자체로는 인스턴스를 생성할 수 없으며, 추상클래스가 상소을 통해 추상메서드를 완성하는 것처럼, 인터페이스도 자신에 정의된 추상메서드의 바디를 만들어주는 클래스를 추가로 작성해야한다.

```java
class {class name} implements {interface name} {
		// Must implements abstract method which declared in interface!
}
```

만일 구현하는 인터페이스의 메서드 중 일부만 구현한다면, abstract를 붙여서 추상클래스로 선언해야 한다. 그리고 이를 상속하는 자식 클래스에서 결국에는 반드시 모두 구현해야한다.

```java
abstract class Flight implements Flyable {
		public void fly(int roll, int pitch, int yaw) {
				...
		}
}
```

```java
class Flight extends Mobility implements Flyable {
		public void fly(int roll, int pitch, int yaw) {
				...
		}
		public void isFaster(Mobility mobility) {
				...
		}
}
```

---

## 인터페이스 레퍼런스를 통해 구현체를 사용하는 방법

인터페이스 타입의 참조변수에 구현체(인터페이스를 implements하는 클래스)를 생성하여 담을 수 있다. 대표적인 예로 List에 ArrayList를 담는 경우가 있다. 이것도 일종으 업캐스팅에 해당한다. 이 경우에 인터페이스 참조변수를 통해서는 인터페이스에 정의된 메서드만 사용할 수 있다. 따라서 구현체의 고유한 메서드 또는 오버라이딩 된 메서드를 사용하기 위해서는 일시적으로 다운캐스팅 해주어야 한다.

```java
import java.util.List;
import java.util.ArrayList;

public class InterfaceReference {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println(list);
    }
}
```

---

## 인터페이스 상속

인터페이스는 인터페이스로부터만 상속받을 수 있으며, 클래스와는 달리 다중ㅅ항속, 즉 여러 개의 인터페이스로부터 상속을 받는 것이 가능하다. (인터페이스는 클래스와 달리 Object 클래스와 같은 최상위 계층 조상이 없다.)

```java
interface Movable {
		void move(int x, int y);
}
interface Attackable {
		void attack(Unit unit);
}
interface Fightable extends Movable, Attackable {}
```

클래스의 상속과 마찬가지로 자손 인터페이스(Fightable)는 조상인터페이스(Movable, Attackable)에 정의된 멤버를 모두 상속받는다. 그래서 Fightable 자체에는 정의된 멤버가 하나도 없지만 조상 인터페이스로부터 상속받는 두 개의 추상메서드, move, attack을 멤버로 갖게 된다.

### 인터페이스를 이용한 다중상속

두 조상으로부터 상속받는 멤버 중에서 멤버변수의 이름이 같거나 메서드의 선언부가 일치하고 구현 내용이 다르다면 이 두 조상으로부터 상속받는 자손클래스는 어느 조상의 것을 상속받게 되는 것인지 알 수 없다. 따라서 어느 한 쪽으로부터의 상속을 포기하던가, 이름이 충돌하지 않도록 조상클래스를 변경하는 수밖에 없다.

---

## 인터페이스의 기본 메소드 (Default Method), 자바 8

자바8 이전에는 인터페이스에 추상 메서드만 선언할 수 있었는데, JDK1.8부터는 디폴트 메서드와 static 메서드도 추가할 수 있게 되었다. 

### 디폴트 메서드

상속 관계에서 조상 클래스에 새로운 메서드를 추가하는 것은 별 일이 아니지만, 인터페이스의 경우에는 보통 큰 일이 아니다. 인터페이스에 메서드를 추가한다는 것은  추상 메서드를 추가한다는 것이고, 이 인터페이스를 구현한 기존의 모든 클래스들이 새로 추가된 메서드를 구현해야하기 때문이다.

인터페이스가 변경되지 않으면 제일 좋겠지만 아무리 설계를 잘해도 언젠가 변경은 발생하기 마련이다.  다시 말해, 변경은 불가피하다. 그래서 JDK의 설계자들은 디폴트 메서드라는 것을 고안해 내었다. 티폴트 메서드는 추상 메서드는 기본적인 구현을 제공하는 메서드로, 추상 메서드가 아니기 때문에 디폴트 메서드가 새로 추가되어도 해당 인터페이스를 구현한 클래스를 변경하지 않아도 된다.

```java
interface MyInterface {
		void method();
		void newMethod();
}
```

위와 같이 새로운 추상 메서드를 추가하는 대신 디폴트 메서드를 추가하면, 기존의 MyInterface를 구현한 클래스를 모두 변경하지 않아도 된다. 즉, 상속관계에서 조상클래스에 새로운 메서드를 추가한 것과 동일한 것이다.

```java
interface MyInterface {
		void method();
		default void newMethod();
}
```

단, 새로 추가된 디폴트 메서드가 기존의 메서드와 이름이 중복되어 충돌하는 경우가 발생한다. 이러한 충돌을 해결하는 JVM의 규칙이 미리 정해져있다.

1. 여러 인터페이스의 디폴트 메서드 간 충돌 - 인터페이스를 구현한 클래스에서 디폴트 메서드를 오버라이딩 해야한다.
2. 디폴트 메서드와 조상 클래스의 메서드 간 충돌 - 조상 클래스의 메서드가 상속되고, 디폴트 메서드는 무시된다.

이 규칙을 외울 필요 없이, 필요한 쪽의 메서드와 같은 내용으로 오버라이딩 하면 된다.

---

## 인터페이스의 static 메소드, 자바 8

디폴트 메서드와 마찬가지로 자바8부터 지원하기 시작하였으며, 인터페이스 내에서 static 메서드를 정의할 수 있도록 변경되었다. 그도 그럴것이 static 메서드는 인스턴스와 관계가 없는 독립적인 메서드이기 때문에 예전부터 인터페이스에 추가하지 못할 이유가 없었다.

다른 static 메서드를 호출하는 방식과 마찬가지로 `{interface name}.{method name}` 같은 형식으로 사용이 가능하다.

```java
interface MyInterface {
    void method();
    static void newMethod() {
        System.out.println("This is static method.");
    }
}

public class InterfaceTest {
    public static void main(String[] args) {
        MyInterface.newMethod();
    }
}
```

---

## 인터페이스의 private 메소드, 자바 9

자바9 이전에는 private 메서드도 정의할 수 없었다. 하지만 자바8 부터 default와 static 메서드를 인터페이스 내부에 정의하는 것이 가능해지면서, 인터페이스 내부에서만 호출되는 메서드 또한 생겨났다. 따라서 이러한 내부에서만 호출되는 메서드가 인터페이스 외부에 노출되는 것을  막기 위한 방법이 요구되었고, 자바9 부터 인터페이스 내부에서 private 메서드를 지원하도록 변경되었다.

```java
import java.util.function.IntPredicate;
import java.util.stream.IntStream;
 
public interface CustomCalculator {
    default int addEvenNumbers(int... nums) {
        return add(n -> n % 2 == 0, nums);
    }
 
    default int addOddNumbers(int... nums) {
        return add(n -> n % 2 != 0, nums);
    }
 
    private int add(IntPredicate predicate, int... nums) { 
        return IntStream.of(nums)
                .filter(predicate)
                .sum();
    }
}
```

```java
public class Main implements CustomCalculator {
 
    public static void main(String[] args) {
        CustomCalculator demo = new Main();
         
        int sumOfEvens = demo.addEvenNumbers(1,2,3,4,5,6,7,8,9);
        System.out.println(sumOfEvens);
         
        int sumOfOdds = demo.addOddNumbers(1,2,3,4,5,6,7,8,9);
        System.out.println(sumOfOdds);
    } 
}
```

- https://howtodoinjava.com/java9/java9-private-interface-methods/



---

## 인터페이스의 장점

- 개발시간을 단축시킬 수 있다.
- 표준화가 가능하다.
- 서로 관계없는 클래스들에게 관계를 맺어줄 수 있다.
- 독립적인 프로그래밍이 가능하다.