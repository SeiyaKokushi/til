# スタートアップ Gradle
## Groovy特有の文法
 Groovyの文法はJavaと互換性があるが、Groovy特有の文法を知らないとGardleのビルドスクリプトを読み書きするのが難しい場合がある。
そのため、Groovyの文法のうちよく使われるものを知っていると便利。

### 文字列
- シングルクォート(')  
　Javaの文字列とほぼ同等の利用方法
- ダブルクォート(")  
　文字列の内部に**$**記号で動的な内容を埋め込むことができる。内部的にはGroovyの**GString**というクラスが使われている。    

　また、どちらも3文字のクォート文字で囲むことで、複数行にわたる文字列を表記できる。
```gradle
    // シングルクォート
    String name = 'Jhon'

    // ダブルクォート
    String title = "Project: ${project.name}"
    // ダブルクォート内の中括弧は省略可能
    String title = "Project: $name"

    // 複数行シングルクォート
    String greet = '''********
    * Hello *
    *********'''

    // 複数行ダブルクォート
    String banner = """######################
    # ${new Date().toGMTString()} #
    ######################"""
```

### メソッド呼び出し時のカッコ省略
　メソッド呼び出し時の()を省略できる。
```gradle
    println('Hello World!')
    println 'Hello World!'
```

　メソッドの引数は2つ以上あっても構わないが、引数がない場合には()が省略できない。
```gradle
    // 引数が複数個
    printf '%5.3f', 345.0

    // メソッドの引数なし
    println   // NG
    println() // OK
```

　次の場合も、()の省略ができない。
```gradle
    // メソッド呼び出しがトップレベルの式でない
    println someMethod 'foo'  // NG
    println someMethod('foo') // OK

    // メソッド呼び出しが式の右辺にある
    def status = someMethod 'foo'  // NG
    def status = someMethod('foo') // OK
```

### defによる型指定の省略
　Javaのように変数や引数に型を指定することもできるが、型の指定を省略することもできる。
型の指定を省略する場合には**def**を利用する。
```gradle
    // 型を指定した変数定義
    String name = 'Jhon'

    // 型を省略した変数定義
    def name = 'Jhon'
```

　defによる型指定の省略は、変数定義だけでなく、メソッドの引数や返り値にも指定できる。
さらに、メソッドの引数の場合は型を省略するとdefとみなされる。  
　defを指定した場合、JavにおいてObject型を指定した場合と同等になる。  
　例えば、Javaでは次のコードはコンパイルエラーになる。
```java
    Object name = "Jhon";
    System.out.println(name.toUpperCase);
```

　しかし、Groovyではエラーにならない。
```gradle
    def name = 'Jhon'
    println name.toUpperCase
```

### クロージャ
　Groovyは言語機能としてクロージャを提供している。
Javaでいうラムダや他のプログラム言語でいう関数オブジェクトみたいなもの。  
　クロージャは中括弧を利用して定義し、<クロージャ名>.call()または通常のメソッド呼び出しと同様に<クロージャ>()で実行できる。
```gradle
    // クロージャ定義
    def clos1 = { mes -> println "Hi, $mes"}
    // 引数を省略した場合は暗黙引数itを利用
    def clos2 = { println "Hi, $it"}

    // クロージャ実行
    clos1.call('Jhon')  // "Hi, Jhon"が表示される
    clos2('Bob')        // "Hi, Bob"が表示される
    //メソッド呼び出しと同時にカッコ省略可能
    clos2 'Alice'       // "Hi, Alice"が表示される
```

　Gradleはクロージャを多用しており、例えば次のようなタスク定義はクロージャによって実現されている。
```gradle
    task hello << {
        println 'Hello Gradle world!'
    }
```

　{}で囲まれた部分は実際にはクロージャ。事前に定義したクロージャを利用して同じタスクを定義すると次のようになる。
```gradle
    // タスクへ追加する処理をクロージャとして定義
    def clos = { println 'Hello Gradle world!' }

    // タスクを定義
    task hello {}
    // タスクにクロージャを追加
    // << は leftShift()の省略形なので以下と等価
    hello.leftShift(clos)
```

## Hello Gradle world
### ビルドスクリプトの作成と実行
　まず、簡単なビルドスクリプトを作成し、実行する。  
　ファイル名はbuild.gradleで、内容は以下の通り。
```gradle
    task hello << {
        println 'Hello Gradle world!'
    }
```

　実行する場合は、次のコマンドをシェルで実行。
```
    $ gradle hello
```

### ビルドスクリプトと実行結果の解説
　先ほど作成したビルドスクリプトには、次の内容を記述している。    

1. helloという名前のタスクを定義し、新しい処理を追加する。
処理内容は中括弧で囲まれた部分に記述。
2. println()で"Hello Gradle world!"を表示する。    

　gradleコマンドはビルドスクリプト(デフォルトではカレントディレクトリのbuild.gradle)を読み込んで解釈し、引数で指定されたタスクを実行する。  

### 組み込みタスク

