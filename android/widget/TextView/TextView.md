# TextViewについて
## TextViewとは
　画面に文字列を表示するためのウィジェット。
文字の大きさやフォントなどを変更でき、簡易的なHTMLの表示も可能。
また、一部の文字列の色を変更したり、スタイルを変更したりなど、文字列の装飾もできる。  
　TextViewは、"文字列を表示する"という基本的な機能を持つため、ButtonやEditTextなどのウィジェットの親クラスにもなっている。    

　公式リファレンスは[こちら](http://developer.android.com/intl/ja/reference/android/widget/TextView.html)
## 様々なTextView
### Normal Text
　最も標準的なTextView。プロパティの各値の変更は特になし。

```xml
<TextView
    android:id="@+id/textNormal"
    android:layout_width="200dp"
    android:layout_height="wrap_content"
    android:text="Normal Text" />
```
　`andrid:text`のパラメータを変えることで、表示する文字列を変更できる。

### Color Text
　Normal Textの文字の色を変更した、TextView。

```xml
<TextView
    android:id="@+id/textColor"
    android:layout_width="200dp"
    android:layout_height="wrap_content"
    android:text="Color Text"
    android:textColor="#FF0000" />
```
　`android:textColor`のパラメータを変えることで、文字列の色を変更できる。
文字列の色は、**"#"**から始まる16進数。

### Size Text
　Normal Textの文字の大きさを変更した、TextView。

```xml
<TextView
    android:id="@+id/textSize"
    android:layout_width="200dp"
    android:layout_height="wrap_content"
    android:text="Size Text"
    android:textSize="30sp" />
```
　`android:textSize`のパラメータを変えることで、文字列の大きさを変更できる。

#### android開発でよく用いる単位について
|単位|説明|
|:--|:--|
|px|Pixelsの略。画面の実際のPixelに対応する。この単位は画面に依存するため使用は推奨されない。|
|dp(dip)|Density-independent Pixels(密度非依存ピクセル)の略で、dpとdipは同じ意味。ディスプレイ解像度の物理的な密度に応じた抽象単位。160dpi(dots per inch)を基準にした単位で、1dpは160dpiの画面で1pxになる。|
|sp|Scale-indeoendent Pixels(スケール非依存ピクセル)の略。dpの単位と似ているが、画面上のサイズはユーザのフォントサイズ設定に準ずる。|
|pt|Pointsの略で、文字サイズによく使用される。1インチの1/72を基準とした画面の物理サイズ。|

### Style Text
　Normal Textにスタイルを付加した、TextView。

```xml
<TextView
    android:id="@+id/textStyle"
    android:layout_width="200dp"
    android:layout_height="wrap_content"
    android:text="Style Text"
    android:textStyle="bold|italic" />
```
　`android:textStyle`のパラメータを変えることで、テキストにスタイルを付加できる。    

- normal  
デフォルトのテキストスタイル
- bold  
ボールド体
- italic  
イタリック体    

　**"|"**で区切ることで、複数のスタイルを組み合わせることができる。

### Multi Text
　文字列を複数行で表示する、TextView。

```xml
<TextView
    android:id="@+id/textMulti"
    android:layout_width="200dp"
    android:layout_height="wrap_content"
    android:text="いろはにほへと ちりぬるを わかよたれそ つねならむ うゐのおくやま けふこえて あさきゆめみし ゑひもせす"
    android:maxLines="3" />
```
　入力された文字列を、`android:maxLines`で指定した行数以下で表示する。指定した行数に収まらない場合でも、指定した行数までしか表示しない。

### Ellipsize Text
　文字列がTextView内に収まらない場合には、"..."を表示して省略する、TextView。

```xml
<TextView
    android:id="@+id/textEllipsize"
    android:layout_width="200dp"
    android:layout_height="wrap_content"
    android:maxLines="1"
    android:text="いろはにほへと ちりぬるを わかよたれそ つねならむ うゐのおくやま けふこえて あさきゆめみし ゑひもせす"
    android:ellipsize="end" />
```
　`android:ellipsize`でどの部分を省略するか決定する。startで文頭、middleで真ん中、endで末尾を省略。

### Marquee Text
　文字列がTextView内に収まらない場合には、自動スライドで全文を順次表示する、TextView。

```xml
<TextView
    android:id="@+id/textMarquee"
    android:layout_width="200dp"
    android:layout_height="wrap_content"
    android:ellipsize="marquee"
    android:focusable="true"
    android:focusableInTouchMode="true"
    android:singleLine="true"
    android:text="いろはにほへと ちりぬるを わかよたれそ つねならむ うゐのおくやま けふこえて あさきゆめみし ゑひもせす" />
```
　マーキー表示させるためには、`android:focusable`と`android:focusableInTouchMode`と`android:singleLine`を**true**にする。

### Gravity Text
　文字列をTextView内のどこかに寄せて表示する、TextView。

```xml
<TextView
    android:id="@+id/textGravity"
    android:layout_width="200dp"
    android:layout_height="64dp"
    android:text="gravity Text"
    android:gravity="bottom|right" />
```
　`android:gravity`のパラメータを変えることで、寄せる場所を変更できる。    

|Constant|Description|
|:--|:--|
|top|オブジェクトのサイズによらず、上に寄せる|
|bottom|オブジェクトのサイズによらず、下に寄せる|
|left|オブジェクトのサイズによらず、左に寄せる|
|right|オブジェクトのサイズによらず、右に寄せる|
|center_vertical|オブジェクトのサイズによらず、縦方向中央に寄せる|
|fill_vertical|オブジェクトの高さを、コンテナのサイズに合わせる|
|center_horizontal|オブジェクトのサイズによらず、横方向中央に寄せる|
|fill_horizontal|オブジェクトの幅を、コンテナのサイズに合わせる|
|center|オブジェクトのサイズによらず、縦方向横方向ともに中央に寄せる|
|fill|オブジェクトの幅と高さを、コンテナのサイズに合わせる|
|clip_vertical|top/bottomの追加オプションとして、オブジェクトの上部/下部の境界をコンテナの境界に合わせる|
|clip_horizontal|left/rightの追加オプションとして、オブジェクトの左側/右側の境界をコンテナの境界に合わせる|
|start|オブジェクトの大きさにかかわらず、コンテナの先頭にオブジェクトを寄せる|
|end|オブジェクトの大きさにかかわらず、コンテナの末尾にオブジェクトを寄せる|

### Revoke Text
　文字列に取り消し線をつけた、TextView。

```java
    // textRevokeを紐付け
    TextView textRevoke = (TextView) findViewById(R.id.textRevoke);
    // textRevokeのペイントを取得し、paintの初期化
    TextPaint paint = textRevoke.getPaint();
    // フラグのセット(ここで取り消し線を装飾するよう設定)
    paint.setFlags(textRevoke.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    // paintにアンチエイリアスを設定
    paint.setAntiAlias(true);
```
　TextViewのPaintを取得し、取り消し線のフラグをセットすることで、装飾が可能。
