package br.com.alura.financialcontrol.ui.activity

import android.content.Intent
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
        configNavigation()
    }

    private fun configNavigation() {
        val newTransactionCardView = binding.newTransactionMaterialCardView
        newTransactionCardView.setOnClickListener {
            val intent = Intent(this, NewTransactionActivity::class.java)
            startActivity(intent)
        }

        val seeExtractTextView = binding.seeExtractTextView
        seeExtractTextView.setOnClickListener {
            val intent = Intent(this, ExtractActivity::class.java)
            startActivity(intent)
        }
    }

}
