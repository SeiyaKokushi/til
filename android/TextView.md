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
    android:text="Normal Text"
```
