# レイアウト
## レイアウトについて
　Androidのレイアウト…"ウィジェットを画面にどう配置するか"をコントロールする機能を持つコンポーネント。
様々な画面サイズのAndroidデバイスが存在するため、固定の座標軸をベースにピクセル指定でウィジェットを配置するのは好ましくない。  
　Androidのレイアウトには、画面の解像度や画面の比率に依存することなく表示できる革新的な機能があり、マルチデバイス開発初心者でも簡単に実装できる。

## レイアウトの振る舞いを変更するには
- デザインモード  
　アプリを実際にAndroidデバイス上で実行した時の画面イメージを見ながら、直感的にレイアウトを変更する。
- テキストモード  
　xmlファイルに直接コードを書き込むことによって、レイアウトを変更する。

## レイアウトの種類
### FrameLayout
　もっとも基本的なレイアウト。FrameLayout自体は内包するウィジェットに対して特別な働きかけをしない。  
　FrameLayoutはandroid:gravity属性によって、内包するウィジェットの配置を変更できる。
ただ配置を指定するだけだと、同じ場所にウィジェトが配置されてしまうので、android:layout_margin属性を変更することで、ウィジェットの位置を調節できる。  
　FrameLayoutを用いることで、複数のウィジェットを重ねたデザインができ、重ね合わせの順番はコンポーネントツリーの下にあるものほど前面に表示される。

### LinearLayout
　レイアウトの中でもっとも使いやすく、またもっとも使用頻度が高いレイアウト。
LinearLayoutにできることは、内包するウィジェットを縦横に並べるだけ。  
- LinearLayoutで使用される属性

|属性名|説明|
|:--|:--|
|android:orientation|値にはhorizontal(横並び)、vertial(縦並び)という自動配置の向きを指定する。この値はデフォルトではhorizontal。|
|android:gravity|FrameLayoutと同じ。|
|android:layout_gravity|親タグのLinearLayoutに設定するandroid:gravityより強制力が強く、個別に設定する場合に使用。|
|android:layout_weight|隣接するウィジェットと幅あるいは高さの値を割合で設定。均等割付のような設定をする場合に使用。|

　**均等割付にしたい場合には、android:layout_widthやandroid:layout_heightの値をwrap_contentにせずに、0を設定する!!**

### TableLayout
　表組みのようなレイアウトを作るのに適したレイアウト。
LinearLayoutを拡張した縦並びのレイアウトで、機能的にもLinearLayoutに似ている。
LinearLayoutと異なる点は、子にTableRowというLinearLayoutを拡張した横並びのレイアウトを持つように設計されているところ。  
　TableLayoutも表組みの特徴同様、同列のアイテムは行頭の位置が揃う。
自動で子の大きさが調整されるので、TableRowに追加するウィジェットは、android:layout_heightとandroid:layout_widthを指定する必要がない。
値を設定しても、wrap_contentとして扱われるので、もし内包するウィジェットの幅をmatch_parent相当の振る舞いにしたい場合は、TableLayoutのandroid:shrinkColumnsやandroid:stretchColumns属性で設定できる。  
- TableLayoutに設定できる独自の属性

|属性|説明|
|:--|:--|
|android:shrinkColumns|折り返してでも狭いスペースでコンテンツを実現しようとする。カンマ区切りで複数の列を指定できる。(初期値は0)|
|android:stretchColumns|指定された列は、行の空いたスペースいっぱいの空間を取ろうとする。カンマ区切りで複数の列を指定できる。(初期値は0)
|android:collapseColumns|指定された行を非表示にする。(初期値は0)|

- TableRowに設定できる独自の属性

|属性|説明|
|:--|:--|
|android:layout_span|複数の列を結合する。HTMLのTableタグのcolspanと同等(数値で指定)|
|android:layout_column|列の位置を指定する(数値で指定)|
