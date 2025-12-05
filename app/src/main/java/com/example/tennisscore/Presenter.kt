package com.example.tennisscore

class Presenter(private val view: MainActivity)
{
    public val model = Model()

    fun onPointA()
    {
        view.disableRdBtn()
        model.pointForA()
        showStats()
        if(model.ckeckWin())
        {
            view.disableBtn()
        }

    }

    fun onPointB()
    {
        view.disableRdBtn()
        model.pointForB()
        showStats()
        if(model.ckeckWin())
        {
            view.disableBtn()
        }
    }

    fun OnBestSelect(n:Int)
    {
        model.changeSets(n)
        showStats()
    }

    fun showStats()
    {
        view.showPoints(model.pointsA,model.pointsB)
        view.showGames(model.gamesA,model.gamesB)
        view.showScore(model.scoreA,model.scoreB)
        view.showSets(model.setsA,model.setsB)
    }

    fun Reset()
    {
        model.Reset()
        showStats()
        view.ResetBtn()
    }
}