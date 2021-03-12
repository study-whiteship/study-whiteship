# 목표

- 자바가 제공하는 제어문을 학습하세요.

# 학습할 것 (필수)

- 선택문

- 반복문

## 선택문

```java
switch(num) {
    case 1 :
        Sysetem.out.println("1");
    case 3 :
        Sysetem.out.println("3");
    case 4 :
        Sysetem.out.println("4");
}
```

위처럼 선언했을때 바이트 코드를 보면

```
  public static void what(int);
    Code:
       0: iload_0
       1: tableswitch   { // 1 to 3
                     1: 28
                     2: 36
                     3: 44
               default: 52
          }
      28: getstatic     #13                 // Field java/lang/System.out:Ljava/io/PrintStream;
      31: ldc           #19                 // String 1
      33: invokevirtual #21                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
      36: getstatic     #13                 // Field java/lang/System.out:Ljava/io/PrintStream;
      39: ldc           #27                 // String 2
      41: invokevirtual #21                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
      44: getstatic     #13                 // Field java/lang/System.out:Ljava/io/PrintStream;
      47: ldc           #29                 // String 3
      49: invokevirtual #21                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
      52: getstatic     #13                 // Field java/lang/System.out:Ljava/io/PrintStream;
      55: ldc           #31                 // String hello
      57: invokevirtual #21                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
      60: return
}
```

위처럼 보면 우리가 case 1 : 이렇게 써줬던 변수들도 스레드의 lva에 값들을 저장해 줌을 알 수 있다.
단순히 비교하는게 아니라 값을 저장 한다는 사실을 처음 알았다. 저게 길어지면 저장해지는 것도 많을테니 switch 문이 참 별로구나
이런 생각이 들었다.

그리고 테이블 스위치에 1 : 28 이렇게 되있는것들은 해당 번호의 바이트 코드를 실행시켜라 이런의미였다~!

그니까 저 테이블에 도달하는 시점에 저 바이트 코드들이 실행되는 구나 싶었다.

## 반복문

아래는 10 개를 for-Each 로 돌릴때이다. 향상된 for 문 이지만 아래정도의 바이트코드가 나온다.
알고리즘을 풀대 왜 for 를 겹쳐쓰면 효율이 안좋을지 알것 같다.

```
public class Loop {
  public Loop();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main();
    Code:
       0: bipush        10
       2: newarray       int
       4: dup
       5: iconst_0
       6: iconst_1
       7: iastore
       8: dup
       9: iconst_1
      10: iconst_2
      11: iastore
      12: dup
      13: iconst_2
      14: iconst_3
      15: iastore
      16: dup
      17: iconst_3
      18: iconst_4
      19: iastore
      20: dup
      21: iconst_4
      22: iconst_5
      23: iastore
      24: dup
      25: iconst_5
      26: bipush        6
      28: iastore
      29: dup
      30: bipush        6
      32: bipush        7
      34: iastore
      35: dup
      36: bipush        7
      38: bipush        8
      40: iastore
      41: dup
      42: bipush        8
      44: bipush        9
      46: iastore
      47: dup
      48: bipush        9
      50: bipush        10
      52: iastore
      53: astore_0
      54: aload_0
      55: astore_1
      56: aload_1
      57: arraylength
      58: istore_2
      59: iconst_0
      60: istore_3
      61: iload_3
      62: iload_2
      63: if_icmpge     85
      66: aload_1
      67: iload_3
      68: iaload
      69: istore        4
      71: getstatic     #7                  // Field java/lang/System.out:Ljava/io/PrintStream;
      74: iload         4
      76: invokevirtual #13                 // Method java/io/PrintStream.print:(I)V
      79: iinc          3, 1
      82: goto          61
      85: return
}
```
