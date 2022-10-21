package SORT;
public class Pdqsort<T extends Comparable<T>> {
    static private final int ins_size = 24;
    static private final int nine_mid_size = 128;
    static private final int partial_limit=8;
    public static void main(String[] args) throws Exception {
        
    }
    public Pdqsort() {

    }

    private void swim(T[] Array, int begin, int end, int k) {
        int p = (k - 1) / 2;
        if (p < begin) {
            return;
        }
        T parent = Array[(k - 1) / 2];
        if (parent.compareTo(Array[k]) < 0) {
            swap2(Array, begin, k);
            swim(Array, begin, end, p);
            return;
        }
        return;
    }

    private void sink(T[] Array, int begin, int end, int k) {
        int m = k - begin;
        int l = begin + 2 * m + 1;
        int r = l + 1;
        int max = 0;
        if (r < end) {
            if (Array[r].compareTo(Array[l]) < 0) {
                max = l;
            } else {
                max = r;
            }
        } else if (l == end - 1) {
            max = l;
        } else {
            return;
        }
        if (Array[max].compareTo(Array[k]) < 0) {
            return;
        } else {
            swap2(Array, k, max);
            sink(Array, begin, end, max);
            return;
        }
    }

    private void make_heap(T[] Array, int begin, int end) {
        for (int i = (end - begin) / 2; i >= begin; i--) {
            sink(Array, begin, end, i);
        }
    }

    private void pop_heap(T[] Array, int begin, int end) {
        // if(end<=begin+1)
        // return;
        swap2(Array, begin, end - 1);
        sink(Array, begin, --end, begin);
        return;
    }

    private void sort_heap(T[] Array, int begin, int end) {
        while (begin < end) {
            pop_heap(Array, begin, end--);
        }
    }

    /*heap sort a Array */
    public void heap_sort(T[] data, int l, int r) {
        make_heap(data, l, r);
        sort_heap(data, l, r);
    }
    /*insert sort a Array */
    public void insertion_sort(T[] data, int l, int r) {
        for (int i = l + 1; i < r; i++) {
            int j = i - 1;
            T tmp = data[i];
            while (j >= l && tmp.compareTo(data[j]) < 0) {
                data[j + 1] = data[j];
                j--;
            }
            data[j + 1] = tmp;
        }
    }
    /*insert sort a Array when the left element is less than begin */
    public void insertion_sort_left(T[] data, int l, int r) {
        for (int i = l + 1; i < r; i++) {
            int j = i - 1;
            T tmp = data[i];

            while (tmp.compareTo(data[j]) < 0) {
                data[j + 1] = data[j];
                j--;
            }
            data[j + 1] = tmp;
        }
    }

