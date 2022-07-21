package cz.jannejezchleba.appliftingspacex.ui.component

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cz.jannejezchleba.appliftingspacex.R
import cz.jannejezchleba.appliftingspacex.ui.theme.CustomMaterialTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@Composable
fun DatePicker(
    modifier: Modifier = Modifier,
    title: String,
    value: LocalDate?,
    scope: CoroutineScope,
    onChange: (String?) -> Unit
) {
    val context = LocalContext.current

    val anyString = stringResource(id = R.string.MISC_ANY)

    val dateFormatShow = SimpleDateFormat(
        stringResource(id = R.string.DATE_FORMAT_DISPLAYED), Locale.ENGLISH
    )
    val dateFormatSave = SimpleDateFormat(
        "yyyy-MM-dd", Locale.ENGLISH
    )
    val formatter = DateTimeFormatter.ofPattern(stringResource(id = R.string.DATE_FORMAT_DISPLAYED))

    val calendar = remember {
        Calendar.getInstance()
    }
    val year = remember {
        value?.year ?: calendar.get(Calendar.YEAR)
    }
    val month = remember {
        value?.month?.value ?: calendar.get(Calendar.MONTH)
    }
    val day = remember {
        value?.dayOfMonth ?: calendar.get(Calendar.DAY_OF_MONTH)
    }

    var date by remember {
        mutableStateOf(value?.format(formatter) ?: anyString)
    }

    val datePickerDialog = remember {
        DatePickerDialog(
            context,
            { _: DatePicker, year: Int, month: Int, day: Int ->
                calendar.set(year, month, day)
                date = dateFormatShow.format(calendar.time)
                onChange(dateFormatSave.format(calendar.time))
            }, year, month, day
        ).apply {
            this.setButton(DatePickerDialog.BUTTON_POSITIVE, "CONFIRM", this)
            this.setButton(DatePickerDialog.BUTTON_NEGATIVE, "CANCEL", this)
        }
    }



    Column(
        modifier
            .fillMaxWidth()
            .padding(CustomMaterialTheme.padding.S),
        verticalArrangement = Arrangement.spacedBy(CustomMaterialTheme.padding.XS),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = title)
        Row(
            modifier = Modifier.fillMaxWidth().padding(CustomMaterialTheme.padding.XS),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = CustomMaterialTheme.colors.secondary,
                        shape = CustomMaterialTheme.shapes.medium
                    )
                    .padding(CustomMaterialTheme.padding.XS)
                    .clickable {
                        scope.launch {
                            datePickerDialog.show()
                        }
                    }) {
                Text(
                    text = date,
                    style = CustomMaterialTheme.typography.subtitle1,
                    modifier = Modifier
                        .fillMaxWidth(0.8F)
                        .padding(CustomMaterialTheme.padding.S)
                )
                if (date != anyString) {
                    IconButton(
                        onClick = {
                            date = anyString
                            onChange(null)
                        }
                    )
                    {
                        Icon(
                            Icons.Default.Close,
                            contentDescription = "close",
                        )
                    }
                } else {
                    IconButton(
                        onClick = {
                            scope.launch {
                                datePickerDialog.show()
                            }
                        }
                    )
                    {
                        Icon(
                            Icons.Default.CalendarToday,
                            contentDescription = "calendar",
                        )
                    }
                }
            }
        }
    }
}