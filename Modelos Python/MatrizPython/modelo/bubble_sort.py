class BubbleSort:
    def __init__(self):
        self.iteraciones = 0

    def sort(self, arr, key=lambda x: x):
        self.iteraciones = 0
        n = len(arr)
        for i in range(n - 1):
            for j in range(n - i - 1):
                self.iteraciones += 1
                if key(arr[j]) > key(arr[j + 1]):
                    arr[j], arr[j + 1] = arr[j + 1], arr[j]
        return arr
