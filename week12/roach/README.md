# **목표**

자바의 애노테이션에 대해 학습하세요.

# **학습할 것 (필수)**

- 애노테이션 정의하는 방법
- **[@retention](https://github.com/retention)**
- **[@target](https://github.com/target)**
- **[@documented](https://github.com/documented)**
- 애노테이션 프로세서

## 어노테이션 정의하는 방법

- 에 들어가기 앞서.. 어노테이션은 정말 중요하니깐 잘알아두길 바란다.
- 아래와 같이 어노테이션을 정의할 수 있다.

```java
public @interface GetMapping {

}
```

- 어노테이션은 진짜 말그대로 메타데이터 처럼 이용된다. 이는 밑으로 더 내려가면서 알아보도록 하자.

### @Retention

- Retention 은 어노테이션이 해석되는 범위를 나타낸다.
- 범위는 `{SOURCE, RUNTIME, CLASS}` 가 있다.
- `SOURCE` : 사실상 주석이랑 다른게 없다..
- `CLASS` : 어노테이션은 컴파일 될때까지 어노테이션을 유지한다. 컴파일 후에는 버려진다.
- `RUNTIME` : 제일 많이 사용되며, 런타임까지 생명주기가 유지된다.

### @Target

- 타겟은 해당 어노테이션을 어디 붙일 수 있는지를 나타낸것이다.
- `{TYPE, METHOD, CONSTRUCTOR, PARAMETER 등등.. 다양하다}`

### @Document

- 해당 주석을 붙여주면, JavaDoc 으로 작성된 문서에 포함 된다.
- 인텔리제이에서 Generate JavaDoc 해보면 보기 쉽다.

## 어노테이션 프로세서

- 가장 중요하다. 사실 어노테이션을 왜쓰는지에 대한 내용이 아닐까 싶다.
- 아래 코드를 작성해보자!

## 요구사항

- 어노테이션에 `data = 10` 이라고 적으면 int 형 필드에 10을 주입시켜주는것.

## 코드 작성

- 일단 인터페이스를 설계해보자

  - 필드가 Target 이고 RUNTIME 동안 유지되야 하니깐..

  ```java
  import java.lang.annotation.ElementType;
  import java.lang.annotation.Retention;
  import java.lang.annotation.RetentionPolicy;
  import java.lang.annotation.Target;

  @Target(ElementType.FIELD)
  @Retention(RetentionPolicy.RUNTIME)
  public @interface InsertIntData {

      int data() default 0;

  }
  ```

- 자 일단 어노테이션은 이정도로 설계 해두면 될것 같다.

- 이제 주입을 어떤 시나리오로 할건지 생성하자

```java
public class Example {

    @InsertIntData(data = 30)
    private int data;

    public Integer getInject() {
        return data;
    }

}
```

- 이렇게 필드위에 적으면 해당 int data 필드가 30으로 바뀌게 하는것이다.

```java
import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        CustomContextContainer customContextContainer = new CustomContextContainer();
        //Example  data = 30;
        Example example = customContextContainer.get(Example.class);

        System.out.println(example.getInject());
    }

}
```

- 자이제 우리의 ExceptionContainer 안에 핸들링을 해주는 메소드들을 넣자

```java
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class CustomContextContainer {

    private <T> T invokeAnnotation(T instance) throws IllegalAccessException {
        Field[] fields = instance.getClass().getDeclaredFields();
        for(Field field : fields) {
            InsertIntData annotation = field.getAnnotation(InsertIntData.class);
            if(annotation != null && field.getType() == int.class) {
                field.setAccessible(true);
                field.set(instance, annotation.data());
            }
        }
        return instance;
    }

    public  <T> T get(Class<T> obj) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        T instance = obj.getDeclaredConstructor().newInstance();
        instance = invokeAnnotation(instance);
        return instance;
    }

}
```

- 선언된 필드 들 중 InserIntData 어노테이션이 붙은것들을 찾아서, 해당 필드에 값을 주입시켜준다.
