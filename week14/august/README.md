# [Week14] 제네릭

## 제네릭 사용법

Java5 부터 제네릭(Generic) 타입이 새로 추가되었는데, 제네릭 타입을 이용함으로써 잘못된 타입이 사용될 수 있는 문제를 컴파일 과정에서 제거할 수 있게 되었다. 제네릭은 컬렉션, 람다식, 스트림, NIO에서 널리 사용되므로 확실히 이해해 두어야 한다. 

API 문서를 보면 제네릭 표현이 매우 많다. 제네릭은 클래스와 인터페이스, 그리고 메소드를 정의할 때 타입(type)을 파라미터(parameter)로 사용할 수 있도록 한다. 타입 파라미터는 코드 작성 시 구체적인 타입으로 대체되어 다양한 코드를 생성하도록 해준다. 제네릭을 사용하는 코드는 비제네릭 코드에 비해 다음과 같은 이점을 가지고 있다.

### 제네릭 장점

- 컴파일 시 강한 타입 체크를 할 수 있다.

  자파 컴파일러는 코드에서 잘못 사용된 타입 때문에 발생하는 문제점을 제거하기 위해 제네릭 코드에 대해 강한 타입 체크를 한다. 실행 시 타입 에러가 나는 것보다는 컴파일 시에 미리 타입을 강하게 체크해서 에러를 사전에 방지하는 것이 좋다.

- 타입 변환(casting)을 제거한다.

  비제네릭 코드는 불필요한 타입 변환을 하기 때문에 프로그램 성능에 악영향을 미친다. 다음 코드를 보면 List에 문자열 요소를 저장했지만, 요소를 찾아올 때는 반드시 String으로 타입 변환을 해야 한다.

  ```java
  List list = new Arraylist();
  list.add("hello");
  String str = (String) list.get(0); // type casting needed
  ```

  다음 과 같이 제네릭 코드로 수정하면 List에 저장되는 요소를 String 타입으로 국한하기 때문에 요소를 찾아올 때 타입 변환을 할 필요가 없어 프로그램 성능이 향상된다.

  ```java
  List<String> list = new ArrayList<String>();
  list.add("hello");
  String str = listr.get(0); // type casting not needed
  ```

### 제네릭 타입(`class<T>`,`interface<T>`)

제네릭 타입은 타입을 파라미터로 가지는 클래스와 인터페이스를 말한다. 제네릭 타입은 클래스 또는 인터페이스 이름 뒤에 "<>"부호가 붙고, 사이에 타입 파라미터가 위치한다.

```java
public class ClassName<T> {...}
public interface InterfaceName<T> {...}
```

타입 파라미터는 변수명과 동일한 규칙에 따라 작성할 수 있지만, 일반적으로 대문자 알파벳 한 글자로 표현한다. 제네릭 타입을 실제 코드에서 사용하려면 타입 파라미터에 구체적인 타입을 지정해야 한다. 그렇다면 왜 이런 타입 파라미터를 사용해야  할까?

### Object와의 차이점

Object 타입을 사용하면 모든 종류의 자바 객체를 저장할 수 있다는 장점은 있지만, 저장할 때 타입 변환이 발생하고, 읽어올 대에도 타입 변환이 발생한다. 이러한 타입 변환이 빈번해지면 전체 프로그램 성능에 좋지 못한 결과를 가져올 수 있다. **모든 종류의 객체를 저장하면서 타입 변환이 발생하지 않도록 하는 방법이 바로 제네릭이다.**

```java
public class Box<T> {
		private T t;
		public T get() { return t; }
		public void set(T t) { this.t = t; }
}
```

타입 파라미터 T를 사용해서 Object 타입을 모두 T로 대체했다. T는 Box 클래스로 객체를 생성할 대 구체적인 타입으로 변경된다. 예를 들어 다음과 같이 Box객체를 생성했다고 가정해본다.

```java
Box<String> box = new Box<String>();
```

타입 파라미터 T는 String 타입으로 변경되어 Box 클래스의 내부는 다음과 같이 자동으로 치환되어 재구성된다. -> 이 부분은 바이트코드로 직접 본다면 좀 더 명확히 알 수 있을 것 같다.

```java
public class Box<String> {
		private String t;
		public String get() { return t; }
		public void set(T t) { this.t = t; }
}
```

---

## 제네릭 주요 개념 (바운디드 타입, 와일드 카드)

### 제한된 타입 파라미터(<T extends 최상위 타입>)

타입 파라미터에 지정되는 구체적인 타입을 제한할 필요가 종종 있다. 예를 들어 숫자를 연산하는 제네릭 메소드는 매개값으로 Number 타입 도는 하위 클래스 타입(Byte, Short, Integer, Long, Double)의 인스턴스만 가져야 한다. 이것이 제한된 타입 파라미터(bounded type parameter)가 필요한 이유이다. 제한된 타입 파라미터를 선언하려면 타입 파라미터 뒤에 extends 키워드를 붙이고 상위 타입을 명시하면 된다. 상위 타입은 클래스뿐만 아니라 인터페이스도 가능하다. 인터페이스라고 해서 implements를 사용하지 않는다.

```java
public <T extends 상위타입> 리턴타입 메소드(매개변수, ...) {...}
```

