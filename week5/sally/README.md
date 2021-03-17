# [Week5] 클래스

# 학습할 것 (필수)

- 클래스 정의하는 방법
- 객체 만드는 방법 (new 키워드 이해하기)
- 메소드 정의하는 방법
- 생성자 정의하는 방법
- this 키워드 이해하기

## 클래스 정의하는 방법

클래스를 정의하는 방법은 다음과 같다.

```java
public class Bicycle {
        
    // 해당 클래스는 세 개의 필드를 가진다.
    public int cadence;
    public int gear;
    public int speed;
        
    // 해당 클래스는 하나의 생성자를 가진다.
    public Bicycle(int startCadence, int startSpeed, int startGear) {
        gear = startGear;
        cadence = startCadence;
        speed = startSpeed;
    }
        
    // 해당 클래스는 네 개의 메서드를 가진다. 
    public void setCadence(int newValue) {
        cadence = newValue;
    }
        
    public void setGear(int newValue) {
        gear = newValue;
    }
        
    public void applyBrake(int decrement) {
        speed -= decrement;
    }
        
    public void speedUp(int increment) {
        speed += increment;
    }
        
}
```

```java
public class MountainBike extends Bicycle { // Bicycle의 subclass인 MountainBike을 선언
        
    public int seatHeight;

    public MountainBike(int startHeight, int startCadence,
                        int startSpeed, int startGear) {
        super(startCadence, startSpeed, startGear);
        seatHeight = startHeight;
    }   
        
    public void setHeight(int newValue) {
        seatHeight = newValue;
    }   
  	// 해당 클래스는 Bicycle을 상속받으므로 Bicycle이 가지는 필드와 메서드를 모두 가진다.
}
```

클래스를 선언할 때 컬리브라켓 안의 부분을 클래스 바디라고 부르고 이 안에서 객체를 새로 만들 때 쓰는 생성자, 클래스와 객체의 상태를 나타낼 수 있는 필드의 선언, 그리고 클래스와 객체의 행동을 구현하는 메서드를 선언할 수 있다. 선언부에는 어떤 클래스들이 해당 클래스를 접근할 수 있는 지 결정하는 접근제어자와 어떤 인터페이스를 상속받을 지도 적어줄 수 있다.

클래스를 선언할 때에는

1. 접근제어자를 먼저 정해준다.
2. 컨벤션에 따라 첫 글자를 대문자로 한 클래스명을 적는다.
3. 부모클래스가 있다면 `extends` 다음으로 부모클래스를 써준다.
4. `implements` 에 이어 받고싶은 인터페이스를 써준다.
5. 컬리브라켓을 이용해 바디를 구현한다.

## 객체 만드는 방법 (new 키워드 이해하기)

자바 프로그램은 여러 객체를 생성해 객체끼리의 정보를 주고받으며 많은 일을 수행한다. 

```java
Point originOne = new Point(23, 94);
Rectangle rectOne = new Rectangle(originOne, 100, 200);
Rectangle rectTwo = new Rectangle(50, 100);
```

객체를 만드는 방법은 위와 같고 위의 세 구문은 1) 선언부, 2) 인스턴스화, 3) 초기화 세 부분으로 나누어져 있다.

1. 선언부는 위 코드에서 `=`이전의 코드로 변수의 타입과 이름을 정해준다. `type name`

   레퍼런스 타입의 경우 이렇게 선언하는 것만으로 객체가 만들어지는 것이 아니다. 단순히 타입과 이름만을 지정해주고 초기화시키지 않은 상태라면 컴파일 에러가 발생한다.

2. `new` 연산자를 이용해 새로운 객체를 생성하는 것을 인스턴스화한다고 한다. `new` 연산자는 객체의 메모리를 할당하고 그 메모리에 대한 참조를 반환해서 1의 선언부에 선언된 변수에 저장된다. `new` 연산자 이후에는 인스턴스화 시키고싶은 클래스의 생성자가 온다. 생성자에 따라 매개변수를 받기도 한다.

3. 초기화는 생성자에서 이루어진다. 모든 클래스에는 적어도 하나 이상의 생성자가 있어야하므로 생성자를 따로 선언하지 않았더라도 컴파일러가 아무 인자가 없는 생성자를 기본 생성자로 추가한다. 

## 메소드 정의하는 방법

메서드는 보통 여섯개의 요소로 정의한다.

1. 접근제어자
2. 리턴타입: 메서드가 반환할 값의 타입을 정의해준다. 반환하는 값이 아무것도 없으면 `void`로 둔다.
3. 메서드명: 컨벤션에 따라 첫 글자는 소문자로 해주고 보통 동사형으로 써주며 여러 단어를 합친 경우 단어의 첫 글자는 대문자로 한다. (`runFast`, `getFinalData` 등)
4. 괄호안의 매개변수: 매개변수끼리는 쉼표로 구분하며 데이터 타입과 메서드 안에서 써줄 변수이름을 써준다.
5. 메서드가 던질 수도 있는 예외. `throws` 뒤에 써준다.
6. 메서드의 바디부분. 메서드의 코드, 로컬변수의 선언 등 컬리브라켓 안에서 모두 구현한다.

## 생성자 정의하는 방법

생성자의 선언은 해당 클래스명을 쓰는 것과 리턴타입이 없는 것만 제외하면 메서드의 선언과 거의 비슷하다.

```java
public Bicycle(int startCadence, int startSpeed, int startGear) {
    gear = startGear;
    cadence = startCadence;
    speed = startSpeed;
}
```

위와 같이 Bicycle의 생성자를 선언하면 Bicycle의 객체인 myBike를 생성할 때에는 다음과 같이 한다.

```java
Bicycle myBike = new Bicycle(30, 0, 8)
```

`new Bicycle(30, 0, 8)` 부분이 객체가 필요한 메모리 공간을 만들고 필드를 초기화해준다.

생성자를 위와 같이 따로 정의해주지 않아도 컴파일되는 과정에서 컴파일러가 자동으로 매개변수가 없는 기본 생성자를 생성해주고 이는 수퍼클래스의 인수없는 생성자를 호출하지만 이것또한 없는 경우 오류가 발생한다. 명시적으로 수퍼클래스가 없는 경우 모든 클래스의 부모클래스인 Object가 수퍼클래스로 지정된다.

## this 키워드 이해하기

인스턴스의 메서드나 생성자에서 `this`는 해당 객체를 의미한다('나!' 이런 느낌..).

`this` 키워드가 쓰이는 제일 큰 이유는 메서드나 생성자의 매개변수에 의해 구분하기가 어려워질 수 있어서이다.

```java
public class Point {
    public int x = 0;
    public int y = 0;
        
    // 생성자
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
```

위와 같은 생성자는 매개변수와 객체의 필드명이 같기 때문에 둘을 구분하기가 어려워지므로 객체의 필드명을 명시할 때에는 앞에 `this` 키워드를 붙임으로 필드명과 매개변수를 구분해준다.

또한 생성자 내부에서도 `this` 키워드를 사용해 다른 생성자를 불러와서 사용할 수 있다.

```java
public class Rectangle {
    private int x, y;
    private int width, height;
        
    public Rectangle() {
        this(0, 0, 1, 1);
    }
    public Rectangle(int width, int height) {
        this(0, 0, width, height);
    }
    public Rectangle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    ...
}
```

위 예제의 클래스는 여러개의 생성자를 가지고 각 생성자는 각각 멤버변수를 다르게 선언하고 있다. 