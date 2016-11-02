package mastermind

import java.util.*

fun playMastermind(
        nLetters: Int = 4,
        nLetterSetSize: Int = 6,
        nTries: Int = 5,
        secret: String = generateSecret(nLetters, nLetterSetSize),
        player: Player = RealPlayer()
) {
    var complete = false
    var triesLeft = nTries
    do {
        val guess = player.guess()
        if (!isGuessValid(guess, nLetters, nLetterSetSize)) {
            player.incorrectInput(guess)
            continue
        }

        val (bulls, cows) = checkGuess(guess, secret)

        if (bulls + cows > secret.length)
            throw IllegalStateException("Oops")

        complete = bulls == secret.length
        player.receiveEvaluation(complete, bulls, cows)
        --triesLeft
    } while (!complete && triesLeft > 0)

    if (!complete) {
        println("Result was: " + secret)
        println("Try again?")
    }
}

fun isGuessValid(guess: String, nLetters: Int, nLetterSetSize: Int): Boolean {
    if (guess.length != nLetters)
        return false

    val letters: Set<Char> = ('A' until 'A' + nLetterSetSize).toSet()

    for (l in guess)
        if ((l !in letters))
            return false

    return true
}

data class Result(
        var bulls: Int,
        var cows: Int)

fun checkGuess(guess: String, secret: String): Result {
    val result = Result(0, 0)
    val left: MutableSet<Char> = TreeSet()
    val right: MutableSet<Char> = TreeSet()

    // check position equality else add to sets and find their intersection
    for ((a, b) in guess.zip(secret)) {
        if (a == b)
            ++result.bulls
        else {
            left.add(a)
            right.add(b)
        }
    }
    left.retainAll(right)
    result.cows = left.size

    return result
}

fun generateSecret(
        numberOfLetters: Int,
        nLetterSetSize: Int,
        differentLetters: Boolean = true
): String {
    // Number of letters should be less than letters length
    val letters: MutableList<Char> = ('A' until 'A' + nLetterSetSize).toMutableList()
    val str = StringBuilder()

    val r = Random()
    for (i in 1..numberOfLetters) {
        val ind = r.nextInt(Math.min(nLetterSetSize, letters.size))
        str.append(letters[ind])
        if (differentLetters)
            letters.removeAt(ind)
    }
    return str.toString()
}