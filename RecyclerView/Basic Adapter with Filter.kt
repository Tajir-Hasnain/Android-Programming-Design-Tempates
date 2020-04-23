
/*
*
* Adapter for RecyclerView
* class name : RecyclerView
* constructor parameters : _mTexts : ArrayList<> , _logos : ArrayList<>
* class extends : ViewHolder
* class implements : Filterable ( For search filter in the recyclerview
*
* */

class RecyclerViewAdapter (
    context: Context,
    
    //TODO: Change parameters accordingly
    _mTexts: ArrayList<String>,
    logos: ArrayList<String>
) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(), Filterable {


    //TODO: Initialization of the necessary variables

    var mTexts = ArrayList<String>()
    var mImages = ArrayList<String>()

    var mTextSearch = ArrayList<String>()   // For filtered list
    var mImagesSearch = ArrayList<String>() // For filtered list

    private val mContext: Context


    /*
    *
    * Constructor of the Adapter
    * Operation : Initializes the variables with the parameters
    * TODO: Change the initialization accordingly
    *
    * */
    init {
        mTexts = _mTexts
        mImages = logos
        mContext = context
    }


    /*
    *
    * Overridden function of ViewHolder class
    * initializes the cards which will be inflated as the view of the list
    *
    * */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.card, parent, false)   // TODO: card layout id here
        return ViewHolder(view)
    }


    /*
    *
    * Overridden function : onBindViewHolder
    * parameters : holder , position    // Represents the "position" of the current "ViewHolder"
    *
    * operation : Binds the data of the position in the ArrayList to this viewHolder
    *
    * */
    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        Log.d(
            TAG_SUCCESS,
            "[+] onBindViewHolder: called on $position"
        )
        Log.d(
            TAG_SUCCESS,
            "[+] restaurant on position " + position + " : " + mTexts[position]
        )
        
        holder.label_name.text = mTexts[position]

        //OnClick Listener for redirecting to Menu activity to be implemented here
        holder.cardLayout.setOnClickListener {

            //TODO: Implementation of the onClickListener for the card

        }
    }

    /*
    *
    * For getting the total items in the arraylist
    *
    * */
    override fun getItemCount(): Int {
        return mTexts.size    //TODO: Change variable name if necessary
    }


    /*
    *
    * Implementation of the ViewHolder Class
    *
    * function : Implements the viewHolder according to the need of the card i.e. what information will be in the layout
    *
    * on the curreent context, there are two elements in the card : logo and label name
    * */
    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        //TODO: Initialize necessary variables that will hold the contents

        var logo: ImageView
        var label_name: TextView
        var cardLayout: LinearLayout

        init {
            /*
            * Contructor of viewHolder
            * TODO: Assign the ids of the layout designs here to the initialized variables
            * */
            logo = itemView.findViewById(R.id.logo)
            label_name = itemView.findViewById(R.id.label_name)
            cardLayout = itemView.findViewById(R.id.card_layout)
        }
    }

    companion object {
        private const val TAG_SUCCESS = "[+] RecyclerViewAdapter"
        private const val TAG_FAIL = "[-] RecyclerViewAdapter"
    }


    /*
    *
    * is Used to filter data for search
    *
    * Outside of adapter operations :
    *   • View sends the editText to the ViewModel
    *   • ViewModel implements TextChangedListener and filters the ArrayList accordingly
    *
    *  Inside adapter operations:
    *   • getFilter triggers exampleFilter that implements performFiltering() and publishResults()
    *   • performFiltering function filters the ArrayList with the input pattern and sends it to publishResults()
    *   • publishResults() updates the ArrayList and notifies the adapter about the data change.
    *
    *
    * */
    override fun getFilter(): Filter {
        return exampleFilter
    }

    private val exampleFilter: Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults? {

            val filteredList: MutableList<String> = ArrayList()   //TODO: change datatype if necessary

            if (constraint == null || constraint.isEmpty()) {
                filteredList.addAll(mTexts)
            } else {

                val filterPattern = constraint.toString().toLowerCase().trim { it <= ' ' }

                for (item in mTexts) {
                    if (item.toLowerCase().contains(filterPattern)) {
                        filteredList.add(item)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        override fun publishResults (
            constraint: CharSequence?,
            results: FilterResults
        ) {
            mTextSearch.clear()
            mTextSearch.addAll(results.values as ArrayList<String>)   //TODO: Change datatype if necessary
            notifyDataSetChanged()
        }
    }



    /*
    *
    * general purpose function activityName
    * For getting the activityName of the current Activity
    * */

    private val activityName: String
        get() {
            val activityName = mContext.javaClass.simpleName
            Log.d(
                TAG_SUCCESS,
                "activity name : $activityName"
            )
            return activityName
        }
}
