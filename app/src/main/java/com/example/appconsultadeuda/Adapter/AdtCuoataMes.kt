package com.example.appconsultadeuda.Adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.appconsultadeuda.R
import com.example.appconsultadeuda.entity.DataCuota.Cuota

class AdtCuoataMes (var data:List<Cuota>): RecyclerView.Adapter<AdtCuoataMes.holderConsultaDataMes>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): holderConsultaDataMes {
        val layoutInflater = LayoutInflater.from(parent.context)
        return holderConsultaDataMes(layoutInflater.inflate(R.layout.card_data_mes,parent,false))
    }

    override fun onBindViewHolder(holder: holderConsultaDataMes, position: Int) {
        holder.holderConsultaStock(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class holderConsultaDataMes(private val view: View): RecyclerView.ViewHolder(view){
        fun holderConsultaStock (data: Cuota){
            val cardDataMes = view.findViewById<LinearLayout>(R.id.cardDataMes)
            val tv_nrocuota = view.findViewById<TextView>(R.id.tv_nrocuota)
            val tv_mes = view.findViewById<TextView>(R.id.tv_mes)
            val tv_fecha = view.findViewById<TextView>(R.id.tv_fecha)
            val tv_estado = view.findViewById<TextView>(R.id.tv_estado)
            val tv_monto = view.findViewById<TextView>(R.id.tv_monto)

            val iv_iconEstado = view.findViewById<ImageView>(R.id.iv_iconEstado)

            tv_nrocuota.text = data.cuota.toString()
            tv_mes.text = data.mes
            tv_fecha.text= data.fechaVencimiento
            tv_estado.text = data.estado
            tv_monto.text = data.importe

            if (data.estado == "PAGADO"){
                iv_iconEstado.setImageResource(R.drawable.ic_check)
                //iv_iconEstado.setColorFilter(ContextCompat.getColor(this.itemView.context, R.color.green))

                tv_nrocuota.setTextColor(ContextCompat.getColor(this.itemView.context, R.color.white))
                tv_mes.setTextColor(ContextCompat.getColor(this.itemView.context, R.color.white))
                tv_fecha.setTextColor(ContextCompat.getColor(this.itemView.context, R.color.white))
                tv_estado.setTextColor(ContextCompat.getColor(this.itemView.context, R.color.white))
                tv_monto.setTextColor(ContextCompat.getColor(this.itemView.context, R.color.white))
                cardDataMes.setBackgroundColor(ContextCompat.getColor(this.itemView.context, R.color.green))
            }
            if (data.estado == "VENCIDO"){
                iv_iconEstado.setImageResource(R.drawable.ic_close)
                //iv_iconEstado.setColorFilter(ContextCompat.getColor(this.itemView.context, R.color.red))

                tv_nrocuota.setTextColor(ContextCompat.getColor(this.itemView.context, R.color.white))
                tv_mes.setTextColor(ContextCompat.getColor(this.itemView.context, R.color.white))
                tv_fecha.setTextColor(ContextCompat.getColor(this.itemView.context, R.color.white))
                tv_estado.setTextColor(ContextCompat.getColor(this.itemView.context, R.color.white))
                tv_monto.setTextColor(ContextCompat.getColor(this.itemView.context, R.color.white))
                cardDataMes.setBackgroundColor(ContextCompat.getColor(this.itemView.context, R.color.red))
            }
            if (data.estado == "PENDIENTE"){
                iv_iconEstado.setImageResource(R.drawable.ic_circle)
                iv_iconEstado.setColorFilter(ContextCompat.getColor(this.itemView.context, R.color.darkGrey))
                cardDataMes.setBackgroundColor(ContextCompat.getColor(this.itemView.context, R.color.white))
            }

        }
    }

    fun filterList(nameItemBuscado: ArrayList<Cuota>) {
        data = nameItemBuscado
        notifyDataSetChanged()
    }

}