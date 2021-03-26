# [Week7] 패키지
## package 키워드

### 패키지

패키지란 클래스의 묶음이다. 자바에서 패키지는 클래스나 인터페이스를 포함시킬 수 있으며, 서로 연관있는 클래스끼리 그룹 단위로 묶어 놓음으로써 클래스를 효율적으로 관리할 수 있게 된다. 같은 이름의 클래스라도 서로 다른 패키지에 존재하는 것이 가능하므로, 다른 클래스와 충돌을 피할 수 있다.

클래스가 물리적으로 하나의 클래스파일(.class)인 것과 마찬가지로 패키지는 물리적으로 하나의 디렉토리에 해당한다.

### 패키지 네이밍 컨벤션

```java
package com.howtodoinjava.webapp.controller;
package com.company.myapplication.web.controller;
package com.google.search.common;
```

기본적으로 위와 같이 패키지 이름은 모두 소문자로 구성되며, 도메인 이름 (예 : com, org, net 등)으로 시작하는 단어 그룹이어야한다. 패키지 이름의 나머지 부분은 조직의 내부 명명 규칙에 따라 다를 수 있다. 보통 조직의 사이트 주소를 반대로 기입하면 된다.

**여기에서 주의할 점은 패키지 네임에 `java`나 `javax`의 키워드가 들어가면 안된다는 것이다. 왜냐하면 자바 언어 자체의 패키지가 이미 java 또는 javax 이기 때문이다.**

- https://howtodoinjava.com/java/basics/java-naming-conventions/#:~:text=Package%20names%20must%20be%20a,organization's%20own%20internal%20naming%20conventions.
- https://docs.oracle.com/javase/tutorial/java/package/namingpkgs.html

### 패키지 네임을 포함한 컴파일

```sh
javac -d . Package.java
```

위 명령을 실행하면 현재 경로를 root로 하여 디렉터리가 생성되며, 패키지 내부에 Package.class 파일이 생성된다. 이 파일을 실행하기 위해서는 해당 프로젝트의 루트 디렉토리를 클래스패스에 등록해야한다. `com.study.whiteship` 패키지를 사용한다면, `com`보다 상위 디렉터리를 클래스패스로 등록해야 컴파일러가 패키지를 찾아갈 수 있다.

```
└── com
    └── study
        ├── blackship
        │   ├── Package.class
        │   └── Package.java
        └── whiteship
            ├── Package.class
            └── Package.java
```

위와 같은 패키지 구조를 가질 때, 같은 이름의 클래스를 서로 다른 패키지에 정의함으로써 구분이 가능하다.

```java
package com.study.blackship;

public class Package {
    public static void printPackage() {
        System.out.println("This is blackship package");
    }
}
```

```java
package com.study.whiteship;

public class Package {
    public static void printPackage() {
        System.out.println("This is whiteship package");
    }
    public static void main(String[] args) {
        com.study.blackship.Package.printPackage();
        printPackage();
    }
}
```

이 코드를 아래의 명령으로 실행해볼 수 있다.

```sh
$ java com.study.whiteship.Package
This is blackship package
This is whiteship package
```

그러면 위와 같은 결과를 확인할 수 있다.

### FQCN(Fully Qualified Class Name)

위의 예제에서 `java com.study.whiteship.Pacage` 라는 명령으로 Package.class 파일을 실행할 수 있었다. 이처럼 같은 이름의 클래스가 서로 다른 패키지에 존재할 수 있으므로, 해당 클래스에 대해 **고유한 이름이 필요하다.** 이때 패키지로 해당 클래스를 정확히 구별할 수 있으므로, 자바에서는 이를 기준으로 구별한다. 이처럼 패키지 네임을 포함한 고유한 이름을 FQCN(Fully Qualified Class Name) 이라고 한다.

---

## import 키워드

소스코드를 작성할 때 다른 패키지의 클래스를 사용하려면 패키지명이 포함된 클래스 이름을 사용해야 한다. 하지만 매번 패키지명을 붙여서 작성하는 것은 매우 번거롭다. 따라서 이를 import문으로 일일이 지정해줄 필요가 없도록 할 수 있다.

