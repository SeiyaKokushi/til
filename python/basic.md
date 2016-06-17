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
