# 학습할 것 (필수)

- 애노테이션 정의하는 방법
- [@retention](https://github.com/retention)
- [@target](https://github.com/target)
- [@documented](https://github.com/documented)
- 애노테이션 프로세서

---

### 어노테이션

어노테이션은 코드를 작성할 수 있는 주석. 따라서 특수한 의미를 부여하거나 기능을 주입할 수 있다.

### 어노테이션 정의하는 방법

```java
@interface 어노테이션명 {
  // 내용
}
```

위와 같은 형식으로 정의할 수 있다. 

요소의 타입으로는 기본형, String, enum, class, 어노테이션이 올 수 있고 매개변수는 올 수 없다. 추상메서드의 형태로 작성하면 된다.

### 어노테이션의 종류

어노테이션은 크게 Built-in Annotation과 Meta Annotation으로 나뉜다.

Built-in Annotation은 자바에서 기본으로 제공하는 어노테이션이고 Meta Annotation은 커스텀 어노테이션을 만들 수 있게 제공된 어노테이션이다. 따라서 Meta Annotation으로 만들어진 커스텀 어노테이션까지 총 세 종류가 있다.

**Built-in Annotation**

| 종류                 | 설명                                                         |
| -------------------- | ------------------------------------------------------------ |
| @Override            | 선언한 메서드가 오버라이드 되었다는 것을 나타냄.* 상위(부모)클래스(또는 인터페이스)에서 해당 메서드를 찾지 못하면 컴파일 에러 발생. |
| @Deprecated          | 해당 메서드가 더이상 사용되지 않음을 표시합니다.* 해당 메서드를 사용할 경우 컴파일 경고를 발생. |
| @SuppressWarnings    | 선언한 곳의 컴파일 경고를 무시.                              |
| @SafeVarargs         | 제네릭 가은 가변인자 매개변수를 사용할 때의 경고를 무시한다.(Java 7 이상) |
| @FunctionalInterface | 람다 함수등을 위한 인터페이스 지정.(Java 8 이상)* 메소드가 없거나 두개이상 되면 컴파일 오류 발생 |

**Meta Annotation**

| 종류        | 설명                                                         |
| ----------- | ------------------------------------------------------------ |
| @Retention  | 어노테이션이 유지되는 기간을 지정하는데 사용한다.(세가지 유지정책 사용) |
| @Documented | 해당 어노테이션을 javadoc에 포함시킵니다.                    |
| @Target     | 어노테이션이 적용할 위치를 지정합니다. 여러 값일 경우 {} 사용 |
| @Inherited  | 어노테이션의 상속을 가능하게 합니다.                         |
| @Repeatable | 연속적으로 어노테이션을 사용할 수 있게 해줍니다.             |

### @Retention

`@Retention`은 어노테이션이 유지되는 기간을 지정하는데 사용한다. 이 때 세가지 유지정책이 사용되는데 Source, Class, Runtime이 있다. 

Source는 컴파일 전까지만 유효, 소스코드에만 존재하고 클래스파일에는 존재하지 않음

Class는 클래스 파일에만 존재하고 실행시 사용 불가.

Runtime은 클래스파일, 실행시점 모두 존재한다. 컴파일 후에도 JVM에 의해 계속 참조 가능.

`RetentionPolicy`라는 enum이 있고 그 안에 SOURCE, CLASS, RUNTIME이 존재한다. `@Retention` 사용 시 `@Retention(RetentionPolicy.RUNTIME)`의 형식으로 지정해주면 된다. 

```java
@Retention(RetentionPolicy.RUNTIME)
@interface SampleAnnotation {
}
```

### @Target

`@Target`은 어노테이션이 적용가능한 대상, 위치를 지정하는데 사용한다. 종류는 Package, Type(class/interface/enum), Annotation_type(어노테이션), Constructor, Field, Local_variable, Method, Parameter, Type_parameter, Type_use(타입이 사용되는 모든 곳, 타입선언)가 있다.

사용 방법은` @Retention`과 동일하지만 차이점은 `@Retention`은 단 하나만 정의할 수 있지만 `@Target`은 여러개를 사용할 수 있고 여러개라면 괄호 안에 중괄호를 써서 나타낸다. 

```java
@Target({ElementType.FIELD, ElementType.METHOD})
@interface SampleAnnotation {
}
```

### @Documented

`@Documented`는 해당 어노테이션을 javadoc에 추가시킬지의 여부를 나타낸다.

```java
@Documented // javadoc에 추가해주세요
@interface SampleAnnotation {
}
```

### 어노테이션 프로세서

어노테이션을 사용하기 위해서는 어노테이션 프로세서가 필요하다. 어노테이션 프로세서는 컴파일 타임에 어노테이션을 분석하고 해당 어노테이션 붙은 곳을 찾아내 분석을 바탕으로 클래스를 만들어 낸다. 어노테이션이 단순한 주석 이상의 역할을 수행할 수 있게 만들어주는 것이다.


