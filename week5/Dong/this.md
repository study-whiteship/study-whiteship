# 자바 공부

# 5주차

## 필수 학습 리스트

- 클래스 정의하는 방법
- 객체 만드는 방법 (new 키워드 이해하기)
- 메소드 정의하는 방법
- 생성자 정의하는 방법
- this 키워드 이해하기

## 과제

- int 값을 가지고 있는 이진 트리를 나타내는 Node 라는 클래스를 정의하세요.
- int value, Node left, right를 가지고 있어야 합니다.
- BinrayTree라는 클래스를 정의하고 주어진 노드를 기준으로 출력하는 bfs(Node node)와 dfs(Node node) 메소드를 구현하세요.
- DFS는 왼쪽, 루트, 오른쪽 순으로 순회하세요.
- 실행결과
  - ![실행결과] <p align="left"><img align="center" width="550" src="https://github.com/d-h-k/Java_Study/blob/main/img/5w_asign.png" alt="d-h-k" /><p/>
  - 위치 : ../img/5w_asign.png)
- 실행 대항 트리의 구조
  - ![김트리 나와라]<p align="left"><img align="center" width="550" src="https://github.com/d-h-k/Java_Study/blob/main/img/5tree.png" alt="d-h-k" /><p/>
  - 위치 : ../img/5tree.png)
  
## 클래스를 정의하는 방법

```java
class gogi { // gogi 라는 클래스 속
	
	static int cow;	// static 키워드를 사용한 클래스 변수
	static final int fork; 	// final 키워드를 사용한 상수

	int chicken; // 인스턴스 변수
	String default_grade = "prime"; 
	private int falver; // private 키워드로 외부 접근을 제한하는 인스턴스 변수

	
    int cooking_time;

	// 생성자(Constructor)
	public gogi (){
		// default 생성자의 모습 생략 가능 /Implied(묵시적) 생성자
		// 파라미터를 입력받으며 생성해야 할때는 반드시 생성자를 Explicit(명시)해야 한다		
	} 

	//파라미터를 가진 생성자, 파라미터를 가지고 변수를 초기화한다.
	public gogi (String type, String grade){
		this.type= this.type;
		this.age = this.grade ;
	}

	// public 매서드
	public void welldone(void) {
		add_butter(7);
		return;
	}	
	public void rare(void) {
		add_butter(1);
		return 0;
	}	

	// private 매서드
	private int add_butter(int how_many) {
		falver += how_many
		return 0;
	}

	private int pan_Frying(int how_long) {

	}


}
```

- 위 코드처럼 앞으로 생성할 객체가 사용할수 있는 메서드, 변수 등을 담고있는
- 일종의 구조체의 확장버전(메서드 포함)이 바로 클래스 입니다.
- 이 코드의 마지막 메서드 두 개는 객체지향적이라고 볼 수 있는데 () 외부에서 setter로 속도를 조절하는 것이 아니라, 객체가 직접 자신의 속성을 의미있게 변경하도록 했기 때문입니다.


# 키워드 정리


###  접근제한자
  - private : 클래스 외부에서 접근이 불가능
  - public : 가능!
  - (없음) : 디폴트 : 같은 패키지 내부에서 접근 가능

<br>

### 클래스의 영역에서
  - 필드
  - 생성자
  - 메서드

<br>

### 이외	
  - static : 시작과 동시에 생성! → 대표적으로 main함수가 static이 강제된다
  - this : 생성된 인스턴스의 클래스변수

<br>

### final 키워드
- final : 한번 선언하면 더이상 변경할수 없다! "변경불가능함을 표시"
  - class에서의 final 키워드 사용 : 상속할 수 없는 클래스 
  - 자바 String class 가 Final
- 클래스에 final 키워드를 사용해 상속을 막는 경우
  - 예시 : java의 String Class 의 경우, final 키워드가 선언되어 상속이 불가능한데, 자바 언어의 규칙대로 문자열 코드가 동작하도록, String 클래스의 동작이 변하지 않는것(상속 및 오버로딩에 의한 변경)을 보장하기 위해 
