# インストールと設定
## Gradleのインストール
　Gradleのインストール手順を説明。  
　Gradleが依存しているソフトウェアはJDKのみ。

### 前提条件
　Gradleを利用するためには、JDK6以上が必要。  
　コマンドプロンプトで`java -version`と入力することで、JDKのバージョンを確かめることができる。  
　GradleはJVM言語であるGroovyをベースに実装されているが、内部にGroovyのライブラリをバンドルしているので、別途Groovyをインストールする必要はない。

### GVMによるGradleのインストール
　Mac OS XやLinux、CygwinなどでBashが使える環境なら、GVM(the Groovy enVironment Manager)を利用してインストールするのがおすすめ。  
　GVMとは、GroovyやGrailsなど、いわゆるGroovyエコシステムに属するプロダクトのインストールやアップデートを一元的に行うためのツール。  
　GVMはGradleの導入から環境設定までを自動的に行ってくれる。
さらに、複数のGradleのバージョンを同時にインストールしておき、適宜バージョンを切り替えることが可能。    

- インストールの準備  
　GVMを利用するためには、Bashに加え、curlコマンドとunzipコマンドが必要。  
　また、GVM実行時に内部でjavaコマンドを呼ぶため、環境変数JAVA_HOMEを正しく設定しておく。    

    - Mac OS XでのJAVA_HOMEの設定  
    　Mac OS X環境では`/usr/libexec/java_home`コマンドで、デフォルトに設定されているJDKのJAVA_HOMEを取得できる。
    これを利用して、例えば~/.bashrcに  
    ```
    export JAVA_HOME='/usr/libexec/java_home'
    ```
    と記述を追加しておく。    

- GVMのインストール  
　GVMのインストール方法は簡単で、プロジェクトのWebサイトからスクリプトをダウンロードして実行するだけ。
インターネットに接続可能な状態で、下のコマンドを実行。
```
$ curl -s get.gvmtool.net | bash
```
　`$ gvm help`と入力してヘルプが表示されればOK!!    

- Gradleのインストール  
　Gradleをインストールするには以下のコマンドを実行。
```
$ gvm install grade
```    

***現在GVMはSDKMAN!というものに置き換わっているので注意!!!***


