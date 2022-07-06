package com.ejemplo1.cardviewitemsrecyclerview

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.ejemplo1.cardviewitemsrecyclerview.Adapter.RecyclerAdapter
import com.ejemplo1.cardviewitemsrecyclerview.Model.Revista
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.util.ArrayList


class MainActivity : AppCompatActivity() {

    private var recyclerViewRevista: RecyclerView? = null
    private var adapterRevista: RecyclerAdapter? = null


    private var edtxtId: EditText? = null
    private var imgFind: ImageView? = null
    private var requestQue: RequestQueue? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        edtxtId = findViewById<View>(R.id.dtxTypeId) as EditText
        imgFind = findViewById<View>(R.id.imgBtnFind) as ImageButton
        recyclerViewRevista = findViewById<View>(R.id.reclyclerRevistas) as RecyclerView
        recyclerViewRevista!!.layoutManager = LinearLayoutManager(this)
        recyclerViewRevista!!.itemAnimator = DefaultItemAnimator()
        imgFind!!.setOnClickListener { searchCover() }
    }


    private fun searchCover() {
        val url = "https://revistas.uteq.edu.ec/ws/issues.php?j_id=" + edtxtId!!.text.toString()
        val requestJson = JsonArrayRequest(
            Request.Method.GET, url, null,
            { response -> showCoverText(response) }
        ) { error ->
            Toast.makeText(
                applicationContext,
                "Error al conectarse:" + error.message,
                Toast.LENGTH_LONG
            ).show()
        }
        requestQue = Volley.newRequestQueue(this)
        requestQue?.add(requestJson)
    }
    private fun showCoverText(jArray: JSONArray) {
        val revistas: MutableList<Revista> = ArrayList<Revista>()
        for (i in 0 until jArray.length()) {
            try {
                val objectJson = JSONObject(jArray[i].toString())
                revistas.add(
                    Revista(
                        objectJson.getString("title"),
                        objectJson.getString("cover"),
                        objectJson.getString("volume"),
                        objectJson.getString("year"),
                        objectJson.getString("number")
                    )
                )
            } catch (e: JSONException) {
                Toast.makeText(this, "Error al cargar lista: " + e.message, Toast.LENGTH_LONG)
                    .show()
            }
        }
        adapterRevista = RecyclerAdapter(this@MainActivity, revistas)
        val id: Int = R.anim.layout_animation_down_up
        val animation = AnimationUtils.loadLayoutAnimation(
            applicationContext,
            id
        )
        recyclerViewRevista!!.layoutAnimation = animation
        recyclerViewRevista!!.adapter = adapterRevista
    }
}
