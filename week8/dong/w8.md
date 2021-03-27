# 8주차 - 자바의 인터페이스에 대해 학습하세요
- 목차
```
인터페이스 정의하는 방법
인터페이스 구현하는 방법
인터페이스 레퍼런스를 통해 구현체를 사용하는 방법
인터페이스 상속
인터페이스의 기본 메소드 (Default Method), 자바 8
인터페이스의 static 메소드, 자바 8
인터페이스의 private 메소드, 자바 9
```
- 참고링크
  - https://velog.io/@codemcd/%EC%9D%B8%ED%84%B0%ED%8E%98%EC%9D%B4%EC%8A%A4Interface


# 인터페이스의 정의

## 인터페이스란?

- 인터페이스란 추상클래스의 한 종류이다
- 인터페이스는 추상클래스처럼 추상메서드를 갖는다
- but! 추상클래스보다 추상화의 정도가 높다
    - **몸통을 갖춘 일반 메서드 또는 멤버변수를 구성원으로 가질 수 없다**
- (JDK 1.8 이전에서는) 오직 추상메서드와 상수만을 멤버로 가질 수 있고, 그외의 다른 어떠한 요소도 맴버로 허용하지 않는다.
- 인터 + 페이스 : 만나는 부분, 접하는 부분이라는 뜻인데, 다른 부분과 결합하는 경계부분을 인터페이스라고 한다
- 자바에서의 인터페이스 
  - 여러 구현체에서 공통적인 부분을 추상화한다. 이를 다형성이라고도 한다
  - 인터페이스를 사용하는 이유는 다형성이다!
    - 다형성(Polymorphism) : 여러 가지 형태를 가질 수 있는 능력, 예를들면 아래와 같은데
    ``` java
    HashMap<String,String> map1
    HashMap<String,Integer> map2
    HashMap<Integer,String> map3
    HashMap<Integer,Integer> map3
    ```
    - 다형성은 상속받은 클래스/인터페이스의 메서드를 재정의하여 서로 다른 행동을 만드는게 가능
    - 상속을 통해 상위 클래스의 타입으로 통일한 후 하위 클래스들을 하나의 타입으로 관리하고, 유연한 확장가능한 코드를 만들 수 있다.
# 인터페이스와 상속의 차이
- 표로 정리
- 
||상속|인터페이스|
|:--:|:--:|:--:|
|대상|클래스|인터페이스|
|할일|상속받기|구현하기|
|판단기준|상속보다 위임||
||||
- 다형성을 위해서 만든다면 클래스 상속보다는 인터페이스를 구현하라




## 인터페이스 작성법(스니핏)

```java
interface 인터페이스이름 {
	static final 타입 상수이름 = 값;
	public abstract 메서드이름(매개변수목록);
}
```

- 인터페이스 작성은 클래스 작성과 거의 유사하다
- 유사점
    - 이외에 접근제어자등은 동일하게 적용됨
    - 인터페이스도 추상클래스처럼 그 자체로는 인스턴스를 생성할 수 없음
    - 
- 차이점
    - 키워드로 class 대신 interface 를 사용
    - (JDK 1.8이후)모든 메서드는 public abstract 이어야 하며 생략가능
    - 단, static 메서드와 default 메서드는 예외

---

---

# 인터페이스의 구현

- 추상클래스가 상속을 통해 추상메서드를 완성하는 것처럼, 인터페이스는 그 자체로 인스턴스를 생성할수 없다.
- 인터페이스도 자신에 정의된 추상메서드의 몸통을 만들어주는 클래스를 작성해야 하며 그 방법은 추상클래스와 같다.

### 1) 키워드 ‘extends’가 아닌 구현한다는 의미의 ‘implements’를 사용한다

```java
class 클래스이름 implements 인터페이스이름 {
	// 인터페이스에 정의된 추상메서드를 구현
}
```

### 2) 구현하는 인터페이스의 메서드 중 일부만 구현하는 경우

- abstract을 붙여서 추상클래스로 선언해야 함

```java
abstract class Soldier implements Fightable {
	public void move(int x, int y) { ... }
}
```

### 3) 상속과 구현을 동시에 하는 경우

