# [Week10] 멀티쓰레드 프로그래밍

## Thread 클래스와 Runnable 인터페이스

- References:
  - https://wikidocs.net/230
  - https://raccoonjy.tistory.com/15

## 쓰레드의 상태

### 스레드의 상태 6가지

| 상태      | 열거 상수     | 설명                                                         |
| --------- | ------------- | ------------------------------------------------------------ |
| 객체 생성 | NEW           | 스레드가 생성되었지만 아직 start() 메서드가 호출되지 않은 상태<br />스레드가 아직 실행할 준비가 되지 않음 |
| 실행 대기 | RUNNABLE      | 실행상태로 언제든지 갈 수 있는 상태<br />스레드가 실행되고 있거나 실행준비되어 스케줄링은 기다리는 상태 |
| 일시 정지 | WAITING       | 다른 스레드가 통지할 때까지 기다리는 상태<br />다른 스레드가 notify(), notifyAll()을 불러주기 기다리고 있는 상태(동기화) |
| 일시 정지 | TIMED_WAITING | 주어진 시간동안 기다리는 상태<br />스레드가 sleep(n) 호출로 인해 n 밀리초동안 잠을 자고 있는 상태 |
| 일시 정지 | BLOCK         | 사용하고자 하는 객체의 lock이 풀릴 때까지 기다리는 상태<br />스레드가 I/O 작업을 요청하면 자동으로 스레드를 BLOCK 상태로 만든다. |
| 종료      | TERMINATED    | 실행을 마친 상태<br />스레드가 종료한 상태                   |

## 쓰레드의 우선순위

멀티 스레드는 동시성(Concurrency) 또는 병렬성(Paralleism)으로 실행되기 때문에 이 두 개념을 잘 이해해야한다. 이것에 대한 기본적인 배경은 **하나의 코어는 한번에 단 하나의 스레드만 실행할 수 있다**는 것이다.

- 동시성 - 멀티 작업을 위해 **하나의 코어**에서 멀티 스레디가 **번갈아가며** 실행하는 성질
- 병렬성 - 멀티 작업을 위해 **멀티 코어**에서 개별 스레드를 동시에 실행하는 성질

### 스레드 스케줄링

스레드의 개수가 코어의 수보다 많을 경우, 스레드를 어떤 순어세 의해 동시성으로 실행할 것인가를 결정해야 하는데, 이것을 스레드 스케줄링이라고 한다. 스레드 스케줄링에 의해 스레드들은 아주 짧은 시간에 번갈아가면서 그들의 `run()` 메서드를 조금씩 실행한다. 이는 멀티 프로세스에서의 타임 쉐어링 기법과 유사하다.

자바의 스레드 스케줄링은 우선순위(Priority)방식과 순환 할당(Round-Robin) 방식을 사용한다. 

- 우선순위 방식 - 우선순위가 높은 스레드가 실행 상태를 더 많이 가지도록 스케줄링, 스레드 객체에 우선 순위 번호를 부여할 수 있어서 개발자가 코드로 제어 가능
- RR 방식 - 시간 할당량(Time Slice)을 정해서 하나의 스레드를 정해진 시간만큼만 실행하고 다시 다른 스레드를 실행, JVM이 알아서 수행하므로 코드로 제어 불가능

우선순위 방식에서 우선순위는 1에서부터 10까지 부여되는데, 1이 가장 낮은 우선순위이고, 10이 가장 높다. 우선순위를 직접 부여하지 않으면 기본적으로 모든 스레드는 5의 우선순위를 가진다. 자바에서는 자주 사용하는 우선 순위를 지정하기 위한 상수를 제공한다.

- static final int MAX_PRIORITY : 우선순위 10 (가장 높은 우선 순위)
- static final int MIN_PRIORITY :  우선순위 1 (가장 낮은 우선 순위)
- static final int NORM_PRIORITY : 우선순위 5 (보통의 우선 순위)

```java
thread.setPriority(우선순위 번호);
```

### 예제 코드

```java
public class CalculateThread extends Thread {
    public CalculateThread(String name) {
        setName(name);
    }

    public void run() {
        for (int i = 0; i < 200000000; i++) {
        }
        System.out.println(getName());
    }
}
```

```java
public class PriorityExample {
    public static void main(String[] args) {
        for(int i = 1; i <= 10; i++) {
            Thread thread = new CalculateThread("Thread" + i);
            if (i != 10) {
                thread.setPriority(Thread.MIN_PRIORITY);
                
            } else {
                thread.setPriority(Thread.MAX_PRIORITY);
            }
            thread.start();
        }
    }
}
```

## Main 쓰레드

## 동기화

### 공유 객체 사용 시 발생가능한 문제

싱글 스레드 프로그램에서는 한 개의 스레드가 객체를 독차지해서 사용하면 되지만, 멀티 스레트 프로그램에서는 스레드들이 객체를 공유해서 작업해야 하는 경우가 있다. 이 경우, 스레드 A를 사용하던 객체가 스레드 B에 의해 상태가 변경될 수 있기 때문에 스레드 A가 의도했던 것과는 다른 결과를 산출할 수가 있다. 따라서 가장 기본적인 해결 방법은 하나의 스레드가 공유 객체를 사용하는 동안 다른 스레드가 접근하지 못 하도록 lock을 걸어서 막는 것이다.

이 부분은 데이터 베이스에서 트랜잭션의 네 가지 특징(ACID) 중 고립성(Isolatio)과 매우 유사하다. DB에서 한 트랜잭션이 실행하는 동안 다른 트랜잭션이 접근하지 못하도록 lock를 거는 것 처럼, 자바의 멀티스레딩에서도 lock을 걸어서 다른 스레드가 접근하지 못하도록 할 수 있다.

