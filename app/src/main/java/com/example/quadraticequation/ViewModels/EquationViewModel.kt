import androidx.lifecycle.ViewModel
import com.example.quadraticequation.Models.EquationModel
import com.example.quadraticequation.MyMath
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map

class EquationViewModel {
    class EquationViewModel : ViewModel() {

        private val _equationFlow: MutableStateFlow<EquationModel> =
            MutableStateFlow(EquationModel())
        private val equationFlow: StateFlow<EquationModel> = _equationFlow

        val rootsFlow: Flow<Pair<Double,Double>> = equationFlow.map { equation ->
            MyMath()
                .calculation(equation.a, equation.b, equation.c)
                .let {
                    Pair(it[0], it[1])
                }
        }
        fun setEquation(a: Double, b: Double, c: Double) {
            _equationFlow.value = EquationModel(a, b, c)
        }
    }
}