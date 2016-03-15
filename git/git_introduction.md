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
![git_introduction_1](https://github.com/SeiyaKokushi/til/blob/master/git/git_introduction_1.png)  

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
![git_introduction_2](https://github.com/SeiyaKokushi/til/blob/master/git/git_introduction_2.png)
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
GitHubを確認して、反映されてればOK!!

## branchについて
### ブランチとは
　今あるファイルのコピーを作成すること。  
　例えば、Aというファイルがあるとしたら、Aはそのまま残して、中身が同じ **A'** を作ってあげる!

### リポジトリの結合
　A'を改良してBというファイルを作ったとして、これをまたAと結合してCとすることもできる!! **(エラーがなかったらのお話)**

### masterブランチとは
　一番最初からあるブランチ。
　上の例だとAまたは、結合したCのことを指す。  
　`$ git push origin master`で指しているmasterというのは、ブランチの名前を指していたんだよ!

### branchの作成
```
$ git branch ブランチ名
```
を実行して、branchを作成。

### 現在のブランチの作成
```
$ git branch
```
を実行すると、現在のbranchが表示される。

### ブランチの切り替え
```
$ git checkout 移動先のブランチ
```
を実行して、branchを切り替える。  
　**これだけでは、新ブランチがGitHubに反映されないのでPushする!!**

### ブランチの変更点をGitHubに反映する
```
$ git push origin ブランチ名
```
を実行すると、新しいブランチがGitHub上にも現れる!

### ブランチの統合
#### merge
```
$ git merge マージするbranch
```
を実行して、今いるブランチに指定したブランチを結合する。
```
$ git add -A
$ git commit -m "コメント"
$ git push origin master
```
を実行して、結合したブランチを反映させるのを忘れないように!    

```
$ git merge --no-ff マージするbranch
```
とオプションをつけて実行すると、マージの記録を残すことができるけど、記録を残す理由を記す必要がある!

#### pull
```
$ git pull <URL> ブランチ名
```
を実行して、今いるブランチに、GitHub上のブランチの内容を結合する。

## 他人のリポジトリを編集する
### GitHubでforkする
1. GitHub上で誰かのリポジトリをforkする。    

2. 自分のGitHub上にある、forkしたリポジトリのURLをコピー    

3. リポジトリをクローン  
　- `$ git clone <コピーしたURL>`を実行すると、現在のディレクトリにGitHub上のプロジェクトがコピーされる。    

4. コピーしたプロジェクトに移動    

5. GitHubにブランチの内容を反映
　- プロジェクト内ののファイルを変更した後に、remote add、add、commit、pushを行ってGitHubにブランチの内容を反映する。    

6. pullリクエスト
　- 変更した内容をPushした後に、GitHubにアクセスするとpullリクエストを送ることができる。
　- pullリクエストが承認されると、自分の変更内容がmergeされる。
