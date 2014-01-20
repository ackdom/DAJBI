package cz.cvut.fit.dajbi.stack;

import java.util.ArrayList;
import java.util.List;

public class Stack<T extends Object> {

	private ArrayList<T> stack;

	public Stack() {
		stack = new ArrayList<T>();
	}

	public T top() {
		if(isEmpty()) {
			return null;
		}
		return stack.get(stack.size() - 1);
	}

	public void push(T object) {
		stack.add(object);
	}

	public T pop() {
		if(isEmpty()) {
			return null;
		}
		return stack.remove(stack.size() - 1);
	}
	
	public int size() {
		return stack.size();
	}
	
	public boolean isEmpty() {
		return stack.size() == 0 ? true : false;
	}
	
	public List<T> popArgList(int count) {
		List<T> subList = stack.subList(size()-count, size());
		List<T> args = new ArrayList<T>(subList);
		subList.clear();
		return args;		
	}

}
