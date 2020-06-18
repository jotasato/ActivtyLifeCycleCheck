package com.example.lifecyclecheck

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    //INITIALIZEDからCREATEDに変わるときに呼ばれる。
    //Actiityのインスタンスが作られたときに、一度だけ呼ばれる。
    //通常、ここでActivityで表示するViewを作成。setContentView(R.layout.activity_main)を行ったりして、Activityで必要になるものを作っていく。
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //onCreateメソッド時のログを出力している。出力結果は、onCreate state:INITIALIZED
        //INITIALIZEDはActivityのインスタンスができているだけで画面などの用意はできていない状態
        Log.d("MainActivity", "onCreate state:" + lifecycle.currentState)
    }

    //CREATEDからSTARTEDに変わるときに呼ばれる。
    //画面が表示されたタイミングで発火する。例えばMainActiviyyからSubActivityに遷移していて、戻るボタンでMainActivityに戻ってきたときには、
    //MainActivityのインスタンスはメモリ不足で破棄されていなければそのまま使われるので、onCreateは呼ばれず、onStartから呼ばれる。
    //例として動画プレイヤーであれば画面が表示されていないのに動画を再生するのは微妙なので、そういう時にonStart()で動画の再生を開始しておいて、
    //onStopで動画を止めるようにすれば、うまく動作させることができる。
    override fun onStart() {
        super.onStart()
        //onStartメソッド時のログを出力している。出力結果は、onStart state:CREATED
        //CREATEDはonCreate()によって画面などはできているが(ActivityでViewが作られている)が、表示されてもおらず、フォーカスを持っていない状態.
        //(画面ができていると言っても表示するためのViewができているだけで、APIなどからデータを取得して表示する時には表示する内容がない場合もある。)
        //どういう時にこの状態のままになるか？　→Activityが別のActivityの裏にいて、画面が隠れている時、この状態のままになる。
        Log.d("MainActivity", "on " +
                "Start state:" + lifecycle.currentState)
    }

    //STARTEDからRESUMEDに変わる時に呼ばれる。
    //画面がフォーカスを持ったタイミングで発火される。
    //Activityは透過させることができ、裏に以前に表示されていたActivityを表示させておくことができたりします。
    //その場合表示はされているのですが、フォーカスを持っていない状態になります。
    //また、Androidにはマルチウィンドウという機能があり、それを使っている場合は画面に複数のActivityが表示されます。
    //その場合には、フォーカスされている画面だけでフォーカスを持つ。
    //ログ計測などで使われたりはよくありますが、そこまで頻繁には使われるメソッドではないです。
    //フォーカスが当たった時に更新したい時などは使用することができる。
    override fun onResume() {
        super.onResume()
        //onResumeメソッド時のログを出力している。出力結果は、onStart state:STARTED
        //STARTEDは画面が表示されているが、フォーカスを持っていない状態
        //どういう時にこの状態のままになるか？　→Activityが他の透過Activityの後ろに表示されている時、マルチアクティビティで他のActivityにフォーカスがあたっている(選択されている)とき。
        Log.d("MainActivity", "onResume state:" + lifecycle.currentState)
    }

    //onPostResume()はonResumeのあとに呼ばれる。
    //画面がフォーカスを持っている状態。
    //どういう時にこの状態のままになるか？ →普通にActivityが最前面で表示されている時。
    override fun onPostResume() { //onPostResume()はonResumeのあとに呼ばれる。
        super.onPostResume()
        //onPostResumeメソッド時のログを出力している。出力結果は、onStart state:RESUMED
        //どういう時にこの状態のままになるか？　→普通にActivityが最前面で表示されている時
        Log.d("MainActivity", "onPostResume state:" + lifecycle.currentState)
    }

    //フォーカスを失った時にonPause()が呼ばれる。
    override fun onPause() {
        super.onPause()
        Log.d("MainActivity", "onPause state:" + lifecycle.currentState)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivity", "onDestroy state:" + lifecycle.currentState)
    }
}