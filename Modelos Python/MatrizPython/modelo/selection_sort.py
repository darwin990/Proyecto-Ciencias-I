class SelectionSort:
    def __init__(self):
        self.iteraciones = 0

    def sort(self, arr, key=lambda x: x):
        self.iteraciones = 0
        n = len(arr)
        for i in range(n - 1):
            min_index = i
            for j in range(i + 1, n):
                self.iteraciones += 1
                if key(arr[j]) < key(arr[min_index]):
                    min_index = j
            arr[i], arr[min_index] = arr[min_index], arr[i]
        return arr
