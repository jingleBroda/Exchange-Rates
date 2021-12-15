package com.example.exchangerates.presentation.fragment.monetaryFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exchangerates.R
import com.example.exchangerates.databinding.FragmentMonetaryRateMenuBinding
import com.example.exchangerates.presentation.adapter.MoneyRecViewAdapter
import com.example.exchangerates.presentation.dialog.calculateBottomFragment.CalculateBottomSheetFragment
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MonetaryRateMenuFragment :DaggerFragment()  {

   private lateinit var binding:FragmentMonetaryRateMenuBinding

   @Inject
   lateinit var viewModelFactory: ViewModelProvider.Factory
   lateinit var viewModel: MonetaryRateMenuFragmentViewModel

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
        viewModel = ViewModelProviders.of(this, viewModelFactory)[MonetaryRateMenuFragmentViewModel::class.java]


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