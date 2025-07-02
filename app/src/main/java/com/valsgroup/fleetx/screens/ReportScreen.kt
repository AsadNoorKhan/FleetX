package com.valsgroup.fleetx.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.Modifier
import com.valsgroup.fleetx.components.ReportListItem
import com.valsgroup.fleetx.components.SectionHeader
import androidx.compose.runtime.remember
import com.valsgroup.fleetx.R

// Data class for a report entry
sealed class ReportEntry {
    data class Item(val iconRes: Int, val title: String) : ReportEntry()
    data class Header(val title: String) : ReportEntry()
}

@Composable
fun ReportScreen(modifier: Modifier = Modifier) {
    // List of all report entries (headers and items)
    val reportEntries = remember {
        listOf(
            ReportEntry.Header("Activity"),
            ReportEntry.Item(R.drawable.image_70, "Travel"),
            ReportEntry.Item(R.drawable.image_71, "Trip"),
            ReportEntry.Item(R.drawable.image_72, "Stoppage"),
            ReportEntry.Item(R.drawable.image_73, "Idle"),
            ReportEntry.Item(R.drawable.image_74, "Inactive"),
            ReportEntry.Item(R.drawable.image_75, "Speed vs Distance"),
            ReportEntry.Item(R.drawable.image_76, "Daywise Distance"),
            ReportEntry.Item(R.drawable.image_77, "Daywise Work Hour"),
            ReportEntry.Item(R.drawable.image_78, "Overspeed Summary"),
            ReportEntry.Header("Alert"),
            ReportEntry.Item(R.drawable.image_79, "Object Alert"),
            ReportEntry.Item(R.drawable.image_80, "Driver Alert"),
            ReportEntry.Item(R.drawable.image_81, "SMS - Email Status"),
            ReportEntry.Item(R.drawable.image_82, "Alert Status"),
            ReportEntry.Header("Temperature"),
            ReportEntry.Item(R.drawable.image_83, "Temperature Status"),
            ReportEntry.Item(R.drawable.image_84, "Temperature Daily Summary"),
            ReportEntry.Header("Sensor"),
            ReportEntry.Item(R.drawable.image_85, "Digital Ports"),
            ReportEntry.Item(R.drawable.image_86, "RFID Data"),
            ReportEntry.Item(R.drawable.image_87, "Ignition"),
            ReportEntry.Item(R.drawable.image_88, "Air Conditioner"),
            ReportEntry.Item(R.drawable.image_89, "Air Conditioner Misused"),
            ReportEntry.Item(R.drawable.image_90, "Analog Data"),
            ReportEntry.Header("Geofence-Address"),
            ReportEntry.Item(R.drawable.image_91, "Address"),
            ReportEntry.Item(R.drawable.image_92, "Fence Inside Travel Report"),
            ReportEntry.Item(R.drawable.image_93, "Fence Outside Travel Report"),
            ReportEntry.Item(R.drawable.image_94, "Geofence to Geofence Trip Report"),
            ReportEntry.Item(R.drawable.image_95, "Geofence Visited Summary"),
            ReportEntry.Item(R.drawable.image_96, "Address Wise"),
            ReportEntry.Header("Reminder"),
            ReportEntry.Item(R.drawable.image_97, "Reminder Status"),
            ReportEntry.Item(R.drawable.image_98, "Acknowledgement History"),
            ReportEntry.Header("Expense"),
            ReportEntry.Item(R.drawable.image_99, "Expense"),
            ReportEntry.Item(R.drawable.image_100, "Vehicle Cost"),
            ReportEntry.Item(R.drawable.image_101, "Category-Wise Expense"),
            ReportEntry.Item(R.drawable.image_102, "Maintenance History"),
            ReportEntry.Header("Fuel"),
            ReportEntry.Item(R.drawable.image_103, "Fuel Trip Cost"),
            ReportEntry.Item(R.drawable.image_104, "Fuel Fill Data"),
            ReportEntry.Item(R.drawable.image_105, "Fuel Economy Summary"),
            ReportEntry.Item(R.drawable.image_106, "Fuel Abnormal Consumption"),
            ReportEntry.Header("RPM"),
            ReportEntry.Item(R.drawable.image_107, "RPM Summary"),
            ReportEntry.Item(R.drawable.image_108, "RPM Status"),
            ReportEntry.Header("Billing"),
            ReportEntry.Item(R.drawable.image_109, "Object Expiry"),
            ReportEntry.Item(R.drawable.image_110, "Payment"),
            ReportEntry.Header("Tyre"),
            ReportEntry.Item(R.drawable.image_111, "Tyre Event Summary"),
            ReportEntry.Item(R.drawable.image_112, "Tyre Status"),
            ReportEntry.Item(R.drawable.image_113, "Object Tyre Pressure Report"),
            ReportEntry.Header("OBD"),
            ReportEntry.Item(R.drawable.image_114, "Engine Temperature"),
            ReportEntry.Header("Logs"),
            ReportEntry.Item(R.drawable.image_115, "System Log"),
            ReportEntry.Header("Block"),
            ReportEntry.Item(R.drawable.image_116, "Violation Summary"),
            ReportEntry.Item(R.drawable.image_117, "Lock Status"),
            ReportEntry.Item(R.drawable.image_118, "Lock Unlock Summary"),
            ReportEntry.Header("Job"),
            ReportEntry.Item(R.drawable.image_119, "Today Job Summary"),
            ReportEntry.Item(R.drawable.image_120, "Today Job Status"),
            ReportEntry.Item(R.drawable.image_121, "Object Job Summary"),
            ReportEntry.Item(R.drawable.image_122, "Job Alert Summary"),
            ReportEntry.Header("Driver"),
            ReportEntry.Item(R.drawable.image_123, "Driver Job Summary")
        )
    }

    LazyColumn(modifier = modifier) {
        items(reportEntries) { entry ->
            when (entry) {
                is ReportEntry.Header -> SectionHeader(title = entry.title)
                is ReportEntry.Item -> ReportListItem(
                    icon = painterResource(id = entry.iconRes),
                    title = entry.title,
                    onClick = { /* TODO: Handle navigation */ }
                )
            }
        }
    }
} 