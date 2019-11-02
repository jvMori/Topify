package com.jvmori.topify.view.customViews

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.jvmori.topify.R
import kotlinx.android.synthetic.main.items_view_section.view.*

class ItemsViewSection(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    init {
        inflate(context, R.layout.items_view_section, this)
        //val titleTextView: TextView = findViewById(R.id.titleTextView)

        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.ItemsViewSection,
            0,
            0
        ).apply {
            try {
                sectionName.text = getString(R.styleable.ItemsViewSection_sectionName)

            } finally {
                recycle()
            }
        }
    }

    fun setSectionName(title: String) {
        sectionName.text = title
    }

    fun setRecyclerView() {

    }
}