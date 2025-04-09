class InsertionSort:
    def __init__(self):
        self.iteraciones = 0

    def sort(self, arr, key=lambda x: x):
        self.iteraciones = 0
        n = len(arr)
        for i in range(1, n):
            actual = arr[i]
            j = i - 1
            while j >= 0 and key(arr[j]) > key(actual):
                arr[j + 1] = arr[j]
                j -= 1
                self.iteraciones += 1
            arr[j + 1] = actual
        return arr
