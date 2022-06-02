public class MaxHeap {
    private int[] heapArray = null;
    private int maxSize = 0;
    private int currentSize = 0;

    MaxHeap(int size){
        maxSize = size;
        heapArray = new int[maxSize];
    }
    MaxHeap() {
        maxSize = 50;
        heapArray = new int[maxSize];
    }

    public void add(int num){
        heapArray[currentSize] = num;
        up(currentSize++);
    }

    private void up(int index) {
        int upValue = heapArray[index];
        int parent = (index - 1)/2;

        while(index > 0 && heapArray[parent] < upValue) {
            heapArray[index] = heapArray[parent];
            index = parent;
            parent = (index - 1)/2;
        }
        heapArray[index] = upValue;
    }

    public int pop() {
        int max = heapArray[0];
        heapArray[0] = heapArray[--currentSize];
        draw(0);
        return max;
    }
    private void draw(int index) {
        int drawValue = heapArray[index];
        int largestChild = 0;

        while(index < currentSize/2){
            int leftChild = index * 2 + 1;
            int rightChild = index * 2 + 2;

            if(rightChild < currentSize && heapArray[rightChild] >= heapArray[leftChild])
                largestChild = rightChild;
            else
                largestChild = leftChild;

            heapArray[index] = heapArray[largestChild];
            index = largestChild;
        }
        heapArray[index] = drawValue;
    }
}
