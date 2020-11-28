# 예외



## 1. 예외란 무엇인가?

- 예외란 프로그램 실행 중 발생하는 이벤트로 프로그램 명령의 정상적인 흐름을 방해하는 것이다.

- 예외가 발생하게 될 경우 예외객체를 만들어 런타임 시스템에 전달한다. 
- 예외객체는 오류발생 당시의 프로그램 상태와 오류정보가 포함되어있다.
- 이러한 과정을 예외 발생이라고 한다.



## 2. 예외의 상속 구조

- 자바에서의 예외는 전부 Throwable을 상속받는다.
- Error의 경우는 개발자가 직접 사용할 일은 거의없다.
  - 자바가 JVM이나 내부에 있는 라이브러리에서 문제가 될법한 상황에서 나온다.
  - 즉, 애플리케이션이 정상적으로 동작하는데 심각한 문제가 있는 경우이다.
- 우리가 주로 사용하게 되는 에러는 Exception과 RuntimeException이다.

![](.\exceptionImage\exception1.png)



- 우리가 사용하는 예외는 크게 Checked Exception, Unchecked Exception 2종류로 분류 할 수 있다.
  - Exception -> Checked Exception
  - RuntimeException -> Unchecked Exception





### 2-1. Checked Exception

- Complie Time Exception이라고도 한다.
- 컴파일 시점에서 Exception을 catch하는지 검사하기 때문에 처리를 하지 않을경우 컴파일 에러가 발생한다.
  - 현 위치에서 처리를 할경우 try/catch를 하여 해결한다.
  - 현 위치에서 처리를 하지 않을 경우 throw 방식을 이용해여 나를 호출한 상위에 위임한다.
- Exception이 발생하는 메소드에서 throws 예약어를 이용하여 Exception을 호출 메소드에 전달해야 한다.

- 장기 혹은 체스를 예로 들때 나의 말이 놓여져 있거나 장기판 혹은 체스판 밖에는 둘 수가 없다. 
- 이와관련된 예외를 처리한다고 할때 아래와 같다

```java
public class InvalidPositionException extends Exception {
    public InvalidPositionException(String message) {
        super(message);
    }
}
```

```java
public class Position {   
    public Position(int x, int y) throws InvalidPositionException {
        if (조건 검사) {
            throw new InvalidPositionException("("+x + ", " + y + ") 위치는 놓을 수 없습니다.");
        }
        ...
    }
    ...
}
```

```java
// 현 위치에서 처리 할 경우
public class Game {
    ...
    public void play() {
        ...
        try {
            Position position = new position(-1, -2);
        } catch(InvalidPositionException e) {
            ...
        }
        ...
    }
}
```

```java
// 현 위치에서 처리 하지 않을 경우
public class Game {
    ...
    public void play() throw InvalidPositionException {
        ...
        Position position = new position(-1, -2);
        ...
    }
}
```





### 2-2.Unchecked Exception

- Runtime Time Exception 이라고 한다.
- 컴파일 시점에 Exception을 catch하는지 확인하지 않기 때문에 컴파일 시점에 Exception이 발생할 것인지의 여부를 판단할 수 없다.
- Exception이 발생하는 메소드에서 throws 예약어를 활용해 Exception을 처리할 필요가 없다. 하지만 처리해도 무방하다.

```java
public class InvalidPositionException extends RuntimeException {
    public InvalidPositionException(String message) {
        super(message);
    }
}
```

```java
public class Position {   
    public Position(int x, int y) {
        if (조건 검사) {
            throw new InvalidPositionException("("+x + ", " + y + ") 위치는 놓을 수 없습니다.");
        }
        ...
    }
    ...
}
```



### 2-3 선택방법

- 호출하는 메소드가 Exception을 활용해 무엇인가 의미 있는 작업을 할 수 있다면 Checked Exception을 사용하라.
- 만약 호출하는 메소드가 Exception을 catch해 예외 상황을 해결하거나 문제를 해결할 수 없다면 Unchecked Exception을 사용하라.
- 명확하지 않다면 Unchecked Exception을 사용하라.



