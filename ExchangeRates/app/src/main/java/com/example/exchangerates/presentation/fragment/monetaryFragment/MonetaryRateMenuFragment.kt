package com.example.exchangerates.presentation.fragment.monetaryFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exchangerates.R
import com.example.exchangerates.databinding.FragmentMonetaryRateMenuBinding
import com.example.exchangerates.presentation.adapter.MoneyRecViewAdapter
import com.example.exchangerates.presentation.dialog.calculateBottomFragment.CalculateBottomSheetFragment
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class MonetaryRateMenuFragment :DaggerFragment()  {

   private var binding:FragmentMonetaryRateMenuBinding? = null

   @Inject
   lateinit var viewModelFactory: ViewModelProvider.Factory
   lateinit var viewModel: MonetaryRateMenuFragmentViewModel

   private var recViewAdapter:MoneyRecViewAdapter? = null
   private var switchRecViewParam:Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMonetaryRateMenuBinding.bind(inflater.inflate(
             R.layout.fragment_monetary_rate_menu,
             container,
            false
        ))
        this.binding = binding
        viewModel = ViewModelProviders.of(this, viewModelFactory)[MonetaryRateMenuFragmentViewModel::class.java]
        return binding.root
    }



    private fun init(){
        viewModel.makeApi()
        viewModel.makeDbData()
    }

    override fun onStart() {
        super.onStart()
        val binding = this.binding!!
        init()
        lifecycleScope.launchWhenStarted {
            viewModel.dbData.collect { dbData ->
                binding.switchText.setOnClickListener {
                    switchRecViewParam = if (switchRecViewParam) {
                        binding.switchText.setText(R.string.switchTextTwo)
                        recViewAdapter?.updateRecViewInMoneyRoomModel(dbData)
                        false
                    } else {
                        binding.switchText.setText(R.string.switchTextOne)
                        recViewAdapter?.updateRecViewInMoneyModel()
                        true
                    }
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.dataApi.collect { dataApi->

                recViewAdapter = MoneyRecViewAdapter(requireContext(), dataApi.Valute)
                binding.moneyRecView.layoutManager = LinearLayoutManager(activity)
                binding.moneyRecView.hasFixedSize()
                binding.moneyRecView.adapter = recViewAdapter

                recViewAdapter?.setOnClickListner { adapterMoneyModel ->

                    val bottomFrag = CalculateBottomSheetFragment.setArguments(
                        adapterMoneyModel.Value / adapterMoneyModel.Nominal,
                        adapterMoneyModel.CharCode
                    )

                    bottomFrag.show(requireActivity().supportFragmentManager, "tag")

                }


                binding.dataToday.text = dataApi.Date.substring(0, 10)
                binding.moneyRecView.visibility = View.VISIBLE
                binding.dataDefaultText.visibility = View.VISIBLE
                binding.dataToday.visibility = View.VISIBLE
                binding.progressBar.visibility = View.GONE
                binding.switchText.visibility = View.VISIBLE


            }
        }
    }

    override fun onDestroy() {
        viewModel.cancelCoroutine()
        binding = null
        super.onDestroy()
    }
}