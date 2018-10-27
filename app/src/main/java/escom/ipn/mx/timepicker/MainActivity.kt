package escom.ipn.mx.timepicker

import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import java.util.*
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var btn : Button
    private lateinit var lbl: TextView
    private lateinit var sun : ImageView
    private lateinit var moon : ImageView
    private lateinit var spinner : Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn = findViewById<Button>(R.id.button3)
        lbl = findViewById<Button>(R.id.text)
        sun = findViewById<ImageView>(R.id.sun)
        moon= findViewById<ImageView>(R.id.moon)
        spinner = findViewById<Spinner>(R.id.spinner)

        val adapter = ArrayAdapter.createFromResource(this,R.array.choiceValues,android.R.layout.simple_spinner_item)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = this

        getTime(null)
    }

    fun getTime(view:View?){

        val time = Calendar.getInstance()
        val hr = time.get(Calendar.HOUR_OF_DAY)
        val min = time.get(Calendar.MINUTE)
        //val hr = min%24 // Used for fast debugging
        val toast = Toast.makeText(this,hr.toString()+" horas "+min+" minutos",Toast.LENGTH_SHORT)
        toast.show()

        setBackgroundColor(hr)
        setPositionSunorMoon(hr,min)

        if(hr>5 && hr < 12)
            lbl.text = getString(R.string.morning)

        else if(hr>=12 && hr < 20)
            lbl.text = getString(R.string.afternoon)

        else
            lbl.text = getString(R.string.night)
    }

    fun setPositionSunorMoon(hr : Int, min : Int){

        val cons = ConstraintSet()
        var start = 0
        var end = 0
        var top = 0
        var bottom = 0
        var id = 0

        when(min%24){
            20 -> {    top = 100; end = 380 }
            21 -> {    top = 25;  end = 285 }
            22 -> { bottom = 50;  end = 190 }
            23 -> { bottom = 125; end = 95  }
            0,1-> { bottom = 200 }
            2 ->  { bottom = 125; start = 95  }
            3 ->  { bottom = 50;  start = 190 }
            4 ->  {    top = 25;  start = 285 }
            5 ->  {    top = 100; start = 380 }
            /*********************************/
            6 ->  {    top = 100; end = 380 }
            7 ->  {    top = 50;  end = 317 }
            8 ->  {    top = 0;   end = 254 }
            9 ->  {    top = 50;  end = 191 }
            10 -> { bottom = 100; end = 128 }
            11 -> { bottom = 150; end = 65 }
            12,13 -> { bottom = 200 }
            14 -> { bottom = 150; start = 65 }
            15 -> { bottom = 100; start = 128 }
            16 -> { bottom = 50;  start = 191 }
            17 -> { bottom = 0;   start = 254 }
            18 -> { bottom = 50;  start = 317 }
            19 -> { bottom = 100; start = 380 }
        }

        if(hr<6 || hr>19) {
            id = R.id.moon
            moon.visibility = View.VISIBLE
            sun.visibility = View.INVISIBLE
        }else {
            id = R.id.sun
            sun.visibility = View.VISIBLE
            moon.visibility = View.INVISIBLE
        }

        cons.clone(constraint)
        cons.connect(id,ConstraintSet.TOP,ConstraintSet.PARENT_ID,ConstraintSet.TOP,top)
        cons.connect(id,ConstraintSet.START,ConstraintSet.PARENT_ID,ConstraintSet.START,start)
        cons.connect(id,ConstraintSet.END,ConstraintSet.PARENT_ID,ConstraintSet.END,end)
        cons.connect(id,ConstraintSet.BOTTOM,ConstraintSet.PARENT_ID,ConstraintSet.BOTTOM,bottom)
        cons.applyTo(findViewById<ConstraintLayout>(R.id.constraint))
    }

    fun setBackgroundColor(hr : Int){
        val layout = findViewById<ConstraintLayout>(R.id.constraint)
        when(hr){
            0,23 -> layout.background = getDrawable(R.color.blue12)
            1,22 -> layout.background = getDrawable(R.color.blue11)
            2,21 -> layout.background = getDrawable(R.color.blue10)
            3,20 -> layout.background = getDrawable(R.color.blue9)
            4,19 -> layout.background = getDrawable(R.color.blue8)
            5,18 -> layout.background = getDrawable(R.color.blue7)
            6,17 -> layout.background = getDrawable(R.color.blue6)
            7,16 -> layout.background = getDrawable(R.color.blue5)
            8,15 -> layout.background = getDrawable(R.color.blue4)
            9,14 -> layout.background = getDrawable(R.color.blue3)
            10,13 -> layout.background = getDrawable(R.color.blue2)
            11,12 -> layout.background = getDrawable(R.color.blue1)
            else  -> layout.background = getDrawable(R.color.white)
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        //val value = parent!!.getItemAtPosition(position)
        if(position==0) {
            btn.background = getDrawable(R.color.colorAccent)
            //spinner.background = getDrawable(R.color.colorAccent)
        }else if(position==1) {
            btn.background = getDrawable(R.color.blue)
            //spinner.background = getDrawable(R.color.blue)
        }else {
            btn.background = getDrawable(R.color.white)
            //spinner.background = getDrawable(R.color.white)
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
