package app.android.pip

import android.app.PictureInPictureParams
import android.graphics.Point
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Rational
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBar

class MainActivity : AppCompatActivity() {
    lateinit var enterPip:Button
    lateinit var actionBar:ActionBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        enterPip = findViewById(R.id.enter_button)
        actionBar = this.supportActionBar!!
        enterPip.setOnClickListener {
            val d = windowManager.defaultDisplay
            val p = Point()
            d.getSize(p)
            val width = p.x
            val height = p.y
            val ratio = Rational(width,height)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                var pip_builder =   PictureInPictureParams.Builder()
                pip_builder.setAspectRatio(ratio).build()
                enterPictureInPictureMode(pip_builder.build())
            } else {
                Toast.makeText(this,"PIP not supported",Toast.LENGTH_SHORT).show()
            }

        }
    }
}