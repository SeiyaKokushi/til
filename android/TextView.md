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
文字列の色は、**#**から始まる16進数。

### Size Text
　Normal Textの文字の大きさを変更した、TextView。

```xml
<TextView
    android:id="+id/textSize"
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
