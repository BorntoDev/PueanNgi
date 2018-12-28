package com.borntodev.trithep.pueanterngijakaira

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import kotlinx.android.synthetic.main.activity_answer.*
import java.io.ByteArrayOutputStream
import kotlin.random.Random

class AnswerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer)

        if(intent.extras != null){
            var question = intent.extras.get("question").toString() + " ?"
            tv_question.text = question

            var answer = getRandomAnswer()

            var imageId = resources.getIdentifier(answer[0],"drawable",packageName)

            iv_friend_ic.setImageResource(imageId)

            tv_answer.text = answer[1]

        }

        btn_play_again.setOnClickListener {
            finish()
        }

        btn_share.setOnClickListener {
            var shareBitmap = getBitmapFromView(result_layout)
            var shareUri = getUriFromBitmap(shareBitmap)
            shareImage(shareUri)
        }
    }

    private fun getRandomAnswer():List<String>{
        var answerList = HashMap<String,List<String>>()
        answerList.put("0",listOf("ic_004_friendship","จัดไปชุดใหญ่ไฟกระพริบ"))
        answerList.put("1",listOf("ic_005_friendship_1","ดีอย่างงี้ต้องไม่พลาด"))
        answerList.put("2",listOf("ic_006_friendship_2","ต้องลองดูซักตั้ง"))
        answerList.put("3",listOf("ic_019_mad","อย่าดีกว่ามั้งงงงง"))
        answerList.put("4",listOf("ic_025_hug","โอ๋ๆๆๆๆๆ\nกอดๆ ไม่เป็นไรนะ"))
        answerList.put("5",listOf("ic_002_hangout","ลองออกเดินทางแล้วจะรู้คำตอบของชีวิต"))
        answerList.put("6",listOf("ic_007_memories","บางอย่างก็ควรเก็บไว้แค่ความทรงจำ"))
        answerList.put("7",listOf("ic_011_high_five","อย่าดีกกว่าเลย!"))
        answerList.put("8",listOf("ic_018_pinky_promise","สัญญาว่าจะทำให้ดีที่สุด"))
        answerList.put("9",listOf("ic_020_bracelet","แตกต่างแต่ไม่แตกแยก \n รออะไรล่ะ!!"))
        answerList.put("10",listOf("ic_036_handshake","จับมือไว้แล้วไปด้วยกัน!!"))

        var random = Random.nextInt(0,answerList.size)
        var answer = answerList[random.toString()]

        return answer!!
    }

    private fun shareImage(imageUri: Uri){
        val share = Intent(Intent.ACTION_SEND)
        share.putExtra(Intent.EXTRA_TEXT,"PueanTerNgi")
        share.putExtra(Intent.EXTRA_STREAM,imageUri)
        share.type = "image/*"
        share.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        startActivity(Intent.createChooser(share,"Share Image"))
    }

    private fun getBitmapFromView(view: View):Bitmap {
        val returnedBitmap = Bitmap.createBitmap(view.width,view.height,Bitmap.Config.ARGB_8888)
        val canvas = Canvas(returnedBitmap)
        val bgDrawable = view.background
        if(bgDrawable != null){
            bgDrawable!!.draw(canvas)
        }else{
            canvas.drawColor(Color.WHITE)
        }

        view.draw(canvas)
        return returnedBitmap
    }

    private fun getUriFromBitmap(bitmap:Bitmap): Uri{
        val bytes = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,bytes)
        val path = MediaStore.Images.Media.insertImage(this.contentResolver,bitmap,"PueanTerNgi",null)
        return Uri.parse(path)
    }


}
