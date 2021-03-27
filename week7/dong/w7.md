# 7주차

- 목표 : 자바의 패키지에 대해 학습하세요.
- 학습할 것 (필수)
  - package 키워드
  - import 키워드
  - 클래스패스
  - CLASSPATH 환경변수
  - -classpath 옵션
  - 접근지시자
- 참고
    ```
    패키지 : https://studymake.tistory.com/428
    패키지 : https://m.blog.naver.com/29java/70188825312

    ```
- 7주차 수업 후 리뷰사항
  - FQCN
    - fully qualified class name : 클래스가 속한 패키지명을 모두 포함한 이름(풀네임)을 말함.
    - 예시
    ```
    String s = new String(); // 일반적으로 사용
    java.lang.String s = new java.lang.String(); //FQCN
    ```
    - 이런짓을 왜하나? 싶겠지만 프로젝트가 거대해지면, 이름이 똑같은 클래스가 import될수 있는데, 이럴때는 꼭 FQCN을 사용해야한다. 아니라면 에러발생
    - 여기서 더 알아볼것은 import static까지 공부해봐라
  - 클래스패스
    - 환경변수로도, 컴파일타임 둘다 쓸수 있다.
    - 공부한답시고 CLASSPATH 환경변수 설정했다면 삭제하기!, 그 이유는 이게 절대경로로 박혀있으면 나중에 라이브러리가 꼬일수 있다.(server 배포할때나 쓰는 옵션)
  - 메이븐 그레이들 공부해라
  - "java.lang" 패캐지는 왜 한번도 임포트를 안했는데 String 클래스를 쓸수 있는걸까?
    - 하수 : 자바 언어가 기본 지원하니까!
    - 고수 : 내가 읽어서 사용하려는 클래스가 부모클래스에 로딩되어있으면, 자식클래스는 자동으로 사용할수 있다(이걸 상속이라고 해도 되나?)
      - 자바언어는 모든 클래스/오브젝트 최상위에 java클래스를 상속받는 구조로 되어있으며, JVM 최상단에 부트스트래퍼가 미리 읽기 때문에 java.lang 을 임포트 하지 않아도 여러분이 시작하는 클래스에서는 임포트하지않아도 이미 임포트가 되어있다!
  - 패키지에는 두종류가 있다
    - 빌트인 패키지
    - 사용자 패키지
  - 자바 Quiz 패키지 : O/X로 생각해보세요
    - 1.모든 클래스는 어떤 패키지에 속해 있다?
      - O : 따로 지정안하면 디폴트패키지, 언네임드 패키지에 포함됨
    - 2.같은 파일 안에 선언된 클래스들은 각기 다른 패키지에 포함시킬수 있다?
      - X : 하나의 파일에는 하나의 패키지만 포함된다
    - 3.폴더를 만들고 새로운 자바파일을 만들어 클래스를 작성하면 새로운 패키지를 꼭 만들어야한다
    - 4.새로운 폴더를 만들면 반드시 새로운 패키지를 만들어아햔다
  - static import
    - 로그함수 등 만들때 쓰이고
    - static import를 사용하면 System.out.pirint() 를 out.pirint()으로 줄일수 있다!
      - import static java.lang.System.*;
<br>
<br>
<br>
<br>
<br>
<br>

