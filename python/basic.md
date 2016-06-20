# Phython基本のき

## 基本文法
### 型と変数
- 型宣言なし
- 変数にどんな型でも入れられる
- Pythonでは関数も型の一つ

### 数字
- 整数(int)、実数(float)、複素数
- べき乗(M**N→MのN乗)
- インクリメント使えない
- 数字を文字列に変換: str(x)

### 文字列
- 3種類の記法  
`string = 'Hello Python'`  
`string = "Hello Python"`  
```
string = """Hello World
Hello Python # this isn't comment
Hello Cisco"""
```
最後のは、改行や特殊記号もそのまま文字列に    

- 文字列にも演算記号が使える  
`print "I love " + "Python"`  
=>I love Python  
`print "hello " * 3`  
=>hello hello hello    

## Bool型
- "かつ"の表現は`A and B`
- "または"の表現は`A or B`
- "A<>B"は"A!=B"と同じ意味

## List型
- 拡張ができる配列[x,y,z]という形式で作成  
```
list1 = [3,4,5]
print(list1)
```
=>[3,4,5]  
```
list2 = ["I","Love","Python"]
print(list2[2])
```
=>Python   
```
list3 = [[1,2]['A',"B"]]
print(list3)
```
=>[[1,2]['A','B']]  
`print(list3[1][1])`  
=>B  

- 配列長を取得: len(x)
- オブジェクトのindexを取得: index(x)
- 配列にオブジェクトを追加: append(x)
- 配列からオブジェクトを削除: del
```
list1 = [1,2,3,4,5]
print(list1.index(3))
```
=>2  
`print(len(list1))`  
=>5  
```
list1.append(6)
print(list1)
```
=>[1,2,3,4,5,6]  
```
del list1[1]
print(list1)
```
=>[1,3,4,5,6]  

## 制御構造
- Pythonは{}を使わずに、インデントで処理をまとめる
```python
for i in range(10):
    if(i%2 == 0):
        print("{} is even".format(i))
    else:
        print("{} is odd".format(i))
print("done")
```

## 条件分岐
```python
if(条件A):
    条件Aがtrueなら実行
elif(条件B):
    条件Aがfalseかつ条件Bがtrueなら実行
else:
    条件AもBもfalseなら実行
```
- switch文がないのでif文で実装する

## ループ処理
- CやJavaなどのforとは別物
- CやJavaなどと同じような使い方も可
```python
for 変数 in リスト:
    実行する処理
```
リストの0番目を変数に代入して実行  
→リストの1番目を変数に代入して実行  
→...  
という感じ  
Javaで言うとこの拡張for文に似てるね
```python
string = ""
for char in ["H","e","l","l","o"]:
    string += char
print(string)
```
- 一定回数の処理にはrange関数を併用
- range(x):0からx-1までの連番のリストを返す
- range(x,y):xからy-1までの連番のリストを返す
```python
for i in range(10)
    print(i)
```
- pythonのforはiteratorと呼ばれる仕組み
- タプルや文字列、辞書などもkeyとして使える
- while文も使える
```python
x = 6789329
i = 1
while(2**i < x):
    i += 1

print("2^{} > {}".format(i,x))
```

## 関数とモジュールの利用
### 関数について
- 特定の処理を行うための呼び出し口
- 関数名に引数を与えて呼び出す
- 関数は返り値を返す
- 関数を使うことでコードがわかりやすくなる
- コードの重複をなくして保守性を向上

### モジュールについて
- モジュールはプログラムの整理整頓手法
- 機能ごとによるプログラムの整理
- モジュールを利用するにはimportが必要

## 標準入力
- `raw_input()`でユーザーからの入力を受け付ける
- 無限echoプログラム
```python
while(True):
    print"please input:"
    input = raw_input()
    print input
```

## コマンドライン引数
- スクリプトを"パラメータ"つきで起動する
- スクリプト内に直接書き込むより融通性がある
- コマンドライン引数にアクセスするにはsysモジュールをimport
- 引数がargvにリストとして格納されている
```python
import sys

print("----args----")
print(len(sys.argv))
print(sys.argv[0])
print(sys.argv[1])
print(sys.argv[2])
print("------------")

a = int(sys.argv[1])
b = int(sys.argv[2])
print("{} + {} = {}".format(a,b,a+b))
```

