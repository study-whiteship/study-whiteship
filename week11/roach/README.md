# **목표**

자바의 열거형에 대해 학습하세요.

# **학습할 것 (필수)**

- enum 정의하는 방법
- enum이 제공하는 메소드 (values()와 valueOf())
- java.lang.Enum
- EnumSet

### Enum 정의하는 방법

- Enum 을 상수들이 모인 하나의 집합이다.
- 우리가 자바에서 **private static final** 로 상수를 선언하는 것이 아니라, Enum 으로 사용하는 경우가 많은데, Enum 은 일단 값 객체로서 사용할 수 있다는 큰 장점이 있다. 하나의 값으로 인정받기에, `==` 을 사용한 비교도 가능하다.
- 가장 큰 장점은 Type 에 좀 더 상세한 제한을 걸 수 있다는 것이다. 아래의 예제를 한번보자

```java
public void getMethod(String method) {
		if(method.eqauls("GET")) {
				System.out.println("GET!!");
		}
}
```

- 위의 예제는 매개변수로 String 을 받을 수 있다. 하지만 아무런 "ABCDE" 같은 값들도 들어 올 수 있어서 그다지 좋은 방법은 아니라고 생각한다. 그리고 HTTP METHOD 의 경우 한정된 개수로 제한되어 있다. 따라서 우리는 ENUM 으로 만들어 상수처럼 관리해볼 수 있다.

```java
public enum Method {
	GET, POST, PUT, DELETE, FETCH
}
```

- 위의 Enum 을 사용해서 아까의 코드를 조금 더 바꿔보자!

```java
public void getMethod(Method requestMethod) {
	if(method == requestMethod) {
		System.out.println(requestMethod.name());
	}
}
```

- 이제 아무런 "abc" 와 같은 쓸모없는 값은 애초에 들어올 수 없게 되었다. 또한 내부에서 `==` 연산자를 사용함으로써 조금의 성능상의 이점을 볼 수 있게 되었다.

### Values()

- Values 는 Enum Type 의 Array 로 반환해준다. 만약 위의 `Method` 를 **values()** 한다고 해보자.

```java
Method[] methods = Method.values();
```

- 위와 같이 사용할 수 있다. 하지만 별로 사용해본적은 없는 메소드이다.

### Value.of(String name)

- 해당 메소드는 나는 자주 이용한다. 위의 예제에서 String 을 Method 로 바꾸는 작업을 해보자!

```java
getMethod(Method.of("GET"))
```

- 위와 같이 사용하면, 아까 만들어둔 메소드에 대입이 가능하다.
- Enum 에서도 `.of` 를 static 으로 선언하고 있는데, 해당 Argument 는 Enum Type 과, name 으로 가능하다. 그런데 그냥 해당 Enum 에서 of 를 쓰자~~!

### EnumSet

- EnumSet 은 Enum 타입에 사용하기 위해 구현된 Set 자료구조이다.

```java
EnumSet<Method> methods = Emumset.of(Method.GET, Method.POST);

EnumSet<Method> methods = Collections.synchronizedSet(Emumset.of(Method.GET, Method.POST));
```
