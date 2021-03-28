# **학습할 것 (필수)**

- 인터페이스 정의하는 방법
- 인터페이스 구현하는 방법
- 인터페이스 레퍼런스를 통해 구현체를 사용하는 방법
- 인터페이스 상속
- 인터페이스의 기본 메소드 (Default Method), 자바 8
- 인터페이스의 static 메소드, 자바 8
- 인터페이스의 private 메소드, 자바 9

## **인터페이스 정의하는 방법**

인터페이스는 추상클래스처럼 추상메서드를 가지지만 추상화 정도가 높아 일반 메서드 또는 멤버변수를 가질 수 없다(추상클래스를 '미완성 설계도'에 비유한다면 인터페이스는 밑바탕만 그려져있는 '기본 설계도'). 인터페이스에서는 오직 추상메서드(public abstrct)와 상수(public static final)만을 멤버로 가질 수 있다.

추상클래스와 마찬가지로 인터페이스는 그 자체로 사용되기보다는 다른 클래스를 작성하는데 도움이 된다.

인터페이스를 정의할 때에는 인터페이스의 접근제어자, `interface`키워드, 인터페이스 이름, (콤마와 함께 부모 인터페이스가 있다면 써준다) 인터페이스 바디로 이루어져 있으며 다음과 같다.

```
interface 인터페이스이름{
  public static final 타입 상수이름 = 값;
  public abstract 메서드이름(매개변수목록);
}
```

위에서 언급한 것과 같이 인터페이스는 `public abstract`를 가지는 추상메서드와 `public static fianl`만을 허용하므로 제어자를 생략해도 된다. 생략된 제어자는 컴파일러가 자동으로 추가해준다.

## **인터페이스 구현하는 방법**

인터페이스를 구현할 때에는 `implements` 키워드를 통해 인터페이스를 가져오고 해당 인터페이스에 속한 추상메서드를 반드시 구현해야 한다.

```
interface Animal {
  public static final int HEAD_COUNT = 1;

  int getLegs();
}

class Cat implements Animal {
  public int getLegs() { // Animal 인터페이스의 추상메서드 구현
    return 4;
  }
}
```

## **인터페이스 레퍼런스를 통해 구현체를 사용하는 방법**

## **인터페이스 상속**

인터페이스는 인터페이스끼리만 상속받을 수 있으며 다중상속이 가능하다!

여러개의 인터페이스를 상속받고싶으면 `implements`키워드 뒤에 상속받을 인터페이스들을 컴마로 구분지어준다.

```
interface Movable { void move(int x, int y); }

interface Attackable { void attack(Unit u); }

interface Fightable extends Movable, Attackable { }
```

## **인터페이스의 기본 메소드(Default Method)**

인터페이스에 새로운 메서드를 추가해야하는 상황이 생겼다고 가정할 때, 그 인터페이스를 구현하던 클래스는 새롭게 추가된 메서드를 구현해야하고 이 과정에서 오류가 날 확률이 크다. 번거로운 일이라 방안책으로 기본 메서드라는 기능이 자바 8에서부터 생겼다.

기본 메서드는 `default` 키워드를 가지며 추상메서드가 아니고 그래서 메서드 정의할 때 몸통 구현이 필수고 그래서 인터페이스를 구현할 때 이 `default method` 는 따로 변경해주지 않아도 된다. 그냥 있는대로 써도 되고 오버라이드해도 되고..

```
interface Example {
  void exampleMethod(); // 인터페이스 내의 보통의 추상메서드

  default void exampleMethod2() {
    System.out.println("나는 기본");
  }
}
```

새로 추가된 기본 메서드와 이름이 같은 기존의 메서드와 충돌하는 경우가 발생할 수 있다.

1. 여러 인터페이스의 기본 메서드 간의 충돌: 인터페이스를 구현한 클래스에서 기본 메서드를 오버라이드 해야한다.
2. 기본 메서드와 수퍼 클래스의 메서드 간의 충돌: 수퍼 클래스의 메서드가 상속되고 인터페이스의 기본메서드는 무시된다.

## **인터페이스의 static 메소드**

자바 8에서부터 인터페이스에 static 메서드 추가가 가능해졌다. 작성 방법은 기존의 static 메서드의 작성방법과 동일하며 인터페이스이므로 `public` 접근제어자는 생략이 가능하다.

`static` 특성 상 변경이 불가능하므로 인터페이스 내에서 구현이 되어야하고 상속받는 클래스는 이를 오버라이드할 수 없다.

사용할 때에는 `인터페이스이름.메서드명` 의 형식으로 쓰인다.

```
interface Sally {
  static void method1() {
    System.out.println("메서드예요");
  }
}
```

## **인터페이스의 private 메소드**

자바 9에서부터 지원하기 시작한 인터페이스의 `private` 메서드와 `private static` 메서드는 인터페이스 내의 default 메서드에서 중복코드를 줄여준다. 디폴트 메서드와 마찬가지로 `private` method(as in interface) must be non-abstract and it is only used inside the interface.
