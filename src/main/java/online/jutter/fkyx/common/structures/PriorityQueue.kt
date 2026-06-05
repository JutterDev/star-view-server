package online.jutter.fkyx.common.structures

/**
 *  Очередь с приоритетами.
 */
class PriorityQueue<T> {

    private val elements = mutableListOf<Pair<Double, T>>()

    /**
     * Проверка на пустую очередь.
     */
    fun isEmpty() = elements.isEmpty()
    fun isNotEmpty() = elements.isNotEmpty()

    /**
     * Кладем элемент в очередь.
     */
    fun put(item: T, priority: Double) {
        elements.add(Pair(priority, item))
        elements.sortBy { it.first }
    }

    /**
     * Получение самого маленького
     * элемента в очереди.
     */
    fun get(): T {
        val item = elements.first().second
        elements.removeFirst()
        return item
    }
}