## 3. 여러개의 Exception 처리방식

1. 메소드에서 여러개의 Exception을 throw 하는 경우 쉼표(,)로 구분 할 수 있다.

```java
public Dummy(int a) throw TypeAException, TypeBException {
    ...
}
```

2. 여러개의 Exception을 trhow하는 경우 부모클래스로 예외를 전달하는 것이 가능하다.

```java
// 위의 예제를 아래와 같이 변경이 가능하다.
public Dummy(int a) throw Exception {
    ...
}
```

3. 여러개의 Exception이 넘어올 경우 아래와 같이 처리한다.

```java
public class Application() {
    public static void main(String[] args) { 
    	try {
            new Dummy(-1);
        } catch (TypeAException e) {
            ...
        } catch (TypeBException e) {
            ...
        }
    }
}
```

4. 예외가 달라도 같이 처리할 경우는 아래와 같이 처리한다.

```java
public class Application() {
    public static void main(String[] args) { 
    	try {
            new Dummy(-1);
        } catch (TypeAException | TypeBException e) {
            ...
        }
    }
}
```

5. 부모클래스를 이용하여 아래와 같이 처리할 수 있다.

```java
public class Application() {
    public static void main(String[] args) { 
    	try {
            new Dummy(-1);
        } catch (Exception e) {
            ...
        }
    }
}
```

6. 예외를 다시 전달할 경우는 아래와 같이 가능하다.

```java
public class Application() {
    public static void main(String[] args) { 
    	try {
            new Dummy(-1);
        } catch (Exception TypeAException) {
            throw new TypeBException();
        } catch (Exception e) {
            throw e;
        }
    }
}
```



## 4. Stack Trace

- 예외가 발생할 경우 예외가 발생한 원인을 찾기 위해 예외의 발생 경로를 추적하는 것이 가능하다.

```java
public class Application() {
    public static void main(String[] args) { 
    	try {
            new Dummy(-1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```



## 5. finally

- JVM은 Exception이 발생하면 catch절로 이동후 프로그램을 종료한다.
- 하지만 예외가 발생하더라도 반드시 실행해야하는 작업이 있다면 finally를 이용하여 처리한다.

```java
public class Application() {
    public static void main(String[] args) { 
    	try {
            // 예외 발생하는 코드
        } catch (Exception e) {
            // 예외에 대한 처리
        } finally {
            // 무조건 실행해야 하는 함수
        }
    }
}
```



## 6. 자바에서 지원하는 예외타입

![](./exceptionImage/exceptionClass.png)

![](./exceptionImage/runtimeExceptionClass.png)

- 더 많은 내용 오라클 공식문서 참조



## 예외의 비용

- 예외의 비용은 스택의 깊이에 따라 비용이 크게 바뀐다고 한다.
- 예외의 비용을 줄이기 위해서는 fillInStackTrace 메소드를 오버라이딩 후 불변객체로 만든 뒤 캐스팅 하여 사용하는 방식으로 에러의 비용을 줄일 수 있다고 한다.
- 커스텀 예외의 경우 기존 예외의 조건이 부합하지 않는 경우, 혹은 프로그램의 가독성을 높이기 위해 사용한다고 한다.



## 추가내용

- 예외는 항상 try에서 잡아야 한다.
- catch에서 trhow를 발생시키면 아래 catch로 넘어가지 않는다.
- 아래의 상황에서 CustomRuntimeException이 발생한다면 IllegalArgumentException이 아래에 있는 catch로 가지 않는다.

```java
public class LottoNumber() {
    
    ...
    
    public static LottoNumber from(final int number) {
        try {
            throw new CustomRuntimeException();
        } catch (CustomRuntimeException e) {
            throw new IllegalArgumentException();
        } catch (IllegalArgumentException e) {
            return new LottoNumber(1);
        }
    }
    
    ...
}

```

- catch도 넣는 순서에 따라서 다르게 동작한다. 작은 단위를 위에 넣어서 작성해야 한다.







## 참조

- Oracle Java Documentation

- 우아한 테크코스 강의