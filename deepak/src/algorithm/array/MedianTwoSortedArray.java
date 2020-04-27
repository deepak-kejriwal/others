package algorithm.array;

public class MedianTwoSortedArray {

	public static void main(String[] args) {
		new MedianTwoSortedArray().solution();

	}

	private void solution() {
		int[] nums1 = {1, 2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
		int[] nums2 = {21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44};
		nums1=createArray(1000,0);
		nums2=createArray(100,1000);
		double result = findMedianSortedArrays(nums1,nums2);
		System.out.println(result);
		result = findMedianSortedArrays1(nums1,nums2);
		System.out.println(result);
	}
    private int[] createArray(int total, int startValue) {
		int[] num=new int[total];
		int i=0;
		while(i<total) {
			num[i]=startValue++;
			i++;
		}
		return num;
	}

	public double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        int n1=nums1.length;
        int n2=nums2.length;
        if(n2<n1) return findMedianSortedArrays1(nums2,nums1);
        int low=0;
        int high=n1;
        
        
        int count=0;
        while(low<=high){count++;
        	int cutX=(low+high)/2;
        	int cutY=(n1+n2+1)/2-cutX;
            int LX=cutX==0?Integer.MIN_VALUE:nums1[cutX-1];
            int LY=cutY==0?Integer.MIN_VALUE:nums2[cutY-1];
            int RX=cutX==n1?Integer.MAX_VALUE:nums1[cutX];
            int RY=cutY==n2?Integer.MAX_VALUE:nums2[cutY];
            if(LX>RY){
                high=cutX-1;
            }else if(LY>RX){
            	low=cutX;
            }else{
                int maxRight=Math.max(LX,LY);
                int minLeft=Math.min(RX,RY);
                System.out.println(count);
                if((n1+n2)%2!=0){
                    return maxRight;
                }else{
                    return (maxRight+minLeft)/2.0;
                }
            }
        }
        return -1;
    }
    
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int N1 = nums1.length;
        int N2 = nums2.length;
        if (N1 < N2) return findMedianSortedArrays(nums2, nums1);	// Make sure A2 is the shorter one.
        int count=0;
        int lo = 0, hi = N2 * 2;
        while (lo <= hi) {
        	count++;
            int mid2 = (lo + hi) / 2;   // Try Cut 2 
            int mid1 = N1 + N2 - mid2;  // Calculate Cut 1 accordingly
            
            double L1 = (mid1 == 0) ? Integer.MIN_VALUE : nums1[(mid1-1)/2];	// Get L1, R1, L2, R2 respectively
            double L2 = (mid2 == 0) ? Integer.MIN_VALUE : nums2[(mid2-1)/2];
            double R1 = (mid1 == N1 * 2) ? Integer.MAX_VALUE : nums1[(mid1)/2];
            double R2 = (mid2 == N2 * 2) ? Integer.MAX_VALUE : nums2[(mid2)/2];
            
            if (L1 > R2) lo = mid2 + 1;		// A1's lower half is too big; need to move C1 left (C2 right)
            else if (L2 > R1) hi = mid2 - 1;	// A2's lower half too big; need to move C2 left.
            else {System.out.println(count);
            	return (Math.max(L1,L2) + Math.min(R1, R2)) / 2;	}// Otherwise, that's the right cut.
        }
        return -1;
    } 
}