# package 키워드
- 자바의 package는 클래스들을 묶어(package) 해 준다는 의미
- 하나의 디렉토리에 같이 담겨있는 클래스(##.java or ##.class) 파일들을 지칭
- package는 하나의 물리적인 디렉토리 내부에 포함된 자바소스코드 파일들을 지칭한다.
  - 마치 Public Class가 (Interface, Enum을 포함한)물리적으로 하나의 소스코드 파일에 포함된 것 처럼
- 패키지는 클래스들의 묶음이고, 패키지가 모여 프로젝트를 만든다
- ![cls-pkg-prj]()

<br>
<br>

## 왜 package 를 사용할까?
  - 비슷/유사한 클래스끼리 묶어서 관리할수 있고
  - 패키지가 다르다면 동일한 클래스명을 쓸 수 있다 (예를들어 Launcher.java )
  - 예를들어서 "project PUPU"를 진행하기 위해서 A회사, B회사가 참여를 했는데,각 회사마다 Init ,Serializer 등등.. 같은 흔한 이름의 클래스명은 중복될수 있는데, 이를 방지하고자 package 라는 개념이 있는듯 하다.
- 패키지같은거 없이도 잘 돌아가는건? : 따로 패키지 선언을 해주지않으면 이름 없는 패키지(unnamed package)'에 속하게 되기 때문
  - 참고로 백준 온라인저지에서는 서버에서 Java Lang으로 채점을 돌리기 위해 명시된 패키지를 없애줘야 된다.  
- 한줄요악 : 프로젝트속 패키지 -> 패키지 속 클래스

<br>
<br>

## package 사용법
- 래스나 인터페이스의 소스파일(.java)의 맨 첫 줄에 아래와 같은 명령문을 적어주면 된다.
    ```java
    package 패키지명;
    ```
- 상황별로 설명해보자면
  - /gogi 디렉토리에 내부에 /chicken 디렉토리, 
  - /chicken 디렉토리에 내부에 /BBQ 디렉토리, 
  - /BBQ 디렉토리 내부에 /goldfried.java 파일이(소스코드) 위치 할때
- goldfried.java에서는 패키지로 묶을 수 있는 방법은 3가지
  - package gogi.chicken.BBQ;
  - package chicken.BBQ;
  - package BBQ;
- 3중에서 어떤걸 쓸지는 어디까지를 하나의 묶음으로 보느냐인데
  - gogi 를 기준으로 묶을때 : package gogi.chicken.BBQ;
  - chicken 를 기준으로 묶을때 : package chicken.BBQ;
  - BBQ 를 기준으로 묶을때 : package BBQ;
- 세가지 중에 뭘로 해도 상관없고 관점/방법/취향의 차이임
- 뭐든 그냥 class path로 등록만 해준다면 이용할수 있다.
- Eclipes의 경우, 프로젝트 > 패키지 > 클래스 생성 과정에서 자동으로 패키지 선언문을 삽입해 준다. 자동화 Tools의 기능.. 좋긴한데 이런 부분은 자연스럽게 생략되므로 알아두자.
- 패키지와 클래스의 차이를 알아야 더 큰프로젝트에서 일부분을 알아갈 수 있다.
- but, javac 컴파일시 '-d' 옵션을 추가하여 컴파일을 하면 자동으로 추가할 수 있다.
  - 컴파일 후 ##.class 파일이 패키지 선언에 쓰인 경로에 폴더를 만들어 자동으로 이동하게 된다. < by 자바정석>

<br>
<br>

## package 사용시 제한사항
1. Source Code 의 가장 첫 줄에 위치
   - 소속을 정의하는 것이기 때문에 

2. 하나의 Source Code마다 단 한 번! Only once! 만의 패키지 선언을 허용
   - 하나의 Source Code가 두개의 폴더에 존재할 수 없기 때문에 
   - 리눅스 심링크/하드링크랑은 어떻게 되는지 알아보기

3. 패키지 이름과 패키지에 속한 클래스가 위치한 폴더 이름은 같아야 함
   - 다르다면 에러 발생, 컴파일 타임에 컴파일러가 패키지에 정의된 내용을 토대로 폴더를 찾기 때문에.

4. 패키지 이름은 java로 시작 불가능
   - 패키지의 이름은 컴파일이 에러가 발생할 여지가 있기에 매우 민감한 사항

5. 패키지 이름은 모두 소문자로 지정
   - 클래스와 구분하기 위해서 소문자로 패키지 이름을 정하는 것을 권장, 강제는 아님

6. 자바의 예약어를 사용 불가능!
   - 패키지 이름에 중간에라도 int, static과 같은 예약어 사용 불가
   - 위반시 컴파일 에러 발생 (예- lang/java/util/javax/type 등등 포함 불가)

<br>
<br>

## 이외 TMI  
- 컨벤션인데, 관습적으로 패키지 이름에 따라 아래 의미가 있음
  - java : 자바 기본 패키지(JDK 벤더에서 개발) 
  - javax : 자바 확장 패키지(JDK 벤더에서 개발) 
  - org : 일반적으로 비영리단체(오픈 소스)의 패키지 
  - com :   일반적으로 영리단체(회사)의 패키지
- Java에서 package는 가장 상위 디렉토리(root) 에서 실행해야한다는 약속이 있음 ->> 팩트체크 중


<br>
<br>
<br>
<br>
<br>

# import 키워드

- 패키지에 속한 클래스를 이용(=접근)할 수 있도록 import 키워드를 사용한다
  - import 클래스의 패키지명을 일일이 열거하지 않아도 된다.
- "java.lang.System"에 포함되어 있는 클래스인 "System"과 "String" 같은 기본 클래스는, 한번만 import하면 쓸수 있다. 
  - import java.util.*

## import 키워드의 위치 및 사용방법
- package 아래, 클래스위에
- 예시를 들면 아래와 같다.
```java
package mypak
import java.util.*;

public class Myjab{
    //...
} 

```
- import static이라는 것도 있다던데 여기서는 pass하겠습니다..

<br>
<br>
<br>
<br>
<br>

# CLASSPATH (클래스패스)
## CLASSPATH란?
- 클래스를 찾기위한 경로 : JVM이 프로그램을 실행할 때, 클래스파일을 찾는 데 기준이 되는 파일 경로
- JVM(java runtime 또는 jre) 가 실행을 위해 모든 ##.class 파일을 찾아야 하는데, 이때 classpath에 지정된 경로를 사용
- classpath를 지정하는 두 가지 방법 
  1. 환경 변수 CLASSPATH를 사용하는 방법
  2. java runtime에 -classpath 플래그 옵션을 사용하는 방법
     - "-classpath" 옵션 사용에 대한 자세한 설명은 아래를 참고
<br>
<br>

## CLASSPATH 환경변수
- 먼저 환경변수란 운영체제에게 실행해야할 프로세스의 위치를 알려준다.
- mac, windows, Linux 모두 원리는 같고, 방법은 다르다
  - windows, Linux : https://galid1.tistory.com/211
- Linux Ubuntu 배포판 기준
  - 먼저 vi 에디터에서 환경변수 파일을 열어준다
     - 모든 계정에 일괄 적용시
      ```
        vi /etc/profile 
      ```
     - 지금 로그인중인 사용자만 적용하고 싶다면
      ```
        vi ~.bashrc
      ``` 
  - 아래 내용 추가
      ```
        export JAVA_HOME=/usr/local/java
        export CLASSPATH=.:$JAVA_HOME/jre/lib/ext:$JAVA_HOME/lib/tools.jar
        PATH=$PATH:$JAVA_HOME/bin
      ```
   - "source /etc/profile" 혹은 "source ~/.bashrc"
   - 잘 설정되었는지 확인하기 위한 명령어
        ```
        echo $JAVA_HOME
        echo $CLASSPATH
        echo $PATH
        java -version 
        ```

<br>
<br>

## -classpath 옵션
- 컴파일러가 컴파일 하기 위해서 필요로 하는 참조할 클래스 파일들을 찾기 위해서 컴파일시 파일 경로를 지정해주는 방식

<br>

- 사용 : 
  - javac -classpath PATH(파일 절대 경로)
  - javac -cp PATH(파일 절대 경로) : classpath의 단축어 cp
- 예시 :
  - 예시를 좀 깔끔하게 적고싶은데 자바 코드랑 디렉토리 파일에 따라 너무 많이 변동되므로, 필요할때마다 알아서 만들어 쓰도록 한다
  - 구글검색시 "javac", "classpath" 라는 키워드를 넣어 검색하면 된다.

<br>

## 정리하자면
- jvm에서 돌아갈 자바 클래스들(소스코드를 컴파일한 형태)의 위치를 알려줘야 하는데 
  - 환경변수는 설정파일에다가 미리 약속한대로 적어놓는것이고
  - -classpath 옵션은 실행할때마다 그때 그때 새로 알려주는 방식임

<br>
<br>
<br>
<br>
<br>

# Access Modifier (접근지시자/접근 제어자)
- 외부에서 클래스, 메소드, 인스턴스 및 클래스 변수에 접근(Read, Write) 권한을 설정할 수 있는 키워드
- 총 4가지 : public, protected, (default) , private
  - public : 어디에서나 접근가능
  - protected : 같은 패키지에 있거나, 상속받는 경우에만
  - default : 접근 제어자가 없는경우 package-private 이라고 칭하며, 같은 패키지 내부에서만 접근이 가능하다
  - private : 외부 접근 불가, 해당 클래스에서만 접근 가능
- 표로 정리해보면 (이거 좀 수정해야될부분 있다@@)

    | 키워드 |동일클래스|동일패키지|상속클래스|전체(import필요)|
    |:--:|:--:|:--:|:--:|:--:|
    |public|O|O|O|O|
    |protected|O|O|O|X|
    |(default)|O|O|X|X|
    |private|O|X|X|X|
    |||||

- 주의사항
  - static 메소드나 변수가 중복시
    - 해당 클래스에 있는 static 변수나 메소드가 우선
- 덧붙임
  - 특히 안전/보안/설정파일 관련한 것들을 특히 public쓰지말고 기본적으로는 private 속성으로 지정해주기..
  


<br>
<br>

# 끝
<br>
