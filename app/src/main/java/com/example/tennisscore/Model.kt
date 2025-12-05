package com.example.tennisscore

class Model
{
    var scoreA = 0
    var scoreB = 0

    var gamesA = 0
    var gamesB = 0

    var setsA = "___"
    var setsB = "___"

    var pointsA = 0
    var pointsB = 0

    var bestOf = 3

    var pointUsed = false


    fun changeSets(n:Int)
    {
        bestOf = n
        if(n == 5)
        {
            setsA = "_____"
            setsB = "_____"
        }else
        {
            setsA = "___"
            setsB = "___"
        }
    }
    fun addSetA(gameA: Int)
    {
        setsA = setsA.replaceFirst("_", gameA.toString())
    }
    fun addSetB(gameB: Int)
    {
        setsB = setsB.replaceFirst("_", gameB.toString())
    }

    fun scoreForA()
    {
        scoreA++
        addSetA(gamesA)
        addSetB(gamesB)
        gamesA = 0
        gamesB = 0
        pointsA = 0
        pointsB = 0

    }

    fun scoreForB()
    {
        scoreB++
        addSetA(gamesA)
        addSetB(gamesB)
        gamesA = 0
        gamesB = 0
        pointsA = 0
        pointsB = 0
    }

    fun gameForA()
    {
        if(gamesA<5)
        {
            gamesA++
        }else if(gamesA==5)
        {
            if(gamesB < 5)
            {
                gamesA++
                scoreForA()
            }else
            {
                gamesA++
            }
        }
        else if(gamesA == 6)
        {
            gamesA++
            scoreForA()
        }
        pointsA = 0
        pointsB = 0
    }

    fun gameForB()
    {
        if(gamesB<5)
        {
            gamesB++
        }else if(gamesB==5)
        {
            if(gamesA < 5)
            {
                gamesB++
                scoreForB()
            }else
            {
                gamesB++
            }
        }
        else if(gamesB == 6)
        {
            gamesB++
            scoreForB()
        }
        pointsA = 0
        pointsB = 0
    }

    fun pointForA()
    {
        pointUsed = true
        if(pointsA<3)
        {
            pointsA++
        }else if(pointsA==3)
        {
            if(pointsB < 3)
            {
                gameForA()
            }else if(pointsB == 3)
            {
                pointsA++
            }else if(pointsB == 4)
            {
                pointsB = 3
                pointsA = 3
            }
        }
        else if(pointsA == 4)
        {
            gameForA()
        }
    }

    fun pointForB()
    {
        pointUsed = true
        if(pointsB<3)
        {
            pointsB++
        }else if(pointsB==3)
        {
            if(pointsA < 3)
            {
                gameForB()
            }else if(pointsA == 3)
            {
                pointsB++
            }else if(pointsA == 4)
            {
                pointsA = 3
                pointsB = 3
            }
        }
        else if(pointsB == 4)
        {
            gameForB()
        }
    }

    fun ckeckWin() : Boolean
    {
        if(bestOf == 3)
        {
            if(scoreA == 2 || scoreB == 2)
            {
                return true
            }
        }else
        {
            if(scoreA == 3 || scoreB == 3)
            {
                return true
            }
        }
        return false
    }

    fun Reset()
    {
        pointsA = 0
        pointsB = 0
        gamesB = 0
        gamesA = 0
        scoreA = 0
        scoreB = 0
        setsA = "___"
        setsB = "___"
        pointUsed = false
    }





}