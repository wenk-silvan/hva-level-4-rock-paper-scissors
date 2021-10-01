package ch.wenksi.madlevel4task2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ch.wenksi.madlevel4task2.databinding.FragmentHistoryBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A simple [Fragment] subclass.
 * Use the [HistoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HistoryFragment : Fragment() {
    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    private lateinit var gameResultRepository: GameResultRepository
    private val gameResults = arrayListOf<GameResult>()
    private val gameResultsAdapter = GameResultsAdapter(gameResults)
    private val mainScope = CoroutineScope(Dispatchers.Main)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gameResultRepository = GameResultRepository(requireContext())
        getStatisticsFromDatabase()
        initRecyclerView()

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initRecyclerView() {
        binding.rvStatistics.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = gameResultsAdapter
            setHasFixedSize(true)
            addItemDecoration(
                DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            )
        }
    }

    private fun getStatisticsFromDatabase() {
        mainScope.launch {
            val results = withContext(Dispatchers.IO) {
                gameResultRepository.getStatistics()
            }
            gameResults.clear()
            gameResults.addAll(results)
            gameResultsAdapter.notifyDataSetChanged()
        }
    }
}