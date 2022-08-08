package com.example.appconsultadeuda.UI

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appconsultadeuda.Adapter.AdtCuota
import com.example.appconsultadeuda.DATAGLOBAL
import com.example.appconsultadeuda.R
import com.example.appconsultadeuda.api.CuotaApi
import com.example.appconsultadeuda.databinding.FrgAllListCuotaBinding
import com.example.appconsultadeuda.databinding.FrgDetalleCuotaBinding
import com.example.appconsultadeuda.entity.DataCuota.Cuota
import com.example.appconsultadeuda.entity.DataCuota.dataCuota
import com.unosoft.ecomercialapp.api.APIClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class frgAllListCuota : Fragment() {
    private var _binding: FrgAllListCuotaBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapterListaCuota: AdtCuota
    private val listaConsultaCuota = ArrayList<dataCuota>()
    private val listaConsultaCuotaDetalle = ArrayList<Cuota>()

    var apiInterface: CuotaApi? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?
    ): View {_binding = FrgAllListCuotaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        apiInterface = APIClient.client?.create(CuotaApi::class.java)

        eventsHandlers()
        iniciarCuota()
        getDataCuota()
    }

    private fun eventsHandlers() {

    }
    private fun iniciarSpinner() {
        val listano = ArrayList<String>()

        listano.add("Todos")
        listaConsultaCuota.forEach {
            listano.add(it.año.toString())
        }


        val spFiltroAno = binding.spFiltroAno
        val AdaptadorVendedor = ArrayAdapter(requireActivity(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, listano)
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

                Toast.makeText(requireActivity(), item, Toast.LENGTH_SHORT).show()

            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

    }
    private fun iniciarCuota() {
        binding.rvCunsultaStocks.layoutManager =
            LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false)
        adapterListaCuota = AdtCuota(listaConsultaCuota)
        binding.rvCunsultaStocks.adapter = adapterListaCuota
    }
    private fun getDataCuota() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = apiInterface!!.getRuc("${DATAGLOBAL.prefs.getUser()}")
            requireActivity().runOnUiThread{
                listaConsultaCuota.clear()
                listaConsultaCuota.addAll(response.body()!!)
                adapterListaCuota.notifyDataSetChanged()
                iniciarSpinner()
            }
        }
    }
    fun getDataCuotDetalle(ano:String) {
        val pd = ProgressDialog(requireActivity())
        pd.setMessage("Validando usuario....")
        pd.setCancelable(false)
        pd.create()
        pd.show()

        CoroutineScope(Dispatchers.IO).launch {
            val response = apiInterface!!.getAnoRuc(DATAGLOBAL.prefs.getUser(),ano)
            requireActivity().runOnUiThread{
                listaConsultaCuota.clear()
                listaConsultaCuota.addAll(response.body()!!)
                adapterListaCuota.notifyDataSetChanged()
                pd.cancel()
            }
        }
    }



}