private var n = 0
private lateinit var info: Array<Pair<Int, Int>>

fun main() {
    solve()
}

private fun solve() {
    input()
    info.sort()
    println(check())
}

private fun input() {
    n = readLine()!!.toInt()
    info = Array(n) { Pair(0, 0) }

    repeat(n) {
        val (time, limit) = readLine()!!.split(" ").map { it.toInt() }
        info[it] = Pair(time, limit)
    }
}

private fun Array<Pair<Int, Int>>.sort() {
    val comparator = compareByDescending<Pair<Int, Int>> { it.second }.thenByDescending { it.first }
    this.sortWith(comparator)
}

private fun check(): Int {
    val max = info.maxOfOrNull { it.second } ?: 0
    val schedule = IntArray(max + 1)

    info.forEach {
        var runningTime = it.first
        val limitTime = it.second

        var currentTime = limitTime
        while(runningTime != 0) {
            if(schedule[currentTime] == 0) {
                schedule[currentTime]++
                runningTime--
            }

            currentTime--

            if(currentTime < 0) return -1
        }
    }

    return schedule.indexOfFirst { it != 0 } - 1
}