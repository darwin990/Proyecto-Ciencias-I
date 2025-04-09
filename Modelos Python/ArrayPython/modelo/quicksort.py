class QuickSort:
    def __init__(self):
        self.iteraciones = 0

    def sort(self, arr, key=lambda x: x):
        self.iteraciones = 0
        self._quicksort(arr, 0, len(arr) - 1, key)
        return arr

    def _quicksort(self, arr, low, high, key):
        if low < high:
            pi = self._partition(arr, low, high, key)
            self._quicksort(arr, low, pi - 1, key)
            self._quicksort(arr, pi + 1, high, key)

    def _partition(self, arr, low, high, key):
        pivot = key(arr[high])
        i = low - 1
        for j in range(low, high):
            self.iteraciones += 1
            if key(arr[j]) < pivot:
                i += 1
                arr[i], arr[j] = arr[j], arr[i]
        arr[i + 1], arr[high] = arr[high], arr[i + 1]
        return i + 1