```java
class Soldier extends Unit implements Fightable {
    public void move(int x, int y) { ... }
    public void attack(Unit u) { ... }
}
```

---

---

# 레퍼런스를 통한 구현체의 사용

- 위에서 학습한 다형성에 의해 자손클래스의 인스턴스를 조상타입의 참조변수로 참조하는 것이 가능한것을 기억!
- 인터페이스 역시 해당 인터페이스의 타입의 참조변수로 이를 구현한 클래스의 인스턴스를 참조가능
- 인터페이스 타입으로의 형변환도 가능하다.인터페이스 타입의 매개변수가 가지는 의미는 메서드 호출 시 해당 인터페이스를 구현한 클래스의 인스턴스를 매개변수로 제공 가능

```java
public class Main {
    public static void main(String[] args) {
        App ap = new App();
        ap.main(args);
    }
}

interface Gogi {
    void GogiName(String type);
}

class gogiCheck {
    public static Gogi getGogi(String typeOfGogi) {
        if(typeOfGogi.equals("채끝살")) {
            return new soo();
        } else if(typeOfGogi.equals("삼겹살")) {
            return new dag();
        } else if(typeOfGogi.equals("닭가슴살")) {
            return new dak();
        }
        else {
            Gogi ret = new vegi();
            return ret;
        }
    }
}

class soo implements Gogi {
    public void GogiName(String partofMeat) {
        System.out.println(partofMeat + "소고기가 짱이지");
    }
}

class dag implements Gogi {
    public void GogiName(String partofMeat) {
        System.out.println(partofMeat + "고기의 왕 돼지고기");
    }
}

class dak implements Gogi {
    public void GogiName(String partofMeat) {
        System.out.println(partofMeat + "닭고기의 깊은맛은 남바완");
    }
}

class vegi implements Gogi {
    public void GogiName(String partofMeat) {
        System.out.println(partofMeat + "이건뭐지..? 콩고기인가?");
    }
}

class App {
    public static void main(String[] args) {
        Gogi typeofgogi = gogiCheck.getGogi("채끝살");
        typeofgogi.GogiName("동준이의 픽! ");

        typeofgogi = gogiCheck.getGogi("삼겹살");
        typeofgogi.GogiName("수현이의 픽! ");

        typeofgogi = gogiCheck.getGogi("닭가슴살");
        typeofgogi.GogiName("운동할땐 역시");

        typeofgogi = gogiCheck.getGogi("정체불명");
        typeofgogi.GogiName("정체불명? ");

    }
}
```


- 위의 예제중 13~25라인은 잘 살펴보면,

```java
class gogiCheck {
    public static Gogi getGogi(String typeOfGogi) {
        if(typeOfGogi.equals("채끝살")) {
            return new soo();
        } else if(typeOfGogi.equals("삼겹살")) {
            return new dag();
        } else if(typeOfGogi.equals("닭가슴살")) {
            return new dak();
        }
        else {
            Gogi ret = new vegi();
            return ret;
        }
    }
}
```

- Method의 return type으로 인터페이스의 객체 type을 지정하는 부분인데,
- return type이 인터페이스라는건 :  메서드가 해당 인터페이스를 구현한 클래스의 인스턴스를 반환한다라는 의미

---

---

# 인터페이스의 상속

- 인터페이스는 인터페이스로부터만 상속을 받을 수 있으며, 다중상속이 가능하다!
- 클래스의 상속과 마찬가지로 자손 인터페이스는 조상 인터페이스에 정의된 멤버를 모두 상속받는다
- 인터페이스는 클래스와 달리 Object클래스와 같은 최고 조상이 없다.

```java
interface Movable {
    void move(int x, int y);
}

interface Attackable {
    void attack(Unit u);
}

interface Fightable extends Movable, Attackable { }
```

---

# 기본 메서드 (Default Method), 자바 8

### Default Method in java8

