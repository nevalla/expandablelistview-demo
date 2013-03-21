package com.example.expandablelistviewdemo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;


public class Category {

	public ArrayList<Category> children;
	public ArrayList<String> selection;
	
	
	public String name;
	
	public Category() {
		children = new ArrayList<Category>();
		selection = new ArrayList<String>();
	}
	
	public Category(String name) {
		this();
		this.name = name;
	}
	
	public String toString() {
		return this.name;
	}
	
	// generate some random amount of child objects (1..10)
	private void generateChildren() {
		Random rand = new Random();
		for(int i=0; i < rand.nextInt(9)+1; i++) {
			Category cat = new Category("Child "+i);
			this.children.add(cat);
		}
	}
	
	public static ArrayList<Category> getCategories() {
		ArrayList<Category> categories = new ArrayList<Category>();
		for(int i = 0; i < 10 ; i++) {
			Category cat = new Category("Category "+i);
			cat.generateChildren();
			categories.add(cat);
		}
		return categories;
	}
	
	public static Category get(String name)
	{
		ArrayList<Category> collection = Category.getCategories();
		for (Iterator<Category> iterator = collection.iterator(); iterator.hasNext();) {
			Category cat = (Category) iterator.next();
			if(cat.name.equals(name)) {
				return cat;
			}
			
		}
		return null;
	}
}
