package com.example.exchangerates.presentation.fragment.monetaryFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exchangerates.R
import com.example.exchangerates.databinding.FragmentMonetaryRateMenuBinding
import com.example.exchangerates.domain.model.MoneyModel
import com.example.exchangerates.domain.model.MoneyRoomModel
import com.example.exchangerates.presentation.adapter.MoneyRecViewAdapter
import com.example.exchangerates.presentation.fragment.calculateBottomFragment.CalculateBottomSheetFragment

class MonetaryRateMenuFragment : Fragment() {

   private lateinit var binding:FragmentMonetaryRateMenuBinding
   private lateinit var viewModel: MonetaryRateMenuFragmentViewModel
   private lateinit var recViewAdapter: MoneyRecViewAdapter
   private var switchRecViewParam:Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_monetary_rate_menu, container, false)

        init(root)

        return root
    }

    private fun init(root:View){
        binding = FragmentMonetaryRateMenuBinding.bind(root)
        viewModel = ViewModelProvider(this).get(MonetaryRateMenuFragmentViewModel::class.java)

        viewModel.makeApi()
        viewModel.makeDbData()
    }


    override fun onStart() {
        super.onStart()

        viewModel.getDbData {dbData->
            Log.d("StartProject", dbData.toString())

            binding.switchText.setOnClickListener{
                if(switchRecViewParam){
                    binding.switchText.setText(R.string.switchTextTwo)
                    recViewAdapter.updateRecViewInMoneyRoomModel(dbData)
                    switchRecViewParam = false
                }
                else{
                    binding.switchText.setText(R.string.switchTextOne)
                    recViewAdapter.updateRecViewInMoneyModel()
                    switchRecViewParam = true
                }
            }

        }

        viewModel.getApiData { result->

            recViewAdapter = MoneyRecViewAdapter(requireContext(), result.Valute)
            binding.moneyRecView.layoutManager = LinearLayoutManager(activity)
            binding.moneyRecView.hasFixedSize()
            binding.moneyRecView.adapter = recViewAdapter

            recViewAdapter.setOnClickListner { adapterMoneyModel ->

                val bottomFrag = CalculateBottomSheetFragment.setArguments(
                    adapterMoneyModel.Value / adapterMoneyModel.Nominal,
                    adapterMoneyModel.CharCode
                )

                bottomFrag.show(requireActivity().supportFragmentManager, "tag")

            }


            binding.dataToday.text = result.Date.substring(0, 10)
            binding.moneyRecView.visibility = View.VISIBLE
            binding.dataDefaultText.visibility = View.VISIBLE
            binding.dataToday.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE
            binding.switchText.visibility = View.VISIBLE


        }
    }

    override fun onDestroy() {
        viewModel.cleareCompositeDisposable()
        super.onDestroy()
    }

}