# ウィジェット(Widget)
## ウィジェットについて
　ウィジェットの定義→**"画面にユーザ入力を受け付けるUIを有し、それらに対するインプットを適切に処理できるコンポーネント"**  
　もっと具体的には、Android SDKに含まれるViewクラスを継承したクラス。

## ウィジェットの使い方
　ウィジェットを使う方法は大きく分けて二つ。    

1. レイアウトエディタのランチャーメニューから、ドラッグ&ドロップでレイアウトに追加    

2. XMLエディタ上で直接タグを入力する  
```xml
    <TextView
        android:id="@+id/textNormal"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_margin="@dimen/item_spece"
        android:text="Normal Text" />
```
　この例の様に、タグ名にはウィジェットのJavaのクラス名を設定する。
本来ならば、タグ名は`android.widget.TextView`が正しいが、Android SDKに含まれる標準のウィジェットに限りパッケージ名を省略可能。

## ウィジェットの種類
　Android Studio標準のウィジェットについて。  
　各ウィジェットにはAPIリファレンスがあるため、参考に。    

- インターネット上のAPIリファレンス  
　http://developer.android.com/reference/android/widget/**<ウィジェット名>**.html  
- Android SDK内のAPIリファレンス  
　file://<Android SDKへのパス>/docs/reference/packages.html    

