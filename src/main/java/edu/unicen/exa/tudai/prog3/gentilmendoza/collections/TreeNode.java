package edu.unicen.exa.tudai.prog3.gentilmendoza.collections;

import edu.unicen.exa.tudai.prog3.gentilmendoza.model.Book;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {

	private String key;
	private List<Book> books;
	private TreeNode left;
	private TreeNode right;
	
	public TreeNode(String key) {
		this.key = key;
		this.books = new ArrayList<>();
		this.left = null;
		this.right = null;
	}

	public boolean isLeaf() {
		return this.getLeft() == null && this.getRight() == null;
	}

	public void addBook(Book book) {
		this.books.add(book);
	}

	public List<Book> getBooks() {
		return new ArrayList<>(this.books);
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public TreeNode getLeft() {
		return left;
	}

	public void setLeft(TreeNode left) {
		this.left = left;
	}

	public TreeNode getRight() {
		return right;
	}

	public void setRight(TreeNode right) {
		this.right = right;
	}	
	
	public void printValue() {
		// TODO: Hmmmm
		System.out.print("(" + this.getKey() + ")");
		if (this.getLeft() == null) {
			System.out.print("-");
		}
		if (this.getRight() == null) {
			System.out.print("-");
		}
	}
	
}
