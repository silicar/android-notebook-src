package cn.wittyneko.notebook.view.cumstor

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import cn.wittyneko.notebook.R

class NestedScrollActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nested_scroll)
        var recycle_view = findViewById(R.id.recycle_view) as RecyclerView
        recycle_view.adapter = DataAdapter()
        recycle_view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    class DataAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        override fun getItemCount(): Int {
            return 20
        }

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
            var item = TextView(parent!!.context)
            item.gravity = Gravity.CENTER
            item.height = 100
            return ViewHolder(item)
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
            var item: TextView = holder!!.itemView as TextView
            item.text = "内容内容内容内容 $position"
        }

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
    }
}
