class QuickSortMatrix:
    def __init__(self):
        self.iteraciones = 0

    def sort(self, matrix, key=lambda x: x):
        self.iteraciones = 0
        for row in matrix:
            self._quicksort(row, 0, len(row) - 1, key)
        return matrix

    def _quicksort(self, row, low, high, key):
        if low < high:
            pi = self._partition(row, low, high, key)
            self._quicksort(row, low, pi - 1, key)
            self._quicksort(row, pi + 1, high, key)

    def _partition(self, row, low, high, key):
        pivot = key(row[high])
        i = low - 1
        for j in range(low, high):
            self.iteraciones += 1
            if key(row[j]) < pivot:
                i += 1
                row[i], row[j] = row[j], row[i]
        row[i + 1], row[high] = row[high], row[i + 1]
        return i + 1
