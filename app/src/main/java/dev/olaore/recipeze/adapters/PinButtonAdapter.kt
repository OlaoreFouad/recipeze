package dev.olaore.recipeze.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.olaore.recipeze.R

class PinButtonAdapter(
    private val onPinButtonClicked: OnPinButtonClicked
) : RecyclerView.Adapter<PinButtonAdapter.PinButtonViewHolder>() {

    private val pinNumberList = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, -1, 0, -2)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PinButtonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pin_button, parent, false)
        return PinButtonViewHolder(view, onPinButtonClicked)
    }

    override fun onBindViewHolder(holder: PinButtonViewHolder, position: Int) {
        holder.bind(pinNumberList[position])
    }

    override fun getItemCount(): Int {
        return pinNumberList.size
    }

    class PinButtonViewHolder(private val view: View, private val onPinButtonClicked: OnPinButtonClicked) : RecyclerView.ViewHolder(view) {

        fun bind(number: Int) {
            val index = adapterPosition
            view.setOnClickListener { onPinButtonClicked.onClick(number.toString(), index == 11) }
            if (index != 9 && index != 11) {
                (view as TextView).text = "$number"
            } else if (index == 11) {
                (view as TextView).text = view.resources.getString(R.string.backspace)
            }
        }

    }

}

interface OnPinButtonClicked {

    fun onClick(value: String, backspace: Boolean = false): Unit

}