package objp.sprint1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

class HealthyPetsAnimalHostel {

	public static void main(String[] args) {

		List<Pet> pets = new ArrayList();

		pets.add(new Dog("Sixten", 5));
		pets.add(new Dog("Dogge", 10));
		pets.add(new Cat("Dogge", 5));
		pets.add(new Cat("Ove", 3));
		pets.add(new Snake("Hypno", 1));

		JFrame frame = new JFrame();
		JPanel panel = new JPanel();

		JTextField name = new JTextField(10);
		JButton search = new JButton("SÖK");
		JLabel result = new JLabel();

		panel.add(new JLabel("Namn: "));
		panel.add(name);
		panel.add(search);
		panel.add(result);

		frame.add(panel);
		frame.setSize(300, 100);
		frame.setVisible(true);

		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				for (Pet p : pets) {
					if (p.getName().toLowerCase().contains(name.getText().toLowerCase())) {
						result.setText(String.valueOf(p.eat()) + " gram " + p.category());
					}
				}
			}
		});
	}
}

interface Foodable {
	enum Portion {
		DOG(100), CAT(150), SNAKE(20);

		int constant;
		Portion(int i) {
			constant = i;
		}

		public int getPortion(int weight) {
			switch (Portion.this) {
			case SNAKE:
				return constant;
			default:
				break;
			}
			return (weight * 1000) / constant;
		}
	}

	public int eat();
	public String category();
}

abstract class Pet implements Foodable {
	private String name;
	private int weight;

	Pet(String name, int weight) {
		this.name = name;
		this.weight = weight;
	}

	String getName() {
		return name;
	}

	int getWeight() {
		return weight;
	}

}

class Dog extends Pet implements Foodable {

	Dog(String name, int weight) {
		super(name, weight);
	}

	@Override
	public int eat() {
		return Foodable.Portion.DOG.getPortion(getWeight());

	}

	@Override
	public String category() {
		return "hundfoder";
	}
}

class Cat extends Pet implements Foodable {

	Cat(String name, int weight) {
		super(name, weight);
	}

	@Override
	public int eat() {
		return Foodable.Portion.CAT.getPortion(getWeight());

	}

	@Override
	public String category() {
		return "kattfoder";
	}
}

class Snake extends Pet implements Foodable {

	Snake(String name, int weight) {
		super(name, weight);
	}

	@Override
	public int eat() {
		return Foodable.Portion.SNAKE.getPortion(getWeight());

	}

	@Override
	public String category() {
		return "ormfoder";
	}
}