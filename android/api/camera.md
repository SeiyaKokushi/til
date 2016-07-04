# Camera API
## 基本のき
カメラをアプリに導入する方法は大きく分けて2つ
- Camera APIを使ってオリジナルのCameraを実装
- camera Intentを使って既存のCameraを使う    

関連のあるクラス
- android.hardware.camera2  
API Level 21から導入された新しいCamera API    

- Camera  
以前まで使われていた古いCamera API    

- SurfaceView  
カメラのプレビューを表示するためのView    

- MediaRecorder  
動画を録画する時に使うレコーダー    

- Intent  
外部のカメラを使う時に使用

## Manifestに書くこと
幾つかのpermissionがついているかチェック。なかったらつける。    

- Camera Permission  
独自のカメラを搭載する時は絶対必要。
でもintentを通じてカメラを使う時は、別にいらない。
```xml
<uses-permission android:name="android.permission.CAMERA" />
```

- Camera Features  
これも必ずつけること。
これをつけることで、カメラが搭載されていない端末とかカメラの条件を満たさない端末にアプリがインストールされるのをGoogle Playが防いでくれる。
```xml
<uses-feature android:name="android.hardware.camera" />
```
もし自分が作ったアプリが、カメラの使用はできるけど必ずしも必要じゃないという場合には`android:required`を`false`にしておく。
```xml
<uses-feature android:name="android.hardware.camera" android:required="false" />
```

- Storage Permission  
カメラで撮った写真や動画を外部ストレージ(SD Card)に保存したいならつける。
```xml
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
```

- Audio Recording Permission  
音も記録したいという場合には、つける。
```xml
<uses-permission android:name="android.permission.RECORD_AUDIO" />
```

- Location Permission  
撮った写真に位置情報を紐付けたいなら、つける。
```xml
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
```

## 既存のカメラアプリを使う

## 独自のカメラアプリを作る
次のやり方は古い方のCamera APIのものなので注意。    

カメラインタフェースを実装する流れ。  
1. Detect and Access Camera  
カメラがあるかどうかチェックするためのコードを書いて、アクセスリクエストする。    

2. Create a Preview Class  
**SurfaceView**を継承し、**SurfaceHolder**を実装したカメラプレビュー用のクラスを作る。
このクラスはカメラからのプレビューを表示する。    

3. Build a Preview Layout  
カメラプレビューやその他のインタフェースを取り入れるためのview layoutを作る。    

4. Setup Listeners for Capture  
ボタンを押したら写真や動画を撮り始めるといった動作のためのlistenerをセットする。    

5. Capture and Save Files  
写真やビデオを撮影して、保存するためのコードを書く。    

6. Release the Camera  
カメラの機能を使わなくなったら適切にreleaseしなおといけない。
そうしないと、他のアプリにも影響が出る。    

>***注意***: Cameraオブジェクトは`Camera.release()`によって確実にreleaseすること。  
>さもないと、自分のアプリを含むカメラを使用するその他のアプリ全てがカメラにアクセスできなくなり、落ちる。

### Detecting camera hardware
manifestにおいて特にカメラの使用を明記してないのであれば、カメラが使用可能かどうかチェックすべき。
チェックには、`PackageManager.hasSystemFeature()`メソッドを使用。
```java
// 端末がカメラを持ってるかチェック
private boolean checkCameraHardware(Context context) {
    if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
        // カメラ持ってる
        return true;
    } else {
        // カメラ持ってない
        return false;
    }
}
```
複数のカメラを持つカメラが今では多い。
Android 2.3(API Level 9)以上から使えるカメラの数を調べる`Camera.getNumberOfCameras()`メソッドが使えるようになった。

### Accessing cameras
カメラにアクセスするには`Camera.open()`を使用する。l
例外処理を行うこと。
```java
public static Camera getCameraInstance() {
    Camera c = null;
    try {
        c = Camera.open();
    } catch (Exception e) {
        // カメラが使えない時の例外処理
    }
    return c;
}
```
>***注意***: `Camera.open()`を使う時は必ず例外処理を行うこと。  
>例外処理を適切に行ってないと落ちるよ。
Android 2.3(API Level 9)以上の端末で特定のカメラにアクセスするには`Camera.open(int)`でアクセスするカメラを選べる。
`Camera.open()`でアクセスすると2つ以上カメラがある場合は、デフォルトで背面カメラにアクセスする。