    public boolean partial_insertion_sort(T[] Array,int begin,int end){
        if(begin==end) 
            return true;
        int limit=0;
        for(int i=begin+1;i!=end;i++){
            int j=i-1;
            T tmp=Array[i];
            while(j>=begin&&tmp.compareTo(Array[j])<0){
                Array[j+1]=Array[j];
                j--;
            }
            Array[j+1]=tmp;
            limit+=i-j+1;
            if(limit>partial_limit){
                return false;
            }
        }
        return true;
    }
    private void swap2(T[] data, int i, int j) {
        T tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

    private void sort2(T[] data, int i, int j) {
        if (data[j].compareTo(data[i]) < 0) {
            swap2(data, i, j);
        }
    }

    private void sort3(T[] data, int l, int m, int r) {
        sort2(data, l, m);
        sort2(data, m, r);
        sort2(data, l, r);
    }

    public void pdqsort(T[] Array,int begin,int end) {
        pdqsort_loop(Array, begin, end, (int)(Math.log(end-begin)/Math.log(2)), false);
    }
    /*part the Array using the first element */
    public int partition_left(T[] Array, int begin, int end) {
        T tmp = Array[begin];
        int l = begin;
        int r = end;
        while (tmp.compareTo(Array[--r]) < 0)
            ;// find the first less tmp position
        if (r + 1 == end) {
            while (l < r && tmp.compareTo(Array[++l]) > 0)
                ;
        } else {
            while (tmp.compareTo(Array[++l]) > 0)
                ;
        }
        while (l < r) {
            swap2(Array, l, r);
            while (tmp.compareTo(Array[++l]) > 0)
                ;
            while (tmp.compareTo(Array[--r]) < 0)
                ;
        }
        swap2(Array, begin, r);
        return r;
    }
    /*part the Array using the first element and judge the Array is aleardy part
     * and the end element is more than the begin
     */
    public int[] partition_right(T[] Array, int begin, int end) {
        int[] result = new int[2];
        T tmp = Array[begin];
        int l = begin;
        int r = end;
        while (tmp.compareTo(Array[++l]) > 0)
            ;
        if (l - 1 == begin) {
            while (l < r && tmp.compareTo(Array[--r]) < 0)
                ;
        } else {
            while (tmp.compareTo(Array[--r]) < 0)
                ;
        }
        int alread_part = l >= r ? 1 : -1;
        while (l < r) {
            swap2(Array, l, r);
            while (tmp.compareTo(Array[++l]) > 0)
                ;
            while (tmp.compareTo(Array[--r]) < 0)
                ;
        }
        int pos = l - 1;
        swap2(Array, begin, pos);
        result[0] = pos;
        result[1] = alread_part;
        return result;
    }
    /*blcok_quick_sort */
    public int[] partition_right_branchless(T[] Array,int begin,int end){
        int[] result=new int[2]; 
        T pivot=Array[begin];
        int l=begin;
        int r=end;
        while(pivot.compareTo(Array[++l])>0);
        if(l-1==begin) while(l<r&&pivot.compareTo(Array[--r])<0);
        else while(pivot.compareTo(Array[--r])<0);
        result[1]=l>=r?1:-1;
        if(result[1]<0){
            swap2(Array, l, r);
            ++l;
            while(l<r){
                int num_u=r-l;
                //int left_s=
                //todo
            }
        }
        return result;
    }
/*pdq sort */
public void pdqsort_loop(T[] Array,int begin,int end,int bad_allow,boolean has_left){
    final int size=end-begin;
    if(size<=1)
    return;
    if(size<ins_size){
        if(has_left){
            insertion_sort_left(Array, begin, end);
        }
        else{
            insertion_sort(Array, begin, end);
        }
        return;
    }
    int mid=begin+(end-begin)/2;
    if(size>nine_mid_size){
        sort3(Array, begin,mid,end-1);
        sort3(Array, begin+1,mid-1,end-2);
        sort3(Array, begin+2,mid+1,end-3);
        sort3(Array, mid-1,mid,mid+1);
        swap2(Array, begin, mid);
    }
    else{
        sort3(Array, mid, begin, end-1);
    }
    if(has_left&&Array[begin].compareTo(Array[begin-1])==0){
        int lbegin=partition_left(Array, begin, end)+1;
        pdqsort_loop(Array, lbegin, end, --bad_allow, has_left);
        return;
    }
    int[] rs=partition_right(Array, begin, end);
    boolean already_part=rs[1]>0;
    int pos=rs[0];
    int l_size=pos-begin;
    int r_size=end-1-pos;
    boolean high_unbalance=l_size<size/8||r_size<size/8;
    if(high_unbalance){
        if(bad_allow==1){
            heap_sort(Array, begin, end);
        }
        if(l_size>ins_size){
            swap2(Array, begin, begin+l_size/4);
            swap2(Array, pos-1, pos-l_size/4);
            if(l_size>nine_mid_size){
                swap2(Array, begin+1, begin+(l_size/4+1));
                swap2(Array, begin+2, begin+(l_size/4+2));
                swap2(Array, pos-2, pos-1-l_size/4);
                swap2(Array, pos-3, pos-2-l_size/4);
            }
        }
        if(r_size>ins_size){
            swap2(Array, pos+1, pos+r_size/4+1);
            swap2(Array, end-1, end-r_size/4);
            if(r_size>nine_mid_size){
                swap2(Array, pos+2, pos+(r_size/4+1));
                swap2(Array, pos+3, pos+(r_size/4+2));
                swap2(Array, end-2, end-1-r_size/4);
                swap2(Array, end-3, end-2-r_size/4);
            }
        }
    }
    else{
        if(already_part&&partial_insertion_sort(Array, begin, pos)&&partial_insertion_sort(Array, pos+1, end)){
            return;
        }
    }
    --bad_allow;
    pdqsort_loop(Array, begin, pos, bad_allow, has_left);
    pdqsort_loop(Array, pos+1, end, bad_allow, true);
    return;
}

}