package com.sort;

import java.util.Comparator;

import com.bean.Candidate;

public class AgeCompare implements Comparator<Candidate>{

	@Override
	public int compare(Candidate c1, Candidate c2) {
		// TODO Auto-generated method stub
		return ((Integer)c1.getAge()).compareTo((Integer)c2.getAge());
	}
}
