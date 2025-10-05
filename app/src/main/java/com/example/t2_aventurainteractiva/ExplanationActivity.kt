package com.example.t2_aventurainteractiva

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class ExplanationActivity : AppCompatActivity() {

    private val componentData = mapOf(
        "cpu_socket" to Pair(R.string.cpu_socket_title, R.string.cpu_socket_description),
        "ram_slots" to Pair(R.string.ram_slots_title, R.string.ram_slots_description),
        "pcie_slots" to Pair(R.string.pcie_slots_title, R.string.pcie_slots_description)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explanation)

        // Apply enter animation
        overridePendingTransition(R.anim.slide_in_up, R.anim.fade_out)

        val titleTextView: TextView = findViewById(R.id.titleTextView)
        val descriptionTextView: TextView = findViewById(R.id.descriptionTextView)
        val backButton: Button = findViewById(R.id.backButton)

        // Retrieve component ID from Intent
        val componentId = intent.getStringExtra(getString(R.string.component_id_extra))

        val prefix = "component@description:~$ "
        val description: String

        if (componentId != null) {
            val componentInfo = componentData[componentId]
            if (componentInfo != null) {
                val (titleResId, descriptionResId) = componentInfo
                titleTextView.text = getString(titleResId)
                description = getString(descriptionResId)
            } else {
                titleTextView.text = getString(R.string.error_title)
                description = getString(R.string.component_info_not_found)
            }
        } else {
            titleTextView.text = getString(R.string.error_title)
            description = getString(R.string.could_not_load_component_info)
        }

        val fullText = "$prefix\n$description"
        val spannable = SpannableString(fullText)

        // Color for the prefix
        val prefixColor = ContextCompat.getColor(this, R.color.colorSecondary)
        spannable.setSpan(
            ForegroundColorSpan(prefixColor),
            0,
            prefix.length,
            Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )

        // Color for the description text
        val descriptionColor = ContextCompat.getColor(this, R.color.textColor)
        val descriptionStart = prefix.length + 1 // +1 for the newline character
        spannable.setSpan(
            ForegroundColorSpan(descriptionColor),
            descriptionStart,
            fullText.length,
            Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )

        descriptionTextView.text = spannable

        backButton.setOnClickListener {
            finish() // Close the current activity
        }
    }

    override fun finish() {
        super.finish()
        // Apply exit animation
        overridePendingTransition(R.anim.fade_in, R.anim.slide_out_down)
    }
}