- method에서 final : 하위 클래스의 오버라이딩이 불가능함 (수정이 불가능한 유언장)
- field에서의 final : const키워드와 유사, 변경할수 없는 키워드를 만들어준다 (못난 아들이 물려받을수 있는 돈은 정해져있다)
### 이외
  - void : return 되는 값이 없다
  - default : 참고한부분 [링크](https://siyoon210.tistory.com/95)
    - 인터페이스 내부에 구현 로직이 포함될 수 없었는데
    - 메소드 선언 시에 default를 명시하게 되면 인터페이스 내부에서도 로직이 포함된 메소드 사용 가능
  - abstract
  - transient
  - synchronized
  - volatile
  - super : 부모 클래스로부터 상속받은 필드나 메소드를 자식 클래스에서 참조하는 데 사용하는 참조 변수입니다.
    - 메서드 상속받아 오버라이드 하는데 주로 사용
 

인스턴스 변수의 이름과 지역 변수의 이름이 같을 경우 인스턴스 변수 앞에 this 키워드를 사용하여 구분할 수 있었습니다.

이와 마찬가지로 부모 클래스의 멤버와 자식 클래스의 멤버 이름이 같을 경우 super 키워드를 사용하여 구별할 수 있습니다.


# 변수 종류(선언 위치별 정리)

- 3가지가 있는데 : 인스턴스 / 클래스 / 지역변수  이렇게 세가지가 존재한다.

    ![@@@](../img/Untitled.png)

- 이론으로만 보면 모르니.. 변수의 위치별

```java

public class Chicken { 
	
	final int sizeof_dak = 9; // 인스턴스 변수 
	static int price; // 클래스 변수 
	private Srting status = "fried"; // private 접근제한자가 붙어도 여전히 인스턴스 변수이다.

	
	String get_status(void) { 
		int cooking_time = 10; // 지역 변수 
		return (this.status);
	} 

	void set_stauts(String ins) {
		this.status = ins;
		return;			
	}

}
```

- 코드별 참조

## 객체 만드는 방법 (new 키워드 이해하기)

```java
Chicken BBQ = new Chicken();
```

- 위에서 만든 chicken 객체를 생성하는 코드이며 이 한줄에 의해 담긴 이야기를 해보겠습니다.
    - Chicken 이라는 클래스(=설계도/구조)를 가진 객체를 생성하는데
    - 이 객체의 이름은 BBQ 입니다.
    - new Chicken() : BBQ라는 객체를 *Heap 메모리 영역*에 생성한다.
- instance 변수 : Class라는 설계도 대로 객체를 여러개 만들었다고 치면, 각 객체는 변수의 형태로 코드에 작동되고, 객체 내부에는 클래스가 정의한대로, 인스턴스별 고유한 값이 들어있다.
- Java에서 객체를 생성할때에는 : Heap 영역에다가 new 키워드로 생성한다!

### Heap 메모리 영역? Heap 자료구조?

- 메모리 영역은 크게 Heap, Stack, Data 영역으로 나뉘는데, 이를 설명하기 위해서는 C언어 시간의 내용을 살짝 참고해오면
- 프로그램이 실행되어 메모리에 Load된 상태로 CPU의 자원을 할당받는 상태를 "프로세스" 라고 하는데, OS로부터 할당받은 메모리에 프로그램이 Load될때 일정한 구역으로 쪼개서 공간을 사용하고 이를 Program Memory Layout 이라고 부릅니다.
- 출처 : [https://commons.wikimedia.org/wiki/File:C-memlayout.svg](https://commons.wikimedia.org/wiki/File:C-memlayout.svg)

    ![(../img//Untitled%201.png](../img/Untitled%201.png)

- 여기서 Stack(스택)은 FILO(First In, Last Out) 형태이고 아래 사진과 같으며

    ![(../img/Untitled%202.png](../img//Untitled%202.png)

    - Program Memory Layout 에서 Stack이 High Address부터 공간을 차지해오며 함수 호출이나 로컬변수를 생성할수록 메모리에 공간을 할당받아 생성되며, 이를 Call stack이라고 합니다.

- 반면에 Heap자료구조는, 아래 사진과 같은 2진 트리이며 priority queue(우선순위 큐) 라는 이름으로도 불리는데요

    ![/Untitled%203.png](../img//Untitled%203.png)

    - 일반적으로 Heap영역이라고 이름붙은 이유는 메모리 블록에 대한 참조를 구성하는 데 사용되는 데이터 구조가 Heap이기 때문에(그중에서도 특히 minimum heap) 그렇습니다.
    - 저장하려는 데이터를 저장할 수있는 가장 작은 메모리 블록을 빠르게 찾을 수있는 적절한 방법인데, 여기서 우선순위는 메모리 블록의 사이즈입니다.
    - 메모리 블록의 크기순으로 항상 정렬되어 있는 메모리 구조이므로, Heap 영역에 메모리 할당을 요청하는 시스템콜(new혹은 malloc)이 들어왔을때 가장 적당한 메모리 공간을 최대한 빠른시간에 할당해주기 위해 Heap 자료구조를 사용합니다.
    - 단 위의 Heap 내용은 **논쟁이 있는 사항**이므로 그런가보다~ 하고 넘어가시면 됩니다.

- 아래 두가지 도 추가로 정리할까 하다가 뭐하는 짓인가 싶어서 Stop 함.. 왜이렇게 자꾸 drill down 을 하는걸까..
    - Heap 영역의 메모리 할당 정책 : [https://zetawiki.com/wiki/메모리배치기법,_최초적합,_최적적합,_최악적합](https://zetawiki.com/wiki/%EB%A9%94%EB%AA%A8%EB%A6%AC%EB%B0%B0%EC%B9%98%EA%B8%B0%EB%B2%95,_%EC%B5%9C%EC%B4%88%EC%A0%81%ED%95%A9,_%EC%B5%9C%EC%A0%81%EC%A0%81%ED%95%A9,_%EC%B5%9C%EC%95%85%EC%A0%81%ED%95%A9)
    - malloc과 new의 차이(c/c++)  : [https://drunkenpsycho.tistory.com/13](https://drunkenpsycho.tistory.com/13)

## 메서드 정의하는 방법

```java
public class Chicken { 
	
	final int sizeof_dak = 9; // 인스턴스 변수 
	static int price; // 클래스 변수 
	private Srting status = "fried"; // private 접근제한자가 붙어도 여전히 인스턴스 변수이다.

	
	String get_status(void) { 
		int cooking_time = 10; // 지역 변수 
		return (this.status);
	} 

	void set_stauts(String ins) {
		this.status = ins;
		return;			
	}

}

public static void main(String args[]) {
	Chicken BBQ = new Chicken();
	BBQ.set_stauts("Well-done");
	String ret = BBQ.get_status();
	return;
}

```

- 메서드를 정의하고 사용하는 예제 코드 입니다.
- 클래스 내부에 get_status(), set_stauts() 함수를 정의해주면 → 클래스의 메서드를 정의
- 실행하며 동작되는 코드 중간에 BBQ.set_stauts(), BBQ.get_status() 인스턴스에서 메서드를 접근해 원하는 동작을 수행하는 모습입니다.

## 생성자 정의하는 방법

- 생성자? : class의 instance를 생성할때 최초로 실행되는 method입니다!
- new 키워드로 새로운 Object 를 생성할때, 자동으로 호출되는 메서드, 인스턴스 변수를 초기화.
- 제한조건
    - 반환타입을 명시하거나 바꿀수 없고
    - 메소드명은 항상 클래스명과 동일하게
    - 입력매개변수를 다르게 해서 여러개의 생성자를 만들어둘수 있는데 이를 생성자 오버라이딩??(추가 조사 필요)

## this 키워드 이해하기

- this 키워드는 인스턴스가 바로 자기 자신을 참조하고/가리킬때 많이 사용합니다.
- 즉, 현재 클래스의 멤버변수를 지정할때 사용합니다.
- **this는 :** 현재 클래스(ParentClass)의 클래스 변수 mother와 동일한 이름의 파라미터(setMother 메소드)가 들어올 경우, 명확히 구분해 줄 수 있습니다.

### this() 란?

- 현재 클래스에 정의된 생성자를 호출할때 사용됩니다.
- **this() 는 :** ParentClass의 생성자가 2개 있을 경우, 생성자 값이 들어오지 않을 경우 this()메소드를 사용하여 두번째 생성자를 불러 초기화 할 수 있습니다.
- 결국 this는 인스턴스를, this()는 생성자를 의미합니다!

<br> 
<br> 
<br> 

# 과제 
  - int 값을 가지고 있는 이진 트리를 나타내는 Node 라는 클래스를 정의하세요.
  - int value, Node left, right를 가지고 있어야 합니다.
  - BinrayTree라는 클래스를 정의하고 주어진 노드를 기준으로 출력하는 bfs(Node node)와 dfs(Node node) 메소드를 구현하세요.
  - DFS는 왼쪽, 루트, 오른쪽 순으로 순회하세요.
- Main.java : 테스트를 하기 위한 자바파일
```java
import java.util.*;
import java.util.Scanner;


public class Main {
	//static final String s[] = {"one","two","three",
		//"four","five","six","seven","eight","nine"};
	
	static public void main(String args[]) {
		//Scanner sc = new Scanner(System.in);
		
		
		
		Btree bt = new Btree();
		bt.root = new Node(1);
        
        Node temp,cur;
        cur = bt.root;
        
        temp = new Node(2);
        cur.set_leftchild(temp);

        temp = new Node(3);
        cur.set_rightchild(temp);
        
        
        cur = cur.get_leftchild();
        temp = new Node(4);
        cur.set_leftchild(temp);
        
        cur = cur.get_leftchild();
        temp = new Node(5);
        cur.set_leftchild(temp);
        
        temp = new Node(6);
        cur.set_rightchild(temp);
	
        
        System.out.print("BFS! \n");
        bt.bfs(bt.root);
        System.out.print("\n\nDFS! \n");
        bt.dfs(bt.root);

		
	}
	
	static void createBTree() {
		
	}
	
}
```
- Btree.java : 바이너리 트리가 구현되어 있는 자바파일
```java
import java.util.*;
//import Node.java;


public class Btree {
	
	public Node root;
	//public List<Integer> bfsList = new ArrayList<>();
	//public List<Integer> dfsList = new ArrayList<>();
	
	
	
	
	public void bfs(Node start_node) {
        Queue<Node> q = new LinkedList<Node>();
        q.add(start_node);
        while (!q.isEmpty()) {
            Node next = q.poll();
            System.out.print(next.get_d() + " ");
            //bfsList.add(n.get_leftchild());
            if (next.get_leftchild() != null) {
                q.add(next.get_leftchild());
            }
            if (next.get_rightchild() != null) {
                q.add(next.get_rightchild());
            }
        }
    }
	
	
	public void dfs(Node start_node) {
		
		preod(start_node); //Preorder Traversal
		//해설 : DFS는 왼쪽, 루트, 오른쪽 순으로 순회하세요.
		
		//inord(start_node); //Inorder Traversal
		
		//posto(start_node); //Postorder Traversal
		
		return;
	}

	private void preod(Node cur_node) {
		if(cur_node != null) {
			System.out.print(cur_node.get_d() +" ");
			preod(cur_node.get_leftchild());
			preod(cur_node.get_rightchild());
		}
	}
	private void inord(Node cur_node) {
		if(cur_node != null) {
			inord(cur_node.get_leftchild());
			System.out.print(cur_node.get_d() +" ");
			inord(cur_node.get_rightchild());
		}
	}
	private void posto(Node cur_node) {
		if(cur_node != null) {
			posto(cur_node.get_leftchild());
			posto(cur_node.get_rightchild());
			System.out.print(cur_node.get_d() +" ");
		}
	}
	
}

```
- Node.java : 트리 구성을 위해 Node를 구현한 자바파일
```java

public class Node {
	
	private int data;
	// private String data;
	private Node leftchild;
	private Node rightchild;
	
	
	Node(int data) {
        this.data = data;
        this.leftchild = null;
        this.rightchild = null;
    }
	
	
	public int get_d() {
        return data;
    }

    public void set_d(int data) {
        this.data = data;
    }

    public void set_leftchild(Node leftnode) {
    	this.leftchild = leftnode;
    }
    
    public Node get_leftchild() {
    	return leftchild;
    }

    public void set_rightchild(Node rightnode) {
    	this.rightchild = rightnode;
    }
    
    public Node get_rightchild() {
    	return rightchild;
    }
    
}

```


- 과제 해결을 위해 참고한 것들
  - https://yaboong.github.io/data-structures/2018/02/12/binary-search-tree/
  - http://www.newthinktank.com/2013/03/binary-tree-in-java/
  - https://jeeneee.dev/java-live-study/week5-class/
