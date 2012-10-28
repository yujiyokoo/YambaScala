package yamba.scala.project

import _root_.winterwell.jtwitter.Twitter

import _root_.android.app.Activity
import _root_.android.os.Bundle
import _root_.android.util.Log
import _root_.android.view.View
import _root_.android.view.View.OnClickListener
import _root_.android.widget.Button
import _root_.android.widget.EditText

class StatusActivity extends Activity with TypedActivity with OnClickListener {
  private val TAG:String = "StatusActivity"
  var twitter: Twitter = null
  var editText: EditText = null
  var updateButton: Button = null

  override def onCreate(bundle: Bundle) {
    super.onCreate(bundle)
    setContentView(R.layout.main)

    editText = findViewById( R.id.editText ).asInstanceOf[ EditText ]
    updateButton = findViewById( R.id.buttonUpdate ).asInstanceOf[ Button ]
    updateButton.setOnClickListener( this )
    twitter = new Twitter( "student", "password" )
    twitter.setAPIRootUrl( "http://yamba.marakana.com/api" )
  }

  override def onClick( v: View ) {
   twitter.setStatus( editText.getText.toString )
   Log.d( TAG, "onClicked" )
  }
}
