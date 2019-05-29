package com.mxw.doraemon.list;

import java.util.*;

public class ListSort {

	public static void main(String[] args) {
		User user1 = new User();
		user1.setName("a");
		user1.setOrder(2);
		User user2 = new User();
		user2.setName("b");
		user2.setOrder(2);

		Set<User> Hset = new HashSet<User>();
		Hset.add(user2);
		Hset.add(user1);

		List<User> list = new ArrayList<User>();
		list.addAll(Hset);

		Collections.sort(list, new Comparator<User>() {
			public int compare(User arg0, User arg1) {
				// 从小到大1，2
				// return arg0.getOrder().compareTo(arg1.getOrder());
				// 从大到小2，1
				return arg1.getOrder().compareTo(arg0.getOrder());
			}
		});
		for (User u : list) {
			System.out.println(u.getName());
		}
	}

}
