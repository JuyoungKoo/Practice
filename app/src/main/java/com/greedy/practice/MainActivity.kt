package com.greedy.practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.CompoundButton
import android.widget.SeekBar
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





    }
}