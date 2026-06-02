package com.example.almacenamiento

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.contentfile
import java.io.File


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val path: String = filesDir.absolutePath

        binding.tvwRuta.text = path

        val filename = "datos_usuario.txt"
        val fileContents = "Puntaje: 1500\nNivel: 5"

        openFileOutput(
            filename,
            Context.MODE_PRIVATE
        ).use { output ->
            output.write(fileContents.toByteArray())
        }

        openFileInput("datos_usuario.txt").buffered
        Reader().use { reader ->
            val text = reader.readText()
            binding.tvwContentFile.txt = text
        }

        val arrayArchivos: Array<String> = fileList()
        arrayArchivos.forEach { nombre ->
            Log.i("Stored", nombre)
        }

        val cacheFile =
            File.createTempFile("session_tmp", ".dat",
                cacheDir)
        cacheFile.writeText("ID_Sesion: 0x99283")


    }
}
