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

### GridLayout
　基本的にAPI Level 14 以上のOSでないと使えないが、サポートライブラリ(android.support.v7.widget.GridLayout)が提供されており、4.0以下のOSでも利用できる。  
　機能的にはTableLayoutと似ているが、使い分けが必要となる。  
　GridLayoutは内包するウィジェットにandroid:layout_columnとandroid:layout_row属性を設定し、ウィジェットをグリッドに直接配置する。
もし、android:layout_colmunとandroid:layout_rowを設定しない場合は、andorid:columnCountとandroid:rowCountを設定することで、ウィジェットを順番に並べていくこともできる。  
- GridLayoutに設定できる独自の属性  

|属性|説明|
|:--|:--|
|andorid:columnCount|行のグリッドの数(数値で指定)|
|android:rowCount|列のグリッドの数(数値で指定)|

- GridLayoutに内包するウィジェットに設定できる独自の属性  

|属性|説明|
|:--|:--|
|android:layout_column|行の位置の指定(初期値は0)|
|android:layout_columnSpan|行の結合数|
|android:layout_row|列の位置の指定(初期値は0)|
|android:layout_rowSpan|列の結合数|
|android:layout_gravity|グリッド内のgravityの指定|

　GridLayoutもTableLayout同様、android:layout_widthとandroid:layout_height属性を指定する必要はない。その代わりに、グリッド内のウィジェットをどのように配置するかをandroid:layout_gravity属性で制御する。  
　TableLayoutは列の結合ができないが、GridLayoutはできる。一方、TableLayoutはandroid:layout_weightを使って大きさの異なる画面に対しても均等に配置することができるが、GridLayoutではできない。
これらの使い分けとしては、結合数が多いか、画面の種別が決まっている場合にはGridLayout、そうでない場合にはTableLayoutを使うといい。

### RelativeLayout
　"画面にウィジェットを配置する"という点で、もっとも柔軟かつシンプルな構造で作成できるレイアウト。  
　RelativeLayoutによるレイアウトの考え方は、基本的には相対的な配置をするレイアウトだと言える。
LinearLayoutやTableLayout、FrameLayout等は画面の左上を基点として配置するが、RelativeLayoutはあるウィジェットに着目し、そのウィジェットを基点として配置する。
"どのウィジェットを基点にするか"が重要で、多くの場合で親となるRelativeLayout自信を基点に配置する。  
　RelativeLayoutの属性は種類が多いが、目的で考えると大きく二つに分けることができる。
一つは親のRelativeLayoutに対する位置を指定する属性、もう一つはRelativeLayoutが内包するウィジェットのどれかを基点に位置を指定する属性。
- RelativeLayoutに対する位置の指定  

|属性|値|説明|
|:--|:--|:--|
|android:layout_alignParentLeft|true/false|RelativeLayoutの左側に配置|
|android:layout_alignParentRight|true/false|RelativeLayoutの右側に配置|
|android:layout_alignParentTop|true/false|RelativeLayoutの上側に配置|
|android:layout_alignParentBottm|true/false|RelativeLayoutの下側に配置|
|android:layout_centerInParent|true/false|RelativeLayoutの中心に配置|

- ウィジェットに対する相対的な位置の指定  

|属性|値|説明|
|:--|:--|:--|
|android:layout_toLeftOf|ウィジェットのID|指定されたウィジェットの左外側に配置|
|andorid:layout_toRightOf|ウィジェットのID|指定されたウィジェットの右外側に配置|
|andorid:layout_above|ウィジェットのID|指定されたウィジェットの上外側に配置|
|android:layout_below|ウィジェットのID|指定されたウィジェットの下外側に配置|
|andorid:layout_alignLeft|ウィジェットのID|指定されたウィジェットの左内側に配置|
|android:layout_alignRight|ウィジェットのID|指定されたウィジェットの右内側に配置|
|andorid:layout_alignTop|ウィジェットのID|指定されたウィジェットの上内側に配置|
|andorid:layout_alignBottm|ウィジェットのID|指定されたウィジェットの下内側に配置|

## レイアウトに設定する属性について
### android:layout_marginとandroid:padding
　どちらも、ウィジェットの上下左右にスペースを設け、位置を調整するための属性。  
　android:layout_marginは、設定した値が上下左右のウィジェットとのスペースになる。  
　android:layout_paddingは、ウィジェットの中身に対するスペースを変更する。
例えば、TextViewなら表示される文字列の位置が調整される。  
　ウィジェトのandorid:background属性に色を設定してみると、2つの違いがよくわかる。

### android:layout_weight
　LinearLayoutやTableLayout、TableRowといったレイアウトの子に設定できる。  
　各Viewがmatch_parentでない場合、順番位並べたときの合計サイズがLinearLayoutの大きさより小さくなる場合がある。
android:layout_weight属性は、その差を余ったスペースとして各ウィジェットに配分するための仕組み。  
　例えば、3つのウィジェトA、B、Cが並べられていた時、Aに1を残りのBとCに2を設定した場合、余ったスペースを1:2:2の割合で分配する。
ただし、あまりのスペースが生成されないケースでは、andorid:leyout_weightに値を設定しても効果はない。  
　ウィジェットの大きさそのものを均等、あるいは正確な比率で表したいのであれば、意図的に各ウィジェットの幅と高さに0を設定することで期待した表示になる。
