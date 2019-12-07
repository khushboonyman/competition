package com.company;

import java.util.*;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static int size;
    public static void main(String[] args) {
        int i,j,k=0,totsum=0,fSum,prevElement=0,saveI=0,currElement,maxSum=0,maxEle=0,saveISum=0,count=0;
        size = scanner.nextInt();
        int[] array = new int[size];
        for(i=0;i<size;i++){
            array[i] = scanner.nextInt();
            totsum+=array[i];
        }
        Arrays.sort(array);
        for(i=0;i<size&&count<3;i++){
            currElement = array[i];
            if (currElement!=prevElement){
                prevElement = currElement;
                array[i] = 0;
                int[] sums = new int[totsum+1];
                sums[0] = 1;
                fSum = findSum(array,sums,size);
                if (fSum > maxSum){
                    maxSum = fSum;
                    maxEle = currElement;
                    saveI = i;
                    saveISum = fSum;
                }
                if (fSum<maxSum)
                    count++;
                array[i] = currElement;
            }
        }
        System.out.println(maxEle);

        int[] sums = new int[totsum+1];
        sums[0] = 1;
        array[saveI] = 0;
        fSum = 0;
        HashMap<Integer,Integer> hm = new HashMap<>();
        hm = findAllSum(array,sums,size);
        hm.put(0,1);
        while(fSum <= saveISum){
            k++;
            fSum = 0;
            HashMap<Integer,Integer> hm1 = new HashMap<>();
            if(!hm.containsKey(k)){
                for(Map.Entry<Integer,Integer> entry : hm.entrySet()){
                    i = entry.getKey();
                    j =  i + k ;
                    if(hm.containsKey(j) || hm1.containsKey(j))
                        break;
                    else{
                        hm1.put(j,1);
                        fSum++;
                    }
                }

            }
        }
        System.out.println(k);
    }

    public static int findSum(int[] array,int[] sums,int size){
        int t,sumElement=0,index=0,retSum=0,c;
        for(t=0;t<size;t++){
            c = array[t];
            if(c!=0){
                index = sumElement;
                while(index>=0){
                    if(sums[index] == 1 && sums[index+c] != 1){
                        sums[index+c] = 1;
                        retSum += 1;
                    }
                    index--;
                }
                sumElement+=c;
            }
        }
        return retSum;
    }

    public static HashMap<Integer, Integer> findAllSum(int[] array, int[] sums, int size){
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        int t,sumElement=0,index=0,c;
        for(t=0;t<size;t++){
            c = array[t];
            index = sumElement;
            while(index>=0){
                if(sums[index] == 1 && sums[index+c] != 1){
                    sums[index+c] = 1;
                    hashMap.put(index+c,1);
                }
                index--;
            }
            sumElement+=c;

        }
        return hashMap;
    }
}