>import문은 프로그램의 성능에 전혀 영향을 미치지 않는다. import문을 많이 사용하면 컴파일 시간이 아주 조금 더 걸릴 뿐이다.
>
>`#include` directive makes the compiler go to the C/C++ standard library and copy the code from the header files into the program. As a result, the program size increases, thus wasting memory and processor’s time. 
>
>`import` statement makes the JVM go to the Java standard library, execute the code there , and substitute the result into the program. Here, no code is copied and hence no waste of memory or processor’s time.hence import is an efficient mechanism than #include.
>
>reference : [What is difference between import and include](https://www.youth4work.com/Talent/Core-Java/Forum/118742-What-is-difference-between-import-and-in#:~:text=Import%20will%20paste%20the%20compiled,they%20are%20to%20be%20compiled.&text=1.-,%23include%20directive%20makes%20the%20compiler%20go%20to%20the%20C%2FC%2B%2B,header%20files%20into%20the%20program.)

### import 선언

```java
public class ImportTest {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4};
        String arrStr = java.util.Arrays.toString(arr);
        System.out.println(arrStr);
    }
}
```

위의 코드를 import문을 사용하여 좀 더 편리하게 사용할 수 있다. 단, 같은 이름의 메서드가 패키지의 다른 클래스에도 존재한다면 클래스 이름으로 명시를 하여 최소한의 구분을 해주어야 한다.

```java
import java.util.Arrays;

public class ImportTest {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4};
        String arrStr = Arrays.toString(arr);
        System.out.println(arrStr);
    }
}
```

기본적으로 `import {상위 패키지}.{하위 패키지}.{하위 패키지 ...}.{클래스 이름}` 의 형태로 선언한다. 하지만 import 문을 여러번 사용하는 대신 `*`를 이용하여 한 문장으로 처리할 수 있다.

예를 들어, 아래와 같이 세 개의 import문이 있다면

```java
import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;
```

`*`을 이용하여 하나의 문장으로 만들 수 있다.

```java
import java.util.*;
```

import문 에서는 `*`을 사용하였을 때 컴파일러는 해당 패키지에서 일치하는 클래스이름을 일일이 찾아야하므로 컴파일 시간은 아주 약간 더 걸릴 수 있겠지만, **실행 시 성능상의 차이는 전혀 없다.**

한 가지 주의할 점은 import문에서 클래스의 이름 대신 '*'를 사용하는 것이 하위 패키지의 클래스까지 포함하는 것은 아니라는 것이다.

```java
import java.util.*;
import java.text.*;
```

그러므로 위의 두 문장 대신 `import java.*;` 와 같이 사용할 수는 없다. 반드시 하위 패키지를 명시해주어야 한다.

### static import 문

import문과 마찬가지로 static import를 사용하면 static멤버를 호출할 때 클래스 이름을 생략할 수 있다. 특정 클래스의 static멤버를 자주 사용할 때 편리하며, 코드도 간결해진다.

```java
import static java.lang.Integer.*;
import static java.lang.Math.random;
import static java.lang.System.out;
```

예를 들어, 아래의 코드와 같이 static import문을 사용하였다면, 앞쪽의 패키지 부분은 생략이 가능하다. 

```java
import static java.lang.Math.*;
import static java.lang.System.out;

public class StaticImportTest {
    public static void main(String[] args) {
        // System.out.println(Math.random());
        out.println(random());
        // System.out.println(Math.PI);
        out.println(PI);
    }
}
```

> 일반적인 import 단축키(ctrl + space, command + space)와 달리 ctrl을 누르고 스페이스바를 한 번이나 두 번 누른다. 그러면 오른쪽에 형광등 표시가 뜨는데 마우스로 클릭하면 Import statically가 뜨는데, 이것을 클릭하면 된다.
>
> 또 다른 방법으로 alt + enter 또는 option + enter 를 눌러서 바로 제안사항을 선택할 수 있다. 참고로 이클립스에서는 static import가 더 불편하다고 한다.
>
> - https://blog.jetbrains.com/ko/2020/03/11/top-15-intellij-idea-shortcuts_ko/

---

## 클래스패스(CLASSPATH)

클래스패스는 컴파일러(javac)나 JVM등이 클래스의 위치를 찾는데 사용되는 경로이다. 클래스패스는 컴파일 중에 또는 런타임 중에 설정할 수 있다. 메이븐과 그레이들에서 각 부분에 대한 설정을 할 수가 있다.

이 부분은 `ClassNotFoundException`의 주범인데, 계속 프로젝트를 빌드 및 실행하다가 갑자기 엔트리 포인트가 포함된 패키지 네임을 변경한다던가, 클래스의 이름을 바꾸고 그대로 실행한다던가 하면 쉽게 발생할 수 있는 예외이다.

### Gradle Classpath

```java
@Documented
@Retention(RUNTIME)
@Target({METHOD,FIELD})
public @interface Classpath
```

이런식으로 클래스패스를 설정할 수 있는 애노테이션이 지원된다.

- https://docs.gradle.org/current/javadoc/org/gradle/api/tasks/Classpath.html
- https://docs.gradle.org/current/userguide/declaring_dependencies.html

### Maven Classpath

- https://maven.apache.org/shared/maven-archiver/examples/classpath.html
- https://maven.apache.org/guides/mini/guide-maven-classloading.html
- https://maven.apache.org/plugins-archives/maven-surefire-plugin-2.12.4/examples/configuring-classpath.html

## CLASSPATH 환경변수

환경변수는 프로세스가 컴퓨터에서 동작하는 방식에 영향을 미치는 동적인 값들의 모임이다. 운영체제에서 자식프로세스를 생성할 때 참조하는 변수들이다. 즉, 운영체제라는 가장 기본이 되는 프로세스에서 사용하는 변수라고 생각할 수 있다.

CLASSPATH 환경변수는 

### 윈도우즈의 CLASSPATH 환경변수

`제어판 > 시스템 > 고급 시스템 설정 > 환경변수 > 새로 만들기` 에서 변수이름에 `CLASSPATH`를 입력하고 변수 값에 jdk의 경로를 복사/붙여넣기 하면 된다. `.;C:\jdk1.8\work`와 같이 클래스패스를 설정할 수 있다.  `;` 를 구분자로 하여 여러 개의 경로를 클래스패스에 지정할 수 있으며, 맨 앞에 `.;`를 붙여주는 이유는 현재 경로를 클래스패스에 포함시키기 위해서이다.

### macOS의 CLASSPATH 환경변수

```
export CLASSPATH=/path/to/some.jar:/path/to/some/other.jar
export CLASSPATH=$CLASSPATH:/path/to/some.jar:/path/to/some/other.jar
```

이렇게 추가할 수도 있지만, 아래와 같은 명령으로도 설정 가능하다.

```sh
setenv CLASSPATH (insert your classpath here)
```

- https://stackoverflow.com/questions/1675765/adding-to-the-classpath-on-osx
- https://whitepaek.tistory.com/28
- https://www.java67.com/2015/11/how-to-set-javahome-path-in-mac-os-x.html

---

## -classpath 옵션

Java `-cp` 또는 `-classpath`는 JVM(Java Virtual Machine) 또는 자바 컴파일러의 매개 변수이다. `-cp` 또는 `CLASSPATH`는 `Java` 명령에 대한 옵션으로 사용된다. 사용자가 정의한 클래스 및 패키지의 위치를 지정하는 JVM 또는 자바 컴파일러의 매개 변수이다. `CLASSPATH` 매개 변수는 명령 행 또는 환경 변수를 통해 설정할 수 있다.

> Specifying -classpath or -cp overrides any setting of the CLASSPATH environment variable.

-cp 매개 변수는 클래스 파일을 검색 할 디렉토리, JAR 아카이브 및 ZIP 아카이브 목록을 지정한다.

예를 들어, 자바 JRE가 utility.myapp 패키지에서 Cool.class라는 클래스를 찾기를 원한다고 가정한다. 해당 디렉토리의 경로가 `C:\java\MyClasses\utility\myapp`이면 클래스 경로를 설정하여 `C:\java\MyClasses`을 담도록 한다.

`java -classpath C:\java\MyClasses utility.myapp.Cool`

- https://stackoverflow.com/questions/2161468/java-classpath-option
- https://techmonitor.ai/what-is/what-is-java-cp-4926798#:~:text=The%20%2Dcp%2C%20or%20CLASSPATH%2C,or%20through%20an%20environment%20variable.
- https://docs.oracle.com/javase/8/docs/technotes/tools/windows/classpath.html

---

## 접근 지시자(접근 제어자, 접근 제한자, Access Modifier)

접근 제어자는 멤버 또는 클래스에 사용되며, 해당 멤버 또는 클래스를 외부에서 접근하지 못하도록 제한하는 역할을 한다. 기본적으로 접근 제어자를 아무것도 명시하지 않으면 default 접근 제어자를 가진다.

접근 제어자가 사용될 수 있는곳은 클래스, 멤버변수, 메서드, 생성자이다. (지역변수는 접근 제어자의 대상이 아니다)

- priavate : 같은 클래스 내에서만 접근이 가능하다.
- defualt : 같은 패키지 내에서만 접근이 가능하다. (package-private)
- protected : 패키지와 관계없이 자식 클래스에서 접근이 가능하다. (inheritence-private)
- public : 모든 접근을 허용한다.

`public > protected > (default) > private` 정도의 접근 수준을 가진다.

### 접근 제어자의 목적 - 캡슐화와 정보 은닉

- 외부로부터 데이터를 보호하기 위해서
- 외부에는 불필요한, 내부적으로만 사용되는 부분을 감추기 위해서

### 생성자와 접근 제어자 - 싱글톤

생성자에 접근 제어자를 사용함으로써 인스턴스의 생성을 제한할 수 있다. 보통 생성자의 접근 제어자는 클래스의 접근 제어자와 같지만, 다르게 지정할 수 있다.

생성자의 접근 제어자를 private으로 지정하면, 외부에서 생성자에 접근할 수 없으므로 인스턴스를 생성할 수 없게 된다. 그래도 클래스 내부에서는 인스턴스를 생성할 수 있다. 하지만 대부분 static 메서드만을 포함하므로 인스턴스를 생성하는 것이 의미가 없는 경우가 많다.

```java
class Singleton {
		private static final Singleton s = new Singleton();

		private Singleton() {
				...
		}
		
		public static Singleton getInstance() {
				return s;
		}		
}
```

이렇게 getInstance() 메서드를 통해 클래스의 인스턴스를 얻어낼 수 있지만, static으로 단 하나의 인스턴스를 가지므로, 이러한 형태의 패턴을 싱글톤 패턴이라고 한다. 추가적으로 final 키워드를 통해서 새로운 참조값이 할당되지 않도록 막는다는 의미를 명시한다. 보통 유틸리티 클래스를 이런 방식으로 구현한다.

