package dev.olaore.recipeze.fragments.recipe

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.olaore.recipeze.R
import dev.olaore.recipeze.models.domain.RecipeIngredient
import dev.olaore.recipeze.models.domain.RecipeInstruction
import dev.olaore.recipeze.obtainParentViewModel
import dev.olaore.recipeze.viewmodels.RecipeViewModel

class RecipeInstructionsFragment : Fragment() {

    private lateinit var recipeViewModel: RecipeViewModel
    private lateinit var recipeInstructionsList: RecyclerView
    private lateinit var recipeInstructionsAdapter: InstructionsAdapter

    private var instructions = mutableListOf<RecipeInstruction>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipe_instructions, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recipeViewModel = obtainParentViewModel(RecipeViewModel::class.java)
        recipeInstructionsList = view.findViewById(R.id.recipeInstructionsList)

        recipeViewModel.instructions.observe(viewLifecycleOwner, Observer {
            provideInstructions(it.toMutableList())
        })

    }

    private fun provideInstructions(instructions: MutableList<RecipeInstruction>) {
        this.instructions.clear()
        this.instructions.addAll(instructions)

        this.recipeInstructionsAdapter = InstructionsAdapter(
            requireContext(), this.instructions
        )

        this.recipeInstructionsList.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = recipeInstructionsAdapter
        }
    }

}

class InstructionsAdapter(
    private var ctx: Context, private var instructions: MutableList<RecipeInstruction>
) : RecyclerView.Adapter<InstructionsAdapter.InstructionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstructionViewHolder {
        return InstructionViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.item_recipe_instruction, parent, false
        ))
    }

    override fun onBindViewHolder(holder: InstructionViewHolder, position: Int) {
        val instructionContent = instructions[position]
        holder.bind(instructionContent, position)
    }

    override fun getItemCount(): Int {
        return instructions.size
    }

    class InstructionViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

        private var instructionIndex: TextView = view.findViewById(R.id.instruction_index)
        private var instructionContent: TextView = view.findViewById(R.id.instruction_content)

        fun bind(instruction: RecipeInstruction, index: Int) {
            instructionIndex.text = "${index + 1}"
            instructionContent.text = instruction.step
        }

    }

}