# GitHubとはなんぞや

## そもそもGitとは、なんぞや
　プログラムの変更履歴を記録・追跡するためのツール→**バージョン管理ツール**  
　これをweb上で管理しちゃおうってのが、[**GitHub**](https://github.com)  
　GitHub以外にも、GitBucket、GitLabってのがあるみたい!    

　つまり...  
　Git→バージョン管理ツール全般  
　GitHub→Gitをweb上で管理するサービスの一つ  

## Gitの仕組みについて
　Gitは4つのパートに分けられる
- ワーキングツリー
- インデックス
- ローカルリポジトリ
- リモートリポジトリ  

　流れとしては、  

1. ワーキングツリーをインデックスに登録(Add)  
　- これによって、リポジトリにコミットする準備をするために、変更内容を一時的に保存する。    

2. インデックスをローカルリポジトリにコピー(Commit)  
　- ファイルやディレクトリの追加や変更をリポジトリに記録する。    

3. ローカルリポジトリからリモートリポジトリ(GitHub)に送信(Push)  
　- ローカルリポジトリの変更をリモートリポジトリにも反映させる。  

## GitHubとターミナルを使って、Gitを操作する
### 最初の設定(初めてGitを使う人だけ)
　ターミナルで、下のコマンドを実行。
```
$ git config --global user.name "Gitのユーザネーム"
$ git config --global user.email Gitのメールアドレス
```
　**登録後は、`$ git config --list`を実行して確認できるよ。**  

### GitHub上に新しいリポジトリの作成
![git_introduction_1](/Users/kokushiseiya/Documents/til/git/git_introduction_1.png)  

　"**+ New repository**"から、新しいリポジトリの作成  
　リポジトリに名前をつけて(ここでは"hoge"という名前をつける)、"**Create repository**"!!

### Commit & Pushしたいディレクトリに移動
```
$ cd /Users/hogehoge/Documents/hoge
```
で、Commit & Pushしたいディレクトリに移動。

### プロジェクトファイルの初期化
```
$ git init
```
を実行して、Gitの管理ファイルを作成。  
→これで、ローカルリポジトリとインデックスの作成。

### インデックスにファイルを登録
```
$ git add -A
```
を実行して、ワーキングツリー内のファイルをインデックスに登録。  
オプションに`-A`をつけることで、ワーキングツリー内のすべてのファイルがインデックスに登録される。ファイルを指定したい時は、`$ git add ファイル名`で登録できる。  
**オプションについては、`$ git help add`を参照!!**

### ローカルリポジトリにファイルを保存
```
$ git commit -m "コメント"
```
を実行して、プロジェクトフォルダー内のファイルをローカルリポジトリに保存する。

### URLの登録
![git_introduction_2](/Users/kokushiseiya/Documents/til/git/git_introduction_2.png)
上の画像の赤い部分をコピー。
```
$ git remote add origin <コピーしたURL>
```
を実行して、ローカルとリモートの紐付け。

### GitHubに送信
```
$ git push origin master
```
を実行して、ローカルリポジトリをリモートリポジトリに送信。
