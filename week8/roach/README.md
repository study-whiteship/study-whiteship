## 목표

자바의 인터페이스에 대해 학습하세요.

## 학습할 것 (필수)

- 인터페이스 정의하는 방법
- 인터페이스 구현하는 방법
- 인터페이스 레퍼런스를 통해 구현체를 사용하는 방법
- 인터페이스 상속
- 인터페이스의 기본 메소드 (Default Method), 자바 8
- 인터페이스의 static 메소드, 자바 8
- 인터페이스의 private 메소드, 자바 9

## 인터페이스 정의하는 방법

```java
public interface Move {

    public void moveOnWard();

    public void back();

    public void moveRight();

    public void moveLeft();

}
```

- 위와 같이 move 라는 특정 동작에 대한 행동들을 모아 놓고 있다. 이는 움직임이 있는 클래스에서 해당 인터페이스를 상속하여 구현할 수 있다.
  얘를 들면 아래와 같다.

### 추상 클래스와 인터페이스의 차이점

- 이 부분이 헷갈려서 예전에 공부한 적이 있는데, 다시 리마인드 할겸 정리해보려고 한다.
- 추상 메소드는 일반 메소드와 추상 메소드 둘다 보유 가능하다.
- 인터 페이스 내의 변수들은 `public static final` 로 선언되어 있다.

## 인터페이스를 구현하는 방법

```java
public class Robot implements Move {

    int[] place = {0,0};

    @Override
    public void moveOnWard() {
        place[0] += 1;
    };

    @Override
    public void back() {
        place[0] -= 1;
    };

    @Override
    public void moveRight() {
        place[1] += 1;
    };

    @Override
    public void moveLeft() {
        place[0] -= 1;
    };

}
```

- 이렇게 implements 를 사용하면 해당 인터페이스를 구현해주어야 한다. interface 는 다중 상속이 가능하므로 여러 동작들을 세분화해서 인터페이스로 나누어도 된다.

## 리퍼런스를 통해 구현하는 방법?

- 리퍼런스를 통해 구현하는 방법이 무슨소리인지 잘 이해가 가지않는다. 아마 백기선님 영상을 보아야 할듯한데,
  인터넷의 글들 중 아래와 같은 예시를 보았다.

```java
public Object findLargest(Object object1, Object object2) {
   Relatable obj1 = (Relatable)object1;
   Relatable obj2 = (Relatable)object2;
   if ((obj1).isLargerThan(obj2) > 0)
      return object1;
   else
      return object2;
}
```

이러한 `isLagerThan` 메소드는 Relatable 인터페이스를 구현한 객체라면 어떠한 객체든지 사용할 수 있다.
뭐 간단히 해당 객체를 구현하고 있으면 레퍼런스도 사용하다는 소리를 하고 있는 것 같다..

## 인터페이스 상속

인터페이스는 상속이 가능하다. 주로 자바 클래스들을 자주 열어본 편이라면 많이 본 구조 일 것이다.

해당 상속받은 인터페이스들을 구현체에서 모두 구현해야 한다.

## 인터페이스의 default 메소드

- 이건 사실 개발자들의 요청에 의해 추가된것 같은데.. 사실 추상화가 조금 깨진다고 생각하긴 하는데, 편하게 개발하는것이 옳다고 생각하기에 좋은 기능이라고 생각한다.

```java
public interface Foo {

    void move();

    default boolean something() {
        printf("SomeThing!!");
    }

}
```

- Foo 라는 인터페이스를 상속받은 클래스에서 공통적으로 something 이라는 메소드가 `printf("SomeThing!!");` 으로 구현되는데, 그걸 구현 받은 클래스에서 공통적인데도 불구하고 구현하는 것은 큰 중복이다.

그래서 default 메소드라는 개념이 도입되서 해당 메소드는 기본적으로 저렇게 동작하도록 설정할 수 있다.

## 인터페이스 static Method

- 인터페이스에 static Method 가 사용가능하다고는 하는데, 어디서 사용되는지.. 옳은 건지 잘 모르겠다.. 일단 쓸 수 있다고 한다. (static 이기에 default 메소드 처럼 동작을 미리 구현해주어야 한다.)

## 인터페이스의 private Method

- 자바 9 부터 가능하게 된 메소드인데, 코드 재사용성을 높이기 위해 도입됬다고 생가갛면 좋다.

- 사실 default 메소드로 인해 생기게 됬는데, 만약 default 메소드에서 중복이 발생하고 있다면 해당 private Method 로 중복 코드를 압축시킬 수 있을 것이다.
