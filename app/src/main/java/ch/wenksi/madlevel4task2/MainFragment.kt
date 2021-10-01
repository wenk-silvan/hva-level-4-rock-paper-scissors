package ch.wenksi.madlevel4task2

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ch.wenksi.madlevel4task2.databinding.FragmentMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.Instant
import java.util.*

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var gameResultRepository: GameResultRepository
    private val mainScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gameResultRepository = GameResultRepository(requireContext())
        initImageButtons()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initImageButtons() {
        binding.ibGameRock.setOnClickListener {
            play(Hand.Rock, getRandomHand())
        }
        binding.ibGamePaper.setOnClickListener {
            play(Hand.Paper, getRandomHand())
        }
        binding.ibGameScissors.setOnClickListener {
            play(Hand.Scissors, getRandomHand())
        }
    }

    private fun displayHandsAndResult(player: Hand, computer: Hand, result: Result) {
        binding.ivHandPlayer.setImageResource(player.imageResourceId)
        binding.ivHandComputer.setImageResource(computer.imageResourceId)
        binding.tvResult.text = result.text
    }

    private fun getRandomHand(): Hand = listOf(Hand.Rock, Hand.Paper, Hand.Scissors).random()

    private fun play(player: Hand, computer: Hand) {
        val result: Result = when(player) {
            Hand.Rock -> when(computer) {
                Hand.Rock -> Result.Draw
                Hand.Paper -> Result.Lose
                Hand.Scissors -> Result.Win
            }
            Hand.Paper -> when(computer) {
                Hand.Rock -> Result.Win
                Hand.Paper -> Result.Draw
                Hand.Scissors -> Result.Lose
            }
            Hand.Scissors -> when(computer) {
                Hand.Rock -> Result.Lose
                Hand.Paper -> Result.Win
                Hand.Scissors -> Result.Draw
            }
        }
        displayHandsAndResult(player, computer, result)
        storeResult(player, computer, result)
    }

    private fun storeResult(player: Hand, computer: Hand, result: Result) {
        val gameResult = GameResult(result.text, Date().time, player.text, computer.text)
        mainScope.launch {
            withContext(Dispatchers.IO) {
                gameResultRepository.insertGameResult(gameResult)
            }
        }
    }
}