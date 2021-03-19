## 목표

자바의 상속에 대해 학습하세요.

## 학습할 것 (필수)

- 자바 상속의 특징
- super 키워드
- 메소드 오버라이딩
- 다이나믹 메소드 디스패치 (Dynamic Method Dispatch)
- 추상 클래스
- final 키워드
- Object 클래스

### 자바 상속의 특징

- 모든 클래스는 `Object` 를 상속하고 있다. 이건 클래스파일로 바꿔보면 보인다. `extends Object` 가 숨어있다.

- 다중 상속을 금지하는 이유는 메소드가 겹칠경우 어떤 클래스를 @Override 해야 하는지 오류가 생길 수 있기 때문이다.

- `public and protected` 로 선언하여도 서브클래스에서 사용가능하다.

- 동일 클래스내에 있으면 `package-private` 으로 선언해도 된다.
  - 아무것도 접근제한자를 안적는것이 package-private 방식인데, 같은 패키지 && 해당 클래스 안에서만 사용 가능하다.

### Super 키워드

```java
public class Parent {

    protected int a = 10;

}

```

```java
public class Child extends Parent {

    void print() {
        System.out.println(a);
        System.out.println(this.a);
        System.out.println(super.a);
    }
}
```

```
--result--
10
10
10
```

```java
public class Child extends Parent {

    int a = 15;

    void print() {
        System.out.println(a);
        System.out.println(this.a);
        System.out.println(super.a);
    }
}

```

```
--result--
15
15
10
```

- 위 처럼 우선순위가 바뀌는 것을 알 수 있다~!

### 메소드 오버라이딩(Overriding)

```java
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface Override {
}
```

위와 같이 어노테이션이 구성되어 있는데, `RetentionPolicy.SOURCE` 인것을 보아 컴파일 단계에서 판단하는 것을 알 수 있다.

아마 해당 어노테이션이 붙어있는 메소드 이름을 가져와서 super class 에 해당 메소드가 있는지 판단할 것이다.

### Dynamic method Dispatch

- 저 영어를 그대로 해석해보면 동적으로 런타임에 method 를 dispatch 해준다는 뜻이다.

- 위의 @Override 도 Dispatch 중 한가지이다. 컴파일 시에는 모르나, 런타임시에 이제 @Override 된 메소드를 호출시켜주는 것이다.

- Overloading 도 마찬가지이다. 런타임시에 어떤 메소드를 실행시킬지 결정한다.

### abstract class

- 추상 메소드.. 개인적으로 자바 추상화의 큰 역할을 한 클래스라고 생각함..

- 만약에 우리가 리모컨을 만든다고 해보자.. 그러면 전원 버튼 , 채널 올리기 / 내리기 등등의 공통적인 기능이 있을 것 이다.

그럼 해당 기능을 추상화 시켜 아래와 같은 코드를 짤 수 있을 것이다.

```java
public abstract class Remote {

    private boolean isOn;

    public void turnOn();

    public void turnOff();

    public void channelUp();

    public void channelDown();

}
```

이렇게 추상화를 시켜놓으면 해당 추상 클래스를 상속하여 각 제조사에서 그 기능을 구현할 것이다.

```java
public class LG extends Remote {

  @Override
  public void turnOn() {
    this.isOn = true;
  }

  @Override
  public void turnOff() {
    this.isOff = true;
  }
}

```

또 어떤 회사는 전원을 지문으로 킨다던가.. 뭐 여러가지로 구현할 수 있지만 결국은 추상화되어 있으므로 사용자는 그것이 어떻게 이루어져 있는지 몰라도된다.
마치 우리가 리모컨이 어떻게 만들어지는지는 모르지만 사용하는 것 처럼.

우리가 만약 리모컨을 산다면 아래와 같을 것이다.

```java
public class Customer {

  Remote[] remotePocket;

  public void buyRemote(Remote remote) {
      remotePocket.add(remote);
  }
}
```

특정 구현체를 써놓지 않은 이유는 우리는 리모컨을 살것이지, 어떻게 구현되었는지는 중요하지 않기 때문이다.

코드 짤때 공통적인 부분이 있다면 인터페이스나 추상클래스로 분리가 가능한지를 먼져 고민해 보는게 좋은 습관 같다.

### final 키워드

- 개인적으로 중요하다고 생각되는 키워드 재할당을수 있다는게 상당한 메리트 라고 생각한다.

- 동시성 관련해서도 final 을 써주는것이 안전하므로, 실수 방지를 위해서라도 final 로 선언하는것이 좋다~!

- [읽기좋은글](https://stackoverflow.com/questions/27369584/what-does-the-jvm-do-with-a-final-variable-in-a-class)

### Object 클래스

- 앞에서 설명했지만, Object 는 모든 클래스의 상위 클래스이다.

- equals , hashcode, clone, toString 등등의 여러 메소드들을 보유하고 있다.

- 리플렉션에서 주로 이용하며, 모든 클래스형태로 캐스팅이 가능하다.
