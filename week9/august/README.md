# [Week9] 예외 처리

## Exception과 Error의 차이는?

컴퓨터 하드웨어의 오동작 또는 고장으로 인해 응용프로그램 실행 오류가 발생하는 것을 자바에서는 에러(error)라고 한다. 에러는 JVM실행에 문제가 생겼다는 것이므로 JVM 위에서 실행되는 프로그램을 아무리 견고하게 만들어도 결국 실행 불능 상태가 된다. 개발자는 이러한 에러에 대해 대처를 할 방법은 없다. 다만 에러가 발생하지 않도록 좀 더 견고한 소프트웨어를 설계하는 수 밖에 없다.

자바에서는 에러 이외에 예외(exception)라고 부르는 오류를 별도로 가지고 있다. 예외란 *사용자의 잘못된 조작 또는 개발자의 잘못된 코딩으로 인해 발생하는 프로그램 오류*를 말한다. 예외가 발생되면 프로그램은 곧바로 종료된다는 점에서는 에러와 동일하다. 그러나 예외는 예외 처리(Exception Handling)를 통해 프로그램을 바로 종료하지 않고 정상 실행 상태가 유지되도록 할 수 있다.

## 자바가 제공하는 예외 계층 구조

자바의 예외는 크게 두 가지 종류가 있다. 하나는 일반 예외(Exception)이고, 다른 하나는 실행 예외(Runtime Exception)이다. 일반 예외는 컴파일러 체크 예외라고도 하는데, 자바 소스를 컴파일하는 과정에서 예외 처리 코드가 필요한지 검사하기 때문이다. 만약 예외 처리 코드가 없다면 컴파일 오류가  발생한다. 결국 예외 처리를 확인하는 시점만 다를 뿐이지 모두 추가적인 예외 처리가 필요하다. (별다른 처리가 없다면 JVM이 프로그램을 종료시킨다.)

```
└── java.lang.Object
	  └── java.lang.Throwable
  	    └── java.lang.Exception
						├── java.lang.ClassNotFoundException ... 등 일반 예외들
            └── java.lang.RuntimeException 실행 예외
```

우선, Exception은 기본적으로 Object를 상속하며, Throwable인터페이스를 구현한다. 일반 예외와 실행예외 모두 이 Exception을 상속한다. 일반 예외와 실행 예외의 차이점은 RuntimeException을 상속하는지 여부이다.



## RuntimeException과 RE가 아닌 것의 차이는?

실행 예외(RuntimeException)은 자바 컴파일러가 체크를 하지 않기 떄문에 오로지 개발자의 경험에 의해서 예외 처리 코드를 삽입해야 한다. 만약 개발자가 실행 예외에 대해 예외 처리 코드를 넣지 않았을 경우, 해당 예외가 발생하면 프로그램은 곧바로 종료된다. 즉, 기본적으로는 예외 처리의 책임을 최종적으로 JVM에게 떠넘겨서 (Throws) 처리가 되도록 되어있다. 

하지만 이렇게 처리하면 모든 실행 예외에 대해서 프로그램이 종료되는 결과만 이루어질 것이다. 따라서 개발자는 자주 발생하는 실행 예외에 대해서 미리 파악하고 처리하는 코드를 작성할 필요가 있다. 그렇게 함으로써 일반 사용자가 프로그램을 적절히 사용할 수 있도록 해야한다.

### NullPointerException

자바 프로그램에서 가장 빈번하게 발생하는 RE는 NullPointerException(NPE)이다. 이것은 객체 참조가 없는 상태, 즉 null 값을 갖는 참조 변수를 통해 (`.` 연산자를 사용하여) 접근했을 때 발생한다. 실행 중에 (객체가 없는 상태에서) 없는 객체를 사용하려 했기 때문에 예외가 발생하는 것이다.

```java
public class NullPointerExceptionExample {
    public static void main(String[] args) {
        String referenceVariable = null;
        System.out.println(referenceVariable.toString()); // 없는 객체를 참조
    }    
}
```

