# [Week12] 애노테이션

## 애노테이션 정의하는 방법

애노테이션(Annotation)은 해당 코드 블록에 대한 메타데이터(metadata)라고 볼 수 있다. 메타데이터란, 애플리케이션이 처리해야 할 데이터가 아니라, 컴파일 과정과 실행 과정에서 코드를 어떻게 컴파일하고 처리할 것인지를 알려주는 정보이다.

### 애노테이션의 용도

- 컴파일러에게 코드 문법 에러를 체크하도록 정보를 제공
- 소프트웨어 개발 툴이 빌드나 배치 시 코드를 자동으로 생성할 수 있도록 정보를 제공
- 실행 시(런타임 시) 특정 기능을 실행하도록 정보를 제공

컴파일러에게 코드 문법 에러를 체크하도록 정보를 제공하는 대표적인 예는 `@Override` 애노테이션이다. `@Override` 는 메소드 선언 시 사용하는데, 메소드가 오버라이드 되었다는 것을 컴파일러에게 알려주어서 컴파일러가 오버라이드 여부를 검사하도록 해준다. 만일 오버라이드가 정확하게 되지 않았다면 컴파일러는 에러를 발생시킨다. 

```java
public class OverrideTest {
    private int id;
    private String title;
    private String content;

    @Override
    public String toString() {
        return "OverrideTest{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
```

애노테이션은 빌드 시 자동으로 XML 설정 파일을 생성하거나, 배포를 위해 JAR 압축 파일을 생성하는데에도 사용된다. 

그리고 실행 시 클래스의 역할을 정의하기도 한다. 이 방식은 스프링에서 주로 사용하는 방식이다.

```java
@Controller
class UserController {
	...
}
```

### 애노테이션 타입 정의와 적용

애노테이션 타입을 정의하는 방법은 인터페이스를 정의하는 것과 유사하다. 다음과 같이 `@interface` 를 사용해서 애노테이션을 정의하며, 그 뒤에 사용할 애노테이션 이름이 온다.

```java
public @interface AnnotationName {
}
```

이렇게 정의한 애노테이션은 코드에서 다음과 같이 사용할 수 있다.

```java
@AnnotationName
class UseAnnotationClass {
}
```

애노테이션은 엘리먼트(element)를 멤버로 가질 수 있다. 이 엘리먼트는 외부의 값을 입력 받는 용도로 사용된다. 각 엘리먼트는 타입과 이름으로 구성되며, 디폴트 값을 가질 수 있다. 메소드의 매개변수와 유사한 느낌으로 생각하면 될 것 같다.

```java
public @interface AnnotationName {
		{type} elementName() [default value]; // element definition
}
```

엘리먼트의 타입으로는 int나 double과 같은 기본 데이터 타입이나 String, 열거 타입, Class 타입, 그리고 이들의 배열 타입을 사용할 수 있다. 엘리먼트의 이름 뒤에는 메소드를 작성하는 것처럼 `()`를 붙여야 한다. 예를 들어, String 타입의 엘리먼트와 int 타입의 엘리먼트를 다음과 같이 선언할 수 있다.

```java
public @interface AnnotationName {
		Strint elementname1();
		int elementName2() default 5;
}
```

이렇게 정의한 애노테이션을 코드에서 사용할 때는 다음과 같이 기술한다. elementName1은 디폴트 값이 없으르모 반드시 값을 적어주어야 하고, elementName2는 생략이 가능하다.

```java
@AnnotationName(elementName1 = "value", elementName2 = 3);
or
@AnnotationName(elementName1 = "value")
```

스프링에서 다음과 같이 종종 사용된다.

```java
@Controller(name = "main")
public class MainController {
}
```

애노테이션은 기본 엘리먼트인 value를 가질 수 있는데, value 엘리먼트를 가진 애노테이션을 코드에서 적용할 때에는 다음과 같이 값만 기술할 수 있다.

```java
public @interface AnnotationName {
		String value();
}
```

```java
@AnnotationName("value")
```

```java
@WebServelet("/main")
public class MainServlet {
}
```

```java
@WebServelet(value = "/main")
public class MainServlet {
}
```

---

## @Target - 애노테이션 적용 대상 

애노테이션을 적용할 수 있는 대상은 java.lang.annotation.ElementType 열거 상수로 다음과 같이 정의되어 있다. `@Target`에 해당 열거 상수를 지정함으로써 해당 애노테이션의 적용 대상(클래스, 메소드 등)을 지정할 수 있다.

| ElementType 열거 상수 | 적용 대상                     |
| --------------------- | ----------------------------- |
| TYPE                  | 클래스, 인터페이스, 열거 타입 |
| ANNOTATION_TYPE       | 애노테이션                    |
| FIELD                 | 필드                          |
| CONSTRUCTOR           | 생성자                        |
| METHOD                | 메소드                        |
| LOCAL_VARIABLE        | 로컬 변수                     |
| PACKAGE               | 패키지                        |

