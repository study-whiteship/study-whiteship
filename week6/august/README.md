# [Week6] 상속
## 자바 상속의 특징

상속이란, 기존의 클래스를 재사용하여 새로운 클래스를 작성하는 것이다. 자바에서 상속을 구현하는 방법은 `extends` 키워드로 가능하다.

```java
class Child extends Parent {
		// ...
}
```

이때 이 두 클래스는 서로 상속 관계에 있다고 하며, 상속해주는 클래스를 '상위 클래스'라 하고, 상속 받는 클래스를 '하위 클래스'라 한다.

## super 키워드

상속 관계에서는 하위 클래스의 생성자는 첫 라인에 super 메서드나 this 메서드를 기재하지 않았을 때 자동적으로 super가 있다고 JVM이 인식하므로 생략할 수 있다.

## 메소드 오버라이딩

상위 클래스로부터 상속받은 메서드의 내용을 하위 클래스에서 변경하는 것을 오버라이딩이라고 한다. 상속받은 메서드를 그대로 사용하기도 하지만, 하위 클래스의 역할에 맞게 변경해야 하는 경우가 많다. 이때 메서드 오버라이딩이 사용된다.

### 오버라이딩 조건

하위 클래스에서 오버라이딩하는 메서드는 상위 클래스의 메서드와 메서드 헤더가 같아야 한다.

- 이름이 같아야 하고
- 매개변수가 같아야 하며
- 리턴타입이 같아야 한다.

```java
class Piece {		
		String getType() {
				return "none";
		}
}
```

```java
class Pawn extends Piece {
		@Override
		String getType() {
				return "pawn";				
		}
}
```

자바 일정 버전 이상에서는 `@Override` 애노테이션을 통해서 컴파일러가 오버라이딩된 메서드가 맞는지 체크해준다. 

## 추상 클래스

클래스가 설계도라면, 추상클래스는 미완성 설계도이다. 즉, 바로 인스턴스화 해서 사용할 수 없는 클래스이다. 마치 육체가 완성되지 않아서 영혼이 깃들지 못하는 것과 같다.

추상클래스 자체로는 클래스로서의 역할을 다 못하지만, 새로운 클래스를 작성하는데 있어서 기본 틀을 형성해준다.

자료구조에서 학습하는 이론적 모델인 ADT(Abstract Data Type)을 자바에서 적용시킨 일종의 프로그래밍 기술이 추상클래스이다.

https://stackoverflow.com/questions/48839522/is-abstract-class-an-example-of-abstract-data-type

```java
abstract class Chess {
		...
}
```

위와같이 추상클래스를 정의할 수 있다.

## final 키워드

`final` 은 '마지막의' 또는 '변경될 수 없는' 의 의미를 가지고 있다. 이 키워드는 자바의 거의 모든 곳(클래스, 메서드, 멤버변수, 지역변수)에 적용 가능하다.

| 대상     | 의미                                          |
| -------- | --------------------------------------------- |
| 클래스   | 변경 및 확장될 수 없는 클래스. 상속을 막는다. |
| 메서드   | 변경될 수 없는 메서드. 오버라이딩을 막는다.   |
| 멤버변수 | 값을 변경할 수 없다. (상수화)                 |
| 지역변수 | 값을 변경할 수 없다. (상수화)                 |

## Object 클래스

자바에서는 모든 클래스가 Object클래스를 상속하도록 되어있다. 즉, Object 클래스는 모든 클래스 상속계층의 최상위에 있는 클래스이다. 예를 들어 다음과 같은 클래스가 정의되어 있다면,

```java
class Television {
		...
}
```

컴파일 중에 컴파일러는 자동으로 `extends Object` 를 추가하여 Object를 상속받도록 한다.

```java
class Television extends Object {
		...
}
```

실제로, 자바 공식 문서를 보면 클래스의 계층도에서 최상위에 Object클래스가 있는 것을 확인할 수 있다.

```java
Class Hierarchy
- java.lang.Object
		javax.swing.AbstractAction (implements javax.swing.Action, java.lang.Cloneable, java.io.Serializable)
	javax.swing.plaf.basic.BasicDesktopPaneUI.CloseAction
	javax.swing.plaf.basic.BasicDesktopPaneUI.MaximizeAction
	javax.swing.plaf.basic.BasicDesktopPaneUI.MinimizeAction
	javax.swing.plaf.basic.BasicDesktopPaneUI.NavigateAction
...
```

https://docs.oracle.com/javase/8/docs/api/

## 다이나믹 메소드 디스패치 (Dynamic Method Dispatch)

다이나믹은 런타임을 의미하며, 디스패치는 어떤 메서드를 호출할지 결정한다는 뜻이다. 즉, 자바 프로그램이 실행중에 오버라이딩된 메서드 중에서 선택이 가능하도록 해주는 기법이다. 

```java
// A Java program to illustrate Dynamic Method
// Dispatch using hierarchical inheritance
class Parent {
    void say() {
        System.out.println("Parent say...");
    }
}

class FirstChild extends Parent {
    @Override
    void say() {
        System.out.println("First Child says...");
    }
}

class SecondChild extends Parent {
    @Override
    void say() {
        System.out.println("Second Child says...");
    }
}

// Driver class
class DynamicMethodDispatch {
    public static void main(String[] args) {
        Parent parent = new Parent();
        FirstChild firstChild = new FirstChild();
        SecondChild secondChild = new SecondChild();

        Parent ref;         // obtain a reference of type Parent
        ref = parent;       // ref refers to an Parent object
        ref.say();          // calling Parent's version of say()
        ref = firstChild;   // now ref refers to a FirstChild object
        ref.say();          // calling FirstChild's version of say()
        ref = secondChild;  // now ref refers to a SecondChild object
        ref.say();          // calling SecondChild's version of say()

        polymorphism(parent);
        polymorphism(firstChild);
        polymorphism(secondChild);
    }

    public static void polymorphism(Parent parent) {
        Parent ref;
        ref = parent;
        ref.say();
    }

}
```