예를 들어, 위의 코드 처럼 null이 들어있는 참조형 변수에 접근하여 toString()메서드를 호출하려고 하면 NPE가 발생한다.

```java
Exception in thread "main" java.lang.NullPointerException
        at NullPointerExceptionExample.main(NullPointerExceptionExample.java:4)
```

### ArrayIndexOutOfBoundsException

배열에서 인덱스 범위를 초과하여 사용할 경우 java.lang.ArrayIndexOutOfBoundsException가 발생한다.

- case 1

```java
public class ArrayIndexOutOfBoundsExceptionExample {
    public static void main(String[] args) {
        String data1 = args[0];
        String data2 = args[1];
        
        System.out.println("args[0]" + data1);
        System.out.println("args[1]" + data2);
    }
}
```

위 코드에서 매개값을 주지 않고 실행하면 args[0]과 args[1]가 없는데 참조하려고 하므로 예외가 발생한다.

```java
Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: Index 0 out of bounds for length 0
        at ArrayIndexOutOfBoundsExceptionExample.main(ArrayIndexOutOfBoundsExceptionExample.java:3)
```

args[0]이 없는데 접근하려고 한다는 에러 메시지가 출력된다.

- case 2

```java
public class ArrayIndexOutOfBoundsExceptionExample {
    public static void main(String[] args) {
        int[] arr = new int[3];

        arr[0] = 0;
        arr[1] = 1;
        arr[2] = 2;
        arr[3] = 3; // 예외 발생
    }
}
```

위의 코드에서는 new int[3]으로 3만큼의 배열공간을 할당했는데, arr[3]에서 네 번째 인덱스에 접근하려고 한다. 따라서 할당되지 않은 배열 인덱스에 접근 하였으므로 예외가 발생한다.

```java
Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: Index 3 out of bounds for length 3
        at ArrayIndexOutOfBoundsExceptionExample.main(ArrayIndexOutOfBoundsExceptionExample.java:8)
```

## 자바에서 예외 처리 방법 (try, catch, throw, throws, finally)

프로그램에서 예외가 발생했을 경우 프로그램의 갑작스러운 종료를 막고, 정상 실행을 유지할 수 있도록 처리하는 코드를 예외 처리 코드라고 한다. 자바 컴파일러는 소스 파일을 컴파일할 때 일반 예외가 발생할 가능성이 있는 코드를 발견하면 컴파일 오류를 발생시켜 개발자로 하여금 강제적으로 예외 처리 코드를 작성하도록 요구한다. (IDE를 사용하다 보면 try-catch 나 throws를 추가하라고 빨간줄이 뜨는 것이 바로 해당 경우이다.) 그러나 실행 예외는 컴파일러가 체크해주지 않기 때문에 오롯이 개발자의 몫이다.

### if문으로 처리

자바에서 예외를 처리하는 가장 간단한 방법은 if문으로 예외 상황을 미리 정확히 예상하여 제어하는 것이다. 예를 들어, 사용자에게 프로그램 실행에 필요한 매개값을 2개 받아야 하는 경우 아래와 같이 작성하여 처리할 수 있다.

```java
public class ArrayIndexOutOfBoundsExceptionHandlingExample {
    public static void main(String[] args) {
        if (args.length == 2) {
            String data1 = args[0];
            String data2 = args[1];
            
            System.out.println("args[0]" + data1);
            System.out.println("args[1]" + data2);
        } else {
            System.out.println("usage:");
            System.out.print("java ArrayIndexOutOfBoundsExceptionHandlingExample ");
            System.out.println("argument1 argument2");
        }
        
    }
}
```

### try-catch-finally

하지만 대부분의 경우 정확히 예외 상황을 예측하기란 힘들다. 따라서 자바에서는 *예외 상황이 발생할 수 있는 코드에 대해서 묶어서 처리하는 방식*의 코드 작성을 할 수 있도록 지원한다. 이는 `try`와 `catch`라는 키워드로 작성 가능하다.

