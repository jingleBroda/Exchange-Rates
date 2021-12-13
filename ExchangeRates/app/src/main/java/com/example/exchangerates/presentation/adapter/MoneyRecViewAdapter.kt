package com.example.exchangerates.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exchangerates.R
import com.example.exchangerates.databinding.MoneyRecViewRowBinding
import com.example.exchangerates.domain.model.MoneyModel
import com.example.exchangerates.domain.model.MoneyRoomModel

class MoneyRecViewAdapter(context:Context, data: MutableMap<String, MoneyModel>):RecyclerView.Adapter<MoneyRecViewAdapter.MoneyRecViewHolder>() {

    private val recAdaptContext = context
    private val recAdapterData = data

    private var actualMoneyType:ArrayList<MoneyModel> = ArrayList() //хранит либо полный список либо
                                                                    //список с закрепленными валютами

    private var oldMoneyType:ArrayList<MoneyModel> = ArrayList()    //хранит полный список валют

    private var onClickIteam:((item: MoneyModel)->Unit)? = null

    init {

        recAdapterData.forEach {
                (key, value) -> actualMoneyType.add(value)
        }

    }


    inner class MoneyRecViewHolder(view: View):RecyclerView.ViewHolder(view) {

        private val binding = MoneyRecViewRowBinding.bind(view)


        init {
            view.setOnClickListener{
                onClickIteam?.invoke(actualMoneyType.get(adapterPosition))
            }
        }

        fun bind(value: MoneyModel){     //
            binding.name.text = value.Name
            binding.nominal.text = value.Nominal.toString() + " " + value.CharCode+" "
            binding.value.text = " "+value.Value.toString()+" RUB"
        }

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoneyRecViewHolder {
        val inflater = LayoutInflater.from(recAdaptContext)
        return MoneyRecViewHolder(inflater.inflate(R.layout.money_rec_view_row, parent,false))
    }

    override fun onBindViewHolder(holder: MoneyRecViewHolder, position: Int) {
        val singleElemAdapterList = actualMoneyType[position]
        holder.bind(singleElemAdapterList)
    }

    override fun getItemCount(): Int {
        return actualMoneyType.size
    }

    fun setOnClickListner(listner: ((item: MoneyModel)->Unit)){
        onClickIteam = listner
    }

    fun updateRecViewInMoneyRoomModel(newData: List<MoneyRoomModel>){

        val transformerNewData:ArrayList<MoneyModel> = ArrayList()
        for(i in actualMoneyType.indices){
            for(j in newData.indices){
                if(actualMoneyType[i].CharCode==newData[j].charCodeId){
                    transformerNewData.add(actualMoneyType[i])
                }
            }
        }

        oldMoneyType = actualMoneyType
        actualMoneyType = transformerNewData

        notifyDataSetChanged()
    }

    fun updateRecViewInMoneyModel(){
        actualMoneyType = oldMoneyType
        notifyDataSetChanged()
    }



}