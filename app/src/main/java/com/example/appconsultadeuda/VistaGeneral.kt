package com.example.appconsultadeuda


import android.app.ProgressDialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appconsultadeuda.Adapter.AdtCuota
import com.example.appconsultadeuda.DATAGLOBAL.Companion.prefs
import com.example.appconsultadeuda.api.CuotaApi
import com.example.appconsultadeuda.databinding.ActivityVistaGeneralBinding
import com.example.appconsultadeuda.entity.DataCuota.Cuota
import com.example.appconsultadeuda.entity.DataCuota.dataCuota
import com.unosoft.ecomercialapp.api.APIClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class VistaGeneral : AppCompatActivity() {
    private lateinit var binding : ActivityVistaGeneralBinding

    private lateinit var adapterListaCuota: AdtCuota
    private val listaConsultaCuota = ArrayList<dataCuota>()
    private val listaConsultaCuotaDetalle = ArrayList<Cuota>()

    var apiInterface: CuotaApi? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVistaGeneralBinding.inflate(layoutInflater)
        setContentView(binding.root)
        apiInterface = APIClient.client?.create(CuotaApi::class.java)


        eventsHandlers()
        iniciarCuota()
        getDataCuota()
    }

    private fun eventsHandlers() {
        funcionTollbar()
    }
    private fun iniciarSpinner() {
        val listano = ArrayList<String>()

        listano.add("Todos")
        listaConsultaCuota.forEach {
            listano.add(it.año.toString())
        }


        val spFiltroAno = binding.spFiltroAno
        val AdaptadorVendedor = ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, listano)
        spFiltroAno.adapter = AdaptadorVendedor

        spFiltroAno.setSelection(0)

        spFiltroAno.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val item: String = parent!!.getItemAtPosition(position).toString()

                binding.btBuscar.setOnClickListener {
                    if(item=="Todos"){
                        getDataCuota()
                    }else{
                        getDataCuotDetalle(item)
                    }
                }

                Toast.makeText(this@VistaGeneral, item, Toast.LENGTH_SHORT).show()

            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

    }
    private fun iniciarCuota() {
        binding.rvCunsultaStocks.layoutManager =LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        adapterListaCuota = AdtCuota(listaConsultaCuota)
        binding.rvCunsultaStocks.adapter = adapterListaCuota
    }
    private fun getDataCuota() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = apiInterface!!.getRuc("${prefs.getUser()}")
            runOnUiThread{

                listaConsultaCuota.clear()
                listaConsultaCuota.addAll(response.body()!!)
                adapterListaCuota.notifyDataSetChanged()
                iniciarSpinner()
            }
        }
    }
    fun getDataCuotDetalle(ano:String) {
        val pd = ProgressDialog(this)
        pd.setMessage("Validando usuario....")
        pd.setCancelable(false)
        pd.create()
        pd.show()

        CoroutineScope(Dispatchers.IO).launch {
            val response = apiInterface!!.getAnoRuc(prefs.getUser(),ano)
            runOnUiThread{
                listaConsultaCuota.clear()
                listaConsultaCuota.addAll(response.body()!!)
                adapterListaCuota.notifyDataSetChanged()
                pd.cancel()
            }
        }
    }

    private fun funcionTollbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.title = null

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_login, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.item_configuracion -> {
                super.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }



    private fun iniciarData() {
        listaConsultaCuotaDetalle.add(Cuota("ENERO","01/01/2000","300.0","PAGADO","0001",1))
        listaConsultaCuotaDetalle.add(Cuota("FEBRERO","01/02/2000","300.0","PAGADO","0001",2))
        listaConsultaCuotaDetalle.add(Cuota("MARZO","01/03/2000","300.0","PAGADO","0001",3))
        listaConsultaCuotaDetalle.add(Cuota("ABRIL","01/04/2000","300.0","PAGADO","0001",4))
        listaConsultaCuotaDetalle.add(Cuota("MAYO","01/05/2000","300.0","PAGADO","0001",5))
        listaConsultaCuotaDetalle.add(Cuota("JUNIO","01/06/2000","300.0","PAGADO","0001",6))
        listaConsultaCuotaDetalle.add(Cuota("JULIO","01/07/2000","300.0","VENCIDO","0001",7))
        listaConsultaCuotaDetalle.add(Cuota("AGOSTO","01/08/2000","300.0","PENDIENTE","0001",8))
        listaConsultaCuotaDetalle.add(Cuota("SETIEMBRE","01/09/2000","300.0","PENDIENTE","0001",9))
        listaConsultaCuotaDetalle.add(Cuota("OCTUBRE","01/10/2000","300.0","PENDIENTE","0001",10))
        listaConsultaCuotaDetalle.add(Cuota("NOVIEMBRE","01/11/2000","300.0","PENDIENTE","0001",11))
        listaConsultaCuotaDetalle.add(Cuota("DICIEMBRE","01/12/2000","300.0","PENDIENTE","0001",12))
        listaConsultaCuota.add(dataCuota(2000,listaConsultaCuotaDetalle))
        listaConsultaCuota.add(dataCuota(2001,listaConsultaCuotaDetalle))
        listaConsultaCuota.add(dataCuota(2002,listaConsultaCuotaDetalle))
        listaConsultaCuota.add(dataCuota(2003,listaConsultaCuotaDetalle))
        listaConsultaCuota.add(dataCuota(2004,listaConsultaCuotaDetalle))
        listaConsultaCuota.add(dataCuota(2005,listaConsultaCuotaDetalle))
        adapterListaCuota.notifyDataSetChanged()
    }

}