```java
public class TryCatchFinallyExample {
    public static void main(String[] args) {
        String data1 = null;
        String data2 = null;

        try {
            data1 = args[0];
            data2 = args[1];
            System.out.println("args[0]" + data1);
            System.out.println("args[1]" + data2);
        } catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("usage:");
            System.out.print("java TryCatchFinallyExample ");
            System.out.println("argument1 argument2");
            return;
        }
        
        try {
            int value1 = Integer.parseInt(data1);
            int value2 = Integer.parseInt(data2);
            int result = value1 + value2;
            System.out.println(data1 + "+" + data2 + "=" + result);
        } catch (NumberFormatException e) {
            System.out.println("Cannot format to number.");
        } finally {
            System.out.println("Enter the proper arguments. (number format)");    
        }

    }
}
```

위의 예제 코드처럼 각 예외가 발생할 수 있는 블록을 개별적으로 묶어서 try-catch-finally구문으로 작성이 가능하다.

```java
public class TryCatchFinallyExample {
    public static void main(String[] args) {
        String data1 = null;
        String data2 = null;

        try {
            data1 = args[0];
            data2 = args[1];
            System.out.println("args[0]" + data1);
            System.out.println("args[1]" + data2);

            int value1 = Integer.parseInt(data1);
            int value2 = Integer.parseInt(data2);
            int result = value1 + value2;
            System.out.println(data1 + "+" + data2 + "=" + result);
        } catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("usage:");
            System.out.print("java TryCatchFinallyExample ");
            System.out.println("argument1 argument2");
            return;
        } catch (NumberFormatException e) {
            System.out.println("Cannot format to number.");
        } finally {
            System.out.println("Enter the proper arguments. (number format)");    
        }
    }
}
```

이렇게 catch 블록을 여러개를 둠으로써 다중 catch를 작성할 수도 있다. 하지만 이때 주의할 점은 상위 클래스의 catch 블록이 위에 있다면 아래에 있는 하위 클래스의 catch 블록은 실행되지 않는다. 따라서 상위 클래스의 catch 블록을 가장 마지막에 배치하는 것이 바람직하다. **자식 예외(더 구체적인 예외)를 위쪽에, 부모 예외(더 막연한)를 아래쪽에 배치한다.**

```java
public class TryCatchFinallyExample {
    public static void main(String[] args) {   
        try {
        		// 예외 발생 가능한 코드
        } catch(ArrayIndexOutOfBoundsException e) {
        		// ArrayIndexOutOfBoundsException 예외 처리 코드    
        } catch (NumberFormatException e) {
        		// NumberFormatException 예외 처리 코드
        } catch (Exception e) {
        		// Exception 예외 처리 코드 (최상위 예외 객체)
        } finally {
        		// 예외 발생 여부와 관계없이 실행할 코드
        }
    }
}
```

만약 예외 처리 코드가 동일하다면 굳이 따로 catch 블록을 작성할 필요가 없다. 따라서 이때는 catch문의 괄호안에 처리할 예외 객체를 모두 병렬적으로 넣어 주면 된다.

```java
public class TryCatchFinallyExample {
    public static void main(String[] args) {   
        try {
        		// 예외 발생 가능한 코드
        } catch(ArrayIndexOutOfBoundsException | NumberFormatException e) {
            // ArrayIndexOutOfBoundsException 및 NumberFormatException 예외 처리 코드
        }
    }
}
```

### try-with-resources (자동 리소스 닫기)

자바7에서 새로 추가된 try-with-resource를 사용하면 예외 발생 여부와 상관없이 사용했던 리소스 객체의 `close()`를 알아서 호출해주어서 안전하게 리소스를 닫아준다.

```java
FileInputStream fis = null;
try {
		fis = new FileInputStream("file.txt");
		...
} catch(IOException e) {
		...
} finally {
		if (fis != null) {
				try {
						fix.close();
				} catch(IOException e) {}
		}
}
```

이전에는 FileInputStream이 null이 아니라면 `close()` 해주는데 또 IOException을 catch 해주는 등 다소 번거로운 코드 작성을 해야했다. 하지만 이러한 작업이 반복되면서 자동으로 리소스를 닫아주는 문법이 나오게 되었다.

