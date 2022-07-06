package com.ejemplo1.cardviewitemsrecyclerview.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.RequestQueue
import com.bumptech.glide.Glide
import com.ejemplo1.cardviewitemsrecyclerview.Model.Revista
import com.ejemplo1.cardviewitemsrecyclerview.R


class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.RevistaViewHolder> {
    private var ctx: Context? = null
    var revistaLista: List<Revista>
    private val request: RequestQueue? = null

    constructor(onClickListener: View.OnClickListener?, revistaLista: List<Revista>) {
        this.revistaLista = revistaLista
    }

    class RevistaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtTitle: TextView
        val txtDescription: TextView
        val imgUrlCover: ImageView

        init {
            txtTitle = itemView.findViewById<View>(R.id.txtTitle) as TextView
            txtDescription = itemView.findViewById<View>(R.id.txtDescr) as TextView
            imgUrlCover = itemView.findViewById<View>(R.id.imgCover) as ImageView
        }
    }

    constructor(mCtx: Context?, revistaLista: List<Revista>) {
        ctx = mCtx
        this.revistaLista = revistaLista
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RevistaViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.cuadro_revista, parent, false)
        return RevistaViewHolder(view)
    }

    //Modificación del contenido para cada cardView
    override fun onBindViewHolder(holder: RevistaViewHolder, position: Int) {
        holder.txtTitle.setText(revistaLista[position].title)
        holder.txtDescription.text = "Vol. " + revistaLista[position].volume
            .toString() + " Núm." + revistaLista[position].number
            .toString() + " (" + revistaLista[position].year.toString() + ")"
        ctx?.let { Glide.with(it).load(revistaLista[position].urlImgCover).into(holder.imgUrlCover) }
    }

    override fun getItemCount(): Int {
        return revistaLista.size
    }
}
