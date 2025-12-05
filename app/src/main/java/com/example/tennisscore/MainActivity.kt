package com.example.tennisscore

import android.os.Bundle
import android.view.Display
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var presenter: Presenter

    private lateinit var scoreAText: TextView
    private lateinit var scoreBText: TextView

    private lateinit var pointsAText: TextView
    private lateinit var pointsBText: TextView

    private lateinit var gamesAText: TextView
    private lateinit var gamesBText: TextView

    private lateinit var setsAText: TextView
    private lateinit var setsBText: TextView

    private lateinit var pointAButton: Button
    private lateinit var pointBButton: Button
    private lateinit var resetButton: Button

    private  lateinit var radioGroup: RadioGroup
    private lateinit var rd3: RadioButton
    private lateinit var rd5: RadioButton
    private  lateinit var model: Model


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = Presenter(this)
        model = presenter.model

        scoreAText = findViewById(R.id.txtScoreA)
        scoreBText = findViewById(R.id.txtScoreB)

        pointsAText = findViewById(R.id.txtPointsA)
        pointsBText = findViewById(R.id.txtPointsB)

        gamesAText = findViewById(R.id.txtGamesA)
        gamesBText = findViewById(R.id.txtGamesB)

        setsAText = findViewById(R.id.txtSetsATotal)
        setsBText = findViewById(R.id.txtSetsBTotal)

        pointAButton = findViewById(R.id.btnPointA)
        pointBButton = findViewById(R.id.btnPointB)
        resetButton = findViewById(R.id.btnReset)

        rd3 = findViewById(R.id.rd3Sets)
        rd5 = findViewById(R.id.rd5Sets)
        radioGroup = findViewById(R.id.rdGroup)

        pointAButton.setOnClickListener { presenter.onPointA() }
        pointBButton.setOnClickListener { presenter.onPointB() }
        resetButton.setOnClickListener { presenter.Reset() }

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId == R.id.rd3Sets) {
                presenter.OnBestSelect(3)
            } else if (checkedId == R.id.rd5Sets) {
                presenter.OnBestSelect(5)
            }
        }

        if (savedInstanceState != null) {
            model.pointsA = savedInstanceState.getInt("pointsA")
            model.pointsB = savedInstanceState.getInt("pointsB")
            model.gamesA = savedInstanceState.getInt("gamesA")
            model.gamesB = savedInstanceState.getInt("gamesB")
            model.scoreA = savedInstanceState.getInt("scoreA")
            model.scoreB = savedInstanceState.getInt("scoreB")
            model.setsA = savedInstanceState.getString("setsA", "___")
            model.setsB = savedInstanceState.getString("setsB", "___")
            model.bestOf = savedInstanceState.getInt("bestOf", 3)
            model.pointUsed = savedInstanceState.getBoolean("pointUsed", false)

            presenter.showStats()
            if (model.bestOf == 3)
            {
                rd3.isChecked = true
            } else
            {
                rd5.isChecked = true
            }
        } else {

            rd3.isChecked = true
        }


        if (model.pointUsed) {
            disableRdBtn()
        } else {
            enableRdBtn()
        }
    }


    fun translatePoints(points: Int): String {
            return when (points) {
                0 -> "00"
                1 -> "15"
                2 -> "30"
                3 -> "40"
                4 -> "AD"
                else -> ""
            }
        }

    override fun onSaveInstanceState(outState: Bundle)
    {
        super.onSaveInstanceState(outState)
        outState.putInt("pointsA", model.pointsA)
        outState.putInt("pointsB", model.pointsB)
        outState.putInt("gamesA", model.gamesA)
        outState.putInt("gamesB", model.gamesB)
        outState.putInt("scoreA", model.scoreA)
        outState.putInt("scoreB", model.scoreB)
        outState.putString("setsA", model.setsA)
        outState.putString("setsB", model.setsB)
        outState.putInt("bestOf", model.bestOf)
        outState.putBoolean("pointUsed", model.pointUsed)
    }


    fun showPoints(pointsA: Int, pointsB: Int) {
            var pointsAtransalted = translatePoints(pointsA)
            var pointsBtransalted = translatePoints(pointsB)
            pointsAText.text = pointsAtransalted
            pointsBText.text = pointsBtransalted
        }

        fun showGames(gamesA: Int, gamesB: Int) {
            gamesAText.text = gamesA.toString()
            gamesBText.text = gamesB.toString()
        }

        fun showScore(scoreA: Int, scoreB: Int) {
            scoreAText.text = scoreA.toString()
            scoreBText.text = scoreB.toString()
        }

        fun showSets(setsA: String, setsB: String) {
            setsAText.text = setsA
            setsBText.text = setsB
        }

        fun ResetBtn()
        {
            pointBButton.isEnabled = true
            pointAButton.isEnabled = true
            rd3.isEnabled = true
            rd5.isEnabled = true
        }

        fun disableBtn()
        {
            pointBButton.isEnabled = false
            pointAButton.isEnabled = false
        }

        fun disableRdBtn()
        {
            rd3.isEnabled = false
            rd5.isEnabled = false
        }

        fun enableRdBtn()
        {
            rd3.isEnabled = true
            rd5.isEnabled = true
        }


    }

