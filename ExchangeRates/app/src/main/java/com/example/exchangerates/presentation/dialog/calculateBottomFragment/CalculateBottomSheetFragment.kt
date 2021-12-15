package com.example.exchangerates.presentation.dialog.calculateBottomFragment

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import androidx.annotation.CallSuper
import com.example.exchangerates.R
import com.example.exchangerates.databinding.BottomShhetFragmentBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import androidx.lifecycle.ViewModelProviders
import com.example.exchangerates.di.viewModelPropertyInject.ViewModelFactory
import com.example.exchangerates.domain.model.MoneyRoomModel
import com.example.exchangerates.navigator.navigator
import com.example.exchangerates.presentation.fragment.monetaryFragment.MonetaryRateMenuFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class CalculateBottomSheetFragment():BottomSheetDialogFragment(), HasAndroidInjector  {


    private lateinit var binding:BottomShhetFragmentBinding

    @Inject
    lateinit var viewModelFactory:ViewModelFactory
    lateinit var viewModel:BottomSheetViewModel

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any?>

    private var insideCoursCurrency:Double = 0.0
    private var insideNameCurrency:String = ""
    private var editTextFocusParam = true

    companion object{
        val coursCurrencyKey = "coursСurrency"
        val nameCurrencyKey = "nameСurrency"

        fun setArguments(coursCurrency:Double, nameCurrency:String):CalculateBottomSheetFragment{
            val newCalculateBottomFrag = CalculateBottomSheetFragment()
            val arg = Bundle()

            arg.putDouble(coursCurrencyKey, coursCurrency)
            arg.putString(nameCurrencyKey, nameCurrency)

            newCalculateBottomFrag.arguments = arg

            return newCalculateBottomFrag
        }
    }


    override fun androidInjector(): AndroidInjector<Any?> = androidInjector
    @CallSuper
    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory)[BottomSheetViewModel::class.java]

        insideCoursCurrency = requireArguments().getDouble(coursCurrencyKey, 0.0)
        insideNameCurrency = requireArguments().getString(nameCurrencyKey).toString()

        viewModel.makeDbSpecificData(insideNameCurrency)

    }

    override fun getTheme() = R.style.AppBottomSheetDialogTheme

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val root = inflater.inflate(R.layout.bottom_shhet_fragment, container)
        binding = BottomShhetFragmentBinding.bind(root)
        binding.nameSelectCurrency.text = insideNameCurrency
        binding.countRubCurrent.setText(insideCoursCurrency.toString())

        dialog?.setOnShowListener { dialog ->
            val d = dialog as BottomSheetDialog
            val bottomSheet =
                d.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout
            val bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        }

        showKeyBoard()

        return root
    }

    override fun onStart() {
        super.onStart()

        viewModel.getDBSpecificData {moneyRoomModel->

            if(moneyRoomModel.lockStatus){
                binding.lockButton.setImageResource(R.drawable.lock_currency)
                binding.lockButton.visibility = View.VISIBLE
            }
            else{
                binding.lockButton.setImageResource(R.drawable.unclock_currency)
                binding.lockButton.visibility = View.VISIBLE
            }

            binding.lockButton.setOnClickListener{
                AnalizeFunOnClickButtonLock(moneyRoomModel)
            }

            settingEditTextBottomSheet()

        }
    }

    private fun AnalizeFunOnClickButtonLock(moneyRoomModel: MoneyRoomModel){
        if(moneyRoomModel.lockStatus){
            binding.lockButton.setImageResource(R.drawable.unclock_currency)
            viewModel.makeDeleteSingeDataInfoDb(insideNameCurrency)
        }
        else{
            binding.lockButton.setImageResource(R.drawable.lock_currency)
            val newData = MoneyRoomModel(
                insideNameCurrency,
                insideCoursCurrency,
                true
            )
            viewModel.makeSingleInsertDbData(newData)
        }
    }

    private fun settingEditTextBottomSheet(){
        binding.onePointCurrent.text =
            "1" + insideNameCurrency + "=" + insideCoursCurrency.toString() + "RUB"

        binding.countSelectCurrent.setOnFocusChangeListener { view, hasfocus ->
            editTextFocusParam = true
        }

        binding.countRubCurrent.setOnFocusChangeListener { view, hasfocus ->
            editTextFocusParam = false
        }

        binding.countSelectCurrent.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

                if (editTextFocusParam) {
                    if (s.toString() != "") {
                        val newMeaning =
                            (s.toString().toDouble() * insideCoursCurrency).toString()

                        binding.countRubCurrent.setText(
                            newMeaning
                        )
                    } else {
                        val newMeaning = "0"

                        binding.countRubCurrent.setText(
                            newMeaning
                        )
                    }
                }

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })


        binding.countRubCurrent.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (!editTextFocusParam) {

                    if (s.toString() != "") {

                        val newMeaning =
                            (s.toString().toDouble() / insideCoursCurrency).toString()

                        binding.countSelectCurrent.setText(
                            newMeaning
                        )
                    } else {
                        val newMeaning = "0"

                        binding.countSelectCurrent.setText(
                            newMeaning
                        )
                    }
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })

    }

    //установка фокуса на editText и вызов клавиатуры
    private fun showKeyBoard(){
        binding.countSelectCurrent.requestFocus();
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInputFromInputMethod(view?.windowToken, 0);
    }

    override fun onDestroy() {
        viewModel.cleareCompositeDisposable()
        val tmpFragment = MonetaryRateMenuFragment()
        navigator().showNewScreen(tmpFragment)
        super.onDestroy()
    }


}