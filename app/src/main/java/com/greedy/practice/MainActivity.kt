package com.greedy.practice

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.CompoundButton
import android.widget.SeekBar
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import com.greedy.practice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    val listener by lazy {
        CompoundButton.OnCheckedChangeListener{ button, isChecked ->
            if(isChecked){
                when(button.id){
                    R.id.checkBox -> Log.d("check", "todo를 완료하였습니다.")
                    R.id.checkBox2 -> Log.d("check", "todo를 완료하였습니다.")
                    R.id.checkBox3 -> Log.d("check", "todo를 완료하였습니다.")
                    R.id.checkBox4 -> Log.d("check", "todo를 완료하였습니다.")
                }
            }else{
                when(button.id){
                    R.id.checkBox -> Log.d("check", "todo 완료가 취소 되었습니다.")
                    R.id.checkBox2 -> Log.d("check", "todo 완료가 취소 되었습니다.")
                    R.id.checkBox3 -> Log.d("check", "todo 완료가 취소 되었습니다.")
                    R.id.checkBox4 -> Log.d("check", "todo 완료가 취소 되었습니다.")
                }
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.checkBox.setOnCheckedChangeListener(listener)
        binding.checkBox2.setOnCheckedChangeListener(listener)
        binding.checkBox3.setOnCheckedChangeListener(listener)
        binding.checkBox4.setOnCheckedChangeListener(listener)

        binding.seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.number.text = "$progress"

            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }

        })

        val intnet = Intent(this,SubActivity::class.java)

        intent.putExtra("from1", "오늘의 할일!")
        intent.putExtra("from2",2022)


        val resultListener = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            /* result의 resultCode가 RESULT_OK라면 */
            if(it.resultCode == Activity.RESULT_OK) {
                /* it.data 자체가 리턴 받은 intent 객체이다.
                *  returnValue라는 key 값으로 intent에 전달 된 값을 message로 꺼내고 */
                val message = it.data?.getStringExtra("returnValue")
                /* MainActivity 위에 짧은 길이로 전달받은 message를 Toast 위젯으로 보여준다. */
                Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
            }
        }

        /* resultListener를 이용한 subactivity 동작 */
        binding.btnAdd.setOnClickListener {
            resultListener.launch(intent)
        }




    }
}