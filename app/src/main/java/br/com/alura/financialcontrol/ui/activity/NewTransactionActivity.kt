package br.com.alura.financialcontrol.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.financialcontrol.databinding.NewTransactionActivityBinding

class NewTransactionActivity : AppCompatActivity() {

    private val binding by lazy {
        NewTransactionActivityBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

}