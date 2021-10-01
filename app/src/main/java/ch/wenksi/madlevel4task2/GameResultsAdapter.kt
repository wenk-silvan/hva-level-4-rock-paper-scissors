package ch.wenksi.madlevel4task2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ch.wenksi.madlevel4task2.databinding.ItemGameResultBinding
import java.util.*

class GameResultsAdapter(private val gameResults: List<GameResult>) :
    RecyclerView.Adapter<GameResultsAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemGameResultBinding.bind(itemView)

        fun databind(gameResult: GameResult) {
            binding.tvResult.text = gameResult.result
            binding.tvTimestamp.text = Date(gameResult.timestamp).toString()
            binding.ivHandPlayer.setImageResource(HandHelper.getFromString(gameResult.playerHand).imageResourceId)
            binding.ivHandComputer.setImageResource(HandHelper.getFromString(gameResult.computerHand).imageResourceId)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_game_result, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return gameResults.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.databind(gameResults[position])
    }
}