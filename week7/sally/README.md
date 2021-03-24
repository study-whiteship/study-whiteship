# 학습할 것 (필수)

- package 키워드
- import 키워드
- 클래스패스
- CLASSPATH 환경변수
- -classpath 옵션
- 접근지시자

## Package 키워드

타입(클래스, 인터페이스, 이넘, 어노테이션)을 쉽게 찾고 쓰기위해, 파일명때문에 발생하는 충돌을 막기위해, 또 접근을 제한하기 위해 사용하는 것이 패키지이다. 한 패키지 안에 비슷한 속성을 띄는 타입들을 그룹지어 넣는다. 쉽게 말해 폴더시스템을 생각하면 된다. 하위패키지는 도트로 구분한다.

패키지는 크게 자바 자체에 내장되어있는 빌트인 패키지와 사용자가 직접 만들어서 쓰는 user-defined 패키지로 나눌 수 있다. 빌트인 패키지는 `java`로 시작하고 커스텀 패키지는 `java`로 시작하는 패키지를 만들어서는 안된다. 가장 기본적인, 자주 쓰이는 `java.lang`의 경우는 따로 불러오지(import) 않아도 자바가 알아서 불러와준다. 

모든 클래스에는 정의된 클래스이름과 패키지 이름이 있고 이 모두를 합쳐줘야 한 클래스르 표현할 수 있다. 이를 FQCN, Fully Qualified Class Name이라고 한다(`java.lang.String`).

`package`키워드는 클래스를 어떤 패키지를 지정할 때 사용한다. 예를 들어 Rabbit이라는 클래스를 만들고 그 클래스를 animal이라는 패키지 내에 지정하고싶으면 아래와 같이 선언하면 된다.

```java
package com.whiteShip.animal;

public class Rabbit {
  // 클래스 구현
}
```

FQCN으로 표현하면 `com.whiteship.animal.Rabbit`이 되겠다.

## import 키워드

`import 패키지명.클래스명(FQCN에 따른 클래스표현)`은 다른 패키지 안의 클래스를 불러와서 사용하고 싶을 때, 패키지선언 다음으로 써주는 형식으로 한다. 

```java 
package com.whiteShip.app; // 해당 클래스의 패키지

import com.whiteShip.animal.Rabbit; // 다른 패키지 안의 클래스 불러오기

public class Zoo {
  Rabbit rabbit = new Rabbit();
}
```

빌트인이던 커스텀이던 위에서 언급한 `java.lang`을 제외하고 같은 패키지 안에 있지 않는 이상 이 임포트문을 통해 불러와야 사용할 수 있다. IDE를 이용하면 메서드명이나 클래스타입을 지정하면 알아서 불러와주기도 한다. 

한 패키지 안에 선언된 모든 타입을 불러올 때에는 `import 패키지명.*`로 불러올 수 있고 static 멤버 또한 이렇게 임포트가 가능하다.

## 클래스패스

클래스패스란 JVM의 클래스로더가 자바 컴파일러가 컴파일된 자바파일, 즉 .class파일을 찾는 기준이 되는 경로를 말한다. 쉽게 말해서 클래스파일을 `java` 명령어로 실행시킬 때 클래스로더가 클래스패스에 지정된 경로로 들어가 해당 클래스파일을 찾는다. 여기서 지정된 경로를 클래스패스라고 한다.

간단히 예를 들어 

1. sally라는 디렉토리를 클래스패스로 지정해두고 
2. java라는 디렉토리에서 hello.java를 컴파일, java 디렉토리 안에는 hello.java, hello.class 두 파일 존재
3. java 디렉토리에서 `java hello.class` 명령어를 실행해도 실행 안 됨 ⇒ JVM이 클래스패스인 sally 디렉토리에서 클래스파일을 찾기 때문!!! 엉뚱한데 가서 찾고있음 ㅠ

클래스패스를 지정할 때에는 환경변수를 이용하는 방법과 -classpath 옵션을 이용하는 방법 두가지가 있다.
### 클래스패스 환경변수

환경변수는 운영체제 자체에 지정해놓아 CLASSPATH 환경변수값을 지정해놓으면 지정해놓은 곳으로 들어가 클래스 파일을 찾는다.

다음은 클래스패스 환경변수를 설정해주는 명령어이다.

```
CLASSPATH=/home/george/java/classes; export CLASSPATH
```

외에도 IDE에서 직접 설정해주는 방법도 있다.

인텔리제이의 경우 맥 기준

`file - project structure` 창의 우측부분에서 source folder를 지정할 수 있다.

### -classpath 옵션

`java`, `javac` 등의 명령어에 `-classpath`, 또는 `-cp` 옵션을 이용해 디렉토리를 지정해 줄 수 있다.

이 옵션을 이용해 입력한 경로는 클래스패스 환경변수를 대체하게 된다. 자바 런타임이 C:\java\MyClasses\utility\myapp 경로에 위치한 utility.myapp 패키지 안에 있는 Cool.class를 찾게하고 싶을 때 이렇게 쓴다.

```
java -classpath C:\java\MyClasses utility.myapp.Cool
```

## 접근지시자

접근 제어자는 다른 클래스로 하여금 해당 클래스/필드가 어디까지 접근이 가능한지를 알려준다.

- public: 어느곳에서나 사용 가능
- protected: 상속받은 클래스에 사용 가능
- default: 같은 패키지 안에서만 사용할 수 있다.  패키지 프라이빗이라고도 한다.
- private: 클래스 내부에서만 사용 가능 [Week5] 클래스

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
