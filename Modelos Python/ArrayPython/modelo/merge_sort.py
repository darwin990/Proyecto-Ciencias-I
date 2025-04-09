class MergeSort:
    def __init__(self):
        self.iteraciones = 0

    def sort(self, array, key=lambda x: x):
        self.iteraciones = 0
        return self._merge_sort(array, key)

    def _merge_sort(self, array, key):
        if len(array) <= 1:
            return array
        mid = len(array) // 2
        left = self._merge_sort(array[:mid], key)
        right = self._merge_sort(array[mid:], key)
        return self._merge(left, right, key)

    def _merge(self, left, right, key):
        result = []
        i = j = 0
        while i < len(left) and j < len(right):
            self.iteraciones += 1
            if key(left[i]) < key(right[j]):
                result.append(left[i])
                i += 1
            else:
                result.append(right[j])
                j += 1
        result.extend(left[i:])
        result.extend(right[j:])
        return result
