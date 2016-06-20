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

