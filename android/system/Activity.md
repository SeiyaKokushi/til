# Activity
## Activityのライフサイクル
- 毎度お馴染みのライフサイクル
![ライフサイクル](http://developer.android.com/intl/ja/images/activity_lifecycle.png)  

　これらのライフサイクルが呼ばれる一般的なタイミング  

|ライフサイクル|タイミング|
|:--|:--|
|onCreate|Activityのインスタンスが生成された時に一度だけ呼ばれる。画面にはまだ表示されない。|
|onStart|Activityが画面に表示される直前に呼ばれる。|
|onRestart|Activityが一度停止した後、画面に再表示される時に呼ばれる。|
|onResume|Activityが表示され、ユーザの入力を受け付ける直前に呼ばれる。|
|onPause|Activityが非活性になる直前に呼ばれる。|
|onStop|Activityが画面に見えなくなった時に呼ばれる。|
|onDestroy|Activityが破棄される直前に呼ばれる。|

## Activity間での情報のやり取り
　Activity間での情報のやり取りには**intent**というものを用いる。

### intentの簡単な使い方
#### Stringをやり取りしたい場合
情報を渡す側のActivity

```java
// intentの生成
Intent intent = new Intent(this, SubActivity);
// intentに情報をセット
intent.putStringExtra("text", "hoge");
// Activityの開始
startActivity(intent);
```

情報を受け取る側のActivity

```java
// intentの取得
Intent intent = getIntent();
//情報の取得
String text = intent.getStringExtra("text");
```