애노테이셩이 적용될 대상을 지정할 때에는 `@Target` 애노테이션을 사용하면 되는데, `@Target`의 기본 엘리먼트인 value는 ElementType 배열을 값으로 가진다. 즉, 애노테이션이 적용될 대상을 여러개 지정할 수 있도록 하기 위해서이다.

```java
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD})
public @interface AnnotationName {
}
```

위와 같이 애노테이션을 정의할 경우, 클래스, 필드, 메소드만 애노테이션을 적용할 수 있다는 의미이다.

```java
@AnnotationName
public class ClassName {
		@AnnotationName
		private String fieldName;
  
		@AnnotationName // @Target에 CONTRUCT가 없으므로 에러 발생
		public ClassName(){}
  
		@AnnotationName
		public void methodName() {}
}
```

---

## @Retention - 애노테이션 유지 정책

애노테이션 정의 시 사용 용도에 따라 어느 범위까지 유지할 것인지 지정해야 한다. 소스 상에만 유지할 것인지, 컴파일된 클래스까지 유지할 것인지, 런타임 시에도 유지할 것인지를 지정해야 한다. 애노테이션 유지 정책을 java.lang.annotation.RetentionPolicy 열거 상수로 다음과 같이 정의되어 있다.

| RetentionPolicy 열거 상수 | 설명                                                         |
| ------------------------- | ------------------------------------------------------------ |
| SOURCE                    | 소스 상에서만 애노테이션 정보를 유지.<br />소스 코드를 분석할 때만 의미가 있으며, 바이트 코드 파일에는 정보가 남지 않는다. |
| CLASS                     | 바이트 코드 파일까지 애노테이션 정보를 유지.<br />하지만 리플렉션을 이용해서 애노테이션 정보를 얻을 수는 없다. |
| RUNTIME                   | 바이트 코드 파일까지 애노테이션 정보를 유지.<br /> 리플렉션을 이용해서 런타임 시에 애노테이션 정보를 얻을 수 있다. |

리플렉션(Reflection)이란 런타임 시에 클래스의 메타 정보를 얻는 기능을 말한다. 예를 들어, 클래스가 가지고 있는 필드가 무엇인지, 어떤 생성자를 갖고 있는지, 어떤 메소드를 가지고 있는지, 적용된 애노테이션이 무엇인지를 알아내는 것이 리플렉션이다. 리플렉션을 이용해서 런타임 시에 애노테이션 정보를 얻으려면 애노테이션 유지 정책을 RUNTIME으로 설정해야 한다. 따라서 대부분은 리플렉션이 가능한 RUNTIME을 사용한다.

애노테이션 유지 정책을 설정할 때는 `@Retention` 애노테이션을 사용한다. `@Retention`의 기본 엘리먼트인 value는 RetentionPoilicy 타입이므로 세 가지 상수 중 하나를 지정하면 된다. 코드 자동 생성 툴을 개발하지 않는 이상 우리가 작성하는 애노테이션은 대부분 런타임 시점에 사용하기 위한 용도로 만들어진다.

```java
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AnnotationName {
}
```

`@Retention`은 어차피 하나만 받기 때문에 `value = RetentionPolicy.RUNTIME` 과 같은 의미이며, 값만 주게 되면 기본 엘리먼트 value에 저장한다.

---

## @Documented

`@Documented`는 메타 애노테이션이다. 어떤 애노테이션을 정의할 때 이것을 사용하면, 해당 애노테이션을 사용하는 클래스들이 자동 생성된 JavaDoc에 보여진다.

```java
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Transactional {
}
```

- https://stackoverflow.com/questions/5592703/documented-annotation-in-java/20432882#:~:text=%40Documented%20is%20a%20meta%2Dannotation,this%20in%20their%20generated%20JavaDoc.

---

## 애노테이션 프로세서

소스 레벨의 애노테이션 프로세싱은 자바5에서 처음 등장했다. 이는 컴파일 단계에서 추가 소스 파일을 생성하는 편리한 기술이다.

소스 파일이 자바 파일일 필요는 없다. 소스 코드의 주석을 기반으로 모든 종류의 설명, 메타 데이터, 문서, 리소스 또는 기타 모든 유형의 파일을 생성 할 수 있다.

주석 처리는 많은 유비쿼터스 자바 라이브러리에서 적극적으로 사용된다. 예를 들어, QueryDSL 및 JPA에서 메타 클래스를 생성하고 Lombok 라이브러리의 상용구 코드로 클래스를 확장한다.

- https://www.baeldung.com/java-annotation-processing-builder
- http://hannesdorfmann.com/annotation-processing/annotationprocessing101/
- https://eyegochild.medium.com/annotation-processor-c409f1f07c84
- https://docs.oracle.com/javase/8/docs/api/javax/annotation/processing/Processor.html
- https://tomgregory.com/annotation-processors-in-gradle-with-the-annotationprocessor-dependency-configuration/

잘은 모르겠지만 애노테이션 프로세서가 JVM에 포함되어 있기 때문에 우리가 막 붙여서 자바 문법으로서 사용할 수 있는 것 같다.