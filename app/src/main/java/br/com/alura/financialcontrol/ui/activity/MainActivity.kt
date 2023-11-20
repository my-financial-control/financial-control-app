package br.com.alura.financialcontrol.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.financialcontrol.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        MainActivityBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(this.binding.root)
    }

}
