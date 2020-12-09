package com.example.criminalintent

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val TAG = "ClassListFragment"

class CrimeListFragment: Fragment() {

    private lateinit var crimeRecyclerView: RecyclerView
    private var adapter: CrimeAdapter? = CrimeAdapter(emptyList())

    private val crimeListViewModel:CrimeListViewModel by lazy {
        ViewModelProviders.of(this).get(CrimeListViewModel::class.java)
    }

    companion object {
        fun newInstance(): CrimeListFragment {
            return CrimeListFragment()
        }
    }

    // When the fragment is called to be created from Mainactivity
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_crime_list, container, false)

        crimeRecyclerView = view.findViewById(R.id.crime_recycler_view) as RecyclerView
        crimeRecyclerView.layoutManager = LinearLayoutManager(context)

        // return the view we have created with the fragment
        return view
    }

    // this function updates the view. Get the crime list, create a new adapter and then add the adapter to the Recyclerview instance
    private fun updateUI(crimes: List<Crime>) {
        adapter = CrimeAdapter(crimes)
        crimeRecyclerView.adapter = adapter
    }

    // This inner class takes a view as a param and adds the title and data text view that will
    // be populated in the recyclerview! Kind of tricky
    // We also implement the OnClickListener in the holder, where the view itself is responding to click events
    // We then perform the action we want in the onClick function we have overridden
    private inner class CrimeHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener{
        private lateinit var crime: Crime

        val titleTextView: TextView = view.findViewById(R.id.crime_title)
        val dateTextView: TextView = itemView.findViewById(R.id.crime_date)
        val solvedImageView: ImageView = itemView.findViewById(R.id.crime_solved)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(crime: Crime) {
            this.crime = crime
            titleTextView.text = this.crime.title
            dateTextView.text = this.crime.date.toString()
            solvedImageView.visibility = if (crime.isSolved){
                View.VISIBLE
            } else {
                View.GONE
            }
        }

        override fun onClick(v : View){
            Toast.makeText(context, "${crime.title} pressed!", Toast.LENGTH_SHORT).show()
        }
    }

    // We create an adapter to interface between the model data and the recycler view. To start, we pass it the model data
    // Then it returns an instance of the adapater that knows all about the data and what view to format
    private inner class CrimeAdapter(var crimes: List<Crime>): RecyclerView.Adapter<CrimeHolder>(){

        // get the layout we want to create, populate the view and then pass the view to a new crime holder object
        // we return a view packaged up nicely that contains the layout we want
        // We will only need a limited amount, RecyclerView reuses the views when they scroll instead of creating new ones
        // with onCreateViewHolder and instead reuses previously made instances! How convenient
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrimeHolder {
            Log.d(TAG, "onCreateViewHolder hit")
            val view = layoutInflater.inflate(R.layout.list_item_crime, parent, false)
            return CrimeHolder(view)
        }

        // so the recycler view knows how many items are in the dataset,
        override fun getItemCount(): Int {
            Log.d(TAG, "getItemCount hit")
            return this.crimes.size
        }

        // will populate a given holder with the crime title and date from a given position, a param. We get the crime from the crime
        // list at a specified position, which is called on from the RecyclerView

        // Onbind is called when we are updating text, recycling the view from one view that has now scrolled off the screen!
        override fun onBindViewHolder(holder: CrimeHolder, position: Int) {
            //get a crime at the specified position and apply the text to the textViews
            Log.d(TAG, "onBindViewHolder hit")
            val crime = crimes[position]
            holder.bind(crime)
        }

    }
}