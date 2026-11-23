class ProductOfNumbers {
    // LeetCode constraint: at most 40,000 calls will be made to add and getProduct
    private final int[] prefixProducts = new int[40001];
    private int size = 0;

    public ProductOfNumbers() {
        prefixProducts[0] = 1;
        size = 1;
    }
    
    public void add(int num) {
        if (num == 0) {
            // Reset pointer instead of clearing memory
            prefixProducts[0] = 1;
            size = 1;
        } else {
            prefixProducts[size] = prefixProducts[size - 1] * num;
            size++;
        }
    }
    
    public int getProduct(int k) {
        if (k >= size) {
            return 0;
        }
        return prefixProducts[size - 1] / prefixProducts[size - 1 - k];
    }
}
