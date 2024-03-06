package ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import epicarchitect.calendar.compose.basis.config.rememberBasisEpicCalendarConfig
import epicarchitect.calendar.compose.datepicker.EpicDatePicker
import epicarchitect.calendar.compose.datepicker.config.rememberEpicDatePickerConfig
import epicarchitect.calendar.compose.datepicker.state.EpicDatePickerState
import epicarchitect.calendar.compose.datepicker.state.rememberEpicDatePickerState
import epicarchitect.calendar.compose.pager.config.rememberEpicCalendarPagerConfig
import kotlinx.datetime.LocalDate

@Composable
fun Calendar(
    onSelectedDates: (List<LocalDate>) -> Unit
) {
    val state = rememberEpicDatePickerState(
        selectionMode = EpicDatePickerState.SelectionMode.Single(maxSize = Int.MAX_VALUE),
        config = rememberEpicDatePickerConfig(
            pagerConfig = rememberEpicCalendarPagerConfig(
                basisConfig = rememberBasisEpicCalendarConfig(
                    displayDaysOfAdjacentMonths = false
                )
            ),
            selectionContentColor = MaterialTheme.colorScheme.onPrimary,
            selectionContainerColor = MaterialTheme.colorScheme.primary
        )
    )
    Column {
        Text("current year and month: ${state.pagerState.currentMonth.year} ${state.pagerState.currentMonth.month.name}")
        EpicDatePicker(
            state = state
        )
    }

    LaunchedEffect(state.selectedDates) {
        // dates changed
        onSelectedDates(state.selectedDates)
    }
}