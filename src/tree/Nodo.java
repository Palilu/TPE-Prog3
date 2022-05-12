package tree;

public class Nodo {

	private Integer value;
	private Nodo left;
	private Nodo rigth;
	
	public Nodo(Integer value) {
		this.value = value;
		this.left = null;
		this.rigth = null;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public Nodo getLeft() {
		return left;
	}

	public void setLeft(Nodo left) {
		this.left = left;
	}

	public Nodo getRigth() {
		return rigth;
	}

	public void setRigth(Nodo rigth) {
		this.rigth = rigth;
	}	
	
	public void printValue() {
		System.out.print("(" + this.getValue() + ")");
		if (this.getLeft() == null) {
			System.out.print("-");
		}
		if (this.getRigth() == null) {
			System.out.print("-");
		}
	}
	
}