### Checking camera features
カメラにアクセスできたら、カメラのさらなる詳しい情報を`Camera.getParameters()`で取得できる。返ってくる値は`Camera.Parameters`。
API Levelが9以上であれば、`Camerea.getCameraInfo`でカメラがどこについてるかや写真の向きについての情報を取得できる。

### Creating a preview class
`SurfaceView`クラスでカメラのプレビューを行う。  
viewのイベントを拾うために、`SurfaceHolder.Callback`を実装する。
```java
public class CameraPreview extends SurfaceView implementts SurfaceHolder.Callback {
    private SurfaceHolder mHolder;
    private Camera mCamera;

    public CameraPreview(Context context, Camera camera) {
        super(context);
        mCamera = camera;

        // SurfaceHolder.Callbackをインストール
        // これによってsurfaceの生成、破棄のイベントを検知できる
        mHolder = getHolder();
        mHolder.addCallback(this);
        // 必須ではないが、Android version 3.0以前のものにはつける
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    public void surfaceCreated(SurfaceHolder holder) {
        // surfaceが生成されたら、カメラのプレビューを描写する場所を指定する
        try {
            mCamera.setPreviewDisplay(holder);
            mCamera.startPreview();
        } catch (IOException e) {
            Log.d(TAG, "Erroe setting camera preview: " + e.getMEssage());
        }
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        // Activity内でCamera previewをreleaseするのを忘れないこと
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
        // プレビューの向き等がわかる時にはここでイベントの処理を行う
        // リサイズやリフォーマットをする時は必ずプレビューをストップすること

        if (mHolder.getSurface() == null) {
            return;
        }

        // 変化を加える前にプレビューをストップさせる
        try {
            mCamera.stopPrecview();
        } catch (Exception e) {
            // ここは特に何もしない
        }

        // ここに向きを変えるとかサイズを変えるとかの処理を書く

        // 新しい設定でプレビューをスタート
        try {
            mCamera.setPreviewDisplay(mHolder);
            mCamera.startPreview();
        } catch (Exception e) {
            Log.d(TAG, "Error starting camera preview: " + e.getMessage());
        }
    }
}
```
カメラプレビューのサイズを特定のサイズに変更したい場合は、`surfaceChanged()`メソッドの中に書くこと。
プレビューサイズを変更する場合は、`getSupportedPreviewSizes()`で帰ってくる値を必ず使用すること。
`setPreviewSize()`に適当な値をセットしてはいけない。

### Placing preview in a layout
今回の例では、FlameLayout上にプレビューをオーバーレイする。
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmls:android="http://schemas.android.com/apk/res/android"
    android:orientaion="horizontal"
    android:layout_width="fill_parent"
    android:layout_height="fill_parent" >

    <FrameLayout
        anroid:id="@+id/camera_preview"
        android:layout_width="fill_parent"
        android:layout_height="fill_parent"
        android:layout_weight="1" />

    <Button
        andorid:id="@+id/button_capture"
        android:text="Capture"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        andorid:layout_gravity="center" />
</LinearLayout>
```
多くのデバイスはカメラプレビューのデフォルトの向きがlandscapeになっている。
今回の例ではhorizontalなレイアウトを作成したので、アプリの向き自体もmanifest内でlandscapeに修正している。
```xml
<activity android:name=".CameraActivity"
    android:label="@string/app_name"
    andorid:screenOrientation="landscape" >
    <!-- ここで向きを設定してる -->

    <intent-filter>
        <action android:name="android.intent.action.MAiN" />
        <category android:name="android.intent.category.LAUNCHER" />
    </intent-filter>
</activity>
```
> **メモ**: カメラの向きはlandscapeである必要はない。  
> Android 2.2(API Level 8)からプレビューの向きを設定する`setDisplayOrientaion()`が使えるようになった。  
> プレビューの向きを変えたい場合は、`sufaceChanged()`の中で`Camera.stopPreview()`によってプレビューを停止させてから、向きを変える。  
> そして最後に`Camera.startPreview()`を呼んでプレビューを再開する。    

次の例でpreview classで作成したものをどうやってActivityに適用するか示す。
```java
public class CameraActivity extends Activity {
    private Camera mCamera;
    private CameraPreview mPreview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // Cameraのインスタンスを生成
        mCamera = getCameraInstance();

        // プレビュー用のviewを生成して、Activity内にセット
        mPreview = new CameraPreview(this, mCamera);
        FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
        preview.addView(mPreview);
    }
}
```

