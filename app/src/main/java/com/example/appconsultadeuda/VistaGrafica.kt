package com.example.appconsultadeuda

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.anychart.AnyChart
import com.anychart.AnyChartView
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.example.appconsultadeuda.databinding.ActivityVistaGraficaBinding


class VistaGrafica : AppCompatActivity() {
    private lateinit var binding : ActivityVistaGraficaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVistaGraficaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        grafica()
    }

    private fun grafica() {
        val pie = AnyChart.pie()

        val data: MutableList<DataEntry> = ArrayList()
        data.add(ValueDataEntry("John", 10000))
        data.add(ValueDataEntry("Jake", 12000))
        data.add(ValueDataEntry("Peter", 18000))

        pie.data(data)

        pie.innerRadius("75%")

        pie.legend(false)

        pie.normal().outline().enabled(true)

        binding.anyChartView.setChart(pie)
    }
}