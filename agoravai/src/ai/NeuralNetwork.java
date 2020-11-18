package ai;

import java.util.ArrayList;

import environment.Car;
//-78375d
public class NeuralNetwork {
	private Double[] bias = {
							0.0d,0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0d, 0d,
							0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0d, 0d,
							0d,0d, 0d, 0d
							};
	
	private Car car;
	private int w = 0;
	
	public NeuralNetwork() {
		
	}
	
	public ArrayList<Double> getSinapse(ArrayList<Double> input, Car car){
		this.car = car;
		ArrayList<Double> firstHiddenLayer = new ArrayList<Double>();
		ArrayList<Double> secondHiddenLayer = new ArrayList<Double>();
		ArrayList<Double> outputNeuralNet = new ArrayList<Double>();
		
		firstHiddenLayer = this.getNeuronLayer(input, 8, 0, 1000000);
//		firstHiddenLayer.forEach(
//				fhl->System.out.println("layer: " + fhl)
//				);
//		
//		System.exit(0);
		secondHiddenLayer = this.getNeuronLayer(firstHiddenLayer, 16, 8, 5000);
		outputNeuralNet = this.getNeuronLayer(firstHiddenLayer, 20, 16, 1500);

		this.w = 0;
		
		return outputNeuralNet;
	}
	
	private Double sigmoidNeuron(ArrayList<Double> we,Double bias) {
		
		Double x = 0d;
		for(int i = 0; i < we.size(); i++) {
			x += we.get(i);
		}
		x += bias;
		Double neuron = 1/(1 + Math.exp(-x));
		
		return neuron;
	}
	
	private ArrayList<Double> getNeuronLayer(ArrayList<Double> input, int numberOfNeuron,int startNeuron, int n) {
		
		ArrayList<Double> we = new ArrayList<Double>();
		ArrayList<Double> neuronLayer = new ArrayList<Double>();
		
		int j = 0;
		while(startNeuron < numberOfNeuron) {
			while(j < input.size()) {
				we.add(j, (((this.car.getGenome().get(this.w) * input.get(j) / n))));
//				System.out.println("genes: " + this.car.getGenome().get(this.w));
//				System.out.println("sensor: " + input.get(j));
//				System.out.println(we.get(j));
				j++;
				this.w++;
			}
			neuronLayer.add(this.sigmoidNeuron(we, this.bias[startNeuron]));
			j = 0;
			we.clear();
			startNeuron++;
		}
		return neuronLayer;
	}

}