```java
try(
    FileInputStream fis = new FileInputStream("input.txt");
    FileOutputStream fos = new FileOutputStream("output.txt")
) {
		...
} catch(IOException e) {
		...
}
```

이런식으로 작성하면 예외 발생 여부와 상관없이 자동으로 `fis.close()`, `fos.close()`가 호출된다.

### throws (예외 떠넘기기)

메서드 내부에서 예외가 발생할 수 있는 코드를 작성할 때 try-catch 블록을 예외를 처리하는 것이 기본이지만, 경우에 따라서는 메서드를 호출한 곳으로 예외를 떠넘길 수도 있다. 이때 사용하는 키워드가 throws이다. 

하지만 예외를 계속 떠넘기다 보면 결국엔 main 메서드까지 가게 된다. 최종적으로 main에서도 throws 하게 되면 JVM에게 그냥 책임을 떠넘기는 꼴이 된다. 보통 컴파일 예외는 잘 모르겠지만 실행 예외의 경우에는 throws를 하더라도 해당 메서드의 호출부에서 중간에 try-catch로 적절한 처리해주는 것이 바람직하다.

```java
public class ThrowsExample {
    public static void main(String[] args) {
        ThrowsExample throwsExample = new ThrowsExample();
        throwsExample.method1();
    }

    public void method1() {
        try {
            method2();
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException occurred : " + e.getMessage());
            // 예외 처리 코드
        }
    }

    public void method2() throws ClassNotFoundException {
        Class clazz = Class.forName("java.lang.String2");
    }
}
```

```
ClassNotFoundException occurred : java.lang.String2
```

### throw (예외 발생시키기)

자바에서는 코드 상에서 예외를 발생시키는 키워드를 제공한다. throw라는 키워드로 예외를 발생시킬 수가 있는데, 이는 throws와는 그 의미가 완전히 다르므로 주의해야한다.

```java
public void method() throws UnauthorizedUserAccessException {
    if (!user.isMatchedPassword(password)) {
        throw new UnauthorizedUserAccessException("Password does not match!");
    }  
}
```

위와 같이 new 키워드로 사용자 정의 예외 또는 자바 표준 예외 객체를 생성하여 발생시킬 수 있는데, 이렇게 발생시킨 예외는 반드시 호출한 곳으로 떠넘겨주어서 처리해 주어야한다.

스프링에서는 따로 throws로 넘겨주지 않아도 애노테이션 맵핑을 통해서 핸들러가 제어하도록 설정이 가능하다. 구체적인 내부동작은 아직 잘 모르겠지만 아마도 자바에서와 마찬가지로 throws로 넘겨주고 다시 받아서 try-catch하는 부분이 있을 것이다.

## 커스텀한 예외 만드는 방법

프로그램을 개발하다 보면 자바 표준 API에서 제공하는 예외 클래스만으로는 다양한 종류의 예외를 표현하는데 한계가 있다. 예를 들어, QnA 게시판을 만드는데 허가되지 않은 사용자의 접근을 막으려면 그에 대한 예외가 필요한데, 이러한 예외가 자바 표준 API에는 존재하지 않는다. 따라서 허가되지 않은 사용자의 접근 예외와 같이 서비스와 관련된 예외를 애플리케이션 예외(Application Exception)라고 한다. 애플리케이션 예외는 개발자가 직접 정의해서 만들어야 하므로 사용자 정의 예외라고도 한다.

### 커스텀 예외 선언

사용자 정의 예외는 컴파일러가 체크하는 일반 예외로 선언할 수도 있고, 실행 예외로 선언할 수도 있다. 일반 예외로 선언할 경우 Exception을 상속하면 되고, 실행 예외로 선언할 경우에는 RuntimeException을 상속하면 된다.

```java
public class UnauthorizedUserAccessException extends RuntimeException {
    public UnauthorizedUserAccessException() {
        super("Unauthorized user cannot access the contents.");
    }

    public UnauthorizedUserAccessException(String message) {
        super(message);
    }
}
```