타입 파라미터에 지정되는 구체적인 타입은 상위 타입이거나 상위 타입의 하위 또는 구현 클래스만 가능하다. 주의할 점은 메소드의 중괄호 {} 안에서 타입 파라미터 변수로 사용 가능한 것은 상위 타입의 멤버(필드, 메소드)로 제한된다. 하위 타입에만 있는 필드와 메소드는 사용할 수 없다. 다음은 숫자 타입만 구체적인 타입으로 갖는 제네릭 메소드 compare()이다. 두 개의 숫자 타입을 매개값으로 받아 차이를 리턴한다.

```java
public <T extends Number> int compare(T t1, T t2) {
		double v1 = t1.doubleValue(); // Number's doubleValue()
		double v2 = t2.doubleValue(); // Number's doubleValue()
		return Double.compare(v1, v2);
}
```

`doubleValue()` 메소드는 Number 클래스에 정의되어 있는 메소드로 숫자를 double 타입으로 변환한다. `Double.compare()` 메소드는 첫 번째 매개값이 작으면 -1을, 같으면 0을, 크면 1을 반환한다.

### 예제 코드

```java
public class BoundedUtil {
    public static <T extends Number> int compare(T t1, T t2) {
        double v1 = t1.doubleValue();
        double v2 = t2.doubleValue();
        return Double.compare(v1, v2);
    }
}

```

```java
public class BoundedTypeParameterExample {
    public static void main(String[] args) {
        String str = BoundedUtil.compare("a", "b");

        int result1 = BoundedUtil.compare(10, 20); // int -> Integer
        System.out.println(result1);

        int result2 = BoundedUtil.compare(4.5, 3); // double -> Double
        System.out.println(result2);
    }
}
```

### 와일드카드 타입(<?>, <? extends ...>, <? super ...>)

코드에서 일반적으로 `?`를 와일드카드(wildcard)라고 부른다. 제네릭 타입을 매개값이나 리턴 타입으로 사용할 때 구체적인 타입 대신에 와일드카드를 다음과 같이 세 가지 형태로 사용할 수 있다.

- 제네릭타입<?>: Unbounded Wildcards(제한 없음)

  타입 파라미터를 대치하는 구체적인 타입으로 모든 클래스나 인터페이스 타입이 올 수 있다.

- 제네릭타입<? extends 상위타입>: Upper Bounded Wildcards(상위 클래스 제한)

  타입 파라미터를 대치하는 구체적인 타입으로 그 타입의 이하 타입만 올 수 있다.

- 제네릭타입<? super 하위타입>: Lower Bounded Wildcards(하위 클래스 제한)

  타입 파라미터를 대치하는 구체적인 타입으로 그 타입의 이상 타입만 올 수 있다.

---

## 제네릭 메소드 만들기

### 제네릭 메소드(<T,R> R method(T t))

제네릭 메소드는 매개 타입과 리턴 타입으로 타입 파라미터를 갖는 메소드를 말한다. 제네릭 메소드를 선언하는 방법은 리턴 타입 앞에 `<>` 기호를 추가하고 타입 파라미터를 기술한 다음, 리턴 타입과 매개 타입으로 타입 파라미터를 사용하면 된다.

```java
public <type parameters...> return_type method(parameters...) {...}
```

다음 boxing() 제네릭 메소드는 `<>`기호 안에 타입 파라미터T를 기술한 뒤, 매개 변수 타입으로 T를 사용했고, 리턴타입으로 제네릭 타입 `Box<T>`를 사용했다.

```java
public <T> Box<T> boxing(T t) {...}
```

제네릭 메소드는 두 가지 방식으로 호출할 수 있다. 코드에서 타입 파라미터의 구체적인 타입을 명시적으로 지정해도 되고, 컴파일러가 매개값의 타입을 보고 구체적인 타입을 추정하도록 할 수도 있다.

```java
return_type variable = <specific type> method(parameter); // explicit
return_type variable = method(parameter); // implicit
```

다음 코드는 `boxing()` 메소드를 호출하는 코드이다.

```java
Box<Integer> box = <Integer>boxing(100); // explicit
Box<Integer> box = boxing(100); // implicit
```

### 예제 코드

```java
public class Util {
    public static <T> Box<T> boxing(T t) {
        Box<T> box = new Box<T>();
        box.set(t);
        return box;
    }
}
```

```java
public class BoxingMethodExample {
    public static void main(String[] args) {
        Box<Integer> box1 = Util.<Integer>boxing(100);
        int intValue = box1.get();

        Box<String> box2 = Util.boxing("august");
        String strValue = box2.get();
    }
}
```

## Erasure

Java 언어에 제네릭이 도입되어 컴파일 시간에보다 엄격한 유형 검사를 제공하고 일반 프로그래밍을 지원한다. 제네릭을 구현하기 위해 Java 컴파일러는 다음에 유형 삭제를 적용한다.

- 제네릭 형식의 모든 형식 매개 변수를 해당 범위 또는 형식 매개 변수가 제한되지 않은 경우 `Object로` 바꾼다 . 따라서 생성 된 바이트 코드에는 일반 클래스, 인터페이스 및 메서드 만 포함된다.
- 유형 안전성을 유지하기 위해 필요한 경우 유형 캐스트를 삽입해야 한다.
- 확장 된 제네릭 유형에서 다형성을 보존하는 브리지 메서드를 생성한다.

유형 삭제는 매개 변수화 된 유형에 대해 새 클래스가 생성되지 않도록합니다. 결과적으로 제네릭은 런타임 오버 헤드를 발생시키지 않는다.

## References

- https://docs.oracle.com/javase/tutorial/java/generics/erasure.html