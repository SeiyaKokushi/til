# Pythonの関数やモジュールの作成
## 続データ型
- Pythonの型
    - ユーザ定義の型(Class)
    - 組み込み型(Pythonが提供)    

## 組み込み型
### 辞書型
- keyとvalueのペアを保持するデータ型
- keyからvalueを取得
- 辞書の操作
```python
# 辞書の形式
map = {"key1":1, "key2":2}
# 値の取得
map["key1"]
# -> 1
# 値の追加
map["key3"] = 3
# 値の削除
del map["key3"]
# key一覧の取得
map.keys()
# -> ['key2', 'key1']
# keyを持っているか
map.has_key("key4")
# -> False
```
- 辞書とリストの違い
    - リスト型は線形にデータを保持
    - 辞書型はハッシュを利用してデータを保持
    - 要素数が増えた場合の検索が高速    

### セット
- 重複、順序のない複数のデータを保持する型
- valueのないMapに近い
```python
a = set([1,2,3,4,5,3,2])
print(a)
# -> set([1, 2, 3, 4, 5])

a = set("hello")
b = set("world")
print(a)
# -> set(['h', 'e', 'l', 'o'])
print(b)
# -> set(['d', 'r', 'o', 'w', 'l'])
print(a & b)
# -> set(['l', 'o'])
```
- セットの操作
```python
# 初期化
a = set([1,2,3,4,5,4,2])
# 追加
a.add(6)
# 削除
a.discard(5)
```

### タプル型
- 不可変なデータ構造
- 要素の数が決まった"一組のデータ"
- タプル型の操作
```python
# タプルの作成
touple = (1, 2, 3)
# 要素の取得
item = touple[0]
print item
# -> 1
```
- 要素の更新はできない
- データ構造を作成可能(クラスの簡易版)
- 不可変なオブジェクト
```python
def getMinMax(numList):
    minNum = numList[0]
    maxNum = numList[0]

    for n in numList:
        if n < minNum:
            minNum = n
        if n > maxNum:
            maxNum = n

    return (minNum, maxNum)
```

### None
- 何もないことを明示するための特殊な型
- C, JavaのNULLとほぼ同じ

## 関数の概念
- 関数の使い方
```python
# 宣言
def 関数名(仮引数):
    #処理

# 呼び出し
関数名(実引数)
```
### 関数作成の書式1
- 引数、返り値なしの書式
- 返り値を明示しない場合はNoneが返る
```python
def printHello():
    print("hello")

printHello()
# -> hello
```
### 関数作成の書式2
- 引数、返り値あり
- 引数は関数名の後の()に必要な数だけ指定
- 返り値はreturnで明示的に書く
```python
def adder(a, b):
    return a + b

print(adder(3, 4))
# -> 7
```

### 引数のデフォルト値
- 指定しない場合に指定される引数
```python
def func(a, b = 1):
    print(a)
    print(b)

func(5, 6)
# -> 5
#    6
func(5)
# -> 5
#    1
```

### global
- 関数内でグローバル変数にアクセスするための宣言
```python
gv = 0

def func1():
    gv = 1

def func2():
    global gv
    gv = 2

print(gv)
# -> 0
func1()
print(gv)
# -> 0
# これではアクセスできてない
func2()
print(gv)
# -> 2
# これでアクセスできてる
```