### 동기화 메서드 및 동기화 블록

스레드가 사용 중인 객체를 다른 스레드가 변경할 수 없도록 하려면 스레드 작업이 끝날 때까지 객체에 잠금을 걸어서 다른 스레드가 사용할 수 없도록 해야한다. 멀티 스레드 프로그램에서 단 하나의 스레드만 실행할 수 있는 코드 영역을 임계 영역(Critical Section)이라고 한다. 자바는 임계 영역을 지정하기 위해 동기화(synchronized) 메서드와 동기화 블록을 제공한다. synchronized 키워드를 붙이면 설정이 가능한데, 이는 인스턴스와 스태틱 메서드 둘 다 붙일 수 있다.

```java
public class Calculator {
    private int memory;

    public int getMemory() {
        return memory;
    }

    public synchronized void setMemory(int memory) {
        this.memory = memory;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        System.out.println(Thread.currentThread().getName() + " : " + this.memory);
    }
}
```

```
UserA : 50
UserB : 50
```

```
UserA : 100
UserB : 50
```

## 데드락

> **교착 상태**(膠着狀態, 영어: deadlock)란 두 개 이상의 작업이 서로 상대방의 작업이 끝나기 만을 기다리고 있기 때문에 결과적으로 아무것도 완료되지 못하는 상태를 가리킨다. 예를 들어 하나의 사다리가 있고, 두 명의 사람이 각각 사다리의 위쪽과 아래쪽에 있다고 가정한다. 이때 아래에 있는 사람은 위로 올라 가려고 하고, 위에 있는 사람은 아래로 내려오려고 한다면, 두 사람은 서로 상대방이 사다리에서 비켜줄 때까지 하염없이 기다리고 있을 것이고 결과적으로 아무도 사다리를 내려오거나 올라가지 못하게 되듯이, 전산학에서 교착 상태란 다중 프로그래밍 환경에서 흔히 발생할 수 있는 문제이다. 

> 데드락이란, 둘 이상의 쓰레드가 lock 을 획득하기 위해 기다리는데, 이 lock 을 잡고 있는 쓰레드도 똑같이 다른 lock 을 기다리며 서로 블록 상태에 놓이는 것을 말한다. 데드락은 다수의 쓰레드가 같은 lock 을, 동시에, 다른 명령에 의해, 획득하려 할 때 발생할 수 있다.
>
> 예를 들자면, thread1 이 A 의 lock 을 가지고 있는 상태에서 B 의 lock 을 획득하려 한다. 그리고 thread2 는 B 의 lock 을 가진 상태에서 A 의 lock 을 획득하려 한다. 데드락이 생긴다. thread1 은 절대 B 의 lock 을 얻을 수 없고, 마찬가지로 thread2 는 절대 A 의 lock 을 얻을 수 없다. 두 쓰레드 중 어느 쪽도 이 사실을 모를 것이며, 쓰레드들은 각자의 lock - A 와 B 의 - 을 가진 채로 영원히 블록 상태에 빠진다. 이러한 상황이 데드락이다.

### 데드락 발생 조건

1. 상호배체 : 한 자원에 대한 여러 프로세스의 동시 접근 불가
2. 점유와 대기 : 자원을 가지고 있는 상태에서 다른 프로세스가 사용하고 있는 자원의 반납을 기다리는 것
3. 비선점 : 다른 프로세스의 자원을 강제로 가져올 수 없음
4. 환형대기 : 각 프로세스가 순환적으로 다음 프로세스가 요구하는 자원을 가지고 있는 상황

### 데드락 해결 방법

| 방법                       | 설명                                                         |
| -------------------------- | ------------------------------------------------------------ |
| **교착 상태 예방 및 회피** | 교착 상태가 되지 않도록 보장하기 위하여 교착 상태를 예방하거나 회피하는 프로토콜을 이용하는 방법 |
| **교착 상태 탐지 및 회복** | 교착 상태가 되도록 허용한 다음에 회복시키는 방법             |
| **교착 상태 무시**         | 대부분의 시스템은 교착 상태가 잘 발생하지 않으며, 교착 상태 예방, 회피, 탐지, 복구하는 것은 비용이 많이 든다. |

### 데드락 예방 방법

네 가지 발생 조건 중에 한 가지만 끊어버리면 된다.

- 상호배제 조건의 제거
- 점유와 대기 조건의 제거
- 비선점 조건의 제거
- 순환 대기 조건의 제거

이 교착 상태의 해결 방법들은 자원 사용의 효율성이 떨어지고 비용이 많이 드는 문제점이 있다.

### 데드락 회피 방법

자원이 어떻게 요청될지에 대한 추가정보를 제공하도록 요구하는 것으로 시스템에 circular wait가 발생하지 않도록 자원 할당 상태를 검사한다.

교착 상태 회피하기 위한 알고리즘으로 크게 두가지가 있다.

1) 자원 할당 그래프 알고리즘 (Resource Allocation Graph Algorithm)
2) 은행원 알고리즘 (Banker’s algorithm)

- References:
  - https://parkcheolu.tistory.com/19
  - https://rightnowdo.tistory.com/entry/JAVA-concurrent-programming-%EA%B5%90%EC%B0%A9%EC%83%81%ED%83%9CDead-Lock
  - https://nowonbun.tistory.com/300
  - https://junsday.tistory.com/32
  - https://travislife.tistory.com/19
  - https://wakestand.tistory.com/253
  - https://includestdio.tistory.com/12