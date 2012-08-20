package com.cdz.sh.dao;

public class BubleTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		int[] ids = {9,8,5,5,5,5,7,6,5,4,3,2,1};
		for (int i : ids) {
			System.out.print(i + ", ");	
		}
		System.out.println("");
		
		for(int i = 0; i<ids.length; i++){
			for(int j = i; j < ids.length; j++){
				if(ids[i] > ids[j]){
					int aux = ids[j];
					ids[j] = ids[i];
					ids[i] = aux;
				}
			}
		}
		
		for (int i : ids) {
			System.out.print(i + ", ");	
		}

	}

}
