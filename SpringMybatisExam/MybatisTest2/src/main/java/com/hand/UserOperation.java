package com.hand;

import java.util.List;

public interface UserOperation {
	public List selectUsers();
	public List selectCustomer();
	public void addUser(Customer user);
	public Address findById(int id);
	public void deleteCustomer(int id);
}