- 인터페이스에 메서드를 추가한다는 것은, 추상 메서드를 추가한다는 것이고, 인터페이스를 구현한 기존의 모든 클래스들이 새로 추가된 메서드를 구현해야 한다.
- 인터페이스가 변경되지 않으면 제일 좋지만, 언젠가는 변경이 발생하기 마련이다. 이를 해결하기 위해서 **디폴트 메서드(default method)**라는 것이 고안되었다.
- 디폴트 메서드는 추상 메서드의 기본적인 구현을 제공하는 메서드로, **추상 메서드가 아니기 때문에** 디폴트 메서드가 새롭게 추가되어도 해당 인터페이스를 구현한 클래스를 변경하지 않아도 된다.
- 디폴트 메서드는 앞에 키워드 "default" 를 붙이며, 추상 메서드와 달리 일반 메서드처럼 몸통 { }이 있어야 한다. 접근 제어자는 public이며, 생략이 가능하다.

```java
interface MyInterface {
    void method();
    // void newMethod();  추상 메서드
    default void newMethod() { }
}
```

- 추상 메서드를 추가하는 대신 디폴트 메서드를 추가하면 조상 클래스에 새로운 메서드를 추가한 것과 동일한 효과를 얻는다.

### 디폴트 메서드 충돌 규칙

- 새로 추가된 디폴트 메서드가 기존의 메서드와 이름이 중복되어 충돌하는 경우 해결하는 규칙은 다음과 같다.
    - 여러 인터페이스의 디폴트 메서드 간의 충돌
        - 인터페이스를 구현한 클래스에서 디폴트 메서드를 오버라이딩해야 한다.
    - 디폴트 메서드와 조상 클래스의 메서드 간의 충돌
        - 조상 클래스의 메서드가 상속되고, 디폴트 메서드는 무시된다.
- 단순하게 필요한 쪽의 메서드와 같은 내용으로 오버라이딩 해서 해결하는 방법도 있다.

---

---

# static 메서드, 자바 8

- Java 8부터 인터페이스에 static 메서드 추가가 가능해졌다.
- 클래스에서 작성하는 방법과 동일하게 작성할 수 있고, 접근 제어자는 항상 public이며 역시 생략이 가능하다.
- static 메서드는 오버라이딩이 불가능하다.

---

---

# private 메서드, 자바 9

- Java 9부터 사용할 수 있게된 private 메서드는 다음과 같은 특성을 가지고 있다.
    - 메서드의 몸통 { }이 있고 abstract이 아니다.
    - 구현체에서 구현할 수 없고 자식 인터페이스에서 상속이 불가능하다.
    - static 메서드도 private이 가능하다.
- private 메서드는 private, abstract, default 또는 static 메서드를 호출할 수 있다. private static은 static 및 static private 메서드만 호출 할 수 있다.

---


# 지금까지 공부한거 리마인드를 하면

## 클래스의 기본 구성으로
- 객체, 클래스, 인스턴스 차이를 설명 
  - 객체란? : 인간이 인지하는 최소의 개념 단위..??
  - 클래스란? : 객체의 설계도, 하나의 탬플릿이며 그 속에 필드(데이터 저장소)와 매소드(연산)이 있다
  - 인스턴스란? : 클래스를 실제로 메모리에 만들어냄, 클래스라는 설계도대로 만들어낸 제품
- 형식
  - 접근제한자
  - 지정예약어 (static, final, stfi, transient, abstract, synchronized)
  - Class이름
  - extends 클래스
  - implements 인터페이스
  - 멤버필드
  - 멤버메소드
  - this매소드와 this()
  - 생성자
  - 중첩클래스
  - 클래스 밖의것들
    - package, 
    - import, 
    - class,
    - 참조변수(생성자, 인스턴스화)
    - 클래스배열 : 클래스를 배열로 만들 때는 먼저 배열을 만들어 준 후,
각각의 배열마다 객체를 선언해 줘야 함
- 기본구성
  - Filed : 데이터 저장공간, 메소드 내부 선언 지역변수
  - Method : 특정 행위의 서술, 행동양식을 정의(맴버 메소드)
  - Construct : 객체 생성 , 초기화를 시켜주는 특별한 메소드, 생성자
  - Nested Class : 중첩 클래스 : 하나의 클래스 내부에 또 다른 클래스를 사용 하는 것
    - 일반/정적/지역/무명


---

---

# 끝
