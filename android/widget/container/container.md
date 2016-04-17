# コンテナを使う
## コンテナについて
　コンテナは複数のコンポーネントを組み合わせた特殊なコンポーネント。  
　例えば、項目を一覧で表示したり、タブ画面によって画面を複数切り替えて表示したりする機能を提供する。    

- ListView
　ListViewは画面に項目を一覧表示するのに適したコンテナで、標準でスクロール機能を持ち、大量のデータを高速でスクロールできる。
ListViewはListViewとListAdapterという2つのクラスで構成されている。  
　ListAdaperはListViewにとってデータの持ち主であり、またそのデータをどのように表示するかといった振る舞いを決める重要なクラス。
実際、ListViewに関する主な実際はListAdapterに集約されていると言っても過言ではない。  
 ListAdapterは抽象クラスなので、継承し拡張したクラスを作成しないとインスタンスを生成できない。
一般的には、ArrrayAdapter< T >クラスか、BaseAdapterを継承したクラス、もしくは対象となるデータがCursorクラスであるなら、CursorAdapterというクラスを使うのが定石。  

    - BaseAdapterを継承したListAdapter  
    ```java
    public class SampleListAdapter extends BaseAdapter{
        private LayoutInflater mLayoutInflater;
        private context mContext;
        private List<ListItem> mItems;

        public SampleListAdapter(Context context, List<ListItem> items) {
            mContext = context;
            mItems = items;
            mLayoutInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return mItems.size;
        }

        @Override
        public Object getItem(int position) {
            return mItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View contentView, ViewGroup parent) {
            if (convertView == null) {
                convertView = mLayoutInflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            }

            ListItem item = mItems.get(position);

            ((TextView) convertView).setText(item.getName());

            return convertView;
        }
    }
    ```

　ListAdapterに求められる機能は、"データの個数はいくつあるか?"、"指定したデータはどれなのか?"、"指定された1のViewはどれなのか?"といった問い合わせに答えること。
BaseAdapterはこの基本的な機能を実装させるため、継承する必須の実装として、以下のメソッドを実装しないといけない。  
- BaseAdapterクラスから実装するメソッド  

|メソッド|説明|
|:--|:--|
|public int getCount()|データの個数を返す|
|public Object getItem(int position)|指定された位置のデータを返す|
|public View getView(int position, View convertView, ViewGroup parent)| 指定された位置のViewのインスタンスを返す|
|public long getItemId(int position)|指定された位置のViewのIDを返す|


