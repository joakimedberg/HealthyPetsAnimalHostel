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

		// a list that acts as database for the pets
		List<Pet> pets = new ArrayList();

		pets.add(new Dog("Sixten", 5));
		pets.add(new Dog("Dogge", 10));
		pets.add(new Cat("Venus", 5));
		pets.add(new Cat("Ove", 3));
		pets.add(new Snake("Hypno", 1));

		
		// GUI
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();

		JLabel label = new JLabel("Vilket djur ska få mat?");
		JTextField textField = new JTextField(10);
		JButton searchButton = new JButton("SÖK");
		JLabel result = new JLabel();

		panel.add(label);
		panel.add(textField);
		panel.add(searchButton);
		panel.add(result);

		frame.add(panel);
		frame.setSize(200, 100);
		frame.setVisible(true);

		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// searches the list for a matching case and displays it with requested info.
				for (Pet p : pets) {
					if (p.getName().toLowerCase().equals(textField.getText().toLowerCase())) {
						result.setText(String.valueOf(p.eat()) + " gram " + p.getFeed());
					}
				}
			}
		});
	}
}

interface Foodable {
	
	// enum represents a pet and its related constant to calculate feed.
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
	public String getFeed();
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
	public String getFeed() {
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
	public String getFeed() {
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
	public String getFeed() {
		return "ormpellets";
	}
}