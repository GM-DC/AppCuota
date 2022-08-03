package com.example.appconsultadeuda.Adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appconsultadeuda.R
import com.example.appconsultadeuda.entity.DataCuota.dataCuota

class AdtCuota (var data:ArrayList<dataCuota>): RecyclerView.Adapter<AdtCuota.holderConsultaDataDeuda>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): holderConsultaDataDeuda {
        val layoutInflater = LayoutInflater.from(parent.context)
        return holderConsultaDataDeuda(layoutInflater.inflate(R.layout.card_data_ano,parent,false))
    }

    override fun onBindViewHolder(holder: holderConsultaDataDeuda, position: Int) {
        holder.holderConsultaStock(data[position])

    }

    override fun getItemCount(): Int {
        return data.size
    }

    class holderConsultaDataDeuda(private val view: View): RecyclerView.ViewHolder(view){
        fun holderConsultaStock (data: dataCuota){
            val tv_ano=view.findViewById<TextView>(R.id.tv_ano)
            val rv_meses=view.findViewById<RecyclerView>(R.id.rv_meses)

            tv_ano.text = data.año.toString()
            tv_ano.setTextColor(ContextCompat.getColor(this.itemView.context, R.color.colorPrimary))


            rv_meses?.layoutManager = GridLayoutManager(this.itemView.context,2, RecyclerView.VERTICAL,false)
            val childRecyclerAdapter = AdtCuoataMes(data.cuotas)
            rv_meses.adapter = childRecyclerAdapter

        }
    }

    fun filterList(nameItemBuscado: ArrayList<dataCuota>) {
        data = nameItemBuscado
        notifyDataSetChanged()
    }

}