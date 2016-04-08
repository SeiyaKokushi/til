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
　`http://developer.android.com/reference/android/widget/<ウィジェット名>.html`  
- Android SDK内のAPIリファレンス  
　`file:///<Android SDKへのパス>/docs/reference/packages.html`    

　ウィジェットの種類  
- TextView
- EditText
- Button
- CheckButton
- RadioButton
- ToggleButton
- Switch
- ImageButton
- ImageView
- ProgressBar
- SeekBar
- RatingBar
- Spinner
- WebView

## ウィジェットのタグに設定する属性について
　配置したウィジェットの振る舞いを変更するには、それぞれのタグにあらかじめ設定されている属性を用いて初期値を変更する必要がある。
タグによっては属性が必須で、設定されていないとエラーになるものもある。    

- android:id  
　必須の属性ではないが、すべてのタグに設定できる。
この属性にユニークなidを設定することで、プログラムからそのタグのインスタンスを取得できる。  
　android:idでは、任意のint型の整数とidの名称を次の形式で設定する。  
```
"<idの種類>/<idの名称>"
```
　設定されている整数は読み込まれているレイアウトファイルの中でユニークでないといけない。
しかし、開発者がそのidの値を管理するのは難しい。
そこで、idの種類に**"@+id"**と書くことで、自動採番される。
```xml
android:id="@+id/<idの名称>"
```
　idの種類には、この他にもAndroid SDKで予約されたidがある。
例えば、`@android:id/<idの名称>`という書き方。
これはAndroid SDKで定義されているidを使う場合に使用する。
実際の使用例は`"@android:id/list"`となる。
@androidという書き方はidの他にもcolorやdrawableといったリソースのアクセスでも使用される。  
　また、idを事前に別のファイルで定義することもできる。
例えば、res/ids.xmlという名前のリソースファイルを作成し、その中で`<item name="testname" type="id"/>`というitemタグを追加すると、タグのid属性に"@id/testname"と書くことができる。
**"@+id"ではなく、"@id"である点に注意!!**  
　定義されたidはJavaプログラムのコンパイル時にRクラスの定数として自動生成される。
このRクラスのおかげでJavaプログラムからidの値を使用できるが、コンパイル時に自動生成されるファイルであるため、レイアウトファイルなどにエラーがあると、自動生成に失敗する。
特に、"Rというクラスが見つからない"というエラーの場合は、リソースファイルに何らかのエラーがある場合が多い。

- android:layout_width, android:layout_height  
　ウィジェットの幅と高さを指定する属性。  
　この属性には、固定の値、あるいはwrap_content、もしくはmatch_parentといった値を設定できる。  
　- wrap_content  
　　自動的にウィジェットを表示するために必要な大きさを設定。  
　　例えば、"こんにちは世界"という文字列を設定したTextViewがあったとして、それを表示する最低限の大きさを計算し、その値を幅あるいは高さとして設定する。  
　- match_parent  
　　表示可能な最大限の大きさを設定。  
　　表示できる大きさはmatch_parentを設定したタグの親のタグに依存する。
つまり、親のタグ内でそのタグを最大にできる値。  
　- 固定の値  
　　自動で大きさが変わって欲しくない時に使用するが、設定する値は単位を指定する必要がある。  
　　単位については以下を参照。  

|単位|説明|
|:--|:--|
|px|Pixelsの略。画面の実際のPixelに対応する。この単位は画面に依存するため使用は推奨されない。|
|dp(dip)|Density-independent Pixels(密度非依存ピクセル)の略でdpとdipは同じ意味。ディスプレイ解像度の物理的な密度に応じた抽象単位。|
|sp|Scale-independent Pixels(スケール非依存ピクセル)の略。dpの単位と似ているが、画面上のサイズはユーザのフォントサイズ設定に準ずる。|
|pt|Pointsの略で、文字サイズによく使用される。|

## ImageView、ImageButtonのスケール種別
　ImageViewやImageButtonでは、画像のスケール種別をandroid:scaleType属性あるいは、JavaプログラムからだとsetScaleTypeメソッドで設定できる。
スケール種別とは、画像がウィジェットと同じ大きさでない場合に、見え方をどのように変更するのかを設定するもので、下に示すような種類がある。

|スケール種別|説明|
|:--|:--|
|CENTER|拡大・縮小をせず、中心に配置|
|CENTER_CROP|ウィジェットのサイズの縦あるいは横が画像の縦あるいは横の小さい方が収まるように縦横比を維持しつつ等倍・縮小し、中心に配置|
|CENTER_INSIDE|ウィジェットのサイズの縦あるいは横が画像の縦あるいは横の大きい方が収まるように縦横比を維持しつつ等倍・縮小し、中心に配置。FIT_CENTERに似ているが、こちらは拡大しない点に注意。|
|FIT_CENTER|縦横比を維持したまま、ウィジェットのサイズに合わせて拡大・縮小し、中央に配置。|
|FIT_END|縦横比を維持したまま、ウィジェットのサイズに合わせて拡大・縮小し、下段に配置。|
|FIT_START|縦横比を維持したまま、ウィジェットのサイズに合わせて拡大・縮小し、上段に配置。|
|FIT_XY|縦横比を無視してウィジェットのサイズに合わせて拡大・縮小。|
|MATRIX|ユーザ設定によるMatrixによって変更。|


